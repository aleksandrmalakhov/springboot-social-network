delete
from messages;

insert into messages(id, text, tag, user_id)
values (1, 'first', 'my-tag', 1),
       (2, 'second', 'more', 1),
       (3, 'third', 'my-tag', 1),
       (4, 'fourth', 'another', 1);

alter table messages AUTO_INCREMENT=10;