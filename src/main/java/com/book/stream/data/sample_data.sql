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
(1, 'J.K. Rowling', '/images/author/Author001.png'),
(2, 'George R.R. Martin', '/images/author/Author002.png'),
(3, 'J.R.R. Tolkien', '/images/author/Author003.png'),
(4, 'Agatha Christie', '/images/author/Author004.png'),
(5, 'Stephen King', '/images/author/Author005.png'),
(6, 'Arthur Conan Doyle', '/images/author/Author006.png'),
(7, 'Mark Twain', '/images/author/Author007.png'),
(8, 'Ernest Hemingway', '/images/author/Author008.png'),
(9, 'Jane Austen', '/images/author/Author009.png'),
(10, 'Charles Dickens', '/images/author/Author010.png');

-- Inserting data into the 'Category' table
INSERT INTO category (id, name, image_path) VALUES
(1, 'Fantasy', '/images/category/Category001.png'),
(2, 'Mystery', '/images/category/Category002.png'),
(3, 'Science Fiction', '/images/category/Category003.png'),
(4, 'Classic Literature', '/images/category/Category004.png'),
(5, 'Horror', '/images/category/Category005.png');

-- Inserting data into the 'Book' table
INSERT INTO book (id, title, image_path, stock_count, category_id, author_id) VALUES
(1, 'Harry Potter and the Philosopher''s Stone', '/images/book/Book001.png', 50, 1, 1),
(2, 'Harry Potter and the Chamber of Secrets', '/images/book/Book002.png', 45, 1, 1),
(3, 'A Game of Thrones', '/images/book/Book003.png', 30, 1, 2),
(4, 'A Clash of Kings', '/images/book/Book004.png', 28, 1, 2),
(5, 'The Hobbit', '/images/book/Book005.png', 35, 1, 3),
(6, 'The Fellowship of the Ring', '/images/book/Book006.png', 25, 1, 3),
(7, 'Murder on the Orient Express', '/images/book/Book007.png', 40, 2, 4),
(8, 'And Then There Were None', '/images/book/Book008.png', 42, 2, 4),
(9, 'The Shining', '/images/book/Book009.png', 33, 5, 5),
(10, 'It', '/images/book/Book010.png', 20, 5, 5),
(11, 'Sherlock Holmes: The Complete Stories', '/images/book/Book011.png', 15, 2, 6),
(12, 'The Adventures of Sherlock Holmes', '/images/book/Book012.png', 18, 2, 6),
(13, 'Adventures of Huckleberry Finn', '/images/book/Book013.png', 30, 4, 7),
(14, 'The Adventures of Tom Sawyer', '/images/book/Book014.png', 22, 4, 7),
(15, 'The Old Man and the Sea', '/images/book/Book015.png', 26, 4, 8),
(16, 'A Farewell to Arms', '/images/book/Book016.png', 27, 4, 8),
(17, 'Pride and Prejudice', '/images/book/Book017.png', 32, 4, 9),
(18, 'Emma', '/images/book/Book018.png', 25, 4, 9),
(19, 'Oliver Twist', '/images/book/Book019.png', 28, 4, 10),
(20, 'Great Expectations', '/images/book/Book020.png', 23, 4, 10);

-- Inserting data into the 'Student' table
INSERT INTO student (id, name, student_id, image_path, email, mobile) VALUES
(1, 'Alice Johnson', 'S001', '/images/student/Student001.png', 'alice.johnson@example.com', '1234567890'),
(2, 'Bob Smith', 'S002', '/images/student/Student002.png', 'bob.smith@example.com', '1234567891'),
(3, 'Charlie Brown', 'S003', '/images/student/Student003.png', 'charlie.brown@example.com', '1234567892'),
(4, 'Daisy Thomas', 'S004', '/images/student/Student004.png', 'daisy.thomas@example.com', '1234567893'),
(5, 'Eve Davis', 'S005', '/images/student/Student005.png', 'eve.davis@example.com', '1234567894'),
(6, 'Frank Miller', 'S006', '/images/student/Student006.png', 'frank.miller@example.com', '1234567895'),
(7, 'Grace Lee', 'S007', '/images/student/Student007.png', 'grace.lee@example.com', '1234567896'),
(8, 'Hannah Moore', 'S008', '/images/student/Student008.png', 'hannah.moore@example.com', '1234567897'),
(9, 'Isaac White', 'S009', '/images/student/Student009.png', 'isaac.white@example.com', '1234567898'),
(10, 'Jack Taylor', 'S010', '/images/student/Student010.png', 'jack.taylor@example.com', '1234567899'),
(11, 'Karen Harris', 'S011', '/images/student/Student011.png', 'karen.harris@example.com', '2234567890'),
(12, 'Leo Martin', 'S012', '/images/student/Student012.png', 'leo.martin@example.com', '2234567891'),
(13, 'Megan Hall', 'S013', '/images/student/Student013.png', 'megan.hall@example.com', '2234567892'),
(14, 'Nathan Clark', 'S014', '/images/student/Student014.png', 'nathan.clark@example.com', '2234567893'),
(15, 'Olivia Scott', 'S015', '/images/student/Student015.png', 'olivia.scott@example.com', '2234567894'),
(16, 'Paul Adams', 'S016', '/images/student/Student016.png', 'paul.adams@example.com', '2234567895'),
(17, 'Quinn Young', 'S017', '/images/student/Student017.png', 'quinn.young@example.com', '2234567896'),
(18, 'Rachel Nelson', 'S018', '/images/student/Student018.png', 'rachel.nelson@example.com', '2234567897'),
(19, 'Sophia Carter', 'S019', '/images/student/Student019.png', 'sophia.carter@example.com', '2234567898'),
(20, 'Thomas Mitchell', 'S020', '/images/student/Student020.png', 'thomas.mitchell@example.com', '2234567899'),
(21, 'Uma Perez', 'S021', '/images/student/Student021.png', 'uma.perez@example.com', '3234567890'),
(22, 'Victor Roberts', 'S022', '/images/student/Student022.png', 'victor.roberts@example.com', '3234567891'),
(23, 'Wendy Evans', 'S023', '/images/student/Student023.png', 'wendy.evans@example.com', '3234567892'),
(24, 'Xander Murphy', 'S024', '/images/student/Student024.png', 'xander.murphy@example.com', '3234567893'),
(25, 'Yara Collins', 'S025', '/images/student/Student025.png', 'yara.collins@example.com', '3234567894'),
(26, 'Zane Bell', 'S026', '/images/student/Student026.png', 'zane.bell@example.com', '3234567895'),
(27, 'Amy Rivera', 'S027', '/images/student/Student027.png', 'amy.rivera@example.com', '3234567896'),
(28, 'Brian Gray', 'S028', '/images/student/Student028.png', 'brian.gray@example.com', '3234567897'),
(29, 'Clara Cooper', 'S029', '/images/student/Student029.png', 'clara.cooper@example.com', '3234567898'),
(30, 'David Bailey', 'S030', '/images/student/Student030.png', 'david.bailey@example.com', '3234567899');

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
