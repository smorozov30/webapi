-- Таблица "Автомобиль"
CREATE TABLE IF NOT EXISTS car (
    id SERIAL PRIMARY KEY,
    brand_id INT NOT NULL,
    model_id INT NOT NULL
);

-- Таблица "Марка"
CREATE TABLE IF NOT EXISTS brand  (
    id SERIAL PRIMARY KEY,
    name VARCHAR (100) NOT NULL
);

-- Таблица "Модель"
CREATE TABLE IF NOT EXISTS model (
    id SERIAL PRIMARY KEY,
    name VARCHAR (100) NOT NULL
);

-- Связующая таблица "Марка-Модель"
CREATE TABLE IF NOT EXISTS brand_model (
    brand_id INT NOT NULL,
    model_id INT NOT NULL,
    PRIMARY KEY (brand_id, model_id)
);

-- Создание ограничений на таблиц
ALTER TABLE car ADD CONSTRAINT fk_car_brand_id FOREIGN KEY (brand_id) REFERENCES brand (id);
ALTER TABLE car ADD CONSTRAINT fk_car_model_id FOREIGN KEY (model_id) REFERENCES model (id);
ALTER TABLE brand_model ADD CONSTRAINT fk_brand_model_brand_id FOREIGN KEY(brand_id) REFERENCES brand(id);
ALTER TABLE brand_model ADD CONSTRAINT fk_brand_model_model_id FOREIGN KEY(model_id) REFERENCES model(id);
