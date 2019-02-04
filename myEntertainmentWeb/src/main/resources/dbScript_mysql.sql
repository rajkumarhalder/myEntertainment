
			
CREATE TABLE expenditure
(
  id bigint NOT NULL,
  amount numeric(19,2),
  create_date timestamp,
  create_id bigint,
  expanditur_date bigint,
  is_active integer,
  month_id bigint,
  purpose varchar(255),
  short_desc varchar(255),
  update_date timestamp,
  update_id bigint,
  year bigint,
  CONSTRAINT expenditure_pkey PRIMARY KEY (id)
);

CREATE TABLE mail_notification
(
  id bigint NOT NULL,
  create_date timestamp,
  create_id bigint NOT NULL,
  event_id bigint,
  is_active integer NOT NULL,
  mail_body varchar(255),
  mail_subject varchar(255),
  update_date timestamp,
  update_id bigint NOT NULL,
  CONSTRAINT mail_notification_pkey PRIMARY KEY (id)
);
CREATE TABLE month_master
(
  id bigint NOT NULL,
  create_date timestamp,
  create_id bigint NOT NULL,
  is_active integer NOT NULL,
  month varchar(255),
  update_date timestamp,
  update_id bigint NOT NULL,
  CONSTRAINT month_master_pkey PRIMARY KEY (id)
);

CREATE TABLE notification
(
  id bigint NOT NULL,
  create_date timestamp,
  create_id bigint,
  is_active integer,
  notification_from bigint,
  notification_msg varchar(255),
  notification_to bigint,
  update_date timestamp ,
  update_id bigint,
  CONSTRAINT notification_pkey PRIMARY KEY (id)
);

CREATE TABLE payment_master
(
  id bigint NOT NULL,
  amount numeric(19,2),
  create_date timestamp,
  create_id bigint,
  is_active integer,
  month_id bigint,
  update_date timestamp,
  update_id bigint,
  year bigint,
  CONSTRAINT payment_master_pkey PRIMARY KEY (id)
);
CREATE TABLE payments
(
  id bigint NOT NULL,
  amount numeric(19,2),
  create_date timestamp,
  create_id bigint,
  is_active integer,
  month_id bigint,
  payment_date bigint,
  payment_status varchar(255),
  update_date timestamp,
  update_id bigint,
  user_id bigint,
  year integer,
  CONSTRAINT payments_pkey PRIMARY KEY (id)
);

CREATE TABLE userdetails
(
  id bigint NOT NULL,
  create_date timestamp,
  create_id bigint NOT NULL,
  date_of_birth timestamp ,
  desk_phone_number varchar(255),
  email_id varchar(255),
  is_active integer NOT NULL,
  mobile_number varchar(255),
  name varchar(255),
  login_pass varchar(255),
  personal_email varchar(255),
  personal_mobile_number varchar(255),
  role_id integer,
  update_date timestamp ,
  update_id bigint NOT NULL,
  user_status integer,
  username varchar(255),
  CONSTRAINT userdetails_pkey PRIMARY KEY (id)
);
ALTER TABLE `userdetails` CHANGE `id` `id` BIGINT(20) NOT NULL AUTO_INCREMENT;
alter TABLE userdetails ADD COLUMN workstation_id VARCHAR(255);


INSERT INTO userdetails(create_date, create_id,is_active, name, login_pass, role_id, update_date, update_id, user_status, 
            username)
    VALUES (now(), 0, 1, 'Rajkumar Halder', 
            'Pass@123', 1, now(), 0, 100, 'rajkumar.halder');

			
