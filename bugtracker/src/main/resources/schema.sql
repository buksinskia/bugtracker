CREATE DATABASE IF NOT EXISTS bugtracker;
USE bugtracker;

CREATE TABLE IF NOT EXISTS status (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      name VARCHAR(255) UNIQUE
    );

INSERT INTO status (name)
SELECT * FROM (SELECT 'Nowe') AS tmp
WHERE NOT EXISTS (
    SELECT name FROM status WHERE name = 'Nowe'
);

INSERT INTO status (name)
SELECT * FROM (SELECT 'Otwarte') AS tmp
WHERE NOT EXISTS (
    SELECT name FROM status WHERE name = 'Otwarte'
);

INSERT INTO status (name)
SELECT * FROM (SELECT 'Zamknięte') AS tmp
WHERE NOT EXISTS (
    SELECT name FROM status WHERE name = 'Zamknięte'
);

INSERT INTO status (name)
SELECT * FROM (SELECT 'Zwrócone do developera') AS tmp
WHERE NOT EXISTS (
    SELECT name FROM status WHERE name = 'Zwrócone do developera'
);
