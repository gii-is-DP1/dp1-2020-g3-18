
SET FOREIGN_KEY_CHECKS=0;
-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');

--ADMIN--ROL
INSERT INTO users(username,password,enabled) VALUES ('a','a',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (99,'a','admin');

--UNIVERSIDADES--DATOS
INSERT INTO colleges(id,name,city) VALUES (1,'etsii', 'Sevilla');

--EXPERIENCIA LABORAL--DATOS
INSERT INTO professionalexperiences(id,university,comment) VALUES (1, 'Universidad de Sevilla', 'Comentario de prueba 1.');

--INVESTIGACION--DATOS
INSERT INTO researchexperiences(id,research) VALUES (1, 'Investigación sobre como sobre como cocinar arroz sin que se te pegue con un boli y un transportador');

--EXPERIENCIA LABORAL--
INSERT INTO teachingexperiences(id,titulation,comment) VALUES (1, 'Graduado en ciencias sociales y económicas del gramo y medio', 'Comentario de prueba a ver que sale...');


--EVALUACION EXTERNA--DATOS
INSERT INTO externalevaluations(id,note,comment) VALUES (1, 4, 'Otro comentario de prueba a ver que sale...');

--EXPERIENCIA PERSONAL--DATOS
INSERT INTO personalexperiences(id,res_experience,tea_experience,pro_experience, ext_evaluation) VALUES (1,1,1,1,1);

--DECANOS--ROL
INSERT INTO users(username,password,enabled) VALUES ('josrojrom1','pepe12345',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (5,'josrojrom1','dean');
INSERT INTO users(username, password,enabled) VALUES ('d', 'd', TRUE);
INSERT INTO authorities(id,username,authority) VALUES (55,'d','dean');
--DECANOS--DATOS
INSERT INTO deans(id,first_name,last_name,username) VALUES (1, 'Pedro', 'Javieles', 'admin1');
INSERT INTO deans(id,first_name,last_name,username,college_id) VALUES (2, 'Paco', 'Administrador', 'a', 1);

--PROFESORES--ROL
INSERT INTO users(username, password, enabled) VALUES('t','t',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (888,'t','teacher');
INSERT INTO users(username,password,enabled) VALUES ('juloga1','juloga1',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (8,'juloga1','teacher');
INSERT INTO users(username,password,enabled) VALUES ('julian','julian',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (13,'julian','teacher');
INSERT INTO users(username,password,enabled) VALUES ('pop','pop',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (14,'pop','teacher');
INSERT INTO users(username,password,enabled) VALUES ('macarron32','macarron32',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (15,'macarron32','teacher');
INSERT INTO users(username,password,enabled) VALUES ('ambrella','ambrella',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (16,'ambrella','teacher');
INSERT INTO users(username,password,enabled) VALUES ('abe','abe',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (17,'abe','teacher');

INSERT INTO users(username,password,enabled) VALUES ('mamapon1','mamapon1',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (9,'mamapon1','teacher');

--Personal Experience Datos--
INSERT INTO personalexperiences(id,name) VALUES (80,'Personal Experience');


--PROFESORES--DATOS
INSERT INTO teachers(id,first_name,last_name,username, personal_experience_id) VALUES (1, 'Julian', 'Locuelo García', 'julian',80);
INSERT INTO teachers(id,first_name,last_name,username) VALUES (2, 'Popench', 'De los bosques Hernández', 'pop');
INSERT INTO teachers(id,first_name,last_name,username) VALUES (3, 'Macarena', 'Arejo Pínchez', 'macarron32');
INSERT INTO teachers(id,first_name,last_name,username) VALUES (4, 'Ambrosio', 'Ansiado Augusto', 'ambrella');
INSERT INTO teachers(id,first_name,last_name,username) VALUES (5, 'Abelle', 'Lambert Ginni', 'abe');

--ESTUDIANTES--ROL
INSERT INTO users(username,password,enabled) VALUES ('tomas','tomas12345',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (10,'tomas','student');
INSERT INTO users(username,password,enabled) VALUES ('fran','fran12345',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (11,'fran','student');
INSERT INTO users(username,password,enabled) VALUES ('s','s',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (12,'s','student');
INSERT INTO users(username,password,enabled) VALUES ('d1','d1',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (44,'d1','dean');
--ESTUDIANTES--DATOS
INSERT INTO students(id,first_name,last_name,username) VALUES (1, 'Tomás Francisco', 'Rodríguez Rodríguez', 'tomas');
INSERT INTO students(id,first_name,last_name,username) VALUES (66, 'administrador', 'Rodríguez Rodríguez', 'a');
INSERT INTO students(id,first_name,last_name,username) VALUES (2, 'Francisco', 'Fernández', 'fran');
INSERT INTO students(id,first_name,last_name,username) VALUES (3, 'Serperior', 'Surmano', 's');

--PUNTUACIONES--DATOS
INSERT INTO scores(id,valu,comment,student_id,teacher_id) VALUES (1, 1, 'Las clases de este profesor me parecen un autentico toston, creo que no debería deshaogarse tanto con nosotros',1,1);
INSERT INTO scores(id,valu,comment,student_id,teacher_id) VALUES (2, 3, 'Las clases de este profesor estan mejorando desde el ultimo trimestre, le falta confianza',1,1);
INSERT INTO scores(id,valu,comment,student_id,teacher_id) VALUES (3, 5, 'Me gusta mucho como da las clases y como ha evolucionado desde el inicio, un auténtico profesor',1,1);
INSERT INTO scores(id,valu,comment,student_id,teacher_id) VALUES (4, 3, 'Me gusta mucho como da las clases y como ha evolucionado desde el inicio, un auténtico profesor',1,2);
INSERT INTO scores(id,valu,comment,student_id,teacher_id) VALUES (5, 4, 'Me gusta mucho como da las clases y como ha evolucionado desde el inicio, un auténtico profesor',1,2);
INSERT INTO scores(id,valu,comment,student_id,teacher_id) VALUES (6, 5, 'Me gusta mucho como da las clases y como ha evolucionado desde el inicio, un auténtico profesor',1,3);

--REPORTES--DATOS
INSERT INTO reports(id,reason, score_id) VALUES (5,'Comportamiento abusivo', 1);

--DEPARTAMENTOS--DATOS
INSERT INTO departments(id,name) VALUES (1,'Lenguaje y sistemas informaticos');
INSERT INTO departments(id,name) VALUES (2,'Tecnologia electronica');
INSERT INTO departments(id,name) VALUES (3,'Matematicas aplicadas');
INSERT INTO departments(id,name) VALUES (4,'Ingenieria del diseño');
INSERT INTO departments(id,name) VALUES (5,'Electronica y magnetismo');

--TEACHING PLAN -- DATOS
INSERT INTO teaching_plans(id,name) VALUES (1,'teaching plan to Redes de Computadores');
INSERT INTO teaching_plans(id,name) VALUES (2,'teaching plan to Diseño y pruebas');


--ASIGNATURAS--DATOS
INSERT INTO subjects(id,name,curso,department_id, teaching_plan_id) VALUES (111,'Diseño y pruebas', 3,1,2);
INSERT INTO subjects(id,name,curso,department_id) VALUES (112,'Fundamentos Fisicos de la Informatica', 1,1);
INSERT INTO subjects(id,name,curso,department_id) VALUES (113,'Matematica Discreta', 2,2);
INSERT INTO subjects(id,name,curso,department_id) VALUES (114,'Circuitos Electronicos Digitales', 1,3);
INSERT INTO subjects(id,name,curso,department_id) VALUES (115,'Sistemas Operativos', 2,3);
INSERT INTO subjects(id,name,curso,department_id) VALUES (116,'Logica Informatica', 2,3);
INSERT INTO subjects(id,name,curso, teaching_plan_id) VALUES (117,'Redes de Computadores', 2,1);
INSERT INTO subjects(id,name,curso) VALUES (118,'Derecho de la Informatica', 4);

--ASIGNATURAS DE PROFESORES--
INSERT INTO teachers_subjects VALUES (1,111);
INSERT INTO teachers_subjects VALUES (2,112);
INSERT INTO teachers_subjects VALUES (1,113);
INSERT INTO teachers_subjects VALUES (3,114);
INSERT INTO teachers_subjects VALUES (2,118);
INSERT INTO teachers_subjects VALUES (4,115);
INSERT INTO teachers_subjects VALUES (5,117);

--DEPARTAMENTOS DE PROFESORES--
INSERT INTO teachers_departments VALUES (1,1);
INSERT INTO teachers_departments VALUES (2,2);
INSERT INTO teachers_departments VALUES (1,3);
INSERT INTO teachers_departments VALUES (3,2);
INSERT INTO teachers_departments VALUES (2,5);
INSERT INTO teachers_departments VALUES (4,4);
INSERT INTO teachers_departments VALUES (5,3);

--ASIGNATURAS DE ALUMNOS--
INSERT INTO students_subjects VALUES (2,111);
INSERT INTO students_subjects VALUES (2,112);
INSERT INTO students_subjects VALUES (2,113);
INSERT INTO students_subjects VALUES (1,114);
INSERT INTO students_subjects VALUES (1,115);
INSERT INTO students_subjects VALUES (3,116);


--ASIGNATURAS POR DEPARTAMENTO--
--INSERT INTO subjects_departments VALUES(111,1);
--INSERT INTO subjects_departments VALUES(112,2);
--INSERT INTO subjects_departments VALUES(113,1);
--INSERT INTO subjects_departments VALUES(114,2);


