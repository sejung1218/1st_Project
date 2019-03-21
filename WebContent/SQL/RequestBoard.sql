----------------------------------------------------------------------------------------------------
SELECT * FROM REQUESTBOARD;
----------------------------------------------------------------------------------------------------
DROP TABLE REQUESTBOARD;
----------------------------------------------------------------------------------------------------
DROP SEQUENCE REQUESTBOARD_SEQ;
----------------------------------------------------------------------------------------------------
CREATE SEQUENCE REQUESTBOARD_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
----------------------------------------------------------------------------------------------------
CREATE TABLE REQUESTBOARD (
        rNum NUMBER(5) PRIMARY KEY,
        mId VARCHAR2(30) REFERENCES MEMBER(MID),
        rTitle VARCHAR2(100) NOT NULL,
        rContent VARCHAR2(1000) NOT NULL,
        rReadcount NUMBER(5) NOT NULL,
        rPw VARCHAR2(30) NOT NULL,
        rGroup NUMBER(5) NOT NULL,
        rStep NUMBER(5) NOT NULL,
        rIndent NUMBER(5) NOT NULL,
        rDate DATE  NOT NULL
);
-------------------------------------------------- �۾��� --------------------------------------------------
INSERT INTO REQUESTBOARD (RNUM, MID, RTITLE, RCONTENT, RREADCOUNT, RPW, RGROUP, RSTEP, RINDENT, RDATE)
        VALUES (REQUESTBOARD_SEQ.NEXTVAL, 'lsj', '��û1', '����1', '0', '111', REQUESTBOARD_SEQ.CURRVAL, 0, 0, SYSDATE);
-------------------------------------------------- �� �ۿ� ���� �亯�� --------------------------------------------------
INSERT INTO REQUESTBOARD (RNUM, MID, RTITLE, RCONTENT, RREADCOUNT, RPW, RGROUP, RSTEP, RINDENT, RDATE)
        VALUES (REQUESTBOARD_SEQ.NEXTVAL, 'lsj', '��û2', '����2', '0', '111', 1, 1, 1, SYSDATE);
-------------------------------------------------- �� �ۿ� ���� �ι�° �亯�� --------------------------------------------------
----------------------- STEP A -----------------------
UPDATE REQUESTBOARD SET RSTEP = RSTEP+1 WHERE RGROUP = 1 AND RSTEP > 0;
----------------------- �ι�° �亯�� -----------------------
INSERT INTO REQUESTBOARD (RNUM, MID, RTITLE, RCONTENT, RREADCOUNT, RPW, RGROUP, RSTEP, RINDENT, RDATE)
        VALUES (REQUESTBOARD_SEQ.NEXTVAL, 'lsj', '��û3', '����3', '0', '111', 1, 1, 1, SYSDATE);
-------------------------------------------------- �۸��(startRow ���� endRow����) --------------------------------------------------
SELECT * FROM (SELECT ROWNUM RN, A.* FROM 
        (SELECT R.* FROM REQUESTBOARD R, MEMBER M WHERE R.MID = M.MID ORDER BY RGROUP DESC, RSTEP) A)
        WHERE RN BETWEEN 1 AND 3;
-------------------------------------------------- �� ���� --------------------------------------------------
SELECT COUNT(*) FROM REQUESTBOARD;
-------------------------------------------------- ��ȸ�� 1 �ø��� --------------------------------------------------
UPDATE REQUESTBOARD SET RREADCOUNT = RREADCOUNT + 1 WHERE RNUM = 1;
-------------------------------------------------- RNUM���� �� Dto ���� --------------------------------------------------
SELECT R.*, MNAME FROM REQUESTBOARD R, MEMBER M WHERE R.MID = M.MID AND RNUM=1;
-------------------------------------------------- �� �����ϱ� --------------------------------------------------
UPDATE REQUESTBOARD SET RTITLE = '��û1',
                                                  RCONTENT = '��û1',
                                                  RPW = '111',
                                                  RDATE = SYSDATE
                                     WHERE RNUM = 1;
-------------------------------------------------- �� �����ϱ� --------------------------------------------------
DELETE FROM REQUESTBOARD WHERE RNUM='1';
-------------------------------------------------- Ŀ��(COMMIT) --------------------------------------------------
COMMIT;
