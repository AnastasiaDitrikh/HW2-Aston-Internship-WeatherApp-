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