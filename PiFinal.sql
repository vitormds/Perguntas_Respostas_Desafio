CREATE DATABASE QUIZCAIRU;

USE QUIZCAIRU;

SET SQL_SAFE_UPDATES = 0;

CREATE TABLE ALUNOS
(
	ID INT NOT NULL AUTO_INCREMENT,
    NOME VARCHAR(50) NOT NULL,
    MATRICULA VARCHAR(50) NOT NULL,
    SENHA VARCHAR(50) NOT NULL,
    EMAIL VARCHAR(60) NOT NULL,
    SEMESTRE VARCHAR(50) NULL,
    VITORIAS INT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE PONTUACOES
(
	ID INT NOT NULL AUTO_INCREMENT,
    ACERTOS INT NULL,
    ALUNO_ID INT NOT NULL,
    FOREIGN KEY(ALUNO_ID) REFERENCES ALUNOS(ID),
    PRIMARY KEY (ID)
);

CREATE TABLE PERGUNTAS
(
	ID INT NOT NULL AUTO_INCREMENT,
    PERGUNTA TEXT NOT NULL,
    OPCAO_CORRETA CHAR(100),
    PRIMARY KEY (ID)
);

CREATE TABLE OPCOES
(
	ID INT NOT NULL AUTO_INCREMENT,
    OPCAO VARCHAR(100) NOT NULL,
    PERGUNTA_ID INT NOT NULL,
    FOREIGN KEY (PERGUNTA_ID) REFERENCES PERGUNTAS (ID),
    PRIMARY KEY (ID)
);

CREATE TABLE DESAFIOS
(
	ID INT NOT NULL AUTO_INCREMENT,
    ALUNO_ID INT NOT NULL,
    ACERTOS_ALUNO INT NOT NULL,
    DESAFIADO_ID INT NOT NULL,
    ACERTOS_DESAFIADO INT NULL,
    VENCEDOR_ID INT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (ALUNO_ID) REFERENCES ALUNOS (ID)
);



INSERT INTO PERGUNTAS (PERGUNTA, OPCAO_CORRETA) VALUES (' É um dispositivo de armazenamento conectado a uma rede que possibilita o armazenamento e a recuperação de dados de um local centralizado para usuários autorizados da rede e clientes heterogêneos.','NAS' );
INSERT INTO OPCOES (OPCAO, PERGUNTA_ID) values ('NAS', 1), ('LOG', 1), ('DAS' , 1), ('SA', 1);

INSERT INTO PERGUNTAS (PERGUNTA, OPCAO_CORRETA) VALUES ('Em um compilador, o analisador léxico', 'cria uma sequência de símbolos.');


INSERT INTO OPCOES (OPCAO,PERGUNTA_ID) values ('cria uma estrutura de dados.', 2), ('cria uma sequência de símbolos.', 2), (' cria uma gramática livre de contexto.', 2), ('cria um código intermediário.', 2);

INSERT INTO PERGUNTAS (PERGUNTA, OPCAO_CORRETA) VALUES ('Como é representado um diagrama de caso de uso?','Atores,Relacionamento e Casos de uso');


INSERT INTO OPCOES (OPCAO, PERGUNTA_ID) values ('Atributos,Mensagem de retorno e Classes', 3), ('Atores,Relacionamento e Casos de uso', 3), ('Classes,Entidades e Relacionamento', 3),('Caso de uso,Atributos e Transações', 3);

INSERT INTO PERGUNTAS (PERGUNTA, OPCAO_CORRETA) VALUES ('Para ser um engenheiro de softaware o profissional tem que saber sobre', 'Todas as alternativas anteriores');



INSERT INTO OPCOES (OPCAO, PERGUNTA_ID) values ('Qualidade do produto', 4), ('Agilidade no desenvolvimento', 4), ('Controle de prazos', 4), ('Todas as alternativas anteriores', 4);

INSERT INTO PERGUNTAS (PERGUNTA, OPCAO_CORRETA) VALUES ('Quais os comandos dml ?', 'Insert,Update e Delete');



INSERT INTO OPCOES (OPCAO, PERGUNTA_ID) values ('Insert,Update e Delete', 5), ('Create,Update e Delete', 5), ('Insert,Alter,Revoke', 5), ('Insert,Delete,Select', 5);

INSERT INTO PERGUNTAS (PERGUNTA, OPCAO_CORRETA) VALUES ('Quais os comandos dcl ?', 'Revoke,Grant e Deny');



INSERT INTO OPCOES (OPCAO, PERGUNTA_ID) values ('Revoke,Deny e Alter', 6), ('Revoke,Grant e Deny', 6), ('Begin,Grant e Revoke', 6), ('Select,Deny e Update', 6);

INSERT INTO PERGUNTAS (PERGUNTA, OPCAO_CORRETA) VALUES ('Durante a compilação de um código-fonte, a fase do compilador que é responsável por produzir uma sequência de tokens é a', 'análise léxica.');



INSERT INTO OPCOES (OPCAO, PERGUNTA_ID) values ('geração de código executável.', 7), ('análise léxica.', 7), ('análise semântica.', 7), ('verificação de tipos.', 7);

INSERT INTO PERGUNTAS (PERGUNTA, OPCAO_CORRETA) VALUES ('A sigla (CPU) faz  referência a qual componente do computador ?', 'Processador');



INSERT INTO OPCOES (OPCAO, PERGUNTA_ID) values ('Memória rom', 8), ('Memória ram', 8), ('Placa de video', 8), ('Processador', 8);





INSERT INTO PERGUNTAS (PERGUNTA, OPCAO_CORRETA) VALUES ('Qual destes programas são reponsaveis por criar planilhas eletronicas?', 'Excel e Calc');

INSERT INTO OPCOES (OPCAO, PERGUNTA_ID) values ('Paint e Excel', 9), ('Excel e Outlook', 9), ('Excel e Calc', 9), ('Excel e Word', 9);






INSERT INTO PERGUNTAS (PERGUNTA, OPCAO_CORRETA) VALUES ('Qual destas opções um sistema não deve ter ?', 'Nenhuma das alternativas anteriores');



INSERT INTO OPCOES (OPCAO, PERGUNTA_ID) values ('Integracao', 10), ('Fluxo Independente', 10), ('Controle', 10), ( 'Nenhuma das alternativas anteriores', 10);

 INSERT INTO PERGUNTAS (PERGUNTA, OPCAO_CORRETA) VALUES (' Automatiza todas as funções relativas ao contato com o cliente ?', 'Crm');



INSERT INTO OPCOES (OPCAO, PERGUNTA_ID) values ('CRM', 11), ('SIG', 11), ('ERP', 11), ( 'SAD', 11);


 INSERT INTO PERGUNTAS (PERGUNTA, OPCAO_CORRETA) VALUES ('O que é Algoritmos?', 'É uma sequência finita de instruções bem definidas e não ambíguas');



INSERT INTO OPCOES (OPCAO, PERGUNTA_ID) values ('São instruções definidas apenas para programas de computadores', 12), ('É uma sequência finita de instruções bem definidas e não ambíguas', 12), ('É o local onde é armazenado os dados', 12), ( 'É um sequencia de instruções bem definidas e ambiguas', 12);

INSERT INTO ALUNOS (ID, NOME, MATRICULA, SENHA, EMAIL) VALUES (1, 'administrador', '1', '1', 'admin');
INSERT INTO ALUNOS (ID, NOME, MATRICULA, SENHA, EMAIL) VALUES (2, 'vitor', '2', '2', '2');