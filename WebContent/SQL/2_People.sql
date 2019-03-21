----------------------------------------------------------------------------------------------------
SELECT * FROM PEOPLE;
----------------------------------------------------------------------------------------------------
DROP TABLE PEOPLE;
----------------------------------------------------------------------------------------------------
DROP SEQUENCE PEOPLE_SEQ;
----------------------------------------------------------------------------------------------------
CREATE SEQUENCE PEOPLE_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
----------------------------------------------------------------------------------------------------
CREATE TABLE PEOPLE (
        pNum NUMBER(5) PRIMARY KEY,
        mId VARCHAR2(30) REFERENCES MEMBER(MID),
        pTitle VARCHAR2(100) NOT NULL,
        pContent VARCHAR2(1000) NOT NULL,
        pFilename VARCHAR2(100) NOT NULL,
        pReadcount NUMBER(5) NOT NULL,
        pPw VARCHAR2(30) NOT NULL,
        pDate DATE  NOT NULL
);
-------------------------------------------------- �۾��� --------------------------------------------------
INSERT INTO PEOPLE (PNUM, MID, PTITLE, PCONTENT, PFILENAME, PREADCOUNT, PPW, PDATE)
        VALUES (PEOPLE_SEQ.NEXTVAL, 'lsj', '�ι�1', '����1', 'nothing.jpg', '0', '111', SYSDATE);
INSERT INTO PEOPLE (PNUM, MID, PTITLE, PCONTENT, PFILENAME, PREADCOUNT, PPW, PDATE)
        VALUES (PEOPLE_SEQ.NEXTVAL, 'lsj', '�ι�2', '����2', 'nothing.jpg', '0', '111', SYSDATE);
INSERT INTO PEOPLE (PNUM, MID, PTITLE, PCONTENT, PFILENAME, PREADCOUNT, PPW, PDATE)
        VALUES (PEOPLE_SEQ.NEXTVAL, 'lsj', '�ι�3', '����3', 'nothing.jpg', '0', '111', SYSDATE);
-------------------------------------------------- �۸��(startRow ���� endRow����) --------------------------------------------------
SELECT * FROM (SELECT ROWNUM RN, A.* FROM
        (SELECT P.* FROM PEOPLE P, MEMBER M WHERE P.MID = M.MID ORDER BY PNUM DESC) A)
        WHERE RN BETWEEN 1 AND 3;
--------------------------------------------------  �� ���� --------------------------------------------------
SELECT COUNT(*) FROM PEOPLE;
-------------------------------------------------- ��ȸ�� 1 �ø��� --------------------------------------------------
UPDATE PEOPLE SET PREADCOUNT = PREADCOUNT + 1 WHERE PNUM = 1;
-------------------------------------------------- MID�� �� Dto ���� --------------------------------------------------
SELECT P.* FROM PEOPLE P, MEMBER M WHERE P.MID = M.MID AND PNUM = 1;
-------------------------------------------------- �� �����ϱ� --------------------------------------------------
UPDATE PEOPLE SET PTITLE = '�ι�1',
                                   PCONTENT = '����1',
                                   PFILENAME = 'nothing.jpg',
                                   PPW = '111',
                                   PDATE = SYSDATE
                      WHERE PNUM = 1;
-------------------------------------------------- �� �����ϱ� --------------------------------------------------
DELETE FROM PEOPLE WHERE PNUM = '1';
-------------------------------------------------- Ŀ��(COMMIT) --------------------------------------------------
COMMIT;


BEGIN
FOR i IN 1..15 LOOP
INSERT INTO PEOPLE(PNUM, MID, PTITLE, PCONTENT, PFILENAME, PREADCOUNT, PPW, PDATE)
VALUES (PEOPLE_SEQ.NEXTVAL, 'lsj', CONCAT('����', i), CONCAT('����', i), CONCAT(i, '.jpg'), 0, 111, SYSDATE);
END LOOP;

END;


