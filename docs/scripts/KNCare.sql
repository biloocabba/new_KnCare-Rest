--
-- PostgreSQL database dump
--

-- Dumped from database version 13.2
-- Dumped by pg_dump version 13.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: business_unit; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.business_unit (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    description character varying(255),
    name character varying(255),
    updated_at timestamp without time zone
);


ALTER TABLE public.business_unit OWNER TO postgres;

--
-- Name: cost_center; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cost_center (
    id bigint NOT NULL,
    code character varying(255),
    name character varying(255)
);


ALTER TABLE public.cost_center OWNER TO postgres;

--
-- Name: country; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.country (
    id bigint NOT NULL,
    iso2 character varying(255),
    iso3 character varying(255),
    name character varying(255),
    num_code integer,
    phone_code integer
);


ALTER TABLE public.country OWNER TO postgres;

--
-- Name: email; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.email (
    id bigint NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    content text,
    subject character varying(255),
    created_by_id bigint
);


ALTER TABLE public.email OWNER TO postgres;

--
-- Name: email_recipient; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.email_recipient (
    email_id bigint NOT NULL,
    recipient_id bigint NOT NULL
);


ALTER TABLE public.email_recipient OWNER TO postgres;

--
-- Name: email_template; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.email_template (
    id bigint NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    content oid,
    description character varying(255),
    title character varying(255),
    created_by_id bigint
);


ALTER TABLE public.email_template OWNER TO postgres;

--
-- Name: employee; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.employee (
    id bigint NOT NULL,
    birth_date date,
    company_mobile_phone character varying(255),
    company_phone character varying(255),
    created_at timestamp without time zone,
    date_of_leave date,
    email character varying(255),
    end_date date,
    first_name character varying(255),
    gender integer,
    international_name character varying(255),
    last_name character varying(255),
    pdm_id bigint,
    start_date date,
    title character varying(255),
    update_at timestamp without time zone,
    business_unit_id bigint,
    cost_center_id bigint,
    dotted_line_manager_id bigint,
    hr_reference_id bigint,
    management_group_id bigint,
    nationality_id bigint,
    office_id bigint,
    solid_line_manager_id bigint,
    working_position_id bigint
);


ALTER TABLE public.employee OWNER TO postgres;

--
-- Name: employee_business_unit_history; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.employee_business_unit_history (
    id bigint NOT NULL,
    end_date date,
    start_date date,
    employee_id bigint,
    business_unit_id bigint
);


ALTER TABLE public.employee_business_unit_history OWNER TO postgres;

--
-- Name: employee_cost_center_history; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.employee_cost_center_history (
    id bigint NOT NULL,
    end_date date,
    start_date date,
    employee_id bigint,
    cost_center_id bigint
);


ALTER TABLE public.employee_cost_center_history OWNER TO postgres;

--
-- Name: employee_employee_history; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.employee_employee_history (
    id bigint NOT NULL,
    end_date date,
    start_date date,
    relation_type integer,
    employee_id bigint,
    ref_employee_id bigint
);


ALTER TABLE public.employee_employee_history OWNER TO postgres;

--
-- Name: employee_working_group_history; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.employee_working_group_history (
    id bigint NOT NULL,
    end_date date,
    start_date date,
    employee_id bigint,
    management_group_id bigint
);


ALTER TABLE public.employee_working_group_history OWNER TO postgres;

--
-- Name: employee_working_position_history; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.employee_working_position_history (
    id bigint NOT NULL,
    end_date date,
    start_date date,
    employee_id bigint,
    working_position_id bigint
);


ALTER TABLE public.employee_working_position_history OWNER TO postgres;

--
-- Name: group; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."group" (
    id bigint NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    description character varying(255),
    name character varying(255),
    created_by_id bigint
);


ALTER TABLE public."group" OWNER TO postgres;

--
-- Name: group_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.group_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.group_id_seq OWNER TO postgres;

--
-- Name: group_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.group_id_seq OWNED BY public."group".id;


