CREATE TABLE IF NOT EXISTS weather
(
    id                BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY UNIQUE,
    locality          VARCHAR(200) NOT NULL,
    localityProvince  VARCHAR(200) NOT NULL,
    dateTime          VARCHAR(200) NOT NULL,
    temp              NUMERIC      NOT NULL,
    windSpeed         NUMERIC      NOT NULL,
    windDir           VARCHAR(20)  NOT NULL,
    pressure          NUMERIC      NOT NULL,
    humidity          NUMERIC      NOT NULL
    );

CREATE TABLE IF NOT EXISTS user
(
    id                BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY UNIQUE,
    login             VARCHAR(50) NOT NULL,
    email             VARCHAR(100) NOT NULL,
    password          VARCHAR(20) NOT NULL
    );