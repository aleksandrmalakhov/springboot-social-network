insert into usr (username, password, active)
values ('admin', '$2a$08$cYOFPfMthDosb90RxQmXGuMhVv9LUdO3Y67.1v1kQnF4VWyDR3Lc.', true);

insert into user_role (user_id, roles)
values (1, 'USER'),
       (1, 'ADMIN');