--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- Name: learning_roadmap; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.learning_roadmap (
    id bigint NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    title character varying(255),
    created_by_id bigint
);


ALTER TABLE public.learning_roadmap OWNER TO postgres;

--
-- Name: learning_roadmap_trainings; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.learning_roadmap_trainings (
    roadmap_id bigint NOT NULL,
    training_id bigint NOT NULL,
    step integer NOT NULL
);


ALTER TABLE public.learning_roadmap_trainings OWNER TO postgres;

--
-- Name: learning_subscription; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.learning_subscription (
    id bigint NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    created_by_id bigint,
    learning_road_map_id bigint,
    subscriber_id bigint
);


ALTER TABLE public.learning_subscription OWNER TO postgres;

--
-- Name: management_group; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.management_group (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    name character varying(255),
    updated_at timestamp without time zone
);


ALTER TABLE public.management_group OWNER TO postgres;

--
-- Name: member; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.member (
    id bigint NOT NULL,
    offboard_date timestamp without time zone NOT NULL,
    onboard_date timestamp without time zone NOT NULL,
    employee_id bigint NOT NULL,
    role_id bigint
);


ALTER TABLE public.member OWNER TO postgres;

--
-- Name: member_group; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.member_group (
    id bigint NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    created_by_id bigint,
    group_id bigint,
    member_id bigint
);


ALTER TABLE public.member_group OWNER TO postgres;

--
-- Name: member_group_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.member_group_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.member_group_id_seq OWNER TO postgres;

--
-- Name: member_group_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.member_group_id_seq OWNED BY public.member_group.id;


--
-- Name: member_roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.member_roles (
    id bigint NOT NULL,
    end_date date,
    start_date date,
    member_id bigint,
    role_id bigint
);


ALTER TABLE public.member_roles OWNER TO postgres;

--
-- Name: member_roles_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.member_roles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.member_roles_id_seq OWNER TO postgres;

--
-- Name: member_roles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.member_roles_id_seq OWNED BY public.member_roles.id;


--
-- Name: notification; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.notification (
    id bigint NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    description character varying(255),
    created_by_id bigint
);


ALTER TABLE public.notification OWNER TO postgres;

--
-- Name: notification_recipient; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.notification_recipient (
    notification_id bigint NOT NULL,
    recipient_id bigint NOT NULL
);


ALTER TABLE public.notification_recipient OWNER TO postgres;

--
-- Name: office; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.office (
    id bigint NOT NULL,
    end_date date,
    start_date date,
    country bigint
);


ALTER TABLE public.office OWNER TO postgres;

--
-- Name: practice; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.practice (
    id bigint NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    content oid,
    description character varying(255),
    title character varying(255),
    created_by_id bigint
);


ALTER TABLE public.practice OWNER TO postgres;

--
-- Name: practice_tags; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.practice_tags (
    practice_id bigint NOT NULL,
    tag_id bigint NOT NULL,
    rate_id bigint NOT NULL
);


ALTER TABLE public.practice_tags OWNER TO postgres;

--
-- Name: rate; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.rate (
    id bigint NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    comment character varying(255),
    rate integer,
    created_by_id bigint
);


ALTER TABLE public.rate OWNER TO postgres;

--
-- Name: role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role (
    id bigint NOT NULL,
    name character varying(255)
);


ALTER TABLE public.role OWNER TO postgres;

--
-- Name: role_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.role_id_seq OWNER TO postgres;

--
-- Name: role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.role_id_seq OWNED BY public.role.id;


--
-- Name: scheduled_training; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.scheduled_training (
    id bigint NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    completed_on timestamp without time zone,
    deadline timestamp without time zone,
    created_by_id bigint,
    member_id bigint,
    training_id bigint
);


ALTER TABLE public.scheduled_training OWNER TO postgres;

--
-- Name: tag; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tag (
    id bigint NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    name character varying(255),
    created_by_id bigint
);


ALTER TABLE public.tag OWNER TO postgres;

