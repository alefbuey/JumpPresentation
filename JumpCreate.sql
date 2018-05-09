--Creation of Sequences
CREATE SEQUENCE userState_id_seq;
CREATE SEQUENCE userJump_id_seq;
CREATE SEQUENCE nationalIDType_id_seq;
CREATE SEQUENCE location_id_seq;
CREATE SEQUENCE job_id_seq;
CREATE SEQUENCE employeeState_id_seq;
CREATE SEQUENCE tagJump_id_seq;
CREATE SEQUENCE category_id_seq;
CREATE SEQUENCE jobState_id_seq;
CREATE SEQUENCE comment_id_seq;
CREATE SEQUENCE jobMode_id_seq;
CREATE SEQUENCE transaction_id_seq;
CREATE SEQUENCE transactionType_id_seq;
CREATE SEQUENCE chat_id_seq;
CREATE SEQUENCE chatLine_id_seq;

--Creation of Tables
CREATE TABLE Card (IdCard int4 NOT NULL, IdUser int4 NOT NULL, NameOfCard varchar(100) NOT NULL, CSV int4 NOT NULL, ExpiredDate date NOT NULL, PRIMARY KEY (IdCard, IdUser));
CREATE TABLE Category (Id SERIAL NOT NULL, Nombre varchar(30) NOT NULL, Description varchar(50), PRIMARY KEY (Id));
CREATE TABLE Chat (Id SERIAL NOT NULL, PRIMARY KEY (Id));
CREATE TABLE ChatLine (Id SERIAL NOT NULL, IdChat int4 NOT NULL, IdUser int4 NOT NULL, LineText text NOT NULL, WrittenDate timestamp NOT NULL, PRIMARY KEY (Id));
CREATE TABLE Comment (Id SERIAL NOT NULL, IdJob int4 NOT NULL, IdUser int4 NOT NULL, IdCommentParent int4 DEFAULT 0 NOT NULL, Description varchar(255), "Date" timestamp, PRIMARY KEY (Id));
CREATE TABLE Employee (Id int4 NOT NULL, Ranking numeric(2, 1) NOT NULL, NumbJobsDone int4, PRIMARY KEY (Id));
CREATE TABLE Employee_TagJump (IdEmployee int4 NOT NULL, IdTag int4 NOT NULL, PRIMARY KEY (IdEmployee, IdTag));
CREATE TABLE EmployeeJob (IdEmployee int4 NOT NULL, IdJob int4 NOT NULL, RankErE numeric(2, 1), RankEEr numeric(2, 1), Salary numeric(9, 2) NOT NULL, CounterOfer numeric(9, 2), State int4 NOT NULL, PRIMARY KEY (IdEmployee, IdJob));
CREATE TABLE EmployeeState (id SERIAL NOT NULL, description varchar(100) NOT NULL, PRIMARY KEY (id));
CREATE TABLE Employer (Id int4 NOT NULL, Ranking numeric(2, 1) NOT NULL, SpentAmount numeric(7, 2) NOT NULL, JobsPosted int4 NOT NULL, PRIMARY KEY (Id));
CREATE TABLE FavoritesJobs (IdEmployee int4 NOT NULL, IdJob int4 NOT NULL, PRIMARY KEY (IdEmployee, IdJob));
CREATE TABLE Followers (IdFollower int4 NOT NULL, IdFollowed int4 NOT NULL, PRIMARY KEY (IdFollower, IdFollowed));
CREATE TABLE Job (Id SERIAL NOT NULL, IdEmployer int4 NOT NULL, Mode int4 NOT NULL, State int2 NOT NULL, IdLocation int4, Title varchar(100) NOT NULL, Description varchar(500) NOT NULL, JobCost numeric(10, 2) NOT NULL, JobCostCovered numeric(10, 2) NOT NULL, DatePosted date NOT NULL, DateStart date NOT NULL, DateEnd date, DatePostEnd date NOT NULL, NumberVacancies int2 DEFAULT 1 NOT NULL, PRIMARY KEY (Id));
CREATE TABLE Job_TagJump (IdJob int4 NOT NULL, IdTag int4 NOT NULL, PRIMARY KEY (IdJob, IdTag));
CREATE TABLE JobAddress (IdJob int4 NOT NULL, Direction int4 NOT NULL, Latitude numeric(10, 8) NOT NULL, Longitude numeric(10, 8) NOT NULL, PRIMARY KEY (IdJob));
CREATE TABLE JobMode (Id SERIAL NOT NULL, Mode varchar(20) NOT NULL, PRIMARY KEY (Id));
CREATE TABLE JobPhotos (IdJob int4 NOT NULL, Image bytea NOT NULL);
CREATE TABLE JobState (Id SERIAL NOT NULL, State varchar(30) NOT NULL, PRIMARY KEY (Id));
CREATE TABLE Location (Id SERIAL NOT NULL, Country varchar(30) NOT NULL, City varchar(30) NOT NULL, PRIMARY KEY (Id));
CREATE TABLE NationalIdentifierType (id SERIAL NOT NULL, description varchar(100) NOT NULL, PRIMARY KEY (id));
CREATE TABLE PaypalAccount (Id int4 NOT NULL, IdUser int4 NOT NULL, Email varchar(25), PRIMARY KEY (Id, IdUser));
CREATE TABLE Preferences (idTag int4 NOT NULL, IdUser int4 NOT NULL, PRIMARY KEY (idTag, IdUser));
CREATE TABLE TagJump (Id SERIAL NOT NULL, Name varchar(50) NOT NULL, Description varchar(100), CategoryId int4 NOT NULL, PRIMARY KEY (Id));
CREATE TABLE TransactionJump (id SERIAL NOT NULL, idUser int4 NOT NULL, Idtype int4 NOT NULL, PRIMARY KEY (id));
CREATE TABLE TransactionType (id SERIAL NOT NULL, name int4 NOT NULL, description int4 NOT NULL, PRIMARY KEY (id));
CREATE TABLE UserJump (Id SERIAL NOT NULL, IdLocation int4, IdState int4, TypeNationalIdentifier int4, NationalIdentifier int4 UNIQUE, Name varchar(30) NOT NULL, LastName varchar(30) NOT NULL, Email varchar(30) NOT NULL UNIQUE, Password varchar(255) NOT NULL, BirthDate date NOT NULL, Direction varchar(255), Gender char(1) NOT NULL, Nationality varchar(30), AvailableMoney numeric(9, 2), Nonce varchar(255) NOT NULL UNIQUE, rank numeric(2, 1), PRIMARY KEY (Id));
CREATE TABLE UserStaff (IdUser int4 NOT NULL, About varchar(500) NOT NULL, Cellphone varchar(20) NOT NULL, Image bytea NOT NULL, PRIMARY KEY (IdUser));
CREATE TABLE UserState (Id SERIAL NOT NULL, State varchar(10) NOT NULL, PRIMARY KEY (Id));




