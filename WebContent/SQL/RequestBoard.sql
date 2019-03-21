----------------------------------------------------------------------------------------------------
SELECT * FROM REQUESTBOARD;
----------------------------------------------------------------------------------------------------
DROP TABLE REQUESTBOARD;
----------------------------------------------------------------------------------------------------
DROP SEQUENCE REQUESTBOARD_SEQ;
----------------------------------------------------------------------------------------------------
CREATE SEQUENCE REQUESTBOARD_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
----------------------------------------------------------------------------------------------------
CREATE TABLE REQUESTBOARD (
        rNum NUMBER(5) PRIMARY KEY,
        mId VARCHAR2(30) REFERENCES MEMBER(MID),
        rTitle VARCHAR2(100) NOT NULL,
        rContent VARCHAR2(1000) NOT NULL,
        rReadcount NUMBER(5) NOT NULL,
        rPw VARCHAR2(30) NOT NULL,
        rGroup NUMBER(5) NOT NULL,
        rStep NUMBER(5) NOT NULL,
        rIndent NUMBER(5) NOT NULL,
        rDate DATE  NOT NULL
);
-------------------------------------------------- 글쓰기 --------------------------------------------------
INSERT INTO REQUESTBOARD (RNUM, MID, RTITLE, RCONTENT, RREADCOUNT, RPW, RGROUP, RSTEP, RINDENT, RDATE)
        VALUES (REQUESTBOARD_SEQ.NEXTVAL, 'lsj', '신청1', '본문1', '0', '111', REQUESTBOARD_SEQ.CURRVAL, 0, 0, SYSDATE);
-------------------------------------------------- 위 글에 대한 답변글 --------------------------------------------------
INSERT INTO REQUESTBOARD (RNUM, MID, RTITLE, RCONTENT, RREADCOUNT, RPW, RGROUP, RSTEP, RINDENT, RDATE)
        VALUES (REQUESTBOARD_SEQ.NEXTVAL, 'lsj', '신청2', '본문2', '0', '111', 1, 1, 1, SYSDATE);
-------------------------------------------------- 위 글에 대한 두번째 답변글 --------------------------------------------------
----------------------- STEP A -----------------------
UPDATE REQUESTBOARD SET RSTEP = RSTEP+1 WHERE RGROUP = 1 AND RSTEP > 0;
----------------------- 두번째 답변글 -----------------------
INSERT INTO REQUESTBOARD (RNUM, MID, RTITLE, RCONTENT, RREADCOUNT, RPW, RGROUP, RSTEP, RINDENT, RDATE)
        VALUES (REQUESTBOARD_SEQ.NEXTVAL, 'lsj', '신청3', '본문3', '0', '111', 1, 1, 1, SYSDATE);
-------------------------------------------------- 글목록(startRow 부터 endRow까지) --------------------------------------------------
SELECT * FROM (SELECT ROWNUM RN, A.* FROM 
        (SELECT R.* FROM REQUESTBOARD R, MEMBER M WHERE R.MID = M.MID ORDER BY RGROUP DESC, RSTEP) A)
        WHERE RN BETWEEN 1 AND 3;
-------------------------------------------------- 글 갯수 --------------------------------------------------
SELECT COUNT(*) FROM REQUESTBOARD;
-------------------------------------------------- 조회수 1 올리기 --------------------------------------------------
UPDATE REQUESTBOARD SET RREADCOUNT = RREADCOUNT + 1 WHERE RNUM = 1;
-------------------------------------------------- RNUM으로 글 Dto 보기 --------------------------------------------------
SELECT R.*, MNAME FROM REQUESTBOARD R, MEMBER M WHERE R.MID = M.MID AND RNUM=1;
-------------------------------------------------- 글 수정하기 --------------------------------------------------
UPDATE REQUESTBOARD SET RTITLE = '신청1',
                                                  RCONTENT = '신청1',
                                                  RPW = '111',
                                                  RDATE = SYSDATE
                                     WHERE RNUM = 1;
-------------------------------------------------- 글 삭제하기 --------------------------------------------------
DELETE FROM REQUESTBOARD WHERE RNUM='1';
-------------------------------------------------- 커밋(COMMIT) --------------------------------------------------
COMMIT;
