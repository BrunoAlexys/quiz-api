CREATE TABLE tb_category
(
    id   BIGSERIAL             NOT NULL,
    name VARCHAR(255)          NULL,
    CONSTRAINT pk_tb_category PRIMARY KEY (id)
);