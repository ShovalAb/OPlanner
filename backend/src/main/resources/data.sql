-- -- data.sql

-- Insert data into StudyPlan table
INSERT INTO study_plan (plan_name) VALUES ("מדעי המחשב");

-- Insert data into Course table
Insert INTO Course (name, number, credits_type, credits_number) VALUES ("מתמטיקה בדידה", 20476, "מתמטיקה", 4);
Insert INTO Course (name, number, credits_type, credits_number) VALUES ("אלגברה ליניארית 1", 20109, "מתמטיקה", 7);
Insert INTO Course (name, number, credits_type, credits_number) VALUES ("אלגברה ליניארית 2", 20229, "מתמטיקה", 5);

-- Insert data into Dependency table
INSERT INTO Dependency (dependent_course, base_course) VALUES (20229, "20476, 20109");

-- Insert data into Mandatory_Requirement table
INSERT INTO mandatory_requirement (plan_id, course_number) VALUES (1, 20476);
INSERT INTO mandatory_requirement (plan_id, course_number) VALUES (1, 20109);
INSERT INTO mandatory_requirement (plan_id, course_number) VALUES (1, 20229);

-- Insert data into Credits_Requirement table
INSERT INTO credits_requirement (plan_id, credits_type, credits_number) VALUES (1, "מתמטיקה", 35);
INSERT INTO credits_requirement (plan_id, credits_type, credits_number) VALUES (1, "מדעי המחשב", 42);
INSERT INTO credits_requirement (plan_id, credits_type, credits_number) VALUES (1, "מדעי המחשב", 24);
INSERT INTO credits_requirement (plan_id, credits_type, credits_number) VALUES (1, "מדעי המחשב", 3);
INSERT INTO credits_requirement (plan_id, credits_type, credits_number) VALUES (1, "מדעי המחשב", 3);

-- Insert data into Course_In_Study_Plan table
INSERT INTO course_in_study_plan (course_number, plan_id) VALUES (20476, 1);
INSERT INTO course_in_study_plan (course_number, plan_id) VALUES (20109, 1);
INSERT INTO course_in_study_plan (course_number, plan_id) VALUES (20229, 1);

-- Insert data into Credit_Types table
INSERT INTO credit_types (credits_type, sub_type) VALUES ("מדעי המחשב","מדעי המחשב, מדעי המחשב מתקדם");
INSERT INTO credit_types (credits_type, sub_type) VALUES ("מדעים", "פיזיקה, מתמטיקה, מדעי המחשב, מדעי המחשב מתקדם");
INSERT INTO credit_types (credits_type, sub_type) VALUES ("מדעי המחשב","מדעי המחשב מתקדם");


