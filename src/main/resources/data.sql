INSERT INTO users (id, nombre, password, username) values (1, 'Test', '$2a$10$J26Yt7NF6deOWLLwtR1E6uAzhbSJ4oreAQXoVXsnfgOsUWo39jFXy', 'test');

INSERT INTO permissions (id, name) VALUES (1, 'USER');

INSERT INTO roles (id, rol_name) VALUES (1, 'USER');

INSERT INTO role_permissions (role_id, permission_id) VALUES (1, 1); -- USER role with BUY permission

INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);

INSERT INTO paises (id, nombre) VALUES (1, 'España');
INSERT INTO paises (id, nombre) VALUES (2, 'Inglaterra');
INSERT INTO paises (id, nombre) VALUES (3, 'Italia');
INSERT INTO paises (id, nombre) VALUES (4, 'Alemania');
INSERT INTO paises (id, nombre) VALUES (5, 'Francia');
INSERT INTO paises (id, nombre) VALUES (6, 'Portugal');
INSERT INTO paises (id, nombre) VALUES (7, 'Países Bajos');
INSERT INTO paises (id, nombre) VALUES (8, 'Escocia');
INSERT INTO paises (id, nombre) VALUES (9, 'Turquía');
INSERT INTO paises (id, nombre) VALUES (10, 'Rusia');

INSERT INTO ligas (id, nombre) VALUES (1, 'La Liga');
INSERT INTO ligas (id, nombre) VALUES (2, 'Premier League');
INSERT INTO ligas (id, nombre) VALUES (3, 'Serie A');
INSERT INTO ligas (id, nombre) VALUES (4, 'Bundesliga');
INSERT INTO ligas (id, nombre) VALUES (5, 'Ligue 1');
INSERT INTO ligas (id, nombre) VALUES (6, 'Primeira Liga');
INSERT INTO ligas (id, nombre) VALUES (7, 'Eredivisie');
INSERT INTO ligas (id, nombre) VALUES (8, 'Scottish Premiership');
INSERT INTO ligas (id, nombre) VALUES (9, 'Süper Lig');
INSERT INTO ligas (id, nombre) VALUES (10, 'Premier League Rusa');

INSERT INTO equipos (id, nombre, liga_id, pais_id) VALUES (1, 'Real Madrid', 1, 1);
INSERT INTO equipos (id, nombre, liga_id, pais_id) VALUES (2, 'FC Barcelona', 1, 1);
INSERT INTO equipos (id, nombre, liga_id, pais_id) VALUES (3, 'Manchester United', 2, 2);
INSERT INTO equipos (id, nombre, liga_id, pais_id) VALUES (4, 'Liverpool FC', 2, 2);
INSERT INTO equipos (id, nombre, liga_id, pais_id) VALUES (5, 'Juventus FC', 3, 3);
INSERT INTO equipos (id, nombre, liga_id, pais_id) VALUES (6, 'AC Milan', 3, 3);
INSERT INTO equipos (id, nombre, liga_id, pais_id) VALUES (7, 'Bayern Munich', 4, 4);
INSERT INTO equipos (id, nombre, liga_id, pais_id) VALUES (8, 'Borussia Dortmund', 4, 4);
INSERT INTO equipos (id, nombre, liga_id, pais_id) VALUES (9, 'Paris Saint-Germain', 5, 5);
INSERT INTO equipos (id, nombre, liga_id, pais_id) VALUES (10, 'Olympique de Marseille', 5, 5);
INSERT INTO equipos (id, nombre, liga_id, pais_id) VALUES (11, 'FC Porto', 6, 6);
INSERT INTO equipos (id, nombre, liga_id, pais_id) VALUES (12, 'Sporting CP', 6, 6);
INSERT INTO equipos (id, nombre, liga_id, pais_id) VALUES (13, 'Ajax Amsterdam', 7, 7);
INSERT INTO equipos (id, nombre, liga_id, pais_id) VALUES (14, 'Feyenoord', 7, 7);
INSERT INTO equipos (id, nombre, liga_id, pais_id) VALUES (15, 'Celtic FC', 8, 8);
INSERT INTO equipos (id, nombre, liga_id, pais_id) VALUES (16, 'Rangers FC', 8, 8);
INSERT INTO equipos (id, nombre, liga_id, pais_id) VALUES (17, 'Galatasaray SK', 9, 9);
INSERT INTO equipos (id, nombre, liga_id, pais_id) VALUES (18, 'Fenerbahçe SK', 9, 9);
INSERT INTO equipos (id, nombre, liga_id, pais_id) VALUES (19, 'FC Zenit Saint Petersburg', 10, 10);
INSERT INTO equipos (id, nombre, liga_id, pais_id) VALUES (20, 'Spartak Moscow', 10, 10);
INSERT INTO equipos (id, nombre, liga_id, pais_id) VALUES (21, 'SL Benfica', 6, 6);
INSERT INTO equipos (id, nombre, liga_id, pais_id) VALUES (22, 'Besiktas JK', 9, 9);
INSERT INTO equipos (id, nombre, liga_id, pais_id) VALUES (23, 'SSC Napoli', 3, 3);
INSERT INTO equipos (id, nombre, liga_id, pais_id) VALUES (24, 'Atlético Madrid', 1, 1);