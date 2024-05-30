CREATE TABLE tb_cidade (
    id_cidade INT PRIMARY KEY NOT NULL,
    nome_cidade VARCHAR(50),
    qtd_habitantes BIGINT
);

INSERT INTO tb_cidade (id_cidade, nome_cidade, qtd_habitantes) VALUES
    (1, 'São Paulo', 12252023),
    (2, 'Rio de Janeiro', 6747815),
    (3, 'Belo Horizonte', 2513456),
    (4, 'Banabuiu', 20000)
