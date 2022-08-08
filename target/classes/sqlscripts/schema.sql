/*drop table if exists roles;
drop table if exists specializations;
drop table if exists status;
drop table if exists designers;
drop table if exists administrators;
drop table if exists projects;
drop table if exists project_infos;
*/


CREATE TABLE IF NOT EXISTS roles (
	id bigserial PRIMARY KEY,
	name varchar(10) UNIQUE NOT NULL
	);

CREATE TABLE IF NOT EXISTS specializations (
	id bigserial PRIMARY KEY,
	name varchar(10) UNIQUE NOT NULL
	);

CREATE TABLE IF NOT EXISTS status (
	id bigserial PRIMARY KEY,
	name varchar(10) UNIQUE NOT NULL
	);

CREATE TABLE if not exists designers (
	id BIGSERIAL PRIMARY KEY,
	firstName TEXT NOT NULL,
	lastName TEXT NOT NULL,
	experience SMALLINT NOT NULL,
	email TEXT NOT NULL,
	password TEXT NOT NULL,
	specialization_id bigint NOT NULL REFERENCES specializations,
	role_id bigint NOT NULL REFERENCES roles,
	deleted boolean NOT NULL DEFAULT false
);

CREATE TABLE if not exists administrators (
	id BIGSERIAL PRIMARY KEY,
	firstName TEXT NOT NULL,
	lastName TEXT NOT NULL,
	email TEXT NOT NULL,
	password TEXT NOT NULL,
	role_id bigint NOT NULL REFERENCES roles,
	deleted boolean NOT NULL DEFAULT false
);

CREATE TABLE if not exists projects (
	id BIGSERIAL PRIMARY KEY,
	admin_id bigint NOT NULL,
	designer_id bigint NOT NULL,
	price_for_m2 DECIMAL(6, 2) NOT NULL,
	square DECIMAL(6, 2) NOT NULL,
	deleted boolean NOT NULL DEFAULT false
);

CREATE TABLE if not exists project_infos (
	id BIGSERIAL PRIMARY KEY,
	designer_id bigint NOT NULL,
	total_price DECIMAL(6, 2) NOT NULL,
	status_id bigint NOT NULL REFERENCES status,
	deleted boolean NOT NULL DEFAULT false
	);