--Change serial with int
ALTER TABLE Category ALTER COLUMN id TYPE int4; 
ALTER TABLE Chat ALTER COLUMN id TYPE int4;
ALTER TABLE ChatLine ALTER COLUMN id TYPE int4; 
ALTER TABLE Comment ALTER COLUMN id TYPE int4; 
ALTER TABLE EmployeeState ALTER COLUMN id TYPE int4; 
ALTER TABLE Job ALTER COLUMN id TYPE int4; 
ALTER TABLE JobMode ALTER COLUMN id TYPE int4; 
ALTER TABLE JobState ALTER COLUMN id TYPE int4; 
ALTER TABLE Location ALTER COLUMN id TYPE int4; 
ALTER TABLE TagJump ALTER COLUMN id TYPE int4; 
ALTER TABLE TransactionJump ALTER COLUMN id TYPE int4; 
ALTER TABLE TransactionType ALTER COLUMN id TYPE int4; 
ALTER TABLE UserJump ALTER COLUMN id TYPE int4; 
ALTER TABLE UserState ALTER COLUMN id TYPE int4; 
ALTER TABLE NationalIdentifierType ALTER COLUMN id TYPE int4; 

--Constraint
ALTER TABLE UserJump ADD CONSTRAINT FKUserJump853392 FOREIGN KEY (IdLocation) REFERENCES Location (Id);
ALTER TABLE Employee ADD CONSTRAINT FKEmployee996956 FOREIGN KEY (Id) REFERENCES UserJump (Id);
ALTER TABLE Employee_TagJump ADD CONSTRAINT FKEmployee_T827862 FOREIGN KEY (IdEmployee) REFERENCES Employee (Id);
ALTER TABLE Employee_TagJump ADD CONSTRAINT FKEmployee_T803399 FOREIGN KEY (IdTag) REFERENCES TagJump (Id);
ALTER TABLE Employer ADD CONSTRAINT FKEmployer996969 FOREIGN KEY (Id) REFERENCES UserJump (Id);
ALTER TABLE Job ADD CONSTRAINT FKJob21280 FOREIGN KEY (Mode) REFERENCES JobMode (Id);
ALTER TABLE Job ADD CONSTRAINT FKJob218444 FOREIGN KEY (State) REFERENCES JobState (Id);
ALTER TABLE Job ADD CONSTRAINT FKJob67976 FOREIGN KEY (IdEmployer) REFERENCES Employer (Id);
ALTER TABLE EmployeeJob ADD CONSTRAINT FKEmployeeJo204629 FOREIGN KEY (IdEmployee) REFERENCES Employee (Id);
ALTER TABLE EmployeeJob ADD CONSTRAINT FKEmployeeJo483457 FOREIGN KEY (IdJob) REFERENCES Job (Id);
ALTER TABLE Job_TagJump ADD CONSTRAINT FKJob_TagJum687162 FOREIGN KEY (IdJob) REFERENCES Job (Id);
ALTER TABLE Job_TagJump ADD CONSTRAINT FKJob_TagJum990453 FOREIGN KEY (IdTag) REFERENCES TagJump (Id);
ALTER TABLE Job ADD CONSTRAINT FKJob50815 FOREIGN KEY (IdLocation) REFERENCES Location (Id);
ALTER TABLE UserJump ADD CONSTRAINT FKUserJump698533 FOREIGN KEY (IdState) REFERENCES UserState (Id);
ALTER TABLE Comment ADD CONSTRAINT FKComment369264 FOREIGN KEY (IdJob) REFERENCES Job (Id);
ALTER TABLE Comment ADD CONSTRAINT FKComment890759 FOREIGN KEY (IdUser) REFERENCES UserJump (Id);
ALTER TABLE Comment ADD CONSTRAINT FKComment218269 FOREIGN KEY (IdCommentParent) REFERENCES Comment (Id);
ALTER TABLE UserStaff ADD CONSTRAINT FKUserStaff243811 FOREIGN KEY (IdUser) REFERENCES UserJump (Id);
ALTER TABLE JobAddress ADD CONSTRAINT FKJobAddress850771 FOREIGN KEY (IdJob) REFERENCES Job (Id);
ALTER TABLE ChatLine ADD CONSTRAINT FKChatLine684908 FOREIGN KEY (IdChat) REFERENCES Chat (Id);
ALTER TABLE ChatLine ADD CONSTRAINT FKChatLine359812 FOREIGN KEY (IdUser) REFERENCES UserJump (Id);
ALTER TABLE TagJump ADD CONSTRAINT FKTagJump445836 FOREIGN KEY (CategoryId) REFERENCES Category (Id);
ALTER TABLE EmployeeJob ADD CONSTRAINT FKEmployeeJo623179 FOREIGN KEY (State) REFERENCES EmployeeState (id);
ALTER TABLE UserJump ADD CONSTRAINT FKUserJump746039 FOREIGN KEY (TypeNationalIdentifier) REFERENCES NationalIdentifierType (id);
ALTER TABLE FavoritesJobs ADD CONSTRAINT FKFavoritesJ937340 FOREIGN KEY (IdEmployee) REFERENCES Employee (Id);
ALTER TABLE FavoritesJobs ADD CONSTRAINT FKFavoritesJ658512 FOREIGN KEY (IdJob) REFERENCES Job (Id);
ALTER TABLE Followers ADD CONSTRAINT FKFollowers802750 FOREIGN KEY (IdFollower) REFERENCES UserJump (Id);
ALTER TABLE Followers ADD CONSTRAINT FKFollowers802736 FOREIGN KEY (IdFollowed) REFERENCES UserJump (Id);
ALTER TABLE TransactionJump ADD CONSTRAINT FKTransactio399029 FOREIGN KEY (Idtype) REFERENCES TransactionType (id);
ALTER TABLE JobPhotos ADD CONSTRAINT FKJobPhotos830987 FOREIGN KEY (IdJob) REFERENCES Job (Id);
ALTER TABLE Preferences ADD CONSTRAINT FKPreference834315 FOREIGN KEY (idTag) REFERENCES TagJump (Id);
ALTER TABLE Preferences ADD CONSTRAINT FKPreference569715 FOREIGN KEY (IdUser) REFERENCES UserJump (Id);









