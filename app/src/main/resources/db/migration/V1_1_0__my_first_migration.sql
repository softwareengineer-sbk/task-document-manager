CREATE TABLE documents(
    id varchar PRIMARY KEY,
    name varchar NOT NULL,
    size int,
    data bytea);
