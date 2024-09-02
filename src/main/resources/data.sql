INSERT INTO users (id, nombre, password, username) values (1, 'Test', '$2a$10$J26Yt7NF6deOWLLwtR1E6uAzhbSJ4oreAQXoVXsnfgOsUWo39jFXy', 'test');

INSERT INTO permissions (id, name) VALUES (1, 'USER');

INSERT INTO roles (id, rol_name) VALUES (1, 'USER');

INSERT INTO role_permissions (role_id, permission_id) VALUES (1, 1); -- USER role with BUY permission

INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);

INSERT INTO paises (nombre) VALUES ('España');
INSERT INTO paises (nombre) VALUES ('Inglaterra');
INSERT INTO paises (nombre) VALUES ('Italia');
INSERT INTO paises (nombre) VALUES ('Alemania');
INSERT INTO paises (nombre) VALUES ('Francia');
INSERT INTO paises (nombre) VALUES ('Portugal');
INSERT INTO paises (nombre) VALUES ('Países Bajos');
INSERT INTO paises (nombre) VALUES ('Escocia');
INSERT INTO paises (nombre) VALUES ('Turquía');
INSERT INTO paises (nombre) VALUES ('Rusia');

INSERT INTO ligas (nombre) VALUES ('La Liga');
INSERT INTO ligas (nombre) VALUES ('Premier League');
INSERT INTO ligas (nombre) VALUES ('Serie A');
INSERT INTO ligas (nombre) VALUES ('Bundesliga');
INSERT INTO ligas (nombre) VALUES ('Ligue 1');
INSERT INTO ligas (nombre) VALUES ('Primeira Liga');
INSERT INTO ligas (nombre) VALUES ('Eredivisie');
INSERT INTO ligas (nombre) VALUES ('Scottish Premiership');
INSERT INTO ligas (nombre) VALUES ('Süper Lig');
INSERT INTO ligas (nombre) VALUES ('Premier League Rusa');

INSERT INTO equipos (nombre, liga_id, pais_id) VALUES ('Real Madrid', 1, 1);
INSERT INTO equipos (nombre, liga_id, pais_id) VALUES ('FC Barcelona', 1, 1);
INSERT INTO equipos (nombre, liga_id, pais_id) VALUES ('Liverpool FC', 2, 2);
INSERT INTO equipos (nombre, liga_id, pais_id) VALUES ('Manchester United', 2, 2);
INSERT INTO equipos (nombre, liga_id, pais_id) VALUES ('Juventus FC', 3, 3);
INSERT INTO equipos (nombre, liga_id, pais_id) VALUES ('AC Milan', 3, 3);
INSERT INTO equipos (nombre, liga_id, pais_id) VALUES ('Bayern Munich', 4, 4);
INSERT INTO equipos (nombre, liga_id, pais_id) VALUES ('Borussia Dortmund', 4, 4);
INSERT INTO equipos (nombre, liga_id, pais_id) VALUES ('Paris Saint-Germain', 5, 5);
INSERT INTO equipos (nombre, liga_id, pais_id) VALUES ('Olympique de Marseille', 5, 5);
INSERT INTO equipos (nombre, liga_id, pais_id) VALUES ('FC Porto', 6, 6);
INSERT INTO equipos (nombre, liga_id, pais_id) VALUES ('Sporting CP', 6, 6);
INSERT INTO equipos (nombre, liga_id, pais_id) VALUES ('Ajax Amsterdam', 7, 7);
INSERT INTO equipos (nombre, liga_id, pais_id) VALUES ('Feyenoord', 7, 7);
INSERT INTO equipos (nombre, liga_id, pais_id) VALUES ('Celtic FC', 8, 8);
INSERT INTO equipos (nombre, liga_id, pais_id) VALUES ('Rangers FC', 8, 8);
INSERT INTO equipos (nombre, liga_id, pais_id) VALUES ('Galatasaray SK', 9, 9);
INSERT INTO equipos (nombre, liga_id, pais_id) VALUES ('Fenerbahçe SK', 9, 9);
INSERT INTO equipos (nombre, liga_id, pais_id) VALUES ('FC Zenit Saint Petersburg', 10, 10);
INSERT INTO equipos (nombre, liga_id, pais_id) VALUES ('Spartak Moscow', 10, 10);
INSERT INTO equipos (nombre, liga_id, pais_id) VALUES ('SL Benfica', 6, 6);
INSERT INTO equipos (nombre, liga_id, pais_id) VALUES ('Besiktas JK', 9, 9);
INSERT INTO equipos (nombre, liga_id, pais_id) VALUES ('SSC Napoli', 3, 3);
INSERT INTO equipos (nombre, liga_id, pais_id) VALUES ('Atlético Madrid', 1, 1);