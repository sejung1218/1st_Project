----------------------------------------------------------------------------------------------------
SELECT * FROM LANDMARK;
----------------------------------------------------------------------------------------------------
DROP TABLE LANDMARK;
----------------------------------------------------------------------------------------------------
DROP SEQUENCE LANDMARK_SEQ;
----------------------------------------------------------------------------------------------------
CREATE SEQUENCE LANDMARK_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
----------------------------------------------------------------------------------------------------
CREATE TABLE LANDMARK (
        lNum NUMBER(5) PRIMARY KEY,
        mId VARCHAR2(30) REFERENCES MEMBER(MID),
        lTitle VARCHAR2(100) NOT NULL,
        lContent VARCHAR2(1000) NOT NULL,
        lFilename VARCHAR2(100) NOT NULL,
        lReadcount NUMBER(5) NOT NULL,
        lPw VARCHAR2(30) NOT NULL,
        lDate DATE  NOT NULL
);
-------------------------------------------------- �۾��� --------------------------------------------------
INSERT INTO LANDMARK (LNUM, MID, LTITLE, LCONTENT, LFILENAME, LREADCOUNT, LPW, LDATE)
        VALUES (LANDMARK_SEQ.NEXTVAL, 'lsj', '�ǹ�1', '����1', 'nothing.jpg', '0', '111', SYSDATE);
INSERT INTO LANDMARK (LNUM, MID, LTITLE, LCONTENT, LFILENAME, LREADCOUNT, LPW, LDATE)
        VALUES (LANDMARK_SEQ.NEXTVAL, 'lsj', '�ǹ�2', '����2', 'nothing.jpg', '0', '111', SYSDATE);
INSERT INTO LANDMARK (LNUM, MID, LTITLE, LCONTENT, LFILENAME, LREADCOUNT, LPW, LDATE)
        VALUES (LANDMARK_SEQ.NEXTVAL, 'lsj', '�ǹ�3', '����3', 'nothing.jpg', '0', '111', SYSDATE);
-------------------------------------------------- �۸��(startRow ���� endRow����) --------------------------------------------------
SELECT * FROM (SELECT ROWNUM RN, A.* FROM
        (SELECT L.* FROM LANDMARK L, MEMBER M WHERE L.MID = M.MID ORDER BY LNUM DESC) A)
        WHERE RN BETWEEN 1 AND 3;
--------------------------------------------------  �� ���� --------------------------------------------------
SELECT COUNT(*) FROM LANDMARK;
-------------------------------------------------- ��ȸ�� 1 �ø��� --------------------------------------------------
UPDATE LANDMARK SET LREADCOUNT = LREADCOUNT + 1 WHERE LNUM = 1;
-------------------------------------------------- MID�� �� Dto ���� --------------------------------------------------
SELECT L.* FROM LANDMARK L, MEMBER M WHERE L.MID = M.MID AND LNUM = 1;
-------------------------------------------------- �� �����ϱ� --------------------------------------------------
UPDATE LANDMARK SET LTITLE = '�ǹ�1',
                                        LCONTENT = '����1',
                                        LFILENAME = 'nothing.jpg',
                                        LPW = '111',
                                        LDATE = SYSDATE
                           WHERE LNUM = 1;
-------------------------------------------------- �� �����ϱ� --------------------------------------------------
DELETE FROM LANDMARK WHERE LNUM = '1';
-------------------------------------------------- Ŀ��(COMMIT) --------------------------------------------------
COMMIT;
