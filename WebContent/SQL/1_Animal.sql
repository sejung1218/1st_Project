----------------------------------------------------------------------------------------------------
SELECT * FROM ANIMAL;
----------------------------------------------------------------------------------------------------
DROP TABLE ANIMAL;
----------------------------------------------------------------------------------------------------
DROP SEQUENCE ANIMAL_SEQ;
----------------------------------------------------------------------------------------------------
CREATE SEQUENCE ANIMAL_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
----------------------------------------------------------------------------------------------------
CREATE TABLE ANIMAL (
        aNum NUMBER(5) PRIMARY KEY,
        mId VARCHAR2(30) REFERENCES MEMBER(MID),
        aTitle VARCHAR2(100) NOT NULL,
        aContent VARCHAR2(1000) NOT NULL,
        aFilename VARCHAR2(100) NOT NULL,
        aReadcount NUMBER(5) NOT NULL,
        aPw VARCHAR2(30) NOT NULL,
        aDate DATE  NOT NULL
);
-------------------------------------------------- �۾��� --------------------------------------------------
INSERT INTO ANIMAL (ANUM, MID, ATITLE, ACONTENT, AFILENAME, AREADCOUNT, APW, ADATE)
        VALUES (ANIMAL_SEQ.NEXTVAL, 'lsj', '����1', '����1', 'nothing.jpg', '0', '111', SYSDATE);
INSERT INTO ANIMAL (ANUM, MID, ATITLE, ACONTENT, AFILENAME, AREADCOUNT, APW, ADATE)
        VALUES (ANIMAL_SEQ.NEXTVAL, 'lsj', '����2', '����2', 'nothing.jpg', '0', '111', SYSDATE);
INSERT INTO ANIMAL (ANUM, MID, ATITLE, ACONTENT, AFILENAME, AREADCOUNT, APW, ADATE)
        VALUES (ANIMAL_SEQ.NEXTVAL, 'lsj', '����3', '����3', 'nothing.jpg', '0', '111', SYSDATE);
-------------------------------------------------- �۸��(startRow ���� endRow����) --------------------------------------------------
SELECT * FROM (SELECT ROWNUM RN, A.* FROM
        (SELECT A.* FROM ANIMAL A, MEMBER M WHERE A.MID = M.MID ORDER BY ANUM DESC) A)
        WHERE RN BETWEEN 1 AND 3;
--------------------------------------------------  �� ���� --------------------------------------------------
SELECT COUNT(*) FROM ANIMAL;
-------------------------------------------------- ��ȸ�� 1 �ø��� --------------------------------------------------
UPDATE ANIMAL SET AREADCOUNT = AREADCOUNT + 1 WHERE ANUM = 1;
-------------------------------------------------- MID�� �� Dto ���� --------------------------------------------------
SELECT A.* FROM ANIMAL A, MEMBER M WHERE A.MID = M.MID AND ANUM = 4;
-------------------------------------------------- �� �����ϱ� --------------------------------------------------
UPDATE ANIMAL SET ATITLE = '����1',
                                  ACONTENT = '����1',
                                  AFILENAME = 'nothing.jpg',
                                  APW = '111',
                                  ADATE = SYSDATE
                     WHERE ANUM = 1;
-------------------------------------------------- �� �����ϱ� --------------------------------------------------
DELETE FROM ANIMAL WHERE ANUM = '18';
-------------------------------------------------- Ŀ��(COMMIT) --------------------------------------------------
COMMIT;
SELECT * FROM ANIMAL;

BEGIN
FOR i IN 1..20 LOOP
INSERT INTO ANIMAL(ANUM, MID, ATITLE, ACONTENT, AFILENAME, AREADCOUNT, APW, ADATE)
VALUES (ANIMAL_SEQ.NEXTVAL, 'lsj', CONCAT('����', i), CONCAT('����', i), CONCAT(i, '.jpg'), 0, 111, SYSDATE);
END LOOP;
END;
