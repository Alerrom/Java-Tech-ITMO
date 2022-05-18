drop table xt_user_roles_tx;
drop table xt_users;
drop table xt_roles;


create table xt_users
(
    id_user       serial
        constraint con_xt_users_pk primary key,
    user_email    text
        constraint con_xt_users_email not null,
    user_password text
        constraint con_xt_users_password not null,
    user_naim     text
        constraint con_xt_users_user_naim not null,
    constraint con_xt_users_user_email unique (user_email),
    constraint con_xt_users_user_naim unique (user_naim)
);

create table xt_roles
(
    id_role   serial
        constraint con_xt_roles_pk primary key,
    role_naim text
        constraint con_xt_roles_naim not null,
    constraint con_xt_roles_uni unique (role_naim)
);

create table xt_user_roles_tx
(
    id_user integer
        constraint xt_user_roles_tx_id_user_fk references xt_users (id_user),
    id_role integer
        constraint xt_user_roles_tx_id_role_fk references xt_roles (id_role),
    constraint con_xt_user_roles_tx_pk primary key (id_user, id_role)
);

insert into xt_users(user_email, user_password, user_naim)
select *
from (select 'user01@yandex.ru'                                             user_email,
             '$2a$10$AYel1955slMlC0KrUi/4eu4cjcH53xTStfwPGmnLM/0nIue6R0Fx.' user_pwd,
             'user01'                                                       user_naim
      union all
      select 'admin01@yandex.ru', '$2a$10$AYel1955slMlC0KrUi/4eu4cjcH53xTStfwPGmnLM/0nIue6R0Fx.', 'admin01') v;

insert into xt_roles(role_naim)
select *
from (select 'ADMIN' naim
      union all
      select 'USER') v;


insert into xt_user_roles_tx(id_user, id_role)
select u.id_user, r.id_role
from xt_users u
         join xt_roles r
              on u.user_naim like lower(role_naim) || '%';