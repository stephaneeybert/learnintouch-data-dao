create table unique_token (
  id number(10) not null,
  version number(10) not null,
  name varchar2(50) not null,
  value varchar2(50) not null,
  creation_datetime date,
  expiration_datetime date,
  constraint unique_token_u1 unique (value),
  constraint unique_token_pk primary key (id)
);
create sequence sq_id_unique_token increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_unique_token
before insert 
on unique_token
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_unique_token.nextval into :new.id from dual;
  end if;
end;
/

create table preference (
  id number(10) not null,
  version number(10) not null,
  name varchar2(50) not null,
  constraint preference_u1 unique (name),
  value clob,
  type number(10) not null,
  constraint preference_pk primary key (id)
);
create sequence sq_id_preference increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_preference 
before insert 
on preference
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_preference.nextval into :new.id from dual;
  end if;
end;
/

create table property (
  id number(10) not null,
  version number(10) not null,
  name varchar2(50) not null,
  constraint property_u1 unique (name),
  value clob,
  constraint property_pk primary key (id)
);
create sequence sq_id_property increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_property 
before insert 
on property
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_property.nextval into :new.id from dual;
  end if;
end;
/

create table profile (
  id number(10) not null,
  version number(10) not null,
  name varchar2(50) not null,
  constraint profile_u1 unique (name),
  value clob,
  constraint profile_pk primary key (id)
);
create sequence sq_id_profile increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_profile 
before insert 
on profile
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_profile.nextval into :new.id from dual;
  end if;
end;
/

create table address (
  id number(10) not null,
  version number(10) not null,
  address1 varchar2(255),
  address2 varchar2(255),
  zip_code varchar2(10),
  city varchar2(255),
  state varchar2(255),
  country varchar2(255),
  postal_box varchar2(50),
  constraint address_pk primary key (id)
);
create sequence sq_id_address increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_address 
before insert 
on address
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_address.nextval into :new.id from dual;
  end if;
end;
/

create table user_account (
  id number(10) not null,
  version number(10) not null,
  firstname varchar2(255) not null,
  lastname varchar2(255) not null,
  organisation varchar2(255),
  email varchar2(255) not null,
  constraint user_u1 unique (email),
  fax varchar2(20),
  home_phone varchar2(20),
  work_phone varchar2(20),
  mobile_phone varchar2(20),
  password varchar2(100) not null,
  password_salt varchar2(50) not null,
  readable_password varchar2(50),
  unconfirmed_email number(1) not null check (unconfirmed_email in (0, 1)),
  valid_until date,
  last_login date not null,
  profile clob,
  image varchar2(255),
  imported number(1) not null check (imported in (0, 1)),
  mail_subscribe number(1) not null check (mail_subscribe in (0, 1)),
  sms_subscribe number(1) not null check (sms_subscribe in (0, 1)),
  creation_datetime date not null,
  address_id number(10),
  constraint user_pk primary key (id),
  constraint user_fk1 foreign key (address_id) references address (id)
);
create sequence sq_id_user_account increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_user_account
before insert 
on user_account
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_user_account.nextval into :new.id from dual;
  end if;
end;
/

create table social_user (
  id number(10) not null,
  version number(10) not null,
  facebook_user_id varchar2(12),
  constraint social_user_u1 unique (facebook_user_id),
  linkedin_user_id varchar2(48),
  constraint social_user_u2 unique (linkedin_user_id),
  google_user_id varchar2(48),
  constraint social_user_u3 unique (google_user_id),
  twitter_user_id varchar2(48),
  constraint social_user_u4 unique (twitter_user_id),
  user_account_id number(10) not null,
  constraint social_user_pk primary key (id),
  constraint social_user_fk1 foreign key (user_account_id) references user_account (id)
);
create sequence sq_id_social_user increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_social_user 
before insert 
on social_user
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_social_user.nextval into :new.id from dual;
  end if;
end;
/

create table admin (
  id number(10) not null,
  version number(10) not null,
  firstname varchar2(255) not null,
  lastname varchar2(255) not null,
  login varchar2(50) not null,
  constraint admin_login_u1 unique (login),
  password varchar2(100) not null,
  password_salt varchar2(50),
  super_admin number(1) not null check (super_admin in (0, 1)),
  preference_admin number(1) not null check (preference_admin in (0, 1)),
  address varchar2(255),
  zip_code varchar2(10),
  city varchar2(255),
  country varchar2(255),
  email varchar2(255),
  profile clob,
  post_login_url varchar2(255),
  constraint admin_pk primary key (id)
);
create sequence sq_id_admin increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_admin 
before insert 
on admin
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_admin.nextval into :new.id from dual;
  end if;
end;
/

create table admin_module (
  id number(10) not null,
  version number(10) not null,
  module varchar2(50) not null,
  admin_id number(10) not null,
  constraint admin_module_pk primary key (id),
  constraint admin_module_fk1 foreign key (admin_id) references admin (id)
);
create sequence sq_id_admin_module increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_admin_module 
before insert 
on admin_module
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_admin_module.nextval into :new.id from dual;
  end if;
end;
/

create table admin_option (
  id number(10) not null,
  version number(10) not null,
  name varchar2(50) not null,
  value varchar2(20),
  admin_id number(10) not null,
  constraint admin_option_pk primary key (id),
  constraint admin_option_fk1 foreign key (admin_id) references admin (id)
);
create sequence sq_id_admin_option increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_admin_option 
before insert 
on admin_option
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_admin_option.nextval into :new.id from dual;
  end if;
end;
/

create table template_property_set (
  id number(10) not null,
  version number(10) not null,
  constraint template_property_set_pk primary key (id)
);
create sequence sq_id_template_property_set increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_t_p_set 
before insert 
on template_property_set
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_template_property_set.nextval into :new.id from dual;
  end if;
end;
/

create table template_model (
  id number(10) not null,
  version number(10) not null,
  name varchar2(50) not null,
  description varchar2(255),
  model_type varchar2(50) not null,
  parent_id number(10),
  template_property_set_id number(10),
  inner_template_property_set_id number(10),
  constraint template_model_u1 unique (name),
  constraint template_model_pk primary key (id),
  constraint template_model_fk1 foreign key (parent_id) references template_model (id),
  constraint template_model_fk2 foreign key (template_property_set_id) references template_property_set (id),
  constraint template_model_fk3 foreign key (inner_template_property_set_id) references template_property_set (id)
);
create sequence sq_id_template_model increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_template_model 
before insert 
on template_model
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_template_model.nextval into :new.id from dual;
  end if;
end;
/

create table template_container (
  id number(10) not null,
  version number(10) not null,
  template_model_id number(10),
  row_nb number(10) not null,
  cell_nb number(10) not null,
  template_property_set_id number(10),
  constraint template_container_pk primary key (id),
  constraint template_container_fk1 foreign key (template_model_id) references template_model (id),
  constraint template_container_fk2 foreign key (template_property_set_id) references template_property_set (id)
);
create sequence sq_id_template_container increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_template_container 
before insert 
on template_container
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_template_container.nextval into :new.id from dual;
  end if;
end;
/

create table template_element (
  id number(10) not null,
  version number(10) not null,
  element_type varchar2(50) not null,
  object_id number(10),
  template_container_id number(10),
  list_order number(10) not null,
  hide number(1) not null check (hide in (0, 1)),
  constraint template_element_pk primary key (id),
  constraint template_element_fk1 foreign key (template_container_id) references template_container (id)
);
create sequence sq_id_template_element increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_template_element 
before insert 
on template_element
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_template_element.nextval into :new.id from dual;
  end if;
end;
/

create table template_element_language (
  id number(10) not null,
  version number(10) not null,
  language_code varchar2(2),
  object_id number(10),
  template_element_id number(10) not null,
  constraint template_element_language_pk primary key (id),
  constraint template_element_language_fk1 foreign key (template_element_id) references template_element (id)
);
create sequence sq_id_t_e_language increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_t_e_language 
before insert 
on template_element_language
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_t_e_language.nextval into :new.id from dual;
  end if;
end;
/

create table template_element_tag (
  id number(10) not null,
  version number(10) not null,
  template_element_id number(10) not null,
  template_property_set_id number(10),
  dom_tag_id varchar2(50) not null,
  constraint template_element_tag_u1 unique (template_element_id, dom_tag_id),
  constraint template_element_tag_pk primary key (id),
  constraint template_element_tag_fk1 foreign key (template_element_id) references template_element (id),
  constraint template_element_tag_fk2 foreign key (template_property_set_id) references template_property_set (id)
);
create sequence sq_id_template_element_tag increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_template_element_tag 
before insert 
on template_element_tag
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_template_element_tag.nextval into :new.id from dual;
  end if;
