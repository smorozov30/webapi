-- Заполнение таблицы "Марки"
INSERT INTO brand (id, name) VALUES (1, 'Lada'), (2, 'VW'), (3, 'Toyota');

-- Заполнение таблицы "Модели"
INSERT INTO model (id, name) VALUES (1, 'Vesta'), (2, 'Granta'), (3, 'Priora'), (4, 'Passat'), (5, 'Camry');

-- Заполнение связующей таблицы "Марки-Модели"
INSERT INTO brand_model (brand_id, model_id) VALUES (1, 1), (1, 2), (1, 3), (2, 4), (3, 5);

-- Заполнение таблицы "Автомобиль"
INSERT INTO car (id, brand_id, model_id) VALUES (1, 1, 1), (2, 2, 4), (3, 3, 5);