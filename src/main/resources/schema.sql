CREATE TABLE IF NOT EXISTS position (
  id INT NOT NULL,
  title VARCHAR(50) NOT NULL,
PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS employee (
  id INT NOT NULL,
  name VARCHAR(50) NOT NULL,
  dob DATE,
  phone VARCHAR (20),
  position_id INT,
  salary REAl,
  PRIMARY KEY (id),
  FOREIGN KEY (position_id)
  REFERENCES "position" (id)
  );

CREATE TABLE IF NOT EXISTS ingredient (
  id INT NOT NULL,
  ingredient_name VARCHAR(50) NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS dish (
  id INT NOT NULL,
  dish_name VARCHAR(50) NOT NULL,
  category VARCHAR (50),
  price REAL,
  weight REAL,
  PRIMARY KEY (id)
  );

CREATE TABLE IF NOT EXISTS dish_ingredient (
  id INT NOT NULL,
  dish_id int,
  ingredient_id int,
  PRIMARY KEY (id),
  FOREIGN KEY (dish_id)
  REFERENCES "dish" (id),
  FOREIGN KEY (ingredient_id)
  REFERENCES "ingredient" (id)
  );

CREATE TABLE IF NOT EXISTS some_order (
  id INT NOT NULL,
  order_date TIMESTAMP,
  table_number int,
  employee_id int,
  dish_id int,
  PRIMARY KEY (id),
  FOREIGN KEY (employee_id)
  REFERENCES "employee" (id),
  FOREIGN KEY (dish_id)
  REFERENCES "dish" (id)
  );

CREATE TABLE IF NOT EXISTS menu (
  id INT NOT NULL,
  menu_name VARCHAR(50) NOT NULL,
  dish_id int,
  PRIMARY KEY (id),
  FOREIGN KEY (dish_id)
  REFERENCES "dish" (id)
  );

CREATE TABLE IF NOT EXISTS store_house (
  id INT NOT NULL,
  ingredient_id int NOT NULL,
  quantiity int,
  PRIMARY KEY (id),
  FOREIGN KEY (ingredient_id)
  REFERENCES "ingredient" (id)
  );

CREATE TABLE IF NOT EXISTS prepared_dishes (
  id INT NOT NULL,
  order_date TIMESTAMP,
  dish_id int,
  employee_id int,
  order_id int,
  PRIMARY KEY (id),
  FOREIGN KEY (dish_id)
  REFERENCES "dish" (id),
  FOREIGN KEY (employee_id)
  REFERENCES "employee" (id),
  FOREIGN KEY (order_id)
  REFERENCES "some_order" (id)
);