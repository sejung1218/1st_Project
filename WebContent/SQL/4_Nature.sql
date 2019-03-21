----------------------------------------------------------------------------------------------------
SELECT * FROM NATURE;
----------------------------------------------------------------------------------------------------
DROP TABLE NATURE;
----------------------------------------------------------------------------------------------------
DROP SEQUENCE NATURE_SEQ;
----------------------------------------------------------------------------------------------------
CREATE SEQUENCE NATURE_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
----------------------------------------------------------------------------------------------------
CREATE TABLE NATURE (
        nNum NUMBER(5) PRIMARY KEY,
        mId VARCHAR2(30) REFERENCES MEMBER(MID),
        nTitle VARCHAR2(100) NOT NULL,
        nContent VARCHAR2(1000) NOT NULL,
        nFilename VARCHAR2(100) NOT NULL,
        nReadcount NUMBER(5) NOT NULL,
        nPw VARCHAR2(30) NOT NULL,
        nDate DATE  NOT NULL
);
-------------------------------------------------- �۾��� --------------------------------------------------
INSERT INTO NATURE (NNUM, MID, NTITLE, NCONTENT, NFILENAME, NREADCOUNT, NPW, NDATE)
        VALUES (NATURE_SEQ.NEXTVAL, 'lsj', '�ڿ�1', '����1', 'nothing.jpg', '0', '111', SYSDATE);
INSERT INTO NATURE (NNUM, MID, NTITLE, NCONTENT, NFILENAME, NREADCOUNT, NPW, NDATE)
        VALUES (NATURE_SEQ.NEXTVAL, 'lsj', '�ڿ�2', '����2', 'nothing.jpg', '0', '111', SYSDATE);
INSERT INTO NATURE (NNUM, MID, NTITLE, NCONTENT, NFILENAME, NREADCOUNT, NPW, NDATE)
        VALUES (NATURE_SEQ.NEXTVAL, 'lsj', '�ڿ�3', '����3', 'nothing.jpg', '0', '111', SYSDATE);
-------------------------------------------------- �۸��(startRow ���� endRow����) --------------------------------------------------
SELECT * FROM (SELECT ROWNUM RN, A.* FROM
        (SELECT N.* FROM NATURE N, MEMBER M WHERE N.MID = M.MID ORDER BY NNUM DESC) A)
        WHERE RN BETWEEN 1 AND 3;
--------------------------------------------------  �� ���� --------------------------------------------------
SELECT COUNT(*) FROM NATURE;
-------------------------------------------------- ��ȸ�� 1 �ø��� --------------------------------------------------
UPDATE NATURE SET NREADCOUNT = NREADCOUNT + 1 WHERE NNUM = 1;
-------------------------------------------------- MID�� �� Dto ���� --------------------------------------------------
SELECT N.* FROM NATURE N, MEMBER M WHERE N.MID = M.MID AND NNUM = 1;
-------------------------------------------------- �� �����ϱ� --------------------------------------------------
UPDATE NATURE SET NTITLE = '�ڿ�1',
                                   NCONTENT = '����1',
                                   NFILENAME = 'nothing.jpg',
                                   NPW = '111',
                                   NDATE = SYSDATE
                      WHERE NNUM = 1;
-------------------------------------------------- �� �����ϱ� --------------------------------------------------
DELETE FROM NATURE WHERE NNUM = '1';
-------------------------------------------------- Ŀ��(COMMIT) --------------------------------------------------
COMMIT;
