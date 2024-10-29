CREATE TABLE books (
  id SERIAL PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  author VARCHAR(255) NOT NULL,
  year INTEGER
);

INSERT INTO books (title, author, year) VALUES ('The Hobbit', 'J.R.R. Tolkien', 1937);
INSERT INTO books (title, author, year) VALUES ('The Lord of the Rings', 'J.R.R. Tolkien', 1954);
INSERT INTO books (title, author, year) VALUES ('The Silmarillion', 'J.R.R. Tolkien', 1977);
