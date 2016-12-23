/*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package com.collectiv.minersapi.db.handlers;

import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author pantelispanka
 */
public abstract class AbstractDbHandler<T> {

    private final Class<T> entityClass;

    public AbstractDbHandler(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

}
