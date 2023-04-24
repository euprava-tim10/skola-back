INSERT INTO school(name, place, type) VALUES ('Vuk Karadzic', 'Loznica', 'OSNOVNA');
INSERT INTO school(name, place, type) VALUES ('Nikola Tesla', 'Novi Sad', 'SREDNJA');

INSERT INTO course(name, school_id) VALUES ('Informacione Tehnologije', 2);
INSERT INTO course(name, school_id) VALUES ('Masinski tehnicar',  2);

INSERT INTO administrator(first_name, last_name, jmbg, school_id) VALUES ('Stefan', 'Vlajkovic', '1234567891234', 1);
INSERT INTO administrator(first_name, last_name, jmbg, school_id) VALUES ('Nikola', 'Nikolic', '9876543214321', 2);

SELECT * FROM contest;
SELECT * FROM contest_quotas;
SELECT * FROM course_quota;
SELECT * FROM course;
