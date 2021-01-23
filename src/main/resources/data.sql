

INSERT INTO MUSIC.USER_MODEL (ID, USERNAME, PASSWORD) VALUES (1, 'admin', '$2a$10$kv.60PxhoTDtSghTLlyGU.PhePEcuvIX.lrcqxpO0trKRa74G3g/.');

INSERT INTO MUSIC.AUTOR (ID, NOME) VALUES
  (1, 'Serj tankian'),
  (2, 'Mike Shinoda'),
  (3, 'Michel Teló'),
  (4, 'Guns N'' Roses');

INSERT INTO MUSIC.ALBUM (ID, NOME, AUTOR_ID, IMAGEM) VALUES
  (1, 'Harakiri', 1, null),
  (2, 'Black Blooms', 1, null),
  (3, 'The Rough Dog', 1, null),
  (4,'The Rising Tied',  2, null),
  (5,'Post Traumatic',  2, null),
  (6,'Post Traumatic EP', 2, null),
  (7,'Where''d You Go', 2, null),
  (8,'Bem Sertanejo', 3, null),
  (9,'Bem Sertanejo - O Show (Ao Vivo)',   3, null),
  (10,'Bem Sertanejo - (1a Temporada) - EP',  3, null),
  (11,'Use Your IIIlusion I',   4, null),
  (12,'Use Your IIIlusion II',  4, null),
  (13,'Greatest Hits',  4, null);