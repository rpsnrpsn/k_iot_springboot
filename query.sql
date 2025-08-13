# 1. 스키마 생성
DROP DATABASE IF EXISTS k5_iot_springboot;

# 2. 스키마 생성 + 문자셋/정렬 설정
CREATE DATABASE IF NOT EXISTS k5_iot_springboot
	CHARACTER SET utf8mb4
    COLLATE utf8mb4_general_ci;
    
# 3. 스키마 선택
USE k5_iot_springboot;

# 0811 (A_Test)
CREATE TABLE IF NOT EXISTS test_id (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
);
SELECT * FROM test_id;

# 0812 (B_Student)
CREATE TABLE IF NOT EXISTS students (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(100) NOT NULL,
	email VARCHAR(100) NOT NULL UNIQUE
    );
SELECT * FROM students;

# 0813 (C_Book)
CREATE TABLE IF NOT EXISTS books (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    writer VARCHAR(50) NOT NULL,
    title VARCHAR(100) NOT NULL,
    content VARCHAR(500) NOT NULL,
    category VARCHAR(20) NOT NULL,
	# 자바 enum 데이터 처리
    # : DB 에서는 VARCHAR(문자열)로 관리 + CHECK 제약 조건으로 문자 제한
    CONSTRAINT chk_book_category CHECK (category IN ('NOVEL', 'ESSAY', 'POEM', 'MAGAZINE')),
    # 같은 저자 + 동일 제목 중복 저장 방지
    CONSTRAINT uk_book_writer_title UNIQUE (writer, title)
    );
    SELECT * FROM books;

    
    