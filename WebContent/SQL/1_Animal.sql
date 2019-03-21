----------------------------------------------------------------------------------------------------
SELECT * FROM ANIMAL;
----------------------------------------------------------------------------------------------------
DROP TABLE ANIMAL;
----------------------------------------------------------------------------------------------------
DROP SEQUENCE ANIMAL_SEQ;
----------------------------------------------------------------------------------------------------
CREATE SEQUENCE ANIMAL_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
----------------------------------------------------------------------------------------------------
CREATE TABLE ANIMAL (
        aNum NUMBER(5) PRIMARY KEY,
        mId VARCHAR2(30) REFERENCES MEMBER(MID),
        aTitle VARCHAR2(100) NOT NULL,
        aContent VARCHAR2(1000) NOT NULL,
        aFilename VARCHAR2(100) NOT NULL,
        aReadcount NUMBER(5) NOT NULL,
        aPw VARCHAR2(30) NOT NULL,
        aDate DATE  NOT NULL
);
-------------------------------------------------- 글쓰기 --------------------------------------------------
INSERT INTO ANIMAL (ANUM, MID, ATITLE, ACONTENT, AFILENAME, AREADCOUNT, APW, ADATE)
        VALUES (ANIMAL_SEQ.NEXTVAL, 'lsj', '동물1', '본문1', 'nothing.jpg', '0', '111', SYSDATE);
INSERT INTO ANIMAL (ANUM, MID, ATITLE, ACONTENT, AFILENAME, AREADCOUNT, APW, ADATE)
        VALUES (ANIMAL_SEQ.NEXTVAL, 'lsj', '동물2', '본문2', 'nothing.jpg', '0', '111', SYSDATE);
INSERT INTO ANIMAL (ANUM, MID, ATITLE, ACONTENT, AFILENAME, AREADCOUNT, APW, ADATE)
        VALUES (ANIMAL_SEQ.NEXTVAL, 'lsj', '동물3', '본문3', 'nothing.jpg', '0', '111', SYSDATE);
-------------------------------------------------- 글목록(startRow 부터 endRow까지) --------------------------------------------------
SELECT * FROM (SELECT ROWNUM RN, A.* FROM
        (SELECT A.* FROM ANIMAL A, MEMBER M WHERE A.MID = M.MID ORDER BY ANUM DESC) A)
        WHERE RN BETWEEN 1 AND 3;
--------------------------------------------------  글 갯수 --------------------------------------------------
SELECT COUNT(*) FROM ANIMAL;
-------------------------------------------------- 조회수 1 올리기 --------------------------------------------------
UPDATE ANIMAL SET AREADCOUNT = AREADCOUNT + 1 WHERE ANUM = 1;
-------------------------------------------------- MID로 글 Dto 보기 --------------------------------------------------
SELECT A.* FROM ANIMAL A, MEMBER M WHERE A.MID = M.MID AND ANUM = 4;
-------------------------------------------------- 글 수정하기 --------------------------------------------------
UPDATE ANIMAL SET ATITLE = '동물1',
                                  ACONTENT = '본문1',
                                  AFILENAME = 'nothing.jpg',
                                  APW = '111',
                                  ADATE = SYSDATE
                     WHERE ANUM = 1;
-------------------------------------------------- 글 삭제하기 --------------------------------------------------
DELETE FROM ANIMAL WHERE ANUM = '18';
-------------------------------------------------- 커밋(COMMIT) --------------------------------------------------
COMMIT;
SELECT * FROM ANIMAL;

BEGIN
FOR i IN 1..20 LOOP
INSERT INTO ANIMAL(ANUM, MID, ATITLE, ACONTENT, AFILENAME, AREADCOUNT, APW, ADATE)
VALUES (ANIMAL_SEQ.NEXTVAL, 'lsj', CONCAT('제목', i), CONCAT('본문', i), CONCAT(i, '.jpg'), 0, 111, SYSDATE);
END LOOP;
END;
