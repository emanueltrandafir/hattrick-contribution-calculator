CREATE TABLE SKILLS (
	ID INT NOT NULL,
	NAME VARCHAR(100) NOT NULL,
	DESCRIPTION VARCHAR(1000) NOT NULL,
	CREATED_BY VARCHAR(100) DEFAULT 'admin' NOT NULL,
	CREATION_DATE DATE DEFAULT CURRENT_DATE NOT NULL,
	LAST_UPDATED_BY VARCHAR(100) DEFAULT 'admin' NOT NULL,
	LAST_UPDATE_DATE DATE DEFAULT CURRENT_DATE NOT NULL,
	CONSTRAINT SKILLS_PK PRIMARY KEY (ID)
);

INSERT INTO SKILLS (ID,NAME,DESCRIPTION,CREATED_BY,CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATE_DATE) VALUES (1,'Stamina','Decides how much of his ability to perform a player loses during the course of the match.','admin','2020-11-30','admin','2020-11-30');
INSERT INTO SKILLS (ID,NAME,DESCRIPTION,CREATED_BY,CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATE_DATE) VALUES (2,'Playmaking','The ability to control the ball and turn it into scoring opportunities.','admin','2020-11-30','admin','2020-11-30');
INSERT INTO SKILLS (ID,NAME,DESCRIPTION,CREATED_BY,CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATE_DATE) VALUES (3,'Scoring','The ball is supposed to go into the net.','admin','2020-11-30','admin','2020-11-30');
INSERT INTO SKILLS (ID,NAME,DESCRIPTION,CREATED_BY,CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATE_DATE) VALUES (4,'Winger','The ability to finish off scoring opportunities by advancing down the sides.','admin','2020-11-30','admin','2020-11-30');
INSERT INTO SKILLS (ID,NAME,DESCRIPTION,CREATED_BY,CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATE_DATE) VALUES (5,'Goalkeeping','The ball should not make it into your own net.','admin','2020-11-30','admin','2020-11-30');
INSERT INTO SKILLS (ID,NAME,DESCRIPTION,CREATED_BY,CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATE_DATE) VALUES (6,'Passing','Players who know how to play the decisive pass are a great help for the team''s attack.','admin','2020-11-30','admin','2020-11-30');
INSERT INTO SKILLS (ID,NAME,DESCRIPTION,CREATED_BY,CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATE_DATE) VALUES (7,'Defending','The ability to stop opponent attacks.','admin','2020-11-30','admin','2020-11-30');
INSERT INTO SKILLS (ID,NAME,DESCRIPTION,CREATED_BY,CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATE_DATE) VALUES (8,'Set pieces','The outcome of your free kicks and penalties depends on how skilled your set pieces taker is.','admin','2020-11-30','admin','2020-11-30');