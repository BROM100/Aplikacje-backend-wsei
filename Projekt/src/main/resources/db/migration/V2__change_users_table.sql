ALTER TABLE users RENAME COLUMN active TO enabled;
ALTER TABLE users ADD COLUMN username VARCHAR(200);
ALTER TABLE users ADD COLUMN role VARCHAR(200);
