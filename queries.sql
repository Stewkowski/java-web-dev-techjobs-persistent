## Part 1: Test it with SQL

SELECT *
FROM job;

## Part 2: Test it with SQL

SELECT name
FROM employer
WHERE (attending = "St. Louis City")

## Part 3: Test it with SQL

DROP TABLE job

## Part 4: Test it with SQL

SELECT name, description
FROM skill ORDER BY name
INNER JOIN job_skills ON skill.id = job_skills.skills_id
INNER JOIN job ON job_skills.job_id = job.id
WHERE job_id IS NOT NULL
