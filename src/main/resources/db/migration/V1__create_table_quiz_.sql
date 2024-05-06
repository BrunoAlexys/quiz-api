CREATE TABLE tb_quiz
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    type           VARCHAR(255)          NOT NULL,
    difficulty     VARCHAR(255)          NOT NULL,
    category       VARCHAR(255)          NOT NULL,
    question       TEXT                  NOT NULL,
    correct_answer VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_tb_quiz PRIMARY KEY (id)
);