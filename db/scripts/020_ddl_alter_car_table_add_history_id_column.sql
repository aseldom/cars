ALTER TABLE car ADD COLUMN history_id int unique REFERENCES history(id);
