INSERT INTO school(name, place, type) VALUES ('Vuk Karadzic', 'Loznica', 'OSNOVNA');
INSERT INTO school(name, place, type) VALUES ('Nikola Tesla', 'Novi Sad', 'SREDNJA');

INSERT INTO course(name, school_id) VALUES ('Informacione Tehnologije', 2);
INSERT INTO course(name, school_id) VALUES ('Masinski tehnicar',  2);

INSERT INTO administrator(first_name, last_name, jmbg, school_id) VALUES ('Stefan', 'Vlajkovic', '1234567891234', 1);
INSERT INTO administrator(first_name, last_name, jmbg, school_id) VALUES ('Nikola', 'Nikolic', '9876543214321', 2);

INSERT INTO student(first_name, last_name, jmbg, school_id, course) VALUES ('Petar', 'Petrovic', '123412341234', 1, null);
INSERT INTO student_gpa_mapping(student_id, gpa, grade_year) VALUES (1, 4.50, 5);
INSERT INTO student_gpa_mapping(student_id, gpa, grade_year) VALUES (1, 4.50, 6);
INSERT INTO student_gpa_mapping(student_id, gpa, grade_year) VALUES (1, 4.50, 7);
INSERT INTO student_gpa_mapping(student_id, gpa, grade_year) VALUES (1, 4.50, 8);

INSERT INTO diploma(course, profession, date, school_id, student_id) VALUES (null, 'OTHER', '2022-07-01', 1, 1);
INSERT INTO diploma_gpa_mapping(diploma_id, gpa, grade_year) VALUES (1, 4.50, 5);
INSERT INTO diploma_gpa_mapping(diploma_id, gpa, grade_year) VALUES (1, 4.50, 6);
INSERT INTO diploma_gpa_mapping(diploma_id, gpa, grade_year) VALUES (1, 4.50, 7);
INSERT INTO diploma_gpa_mapping(diploma_id, gpa, grade_year) VALUES (1, 4.50, 8);


SELECT * FROM contest;
SELECT * FROM contest_quotas;
SELECT * FROM course_quota;
SELECT * FROM course;
SELECT * FROM diploma;
SELECT * FROM contest_application;
SELECT * FROM student;
