CREATE TABLE tb_quiz
(
    id             BIGSERIAL             NOT NULL,
    difficulty     VARCHAR(255)          NULL,
    category_id    BIGINT                NULL,
    question       VARCHAR(255)          NULL,
    correct_answer VARCHAR(255)          NULL,
    CONSTRAINT pk_tb_quiz PRIMARY KEY (id)
);

ALTER TABLE tb_quiz
    ADD CONSTRAINT FK_TB_QUIZ_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES tb_category (id);