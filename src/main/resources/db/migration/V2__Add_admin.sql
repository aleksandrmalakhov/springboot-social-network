insert into usr (username, password, active)
values ('admin', '$2a$10$ErssVH2gUU0E5pvzrxM1SOAdECb4zEZOdhCd5oXTx5Pw2CTUvKURy', true);

insert into user_role (user_id, roles)
values (1, 'USER'),
       (1, 'ADMIN');
