----------------------------------------------------------------------------------------------------
SELECT * FROM FREEBOARD;
----------------------------------------------------------------------------------------------------
DROP TABLE FREEBOARD;
----------------------------------------------------------------------------------------------------
DROP SEQUENCE FREEBOARD_SEQ;
----------------------------------------------------------------------------------------------------
CREATE SEQUENCE FREEBOARD_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
----------------------------------------------------------------------------------------------------
CREATE TABLE FREEBOARD (
        fNum NUMBER(5) PRIMARY KEY,
        mId VARCHAR2(30) REFERENCES MEMBER(MID),
        fTitle VARCHAR2(100) NOT NULL,
        fContent VARCHAR2(1000) NOT NULL,
        fReadcount NUMBER(5) NOT NULL,
        fPw VARCHAR2(30) NOT NULL,
        fGroup NUMBER(5) NOT NULL,
        fStep NUMBER(5) NOT NULL,
        fIndent NUMBER(5) NOT NULL,
        fDate DATE  NOT NULL
);
-------------------------------------------------- �۾��� --------------------------------------------------
INSERT INTO FREEBOARD (FNUM, MID, FTITLE, FCONTENT, FREADCOUNT, FPW, FGROUP, FSTEP, FINDENT, FDATE)
        VALUES (FREEBOARD_SEQ.NEXTVAL, 'lsj', '����1', '����1', '0', '111', FREEBOARD_SEQ.CURRVAL, 0, 0, SYSDATE);
-------------------------------------------------- �� �ۿ� ���� �亯�� --------------------------------------------------
INSERT INTO FREEBOARD (FNUM, MID, FTITLE, FCONTENT, FREADCOUNT, FPW, FGROUP, FSTEP, FINDENT, FDATE)
        VALUES (FREEBOARD_SEQ.NEXTVAL, 'lsj', '����2', '����2', '0', '111', 1, 1, 1, SYSDATE);
-------------------------------------------------- �� �ۿ� ���� �ι�° �亯�� --------------------------------------------------
----------------------- STEP A -----------------------
UPDATE FREEBOARD SET FSTEP = FSTEP+1 WHERE FGROUP = 1 AND FSTEP > 0;
----------------------- �ι�° �亯�� -----------------------
INSERT INTO FREEBOARD (FNUM, MID, FTITLE, FCONTENT, FREADCOUNT, FPW, FGROUP, FSTEP, FINDENT, FDATE)
        VALUES (FREEBOARD_SEQ.NEXTVAL, 'lsj', '����3', '����3', '0', '111', 1, 1, 1, SYSDATE);
-------------------------------------------------- �۸��(startRow ���� endRow����) --------------------------------------------------
SELECT * FROM (SELECT ROWNUM RN, A.* FROM
        (SELECT F.* FROM FREEBOARD F, MEMBER M WHERE F.MID = M.MID ORDER BY FGROUP DESC, FSTEP) A)
        WHERE RN BETWEEN 1 AND 15;
-------------------------------------------------- �� ���� --------------------------------------------------
SELECT COUNT(*) FROM FREEBOARD;
-------------------------------------------------- ��ȸ�� 1 �ø��� --------------------------------------------------
UPDATE FREEBOARD SET FREADCOUNT = FREADCOUNT + 1 WHERE FNUM = 1;
-------------------------------------------------- FNUM�� �� Dto ���� --------------------------------------------------
SELECT F.* FROM FREEBOARD F, MEMBER M WHERE F.MID = M.MID AND FNUM=1;
-------------------------------------------------- �� �����ϱ� --------------------------------------------------
UPDATE FREEBOARD SET FTITLE = '����1',
                                           FCONTENT = '����1',
                                           FPW = '111',
                                           FDATE = SYSDATE
                              WHERE FNUM = 1;
-------------------------------------------------- �� �����ϱ� --------------------------------------------------
DELETE FROM FREEBOARD WHERE FNUM='1';
-------------------------------------------------- Ŀ��(COMMIT) --------------------------------------------------
SELECT COUNT(*) FROM FREEBOARD WHERE MID='lsj';


COMMIT;
