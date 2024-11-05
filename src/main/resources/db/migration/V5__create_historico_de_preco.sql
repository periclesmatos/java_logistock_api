BEGIN;

INSERT INTO historico_de_precos (produto_id, preco, data_inicio, data_final)
SELECT id, preco, NOW(), NULL
FROM produtos;

COMMIT;
