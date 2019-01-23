
			
CREATE TABLE entmt.expenditure
(
  id bigint NOT NULL,
  amount numeric(19,2),
  create_date timestamp without time zone,
  create_id bigint,
  expanditur_date bigint,
  is_active integer,
  month_id bigint,
  purpose character varying(255),
  short_desc character varying(255),
  update_date timestamp without time zone,
  update_id bigint,
  year bigint,
  CONSTRAINT expenditure_pkey PRIMARY KEY (id)
);

CREATE TABLE entmt.mail_notification
(
  id bigint NOT NULL,
  create_date timestamp without time zone,
  create_id bigint NOT NULL,
  event_id bigint,
  is_active integer NOT NULL,
  mail_body character varying(255),
  mail_subject character varying(255),
  update_date timestamp without time zone,
  update_id bigint NOT NULL,
  CONSTRAINT mail_notification_pkey PRIMARY KEY (id)
);
CREATE TABLE entmt.month_master
(
  id bigint NOT NULL,
  create_date timestamp without time zone,
  create_id bigint NOT NULL,
  is_active integer NOT NULL,
  month character varying(255),
  update_date timestamp without time zone,
  update_id bigint NOT NULL,
  CONSTRAINT month_master_pkey PRIMARY KEY (id)
);

CREATE TABLE entmt.notification
(
  id bigint NOT NULL,
  create_date timestamp without time zone,
  create_id bigint,
  is_active integer,
  notification_from bigint,
  notification_msg character varying(255),
  notification_to bigint,
  update_date timestamp without time zone,
  update_id bigint,
  CONSTRAINT notification_pkey PRIMARY KEY (id)
);

CREATE TABLE entmt.payment_master
(
  id bigint NOT NULL,
  amount numeric(19,2),
  create_date timestamp without time zone,
  create_id bigint,
  is_active integer,
  month_id bigint,
  update_date timestamp without time zone,
  update_id bigint,
  year bigint,
  CONSTRAINT payment_master_pkey PRIMARY KEY (id)
);
CREATE TABLE entmt.payments
(
  id bigint NOT NULL,
  amount numeric(19,2),
  create_date timestamp without time zone,
  create_id bigint,
  is_active integer,
  month_id bigint,
  payment_date bigint,
  payment_status character varying(255),
  update_date timestamp without time zone,
  update_id bigint,
  user_id bigint,
  year integer,
  CONSTRAINT payments_pkey PRIMARY KEY (id)
);

CREATE TABLE entmt.userdetails
(
  id bigint NOT NULL,
  create_date timestamp without time zone,
  create_id bigint NOT NULL,
  date_of_birth timestamp without time zone,
  desk_phone_number character varying(255),
  email_id character varying(255),
  is_active integer NOT NULL,
  mobile_number character varying(255),
  name character varying(255),
  password character varying(255),
  personal_email character varying(255),
  personal_mobile_number character varying(255),
  role_id integer,
  update_date timestamp without time zone,
  update_id bigint NOT NULL,
  user_status integer,
  username character varying(255),
  CONSTRAINT userdetails_pkey PRIMARY KEY (id)
);


﻿INSERT INTO entmt.userdetails(id, create_date, create_id,is_active, name, password, role_id, update_date, update_id, user_status, 
            username)
    VALUES (nextval('entmt.userdetails_seq'), now(), 0, 1, 'Rajkumar Halder', 
            'Pass@123', 1, now(), 0, 100, 'rajkumar.halder');

			