--
-- Name: training; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.training (
    id bigint NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    content oid,
    description character varying(255),
    title character varying(255),
    created_by_id bigint
);


ALTER TABLE public.training OWNER TO postgres;

--
-- Name: training_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.training_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.training_id_seq OWNER TO postgres;

--
-- Name: training_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.training_id_seq OWNED BY public.training.id;


--
-- Name: working_position; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.working_position (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    description character varying(255),
    name character varying(255)
);


ALTER TABLE public.working_position OWNER TO postgres;

--
-- Name: group id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."group" ALTER COLUMN id SET DEFAULT nextval('public.group_id_seq'::regclass);


--
-- Name: member_group id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.member_group ALTER COLUMN id SET DEFAULT nextval('public.member_group_id_seq'::regclass);


--
-- Name: member_roles id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.member_roles ALTER COLUMN id SET DEFAULT nextval('public.member_roles_id_seq'::regclass);


--
-- Name: role id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role ALTER COLUMN id SET DEFAULT nextval('public.role_id_seq'::regclass);


--
-- Name: training id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.training ALTER COLUMN id SET DEFAULT nextval('public.training_id_seq'::regclass);


--
-- Data for Name: business_unit; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.business_unit (id, created_at, description, name, updated_at) FROM stdin;
\.


--
-- Data for Name: cost_center; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cost_center (id, code, name) FROM stdin;
\.


--
-- Data for Name: country; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.country (id, iso2, iso3, name, num_code, phone_code) FROM stdin;
\.


--
-- Data for Name: email; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.email (id, created_at, updated_at, content, subject, created_by_id) FROM stdin;
\.


--
-- Data for Name: email_recipient; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.email_recipient (email_id, recipient_id) FROM stdin;
\.


--
-- Data for Name: email_template; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.email_template (id, created_at, updated_at, content, description, title, created_by_id) FROM stdin;
\.


--
-- Data for Name: employee; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.employee (id, birth_date, company_mobile_phone, company_phone, created_at, date_of_leave, email, end_date, first_name, gender, international_name, last_name, pdm_id, start_date, title, update_at, business_unit_id, cost_center_id, dotted_line_manager_id, hr_reference_id, management_group_id, nationality_id, office_id, solid_line_manager_id, working_position_id) FROM stdin;
\.


--
-- Data for Name: employee_business_unit_history; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.employee_business_unit_history (id, end_date, start_date, employee_id, business_unit_id) FROM stdin;
\.


--
-- Data for Name: employee_cost_center_history; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.employee_cost_center_history (id, end_date, start_date, employee_id, cost_center_id) FROM stdin;
\.


--
-- Data for Name: employee_employee_history; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.employee_employee_history (id, end_date, start_date, relation_type, employee_id, ref_employee_id) FROM stdin;
\.


--
-- Data for Name: employee_working_group_history; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.employee_working_group_history (id, end_date, start_date, employee_id, management_group_id) FROM stdin;
\.


--
-- Data for Name: employee_working_position_history; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.employee_working_position_history (id, end_date, start_date, employee_id, working_position_id) FROM stdin;
\.


--
-- Data for Name: group; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."group" (id, created_at, updated_at, description, name, created_by_id) FROM stdin;
\.


--
-- Data for Name: learning_roadmap; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.learning_roadmap (id, created_at, updated_at, title, created_by_id) FROM stdin;
\.


--
-- Data for Name: learning_roadmap_trainings; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.learning_roadmap_trainings (roadmap_id, training_id, step) FROM stdin;
\.


--
-- Data for Name: learning_subscription; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.learning_subscription (id, created_at, updated_at, created_by_id, learning_road_map_id, subscriber_id) FROM stdin;
\.


--
-- Data for Name: management_group; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.management_group (id, created_at, name, updated_at) FROM stdin;
\.


--
-- Data for Name: member; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.member (id, offboard_date, onboard_date, employee_id, role_id) FROM stdin;
\.


