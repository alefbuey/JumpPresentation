--Creation of Sequences
CREATE SEQUENCE userState_id_seq;
CREATE SEQUENCE userJump_id_seq;
CREATE SEQUENCE nationalIDType_id_seq;
CREATE SEQUENCE location_id_seq;
CREATE SEQUENCE ranking_id_seq;
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
CREATE TABLE Employee (Id int4 NOT NULL, Ranking int4 NOT NULL, NumbJobsDone int4, PRIMARY KEY (Id));
CREATE TABLE Employee_TagJump (IdEmployee int4 NOT NULL, IdTag int4 NOT NULL, PRIMARY KEY (IdEmployee, IdTag));
CREATE TABLE EmployeeJob (IdEmployee int4 NOT NULL, IdJob int4 NOT NULL, RankErE int4, RankEEr int4, Salary numeric(9, 2) NOT NULL, CounterOfer numeric(9, 2), State int4 NOT NULL, PRIMARY KEY (IdEmployee, IdJob));
CREATE TABLE EmployeeState (id SERIAL NOT NULL, description varchar(100) NOT NULL, PRIMARY KEY (id));
CREATE TABLE Employer (Id int4 NOT NULL, Ranking int4 NOT NULL, SpentAmount numeric(7, 2) NOT NULL, JobsPosted int4 NOT NULL, PRIMARY KEY (Id));
CREATE TABLE FavoritesJobs (IdEmployee int4 NOT NULL, IdJob int4 NOT NULL, PRIMARY KEY (IdEmployee, IdJob));
CREATE TABLE Followers (IdFollower int4 NOT NULL, IdFollowed int4 NOT NULL, PRIMARY KEY (IdFollower, IdFollowed));
CREATE TABLE Job (Id SERIAL NOT NULL, IdEmployeer int4 NOT NULL, Mode int4 NOT NULL, State int4 NOT NULL, IdLocation int4 NOT NULL, Title varchar(100) NOT NULL, Description varchar(500) NOT NULL, JobCost numeric(9, 2) NOT NULL, PhotoPath varchar(500) NOT NULL, DatePosted date NOT NULL, DateStart date NOT NULL, DateEnd date NOT NULL, DatePostEnd date NOT NULL, AvailablePercentage int4 NOT NULL, NumberApplicants int4 DEFAULT 1 NOT NULL, PRIMARY KEY (Id));
CREATE TABLE Job_TagJump (IdJob int4 NOT NULL, IdTag int4 NOT NULL, PRIMARY KEY (IdJob, IdTag));
CREATE TABLE JobAddress (IdJob int4 NOT NULL, Direction int4 NOT NULL, Latitude numeric(10, 8) NOT NULL, Longitude numeric(10, 8) NOT NULL, PRIMARY KEY (IdJob));
CREATE TABLE JobMode (Id SERIAL NOT NULL, Mode varchar(20) NOT NULL, PRIMARY KEY (Id));
CREATE TABLE JobPhotos (IdJob int4 NOT NULL, Image bytea NOT NULL);
CREATE TABLE JobState (Id SERIAL NOT NULL, State varchar(10) NOT NULL, PRIMARY KEY (Id));
CREATE TABLE Location (Id SERIAL NOT NULL, Country varchar(30) NOT NULL, City varchar(30) NOT NULL, PRIMARY KEY (Id));
CREATE TABLE NationalIdentifierType (id SERIAL NOT NULL, description varchar(100) NOT NULL, PRIMARY KEY (id));
CREATE TABLE PaypalAccount (Id int4 NOT NULL, IdUser int4 NOT NULL, Email varchar(25), PRIMARY KEY (Id, IdUser));
CREATE TABLE Ranking (Id SERIAL NOT NULL, RankVal numeric(2, 1) NOT NULL, PRIMARY KEY (Id));
CREATE TABLE TagJump (Id SERIAL NOT NULL, Name varchar(50) NOT NULL, Description varchar(100), CategoryId int4 NOT NULL, PRIMARY KEY (Id));
CREATE TABLE TransactionJump (id SERIAL NOT NULL, idUser int4 NOT NULL, Idtype int4 NOT NULL, PRIMARY KEY (id));
CREATE TABLE TransactionType (id SERIAL NOT NULL, name int4 NOT NULL, description int4 NOT NULL, PRIMARY KEY (id));
CREATE TABLE UserJump (Id SERIAL NOT NULL, Id_Location int4 NOT NULL, IdState int4 NOT NULL, TypeNationalIdentifier int4 NOT NULL, NationalIdentifier int4 NOT NULL UNIQUE, Name varchar(30) NOT NULL, LastName varchar(30) NOT NULL, Email varchar(30) NOT NULL UNIQUE, Password varchar(15) NOT NULL, BirthDate date NOT NULL, Direction varchar(255), Gender char(1) NOT NULL, Nationality varchar(30) NOT NULL, AvailableMoney numeric(9, 2) NOT NULL, Nonce varchar(10) NOT NULL UNIQUE, PRIMARY KEY (Id));
CREATE TABLE UserStaff (IdUser int4 NOT NULL, About varchar(500) NOT NULL, PhotoPath varchar(225) NOT NULL, Cellphone varchar(20) NOT NULL, Image bytea NOT NULL, PRIMARY KEY (IdUser));
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
ALTER TABLE Ranking ALTER COLUMN id TYPE int4; 
ALTER TABLE TagJump ALTER COLUMN id TYPE int4; 
ALTER TABLE TransactionJump ALTER COLUMN id TYPE int4; 
ALTER TABLE TransactionType ALTER COLUMN id TYPE int4; 
ALTER TABLE UserJump ALTER COLUMN id TYPE int4; 
ALTER TABLE UserState ALTER COLUMN id TYPE int4; 
ALTER TABLE NationalIdentifierType ALTER COLUMN id TYPE int4; 

--Constraint
ALTER TABLE UserJump ADD CONSTRAINT FKUserJump139442 FOREIGN KEY (Id_Location) REFERENCES Location (Id);
ALTER TABLE Employee ADD CONSTRAINT FKEmployee996956 FOREIGN KEY (Id) REFERENCES UserJump (Id);
ALTER TABLE Employee_TagJump ADD CONSTRAINT FKEmployee_T827862 FOREIGN KEY (IdEmployee) REFERENCES Employee (Id);
ALTER TABLE Employee_TagJump ADD CONSTRAINT FKEmployee_T803399 FOREIGN KEY (IdTag) REFERENCES TagJump (Id);
ALTER TABLE Employer ADD CONSTRAINT FKEmployer996969 FOREIGN KEY (Id) REFERENCES UserJump (Id);
ALTER TABLE Employer ADD CONSTRAINT FKEmployer290876 FOREIGN KEY (Ranking) REFERENCES Ranking (Id);
ALTER TABLE Employee ADD CONSTRAINT FKEmployee290889 FOREIGN KEY (Ranking) REFERENCES Ranking (Id);
ALTER TABLE Job ADD CONSTRAINT FKJob21280 FOREIGN KEY (Mode) REFERENCES JobMode (Id);
ALTER TABLE Job ADD CONSTRAINT FKJob218444 FOREIGN KEY (State) REFERENCES JobState (Id);
ALTER TABLE Job ADD CONSTRAINT FKJob67976 FOREIGN KEY (IdEmployeer) REFERENCES Employer (Id);
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
ALTER TABLE Ranking ALTER COLUMN ID SET DEFAULT nextval('ranking_id_seq');
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
ALTER SEQUENCE ranking_id_seq OWNED BY ranking.id;
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

--Insert
