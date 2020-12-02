CREATE TABLE POSITIONS (
	ID INT NOT NULL,
	CODE VARCHAR(10) NOT NULL,
	NAME VARCHAR(100) NOT NULL,
	CREATED_BY VARCHAR(100) DEFAULT 'admin' NOT NULL,
	CREATION_DATE DATE DEFAULT CURRENT_DATE NOT NULL,
	LAST_UPDATED_BY VARCHAR(100) DEFAULT 'admin' NOT NULL,
	LAST_UPDATE_DATE DATE DEFAULT CURRENT_DATE NOT NULL,
	CONSTRAINT POSITIONS_PK PRIMARY KEY (ID)
);

INSERT INTO PUBLIC.POSITIONS (ID,CODE,NAME,CREATED_BY,CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATE_DATE) VALUES (1,'GK','Goalkeeper','admin','2020-11-30','admin','2020-11-30');
INSERT INTO PUBLIC.POSITIONS (ID,CODE,NAME,CREATED_BY,CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATE_DATE) VALUES (2,'CD','Central defender','admin','2020-11-30','admin','2020-11-30');
INSERT INTO PUBLIC.POSITIONS (ID,CODE,NAME,CREATED_BY,CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATE_DATE) VALUES (3,'CDtw','Central defender towards wing','admin','2020-11-30','admin','2020-11-30');
INSERT INTO PUBLIC.POSITIONS (ID,CODE,NAME,CREATED_BY,CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATE_DATE) VALUES (4,'CDo','Central defender offensive','admin','2020-11-30','admin','2020-11-30');
INSERT INTO PUBLIC.POSITIONS (ID,CODE,NAME,CREATED_BY,CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATE_DATE) VALUES (5,'WBd','Wing back defensive','admin','2020-11-30','admin','2020-11-30');
INSERT INTO PUBLIC.POSITIONS (ID,CODE,NAME,CREATED_BY,CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATE_DATE) VALUES (6,'WB','Wing back','admin','2020-11-30','admin','2020-11-30');
INSERT INTO PUBLIC.POSITIONS (ID,CODE,NAME,CREATED_BY,CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATE_DATE) VALUES (7,'WBtm','Wing back towards middle','admin','2020-11-30','admin','2020-11-30');
INSERT INTO PUBLIC.POSITIONS (ID,CODE,NAME,CREATED_BY,CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATE_DATE) VALUES (8,'WBo','Wing back offensive','admin','2020-11-30','admin','2020-11-30');
INSERT INTO PUBLIC.POSITIONS (ID,CODE,NAME,CREATED_BY,CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATE_DATE) VALUES (9,'IMd','Inner midfield defensive','admin','2020-11-30','admin','2020-11-30');
INSERT INTO PUBLIC.POSITIONS (ID,CODE,NAME,CREATED_BY,CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATE_DATE) VALUES (10,'IM','Inner midfield','admin','2020-11-30','admin','2020-11-30');
INSERT INTO PUBLIC.POSITIONS (ID,CODE,NAME,CREATED_BY,CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATE_DATE) VALUES (11,'IMtw','Inner midfield towards wing','admin','2020-11-30','admin','2020-11-30');
INSERT INTO PUBLIC.POSITIONS (ID,CODE,NAME,CREATED_BY,CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATE_DATE) VALUES (12,'IMo','Inner midfield','admin','2020-11-30','admin','2020-11-30');
INSERT INTO PUBLIC.POSITIONS (ID,CODE,NAME,CREATED_BY,CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATE_DATE) VALUES (13,'Wd','Winger defensive','admin','2020-11-30','admin','2020-11-30');
INSERT INTO PUBLIC.POSITIONS (ID,CODE,NAME,CREATED_BY,CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATE_DATE) VALUES (14,'W','Winger','admin','2020-11-30','admin','2020-11-30');
INSERT INTO PUBLIC.POSITIONS (ID,CODE,NAME,CREATED_BY,CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATE_DATE) VALUES (15,'Wtm','Winger towards middle','admin','2020-11-30','admin','2020-11-30');
INSERT INTO PUBLIC.POSITIONS (ID,CODE,NAME,CREATED_BY,CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATE_DATE) VALUES (16,'Wo','Winger offensive','admin','2020-11-30','admin','2020-11-30');
INSERT INTO PUBLIC.POSITIONS (ID,CODE,NAME,CREATED_BY,CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATE_DATE) VALUES (17,'Fd','Forward defensive','admin','2020-11-30','admin','2020-11-30');
INSERT INTO PUBLIC.POSITIONS (ID,CODE,NAME,CREATED_BY,CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATE_DATE) VALUES (18,'F','Forward','admin','2020-11-30','admin','2020-11-30');
INSERT INTO PUBLIC.POSITIONS (ID,CODE,NAME,CREATED_BY,CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATE_DATE) VALUES (19,'Ftw','Forward towards wing','admin','2020-11-30','admin','2020-11-30');
INSERT INTO PUBLIC.POSITIONS (ID,CODE,NAME,CREATED_BY,CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATE_DATE) VALUES (20,'TdF','Technical defensive forward','admin','2020-11-30','admin','2020-11-30');