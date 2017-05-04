/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collectiv.minersapi.facade;

import com.collectiv.minersapi.db.handlers.PasswordHandler;
import com.collectiv.minersapi.db.handlers.UserHandler;
import com.collectiv.minersapi.db.models.MinersUsers;
import com.collectiv.minersapi.db.models.UserPasswords;
import com.collectiv.minersapi.dto.ApiKey;
import com.collectiv.minersapi.dto.Credentials;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.impl.crypto.MacProvider;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotAuthorizedException;

/**
 *
 * @author pantelispanka
 */
@RequestScoped
public class AuthenticationFacade {

    private static final Logger LOG = Logger.getLogger(AuthenticationFacade.class.getName());

    @EJB
    UserHandler usersHandler;

    @Inject
    PasswordHandler passwordsHandler;

    private static ConcurrentHashMap ApiKeyMap;

    static {
        ApiKeyMap = new ConcurrentHashMap<>();
    }

    public ApiKey UserAuthentication(Credentials credentials) throws BadRequestException {

        List<MinersUsers> user;
        UserPasswords pass;
        ApiKey apiKey = new ApiKey();
        Key key;
        String token;
        try {
            key = MacProvider.generateKey();
        } catch (Exception e) {
            throw new InternalServerErrorException("Key could not be generated", e);
        }

        String emailToCheck = credentials.getEmail();
        String passwordToCheck = credentials.getPassword();

//        try {
//            user = usersHandler.getByEmail(emailToCheck);
//        } catch (NoResultException e) {
//            throw new BadRequestException("Email Could Not Be Found", e);
//        }

        user = usersHandler.getByEmail(emailToCheck);
        if (user.isEmpty()) {
            throw new BadRequestException("Email Could Not Be Found");
        }
        if (user.size() > 1) {
            throw new InternalServerErrorException("DB Probleb. Please Contact Administrator");
        }

        int user_id = user.get(0).getId();
        pass = passwordsHandler.getPassword(user_id);
        String userStatus = user.get(0).getItemStatus().getStatus().toString();
        String userRole = user.get(0).getUserRole().getRole().toString();
        String userPos = user.get(0).getUserPossition().getPossition().toString();

        if (passwordToCheck.equals(pass.getPassword())
                && userStatus.equals("ACTIVE")) {

            Date expireDate = new Date(System.currentTimeMillis() + 10800000);

            token = Jwts.builder().setIssuer(user.get(0).getId().toString())
                    .setAudience(userRole).setExpiration(expireDate)
                    .signWith(SignatureAlgorithm.HS512, key)
                    .compact();

            ApiKeyMap.put(token, key);
            apiKey.setApi_key(token);
        } else {
            throw new BadRequestException("The Password you provided couldn't be found");
        }

//
        return apiKey;

    }

    public Boolean apiKeyCheck(String apiKey) {

        Key key;
        Claims claims;

        key = (Key) ApiKeyMap.get(apiKey);
        if (key == null) {
            LOG.log(Level.WARNING, "Api key without secret key found");
            throw new BadRequestException("The Api key you provided could not be verified");
        }

        try {
            claims = Jwts.parser().setSigningKey(key)
                    .parseClaimsJws(apiKey).getBody();
            Date expires = claims.getExpiration();
            timeValidator(apiKey, key);
        } catch (Exception e) {
            throw new NotAuthorizedException("The Api Key could not resolve. please login again", e);
        }

        return true;
    }

    public void timeValidator(String api_key, Key key) {

        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(api_key).getBody();
        Date expires = claims.getExpiration();
        if (expires.before(new Date(System.currentTimeMillis()))) {
            ApiKeyMap.remove(api_key);
            throw new BadRequestException("The Api Key Provided Have Expired. Please Login Again");
        }

    }

    public Integer getUserId(String apiKey) {

        Integer user_id;
        Key key = (Key) ApiKeyMap.get(apiKey);

        try {
            Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(apiKey)
                    .getBody();
            user_id = Integer.parseInt(claims.getIssuer());
        } catch (ExpiredJwtException | MalformedJwtException | SignatureException | UnsupportedJwtException | IllegalArgumentException e) {
            throw new BadRequestException("Not A valid User Id Could be retrieved from the key provided", e);
        }

        return user_id;

    }

    public String getUserRole(String apiKey) {

        String userRole;
        Key key = (Key) ApiKeyMap.get(apiKey);

        try {
            Claims claims = Jwts.parser().setSigningKey(key)
                    .parseClaimsJws(apiKey)
                    .getBody();
            userRole = claims.getAudience();
        } catch (ExpiredJwtException | MalformedJwtException | SignatureException | UnsupportedJwtException | IllegalArgumentException e) {
            throw new BadRequestException("Not A valid User Role Could be retrieved from the key provided", e);
        }

        return userRole;

    }

}
