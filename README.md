# Job Portal Application

![](https://github.com/procha159/job-portal/blob/main/readme-resources/Job-App.gif)

## Recruiter Dashboard
![](https://github.com/procha159/job-portal/blob/main/readme-resources/Job-App-Recruiter.gif)

## Overview
The Job Portal Application is a web-based platform that allows jobseekers to search and apply for job openings, while employers can post and manage job listings. The application is built using Spring Boot for the backend, Thymeleaf for server-side rendering of views, and PostgreSQL as the database.

## Features

### Account Registration
Users can register for a Recruiter or Job Seeker account. Configured to allow registered users into the application

### Recruiters Dashboard
Register and create a Recruiter profile.
Post new job openings with details like role, location, and salary.
View applications and edit job postings.

### Job Seekers Dashboard
Register and create a Job Seeker profile.
Browse and search for jobs by category, location, or keywords.
Apply for jobs and track application status.
Save favorite job postings.

## Tech Stack
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