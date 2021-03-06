CREATE TABLE TEAM_PARTS (
	ID INT NOT NULL,
	CODE VARCHAR(10) NOT NULL,
	NAME VARCHAR(100) NOT NULL,
	PARENT_TEAM_PART_ID INT,
	OPPOSITE_TEAM_PART_ID INT,
	CREATED_BY VARCHAR(100) DEFAULT 'admin' NOT NULL,
	CREATION_DATE DATE DEFAULT CURRENT_DATE NOT NULL,
	LAST_UPDATED_BY VARCHAR(100) DEFAULT 'admin' NOT NULL,
	LAST_UPDATE_DATE DATE DEFAULT CURRENT_DATE NOT NULL,
	CONSTRAINT TEAM_PARTS_PK PRIMARY KEY (ID),
	CONSTRAINT TEAM_PARTS_PARENT_FK FOREIGN KEY (PARENT_TEAM_PART_ID) REFERENCES TEAM_PARTS(ID),
	CONSTRAINT TEAM_PARTS_OPPOSITE_FK FOREIGN KEY (OPPOSITE_TEAM_PART_ID) REFERENCES TEAM_PARTS(ID)
);

INSERT INTO TEAM_PARTS (ID,CODE,NAME,PARENT_TEAM_PART_ID,OPPOSITE_TEAM_PART_ID,CREATED_BY,CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATE_DATE) VALUES (1,'Mid','Midfield',NULL,NULL,'admin','2020-11-30','admin','2020-11-30');
INSERT INTO TEAM_PARTS (ID,CODE,NAME,PARENT_TEAM_PART_ID,OPPOSITE_TEAM_PART_ID,CREATED_BY,CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATE_DATE) VALUES (2,'CDef','Central defence',NULL,NULL,'admin','2020-11-30','admin','2020-11-30');
INSERT INTO TEAM_PARTS (ID,CODE,NAME,PARENT_TEAM_PART_ID,OPPOSITE_TEAM_PART_ID,CREATED_BY,CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATE_DATE) VALUES (3,'SDef','Side defence',NULL,NULL,'admin','2020-11-30','admin','2020-11-30');
INSERT INTO TEAM_PARTS (ID,CODE,NAME,PARENT_TEAM_PART_ID,OPPOSITE_TEAM_PART_ID,CREATED_BY,CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATE_DATE) VALUES (4,'LDef','Left defence',3,NULL,'admin','2020-11-30','admin','2020-11-30');
INSERT INTO TEAM_PARTS (ID,CODE,NAME,PARENT_TEAM_PART_ID,OPPOSITE_TEAM_PART_ID,CREATED_BY,CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATE_DATE) VALUES (5,'RDef','Right defence',3,NULL,'admin','2020-11-30','admin','2020-11-30');
INSERT INTO TEAM_PARTS (ID,CODE,NAME,PARENT_TEAM_PART_ID,OPPOSITE_TEAM_PART_ID,CREATED_BY,CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATE_DATE) VALUES (6,'CAt','Central attack',NULL,NULL,'admin','2020-11-30','admin','2020-11-30');
INSERT INTO TEAM_PARTS (ID,CODE,NAME,PARENT_TEAM_PART_ID,OPPOSITE_TEAM_PART_ID,CREATED_BY,CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATE_DATE) VALUES (7,'SAt','Side attack',NULL,NULL,'admin','2020-11-30','admin','2020-11-30');
INSERT INTO TEAM_PARTS (ID,CODE,NAME,PARENT_TEAM_PART_ID,OPPOSITE_TEAM_PART_ID,CREATED_BY,CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATE_DATE) VALUES (8,'LAt','Left attack',7,NULL,'admin','2020-11-30','admin','2020-11-30');
INSERT INTO TEAM_PARTS (ID,CODE,NAME,PARENT_TEAM_PART_ID,OPPOSITE_TEAM_PART_ID,CREATED_BY,CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATE_DATE) VALUES (9,'RAt','Right attack',7,NULL,'admin','2020-11-30','admin','2020-11-30');
INSERT INTO TEAM_PARTS (ID,CODE,NAME,PARENT_TEAM_PART_ID,OPPOSITE_TEAM_PART_ID,CREATED_BY,CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATE_DATE) VALUES (10,'OsAt','Opposite side attack',NULL,NULL,'admin','2020-11-30','admin','2020-11-30');
INSERT INTO TEAM_PARTS (ID,CODE,NAME,PARENT_TEAM_PART_ID,OPPOSITE_TEAM_PART_ID,CREATED_BY,CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATE_DATE) VALUES (11,'OsLAt','Opposite left attack',10,9,'admin','2020-11-30','admin','2020-11-30');
INSERT INTO TEAM_PARTS (ID,CODE,NAME,PARENT_TEAM_PART_ID,OPPOSITE_TEAM_PART_ID,CREATED_BY,CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATE_DATE) VALUES (12,'OsRAt','Opposite right attack',10,8,'admin','2020-11-30','admin','2020-11-30');