-- Idempotent schema for PostgreSQL
--
-- NOTE: Spring Boot will execute this file against the configured datasource database.
-- Ensure your `spring.datasource.url` points to the target database (e.g. jdbc:postgresql://localhost:5432/seyi_blog_db)

/*
Optional: create the database if it doesn't exist. This block must be run against a maintenance DB
(for example `postgres`) because you cannot create a database while connected to the target database.
Run it manually with psql or a DB admin account when needed.

DO $$
BEGIN
   IF NOT EXISTS (SELECT FROM pg_database WHERE datname = 'seyi_blog_db') THEN
      PERFORM dblink_exec('dbname=postgres', 'CREATE DATABASE seyi_blog_db');
   END IF;
END
$$;

-- The block above uses dblink_exec; if dblink is not installed or available, you can run:
-- SELECT 1 FROM pg_database WHERE datname = 'seyi_blog_db';
-- and if not found run: CREATE DATABASE seyi_blog_db;
*/

-- Schema creation (idempotent). This is safe to place as `schema.sql` in Spring Boot resources.

CREATE TABLE IF NOT EXISTS authors (
    id SERIAL PRIMARY KEY,
    author_name VARCHAR(255),
    author_display_name VARCHAR(255),
    author_email VARCHAR(255) UNIQUE,
    author_account_status BOOLEAN DEFAULT FALSE,
    author_pix VARCHAR(255),
    user_name VARCHAR(255) UNIQUE,
    password VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    user_display_name VARCHAR(255),
    user_complete_name VARCHAR(255),
    user_name VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    user_pix VARCHAR(255),
    user_status BOOLEAN DEFAULT FALSE
);


CREATE TABLE IF NOT EXISTS news (
    id SERIAL PRIMARY KEY,
    category VARCHAR(255),
    news_title VARCHAR(255) UNIQUE,
    news_poster VARCHAR(255),
    news_video VARCHAR(255),
    news_content TEXT,
    date_posted VARCHAR(100),
    date_updated VARCHAR(100),
    author_name VARCHAR(255),
    comment_status BOOLEAN DEFAULT TRUE,
    is_liked BOOLEAN DEFAULT FALSE,
    is_disliked BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS comments (
    id SERIAL PRIMARY KEY,
    comment_content TEXT,
    news_id INTEGER,
    comment_date VARCHAR(100),
    user_id INTEGER,
    comment_status BOOLEAN DEFAULT TRUE,
    CONSTRAINT fk_comments_news FOREIGN KEY (news_id) REFERENCES news(id) ON DELETE SET NULL,
    CONSTRAINT fk_comments_users FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL
);

-- Optional indexes to speed up lookups used in repository methods
CREATE INDEX IF NOT EXISTS idx_authors_email ON authors(author_email);
CREATE INDEX IF NOT EXISTS idx_authors_username ON authors(user_name);
CREATE INDEX IF NOT EXISTS idx_users_username ON users(user_name);
CREATE INDEX IF NOT EXISTS idx_news_category ON news(category);

-- End of schema