end;
/

create table template_page (
  id number(10) not null,
  version number(10) not null,
  system_page varchar2(50) not null,
  template_model_id number(10) not null,
  constraint template_page_pk primary key (id),
  constraint template_page_fk1 foreign key (template_model_id) references template_model (id)
);
create sequence sq_id_template_page increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_template_page 
before insert 
on template_page
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_template_page.nextval into :new.id from dual;
  end if;
end;
/

create table template_page_tag (
  id number(10) not null,
  version number(10) not null,
  template_page_id number(10) not null,
  template_property_set_id number(10),
  dom_tag_id varchar2(50) not null,
  constraint template_page_tag_u1 unique (template_page_id, dom_tag_id),
  constraint template_page_tag_pk primary key (id),
  constraint template_page_tag_fk1 foreign key (template_page_id) references template_page (id),
  constraint template_page_tag_fk2 foreign key (template_property_set_id) references template_property_set (id)
);
create sequence sq_id_template_page_tag increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_template_page_tag 
before insert 
on template_page_tag
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_template_page_tag.nextval into :new.id from dual;
  end if;
end;
/

create table template_property (
  id number(10) not null,
  version number(10) not null,
  name varchar2(50) not null,
  value varchar2(50),
  template_property_set_id number(10),
  constraint template_property_pk primary key (id),
  constraint template_property_fk1 foreign key (template_property_set_id) references template_property_set (id)
);
create sequence sq_id_template_property increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_template_property 
before insert 
on template_property
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_template_property.nextval into :new.id from dual;
  end if;
end;
/

create table client (
  id number(10) not null,
  version number(10) not null,
  name varchar2(50) not null,
  description varchar2(255),
  image varchar2(255),
  url varchar2(255),
  constraint client_pk primary key (id)
);
create sequence sq_id_client increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_client 
before insert 
on client
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_client.nextval into :new.id from dual;
  end if;
end;
/

create table contact_referer (
  id number(10) not null,
  version number(10) not null,
  description clob,
  list_order number(10) not null,
  constraint contact_referer_pk primary key (id)
);
create sequence sq_id_contact_referer increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_contact_referer 
before insert 
on contact_referer
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_contact_referer.nextval into :new.id from dual;
  end if;
end;
/

create table contact_status (
  id number(10) not null,
  version number(10) not null,
  name varchar2(50) not null,
  description varchar2(255),
  list_order number(10) not null,
  constraint contact_status_pk primary key (id)
);
create sequence sq_id_contact_status increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_contact_status 
before insert 
on contact_status
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_contact_status.nextval into :new.id from dual;
  end if;
end;
/

create table contact (
  id number(10) not null,
  version number(10) not null,
  firstname varchar2(255),
  lastname varchar2(255),
  email varchar2(255) not null,
  organisation varchar2(255),
  telephone varchar2(20),
  subject varchar2(255),
  message clob not null,
  contact_datetime date,
  contact_status_id number(10),
  contact_referer_id number(10),
  garbage number(1) not null check (garbage in (0, 1)),
  constraint contact_pk primary key (id),
  constraint contact_fk1 foreign key (contact_status_id) references contact_status (id),
  constraint contact_fk2 foreign key (contact_referer_id) references contact_referer (id)
);
create sequence sq_id_contact increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_contact 
before insert 
on contact
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_contact.nextval into :new.id from dual;
  end if;
end;
/

create table container (
  id number(10) not null,
  version number(10) not null,
  content clob,
  constraint container_pk primary key (id)
);
create sequence sq_id_container increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_container 
before insert 
on container
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_container.nextval into :new.id from dual;
  end if;
end;
/

create table content_import (
  id number(10) not null,
  version number(10) not null,
  domain_name varchar2(255) not null,
  is_importing number(1) not null check (is_importing in (0, 1)),
  is_exporting number(1) not null check (is_exporting in (0, 1)),
  permission_key varchar2(10),
  permission_status varchar2(10),
  constraint content_import_pk primary key (id)
);
create sequence sq_id_content_import increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_content_import 
before insert 
on content_import
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_content_import.nextval into :new.id from dual;
  end if;
end;
/

create table content_import_history (
  id number(10) not null,
  version number(10) not null,
  domain_name varchar2(255) not null,
  course varchar2(255),
  lesson varchar2(255),
  exercise varchar2(255),
  import_datetime date,
  constraint content_import_history_pk primary key (id)
);
create sequence sq_id_content_import_history increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_c_i_history 
before insert 
on content_import_history
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_content_import_history.nextval into :new.id from dual;
  end if;
end;
/

create table document_category (
  id number(10) not null,
  version number(10) not null,
  name varchar2(50) not null,
  description varchar2(255),
  list_order number(10) not null,
  constraint document_category_pk primary key (id)
);
create sequence sq_id_document_category increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_document_category 
before insert 
on document_category
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_document_category.nextval into :new.id from dual;
  end if;
end;
/

create table document (
  id number(10) not null,
  version number(10) not null,
  reference varchar2(50),
  description varchar2(255),
  filename varchar2(50),
  hide number(1) not null check (hide in (0, 1)),
  secured number(1) not null check (secured in (0, 1)),
  category_id number(10),
  list_order number(10) not null,
  constraint document_pk primary key (id),
  constraint document_fk1 foreign key (category_id) references document_category (id)
);
create sequence sq_id_document increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_document 
before insert 
on document
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_document.nextval into :new.id from dual;
  end if;
end;
/

create table elearning_scoring (
  id number(10) not null,
  version number(10) not null,
  name varchar2(255) not null,
  description varchar2(255),
  required_score number(10),
  constraint elearning_scoring_pk primary key (id)
);
create sequence sq_id_elearning_scoring increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_elearning_scoring 
before insert 
on elearning_scoring
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_elearning_scoring.nextval into :new.id from dual;
  end if;
end;
/

create table elearning_scoring_range (
  id number(10) not null,
  version number(10) not null,
  upper_range number(10) not null,
  score clob,
  advice clob,
  proposal clob,
  link_text varchar2(255),
  link_url varchar2(255),
  elearning_scoring_id number(10) not null,
  constraint elearning_scoring_range_pk primary key (id),
  constraint elearning_scoring_fk1 foreign key (elearning_scoring_id) references elearning_scoring (id)
);
create sequence sq_id_elearning_scoring_range increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_e_s_range 
before insert 
on elearning_scoring_range
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_elearning_scoring_range.nextval into :new.id from dual;
  end if;
end;
/

create table elearning_category (
  id number(10) not null,
  version number(10) not null,
  name varchar2(255) not null,
  description varchar2(255),
  constraint elearning_category_pk primary key (id)
);
create sequence sq_id_elearning_category increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_elearning_category 
before insert 
on elearning_category
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_elearning_category.nextval into :new.id from dual;
  end if;
end;
/

create table elearning_subject (
  id number(10) not null,
  version number(10) not null,
  name varchar2(255) not null,
  description varchar2(255),
  constraint elearning_subject_pk primary key (id)
);
create sequence sq_id_elearning_subject increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_elearning_subject 
before insert 
on elearning_subject
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_elearning_subject.nextval into :new.id from dual;
  end if;
end;
/

create table elearning_level (
  id number(10) not null,
  version number(10) not null,
  name varchar2(255) not null,
  description varchar2(255),
  constraint elearning_level_pk primary key (id)
);
create sequence sq_id_elearning_level increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_elearning_level 
before insert 
on elearning_level
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_elearning_level.nextval into :new.id from dual;
  end if;
end;
/

create table elearning_matter (
  id number(10) not null,
  version number(10) not null,
  name varchar2(255) not null,
  description varchar2(255),
  constraint elearning_matter_pk primary key (id)
);
create sequence sq_id_elearning_matter increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_elearning_matter 
before insert 
on elearning_matter
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_elearning_matter.nextval into :new.id from dual;
  end if;
end;
/

create table elearning_session (
  id number(10) not null,
  version number(10) not null,
  name varchar2(255) not null,
  description varchar2(255),
  opening_date date not null,
  closing_date date,
  closed number(1) not null check (closed in (0, 1)),
  constraint elearning_session_pk primary key (id)
);
create sequence sq_id_elearning_session increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_elearning_session 
before insert 
on elearning_session
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_elearning_session.nextval into :new.id from dual;
  end if;
