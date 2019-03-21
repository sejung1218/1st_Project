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
-------------------------------------------------- 글쓰기 --------------------------------------------------
INSERT INTO LANDMARK (LNUM, MID, LTITLE, LCONTENT, LFILENAME, LREADCOUNT, LPW, LDATE)
        VALUES (LANDMARK_SEQ.NEXTVAL, 'lsj', '건물1', '본문1', 'nothing.jpg', '0', '111', SYSDATE);
INSERT INTO LANDMARK (LNUM, MID, LTITLE, LCONTENT, LFILENAME, LREADCOUNT, LPW, LDATE)
        VALUES (LANDMARK_SEQ.NEXTVAL, 'lsj', '건물2', '본문2', 'nothing.jpg', '0', '111', SYSDATE);
INSERT INTO LANDMARK (LNUM, MID, LTITLE, LCONTENT, LFILENAME, LREADCOUNT, LPW, LDATE)
        VALUES (LANDMARK_SEQ.NEXTVAL, 'lsj', '건물3', '본문3', 'nothing.jpg', '0', '111', SYSDATE);
-------------------------------------------------- 글목록(startRow 부터 endRow까지) --------------------------------------------------
SELECT * FROM (SELECT ROWNUM RN, A.* FROM
        (SELECT L.* FROM LANDMARK L, MEMBER M WHERE L.MID = M.MID ORDER BY LNUM DESC) A)
        WHERE RN BETWEEN 1 AND 3;
--------------------------------------------------  글 갯수 --------------------------------------------------
SELECT COUNT(*) FROM LANDMARK;
-------------------------------------------------- 조회수 1 올리기 --------------------------------------------------
UPDATE LANDMARK SET LREADCOUNT = LREADCOUNT + 1 WHERE LNUM = 1;
-------------------------------------------------- MID로 글 Dto 보기 --------------------------------------------------
SELECT L.* FROM LANDMARK L, MEMBER M WHERE L.MID = M.MID AND LNUM = 1;
-------------------------------------------------- 글 수정하기 --------------------------------------------------
UPDATE LANDMARK SET LTITLE = '건물1',
                                        LCONTENT = '본문1',
                                        LFILENAME = 'nothing.jpg',
                                        LPW = '111',
                                        LDATE = SYSDATE
                           WHERE LNUM = 1;
-------------------------------------------------- 글 삭제하기 --------------------------------------------------
DELETE FROM LANDMARK WHERE LNUM = '1';
-------------------------------------------------- 커밋(COMMIT) --------------------------------------------------
COMMIT;
