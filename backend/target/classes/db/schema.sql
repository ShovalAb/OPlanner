
-- Study plan
DROP TABLE IF EXISTS Study_Plan;
CREATE TABLE  Study_Plan(
  id int auto_increment primary key NOT NULL,
  plan_name VARCHAR(100) NOT NULL
);

-- Course 
DROP TABLE IF EXISTS Course;
CREATE TABLE  Course(
  id int auto_increment primary key NOT NULL,
  name VARCHAR(100) NOT NULL,
  number int NOT NULL,
  credits_type VARCHAR(100) NOT NULL,
  credits_number int NOT NULL
);

-- Dependency
DROP TABLE IF EXISTS Dependency;
CREATE TABLE  Dependency(
  id int auto_increment primary key NOT NULL,
  dependent_course int NOT NULL,
  base_course VARCHAR(100) NOT NULL
);

-- MandatoryRequirement
DROP TABLE IF EXISTS Mandatory_Requirement;
CREATE TABLE  Mandatory_Requirement(
  id int auto_increment primary key NOT NULL,
  plan_id int NOT NULL,
  course_number VARCHAR(100) NOT NULL
  );

-- CreditsRequirement
DROP TABLE IF EXISTS Credits_Requirement;
CREATE TABLE  Credits_Requirement(
  id int auto_increment primary key NOT NULL,
  plan_id int NOT NULL,
  credits_type VARCHAR(100) NOT NULL,
  credits_number int NOT NULL
);

-- Course In Study Plan
DROP TABLE IF EXISTS Course_In_Study_Plan;
CREATE TABLE  Course_In_Study_Plan(
  id int auto_increment primary key NOT NULL,
  course_number int NOT NULL,
  plan_Id int NOT NULL,
  department VARCHAR(100) NOT NULL
);



