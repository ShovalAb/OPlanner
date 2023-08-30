# Oplanner - Study Plan for The Open University

## Description

Oplanner is a Spring Boot web Application designed to provide an interactive and simple way for the students to plan their study plan. The app will assist students in planning their courses and schedules effectively.

## Prerequisites
Before you begin, ensure you have the following prerequisites:

- Java 17
- Maven
- MySQL database
- npm
- Node.js

## Installation and Execution

- unzip Oplanner.zip
- one terminal for the backend
	- mvn -N wrapper:wrapper
	- ./mvnw clean install
	- ./mvnw spring-boot:run
- one terminal for the fronend
	- npm start

## DB Configuration
1. Open your MySQL command-line interface or a MySQL client like phpMyAdmin.
2. Create a new database named oplanner:
bash
CREATE DATABASE oplanner;

3. configure the DataSource programmatically using the DataSourceConfig class:
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/oplanner");
        dataSource.setUsername(<User>);
        dataSource.setPassword(<Password>); 
