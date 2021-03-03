create table house (id bigint not null, description varchar(255), human_id bigint, primary key (id));
create table human (id bigint not null, name varchar(255), primary key (id));