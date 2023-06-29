#UPDATE diploma
#SET student_id = NULL;

#UPDATE student
#SET school_id = NULL;

#DELETE FROM diploma_gpa_mapping;
#DELETE FROM student_gpa_mapping;

#DELETE FROM administrator;
#DELETE FROM contest;
#DELETE FROM contest_application;
#DELETE FROM contest_quotas;
#DELETE FROM course;
#DELETE FROM course_quota;
#DELETE FROM diploma;
#DELETE FROM school;
#DELETE FROM student;
#DELETE FROM notification;

INSERT INTO school(id, name, place, type) VALUES (1, 'Vuk Karadzic', 'Loznica', 'OSNOVNA');
INSERT INTO school(id, name, place, type) VALUES (2, 'Nikola Tesla', 'Novi Sad', 'SREDNJA');

INSERT INTO course(id, name, school_id) VALUES (1, 'Informacione Tehnologije', 2);
INSERT INTO course(id, name, school_id) VALUES (2, 'Masinski tehnicar',  2);

INSERT INTO administrator(id, first_name, last_name, jmbg, school_id) VALUES (1, 'Stefan', 'Vlajkovic', '1234567891234', 1);
INSERT INTO administrator(id, first_name, last_name, jmbg, school_id) VALUES (2, 'Nikola', 'Nikolic', '9876543214321', 2);

INSERT INTO student(id, first_name, last_name, jmbg, school_id, course) VALUES (1, 'Petar', 'Petrovic', '123412341234', 1, null);
#INSERT INTO student(id, first_name, last_name, jmbg, school_id, course) VALUES (2, 'Petar', 'Petrovic', '123412341234', 2, 1);

INSERT INTO student_gpa_mapping(student_id, gpa, grade_year) VALUES (1, 4.50, 1);
INSERT INTO student_gpa_mapping(student_id, gpa, grade_year) VALUES (1, 4.50, 2);
INSERT INTO student_gpa_mapping(student_id, gpa, grade_year) VALUES (1, 4.50, 3);
INSERT INTO student_gpa_mapping(student_id, gpa, grade_year) VALUES (1, 4.50, 4);

INSERT INTO diploma(id, course, profession, date, school_id, student_id) VALUES (1, null, 'OTHER', '2022-07-01', 1, 1);
#INSERT INTO diploma(id, course, profession, date, school_id, student_id) VALUES (2, 1, 'OTHER', '2022-07-01', 2, 1);

INSERT INTO diploma_gpa_mapping(diploma_id, gpa, grade_year) VALUES (1, 4.50, 5);
INSERT INTO diploma_gpa_mapping(diploma_id, gpa, grade_year) VALUES (1, 4.50, 6);
INSERT INTO diploma_gpa_mapping(diploma_id, gpa, grade_year) VALUES (1, 4.50, 7);
INSERT INTO diploma_gpa_mapping(diploma_id, gpa, grade_year) VALUES (1, 4.50, 8);

#INSERT INTO diploma_gpa_mapping(diploma_id, gpa, grade_year) VALUES (2, 4.50, 1);
#INSERT INTO diploma_gpa_mapping(diploma_id, gpa, grade_year) VALUES (2, 4.50, 2);
#INSERT INTO diploma_gpa_mapping(diploma_id, gpa, grade_year) VALUES (2, 4.50, 3);
#INSERT INTO diploma_gpa_mapping(diploma_id, gpa, grade_year) VALUES (2, 4.50, 4);