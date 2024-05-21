CREATE TABLE tb_incorrect_answers
(
    id               BIGSERIAL             NOT NULL,
    quiz_id          BIGINT                NULL,
    incorrect_answer VARCHAR(255)          NULL,
    CONSTRAINT pk_tb_incorrect_answers PRIMARY KEY (id)
);

ALTER TABLE tb_incorrect_answers
    ADD CONSTRAINT FK_TB_INCORRECT_ANSWERS_ON_QUIZ FOREIGN KEY (quiz_id) REFERENCES tb_quiz (id);