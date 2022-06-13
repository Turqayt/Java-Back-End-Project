-- mobile."role" definition

-- Drop table

-- DROP TABLE mobile."role";

CREATE TABLE mobile."role" (
	id serial4 NOT NULL,
	role_name varchar(255) NULL,
	short_name varchar(255) NULL,
	CONSTRAINT role_pkey PRIMARY KEY (id)
);


-- mobile."user" definition

-- Drop table

-- DROP TABLE mobile."user";

CREATE TABLE mobile."user" (
	id serial4 NOT NULL,
	address varchar(255) NULL,
	company_name varchar(255) NULL,
	created_at timestamp NULL,
	created_by varchar(255) NULL,
	email varchar(255) NULL,
	"name" varchar(255) NULL,
	occupation varchar(255) NULL,
	"password" varchar(255) NULL,
	phone_number varchar(255) NULL,
	status int2 NULL,
	surname varchar(255) NULL,
	tc_number varchar(255) NULL,
	updated_at timestamp NULL,
	updated_by varchar(255) NULL,
	user_name varchar(255) NULL,
	reset_key varchar(255) NULL,
	is_admin int2 NULL,
	CONSTRAINT uklqjrcobrh9jc8wpcar64q1bfh UNIQUE (user_name),
	CONSTRAINT ukob8kqyqqgmefl0aco34akdtpe UNIQUE (email),
	CONSTRAINT user_pkey PRIMARY KEY (id)
);
CREATE INDEX user_lower_idx ON mobile."user" USING btree (lower((name)::text));
CREATE INDEX user_lower_idx1 ON mobile."user" USING btree (lower((address)::text));
CREATE INDEX user_lower_idx2 ON mobile."user" USING btree (lower((company_name)::text));
CREATE INDEX user_lower_idx3 ON mobile."user" USING btree (lower((occupation)::text));
CREATE INDEX user_lower_idx4 ON mobile."user" USING btree (lower((email)::text));
CREATE INDEX user_lower_idx5 ON mobile."user" USING btree (lower((surname)::text));
CREATE INDEX user_lower_idx6 ON mobile."user" USING btree (lower((tc_number)::text));
CREATE INDEX user_lower_idx7 ON mobile."user" USING btree (lower((user_name)::text));


-- mobile.user_role definition

-- Drop table

-- DROP TABLE mobile.user_role;

CREATE TABLE mobile.userrole (
	role_id int4 NOT NULL,
	user_id int4 NOT NULL,
	CONSTRAINT user_role_pkey PRIMARY KEY (role_id, user_id),
	CONSTRAINT fk859n2jvi8ivhui0rl0esws6o FOREIGN KEY (user_id) REFERENCES mobile."user"(id),
	CONSTRAINT fka68196081fvovjhkek5m97n3y FOREIGN KEY (role_id) REFERENCES mobile."role"(id)
);

CREATE TABLE mobile."homepage" (
                               id serial4 NOT NULL,
                               number_of_forms int2 NULL,
                               number_of_users int2 NULL,
                               number_of_completed_forms int2 NULL,
                               CONSTRAINT homepage_pkey PRIMARY KEY (id)
);
