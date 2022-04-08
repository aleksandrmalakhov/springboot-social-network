delete
from user_role;
delete
from usr;

insert into usr (id, username, password, active)
values (1, 'admin', '$2a$08$8Z3Yte/ua6Kb0I70hHRsHuLeL.2YxA.SfY4SmeB/YRiy5xrJCHd4y', true),
       (2, 'user', '$2a$08$RHU2nF2lQZfFe2m2UfsEgeWpp2zBWpP7hZQ.lxXLjNKRLm.7FZYmS', true);

insert into user_role (user_id, roles)
values (1, 'USER'),
       (1, 'ADMIN'),
       (2, 'USER');