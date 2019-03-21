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
-------------------------------------------------- 글쓰기 --------------------------------------------------
INSERT INTO TECHNIQUE (TNUM, MID, TTITLE, TCONTENT, TFILENAME, TREADCOUNT, TPW, TDATE)
        VALUES (TECHNIQUE_SEQ.NEXTVAL, 'lsj', '기술1', '본문1', 'nothing.jpg', '0', '111', SYSDATE);
INSERT INTO TECHNIQUE (TNUM, MID, TTITLE, TCONTENT, TFILENAME, TREADCOUNT, TPW, TDATE)
        VALUES (TECHNIQUE_SEQ.NEXTVAL, 'lsj', '기술2', '본문2', 'nothing.jpg', '0', '111', SYSDATE);
INSERT INTO TECHNIQUE (TNUM, MID, TTITLE, TCONTENT, TFILENAME, TREADCOUNT, TPW, TDATE)
        VALUES (TECHNIQUE_SEQ.NEXTVAL, 'lsj', '기술3', '본문3', 'nothing.jpg', '0', '111', SYSDATE);
-------------------------------------------------- 글목록(startRow 부터 endRow까지) --------------------------------------------------
SELECT * FROM (SELECT ROWNUM RN, A.* FROM
        (SELECT T.* FROM TECHNIQUE T, MEMBER M WHERE T.MID = M.MID ORDER BY TNUM DESC) A)
        WHERE RN BETWEEN 1 AND 3;
--------------------------------------------------  글 갯수 --------------------------------------------------
SELECT COUNT(*) FROM TECHNIQUE;
-------------------------------------------------- 조회수 1 올리기 --------------------------------------------------
UPDATE TECHNIQUE SET TREADCOUNT = TREADCOUNT + 1 WHERE TNUM = 1;
-------------------------------------------------- MID로 글 Dto 보기 --------------------------------------------------
SELECT T.* FROM TECHNIQUE T, MEMBER M WHERE T.MID = M.MID AND TNUM = 1;
-------------------------------------------------- 글 수정하기 --------------------------------------------------
UPDATE TECHNIQUE SET TTITLE = '기술1',
                                         TCONTENT = '본문1',
                                         TFILENAME = 'nothing.jpg',
                                         TPW = '111',
                                         TDATE = SYSDATE
                            WHERE TNUM = 1;
-------------------------------------------------- 글 삭제하기 --------------------------------------------------
DELETE FROM TECHNIQUE WHERE TNUM = '1';
-------------------------------------------------- 커밋(COMMIT) --------------------------------------------------
COMMIT;


SELECT * FROM MVC_MEMBER;
