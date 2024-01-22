SHOW DATABASES;
CREATE DATABASE dogfit;
drop database dogfit;
USE dogfit;
SHOW TABLES;
drop table member;

select * from member;
select * from board;
select * from likepoint;
select * from reply;

CREATE TABLE member (
                        mno INT primary key auto_increment,
                        id VARCHAR(255) unique NOT NULL,
                        password VARCHAR(255) NOT NULL,
                        email VARCHAR(255) NOT NULL,
                        name VARCHAR(50) NOT NULL,
                        nick_name VARCHAR(50) unique NOT NULL,
                        phone VARCHAR(13),
                        zipcode LONG,
                        streetAddress VARCHAR(255),
                        detailAddress VARCHAR(255),
                        gender VARCHAR(10),
                        birthday DATE,
                        regdate TIMESTAMP,
                        updated_date TIMESTAMP,
                        member_level INT
);

CREATE TABLE board (
                       bno INT PRIMARY KEY auto_increment,
                       title VARCHAR(255) NOT NULL,
                       content VARCHAR(2000) NOT NULL,
                       like_counts INT,
                       view_counts INT,
                       written_date TIMESTAMP,
                       updated_date TIMESTAMP,
                       category VARCHAR(50),
                       mno INT,
    -- fno INT,
                       FOREIGN KEY (mno) REFERENCES member(mno) ON DELETE CASCADE
    -- FOREIGN KEY (fno) REFERENCES file(fno)
);

-- CREATE TABLE file (
--     fno INT PRIMARY KEY auto_increment,
--     file_name VARCHAR(255),
--     origin_name VARCHAR(255),
--     repimgYn  VARCHAR(255),
--     uploaded_date TIMESTAMP,
--     updated_date TIMESTAMP,
--     file_upload BLOB,
--     bno INT,
--     mno INT,
--     FOREIGN KEY (bno) REFERENCES board(bno),
--     FOREIGN KEY (mno) REFERENCES member(mno)
-- );

CREATE TABLE reply (
                       rno INT PRIMARY KEY auto_increment,
                       content VARCHAR(255) NOT NULL,
                       written_date TIMESTAMP,
                       updated_date TIMESTAMP,
                       bno INT,
                       mno INT,
                       FOREIGN KEY (bno) REFERENCES board(bno) ON DELETE CASCADE,
                       FOREIGN KEY (mno) REFERENCES member(mno) ON DELETE CASCADE
);

CREATE TABLE likepoint (
                           lno INT PRIMARY KEY auto_increment,
                           bno INT,
                           mno INT,
                           FOREIGN KEY (bno) REFERENCES board(bno) ON DELETE CASCADE,
                           FOREIGN KEY (mno) REFERENCES member(mno) ON DELETE CASCADE
);

-- alter table board add fno INT;
-- alter table board add FOREIGN KEY (fno) REFERENCES file(fno);
