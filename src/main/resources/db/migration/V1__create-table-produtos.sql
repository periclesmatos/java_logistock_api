create table podutos(

    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT,
    categoria VARCHAR(100),
    quantidade INTEGER NOT NULL,
    preco NUMERIC(10, 2) NOT NULL,
    sku VARCHAR(11) NOT NULL,
    CONSTRAINT sku_format CHECK (sku ~ '^[A-Z0-9]{3}-[A-Z0-9]{3}-[A-Z0-9]{3}$')

);