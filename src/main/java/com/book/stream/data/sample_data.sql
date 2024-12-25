-- Clear existing data
DELETE FROM role;
DELETE FROM user;
DELETE FROM book;
DELETE FROM author;
DELETE FROM category;

DELETE FROM student;
DELETE FROM issued_book;

-- Check and insert ROLE_USER
INSERT INTO role (id, name)
SELECT 1, 'ROLE_USER'
WHERE NOT EXISTS (SELECT 1 FROM role WHERE name = 'ROLE_USER');

-- Check and insert ROLE_ADMIN
INSERT INTO role (id, name)
SELECT 2, 'ROLE_ADMIN'
WHERE NOT EXISTS (SELECT 1 FROM role WHERE name = 'ROLE_ADMIN');

INSERT INTO book_stream.`user` (expiration_time,code,email,full_name,password,phone_number,username) VALUES
	 (NULL,NULL,'karnan.learning@gmail.com',NULL,'$2a$10$.hl8V6BtmOPGKB7m3jXpOOocV1KdEKmMtHgfIsdSTw4H.N2qBzY52','0767039634','karnan');

-- Inserting data into the 'Author' table
INSERT INTO author (id, name, image_path) VALUES
(1, 'J.K. Rowling', '/author/Author001.png'),
(2, 'George R.R. Martin', '/author/Author002.png'),
(3, 'J.R.R. Tolkien', '/author/Author003.png'),
(4, 'Agatha Christie', '/author/Author004.png'),
(5, 'Stephen King', '/author/Author005.png'),
(6, 'Arthur Conan Doyle', '/author/Author006.png'),
(7, 'Mark Twain', '/author/Author007.png'),
(8, 'Ernest Hemingway', '/author/Author008.png'),
(9, 'Jane Austen', '/author/Author009.png'),
(10, 'Charles Dickens', '/author/Author010.png');

-- Inserting data into the 'Category' table
INSERT INTO category (id, name, image_path) VALUES
(1, 'Fantasy', '/category/Category001.png'),
(2, 'Mystery', '/category/Category002.png'),
(3, 'Science Fiction', '/category/Category003.png'),
(4, 'Classic Literature', '/category/Category004.png'),
(5, 'Horror', '/category/Category005.png');

-- Inserting data into the 'Book' table
INSERT INTO book (id, title, image_path, stock_count, category_id, author_id) VALUES
(1, 'Harry Potter and the Philosopher''s Stone', '/book/Book001.png', 50, 1, 1),
(2, 'Harry Potter and the Chamber of Secrets', '/book/Book002.png', 45, 1, 1),
(3, 'A Game of Thrones', '/book/Book003.png', 30, 1, 2),
(4, 'A Clash of Kings', '/book/Book004.png', 28, 1, 2),
(5, 'The Hobbit', '/book/Book005.png', 35, 1, 3),
(6, 'The Fellowship of the Ring', '/book/Book006.png', 25, 1, 3),
(7, 'Murder on the Orient Express', '/book/Book007.png', 40, 2, 4),
(8, 'And Then There Were None', '/book/Book008.png', 42, 2, 4),
(9, 'The Shining', '/book/Book009.png', 33, 5, 5),
(10, 'It', '/book/Book010.png', 20, 5, 5),
(11, 'Sherlock Holmes: The Complete Stories', '/book/Book011.png', 15, 2, 6),
(12, 'The Adventures of Sherlock Holmes', '/book/Book012.png', 18, 2, 6),
(13, 'Adventures of Huckleberry Finn', '/book/Book013.png', 30, 4, 7),
(14, 'The Adventures of Tom Sawyer', '/book/Book014.png', 22, 4, 7),
(15, 'The Old Man and the Sea', '/book/Book015.png', 26, 4, 8),
(16, 'A Farewell to Arms', '/book/Book016.png', 27, 4, 8),
(17, 'Pride and Prejudice', '/book/Book017.png', 32, 4, 9),
(18, 'Emma', '/book/Book018.png', 25, 4, 9),
(19, 'Oliver Twist', '/book/Book019.png', 28, 4, 10),
(20, 'Great Expectations', '/book/Book020.png', 23, 4, 10);

