INSERT INTO users(user_id, name, surname, password, email)
VALUES (1, 'John', 'Smith', '$2y$10$ChYovDTG7vDE722VcPQFxOAW40AgBW2bioxbdRNJpW5GoDHNHV57u', 'john.smith@example.com');

INSERT INTO users(user_id, name, surname, password, email)
VALUES (2, 'Jan', 'Nowak', '$2y$10$ChYovDTG7vDE722VcPQFxOAW40AgBW2bioxbdRNJpW5GoDHNHV57u', 'jan.nowak@example.com');

INSERT INTO pictures(picture_id, user_id, picture_url, name, watermark_url, algorithm)
VALUES (1, 1, 'picture_url', 'Mona Lisa', 'watermark_url', 'algorithm1');
