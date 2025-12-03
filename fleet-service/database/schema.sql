create database fleetDrivers;
CREATE TABLE drivers (
    id SERIAL PRIMARY KEY,
    full_name VARCHAR(150) NOT NULL,
    cpf CHAR(11) NOT NULL UNIQUE CHECK (cpf ~ '^[0-9]{11}$'),
    cnh_number CHAR(9) NOT NULL CHECK (cnh_number ~ '^[0-9]{9}$'),
    cnh_category varchar(20) check (cnh_category in ('A', 'B', 'C', 'D', 'E')),
    cnh_expires_date DATE NOT NULL,
    active boolean default true
);

create database fleetTrips;
CREATE TABLE trip (
    id SERIAL PRIMARY KEY,
    driver_id BIGINT NOT NULL,
    vehicle_id BIGINT NOT NULL,
    origin VARCHAR(150) NOT NULL,
    destination VARCHAR(150) NOT NULL,
    start_datetime TIMESTAMP NOT NULL,
    end_datetime TIMESTAMP NOT NULL,
    status VARCHAR(20) NOT NULL CHECK (
        status IN ('PLANNED', 'IN_PROGRESS', 'COMPLETED', 'CANCELED')
    )
);

create database fleetVehicles;
CREATE TABLE vehicle (
    id SERIAL PRIMARY KEY,
    license_plate VARCHAR(10) NOT NULL UNIQUE,
    model VARCHAR(100) NOT NULL,
    model_year INTEGER NOT NULL CHECK (model_year > 2000),
    status VARCHAR(20) CHECK (status IN ('A', 'B', 'C', 'D', 'E'))
);