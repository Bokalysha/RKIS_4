CREATE DATABASE rkis_4;

\c rkis_4

CREATE TABLE IF NOT EXISTS phones
(
    id                  SERIAL PRIMARY KEY,
    brand               TEXT,
    model               TEXT,
    operating_system    TEXT,
    storage_capacity_gb INT,
    price_usd           NUMERIC(10, 2)
);