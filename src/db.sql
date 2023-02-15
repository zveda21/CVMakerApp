SET search_path TO cv_maker;

CREATE TABLE skill (
                       id SERIAL PRIMARY KEY,
                       name varchar(40)
);


CREATE TABLE work_experience(
                                id SERIAL PRIMARY KEY,
                                job_title varchar(30),
                                company text,
                                start_date DATE,
                                end_date DATE,
                                description text
);

ALTER TABLE work_experience
    ADD COLUMN currentDate BOOLEAN not null default false;

CREATE TABLE users(
                      id SERIAL PRIMARY KEY,
                      username varchar(30) UNIQUE NOT NULL ,
                      password text NOT NULL ,
                      firstname varchar(30),
                      lastname varchar(30),
                      email text,
                      address text,
                      work_email text,
                      position text
);
CREATE TABLE degree (
                        e_degree_id SERIAL PRIMARY KEY ,
                        degree_type varchar(30)
);



ALTER TABLE work_experience
    ADD COLUMN user_id int,
    ADD CONSTRAINT w_user_fk FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE;

CREATE TABLE  language (
                           id SERIAL PRIMARY KEY,
                           name varchar(30)
);

ALTER TABLE users
    ADD COLUMN phone text;

ALTER TABLE language
    ADD COLUMN user_id int,
    ADD CONSTRAINT l_user_fk FOREIGN KEY (user_id) REFERENCES users(id);

ALTER TABLE skill
    ADD COLUMN user_id int,
    ADD CONSTRAINT s_user_fk FOREIGN KEY (user_id) REFERENCES users(id);

CREATE TABLE education(
                          id SERIAL PRIMARY KEY,
                          start_date DATE ,
                          description text,
                          end_date DATE,
                          university varchar(50)

);
ALTER TABLE education
    ADD COLUMN user_id int,
    ADD CONSTRAINT e_user_fk FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE ;

ALTER TABLE education
    ADD COLUMN degree_id int,
    ADD CONSTRAINT e_degree_fk FOREIGN KEY (degree_id) REFERENCES degree (e_degree_id) ON DELETE CASCADE;


INSERT INTO degree(degree_type)values ('Bachelor degree');
INSERT INTO degree(degree_type)values ('Master degree');
INSERT INTO degree(degree_type)values ('PHD');
SELECT * from degree;
SELECT * from users;

ALTER TABLE users
    RENAME COLUMN work_email TO work_mail;