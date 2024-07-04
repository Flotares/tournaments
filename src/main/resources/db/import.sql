insert into Tournament(name, start_date, status) values
('20. Hermann Wallner Turnier', STR_TO_DATE('01.06.2025', '%d.%m.%Y'), 0),
('15. Michael Muhr Gedenkturnier', STR_TO_DATE('01.12.2025', '%d.%m.%Y'), 0),
('20. Jürgen Stiftinger Gedenkturnier', STR_TO_DATE('25.12.2025', '%d.%m.%Y'), 0);

insert into Phase(phase_type, name, phase_order, tournament_id) values
(0, 'Vorrunde', 0, 1),
(0, 'Zwischenrunde', 1, 1),
(1, 'Kreuzspiele', 2, 1),
(1, 'Finalspiele', 3, 1);

insert into Club (name) values
('SC St. Valentin'),
('Floridsdorfer AC'),
('SK Admira Linz'),
('ASKÖ Luftenberg'),
('SK Asten'),
('LASK');

insert into Team(name, club_id) values
('SC St. Valentin I', 1),
('SC St. Valentin II', 1),
('Floridsdorfer AC', 2),
('SK Admira Linz', 3);

insert into Tournament_Team(tournament_id, teams_id) values
(1, 1),
(1, 2),
(1, 3),
(1, 4);