--Constraints extras
--Añadir el constraint para los rankings

--Alter Tables with sequences
ALTER TABLE Category ALTER COLUMN ID SET DEFAULT nextval('category_id_seq');
ALTER TABLE Chat ALTER COLUMN ID SET DEFAULT nextval('chat_id_seq');
ALTER TABLE ChatLine ALTER COLUMN ID SET DEFAULT nextval('chatLine_id_seq');
ALTER TABLE Comment ALTER COLUMN ID SET DEFAULT nextval('comment_id_seq');
ALTER TABLE EmployeeState ALTER COLUMN ID SET DEFAULT nextval('employeeState_id_seq');
ALTER TABLE Job ALTER COLUMN ID SET DEFAULT nextval('job_id_seq');
ALTER TABLE JobMode ALTER COLUMN ID SET DEFAULT nextval('jobMode_id_seq');
ALTER TABLE JobState ALTER COLUMN ID SET DEFAULT nextval('jobState_id_seq');
ALTER TABLE Location ALTER COLUMN ID SET DEFAULT nextval('location_id_seq');
ALTER TABLE TagJump ALTER COLUMN ID SET DEFAULT nextval('tagJump_id_seq');
ALTER TABLE TransactionJump ALTER COLUMN ID SET DEFAULT nextval('transaction_id_seq');
ALTER TABLE TransactionType ALTER COLUMN ID SET DEFAULT nextval('transactionType_id_seq');
ALTER TABLE UserJump ALTER COLUMN ID SET DEFAULT nextval('userJump_id_seq');
ALTER TABLE UserState ALTER COLUMN ID SET DEFAULT nextval('userState_id_seq');
ALTER TABLE NationalIdentifierType ALTER COLUMN ID SET DEFAULT nextval('nationalIDType_id_seq');