end;
/

create table elearning_class (
  id number(10) not null,
  version number(10) not null,
  name varchar2(255) not null,
  description varchar2(255),
  constraint elearning_class_pk primary key (id)
);
create sequence sq_id_elearning_class increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_elearning_class 
before insert 
on elearning_class
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_elearning_class.nextval into :new.id from dual;
  end if;
end;
/

create table elearning_teacher (
  id number(10) not null,
  version number(10) not null,
  user_account_id number(10) not null,
  constraint elearning_teacher_u1 unique (user_account_id),
  constraint elearning_teacher_pk primary key (id),
  constraint elearning_teacher_fk1 foreign key (user_account_id) references user_account (id)
);
create sequence sq_id_elearning_teacher increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_elearning_teacher 
before insert 
on elearning_teacher
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_elearning_teacher.nextval into :new.id from dual;
  end if;
end;
/

create table elearning_course (
  id number(10) not null,
  version number(10) not null,
  name varchar2(255) not null,
  description varchar2(255),
  image varchar2(255),
  instant_correction number(1) not null check (instant_correction in (0, 1)),
  instant_congratulation number(1) not null check (instant_congratulation in (0, 1)),
  instant_solution number(1) not null check (instant_solution in (0, 1)),
  importable number(1) not null check (importable in (0, 1)),
  locked number(1) not null check (locked in (0, 1)),
  secured number(1) not null check (secured in (0, 1)),
  free_samples number(10),
  auto_subscription number(1) not null check (auto_subscription in (0, 1)),
  auto_unsubscription number(1) not null check (auto_unsubscription in (0, 1)),
  interrupt_timed_out_exercise number(1) not null check (interrupt_timed_out_exercise in (0, 1)),
  reset_exercise_answers number(1) not null check (reset_exercise_answers in (0, 1)),
  exercise_only_once number(1) not null check (exercise_only_once in (0, 1)),
  exercise_any_order number(1) not null check (exercise_any_order in (0, 1)),
  save_result_option varchar2(50),
  shuffle_questions number(1) not null check (shuffle_questions in (0, 1)),
  shuffle_answers number(1) not null check (shuffle_answers in (0, 1)),
  matter_id number(10) not null,
  user_account_id number(10),
  constraint elearning_course_pk primary key (id),
  constraint elearning_course_fk1 foreign key (matter_id) references elearning_matter (id),
  constraint elearning_course_fk2 foreign key (user_account_id) references user_account (id)
);
create sequence sq_id_elearning_course increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_elearning_course 
before insert 
on elearning_course
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_elearning_course.nextval into :new.id from dual;
  end if;
end;
/

create table elearning_session_course (
  id number(10) not null,
  version number(10) not null,
  elearning_session_id number(10) not null,
  elearning_course_id number(10) not null,
  constraint elearning_session_course_pk primary key (id),
  constraint elearning_session_course_u1 unique (elearning_session_id, elearning_course_id),
  constraint elearning_session_course_fk1 foreign key (elearning_session_id) references elearning_session (id),
  constraint elearning_session_course_fk2 foreign key (elearning_course_id) references elearning_course (id)
);
create sequence sq_id_elearning_session_course increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_e_s_course 
before insert 
on elearning_session_course
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_elearning_session_course.nextval into :new.id from dual;
  end if;
end;
/

create table elearning_course_info (
  id number(10) not null,
  version number(10) not null,
  headline varchar2(255),
  information clob,
  list_order number(10) not null,
  elearning_course_id number(10) not null,
  constraint elearning_course_info_pk primary key (id),
  constraint elearning_course_info_fk1 foreign key (elearning_course_id) references elearning_course (id)
);
create sequence sq_id_elearning_course_info increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_e_c_info 
before insert 
on elearning_course_info
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_elearning_course_info.nextval into :new.id from dual;
  end if;
end;
/

create table elearning_exercise (
  id number(10) not null,
  version number(10) not null,
  name varchar2(255) not null,
  constraint elearning_exercise_u1 unique (name),
  description clob,
  instructions clob,
  introduction clob,
  hide_introduction number(1) not null check (hide_introduction in (0, 1)),
  image varchar2(255),
  audio varchar2(255),
  public_access number(1) not null check (public_access in (0, 1)),
  max_duration number(10),
  release_date date not null,
  secured number(1) not null check (secured in (0, 1)),
  skip_exercise_introduction number(1) not null check (skip_exercise_introduction in (0, 1)),
  social_connect number(1) not null check (social_connect in (0, 1)),
  hide_solutions number(1) not null check (hide_solutions in (0, 1)),
  hide_progression_bar number(1) not null check (hide_progression_bar in (0, 1)),
  hide_page_tabs number(1) not null check (hide_page_tabs in (0, 1)),
  disable_next_page_tabs number(1) not null check (disable_next_page_tabs in (0, 1)),
  number_page_tabs number(10),
  hide_keyboard number(1) not null check (hide_keyboard in (0, 1)),
  contact_page number(1) not null check (contact_page in (0, 1)),
  category_id number(10),
  webpage_id varchar2(255),
  level_id number(10),
  subject_id number(10),
  scoring_id number(10),
  garbage number(1) not null check (garbage in (0, 1)),
  locked number(1) not null check (locked in (0, 1)),
  constraint elearning_exercise_pk primary key (id),
  constraint elearning_exercise_fk1 foreign key (category_id) references elearning_category (id),
  constraint elearning_exercise_fk2 foreign key (level_id) references elearning_level (id),
  constraint elearning_exercise_fk3 foreign key (subject_id) references elearning_subject (id),
  constraint elearning_exercise_fk4 foreign key (scoring_id) references elearning_scoring (id)
);
create sequence sq_id_elearning_exercise increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_elearning_exercise 
before insert 
on elearning_exercise
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_elearning_exercise.nextval into :new.id from dual;
  end if;
end;
/

create table elearning_exercise_page (
  id number(10) not null,
  version number(10) not null,
  name varchar2(255),
  description varchar2(255),
  instructions clob,
  text clob,
  hide_text number(1) not null check (hide_text in (0, 1)),
  text_max_height number(10) not null,
  image varchar2(255),
  audio varchar2(255),
  video varchar2(1024),
  video_url varchar2(255),
  question_type varchar2(50),
  hint_placement varchar2(50),
  elearning_exercise_id number(10),
  list_order number(10) not null,
  constraint elearning_exercise_page_pk primary key (id),
  constraint elearning_exercise_page_fk1 foreign key (elearning_exercise_id) references elearning_exercise (id)
);
create sequence sq_id_elearning_exercise_page increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_e_e_page 
before insert 
on elearning_exercise_page
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_elearning_exercise_page.nextval into :new.id from dual;
  end if;
end;
/

create table elearning_question (
  id number(10) not null,
  version number(10) not null,
  question clob,
  explanation clob,
  elearning_exercise_page_id number(10) not null,
  image varchar2(255),
  audio varchar2(255),
  hint clob,
  points number(10),
  answer_nb_words number(10),
  list_order number(10) not null,
  constraint elearning_question_pk primary key (id),
  constraint elearning_question_fk1 foreign key (elearning_exercise_page_id) references elearning_exercise_page (id)
);
create sequence sq_id_elearning_question increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_elearning_question 
before insert 
on elearning_question
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_elearning_question.nextval into :new.id from dual;
  end if;
end;
/

create table elearning_answer (
  id number(10) not null,
  version number(10) not null,
  answer varchar2(255),
  explanation clob,
  image varchar2(255),
  audio varchar2(255),
  elearning_question_id number(10) not null,
  list_order number(10) not null,
  constraint elearning_answer_pk primary key (id),
  constraint elearning_answer_fk1 foreign key (elearning_question_id) references elearning_question (id)
);
create sequence sq_id_elearning_answer increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_elearning_answer 
before insert 
on elearning_answer
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_elearning_answer.nextval into :new.id from dual;
  end if;
end;
/

create table elearning_solution (
  id number(10) not null,
  version number(10) not null,
  elearning_question_id number(10) not null,
  elearning_answer_id number(10) not null,
  constraint elearning_solution_u1 unique (elearning_question_id, elearning_answer_id),
  constraint elearning_solution_pk primary key (id),
  constraint elearning_solution_fk1 foreign key (elearning_question_id) references elearning_question (id),
  constraint elearning_solution_fk2 foreign key (elearning_answer_id) references elearning_answer (id)
);
create sequence sq_id_elearning_solution increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_elearning_solution 
before insert 
on elearning_solution
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_elearning_solution.nextval into :new.id from dual;
  end if;
