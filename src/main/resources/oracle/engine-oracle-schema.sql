create table language (
  id number(10) not null,
  version number(10) not null,
  code varchar2(2) not null,
  constraint language_u1 unique (code),
  name varchar2(50) not null,
  locale varchar2(50),
  image varchar2(255),
  constraint language_pk primary key (id)
);
create sequence sq_id_language increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_language
before insert
on language
for each row
declare
begin
  if (:new.id is null)
    then
    select sq_id_language.nextval into :new.id from dual;
  end if;
end;
/

create table location_country (
  id number(10) not null,
  version number(10) not null,
  code varchar2(4) not null,
  constraint location_country_u1 unique (code),
  name varchar2(50) not null,
  list_order number(10) not null,
  constraint location_country_pk primary key (id)
);
create sequence sq_id_location_country increment by 1 start with 1 nomaxvalue nocycle cache 10;
create index location_country_i1 on location_country (list_order, name);
create or replace trigger tr_id_inc_location_country
before insert
on location_country
for each row
declare
begin
  select sq_id_location_country.nextval into :new.id from dual;
end;
/

create table location_region (
  id number(10) not null,
  version number(10) not null,
  code varchar2(4) not null,
  constraint location_region_u1 unique (code),
  name varchar2(50) not null,
  upper_name varchar2(50) not null,
  country varchar2(4) not null,
  constraint location_region_pk primary key (id)
);
create sequence sq_id_location_region increment by 1 start with 1 nomaxvalue nocycle cache 10;
create index location_region_i1 on location_region (name);
create index location_region_i2 on location_region (country);
create or replace trigger tr_id_inc_location_region
before insert
on location_region
for each row
declare
begin
  select sq_id_location_region.nextval into :new.id from dual;
end;
/

create table location_state (
  id number(10) not null,
  version number(10) not null,
  code varchar2(4) not null,
  constraint location_state_u1 unique (code),
  name varchar2(50) not null,
  upper_name varchar2(50) not null,
  region varchar2(4) not null,
  country varchar2(4) not null,
  constraint location_state_pk primary key (id)
);
create sequence sq_id_location_state increment by 1 start with 1 nomaxvalue nocycle cache 10;
create index location_state_i1 on location_state (name);
create index location_state_i2 on location_state (country, region);
create or replace trigger tr_id_inc_location_state
before insert
on location_state
for each row
declare
begin
  select sq_id_location_state.nextval into :new.id from dual;
end;
/

create table location_zipcode (
  id number(10) not null,
  version number(10) not null,
  code varchar2(10) not null,
  constraint location_zipcode_u1 unique (code),
  name varchar2(50) not null,
  country varchar2(4) not null,
  constraint location_zipcode_pk primary key (id)
);
create sequence sq_id_location_zipcode increment by 1 start with 1 nomaxvalue nocycle cache 10;
create index location_zipcode_i1 on location_zipcode (name);
create index location_zipcode_i2 on location_zipcode (country, code);
create or replace trigger tr_id_inc_location_zipcode
before insert
on location_zipcode
for each row
declare
begin
  select sq_id_location_zipcode.nextval into :new.id from dual;
end;
/

create table website (
  id number(10) not null,
  version number(10) not null,
  name varchar2(255) not null,
  system_name varchar2(50) not null,
  db_name varchar2(50) not null,
  domain_name varchar2(255) not null,
  firstname varchar2(255),
  lastname varchar2(255),
  email varchar2(255),
  disk_space number(10),
  package varchar2(50),
  constraint website_pk primary key (id)
);
create sequence sq_id_website increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_website
before insert
on website
for each row
declare
begin
  select sq_id_website.nextval into :new.id from dual;
end;
/

create table website_address (
  id number(10) not null,
  version number(10) not null,
  address1 varchar2(50),
  address2 varchar2(50),
  zip_code varchar2(10),
  city varchar2(50),
  state varchar2(50),
  country varchar2(50),
  postal_box varchar2(50),
  telephone varchar2(20),
  mobile varchar2(20),
  fax varchar2(20),
  vat_number varchar2(50),
  website_id number(10) not null,
  constraint website_address_pk primary key (id)
);
create sequence sq_id_website_address increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_website_address
before insert
on website_address
for each row
declare
begin
  select sq_id_website_address.nextval into :new.id from dual;
end;
/

create table website_option (
  id number(10) not null,
  version number(10) not null,
  name varchar2(50) not null,
  value varchar2(20),
  website_id number(10) not null,
  constraint website_option_pk primary key (id)
);
create sequence sq_id_website_option increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_website_option
before insert
on website_option
for each row
declare
begin
  select sq_id_website_option.nextval into :new.id from dual;
end;
/

create table website_subscription (
  id number(10) not null,
  version number(10) not null,
  opening_date date not null,
  fee number(10),
  duration number(10),
  auto_renewal char(1) not null check (auto_renewal in (0, 1)),
  termination_date date,
  website_id number(10) not null,
  constraint website_subscription_pk primary key (id)
);
create sequence sq_id_website_subscription increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_website_subscription
before insert
on website_subscription
for each row
declare
begin
  select sq_id_website_subscription.nextval into :new.id from dual;
end;
/

quit;

