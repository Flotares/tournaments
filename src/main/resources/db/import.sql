insert into Tournament(name, start_date, status) values
('20. Hermann Wallner Turnier', STR_TO_DATE('01.06.2025', '%d.%m.%Y'), 0),
('15. Michael Muhr Gedenkturnier', STR_TO_DATE('01.12.2025', '%d.%m.%Y'), 0),
('20. Jürgen Stiftinger Gedenkturnier', STR_TO_DATE('25.12.2025', '%d.%m.%Y'), 0);

insert into Phase(phase_type, name, phase_order, tournament_id) values
(0, 'Vorrunde', 0, 1),
(0, 'Zwischenrunde', 1, 1),
(2, 'Kreuzspiele', 2, 1),
(2, 'Finalspiele', 3, 1);

insert into Pot(tournament_id, name) values
(1, 'Pot 1'),
(1, 'Pot 2'),
(1, 'Pot 3'),
(1, 'Pot 4');

insert into Club (name) values
('FAC Wien'),
('SK Admira Linz'),
('ASKÖ Luftenberg'),
('SK Asten'),
('LASK'),
('Union St. Florian'),
('USC Perchtoldsdorf'),
('SV Lostenstein'),
('Red Bull Salzburg'),
('FC Stahl Linz'),
('SC Columbia Floridsdorf'),
('Union Dietach'),
('SG Waidhofen/Ybbs'),
('JARR Graz Umgebung Süd'),
('ASKÖ Ebelsberg'),
('ASK St. Valentin'),
('WSC Hertha'),
('USC Markersdorf'),
('Union Puchenau'),
('ASV Haidershofen/Behamberg'),
('Austria Salzburg'),
('ASKÖ Donau Linz'),
('Union Wolfern'),
('SKN St. Pölten'),
('SPG Hörsching/Oedt'),
('SC St. Pantaleon-Erla'),
('SV Sierning'),
('FC Fleyeralarm Admira'),
('SV Wienerberg 1921'),
('SC St. Valentin'),
('Union Weißkirchen');