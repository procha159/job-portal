DROP DATABASE  IF EXISTS jobportal;
CREATE DATABASE jobportal;
\c jobportal;

CREATE TABLE users_type (
  user_type_id SERIAL PRIMARY KEY,
  user_type_name varchar(255) DEFAULT NULL
);

INSERT INTO users_type VALUES (1,'Recruiter'),(2,'Job Seeker');

CREATE TABLE users (
  user_id SERIAL PRIMARY KEY,
  email varchar(255) DEFAULT NULL UNIQUE,
  is_active BOOLEAN DEFAULT NULL,
  password varchar(255) DEFAULT NULL,
  registration_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  user_type_id int DEFAULT NULL,
  CONSTRAINT fk_user_type FOREIGN KEY (user_type_id)
  REFERENCES users_type (user_type_id)
  ON DELETE CASCADE
);

CREATE TABLE job_company (
  id SERIAL PRIMARY KEY,
  logo varchar(255) DEFAULT NULL,
  name varchar(255) DEFAULT NULL
);

CREATE TABLE job_location (
  id SERIAL PRIMARY KEY,
  city varchar(255) DEFAULT NULL,
  country varchar(255) DEFAULT NULL,
  state varchar(255) DEFAULT NULL
);

CREATE TABLE job_seeker_profile (
  user_account_id SERIAL PRIMARY KEY,
  city varchar(255) DEFAULT NULL,
  country varchar(255) DEFAULT NULL,
  employment_type varchar(255) DEFAULT NULL,
  first_name varchar(255) DEFAULT NULL,
  last_name varchar(255) DEFAULT NULL,
  profile_photo varchar(255) DEFAULT NULL,
  resume varchar(255) DEFAULT NULL,
  state varchar(255) DEFAULT NULL,
  work_authorization varchar(255) DEFAULT NULL,
  CONSTRAINT fk_user_account FOREIGN KEY (user_account_id)
  REFERENCES users (user_id)
  ON DELETE CASCADE
);

CREATE TABLE recruiter_profile (
  user_account_id SERIAL PRIMARY KEY,
  city varchar(255) DEFAULT NULL,
  company varchar(255) DEFAULT NULL,
  country varchar(255) DEFAULT NULL,
  first_name varchar(255) DEFAULT NULL,
  last_name varchar(255) DEFAULT NULL,
  profile_photo varchar(64) DEFAULT NULL,
  state varchar(255) DEFAULT NULL,
  CONSTRAINT fk_user_account FOREIGN KEY (user_account_id)
  REFERENCES users (user_id)
  ON DELETE CASCADE
);

CREATE TABLE job_post_activity (
  job_post_id SERIAL PRIMARY KEY,
  description_of_job varchar(10000) DEFAULT NULL,
  job_title varchar(255) DEFAULT NULL,
  job_type varchar(255) DEFAULT NULL,
  posted_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  remote varchar(255) DEFAULT NULL,
  salary varchar(255) DEFAULT NULL,
  job_company_id int DEFAULT NULL,
  job_location_id int DEFAULT NULL,
  posted_by_id int DEFAULT NULL,
  CONSTRAINT fk_job_location_id FOREIGN KEY (job_location_id)
  REFERENCES job_location (id),
  CONSTRAINT fk_posted_id FOREIGN KEY (posted_by_id)
  REFERENCES users (user_id),
  CONSTRAINT fk_job_company_id FOREIGN KEY (job_company_id)
  REFERENCES job_company (id)
);

CREATE TABLE job_seeker_save (
  id SERIAL PRIMARY KEY,
  job int DEFAULT NULL,
  user_id int DEFAULT NULL,
  UNIQUE (user_id,job),
  CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES job_seeker_profile (user_account_id),
  CONSTRAINT fk_job FOREIGN KEY (job) REFERENCES job_post_activity (job_post_id)
);


CREATE TABLE job_seeker_apply (
  id SERIAL PRIMARY KEY,
  apply_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  cover_letter varchar(255) DEFAULT NULL,
  job int DEFAULT NULL,
  user_id int DEFAULT NULL,
  UNIQUE (user_id,job),
  CONSTRAINT fk_job_post FOREIGN KEY (job) REFERENCES job_post_activity (job_post_id),
  CONSTRAINT fk_job_seeker FOREIGN KEY (user_id) REFERENCES job_seeker_profile (user_account_id)
);

CREATE TABLE skills (
  id SERIAL PRIMARY KEY ,
  experience_level varchar(255) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  years_of_experience varchar(255) DEFAULT NULL,
  job_seeker_profile int DEFAULT NULL,
  CONSTRAINT fk_job_seeker FOREIGN KEY (job_seeker_profile) REFERENCES job_seeker_profile (user_account_id)
);