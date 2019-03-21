----------------------------------------------------------------------------------------------------
SELECT * FROM VEHICLE;
----------------------------------------------------------------------------------------------------
DROP TABLE VEHICLE;
----------------------------------------------------------------------------------------------------
DROP SEQUENCE VEHICLE_SEQ;
----------------------------------------------------------------------------------------------------
CREATE SEQUENCE VEHICLE_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
----------------------------------------------------------------------------------------------------
CREATE TABLE VEHICLE (
        vNum NUMBER(5) PRIMARY KEY,
        mId VARCHAR2(30) REFERENCES MEMBER(MID),
        vTitle VARCHAR2(100) NOT NULL,
        vContent VARCHAR2(1000) NOT NULL,
        vFilename VARCHAR2(100) NOT NULL,
        vReadcount NUMBER(5) NOT NULL,
        vPw VARCHAR2(30) NOT NULL,
        vDate DATE  NOT NULL
);
-------------------------------------------------- �۾��� --------------------------------------------------
INSERT INTO VEHICLE (VNUM, MID, VTITLE, VCONTENT, VFILENAME, VREADCOUNT, VPW, VDATE)
        VALUES (VEHICLE_SEQ.NEXTVAL, 'lsj', '����1', '����1', 'nothing.jpg', '0', '111', SYSDATE);
INSERT INTO VEHICLE (VNUM, MID, VTITLE, VCONTENT, VFILENAME, VREADCOUNT, VPW, VDATE)
        VALUES (VEHICLE_SEQ.NEXTVAL, 'lsj', '����2', '����2', 'nothing.jpg', '0', '111', SYSDATE);
INSERT INTO VEHICLE (VNUM, MID, VTITLE, VCONTENT, VFILENAME, VREADCOUNT, VPW, VDATE)
        VALUES (VEHICLE_SEQ.NEXTVAL, 'lsj', '����3', '����3', 'nothing.jpg', '0', '111', SYSDATE);
-------------------------------------------------- �۸��(startRow ���� endRow����) --------------------------------------------------
SELECT * FROM (SELECT ROWNUM RN, A.* FROM
        (SELECT V.* FROM VEHICLE V, MEMBER M WHERE V.MID = M.MID ORDER BY VNUM DESC) A)
        WHERE RN BETWEEN 1 AND 3;
--------------------------------------------------  �� ���� --------------------------------------------------
SELECT COUNT(*) FROM VEHICLE;
-------------------------------------------------- ��ȸ�� 1 �ø��� --------------------------------------------------
UPDATE VEHICLE SET VREADCOUNT = VREADCOUNT + 1 WHERE VNUM = 1;
-------------------------------------------------- MID�� �� Dto ���� --------------------------------------------------
SELECT V.* FROM VEHICLE V, MEMBER M WHERE V.MID = M.MID AND VNUM = 1;
-------------------------------------------------- �� �����ϱ� --------------------------------------------------
UPDATE VEHICLE SET VTITLE = '����1',
                                    VCONTENT = '����1',
                                    VFILENAME = 'nothing.jpg',
                                    VPW = '111',
                                    VDATE = SYSDATE
                       WHERE VNUM = 1;
-------------------------------------------------- �� �����ϱ� --------------------------------------------------
DELETE FROM VEHICLE WHERE VNUM = '1';
-------------------------------------------------- Ŀ��(COMMIT) --------------------------------------------------
COMMIT;
