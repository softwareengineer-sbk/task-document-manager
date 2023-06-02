CREATE TABLE IF NOT EXISTS documents (
    id string PRIMARY KEY,
    name varchar,
    size int,
    data bytea
);