end;
/

create table elearning_subscription (
  id number(10) not null,
  version number(10) not null,
  user_account_id number(10) not null,
  teacher_id number(10),
  session_id number(10),
  course_id number(10),
  class_id number(10),
  subscription_date date,
  subscription_close date,
  watch_live number(1) not null check (watch_live in (0, 1)),
  last_exercise_id number(10),
  last_exercise_page_id number(10),
  last_active date,
  whiteboard clob,
  constraint elearning_subscription_pk primary key (id),
  constraint elearning_subscription_fk1 foreign key (user_account_id) references user_account (id),
  constraint elearning_subscription_fk2 foreign key (teacher_id) references elearning_teacher (id),
  constraint elearning_subscription_fk3 foreign key (session_id) references elearning_session (id),
  constraint elearning_subscription_fk4 foreign key (course_id) references elearning_course (id),
  constraint elearning_subscription_fk5 foreign key (class_id) references elearning_class (id),
  constraint elearning_subscription_fk6 foreign key (last_exercise_id) references elearning_exercise (id),
  constraint elearning_subscription_fk7 foreign key (last_exercise_page_id) references elearning_exercise_page (id)
);
create sequence sq_id_elearning_subscription increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_e_subscription 
before insert 
on elearning_subscription
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_elearning_subscription.nextval into :new.id from dual;
  end if;
end;
/

create table elearning_result (
  id number(10) not null,
  version number(10) not null,
  elearning_exercise_id number(10),
  subscription_id number(10),
  exercise_datetime date,
  exercise_elapsed_time number(10),
  firstname varchar2(255),
  lastname varchar2(255),
  message clob,
  text_comment clob,
  hide_comment number(1) not null check (hide_comment in (0, 1)),
  whiteboard clob,
  email varchar2(255),
  nb_reading_questions number(10),
  nb_correct_reading_answers number(10),
  nb_incorrect_reading_answers number(10),
  nb_reading_points number(10),
  nb_writing_questions number(10),
  nb_correct_writing_answers number(10),
  nb_incorrect_writing_answers number(10),
  nb_writing_points number(10),
  nb_listening_questions number(10),
  nb_correct_listening_answers number(10),
  nb_incorrect_listening_answers number(10),
  nb_listening_points number(10),
  nb_not_answered number(10),
  nb_incorrect_answers number(10),
  constraint elearning_result_pk primary key (id),
  constraint elearning_exercise_id_fk1 foreign key (elearning_exercise_id) references elearning_exercise (id),
  constraint elearning_subscription_id_fk2 foreign key (subscription_id) references elearning_subscription (id)
);
create sequence sq_id_elearning_result increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_elearning_result 
before insert 
on elearning_result
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_elearning_result.nextval into :new.id from dual;
  end if;
end;
/

create table elearning_question_result (
  id number(10) not null,
  version number(10) not null,
  elearning_result_id number(10) not null,
  elearning_question_id number(10) not null,
  elearning_answer_id number(10),
  elearning_answer_text clob,
  elearning_answer_order number(10),
  constraint elearning_question_result_pk primary key (id),
  constraint elearning_result_id_fk1 foreign key (elearning_result_id) references elearning_result (id),
  constraint elearning_question_id_fk2 foreign key (elearning_question_id) references elearning_question (id),
  constraint elearning_answer_id_fk3 foreign key (elearning_answer_id) references elearning_answer (id)
);
create sequence sq_id_e_q_result increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_e_q_result 
before insert 
on elearning_question_result
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_e_q_result.nextval into :new.id from dual;
  end if;
end;
/

create table elearning_result_range (
  id number(10) not null,
  version number(10) not null,
  upper_range number(10) not null,
  grade varchar2(20)
);
create sequence sq_id_elearning_result_range increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_e_r_range 
before insert 
on elearning_result_range
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_elearning_result_range.nextval into :new.id from dual;
  end if;
end;
/

create table elearning_lesson_model (
  id number(10) not null,
  version number(10) not null,
  name varchar2(255) not null,
  description varchar2(255),
  instructions clob,
  locked number(1) not null check (locked in (0, 1)),
  constraint elearning_lesson_model_pk primary key (id)
);
create sequence sq_id_elearning_lesson_model increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_e_l_model 
before insert 
on elearning_lesson_model
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_elearning_lesson_model.nextval into :new.id from dual;
  end if;
end;
/

create table elearning_lesson_heading (
  id number(10) not null,
  version number(10) not null,
  name varchar2(255) not null,
  content clob,
  list_order number(10) not null,
  image varchar2(255),
  elearning_lesson_model_id number(10),
  constraint elearning_lesson_heading_pk primary key (id),
  constraint elearning_lesson_heading_fk1 foreign key (elearning_lesson_model_id) references elearning_lesson_model (id)
);
create sequence sq_id_elearning_lesson_heading increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_e_l_heading 
before insert 
on elearning_lesson_heading
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_elearning_lesson_heading.nextval into :new.id from dual;
  end if;
end;
/

create table elearning_lesson (
  id number(10) not null,
  version number(10) not null,
  name varchar2(255) not null,
  constraint elearning_lesson_u1 unique (name),
  description clob,
  instructions clob,
  image varchar2(255),
  audio varchar2(255),
  introduction clob,
  secured number(1) not null check (secured in (0, 1)),
  public_access number(1) not null check (public_access in (0, 1)),
  release_date date not null,
  garbage number(1) not null check (garbage in (0, 1)),
  locked number(1) not null check (locked in (0, 1)),
  lesson_model_id number(10),
  category_id number(10),
  level_id number(10),
  subject_id number(10),
  constraint elearning_lesson_pk primary key (id),
  constraint elearning_lesson_fk1 foreign key (category_id) references elearning_category (id),
  constraint elearning_lesson_fk2 foreign key (level_id) references elearning_level (id),
  constraint elearning_lesson_fk3 foreign key (subject_id) references elearning_subject (id),
  constraint elearning_lesson_fk4 foreign key (lesson_model_id) references elearning_lesson_model (id)
);
create sequence sq_id_elearning_lesson increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_elearning_lesson 
before insert 
on elearning_lesson
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_elearning_lesson.nextval into :new.id from dual;
  end if;
end;
/

create table elearning_lesson_paragraph (
  id number(10) not null,
  version number(10) not null,
  headline varchar2(255) not null,
  body clob,
  image varchar2(255),
  audio varchar2(255),
  video varchar2(1024),
  video_url varchar2(255),
  list_order number(10) not null,
  elearning_lesson_id number(10) not null,
  elearning_lesson_heading_id number(10),
  elearning_exercise_id number(10),
  exercise_title varchar2(255),
  constraint elearning_lesson_paragraph_pk primary key (id),
  constraint elearning_lesson_paragraph_fk1 foreign key (elearning_lesson_id) references elearning_lesson (id),
  constraint elearning_lesson_paragraph_fk2 foreign key (elearning_lesson_heading_id) references elearning_lesson_heading (id),
  constraint elearning_lesson_paragraph_fk3 foreign key (elearning_exercise_id) references elearning_exercise (id)
);
create sequence sq_id_e_l_paragraph increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_e_l_paragraph 
before insert 
on elearning_lesson_paragraph
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_e_l_paragraph.nextval into :new.id from dual;
  end if;
end;
/

create table elearning_course_item (
  id number(10) not null,
  version number(10) not null,
  elearning_course_id number(10) not null,
  elearning_exercise_id number(10),
  elearning_lesson_id number(10),
  list_order number(10) not null,
  constraint elearning_course_item_pk primary key (id),
  constraint elearning_course_item_fk1 foreign key (elearning_course_id) references elearning_course (id),
  constraint elearning_course_item_fk2 foreign key (elearning_exercise_id) references elearning_exercise (id),
  constraint elearning_course_item_fk3 foreign key (elearning_lesson_id) references elearning_lesson (id)
);
create sequence sq_id_elearning_course_item increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_e_c_item 
before insert 
on elearning_course_item
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_elearning_course_item.nextval into :new.id from dual;
  end if;
end;
/

