----------------------------------------------------------------------------------------------------
SELECT * FROM ILLUST;
----------------------------------------------------------------------------------------------------
DROP TABLE ILLUST;
----------------------------------------------------------------------------------------------------
DROP SEQUENCE ILLUST_SEQ;
----------------------------------------------------------------------------------------------------
CREATE SEQUENCE ILLUST_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
----------------------------------------------------------------------------------------------------
CREATE TABLE ILLUST (
        iNum NUMBER(5) PRIMARY KEY,
        mId VARCHAR2(30) REFERENCES MEMBER(MID),
        iTitle VARCHAR2(100) NOT NULL,
        iContent VARCHAR2(1000) NOT NULL,
        iFilename VARCHAR2(100) NOT NULL,
        iReadcount NUMBER(5) NOT NULL,
        iPw VARCHAR2(30) NOT NULL,
        iDate DATE  NOT NULL
);
-------------------------------------------------- �۾��� --------------------------------------------------
INSERT INTO ILLUST (INUM, MID, ITITLE, ICONTENT, IFILENAME, IREADCOUNT, IPW, IDATE)
        VALUES (ILLUST_SEQ.NEXTVAL, 'lsj', '�Ϸ���Ʈ1', '����1', 'nothing.jpg', '0', '111', SYSDATE);
INSERT INTO ILLUST (INUM, MID, ITITLE, ICONTENT, IFILENAME, IREADCOUNT, IPW, IDATE)
        VALUES (ILLUST_SEQ.NEXTVAL, 'lsj', '�Ϸ���Ʈ2', '����2', 'nothing.jpg', '0', '111', SYSDATE);
INSERT INTO ILLUST (INUM, MID, ITITLE, ICONTENT, IFILENAME, IREADCOUNT, IPW, IDATE)
        VALUES (ILLUST_SEQ.NEXTVAL, 'lsj', '�Ϸ���Ʈ3', '����3', 'nothing.jpg', '0', '111', SYSDATE);
-------------------------------------------------- �۸��(startRow ���� endRow����) --------------------------------------------------
SELECT * FROM (SELECT ROWNUM RN, A.* FROM
        (SELECT I.* FROM ILLUST I, MEMBER M WHERE I.MID = M.MID ORDER BY INUM DESC) A)
        WHERE RN BETWEEN 1 AND 3;
--------------------------------------------------  �� ���� --------------------------------------------------
SELECT COUNT(*) FROM ILLUST;
-------------------------------------------------- ��ȸ�� 1 �ø��� --------------------------------------------------
UPDATE ILLUST SET IREADCOUNT = IREADCOUNT + 1 WHERE INUM = 1;
-------------------------------------------------- MID�� �� Dto ���� --------------------------------------------------
SELECT I.* FROM ILLUST I, MEMBER M WHERE I.MID = M.MID AND INUM = 1;
-------------------------------------------------- �� �����ϱ� --------------------------------------------------
UPDATE ILLUST SET ITITLE = '�Ϸ���Ʈ1',
                                 ICONTENT = '����1',
                                 IFILENAME = 'nothing.jpg',
                                 IPW = '111',
                                 IDATE = SYSDATE
                    WHERE INUM = 1;
-------------------------------------------------- �� �����ϱ� --------------------------------------------------
DELETE FROM ILLUST WHERE INUM = '1';
-------------------------------------------------- Ŀ��(COMMIT) --------------------------------------------------
COMMIT;



BEGIN
FOR i IN 1..15 LOOP
INSERT INTO ILLUST(INUM, MID, ITITLE, ICONTENT, IFILENAME, IREADCOUNT, IPW, IDATE)
VALUES (ILLUST_SEQ.NEXTVAL, 'lsj', CONCAT('����', i), CONCAT('����', i), CONCAT(i, '.jpg'), 0, 111, SYSDATE);
END LOOP;
END;
