/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  pantelispanka
 * Created: Jan 15, 2017
 */

INSERT INTO item_status VALUES (DEFAULT, 'ACTIVE'), (DEFAULT, 'INACTIVE'), (DEFAULT, 'BANNED'), (DEFAULT, 'DELETED'), (DEFAULT, 'VISIBLE'),(DEFAULT, 'INVISIBLE');
INSERT INTO miners_users values (DEFAULT, DEFAULT, 'minerssupervisor@miners.com', 1,1,1);
INSERT INTO possition VALUES (DEFAULT, 'HEAD'), (DEFAULT, 'SIMPLE');
INSERT INTO user_passwords VALUES (DEFAULT, 'miners', 1);
INSERT INTO user_role VALUES (DEFAULT, 'ADMINISTRATOR'), (DEFAULT, 'DEFAULT_USER');