-- Inserting data into the 'Student' table
INSERT INTO student (id, name, student_id, image_path) VALUES
(1, 'Alice Johnson', 'S001', '/student/Student001.png'),
(2, 'Bob Smith', 'S002', '/student/Student002.png'),
(3, 'Charlie Brown', 'S003', '/student/Student003.png'),
(4, 'Daisy Thomas', 'S004', '/student/Student004.png'),
(5, 'Eve Davis', 'S005', '/student/Student005.png'),
(6, 'Frank Miller', 'S006', '/student/Student006.png'),
(7, 'Grace Lee', 'S007', '/student/Student007.png'),
(8, 'Hannah Moore', 'S008', '/student/Student008.png'),
(9, 'Isaac White', 'S009', '/student/Student009.png'),
(10, 'Jack Taylor', 'S010', '/student/Student010.png'),
(11, 'Karen Harris', 'S011', '/student/Student011.png'),
(12, 'Leo Martin', 'S012', '/student/Student012.png'),
(13, 'Megan Hall', 'S013', '/student/Student013.png'),
(14, 'Nathan Clark', 'S014', '/student/Student014.png'),
(15, 'Olivia Scott', 'S015', '/student/Student015.png'),
(16, 'Paul Adams', 'S016', '/student/Student016.png'),
(17, 'Quinn Young', 'S017', '/student/Student017.png'),
(18, 'Rachel Nelson', 'S018', '/student/Student018.png'),
(19, 'Sophia Carter', 'S019', '/student/Student019.png'),
(20, 'Thomas Mitchell', 'S020', '/student/Student020.png'),
(21, 'Uma Perez', 'S021', '/student/Student021.png'),
(22, 'Victor Roberts', 'S022', '/student/Student022.png'),
(23, 'Wendy Evans', 'S023', '/student/Student023.png'),
(24, 'Xander Murphy', 'S024', '/student/Student024.png'),
(25, 'Yara Collins', 'S025', '/student/Student025.png'),
(26, 'Zane Bell', 'S026', '/student/Student026.png'),
(27, 'Amy Rivera', 'S027', '/student/Student027.png'),
(28, 'Brian Gray', 'S028', '/student/Student028.png'),
(29, 'Clara Cooper', 'S029', '/student/Student029.png'),
(30, 'David Bailey', 'S030', '/student/Student030.png');

-- Inserting data into the 'IssuedBook' table
INSERT INTO issued_book (id, count, duration, issue_date, book_id, student_id) VALUES
(1, 1, 14, '2024-12-01', 1, 1),
(2, 1, 21, '2024-12-03', 2, 2),
(3, 2, 7, '2024-12-05', 3, 3),
(4, 1, 30, '2024-12-07', 4, 4),
(5, 1, 14, '2024-12-09', 5, 5),
(6, 1, 21, '2024-12-11', 6, 6),
(7, 1, 7, '2024-12-13', 7, 7),
(8, 1, 14, '2024-12-15', 8, 8),
(9, 1, 21, '2024-12-17', 9, 9),
(10, 1, 7, '2024-12-19', 10, 10),
(11, 1, 14, '2024-12-21', 11, 11),
(12, 2, 30, '2024-12-23', 12, 12),
(13, 1, 14, '2024-12-25', 13, 13),
(14, 1, 21, '2024-12-27', 14, 14),
(15, 1, 7, '2024-12-29', 15, 15),
(16, 1, 14, '2025-01-01', 16, 16),
(17, 1, 21, '2025-01-03', 17, 17),
(18, 1, 7, '2025-01-05', 18, 18),
(19, 1, 14, '2025-01-07', 19, 19),
(20, 1, 21, '2025-01-09', 20, 20),
(21, 1, 7, '2025-01-11', 1, 21),
(22, 1, 14, '2025-01-13', 2, 22),
(23, 1, 21, '2025-01-15', 3, 23),
(24, 1, 7, '2025-01-17', 4, 24),
(25, 1, 14, '2025-01-19', 5, 25),
(26, 1, 21, '2025-01-21', 6, 26),
(27, 1, 7, '2025-01-23', 7, 27),
(28, 1, 14, '2025-01-25', 8, 28),
(29, 1, 21, '2025-01-27', 9, 29),
(30, 1, 7, '2025-01-29', 10, 30);
