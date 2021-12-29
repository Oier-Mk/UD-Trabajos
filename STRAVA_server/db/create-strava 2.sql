/* DELETE 'strava_user' database*/
DROP SCHEMA IF EXISTS stravaDb;
/* DELETE USER 'strava_user' AT LOCAL SERVER*/
DROP USER IF EXISTS 'strava_user'@'%';

/* CREATE 'stravadb' DATABASE */
CREATE SCHEMA stravaDb;
/* CREATE THE USER 'strava_user' AT LOCAL SERVER WITH PASSWORD 'strava_user' */
CREATE USER 'strava_user'@'%' IDENTIFIED BY 'password';
/* GRANT FULL ACCESS TO THE DATABASE 'stravadb' FOR THE USER 'strava_user' AT LOCAL SERVER*/
GRANT ALL ON stravaDb.* TO 'strava_user'@'%';
