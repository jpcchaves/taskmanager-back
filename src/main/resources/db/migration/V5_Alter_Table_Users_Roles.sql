alter table users_roles
       foreign key (role_id);
       references roles (id);
       foreign key (user_id);
       references users (id);