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
-------------------------------------------------- 글쓰기 --------------------------------------------------
INSERT INTO SPORTS (SNUM, MID, STITLE, SCONTENT, SFILENAME, SREADCOUNT, SPW, SDATE)
        VALUES (SPORTS_SEQ.NEXTVAL, 'lsj', '스포츠1', '본문1', 'nothing.jpg', '0', '111', SYSDATE);
INSERT INTO SPORTS (SNUM, MID, STITLE, SCONTENT, SFILENAME, SREADCOUNT, SPW, SDATE)
        VALUES (SPORTS_SEQ.NEXTVAL, 'lsj', '스포츠2', '본문2', 'nothing.jpg', '0', '111', SYSDATE);
INSERT INTO SPORTS (SNUM, MID, STITLE, SCONTENT, SFILENAME, SREADCOUNT, SPW, SDATE)
        VALUES (SPORTS_SEQ.NEXTVAL, 'lsj', '스포츠3', '본문3', 'nothing.jpg', '0', '111', SYSDATE);
-------------------------------------------------- 글목록(startRow 부터 endRow까지) --------------------------------------------------
SELECT * FROM (SELECT ROWNUM RN, A.* FROM
        (SELECT S.* FROM SPORTS S, MEMBER M WHERE S.MID = M.MID ORDER BY SNUM DESC) A)
        WHERE RN BETWEEN 1 AND 3;
--------------------------------------------------  글 갯수 --------------------------------------------------
SELECT COUNT(*) FROM SPORTS;
-------------------------------------------------- 조회수 1 올리기 --------------------------------------------------
UPDATE SPORTS SET SREADCOUNT = SREADCOUNT + 1 WHERE SNUM = 1;
-------------------------------------------------- MID로 글 Dto 보기 --------------------------------------------------
SELECT S.* FROM SPORTS S, MEMBER M WHERE S.MID = M.MID AND SNUM = 1;
-------------------------------------------------- 글 수정하기 --------------------------------------------------
UPDATE SPORTS SET STITLE = '스포츠1',
                                    SCONTENT = '본문1',
                                    SFILENAME = 'nothing.jpg',
                                    SPW = '111',
                                    SDATE = SYSDATE
                       WHERE SNUM = 1;
-------------------------------------------------- 글 삭제하기 --------------------------------------------------
DELETE FROM SPORTS WHERE SNUM = '1';
-------------------------------------------------- 커밋(COMMIT) --------------------------------------------------
COMMIT;
