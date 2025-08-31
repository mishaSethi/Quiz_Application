CREATE DATABASE IF NOT EXISTS quiz;
USE quiz;

CREATE TABLE users (
   user_id INT AUTO_INCREMENT PRIMARY KEY,
   username VARCHAR(100) UNIQUE NOT NULL,
   password VARCHAR(255) NOT NULL,
   email VARCHAR(100) UNIQUE NOT NULL,
   gender ENUM('male', 'female', 'other'),
   role ENUM('USER', 'ADMIN') DEFAULT 'USER',
   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE quizz(
   quiz_id INT AUTO_INCREMENT PRIMARY KEY,
   title VARCHAR(100) NOT NULL,
   description TEXT,
   created_by INT,
   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   FOREIGN KEY (created_by) REFERENCES users(user_id) ON DELETE SET NULL
);

CREATE TABLE questions (
   question_id INT AUTO_INCREMENT PRIMARY KEY,
   quiz_id INT NOT NULL,
   question_text TEXT NOT NULL,
   FOREIGN KEY (quiz_id) REFERENCES quizz(quiz_id) ON DELETE CASCADE
);

CREATE TABLE options (
   option_id INT AUTO_INCREMENT PRIMARY KEY,
   question_id INT NOT NULL,
   option_text TEXT  NOT NULL,
   is_correct BOOLEAN DEFAULT FALSE,
   FOREIGN KEY (question_id) REFERENCES questions(question_id) ON DELETE CASCADE
);

CREATE TABLE quiz_attempt (
    attempt_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    quiz_id INT NOT NULL,
    started_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    completed_at TIMESTAMP NULL,
    score INT DEFAULT 0,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (quiz_id) REFERENCES quizz(quiz_id) ON DELETE CASCADE
);

CREATE TABLE users_response (
    response_id INT AUTO_INCREMENT PRIMARY KEY,
    attempt_id INT NOT NULL,
    question_id INT NOT NULL,
    selected_option_id INT,
    is_correct BOOLEAN,
    answered_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (attempt_id) REFERENCES quiz_attempt(attempt_id) ON DELETE CASCADE,
    FOREIGN KEY (question_id) REFERENCES questions(question_id) ON DELETE CASCADE,
    FOREIGN KEY (selected_option_id) REFERENCES options(option_id) ON DELETE SET NULL
);

CREATE VIEW leaderboards AS
SELECT
    u.user_id,
    u.username,
    q.quiz_id,
    q.title AS quiz_title,
    MAX(a.score) AS best_score,
    ROUND(AVG(a.score)) AS avg_score,
    COUNT(a.attempt_id) AS attempts
FROM users u
JOIN quiz_attempt a ON u.user_id = a.user_id
JOIN quizz q ON a.quiz_id = q.quiz_id
GROUP BY u.user_id, q.quiz_id
ORDER BY best_score DESC;

SELECT * FROM users;
SELECT * FROM quizz;
SELECT * FROM questions;
SELECT * FROM options;
SELECT * FROM quiz_attempt;
SELECT * FROM users_response;
SELECT * FROM leaderboards;

SHOW DATABASES;
