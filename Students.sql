drop database if exists Students;
create database if not exists Students;
use Students;

create table ADRESA(
id int unique auto_increment primary key,
tara char(10),
oras varchar(20),
strada char(20));

create table STUDENT(
id int unique auto_increment primary key,
nume char(40),
data_nasterii varchar(10),
id_adresa int,
foreign key (id_adresa) references ADRESA(id));

create table CURS(
id int unique auto_increment primary key,
nume char(40),
profesor char(40),
an_studiu int);

create table INROLARE(
id int unique auto_increment primary key,
id_student int,
id_curs int,
foreign key (id_student) references STUDENT(id),
foreign key (id_curs) references CURS(id));

insert into ADRESA values
(1,'Romania', 'Jibou', 'Stadionului'),
(2,'Romania', 'Cluj-Napoca', 'Padurii'),
(3,'Romania', 'Cluj-Napoca','Ferdinand');

insert into STUDENT values
(1,'Sechel Raluca','1995-07-15',1),
(2,'Dregan Anda', '1995-06-14',2),
(3,'Matei Dan','1994-02-08',3),
(4,'Ionescu Vlad','1993-05-10',1);

insert into CURS values
(1,'Tehnici de programare','Pop Radu',2),
(2,'Inginerie software','Rus Cornel',3),
(3,'Proiectare software','Popescu Ion',3);
insert into CURS values
(4,'Matematica','Sechel Stefan',1);

insert into INROLARE values
(1,1,1),
(2,1,2),
(3,2,1);

DROP PROCEDURE IF EXISTS STUDENT_NOU;
DELIMITER //

CREATE PROCEDURE STUDENT_NOU(nume char(20),data_nasterii date,id_adresa int)
  BEGIN
		SET @idStudent := NULL;
		
        SELECT @idStudent := MAX(id) from STUDENT;
        SET @idStudent := @idStudent+1;
        
        INSERT INTO STUDENT
        VALUES(@idStudent,nume,data_nasterii,id_adresa);
        
  END //
DELIMITER ;

call STUDENT_NOU('Dregan Anda','1995-06-14',2);


DROP PROCEDURE IF EXISTS CURS_NOU;
DELIMITER //

CREATE PROCEDURE CURS_NOU(nume char(20),profesor char(20),an_studiu int)
  BEGIN
		SET @idCurs := NULL;
		
        SELECT @idCurs := MAX(id) from CURS;
        SET @idCurs := @idCurs+1;
        
        INSERT INTO CURS
        VALUES(@idCurs,nume,profesor,an_studiu);
        
  END //
DELIMITER ;

call CURS_NOU('Matematica','Sechel Stefan',4);


DROP PROCEDURE IF EXISTS ACTUALIZARE_STUDENT;

DELIMITER //
CREATE PROCEDURE ACTUALIZARE_STUDENT(numeS char(20),tara char(10), oras char(10), strada char(10))
  BEGIN
    START TRANSACTION;

            UPDATE adresa,student SET adresa.tara = tara,adresa.oras = oras,adresa.strada = strada
            WHERE
            student.nume=numeS and adresa.id=student.id_adresa;
   			COMMIT;
  END //
DELIMITER ;

call ACTUALIZARE_STUDENT('Sechel Raluca','Romania','Jibou','Padurii');


DROP PROCEDURE IF EXISTS ACTUALIZARE_CURS;

DELIMITER //
CREATE PROCEDURE ACTUALIZARE_CURS(numeC char(20),profesor char(10), an_studiu int)
  BEGIN
    START TRANSACTION;

            UPDATE CURS SET curs.profesor = profesor,curs.an_studiu=an_studiu
            WHERE
            curs.nume=numeC;
   			COMMIT;
  END //
DELIMITER ;


call ACTUALIZARE_CURS('Inginerie Software','Sechel Stefan',2);

DROP PROCEDURE IF EXISTS STERGE_STUDENT;
DELIMITER //

CREATE PROCEDURE STERGE_STUDENT(numeS char(20))
  BEGIN
		
  DELETE student , inrolare  FROM student  INNER JOIN inrolare  
  WHERE student.nume= numeS and student.id=inrolare.id_student;
        
  END //
DELIMITER ;

call STERGE_STUDENT('Dregan Anda');


DROP PROCEDURE IF EXISTS STERGE_CURS;
DELIMITER //

CREATE PROCEDURE STERGE_CURS(numeC char(20))
  BEGIN
		
  DELETE curs , inrolare  FROM curs  INNER JOIN inrolare  
  WHERE curs.nume= numeC and curs.id=inrolare.id_curs;
        
  END //
DELIMITER ;

call STERGE_CURS('Matematica');

DROP PROCEDURE IF EXISTS INROLARE_PR;

DELIMITER //
CREATE PROCEDURE INROLARE_PR(id_st int,id_curs int)
  BEGIN
		SET @idInr := NULL;
		
        SELECT @idInr := MAX(id) from INROLARE;
        SET @idInr := @idInr+1;
        
        INSERT INTO INROLARE
        VALUES(@idInr,id_st,id_curs);
        
  END //
DELIMITER ;

call INROLARE_PR(1,2);


select * from CURS where curs.id=4;

select * from STUDENT;

select * from CURS;

select *from ADRESA;

select * from INROLARE;

#PT UPDATE !!
select s.data_nasterii as DATA_NASTERII, a.tara as TARA, a.oras as ORAS, a.strada as STRADA
		    		from student s,adresa a
		    		where s.nume='Dregan Anda'
		    		and s.id_adresa=a.id;
                    
select c.nume,s.nume
from curs c,student s,inrolare i
where c.nume='Tehnici de programare'
and c.id=i.id_curs
and s.id=i.id_student;


