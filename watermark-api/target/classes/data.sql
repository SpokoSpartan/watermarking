INSERT INTO users(user_id, name, surname, password, email)
VALUES (1, 'John', 'Smith', 'password123', 'john.smith@example.com');

INSERT INTO pictures(picture_id, user_id, picture_url, name, watermark_url, algorithm)
VALUES (1, 1, 'picture_url', 'Mona Lisa', 'watermark_url', 'algorithm1');