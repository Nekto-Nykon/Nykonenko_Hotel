

-- Create the "roles" table
CREATE TABLE IF NOT EXISTS roles (
                                           id SERIAL PRIMARY KEY,
                                           name VARCHAR(255)UNIQUE NOT NULL
    );
-- Create the "users" table
CREATE TABLE IF NOT EXISTS users (
                                           id SERIAL PRIMARY KEY,
                                           name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role_id INTEGER,
    CONSTRAINT fk_role_id FOREIGN KEY (role_id) REFERENCES roles(id)
    );

-- Create the "rooms" table
CREATE TABLE IF NOT EXISTS rooms (
                                           id SERIAL PRIMARY KEY,
                                           name VARCHAR(255) NOT NULL,
    comfort_level  VARCHAR(255) NOT NULL,
    price NUMERIC(10, 2) NOT NULL,
    adult_capacity INTEGER NOT NULL,
    child_capacity INTEGER NOT NULL
    );

-- Create the "orders" table
CREATE TABLE IF NOT EXISTS orders (
                                            id SERIAL PRIMARY KEY,
                                            id_account INTEGER NOT NULL,
                                            id_room INTEGER NOT NULL,
                                            check_in DATE NOT NULL,
                                            check_out DATE NOT NULL,
                                            status VARCHAR(255) NOT NULL,
                                            adult_capacity_requirement INTEGER NOT NULL,
                                            child_capacity_requirement INTEGER NOT NULL,
                                            comfort_level_requirement VARCHAR(255) NOT NULL
);

-- Create an index on the email column in the "users" table for faster lookups (optional)
CREATE INDEX idx_users_email ON users (email);