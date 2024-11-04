CREATE TABLE historico_de_precos (
    id SERIAL PRIMARY KEY,
    produto_id INT NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    data_inicio DATE NOT NULL,
    data_final DATE,
    CONSTRAINT fk_produto FOREIGN KEY (produto_id) REFERENCES produtos(id)
);
