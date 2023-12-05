ALTER TABLE car ADD COLUMN owner_id int unique REFERENCES owners(id);
