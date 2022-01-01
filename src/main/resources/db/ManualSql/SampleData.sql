INSERT INTO public.business_unit (id, created_at, description, name, updated_at)
VALUES (1, '2021-08-04 12:16:27.000000', 'description of business unit', 'IT', '2021-08-04 12:16:59.000000');


INSERT INTO public.country (id, iso2, iso3, name, num_code, phone_code)
VALUES (1, 'EST', 'EST', 'ESTONIA', 111, 372);


INSERT INTO public.cost_center (id, code, name)
VALUES (1, 'CC1', 'COST_CENTER1');


INSERT INTO public.management_group (id, created_at, name, updated_at)
VALUES (1, '2021-08-04 12:26:28.000000', 'IT management', '2021-08-04 12:26:31.000000');


INSERT INTO public.office (id, end_date, start_date, country)
VALUES (1, '2021-08-04', '2021-08-04', 1);

INSERT INTO public.working_position (id, created_at, description, name)
VALUES (1, '2021-08-04 12:30:57.000000', 'Developer job description', 'Developer');


INSERT INTO public.employee (id, birth_date, company_mobile_phone, company_phone, created_at, date_of_leave, email,
                             end_date, first_name, gender, international_name, last_name, pdm_id, start_date, title,
                             update_at, business_unit_id, cost_center_id, dotted_line_manager_id, hr_reference_id,
                             management_group_id, nationality_id, office_id, solid_line_manager_id, working_position_id)
VALUES (1, '1999-08-01', '555112233', '123456', '2021-08-04 12:20:31.000000', null, 'employee01@gmail.com', null,
        'Billy', 1, 'Billy Thornton', 'Thornton', 1001, '2020-08-04', 'Developer', '2021-08-04 12:25:38.000000', 1, 1,
        2, 1, 1, 1, 1, 1, 1);




##EMployee 2 hor HR ref
INSERT INTO public.employee (id, birth_date, company_mobile_phone, company_phone, created_at, date_of_leave, email,
                             end_date, first_name, gender, international_name, last_name, pdm_id, start_date, title,
                             update_at, business_unit_id, cost_center_id, dotted_line_manager_id, hr_reference_id,
                             management_group_id, nationality_id, office_id, solid_line_manager_id, working_position_id)
VALUES (2, '1999-08-01', '555112233', '123456', '2021-08-04 12:20:31.000000', null, 'employee01@gmail.com', null,
        'Kersti', 1, 'Kersti Soomre', 'Soomre', 1002, '2020-08-04', 'HR', '2021-08-04 12:25:38.000000', 1, 1, 1, 1, 1,
        1, 1, 1, 1);

INSERT INTO public.role (id, name)
VALUES (1, 'Care Advocate');

INSERT INTO public.member (id, offboard_date, onboard_date, employee_id, role_id)
VALUES (1, '2021-08-04 12:33:32.000000', '2022-08-04 12:33:34.000000', 1, 1);