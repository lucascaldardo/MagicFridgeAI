CREATE TABLE food_item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    categoria ENUM('Carne','Legume','Fruta','Bebida','Outros'),
    quantidade INTEGER,
    validade DATETIME
);