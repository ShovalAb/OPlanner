
-- Study plan
DROP TABLE IF EXISTS Study_Plan;
DROP TABLE IF EXISTS study_plan;

CREATE TABLE  study_plan(
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
DROP TABLE IF EXISTS mandatory_requirement;

CREATE TABLE  mandatory_requirement(
  id int auto_increment primary key NOT NULL,
  plan_id int NOT NULL,
  course_number VARCHAR(100) NOT NULL
  );

-- CreditsRequirement
DROP TABLE IF EXISTS credits_requirement;

CREATE TABLE  credits_requirement(
  id int auto_increment primary key NOT NULL,
  plan_id int NOT NULL,
  credits_type VARCHAR(100) NOT NULL,
  credits_number int NOT NULL
);

-- Course In Study Plan
DROP TABLE IF EXISTS course_in_study_plan;

CREATE TABLE  course_in_study_plan(
  id int auto_increment primary key NOT NULL,
  course_number int NOT NULL,
  plan_Id int NOT NULL
);

-- Credits types
DROP TABLE IF EXISTS credit_types;

CREATE TABLE  credit_types(
  id int auto_increment primary key NOT NULL,
  credits_type VARCHAR(100) NOT NULL,
  sub_type VARCHAR(100) NOT NULL
);



