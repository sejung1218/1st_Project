----------------------------------------------------------------------------------------------------
SELECT * FROM SPORTS;
----------------------------------------------------------------------------------------------------
DROP TABLE SPORTS;
----------------------------------------------------------------------------------------------------
DROP SEQUENCE SPORTS_SEQ;
----------------------------------------------------------------------------------------------------
CREATE SEQUENCE SPORTS_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
----------------------------------------------------------------------------------------------------
CREATE TABLE SPORTS (
        sNum NUMBER(5) PRIMARY KEY,
        mId VARCHAR2(30) REFERENCES MEMBER(MID),
        sTitle VARCHAR2(100) NOT NULL,
        sContent VARCHAR2(1000) NOT NULL,
        sFilename VARCHAR2(100) NOT NULL,
        sReadcount NUMBER(5) NOT NULL,
        sPw VARCHAR2(30) NOT NULL,
        sDate DATE  NOT NULL
);
-------------------------------------------------- �۾��� --------------------------------------------------
INSERT INTO SPORTS (SNUM, MID, STITLE, SCONTENT, SFILENAME, SREADCOUNT, SPW, SDATE)
        VALUES (SPORTS_SEQ.NEXTVAL, 'lsj', '������1', '����1', 'nothing.jpg', '0', '111', SYSDATE);
INSERT INTO SPORTS (SNUM, MID, STITLE, SCONTENT, SFILENAME, SREADCOUNT, SPW, SDATE)
        VALUES (SPORTS_SEQ.NEXTVAL, 'lsj', '������2', '����2', 'nothing.jpg', '0', '111', SYSDATE);
INSERT INTO SPORTS (SNUM, MID, STITLE, SCONTENT, SFILENAME, SREADCOUNT, SPW, SDATE)
        VALUES (SPORTS_SEQ.NEXTVAL, 'lsj', '������3', '����3', 'nothing.jpg', '0', '111', SYSDATE);
-------------------------------------------------- �۸��(startRow ���� endRow����) --------------------------------------------------
SELECT * FROM (SELECT ROWNUM RN, A.* FROM
        (SELECT S.* FROM SPORTS S, MEMBER M WHERE S.MID = M.MID ORDER BY SNUM DESC) A)
        WHERE RN BETWEEN 1 AND 3;
--------------------------------------------------  �� ���� --------------------------------------------------
SELECT COUNT(*) FROM SPORTS;
-------------------------------------------------- ��ȸ�� 1 �ø��� --------------------------------------------------
UPDATE SPORTS SET SREADCOUNT = SREADCOUNT + 1 WHERE SNUM = 1;
-------------------------------------------------- MID�� �� Dto ���� --------------------------------------------------
SELECT S.* FROM SPORTS S, MEMBER M WHERE S.MID = M.MID AND SNUM = 1;
-------------------------------------------------- �� �����ϱ� --------------------------------------------------
UPDATE SPORTS SET STITLE = '������1',
                                    SCONTENT = '����1',
                                    SFILENAME = 'nothing.jpg',
                                    SPW = '111',
                                    SDATE = SYSDATE
                       WHERE SNUM = 1;
-------------------------------------------------- �� �����ϱ� --------------------------------------------------
DELETE FROM SPORTS WHERE SNUM = '1';
-------------------------------------------------- Ŀ��(COMMIT) --------------------------------------------------
COMMIT;