--
-- Data for Name: member_group; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.member_group (id, created_at, updated_at, created_by_id, group_id, member_id) FROM stdin;
\.


--
-- Data for Name: member_roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.member_roles (id, end_date, start_date, member_id, role_id) FROM stdin;
\.


--
-- Data for Name: notification; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.notification (id, created_at, updated_at, description, created_by_id) FROM stdin;
\.


--
-- Data for Name: notification_recipient; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.notification_recipient (notification_id, recipient_id) FROM stdin;
\.


--
-- Data for Name: office; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.office (id, end_date, start_date, country) FROM stdin;
\.


--
-- Data for Name: practice; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.practice (id, created_at, updated_at, content, description, title, created_by_id) FROM stdin;
\.


--
-- Data for Name: practice_tags; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.practice_tags (practice_id, tag_id, rate_id) FROM stdin;
\.


--
-- Data for Name: rate; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.rate (id, created_at, updated_at, comment, rate, created_by_id) FROM stdin;
\.


--
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.role (id, name) FROM stdin;
\.


--
-- Data for Name: scheduled_training; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.scheduled_training (id, created_at, updated_at, completed_on, deadline, created_by_id, member_id, training_id) FROM stdin;
\.


--
-- Data for Name: tag; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tag (id, created_at, updated_at, name, created_by_id) FROM stdin;
\.


--
-- Data for Name: training; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.training (id, created_at, updated_at, content, description, title, created_by_id) FROM stdin;
\.


--
-- Data for Name: working_position; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.working_position (id, created_at, description, name) FROM stdin;
\.


--
-- Name: group_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.group_id_seq', 1, false);


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 1, false);


--
-- Name: member_group_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.member_group_id_seq', 1, false);


--
-- Name: member_roles_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.member_roles_id_seq', 1, false);


--
-- Name: role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.role_id_seq', 1, false);


--
-- Name: training_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.training_id_seq', 1, false);


--
-- Name: business_unit business_unit_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.business_unit
    ADD CONSTRAINT business_unit_pkey PRIMARY KEY (id);


--
-- Name: cost_center cost_center_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cost_center
    ADD CONSTRAINT cost_center_pkey PRIMARY KEY (id);


--
-- Name: country country_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.country
    ADD CONSTRAINT country_pkey PRIMARY KEY (id);


--
-- Name: email email_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.email
    ADD CONSTRAINT email_pkey PRIMARY KEY (id);


--
-- Name: email_template email_template_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.email_template
    ADD CONSTRAINT email_template_pkey PRIMARY KEY (id);


--
-- Name: employee_business_unit_history employee_business_unit_history_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee_business_unit_history
    ADD CONSTRAINT employee_business_unit_history_pkey PRIMARY KEY (id);


--
-- Name: employee_cost_center_history employee_cost_center_history_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee_cost_center_history
    ADD CONSTRAINT employee_cost_center_history_pkey PRIMARY KEY (id);


--
-- Name: employee_employee_history employee_employee_history_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee_employee_history
    ADD CONSTRAINT employee_employee_history_pkey PRIMARY KEY (id);


--
-- Name: employee employee_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_pkey PRIMARY KEY (id);


--
-- Name: employee_working_group_history employee_working_group_history_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee_working_group_history
    ADD CONSTRAINT employee_working_group_history_pkey PRIMARY KEY (id);


--
-- Name: employee_working_position_history employee_working_position_history_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee_working_position_history
    ADD CONSTRAINT employee_working_position_history_pkey PRIMARY KEY (id);


--
-- Name: group group_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."group"
    ADD CONSTRAINT group_pkey PRIMARY KEY (id);


--
-- Name: learning_roadmap learning_roadmap_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.learning_roadmap
    ADD CONSTRAINT learning_roadmap_pkey PRIMARY KEY (id);


--
-- Name: learning_roadmap_trainings learning_roadmap_trainings_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.learning_roadmap_trainings
    ADD CONSTRAINT learning_roadmap_trainings_pkey PRIMARY KEY (roadmap_id, step);