create table elearning_assignment (
  id number(10) not null,
  version number(10) not null,
  elearning_subscription_id number(10) not null,
  elearning_exercise_id number(10) not null,
  elearning_result_id number(10),
  only_once number(1) not null check (only_once in (0, 1)),
  opening_date date,
  closing_date date,
  constraint elearning_assignment_pk primary key (id),
  constraint elearning_assignment_u1 unique (elearning_subscription_id, elearning_exercise_id),
  constraint elearning_assignment_fk1 foreign key (elearning_subscription_id) references elearning_subscription (id),
  constraint elearning_assignment_fk2 foreign key (elearning_exercise_id) references elearning_exercise (id),
  constraint elearning_assignment_fk3 foreign key (elearning_result_id) references elearning_result (id)
);
create sequence sq_id_elearning_assignment increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_elearning_assignment 
before insert 
on elearning_assignment
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_elearning_assignment.nextval into :new.id from dual;
  end if;
end;
/

create table flash (
  id number(10) not null,
  version number(10) not null,
  filename varchar2(50),
  width varchar2(10),
  height varchar2(10),
  bgcolor varchar2(10),
  wddx varchar2(50),
  constraint flash_pk primary key (id)
);
create sequence sq_id_flash increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_flash 
before insert 
on flash
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_flash.nextval into :new.id from dual;
  end if;
end;
/

create table mail_category (
  id number(10) not null,
  version number(10) not null,
  name varchar2(50) not null,
  description varchar2(255),
  constraint mail_category_pk primary key (id)
);
create sequence sq_id_mail_category increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_mail_category 
before insert 
on mail_category
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_mail_category.nextval into :new.id from dual;
  end if;
end;
/

create table mail (
  id number(10) not null,
  version number(10) not null,
  subject varchar2(255) not null,
  body clob,
  description varchar2(255),
  text_format number(1) not null check (text_format in (0, 1)),
  attachments clob,
  creation_datetime date,
  send_datetime date,
  locked number(1) not null check (locked in (0, 1)),
  admin_id number(10),
  category_id number(10),
  constraint mail_pk primary key (id),
  constraint mail_fk1 foreign key (admin_id) references admin (id),
  constraint mail_fk2 foreign key (category_id) references mail_category (id)
);
create sequence sq_id_mail increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_mail 
before insert 
on mail
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_mail.nextval into :new.id from dual;
  end if;
end;
/

create table mail_address (
  id number(10) not null,
  version number(10) not null,
  firstname varchar2(255),
  lastname varchar2(255),
  email varchar2(255) not null,
  constraint mail_address_u1 unique (email),
  text_comment clob,
  country varchar2(255),
  subscribe number(1) not null check (subscribe in (0, 1)),
  imported number(1) not null check (imported in (0, 1)),
  creation_datetime date default NULL,
  constraint mail_address_pk primary key (id)
);
create sequence sq_id_mail_address increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_mail_address 
before insert 
on mail_address
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_mail_address.nextval into :new.id from dual;
  end if;
end;
/

create table mail_list (
  id number(10) not null,
  version number(10) not null,
  name varchar2(255) not null,
  description varchar2(255),
  auto_subscribe number(1) not null check (auto_subscribe in (0, 1)),
  constraint mail_list_pk primary key (id)
);
create sequence sq_id_mail_list increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_mail_list 
before insert 
on mail_list
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_mail_list.nextval into :new.id from dual;
  end if;
end;
/

create table mail_outbox (
  id number(10) not null,
  version number(10) not null,
  firstname varchar2(255),
  lastname varchar2(255),
  email varchar2(255) not null,
  password varchar2(20),
  sent number(1) not null check (sent in (0, 1)),
  error_message varchar2(255),
  meta_names clob,
  constraint mail_outbox_pk primary key (id)
);
create sequence sq_id_mail_outbox increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_mail_outbox 
before insert 
on mail_outbox
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_mail_outbox.nextval into :new.id from dual;
  end if;
end;
/

create table mail_history (
  id number(10) not null,
  version number(10) not null,
  subject varchar2(255) not null,
  body clob,
  description varchar2(255),
  attachments clob,
  mail_list_id number(10),
  email varchar2(255),
  admin_id number(10),
  send_datetime date default NULL,
  constraint mail_history_pk primary key (id),
  constraint mail_history_fk1 foreign key (mail_list_id) references mail_list (id),
  constraint mail_history_fk2 foreign key (admin_id) references admin (id)
);
create sequence sq_id_mail_history increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_mail_history 
before insert 
on mail_history
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_mail_history.nextval into :new.id from dual;
  end if;
end;
/

create table mail_list_address (
  id number(10) not null,
  version number(10) not null,
  mail_list_id number(10) not null,
  mail_address_id number(10) not null,
  constraint mail_list_address_pk primary key (id),
  constraint mail_list_address_fk1 foreign key (mail_list_id) references mail_list (id),
  constraint mail_list_address_fk2 foreign key (mail_address_id) references mail_address (id)
);
create sequence sq_id_mail_list_address increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_mail_list_address 
before insert 
on mail_list_address
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_mail_list_address.nextval into :new.id from dual;
  end if;
end;
/

create table mail_list_user (
  id number(10) not null,
  version number(10) not null,
  mail_list_id number(10) not null,
  user_account_id number(10) not null,
  constraint mail_list_user_pk primary key (id),
  constraint mail_list_user_fk1 foreign key (mail_list_id) references mail_list (id),
  constraint mail_list_user_fk2 foreign key (user_account_id) references user_account (id)
);
create sequence sq_id_mail_list_user increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_mail_list_user 
before insert 
on mail_list_user
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_mail_list_user.nextval into :new.id from dual;
  end if;
end;
/

create table form (
  id number(10) not null,
  version number(10) not null,
  name varchar2(50) not null,
  description varchar2(255),
  title varchar2(255),
  image varchar2(255),
  email varchar2(255),
  instructions clob,
  acknowledge clob,
  webpage_id varchar2(255),
  mail_subject varchar2(255),
  mail_message clob,
  constraint form_pk primary key (id)
);
create sequence sq_id_form increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_form 
before insert 
on form
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_form.nextval into :new.id from dual;
  end if;
end;
/

create table form_item (
  id number(10) not null,
  version number(10) not null,
  type varchar2(50) not null,
  name varchar2(50) not null,
  text clob,
  help varchar2(255),
  default_value varchar2(50),
  item_size varchar2(3),
  maxlength varchar2(4),
  list_order number(10) not null,
  in_mail_address number(1) not null check (in_mail_address in (0, 1)),
  mail_list_id number(10),
  form_id number(10) not null,
  constraint form_item_pk primary key (id),
  constraint form_item_fk1 foreign key (mail_list_id) references mail_list (id),
  constraint form_item_fk2 foreign key (form_id) references form (id)
);
create sequence sq_id_form_item increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_form_item 
before insert 
on form_item
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_form_item.nextval into :new.id from dual;
  end if;
end;
/

create table form_item_value (
  id number(10) not null,
  version number(10) not null,
  value varchar2(50),
  text clob,
  form_item_id number(10) not null,
  constraint form_item_value_pk primary key (id),
  constraint form_item_value_fk1 foreign key (form_item_id) references form_item (id)
);
create sequence sq_id_form_item_value increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_form_item_value 
before insert 
on form_item_value
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_form_item_value.nextval into :new.id from dual;
  end if;
end;
/

create table form_valid (
  id number(10) not null,
  version number(10) not null,
  type varchar2(30) not null,
  message clob,
  boundary varchar2(20),
  form_item_id number(10) not null,
  constraint form_valid_pk primary key (id),
  constraint form_valid_fk1 foreign key (form_item_id) references form_item (id)
);
create sequence sq_id_form_valid increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_form_valid 
before insert 
on form_valid
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_form_valid.nextval into :new.id from dual;
  end if;
end;
/

create table guestbook_entry (
  id number(10) not null,
  version number(10) not null,
  body clob not null,
  user_account_id number(10),
  email varchar2(255),
  firstname varchar2(255),
  lastname varchar2(255),
  publication_datetime date not null,
  constraint guestbook_entry_pk primary key (id),
  constraint guestbook_entry_fk1 foreign key (user_account_id) references user_account (id)
);
create sequence sq_id_guestbook_entry increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_guestbook_entry 
before insert 
on guestbook_entry
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_guestbook_entry.nextval into :new.id from dual;
  end if;
end;
/

create table lexicon_entry (
  id number(10) not null,
  version number(10) not null,
  name varchar2(255) not null,
  explanation clob,
  image varchar2(255),
  constraint lexicon_entry_pk primary key (id)
);
create sequence sq_id_lexicon_entry increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_lexicon_entry 
before insert 
on lexicon_entry
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_lexicon_entry.nextval into :new.id from dual;
  end if;
end;
/

