ALTER TABLE owners DROP COLUMN user_id;

ALTER TABLE owners ADD COLUMN user_id int not null unique references auto_user(id);