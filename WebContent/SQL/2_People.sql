----------------------------------------------------------------------------------------------------
SELECT * FROM PEOPLE;
----------------------------------------------------------------------------------------------------
DROP TABLE PEOPLE;
----------------------------------------------------------------------------------------------------
DROP SEQUENCE PEOPLE_SEQ;
----------------------------------------------------------------------------------------------------
CREATE SEQUENCE PEOPLE_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
----------------------------------------------------------------------------------------------------
CREATE TABLE PEOPLE (
        pNum NUMBER(5) PRIMARY KEY,
        mId VARCHAR2(30) REFERENCES MEMBER(MID),
        pTitle VARCHAR2(100) NOT NULL,
        pContent VARCHAR2(1000) NOT NULL,
        pFilename VARCHAR2(100) NOT NULL,
        pReadcount NUMBER(5) NOT NULL,
        pPw VARCHAR2(30) NOT NULL,
        pDate DATE  NOT NULL
);
-------------------------------------------------- 글쓰기 --------------------------------------------------
INSERT INTO PEOPLE (PNUM, MID, PTITLE, PCONTENT, PFILENAME, PREADCOUNT, PPW, PDATE)
        VALUES (PEOPLE_SEQ.NEXTVAL, 'lsj', '인물1', '본문1', 'nothing.jpg', '0', '111', SYSDATE);
INSERT INTO PEOPLE (PNUM, MID, PTITLE, PCONTENT, PFILENAME, PREADCOUNT, PPW, PDATE)
        VALUES (PEOPLE_SEQ.NEXTVAL, 'lsj', '인물2', '본문2', 'nothing.jpg', '0', '111', SYSDATE);
INSERT INTO PEOPLE (PNUM, MID, PTITLE, PCONTENT, PFILENAME, PREADCOUNT, PPW, PDATE)
        VALUES (PEOPLE_SEQ.NEXTVAL, 'lsj', '인물3', '본문3', 'nothing.jpg', '0', '111', SYSDATE);
-------------------------------------------------- 글목록(startRow 부터 endRow까지) --------------------------------------------------
SELECT * FROM (SELECT ROWNUM RN, A.* FROM
        (SELECT P.* FROM PEOPLE P, MEMBER M WHERE P.MID = M.MID ORDER BY PNUM DESC) A)
        WHERE RN BETWEEN 1 AND 3;
--------------------------------------------------  글 갯수 --------------------------------------------------
SELECT COUNT(*) FROM PEOPLE;
-------------------------------------------------- 조회수 1 올리기 --------------------------------------------------
UPDATE PEOPLE SET PREADCOUNT = PREADCOUNT + 1 WHERE PNUM = 1;
-------------------------------------------------- MID로 글 Dto 보기 --------------------------------------------------
SELECT P.* FROM PEOPLE P, MEMBER M WHERE P.MID = M.MID AND PNUM = 1;
-------------------------------------------------- 글 수정하기 --------------------------------------------------
UPDATE PEOPLE SET PTITLE = '인물1',
                                   PCONTENT = '본문1',
                                   PFILENAME = 'nothing.jpg',
                                   PPW = '111',
                                   PDATE = SYSDATE
                      WHERE PNUM = 1;
-------------------------------------------------- 글 삭제하기 --------------------------------------------------
DELETE FROM PEOPLE WHERE PNUM = '1';
-------------------------------------------------- 커밋(COMMIT) --------------------------------------------------
COMMIT;


BEGIN
FOR i IN 1..15 LOOP
INSERT INTO PEOPLE(PNUM, MID, PTITLE, PCONTENT, PFILENAME, PREADCOUNT, PPW, PDATE)
VALUES (PEOPLE_SEQ.NEXTVAL, 'lsj', CONCAT('제목', i), CONCAT('본문', i), CONCAT(i, '.jpg'), 0, 111, SYSDATE);
END LOOP;

END;