create table link_category (
  id number(10) not null,
  version number(10) not null,
  name varchar2(50) not null,
  description varchar2(255),
  list_order number(10) not null,
  constraint link_category_pk primary key (id)
);
create sequence sq_id_link_category increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_link_category 
before insert 
on link_category
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_link_category.nextval into :new.id from dual;
  end if;
end;
/

create table link (
  id number(10) not null,
  version number(10) not null,
  name varchar2(50),
  description varchar2(255),
  image varchar2(255),
  url varchar2(255),
  category_id number(10),
  list_order number(10) not null,
  constraint link_pk primary key (id),
  constraint link_fk1 foreign key (category_id) references link_category (id)
);
create sequence sq_id_link increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_link 
before insert 
on link
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_link.nextval into :new.id from dual;
  end if;
end;
/

create table navbar (
  id number(10) not null,
  version number(10) not null,
  hide number(1) not null check (hide in (0, 1)),
  constraint navbar_pk primary key (id)
);
create sequence sq_id_navbar increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_navbar 
before insert 
on navbar
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_navbar.nextval into :new.id from dual;
  end if;
end;
/

create table navbar_language (
  id number(10) not null,
  version number(10) not null,
  language_code varchar2(2),
  navbar_id number(10) not null,
  constraint navbar_language_pk primary key (id),
  constraint navbar_language_fk1 foreign key (navbar_id) references navbar (id)
);
create sequence sq_id_navbar_language increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_navbar_language 
before insert 
on navbar_language
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_navbar_language.nextval into :new.id from dual;
  end if;
end;
/

create table navbar_item (
  id number(10) not null,
  version number(10) not null,
  name varchar2(50),
  image varchar2(255),
  image_over varchar2(50),
  url varchar2(255),
  blank_target number(1) not null check (blank_target in (0, 1)),
  description varchar2(255),
  hide number(1) not null check (hide in (0, 1)),
  template_model_id number(10),
  list_order number(10) not null,
  navbar_language_id number(10) not null,
  constraint navbar_item_pk primary key (id),
  constraint navbar_item_fk1 foreign key (template_model_id) references template_model (id),
  constraint navbar_item_fk2 foreign key (navbar_language_id) references navbar_language (id)
);
create sequence sq_id_navbar_item increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_navbar_item 
before insert 
on navbar_item
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_navbar_item.nextval into :new.id from dual;
  end if;
end;
/

create table navlink (
  id number(10) not null,
  version number(10) not null,
  constraint navlink_pk primary key (id)
);
create sequence sq_id_navlink increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_navlink 
before insert 
on navlink
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_navlink.nextval into :new.id from dual;
  end if;
end;
/

create table navlink_item (
  id number(10) not null,
  version number(10) not null,
  name varchar2(255),
  description varchar2(255),
  image varchar2(255),
  image_over varchar2(50),
  url varchar2(255),
  blank_target number(1) not null check (blank_target in (0, 1)),
  language_code varchar2(2),
  template_model_id number(10),
  navlink_id number(10) not null,
  constraint navlink_item_pk primary key (id),
  constraint navlink_item_fk1 foreign key (template_model_id) references template_model (id),
  constraint navlink_item_fk2 foreign key (navlink_id) references navlink (id)
);
create sequence sq_id_navlink_item increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_navlink_item 
before insert 
on navlink_item
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_navlink_item.nextval into :new.id from dual;
  end if;
end;
/

create table navmenu (
  id number(10) not null,
  version number(10) not null,
  hide number(1) not null check (hide in (0, 1)),
  constraint navmenu_pk primary key (id)
);
create sequence sq_id_navmenu increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_navmenu 
before insert 
on navmenu
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_navmenu.nextval into :new.id from dual;
  end if;
end;
/

create table navmenu_item (
  id number(10) not null,
  version number(10) not null,
  name varchar2(50),
  image varchar2(255),
  image_over varchar2(50),
  url varchar2(255),
  blank_target number(1) not null check (blank_target in (0, 1)),
  description varchar2(255),
  hide number(1) not null check (hide in (0, 1)),
  template_model_id number(10),
  list_order number(10) not null,
  parent_id number(10),
  constraint navmenu_item_pk primary key (id),
  constraint navmenu_item_fk1 foreign key (template_model_id) references template_model (id),
  constraint navmenu_item_fk2 foreign key (parent_id) references navmenu_item (id)
);
create sequence sq_id_navmenu_item increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_navmenu_item 
before insert 
on navmenu_item
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_navmenu_item.nextval into :new.id from dual;
  end if;
end;
/

create table navmenu_language (
  id number(10) not null,
  version number(10) not null,
  language_code varchar2(2),
  navmenu_id number(10) not null,
  navmenu_item_id number(10) not null,
  constraint navmenu_language_pk primary key (id),
  constraint navmenu_language_fk1 foreign key (navmenu_id) references navmenu (id),
  constraint navmenu_language_fk2 foreign key (navmenu_item_id) references navmenu_item (id)
);
create sequence sq_id_navmenu_language increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_navmenu_language 
before insert 
on navmenu_language
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_navmenu_language.nextval into :new.id from dual;
  end if;
end;
/

create table webpage (
  id number(10) not null,
  version number(10) not null,
  name varchar2(50) not null,
  description varchar2(255),
  content clob,
  hide number(1) not null check (hide in (0, 1)),
  garbage number(1) not null check (garbage in (0, 1)),
  list_order number(10) not null,
  secured number(1) not null check (secured in (0, 1)),
  parent_id number(10),
  admin_id number(10),
  constraint webpage_pk primary key (id),
  constraint webpage_fk1 foreign key (parent_id) references webpage (id),
  constraint webpage_fk2 foreign key (admin_id) references admin (id)
);
create sequence sq_id_webpage increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_webpage 
before insert 
on webpage
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_webpage.nextval into :new.id from dual;
  end if;
end;
/

create table webpage_navmenu (
  id number(10) not null,
  version number(10) not null,
  parent_id number(10),
  constraint webpage_navmenu_pk primary key (id)
);
create sequence sq_id_webpage_navmenu increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_webpage_navmenu 
before insert 
on webpage_navmenu
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_webpage_navmenu.nextval into :new.id from dual;
  end if;
end;
/

create table news_publication (
  id number(10) not null,
  version number(10) not null,
  name varchar2(255) not null,
  description varchar2(255),
  nb_columns number(2),
  slide_down number(1) not null check (slide_down in (0, 1)),
  align varchar2(10),
  with_archive number(1) not null check (with_archive in (0, 1)),
  with_others number(1) not null check (with_others in (0, 1)),
  with_by_heading number(1) not null check (with_by_heading in (0, 1)),
  hide_heading number(1) not null check (hide_heading in (0, 1)),
  auto_archive number(3),
  auto_delete number(3),
  secured number(1) not null check (secured in (0, 1)),
  constraint news_publication_pk primary key (id)
);
create sequence sq_id_news_publication increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_news_publication 
before insert 
on news_publication
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_news_publication.nextval into :new.id from dual;
  end if;
end;
/

create table news_editor (
  id number(10) not null,
  version number(10) not null,
  admin_id number(10) not null,
  constraint news_editor_u1 unique (admin_id),
  constraint news_editor_pk primary key (id),
  constraint news_editor_fk1 foreign key (admin_id) references admin (id)
);
create sequence sq_id_news_editor increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_news_editor 
before insert 
on news_editor
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_news_editor.nextval into :new.id from dual;
  end if;
end;
/

create table news_heading (
  id number(10) not null,
  version number(10) not null,
  list_order number(10) not null,
  name varchar2(50) not null,
  description varchar2(255),
  image varchar2(255),
  news_publication_id number(10),
  constraint news_heading_pk primary key (id),
  constraint news_heading_fk1 foreign key (news_publication_id) references news_publication (id)
);
create sequence sq_id_news_heading increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_news_heading 
before insert 
on news_heading
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_news_heading.nextval into :new.id from dual;
  end if;
end;
/

create table news_paper (
  id number(10) not null,
  version number(10) not null,
  title varchar2(255) not null,
  image varchar2(255),
  header clob,
  footer clob,
  release_date date,
  archive_date date,
  not_published number(1) not null check (not_published in (0, 1)),
  news_publication_id number(10),
  constraint news_paper_pk primary key (id),
  constraint news_paper_fk1 foreign key (news_publication_id) references news_publication (id)
);
create sequence sq_id_news_paper increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_news_paper 
before insert 
on news_paper
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_news_paper.nextval into :new.id from dual;
  end if;
end;
/