--
-- Name: learning_subscription learning_subscription_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.learning_subscription
    ADD CONSTRAINT learning_subscription_pkey PRIMARY KEY (id);


--
-- Name: management_group management_group_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.management_group
    ADD CONSTRAINT management_group_pkey PRIMARY KEY (id);


--
-- Name: member_group member_group_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.member_group
    ADD CONSTRAINT member_group_pkey PRIMARY KEY (id);


--
-- Name: member member_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.member
    ADD CONSTRAINT member_pkey PRIMARY KEY (id);


--
-- Name: member_roles member_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.member_roles
    ADD CONSTRAINT member_roles_pkey PRIMARY KEY (id);


--
-- Name: notification notification_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notification
    ADD CONSTRAINT notification_pkey PRIMARY KEY (id);


--
-- Name: office office_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.office
    ADD CONSTRAINT office_pkey PRIMARY KEY (id);


--
-- Name: practice practice_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.practice
    ADD CONSTRAINT practice_pkey PRIMARY KEY (id);


--
-- Name: practice_tags practice_tags_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.practice_tags
    ADD CONSTRAINT practice_tags_pkey PRIMARY KEY (practice_id, tag_id);


--
-- Name: rate rate_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rate
    ADD CONSTRAINT rate_pkey PRIMARY KEY (id);


--
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- Name: scheduled_training scheduled_training_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.scheduled_training
    ADD CONSTRAINT scheduled_training_pkey PRIMARY KEY (id);


--
-- Name: tag tag_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tag
    ADD CONSTRAINT tag_pkey PRIMARY KEY (id);


--
-- Name: training training_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.training
    ADD CONSTRAINT training_pkey PRIMARY KEY (id);


--
-- Name: member uk_4rebv7u7h123c0kvaq3jv89ym; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.member
    ADD CONSTRAINT uk_4rebv7u7h123c0kvaq3jv89ym UNIQUE (employee_id);


--
-- Name: learning_roadmap_trainings uk_rdgp4kx0hs4xyvd2yq9qa2gr5; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.learning_roadmap_trainings
    ADD CONSTRAINT uk_rdgp4kx0hs4xyvd2yq9qa2gr5 UNIQUE (training_id);


--
-- Name: working_position working_position_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.working_position
    ADD CONSTRAINT working_position_pkey PRIMARY KEY (id);


--
-- Name: employee_working_group_history fk10bbwkoqgtvskce4rjhp51fc5; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee_working_group_history
    ADD CONSTRAINT fk10bbwkoqgtvskce4rjhp51fc5 FOREIGN KEY (management_group_id) REFERENCES public.management_group(id);


--
-- Name: employee fk1d2fq2opij674uxm58ju1u9ov; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT fk1d2fq2opij674uxm58ju1u9ov FOREIGN KEY (management_group_id) REFERENCES public.management_group(id);


--
-- Name: member_group fk1d55qbjdu2lx2sl19c1nf3dn6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.member_group
    ADD CONSTRAINT fk1d55qbjdu2lx2sl19c1nf3dn6 FOREIGN KEY (created_by_id) REFERENCES public.member(id);


--
-- Name: practice_tags fk2jsd5r588wyx6ayh12nb0tu5t; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.practice_tags
    ADD CONSTRAINT fk2jsd5r588wyx6ayh12nb0tu5t FOREIGN KEY (practice_id) REFERENCES public.practice(id);


--
-- Name: group fk2muunx7yagr4ln1s2yodtv2ly; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."group"
    ADD CONSTRAINT fk2muunx7yagr4ln1s2yodtv2ly FOREIGN KEY (created_by_id) REFERENCES public.member(id);


--
-- Name: email_template fk3avk0i6esa9ut2x85f78jm2br; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.email_template
    ADD CONSTRAINT fk3avk0i6esa9ut2x85f78jm2br FOREIGN KEY (created_by_id) REFERENCES public.member(id);


