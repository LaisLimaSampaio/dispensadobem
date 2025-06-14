ALTER TABLE estabelecimentos
    DROP COLUMN categoria;

ALTER TABLE estabelecimentos
    ADD COLUMN categoria_id INT REFERENCES categoria_estabelecimento(id);