create table news_story (
  id number(10) not null,
  version number(10) not null,
  headline varchar2(255) not null,
  excerpt clob,
  audio varchar2(255),
  audio_url varchar2(255),
  link varchar2(255),
  release_date date,
  archive_date date,
  event_start_date date,
  event_end_date date,
  news_editor_id number(10),
  news_paper_id number(10) not null,
  news_heading_id number(10),
  list_order number(10) not null,
  constraint news_story_pk primary key (id),
  constraint news_story_fk1 foreign key (news_editor_id) references news_editor (id),
  constraint news_story_fk2 foreign key (news_paper_id) references news_paper (id),
  constraint news_story_fk3 foreign key (news_heading_id) references news_heading (id)
);
create sequence sq_id_news_story increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_news_story 
before insert 
on news_story
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_news_story.nextval into :new.id from dual;
  end if;
end;
/

create table news_story_image (
  id number(10) not null,
  version number(10) not null,
  image varchar2(255),
  description varchar2(255),
  list_order number(10) not null,
  news_story_id number(10),
  constraint news_story_image_pk primary key (id),
  constraint news_story_image_fk1 foreign key (news_story_id) references news_story (id)
);
create sequence sq_id_news_story_image increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_news_story_image 
before insert 
on news_story_image
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_news_story_image.nextval into :new.id from dual;
  end if;
end;
/

create table news_story_paragraph (
  id number(10) not null,
  version number(10) not null,
  header clob,
  body clob,
  footer clob,
  news_story_id number(10),
  constraint news_story_paragraph_pk primary key (id),
  constraint news_story_paragraph_fk1 foreign key (news_story_id) references news_story (id)
);
create sequence sq_id_news_story_paragraph increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_news_story_paragraph 
before insert 
on news_story_paragraph
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_news_story_paragraph.nextval into :new.id from dual;
  end if;
end;
/

create table news_feed (
  id number(10) not null,
  version number(10) not null,
  news_paper_id number(10),
  image varchar2(255),
  max_display_number number(10),
  image_align varchar2(10),
  image_width number(10),
  with_excerpt number(1) not null check (with_excerpt in (0, 1)),
  with_image number(1) not null check (with_image in (0, 1)),
  search_options number(1) not null check (search_options in (0, 1)),
  search_calendar number(1) not null check (search_calendar in (0, 1)),
  display_upcoming number(1) not null check (display_upcoming in (0, 1)),
  search_title varchar2(255),
  search_display_as_page number(1) not null check (search_display_as_page in (0, 1)),
  constraint news_feed_pk primary key (id)
);
create sequence sq_id_news_feed increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_news_feed 
before insert 
on news_feed
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_news_feed.nextval into :new.id from dual;
  end if;
end;
/

create table people_category (
  id number(10) not null,
  version number(10) not null,
  name varchar2(50) not null,
  description varchar2(255),
  list_order number(10) not null,
  constraint people_category_pk primary key (id)
);
create sequence sq_id_people_category increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_people_category 
before insert 
on people_category
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_people_category.nextval into :new.id from dual;
  end if;
end;
/

create table people (
  id number(10) not null,
  version number(10) not null,
  firstname varchar2(255),
  lastname varchar2(255),
  email varchar2(255),
  work_phone varchar2(20),
  mobile_phone varchar2(20),
  profile clob,
  image varchar2(255),
  category_id number(10),
  list_order number(10) not null,
  constraint people_pk primary key (id),
  constraint people_fk1 foreign key (category_id) references people_category (id)
);
create sequence sq_id_people increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_people 
before insert 
on people
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_people.nextval into :new.id from dual;
  end if;
end;
/

create table photo_album (
  id number(10) not null,
  version number(10) not null,
  name varchar2(50) not null,
  folder_name varchar2(50) not null,
  event varchar2(255),
  location varchar2(255),
  publication_date date,
  price number(10),
  hide number(1) not null check (hide in (0, 1)),
  no_slide_show number(1) not null check (no_slide_show in (0, 1)),
  no_zoom number(1) not null check (no_zoom in (0, 1)),
  list_order number(10) not null,
  constraint photo_album_pk primary key (id)
);
create sequence sq_id_photo_album increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_photo_album 
before insert 
on photo_album
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_photo_album.nextval into :new.id from dual;
  end if;
end;
/

create table photo_format (
  id number(10) not null,
  version number(10) not null,
  name varchar2(50) not null,
  description varchar2(255),
  price number(10),
  constraint photo_format_pk primary key (id)
);
create sequence sq_id_photo_format increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_photo_format 
before insert 
on photo_format
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_photo_format.nextval into :new.id from dual;
  end if;
end;
/

create table photo_album_format (
  id number(10) not null,
  version number(10) not null,
  photo_album_id number(10) not null,
  photo_format_id number(10) not null,
  price number(10) not null,
  constraint photo_album_format_pk primary key (id),
  constraint photo_album_format_fk1 foreign key (photo_album_id) references photo_album (id),
  constraint photo_album_format_fk2 foreign key (photo_format_id) references photo_format (id)
);
create sequence sq_id_photo_album_format increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_photo_album_format 
before insert 
on photo_album_format
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_photo_album_format.nextval into :new.id from dual;
  end if;
end;
/

create table photo (
  id number(10) not null,
  version number(10) not null,
  reference varchar2(50),
  name varchar2(50),
  description varchar2(255),
  tags varchar2(255),
  text_comment clob,
  image varchar2(255),
  url varchar2(255),
  price number(10),
  photo_album_id number(10),
  photo_format_id number(10),
  list_order number(10) not null,
  constraint photo_pk primary key (id),
  constraint photo_fk1 foreign key (photo_album_id) references photo_album (id),
  constraint photo_fk2 foreign key (photo_format_id) references photo_format (id)
);
create sequence sq_id_photo increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_photo 
before insert 
on photo
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_photo.nextval into :new.id from dual;
  end if;
end;
/

create table rss_feed (
  id number(10) not null,
  version number(10) not null,
  constraint rss_feed_pk primary key (id)
);
create sequence sq_id_rss_feed increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_rss_feed 
before insert 
on rss_feed
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_rss_feed.nextval into :new.id from dual;
  end if;
end;
/

create table rss_feed_language (
  id number(10) not null,
  version number(10) not null,
  language_code varchar2(2),
  title varchar2(50),
  url varchar2(255),
  rss_feed_id number(10) not null,
  constraint rss_feed_language_pk primary key (id),
  constraint rss_feed_language_fk1 foreign key (rss_feed_id) references rss_feed (id)
);
create sequence sq_id_rss_feed_language increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_rss_feed_language 
before insert 
on rss_feed_language
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_rss_feed_language.nextval into :new.id from dual;
  end if;
end;
/

create table shop_affiliate (
  id number(10) not null,
  version number(10) not null,
  user_account_id number(10) not null,
  constraint shop_affiliate_pk primary key (id),
  constraint shop_affiliate_fk1 foreign key (user_account_id) references user_account (id)
);
create sequence sq_id_shop_affiliate increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_shop_affiliate 
before insert 
on shop_affiliate
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_shop_affiliate.nextval into :new.id from dual;
  end if;
end;
/

create table shop_category (
  id number(10) not null,
  version number(10) not null,
  name varchar2(50) not null,
  description varchar2(255),
  list_order number(10) not null,
  parent_id number(10),
  constraint shop_category_pk primary key (id),
  constraint shop_category_fk1 foreign key (parent_id) references shop_category (id)
);
create sequence sq_id_shop_category increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_shop_category 
before insert 
on shop_category
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_shop_category.nextval into :new.id from dual;
  end if;
end;
/

create table shop_item (
  id number(10) not null,
  version number(10) not null,
  name varchar2(50) not null,
  short_description varchar2(255),
  long_description clob,
  reference varchar2(30),
  weight varchar2(3),
  price varchar2(12),
  vat_rate varchar2(5),
  shipping_fee varchar2(10),
  category_id number(10),
  url varchar2(255),
  list_order number(10) not null,
  hide number(1) not null check (hide in (0, 1)),
  added date not null,
  last_modified date not null,
  available date not null,
  constraint shop_item_pk primary key (id),
  constraint shop_item_fk1 foreign key (category_id) references shop_category (id)
);
create sequence sq_id_shop_item increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_shop_item 
before insert 
on shop_item
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_shop_item.nextval into :new.id from dual;
  end if;
end;
/

