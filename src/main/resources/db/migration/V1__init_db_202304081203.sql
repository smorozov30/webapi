-- Таблица "Автомобиль"
CREATE TABLE car (
    id SERIAL PRIMARY KEY,
    make_id INT NOT NULL,
    model_id INT NOT NULL
);

-- Таблица "Марка"
CREATE TABLE make  (
    id SERIAL PRIMARY KEY,
    name VARCHAR (100) NOT NULL
);

-- Таблица "Модель"
CREATE TABLE model (
    id SERIAL PRIMARY KEY,
    name VARCHAR (100) NOT NULL
);

-- Связующая таблица "Марка-Модель"
CREATE TABLE make_model (
    make_id INT NOT NULL,
    model_id INT NOT NULL,
    PRIMARY KEY (make_id, model_id)
);

-- Создание ограничений на таблиц
ALTER TABLE car ADD CONSTRAINT fk_car_make_id FOREIGN KEY (make_id) REFERENCES make (id);
ALTER TABLE car ADD CONSTRAINT fk_car_model_id FOREIGN KEY (model_id) REFERENCES model (id);
ALTER TABLE make_model ADD CONSTRAINT fk_make_model_make_id FOREIGN KEY(make_id) REFERENCES make(id);
ALTER TABLE make_model ADD CONSTRAINT fk_make_model_model_id FOREIGN KEY(model_id) REFERENCES model(id);