--
-- Name: employee fk3kmmuschwh9bdhclx3qqs2bnn; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT fk3kmmuschwh9bdhclx3qqs2bnn FOREIGN KEY (nationality_id) REFERENCES public.country(id);


--
-- Name: member_group fk4fgg228gsggmolmxtwmdb7bry; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.member_group
    ADD CONSTRAINT fk4fgg228gsggmolmxtwmdb7bry FOREIGN KEY (member_id) REFERENCES public.member(id);


--
-- Name: email_recipient fk6lyn0kiinqvuwv2blru3i3r8h; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.email_recipient
    ADD CONSTRAINT fk6lyn0kiinqvuwv2blru3i3r8h FOREIGN KEY (recipient_id) REFERENCES public.member(id);


--
-- Name: practice_tags fk7krgy6gdi3lcg8jcnew0ovcms; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.practice_tags
    ADD CONSTRAINT fk7krgy6gdi3lcg8jcnew0ovcms FOREIGN KEY (rate_id) REFERENCES public.rate(id);


--
-- Name: member fk7u6tjhx5m1fx98ks4w5tuipq1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.member
    ADD CONSTRAINT fk7u6tjhx5m1fx98ks4w5tuipq1 FOREIGN KEY (employee_id) REFERENCES public.employee(id);


--
-- Name: notification fk8ahyrgl012hdw1b37k5jiyo6i; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notification
    ADD CONSTRAINT fk8ahyrgl012hdw1b37k5jiyo6i FOREIGN KEY (created_by_id) REFERENCES public.member(id);


--
-- Name: member_group fk8h083ll5syxs8b66cisdxvn5r; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.member_group
    ADD CONSTRAINT fk8h083ll5syxs8b66cisdxvn5r FOREIGN KEY (group_id) REFERENCES public."group"(id);


--
-- Name: learning_subscription fk8i8livm2vyysqy7q3u1bsd923; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.learning_subscription
    ADD CONSTRAINT fk8i8livm2vyysqy7q3u1bsd923 FOREIGN KEY (learning_road_map_id) REFERENCES public.learning_roadmap(id);


--
-- Name: employee_working_position_history fk8vm8q94oy5tuy6pb8fw8jv3nt; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee_working_position_history
    ADD CONSTRAINT fk8vm8q94oy5tuy6pb8fw8jv3nt FOREIGN KEY (employee_id) REFERENCES public.employee(id);


--
-- Name: learning_roadmap fk9nrt51mledgjqaoji12by6hg3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.learning_roadmap
    ADD CONSTRAINT fk9nrt51mledgjqaoji12by6hg3 FOREIGN KEY (created_by_id) REFERENCES public.member(id);


--
-- Name: employee_cost_center_history fka0ull1sbcqb8u729a3j11h964; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee_cost_center_history
    ADD CONSTRAINT fka0ull1sbcqb8u729a3j11h964 FOREIGN KEY (cost_center_id) REFERENCES public.cost_center(id);


--
-- Name: email fkact0si64w8fo540rjrfg58kfc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.email
    ADD CONSTRAINT fkact0si64w8fo540rjrfg58kfc FOREIGN KEY (created_by_id) REFERENCES public.member(id);


--
-- Name: member fkamlxknkc3asrfeo11wpmosvjx; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.member
    ADD CONSTRAINT fkamlxknkc3asrfeo11wpmosvjx FOREIGN KEY (role_id) REFERENCES public.role(id);


--
-- Name: office fkaw8d3sndhj0i35qy5hdg8r470; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.office
    ADD CONSTRAINT fkaw8d3sndhj0i35qy5hdg8r470 FOREIGN KEY (country) REFERENCES public.country(id);


--
-- Name: employee fkb8o64tbj1yboy7s34v8wqchfi; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT fkb8o64tbj1yboy7s34v8wqchfi FOREIGN KEY (cost_center_id) REFERENCES public.cost_center(id);