create table shop_item_image (
  id number(10) not null,
  version number(10) not null,
  image varchar2(255),
  description varchar2(255),
  list_order number(10) not null,
  shop_item_id number(10) not null,
  constraint shop_item_image_pk primary key (id),
  constraint shop_item_image_fk1 foreign key (shop_item_id) references shop_item (id)
);
create sequence sq_id_shop_item_image increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_shop_item_image 
before insert 
on shop_item_image
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_shop_item_image.nextval into :new.id from dual;
  end if;
end;
/

create table shop_order (
  id number(10) not null,
  version number(10) not null,
  firstname varchar2(255) not null,
  lastname varchar2(255) not null,
  organisation varchar2(255),
  vat_number varchar2(50),
  email varchar2(255) not null,
  telephone varchar2(20),
  mobile_phone varchar2(20),
  fax varchar2(20),
  message clob,
  handling_fee number(10),
  discount_code varchar2(12),
  discount_amount varchar2(10),
  currency varchar2(3) not null,
  invoice_number varchar2(50),
  invoice_note varchar2(1024),
  invoice_language_code varchar2(2),
  invoice_address_id number(10) not null,
  shipping_address_id number(10),
  order_date date not null,
  due_date date not null,
  client_ip varchar2(20) not null,
  status varchar2(10) not null,
  payment_type varchar2(10) not null,
  payment_transaction_id varchar2(50),
  user_account_id number(10),
  constraint shop_order_pk primary key (id),
  constraint shop_order_fk1 foreign key (invoice_address_id) references address (id),
  constraint shop_order_fk2 foreign key (shipping_address_id) references address (id)
);
create sequence sq_id_shop_order increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_shop_order 
before insert 
on shop_order
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_shop_order.nextval into :new.id from dual;
  end if;
end;
/

create table shop_order_item (
  id number(10) not null,
  version number(10) not null,
  name varchar2(50),
  short_description varchar2(255),
  reference varchar2(30),
  price varchar2(12) not null,
  vat_rate varchar2(5),
  shipping_fee varchar2(10),
  quantity number(2) not null,
  is_gift number(1) not null check (is_gift in (0, 1)),
  options varchar2(255),
  shop_order_id number(10) not null,
  shop_item_id number(10),
  image_url varchar2(255),
  constraint shop_order_item_pk primary key (id),
  constraint shop_order_item_fk1 foreign key (shop_order_id) references shop_order (id),
  constraint shop_order_item_fk2 foreign key (shop_item_id) references shop_item (id)
);
create sequence sq_id_shop_order_item increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_shop_order_item 
before insert 
on shop_order_item
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_shop_order_item.nextval into :new.id from dual;
  end if;
end;
/

create table shop_discount (
  id number(10) not null,
  version number(10) not null,
  discount_code varchar2(12) not null,
  discount_rate varchar2(5) not null,
  shop_affiliate_id number(10) not null,
  constraint shop_discount_pk primary key (id),
  constraint shop_discount_fk1 foreign key (shop_affiliate_id) references shop_affiliate (id)
);
create sequence sq_id_shop_discount increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_shop_discount 
before insert 
on shop_discount
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_shop_discount.nextval into :new.id from dual;
  end if;
end;
/

create table sms_category (
  id number(10) not null,
  version number(10) not null,
  name varchar2(50) not null,
  description varchar2(255),
  constraint sms_category_pk primary key (id)
);
create sequence sq_id_sms_category increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_sms_category 
before insert 
on sms_category
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_sms_category.nextval into :new.id from dual;
  end if;
end;
/

create table sms (
  id number(10) not null,
  version number(10) not null,
  body clob not null,
  description varchar2(255),
  admin_id number(10),
  category_id number(10),
  constraint sms_pk primary key (id),
  constraint sms_fk1 foreign key (admin_id) references admin (id),
  constraint sms_fk2 foreign key (category_id) references sms_category (id)
);
create sequence sq_id_sms increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_sms 
before insert 
on sms
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_sms.nextval into :new.id from dual;
  end if;
end;
/

create table sms_list (
  id number(10) not null,
  version number(10) not null,
  name varchar2(255) not null,
  description varchar2(255),
  auto_subscribe number(1) not null check (auto_subscribe in (0, 1)),
  constraint sms_list_pk primary key (id)
);
create sequence sq_id_sms_list increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_sms_list 
before insert 
on sms_list
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_sms_list.nextval into :new.id from dual;
  end if;
end;
/

create table sms_history (
  id number(10) not null,
  version number(10) not null,
  sms_id number(10) not null,
  sms_list_id number(10),
  mobile_phone varchar2(50),
  admin_id number(10),
  send_datetime date,
  nb_recipients number(10),
  constraint sms_history_pk primary key (id),
  constraint sms_history_fk1 foreign key (sms_id) references sms (id),
  constraint sms_history_fk2 foreign key (sms_list_id) references sms_list (id),
  constraint sms_history_fk3 foreign key (admin_id) references admin (id)
);
create sequence sq_id_sms_history increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_sms_history 
before insert 
on sms_history
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_sms_history.nextval into :new.id from dual;
  end if;
end;
/

create table sms_outbox (
  id number(10) not null,
  version number(10) not null,
  firstname varchar2(255),
  lastname varchar2(255),
  mobile_phone varchar2(20) not null,
  email varchar2(255),
  password varchar2(20),
  sent number(1) not null check (sent in (0, 1)),
  constraint sms_outbox_pk primary key (id)
);
create sequence sq_id_sms_outbox increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_sms_outbox 
before insert 
on sms_outbox
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_sms_outbox.nextval into :new.id from dual;
  end if;
end;
/

create table sms_number (
  id number(10) not null,
  version number(10) not null,
  firstname varchar2(255),
  lastname varchar2(255),
  mobile_phone varchar2(20) not null,
  subscribe number(1) not null check (subscribe in (0, 1)),
  imported number(1) not null check (imported in (0, 1)),
  constraint sms_number_pk primary key (id)
);
create sequence sq_id_sms_number increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_sms_number 
before insert 
on sms_number
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_sms_number.nextval into :new.id from dual;
  end if;
end;
/

create table sms_list_number (
  id number(10) not null,
  version number(10) not null,
  sms_list_id number(10) not null,
  sms_number_id number(10) not null,
  constraint sms_list_number_pk primary key (id),
  constraint sms_list_number_fk1 foreign key (sms_list_id) references sms_list (id),
  constraint sms_list_number_fk2 foreign key (sms_number_id) references sms_number (id)
);
create sequence sq_id_sms_list_number increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_sms_list_number 
before insert 
on sms_list_number
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_sms_list_number.nextval into :new.id from dual;
  end if;
end;
/

create table sms_list_user (
  id number(10) not null,
  version number(10) not null,
  sms_list_id number(10) not null,
  user_account_id number(10) not null,
  constraint sms_list_user_pk primary key (id),
  constraint sms_list_user_fk1 foreign key (sms_list_id) references sms_list (id),
  constraint sms_list_user_fk2 foreign key (user_account_id) references user_account (id)
);
create sequence sq_id_sms_list_user increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_sms_list_user 
before insert 
on sms_list_user
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_sms_list_user.nextval into :new.id from dual;
  end if;
end;
/

create table statistics_referer (
  id number(10) not null,
  version number(10) not null,
  name varchar2(50),
  description varchar2(255),
  url varchar2(255) not null,
  constraint statistics_referer_pk primary key (id)
);
create sequence sq_id_statistics_referer increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_statistics_referer 
before insert 
on statistics_referer
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_statistics_referer.nextval into :new.id from dual;
  end if;
end;
/

create table statistics_page (
  id number(10) not null,
  version number(10) not null,
  page varchar2(255) not null,
  hits number(10) not null,
  month number(10) not null,
  year number(10) not null,
  constraint statistics_page_u1 unique (page, month, year),
  constraint statistics_page_pk primary key (id)
);
create sequence sq_id_statistics_page increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_statistics_page 
before insert 
on statistics_page
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_statistics_page.nextval into :new.id from dual;
  end if;
end;
/

create table statistics_visit (
  id number(10) not null,
  version number(10) not null,
  visit_datetime date not null,
  visitor_host_address varchar2(255) not null,
  visitor_browser varchar2(255) not null,
  visitor_referer varchar2(255) not null,
  constraint statistics_visit_pk primary key (id)
);
create sequence sq_id_statistics_visit increment by 1 start with 1 nomaxvalue nocycle cache 10;
create or replace trigger tr_id_inc_statistics_visit 
before insert 
on statistics_visit
for each row
declare
begin
  if (:new.id is null)
  then
    select sq_id_statistics_visit.nextval into :new.id from dual;
  end if;
end;
/

quit