--Alter Sequences
ALTER SEQUENCE userState_id_seq OWNED BY userState.id;
ALTER SEQUENCE userJump_id_seq OWNED BY userJump.id;
ALTER SEQUENCE nationalIDType_id_seq OWNED BY nationalIdentifierType.id;
ALTER SEQUENCE location_id_seq OWNED BY location.id;
ALTER SEQUENCE job_id_seq OWNED BY job.id;
ALTER SEQUENCE employeeState_id_seq OWNED BY employeeState.id;
ALTER SEQUENCE tagJump_id_seq OWNED BY tagJump.id;
ALTER SEQUENCE category_id_seq OWNED BY category.id;
ALTER SEQUENCE jobState_id_seq OWNED BY jobState.id;
ALTER SEQUENCE comment_id_seq OWNED BY comment.id;
ALTER SEQUENCE jobMode_id_seq OWNED BY jobMode.id;
ALTER SEQUENCE transaction_id_seq OWNED BY TransactionJump.id;
ALTER SEQUENCE transactionType_id_seq OWNED BY transactionType.id;
ALTER SEQUENCE chat_id_seq OWNED BY chat.id;
ALTER SEQUENCE chatLine_id_seq OWNED BY chatLine.id;

--DROP


--Insert
insert into userstate (state)values
('Active'),
('Inactive'),
('Banned');

