INSERT INTO USUARIO(nome, email, senha) VALUES('Teste', 'aws@teste.com', '$2a$10$za0XJpLMaXsc/kgH6YZq4uby1S4uGKwNU51fZ0lfYYskuQQvc56hi');--12345

INSERT INTO TIME(nome, localidade) values ('Time 1', 'Localidade 1');
INSERT INTO TIME(nome, localidade) values ('Time 2', 'Localidade 2');
INSERT INTO TIME(nome, localidade) values ('Time 3', 'Localidade 3');
INSERT INTO TIME(nome, localidade) values ('Time 4', 'Localidade 4');


INSERT INTO JOGADOR(nome, nascimento, pais, time_id) values ('Jogador 1', '01/01/1993', 'Brasil', 1);
INSERT INTO JOGADOR(nome, nascimento, pais, time_id) values ('Jogador 2', '01/02/1994', 'Brasil', 2);
INSERT INTO JOGADOR(nome, nascimento, pais, time_id) values ('Jogador 3', '01/03/1995', 'Brasil', 3);
INSERT INTO JOGADOR(nome, nascimento, pais, time_id) values ('Jogador 4', '01/04/1996', 'Brasil', 4);

INSERT INTO TRANSFERENCIA(data, jogador_id, origem_id, destino_id, valor) values ('2019-05-05 18:00:00', 1, 1, 2, 50000.99);
INSERT INTO TRANSFERENCIA(data, jogador_id, origem_id, destino_id, valor) values ('2019-05-05 18:00:00', 2, 2, 3, 78979.00);

INSERT INTO TORNEIO(nome, data_inicio) values ('Torneio 1', '2022-06-06 18:00:00' );
INSERT INTO TORNEIO(nome, data_inicio) values ('Torneio 2', '2022-07-07 18:00:00' );
INSERT INTO TORNEIO(nome, data_inicio) values ('Torneio 3', '2022-08-08 18:00:00' );

INSERT INTO PARTIDA(local, data_prevista, torneio_id, time1_id, time2_id) values ('Mineirão', '2022-06-06 18:00:00', 1, 1, 2);
INSERT INTO PARTIDA(local, data_prevista, torneio_id, time1_id, time2_id) values ('Independência', '2022-07-07 18:00:00', 1, 3, 4);