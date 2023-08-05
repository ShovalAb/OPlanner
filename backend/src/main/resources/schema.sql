
-- Study plan
DROP TABLE IF EXISTS STUDY_PLAN;
CREATE TABLE  STUDY_PLAN(
  ID int auto_increment primary key NOT NULL,
  PLAN_NAME VARCHAR(100) NOT NULL
);

-- Course 
DROP TABLE IF EXISTS Course;
CREATE TABLE  Course(
  id int auto_increment primary key NOT NULL,
  course_Name VARCHAR(100) NOT NULL,
  course_Number int NOT NULL,
  credits_Type VARCHAR(100) NOT NULL,
  credits_Number int NOT NULL
);

-- Dependency
DROP TABLE IF EXISTS Dependency;
CREATE TABLE  Dependency(
  id int auto_increment primary key NOT NULL,
  dependent_course int NOT NULL,
  base_course int array[10] NOT NULL
);

-- MandatoryRequirement
DROP TABLE IF EXISTS Mandatory_Requirement;
CREATE TABLE  Mandatory_Requirement(
  id int auto_increment primary key NOT NULL,
  plan_Id int NOT NULL,
  course_Id int array[10] NOT NULL
  );

-- CreditsRequirement
DROP TABLE IF EXISTS Credits_Requirement;
CREATE TABLE  Credits_Requirement(
  id int auto_increment primary key NOT NULL,
  plan_Id int NOT NULL,
  credits_Type VARCHAR(100) NOT NULL,
  credits_Number int NOT NULL
);

-- Course In Study Plan
DROP TABLE IF EXISTS Course_In_Study_Plan;
CREATE TABLE  Course_In_Study_Plan(
  id int auto_increment primary key NOT NULL,
  course int NOT NULL,
  study_Plan_Id int NOT NULL,
  department VARCHAR(100) NOT NULL
);



