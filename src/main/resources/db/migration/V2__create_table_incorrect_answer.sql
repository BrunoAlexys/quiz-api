CREATE TABLE tb_incorrect_answer
(
    id               BIGINT AUTO_INCREMENT NOT NULL,
    quiz_id          BIGINT                NOT NULL,
    incorrect_answer VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_tb_incorrect_answer PRIMARY KEY (id)
);

ALTER TABLE tb_incorrect_answer
    ADD CONSTRAINT FK_TB_INCORRECT_ANSWER_ON_QUIZ FOREIGN KEY (quiz_id) REFERENCES tb_quiz (id);