CREATE TYPE unit_of_measure AS ENUM (
    'KILOGRAMS',
    'METERS',
    'MILLILITERS',
    'MILLIGRAMS'
);

CREATE TYPE color AS ENUM (
    'GREEN',
    'RED',
    'BLACK',
    'YELLOW',
    'ORANGE',
    'WHITE',
    'BROWN'
);

CREATE TABLE Person
(
    passportID VARCHAR(44) PRIMARY KEY,
    name       VARCHAR NOT NULL CHECK (name <> ''),
    eyeColor   color,
    hairColor  color
);

CREATE TABLE Product
(
    id              BIGSERIAL PRIMARY KEY,
    name            VARCHAR                  NOT NULL CHECK (name <> ''),
    coordinates     POINT                    NOT NULL,
    creationDate    DATE                     NOT NULL,
    price           BIGINT CHECK (price > 0) NOT NULL,
    partNumber      VARCHAR CHECK (LENGTH(partNumber) >= 10 OR partNumber IS NULL),
    manufacturerCost BIGINT                   NOT NULL,
    unitOfMeasure   unit_of_measure          NOT NULL,
    ownerPassportID VARCHAR(44),
    FOREIGN KEY (ownerPassportID) REFERENCES Person (passportID)
);

ALTER TABLE Product
    ADD CONSTRAINT unique_partNumber UNIQUE (partNumber);
