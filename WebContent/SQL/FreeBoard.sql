----------------------------------------------------------------------------------------------------
SELECT * FROM FREEBOARD;
----------------------------------------------------------------------------------------------------
DROP TABLE FREEBOARD;
----------------------------------------------------------------------------------------------------
DROP SEQUENCE FREEBOARD_SEQ;
----------------------------------------------------------------------------------------------------
CREATE SEQUENCE FREEBOARD_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
----------------------------------------------------------------------------------------------------
CREATE TABLE FREEBOARD (
        fNum NUMBER(5) PRIMARY KEY,
        mId VARCHAR2(30) REFERENCES MEMBER(MID),
        fTitle VARCHAR2(100) NOT NULL,
        fContent VARCHAR2(1000) NOT NULL,
        fReadcount NUMBER(5) NOT NULL,
        fPw VARCHAR2(30) NOT NULL,
        fGroup NUMBER(5) NOT NULL,
        fStep NUMBER(5) NOT NULL,
        fIndent NUMBER(5) NOT NULL,
        fDate DATE  NOT NULL
);
-------------------------------------------------- 글쓰기 --------------------------------------------------
INSERT INTO FREEBOARD (FNUM, MID, FTITLE, FCONTENT, FREADCOUNT, FPW, FGROUP, FSTEP, FINDENT, FDATE)
        VALUES (FREEBOARD_SEQ.NEXTVAL, 'lsj', '자유1', '본문1', '0', '111', FREEBOARD_SEQ.CURRVAL, 0, 0, SYSDATE);
-------------------------------------------------- 위 글에 대한 답변글 --------------------------------------------------
INSERT INTO FREEBOARD (FNUM, MID, FTITLE, FCONTENT, FREADCOUNT, FPW, FGROUP, FSTEP, FINDENT, FDATE)
        VALUES (FREEBOARD_SEQ.NEXTVAL, 'lsj', '자유2', '본문2', '0', '111', 1, 1, 1, SYSDATE);
-------------------------------------------------- 위 글에 대한 두번째 답변글 --------------------------------------------------
----------------------- STEP A -----------------------
UPDATE FREEBOARD SET FSTEP = FSTEP+1 WHERE FGROUP = 1 AND FSTEP > 0;
----------------------- 두번째 답변글 -----------------------
INSERT INTO FREEBOARD (FNUM, MID, FTITLE, FCONTENT, FREADCOUNT, FPW, FGROUP, FSTEP, FINDENT, FDATE)
        VALUES (FREEBOARD_SEQ.NEXTVAL, 'lsj', '자유3', '본문3', '0', '111', 1, 1, 1, SYSDATE);
-------------------------------------------------- 글목록(startRow 부터 endRow까지) --------------------------------------------------
SELECT * FROM (SELECT ROWNUM RN, A.* FROM
        (SELECT F.* FROM FREEBOARD F, MEMBER M WHERE F.MID = M.MID ORDER BY FGROUP DESC, FSTEP) A)
        WHERE RN BETWEEN 1 AND 15;
-------------------------------------------------- 글 갯수 --------------------------------------------------
SELECT COUNT(*) FROM FREEBOARD;
-------------------------------------------------- 조회수 1 올리기 --------------------------------------------------
UPDATE FREEBOARD SET FREADCOUNT = FREADCOUNT + 1 WHERE FNUM = 1;
-------------------------------------------------- FNUM로 글 Dto 보기 --------------------------------------------------
SELECT F.* FROM FREEBOARD F, MEMBER M WHERE F.MID = M.MID AND FNUM=1;
-------------------------------------------------- 글 수정하기 --------------------------------------------------
UPDATE FREEBOARD SET FTITLE = '자유1',
                                           FCONTENT = '본문1',
                                           FPW = '111',
                                           FDATE = SYSDATE
                              WHERE FNUM = 1;
-------------------------------------------------- 글 삭제하기 --------------------------------------------------
DELETE FROM FREEBOARD WHERE FNUM='1';
-------------------------------------------------- 커밋(COMMIT) --------------------------------------------------
SELECT COUNT(*) FROM FREEBOARD WHERE MID='lsj';


COMMIT;
