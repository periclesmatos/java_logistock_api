INSERT INTO historico_de_precos (produto_id, preco, data_inicio)
SELECT id, preco, CURRENT_DATE
FROM produtos;
