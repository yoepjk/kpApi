
CREATE TABLE finance (
  id int NOT NULL auto_increment,
  year int NOT NULL,
  month int NOT NULL,
  institute_code varchar(30) NOT NULL,
  value int NOT NULL,
  PRIMARY KEY  (id)
);

CREATE INDEX year ON finance(year);
CREATE INDEX month ON finance(month);
CREATE INDEX institute_code ON finance(institute_code);


CREATE TABLE institute (
  id int NOT NULL auto_increment,
  institute_name varchar(30) NOT NULL,
  institute_code varchar(30) NOT NULL,
  PRIMARY KEY  (id)
);