--
-- Name: scheduled_training fkc6f3hvhohtux1yykaes9fw4en; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.scheduled_training
    ADD CONSTRAINT fkc6f3hvhohtux1yykaes9fw4en FOREIGN KEY (member_id) REFERENCES public.member(id);


--
-- Name: learning_roadmap_trainings fkcn0ujwl8036pjlfdwvufugp38; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.learning_roadmap_trainings
    ADD CONSTRAINT fkcn0ujwl8036pjlfdwvufugp38 FOREIGN KEY (training_id) REFERENCES public.training(id);


--
-- Name: employee fkd8kd2vc83421kknmo1e9dao9y; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT fkd8kd2vc83421kknmo1e9dao9y FOREIGN KEY (hr_reference_id) REFERENCES public.employee(id);


--
-- Name: training fke3spcg2eu6y1caae2ivufwu4w; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.training
    ADD CONSTRAINT fke3spcg2eu6y1caae2ivufwu4w FOREIGN KEY (created_by_id) REFERENCES public.member(id);


--
-- Name: member_roles fke6spxgtlq85rqqqnbaxvqimdk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.member_roles
    ADD CONSTRAINT fke6spxgtlq85rqqqnbaxvqimdk FOREIGN KEY (role_id) REFERENCES public.role(id);


--
-- Name: employee_working_group_history fke6ysj1rhv48k8skec6f7x7r4c; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee_working_group_history
    ADD CONSTRAINT fke6ysj1rhv48k8skec6f7x7r4c FOREIGN KEY (employee_id) REFERENCES public.employee(id);


--
-- Name: employee_cost_center_history fkgpjo4tgt99m6ciidx8hf5b76g; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee_cost_center_history
    ADD CONSTRAINT fkgpjo4tgt99m6ciidx8hf5b76g FOREIGN KEY (employee_id) REFERENCES public.employee(id);


--
-- Name: learning_roadmap_trainings fkgsfr9rx1hcd6dnl6a4sts9ag0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.learning_roadmap_trainings
    ADD CONSTRAINT fkgsfr9rx1hcd6dnl6a4sts9ag0 FOREIGN KEY (roadmap_id) REFERENCES public.learning_roadmap(id);


--
-- Name: member_roles fkies3e6qexv81ygw2asibdhftl; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.member_roles
    ADD CONSTRAINT fkies3e6qexv81ygw2asibdhftl FOREIGN KEY (member_id) REFERENCES public.member(id);


--
-- Name: learning_subscription fkj5tpcxyhu5mb1xublwy0osme5; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.learning_subscription
    ADD CONSTRAINT fkj5tpcxyhu5mb1xublwy0osme5 FOREIGN KEY (created_by_id) REFERENCES public.member(id);


--
-- Name: learning_subscription fkj993k2dngjc2bfj41jfek11wn; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.learning_subscription
    ADD CONSTRAINT fkj993k2dngjc2bfj41jfek11wn FOREIGN KEY (subscriber_id) REFERENCES public.member(id);


--
-- Name: scheduled_training fkjfsgbvlvuix21iwf6ja71cey3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.scheduled_training
    ADD CONSTRAINT fkjfsgbvlvuix21iwf6ja71cey3 FOREIGN KEY (created_by_id) REFERENCES public.member(id);


--
-- Name: employee fkjurhambl7fs34cp8i36xpd5yp; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT fkjurhambl7fs34cp8i36xpd5yp FOREIGN KEY (office_id) REFERENCES public.office(id);


--
-- Name: employee fkkbw8ouyy968ifwvlbvqm36kcw; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT fkkbw8ouyy968ifwvlbvqm36kcw FOREIGN KEY (working_position_id) REFERENCES public.working_position(id);


--
-- Name: employee_business_unit_history fkkfe78s55y7aej7ufn23prx2vf; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee_business_unit_history
    ADD CONSTRAINT fkkfe78s55y7aej7ufn23prx2vf FOREIGN KEY (employee_id) REFERENCES public.employee(id);


