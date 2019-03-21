----------------------------------------------------------------------------------------------------
SELECT * FROM TECHNIQUE;
----------------------------------------------------------------------------------------------------
DROP TABLE TECHNIQUE;
----------------------------------------------------------------------------------------------------
DROP SEQUENCE TECHNIQUE_SEQ;
----------------------------------------------------------------------------------------------------
CREATE SEQUENCE TECHNIQUE_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
----------------------------------------------------------------------------------------------------
CREATE TABLE TECHNIQUE (
        tNum NUMBER(5) PRIMARY KEY,
        mId VARCHAR2(30) REFERENCES MEMBER(MID),
        tTitle VARCHAR2(100) NOT NULL,
        tContent VARCHAR2(1000) NOT NULL,
        tFilename VARCHAR2(100) NOT NULL,
        tReadcount NUMBER(5) NOT NULL,
        tPw VARCHAR2(30) NOT NULL,
        tDate DATE  NOT NULL
);
-------------------------------------------------- �۾��� --------------------------------------------------
INSERT INTO TECHNIQUE (TNUM, MID, TTITLE, TCONTENT, TFILENAME, TREADCOUNT, TPW, TDATE)
        VALUES (TECHNIQUE_SEQ.NEXTVAL, 'lsj', '���1', '����1', 'nothing.jpg', '0', '111', SYSDATE);
INSERT INTO TECHNIQUE (TNUM, MID, TTITLE, TCONTENT, TFILENAME, TREADCOUNT, TPW, TDATE)
        VALUES (TECHNIQUE_SEQ.NEXTVAL, 'lsj', '���2', '����2', 'nothing.jpg', '0', '111', SYSDATE);
INSERT INTO TECHNIQUE (TNUM, MID, TTITLE, TCONTENT, TFILENAME, TREADCOUNT, TPW, TDATE)
        VALUES (TECHNIQUE_SEQ.NEXTVAL, 'lsj', '���3', '����3', 'nothing.jpg', '0', '111', SYSDATE);
-------------------------------------------------- �۸��(startRow ���� endRow����) --------------------------------------------------
SELECT * FROM (SELECT ROWNUM RN, A.* FROM
        (SELECT T.* FROM TECHNIQUE T, MEMBER M WHERE T.MID = M.MID ORDER BY TNUM DESC) A)
        WHERE RN BETWEEN 1 AND 3;
--------------------------------------------------  �� ���� --------------------------------------------------
SELECT COUNT(*) FROM TECHNIQUE;
-------------------------------------------------- ��ȸ�� 1 �ø��� --------------------------------------------------
UPDATE TECHNIQUE SET TREADCOUNT = TREADCOUNT + 1 WHERE TNUM = 1;
-------------------------------------------------- MID�� �� Dto ���� --------------------------------------------------
SELECT T.* FROM TECHNIQUE T, MEMBER M WHERE T.MID = M.MID AND TNUM = 1;
-------------------------------------------------- �� �����ϱ� --------------------------------------------------
UPDATE TECHNIQUE SET TTITLE = '���1',
                                         TCONTENT = '����1',
                                         TFILENAME = 'nothing.jpg',
                                         TPW = '111',
                                         TDATE = SYSDATE
                            WHERE TNUM = 1;
-------------------------------------------------- �� �����ϱ� --------------------------------------------------
DELETE FROM TECHNIQUE WHERE TNUM = '1';
-------------------------------------------------- Ŀ��(COMMIT) --------------------------------------------------
COMMIT;


SELECT * FROM MVC_MEMBER;
