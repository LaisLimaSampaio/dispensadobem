ALTER TABLE produtos
    DROP COLUMN categoria;

ALTER TABLE produtos
    ADD COLUMN categoria_id INT REFERENCES categoria_produto(id);
