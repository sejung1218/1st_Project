----------------------------------------------------------------------------------------------------
SELECT * FROM ILLUST;
----------------------------------------------------------------------------------------------------
DROP TABLE ILLUST;
----------------------------------------------------------------------------------------------------
DROP SEQUENCE ILLUST_SEQ;
----------------------------------------------------------------------------------------------------
CREATE SEQUENCE ILLUST_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
----------------------------------------------------------------------------------------------------
CREATE TABLE ILLUST (
        iNum NUMBER(5) PRIMARY KEY,
        mId VARCHAR2(30) REFERENCES MEMBER(MID),
        iTitle VARCHAR2(100) NOT NULL,
        iContent VARCHAR2(1000) NOT NULL,
        iFilename VARCHAR2(100) NOT NULL,
        iReadcount NUMBER(5) NOT NULL,
        iPw VARCHAR2(30) NOT NULL,
        iDate DATE  NOT NULL
);
-------------------------------------------------- 글쓰기 --------------------------------------------------
INSERT INTO ILLUST (INUM, MID, ITITLE, ICONTENT, IFILENAME, IREADCOUNT, IPW, IDATE)
        VALUES (ILLUST_SEQ.NEXTVAL, 'lsj', '일러스트1', '본문1', 'nothing.jpg', '0', '111', SYSDATE);
INSERT INTO ILLUST (INUM, MID, ITITLE, ICONTENT, IFILENAME, IREADCOUNT, IPW, IDATE)
        VALUES (ILLUST_SEQ.NEXTVAL, 'lsj', '일러스트2', '본문2', 'nothing.jpg', '0', '111', SYSDATE);
INSERT INTO ILLUST (INUM, MID, ITITLE, ICONTENT, IFILENAME, IREADCOUNT, IPW, IDATE)
        VALUES (ILLUST_SEQ.NEXTVAL, 'lsj', '일러스트3', '본문3', 'nothing.jpg', '0', '111', SYSDATE);
-------------------------------------------------- 글목록(startRow 부터 endRow까지) --------------------------------------------------
SELECT * FROM (SELECT ROWNUM RN, A.* FROM
        (SELECT I.* FROM ILLUST I, MEMBER M WHERE I.MID = M.MID ORDER BY INUM DESC) A)
        WHERE RN BETWEEN 1 AND 3;
--------------------------------------------------  글 갯수 --------------------------------------------------
SELECT COUNT(*) FROM ILLUST;
-------------------------------------------------- 조회수 1 올리기 --------------------------------------------------
UPDATE ILLUST SET IREADCOUNT = IREADCOUNT + 1 WHERE INUM = 1;
-------------------------------------------------- MID로 글 Dto 보기 --------------------------------------------------
SELECT I.* FROM ILLUST I, MEMBER M WHERE I.MID = M.MID AND INUM = 1;
-------------------------------------------------- 글 수정하기 --------------------------------------------------
UPDATE ILLUST SET ITITLE = '일러스트1',
                                 ICONTENT = '본문1',
                                 IFILENAME = 'nothing.jpg',
                                 IPW = '111',
                                 IDATE = SYSDATE
                    WHERE INUM = 1;
-------------------------------------------------- 글 삭제하기 --------------------------------------------------
DELETE FROM ILLUST WHERE INUM = '1';
-------------------------------------------------- 커밋(COMMIT) --------------------------------------------------
COMMIT;



BEGIN
FOR i IN 1..15 LOOP
INSERT INTO ILLUST(INUM, MID, ITITLE, ICONTENT, IFILENAME, IREADCOUNT, IPW, IDATE)
VALUES (ILLUST_SEQ.NEXTVAL, 'lsj', CONCAT('제목', i), CONCAT('본문', i), CONCAT(i, '.jpg'), 0, 111, SYSDATE);
END LOOP;
END;
