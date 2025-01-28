-- Drop the admin user if it exists
DO $$
BEGIN
    IF EXISTS (SELECT 1 FROM pg_roles WHERE rolname = 'admin') THEN
DROP ROLE admin;
END IF;
END $$;

-- Create the admin user with a specified password
CREATE ROLE admin WITH LOGIN PASSWORD 'password';
-- Grant all privileges to the admin user if needed
ALTER ROLE admin WITH CREATEDB CREATEROLE SUPERUSER;