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
-------------------------------------------------- 글쓰기 --------------------------------------------------
INSERT INTO VEHICLE (VNUM, MID, VTITLE, VCONTENT, VFILENAME, VREADCOUNT, VPW, VDATE)
        VALUES (VEHICLE_SEQ.NEXTVAL, 'lsj', '교통1', '본문1', 'nothing.jpg', '0', '111', SYSDATE);
INSERT INTO VEHICLE (VNUM, MID, VTITLE, VCONTENT, VFILENAME, VREADCOUNT, VPW, VDATE)
        VALUES (VEHICLE_SEQ.NEXTVAL, 'lsj', '교통2', '본문2', 'nothing.jpg', '0', '111', SYSDATE);
INSERT INTO VEHICLE (VNUM, MID, VTITLE, VCONTENT, VFILENAME, VREADCOUNT, VPW, VDATE)
        VALUES (VEHICLE_SEQ.NEXTVAL, 'lsj', '교통3', '본문3', 'nothing.jpg', '0', '111', SYSDATE);
-------------------------------------------------- 글목록(startRow 부터 endRow까지) --------------------------------------------------
SELECT * FROM (SELECT ROWNUM RN, A.* FROM
        (SELECT V.* FROM VEHICLE V, MEMBER M WHERE V.MID = M.MID ORDER BY VNUM DESC) A)
        WHERE RN BETWEEN 1 AND 3;
--------------------------------------------------  글 갯수 --------------------------------------------------
SELECT COUNT(*) FROM VEHICLE;
-------------------------------------------------- 조회수 1 올리기 --------------------------------------------------
UPDATE VEHICLE SET VREADCOUNT = VREADCOUNT + 1 WHERE VNUM = 1;
-------------------------------------------------- MID로 글 Dto 보기 --------------------------------------------------
SELECT V.* FROM VEHICLE V, MEMBER M WHERE V.MID = M.MID AND VNUM = 1;
-------------------------------------------------- 글 수정하기 --------------------------------------------------
UPDATE VEHICLE SET VTITLE = '교통1',
                                    VCONTENT = '본문1',
                                    VFILENAME = 'nothing.jpg',
                                    VPW = '111',
                                    VDATE = SYSDATE
                       WHERE VNUM = 1;
-------------------------------------------------- 글 삭제하기 --------------------------------------------------
DELETE FROM VEHICLE WHERE VNUM = '1';
-------------------------------------------------- 커밋(COMMIT) --------------------------------------------------
COMMIT;