--
-- Name: practice_tags fkkxhybc1fix9nbi62engjjns3s; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.practice_tags
    ADD CONSTRAINT fkkxhybc1fix9nbi62engjjns3s FOREIGN KEY (tag_id) REFERENCES public.tag(id);


--
-- Name: email_recipient fklbo3bf6r519cwacv2qyo4mu88; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.email_recipient
    ADD CONSTRAINT fklbo3bf6r519cwacv2qyo4mu88 FOREIGN KEY (email_id) REFERENCES public.email(id);


--
-- Name: employee fklqygfa666w5om41snwqigp6tt; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT fklqygfa666w5om41snwqigp6tt FOREIGN KEY (dotted_line_manager_id) REFERENCES public.employee(id);


--
-- Name: employee_business_unit_history fkm58r7ob9mxvotn5m3olfiplte; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee_business_unit_history
    ADD CONSTRAINT fkm58r7ob9mxvotn5m3olfiplte FOREIGN KEY (business_unit_id) REFERENCES public.business_unit(id);


--
-- Name: employee fkmhvom9r98bsrqrhwib6q8dya7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT fkmhvom9r98bsrqrhwib6q8dya7 FOREIGN KEY (solid_line_manager_id) REFERENCES public.employee(id);


--
-- Name: tag fknsm1s9c4fnwfbdxhffx66bvet; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tag
    ADD CONSTRAINT fknsm1s9c4fnwfbdxhffx66bvet FOREIGN KEY (created_by_id) REFERENCES public.member(id);


--
-- Name: employee_employee_history fko9psvok22dmxhrjvgom4jufqf; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee_employee_history
    ADD CONSTRAINT fko9psvok22dmxhrjvgom4jufqf FOREIGN KEY (ref_employee_id) REFERENCES public.employee(id);


--
-- Name: employee fkp4vahj8g9cybb87r69rcqv2k7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT fkp4vahj8g9cybb87r69rcqv2k7 FOREIGN KEY (business_unit_id) REFERENCES public.business_unit(id);


--
-- Name: employee_employee_history fkpaixb0hm51m4e2th7idvgp21l; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee_employee_history
    ADD CONSTRAINT fkpaixb0hm51m4e2th7idvgp21l FOREIGN KEY (employee_id) REFERENCES public.employee(id);


--
-- Name: scheduled_training fkpyqgi1py23b7xbdbghoedvmy5; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.scheduled_training
    ADD CONSTRAINT fkpyqgi1py23b7xbdbghoedvmy5 FOREIGN KEY (training_id) REFERENCES public.training(id);


--
-- Name: practice fkrqkf1dhybmof5dd5ln2dqd7n8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.practice
    ADD CONSTRAINT fkrqkf1dhybmof5dd5ln2dqd7n8 FOREIGN KEY (created_by_id) REFERENCES public.member(id);


--
-- Name: employee_working_position_history fkrtj4dfgxmm5qxcminnv5w3mgm; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee_working_position_history
    ADD CONSTRAINT fkrtj4dfgxmm5qxcminnv5w3mgm FOREIGN KEY (working_position_id) REFERENCES public.working_position(id);


--
-- Name: rate fks3uy3y734l721wycm2u32aoft; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rate
    ADD CONSTRAINT fks3uy3y734l721wycm2u32aoft FOREIGN KEY (created_by_id) REFERENCES public.member(id);


--
-- Name: notification_recipient fksb691ohcapb481gtapcm6humu; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notification_recipient
    ADD CONSTRAINT fksb691ohcapb481gtapcm6humu FOREIGN KEY (notification_id) REFERENCES public.notification(id);


--
-- Name: notification_recipient fktkgk3f63t6u7w5t7a60d6x0xm; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notification_recipient
    ADD CONSTRAINT fktkgk3f63t6u7w5t7a60d6x0xm FOREIGN KEY (recipient_id) REFERENCES public.member(id);


--
-- PostgreSQL database dump complete
--

