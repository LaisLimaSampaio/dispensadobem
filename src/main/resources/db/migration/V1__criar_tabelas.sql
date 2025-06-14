-- 1. Tabela cliente
CREATE TABLE cliente (
                         id SERIAL PRIMARY KEY,
                         nome VARCHAR NOT NULL,
                         email VARCHAR UNIQUE NOT NULL,
                         senha VARCHAR NOT NULL,
                         telefone VARCHAR,
                         data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Tabela estabelecimentos
CREATE TABLE estabelecimentos (
                                  id SERIAL PRIMARY KEY,
                                  nome_fantasia VARCHAR NOT NULL,
                                  cnpj VARCHAR UNIQUE NOT NULL,
                                  email VARCHAR UNIQUE NOT NULL,
                                  senha VARCHAR NOT NULL,
                                  horario_funcionamento VARCHAR,
                                  oferece_entrega BOOLEAN DEFAULT FALSE,
                                  taxa_entrega DECIMAL,
                                  entrega_gratis_acima DECIMAL,
                                  distancia_max_entrega_km DECIMAL,
                                  telefone VARCHAR,
                                  categoria VARCHAR
);

-- 3. Endereço para Clientes
CREATE TABLE endereco_cliente (
                                  id SERIAL PRIMARY KEY,
                                  rua VARCHAR,
                                  numero VARCHAR,
                                  complemento VARCHAR,
                                  bairro VARCHAR,
                                  cidade VARCHAR,
                                  estado CHAR(2),
                                  cep VARCHAR,
                                  latitude DECIMAL(10, 8),
                                  longitude DECIMAL(11, 8),
                                  cliente_id INT UNIQUE REFERENCES cliente(id)
);

-- 4. Endereço para Estabelecimentos
CREATE TABLE endereco_estabelecimento (
                                          id SERIAL PRIMARY KEY,
                                          rua VARCHAR,
                                          numero VARCHAR,
                                          complemento VARCHAR,
                                          bairro VARCHAR,
                                          cidade VARCHAR,
                                          estado CHAR(2),
                                          cep VARCHAR,
                                          latitude DECIMAL(10, 8),
                                          longitude DECIMAL(11, 8),
                                          estabelecimento_id INT UNIQUE REFERENCES estabelecimentos(id)
);

-- 5. Tabela produtos
CREATE TABLE produtos (
                          id SERIAL PRIMARY KEY,
                          estabelecimento_id INT NOT NULL REFERENCES estabelecimentos(id),
                          nome VARCHAR NOT NULL,
                          descricao TEXT,
                          categoria VARCHAR,
                          foto_url TEXT,
                          data_validade DATE,
                          preco_original DECIMAL NOT NULL CHECK (preco_original >= 0),
                          preco_desconto DECIMAL CHECK (preco_desconto >= 0),
                          criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 6. Tabela pedidos
CREATE TABLE pedidos (
                         id SERIAL PRIMARY KEY,
                         cliente_id INT NOT NULL REFERENCES cliente(id),
                         data_pedido TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         status VARCHAR NOT NULL,
                         total DECIMAL NOT NULL CHECK (total >= 0),
                         forma_pagamento VARCHAR NOT NULL
);

-- 7. Tabela itens_pedido
CREATE TABLE itens_pedido (
                              id SERIAL PRIMARY KEY,
                              pedido_id INT NOT NULL REFERENCES pedidos(id),
                              produto_id INT NOT NULL REFERENCES produtos(id),
                              quantidade INT NOT NULL CHECK (quantidade > 0),
                              preco_unitario DECIMAL NOT NULL CHECK (preco_unitario >= 0)
);

-- 8. Tabela avaliacoes
CREATE TABLE avaliacoes (
                            id SERIAL PRIMARY KEY,
                            cliente_id INT NOT NULL REFERENCES cliente(id),
                            estabelecimento_id INT NOT NULL REFERENCES estabelecimentos(id),
                            nota INT CHECK (nota BETWEEN 1 AND 5),
                            comentario TEXT,
                            data TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 9. Tabela favoritos
CREATE TABLE favoritos (
                           id SERIAL PRIMARY KEY,
                           cliente_id INT NOT NULL REFERENCES cliente(id),
                           estabelecimento_id INT NOT NULL REFERENCES estabelecimentos(id),
                           UNIQUE(cliente_id, estabelecimento_id)
);
