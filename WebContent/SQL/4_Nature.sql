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
-------------------------------------------------- 글쓰기 --------------------------------------------------
INSERT INTO NATURE (NNUM, MID, NTITLE, NCONTENT, NFILENAME, NREADCOUNT, NPW, NDATE)
        VALUES (NATURE_SEQ.NEXTVAL, 'lsj', '자연1', '본문1', 'nothing.jpg', '0', '111', SYSDATE);
INSERT INTO NATURE (NNUM, MID, NTITLE, NCONTENT, NFILENAME, NREADCOUNT, NPW, NDATE)
        VALUES (NATURE_SEQ.NEXTVAL, 'lsj', '자연2', '본문2', 'nothing.jpg', '0', '111', SYSDATE);
INSERT INTO NATURE (NNUM, MID, NTITLE, NCONTENT, NFILENAME, NREADCOUNT, NPW, NDATE)
        VALUES (NATURE_SEQ.NEXTVAL, 'lsj', '자연3', '본문3', 'nothing.jpg', '0', '111', SYSDATE);
-------------------------------------------------- 글목록(startRow 부터 endRow까지) --------------------------------------------------
SELECT * FROM (SELECT ROWNUM RN, A.* FROM
        (SELECT N.* FROM NATURE N, MEMBER M WHERE N.MID = M.MID ORDER BY NNUM DESC) A)
        WHERE RN BETWEEN 1 AND 3;
--------------------------------------------------  글 갯수 --------------------------------------------------
SELECT COUNT(*) FROM NATURE;
-------------------------------------------------- 조회수 1 올리기 --------------------------------------------------
UPDATE NATURE SET NREADCOUNT = NREADCOUNT + 1 WHERE NNUM = 1;
-------------------------------------------------- MID로 글 Dto 보기 --------------------------------------------------
SELECT N.* FROM NATURE N, MEMBER M WHERE N.MID = M.MID AND NNUM = 1;
-------------------------------------------------- 글 수정하기 --------------------------------------------------
UPDATE NATURE SET NTITLE = '자연1',
                                   NCONTENT = '본문1',
                                   NFILENAME = 'nothing.jpg',
                                   NPW = '111',
                                   NDATE = SYSDATE
                      WHERE NNUM = 1;
-------------------------------------------------- 글 삭제하기 --------------------------------------------------
DELETE FROM NATURE WHERE NNUM = '1';
-------------------------------------------------- 커밋(COMMIT) --------------------------------------------------
COMMIT;