insert into location(country,city) values
('Ecuador','Quito'),
('United States','New York'),
('Colombia','Bogota');

insert into NationalIdentifierType(description) values
('Identification Card'),
('Passport');

insert into UserJump(idLocation,idstate,typeNationalIdentifier,nationalidentifier,name,lastname,email,password,birthdate,direction,gender,nationality,availablemoney,nonce,rank) values
(1,1,1,'123456789','Fernanda','Zapata','ferchz123@gmail.com','f123','1996-04-25','Quicentro Sur','F','Ecuadorian',1000.00,'A1H3F',4.5),
(1,2,1,'789465198','Camilo','Guitierrez','camilo566@gmail.com','c566','1998-04-25','Alameda','M','Ecuadorian',500.00,'W7G3R',2.3),
(1,3,1,'516549716','Laura','Rivera','laur894@gmail.com','l894','1995-03-21','Amaguaña','F','Ecuadorian',20.00,'T9K4I',3.5),
(2,1,2,'198952358','Enrique','Rivera','erivera879@gmail.com','e879','2000-05-17','Central Park','M','American',50.00,'U9D0E',4.7),
(3,1,1,'161698726','Jaime','Alban','jaimealba451@gmail.com','j451','1996-07-21','Iglesia de Veracruz','M','Colombian',700.00,'G79W5E',4.0);

insert into UserStaff values
(2,'Estudie en Yachay Tech. Ingeniero gradudado con conocimientos en Programacion Web, Inteligencia Artificial. Me considero una persona capaz de tomar nuevos retos e iniciativas','0984657213',bytea('/opt/PostgreSQL/images/profile/2.jpg
'));

insert into Employee values
(1,4.5,8),
(2,3.5,9),
(3,5.0,15),
(4,4.3,8),
(5,2.5,2);

insert into Employer values
(1,2.5,1500.00,2),
(2,4.5,2500.00,3),
(3,3.0,3000.00,4),
(4,2.3,500.00,1),
(5,4.5,10000.0,8);

insert into jobState values 
(default, 'Posted'),
(default, 'Posted_InCourse'),
(default, 'InCourse'),
(default, 'FinishedNoPay'),
(default, 'Paid');


insert into jobmode values
(default, 'Single'),
(default, 'Single_Physical'),
(default, 'Team'),
(default, 'Team_Physical');

insert into job values
(default, 1, 1,1,1,'Web Page Development','No description yet', 10000,5000, '2018-04-24','2018-04-30','2018-09-30','2018-04-29',1),
(default, 2, 3,1,2,'Database design','No description yet', 100,50, '2018-03-24','2018-05-30','2018-06-30','2018-05-15',3),
(default, 3, 2,1,3,'Walking my dog','Just walk my dog!', 10,10, '2018-04-24','2018-04-26','2018-04-26','2018-04-25',1);

insert into employeestate values 
(default, 'Applying'),
(default, 'Working');

insert into employeejob values
(4,2,null,null,50,null,1);

insert into favoritesjobs values
(1,1),
(1,2),
(1,3);

insert into category (nombre, description) values
('Programming', ''),
('Home',''),
('Teaching','');

insert into tagjump (name, description, categoryid) values
('Web Programming', '', 1),
('Haskell', '', 1),
('Postgres', '', 1),
('Plumbery', '', 2),
('Carpentry', '', 2),
('Clean house', '', 2),
('Math tutoring', '', 3),
('English tutoring', '', 3),
('Programming teaching', '', 3);

insert into preferences values
(1,2),
(2,2),
(3,2),
(4,2);

