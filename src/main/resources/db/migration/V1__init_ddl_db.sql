# Roles table
CREATE TABLE role (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    role VARCHAR(30) NOT NULL,
    PRIMARY KEY (id)
);

# Access Type table
CREATE TABLE access_type (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    type VARCHAR(30) NOT NULL,
    PRIMARY KEY (id)
);

# Department Table
CREATE TABLE department (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

# Employee Table
CREATE TABLE employee (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    employee_code VARCHAR(25) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    department_id BIGINT(20) NOT NULL,
    PRIMARY KEY (id)
);

# User Table
CREATE TABLE user (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(250) NOT NULL,
    employee_id BIGINT(20) NOT NULL,
    role_id BIGINT(20) NOT NULL ,
    PRIMARY KEY (id)
);

# Access Table
CREATE TABLE access (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    user_id BIGINT(20) NOT NULL,
    access_type_id BIGINT(20) NOT NULL,
    created_at TIMESTAMP,
    PRIMARY KEY (id)
);

# Foreign keys
ALTER TABLE employee
    ADD FOREIGN KEY (department_id) REFERENCES department(id);
ALTER TABLE user
    ADD FOREIGN KEY (employee_id) REFERENCES employee(id);
ALTER TABLE user
    ADD FOREIGN KEY (role_id) REFERENCES role(id);
ALTER TABLE access
    ADD FOREIGN KEY (user_id) REFERENCES user(id);
ALTER TABLE access
    ADD FOREIGN KEY (access_type_id) REFERENCES access_type(id);