# Job Portal Application

## Overview
The Job Portal Application is a web-based platform that allows job seekers to search and apply for job openings, while employers can post and manage job listings. The application is built using Spring Boot for the backend, Thymeleaf for server-side rendering of views, and PostgreSQL as the database.

## Features
### For Job Seekers
Register and create a profile.
Browse and search for jobs by category, location, or keywords.
Apply for jobs and track application status.
Save favorite job postings.
### For Employers
Register and create a company profile.
Post new job openings with details like role, location, and salary.
View applications and manage job postings.

## Technologies Used
* Backend: Spring Boot
* Frontend: Thymeleaf, HTML, CSS, JavaScript
* Database: PostgreSQL
* Build Tool: Gradle
* Version Control: Git
* Others: Docker

## Using Local Docker
Build the Docker image:

`docker build -t job-portal:latest . `

Run the application using Docker Compose:

` docker-compose up `