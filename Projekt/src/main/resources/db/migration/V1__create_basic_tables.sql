create table if not exists users
(
    id              UUID primary key,
    name            VARCHAR(200),
    surname         VARCHAR(200),
    gender          VARCHAR(20),
    email           VARCHAR(200),
    phone_number    VARCHAR(200),
    password        VARCHAR(200),
    street          VARCHAR(200),
    street_number   VARCHAR(200),
    city            VARCHAR(200),
    postal_code     VARCHAR(200),
    country_code    VARCHAR(200),
    active          BOOLEAN default true,
    confirmed       BOOLEAN default false,
    created_at      TIMESTAMP
);

create table if not exists items
(
    id              UUID primary key,
    name            VARCHAR(200),
    category        VARCHAR(200),
    description     TEXT,
    active          BOOLEAN default true,
    created_at      TIMESTAMP
);

create table if not exists orders
(
    id              UUID primary key,
    items           TEXT,
    status          VARCHAR(50),
    address         VARCHAR(200),
    invoice_address VARCHAR(200),
    special_notes   VARCHAR(200),
    reference       VARCHAR(200),
    created_at      TIMESTAMP
);
