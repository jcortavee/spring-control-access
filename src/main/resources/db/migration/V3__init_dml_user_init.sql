INSERT INTO department (id, name) VALUES (1, 'Gerencia');
INSERT INTO department (id, name) VALUES (2, 'Desarrollo');
INSERT INTO department (id, name) VALUES (3, 'Marketing');
INSERT INTO department (id, name) VALUES (4, 'Contabilidad');
INSERT INTO department (id, name) VALUES (5, 'Ventas');

INSERT INTO employee (id, employee_code, first_name, last_name, department_id) VALUES (1, 'USER001', 'John', 'Doe', 1);

INSERT INTO user (id, username, password, employee_id, role_id) VALUES (1, 'johndoe', '$2a$10$FbQ4Su1VhRzeigGrPgdeb.IgZH0xL8xw1EPPQi4OmIc5XrXpt/WQW', 1, 1);