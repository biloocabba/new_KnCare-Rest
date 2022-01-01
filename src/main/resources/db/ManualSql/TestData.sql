INSERT INTO country (id, name, num_code, phone_code) VALUES (6, 'Italy', 1, 2);

-- INSERT INTO employee (id, international_name, nationality_id) VALUES (7, 'Test Test', 6);

-- INSERT INTO member (id, onboard_date, offboard_date,employee_id) VALUES (8,localtimestamp, localtimestamp, 7);
INSERT INTO employee (international_name, email, id) VALUES ('Member 1', 'Email1@test.com', 1);
INSERT INTO employee (international_name, email, id) VALUES ('Member 2', 'Email2@test.com', 2);
INSERT INTO employee (international_name, email, id) VALUES ('Member 3', 'Email3@test.com', 3);
INSERT INTO employee (international_name, email, id) VALUES ('Member 4', 'Email4@test.com', 4);
INSERT INTO employee (international_name, email, id) VALUES ('Member 5', 'Email5@test.com', 5);
INSERT INTO employee (international_name, email, id) VALUES ('Member 6', 'Email6@test.com', 6);
INSERT INTO employee (international_name, email, id) VALUES ('Member 7', 'Email7@test.com', 7);
INSERT INTO employee (international_name, email, id) VALUES ('Member 8', 'Email8@test.com', 8);
INSERT INTO employee (international_name, email, id) VALUES ('Member 9', 'Email9@test.com', 9);
INSERT INTO employee (international_name, email, id) VALUES ('Member 10', 'Email10@test.com', 10);

INSERT INTO member (offboard_date, onboard_date, employee_id, id) VALUES (localtimestamp, '2021-08-03 14:38:33.000000', 1, 1);
INSERT INTO member (offboard_date, onboard_date, employee_id, id) VALUES (localtimestamp, '2021-08-04 14:38:33.000000', 2, 2);
INSERT INTO member (offboard_date, onboard_date, employee_id, id) VALUES (localtimestamp, '2021-08-05 14:38:33.000000', 3, 3);
INSERT INTO member (offboard_date, onboard_date, employee_id, id) VALUES (localtimestamp, '2021-08-06 14:38:33.000000', 4, 4);
INSERT INTO member (offboard_date, onboard_date, employee_id, id) VALUES (localtimestamp, '2021-08-07 14:38:33.000000', 5, 5);
INSERT INTO member (offboard_date, onboard_date, employee_id, id) VALUES (localtimestamp, '2021-08-08 14:38:33.000000', 6, 6);
INSERT INTO member (offboard_date, onboard_date, employee_id, id) VALUES (localtimestamp, '2021-08-09 14:38:33.000000', 7, 7);
INSERT INTO member (offboard_date, onboard_date, employee_id, id) VALUES (localtimestamp, '2021-08-10 14:38:33.000000', 8, 8);
INSERT INTO member (offboard_date, onboard_date, employee_id, id) VALUES (localtimestamp, '2021-08-11 14:38:33.000000', 9, 9);
INSERT INTO member (offboard_date, onboard_date, employee_id, id) VALUES (localtimestamp, '2021-08-12 14:38:33.000000', 10, 10);

INSERT INTO email (content, subject, created_by_id, created_at, updated_at) VALUES ('Lorem ipsum dolor sit amet, consectetur adipiscing.', 'Pellentesque at', 1, '2021-08-03 14:38:33.000000', '2021-08-03 14:38:33.000000');
INSERT INTO email (content, subject, created_by_id, created_at, updated_at) VALUES ('Nunc quis ultrices elit. Nullam eros turpis.', 'Nunc eu', 1, '2021-09-03 14:38:33.000000', '2021-08-04 14:38:33.000000');
INSERT INTO email (content, subject, created_by_id, created_at, updated_at) VALUES ('Quisque orci sem, cursus sed laoreet eget.', 'Sed rutrum', 1, '2021-10-03 14:38:33.000000', '2021-08-05 14:38:33.000000');
INSERT INTO email (content, subject, created_by_id, created_at, updated_at) VALUES ('Etiam in dictum nulla. Nullam nisl lectus.', 'Aliquam erat', 2, '2021-11-03 14:38:33.000000', '2021-08-06 14:38:33.000000');
INSERT INTO email (content, subject, created_by_id, created_at, updated_at) VALUES ('Extum talis eleates est.', 'Pellentesque habitant', 2, '2021-12-03 14:38:33.000000', '2021-08-07 14:38:33.000000');

INSERT INTO "group" (created_at, updated_at, description, name, created_by_id) VALUES ('2021-08-17T13:35:03.2031009', '2021-08-17T13:35:12.3689555', 'description', 'group 1', '1');
INSERT INTO "group" (created_at, updated_at, description, name, created_by_id) VALUES ('2021-08-17T13:35:03.2031009', '2021-08-17T13:35:12.3689555', 'description', 'group 2', '1');

INSERT INTO country (id, name, num_code, phone_code) VALUES (4, 'France', 1, 2);

INSERT INTO member_group (created_at, updated_at, created_by_id, group_id, member_id) VALUES ('2021-08-17T13:36:30.3629246', '2021-08-17T13:36:33.8894172', 1 , 1, 2);
INSERT INTO member_group (created_at, updated_at, created_by_id, group_id, member_id) VALUES ('2021-08-17T13:36:30.3629246', '2021-08-17T13:36:33.8894172', 1 , 1, 3);

INSERT INTO member_group (created_at, updated_at, created_by_id, group_id, member_id) VALUES ('2021-08-17T13:36:30.3629246', '2021-08-17T13:36:33.8894172', 1 , 2, 4);
INSERT INTO member_group (created_at, updated_at, created_by_id, group_id, member_id) VALUES ('2021-08-17T13:36:30.3629246', '2021-08-17T13:36:33.8894172', 1 , 2, 5);

INSERT INTO member_group (created_at, updated_at, created_by_id, group_id, member_id) VALUES ('2021-08-17T13:36:30.3629246', '2021-08-17T13:36:33.8894172', 1 , 2, 2);
INSERT INTO member_group (created_at, updated_at, created_by_id, group_id, member_id) VALUES ('2021-08-17T13:36:30.3629246', '2021-08-17T13:36:33.8894172', 1 , 1, 5);

INSERT INTO email_recipient (email_id, recipient_id) VALUES (1, 2);
INSERT INTO email_recipient (email_id, recipient_id) VALUES (1, 3);