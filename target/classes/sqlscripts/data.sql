/*
truncate table designers cascade;
truncate table administrators cascade;
truncate table projects cascade;
truncate table project_infos cascade;
*/

INSERT INTO roles (name)
VALUES ('GUEST'),
       ('ADMIN'),
       ('DESIGNER');

INSERT INTO specializations (name)
VALUES ('INTERIOR'),
       ('EXTERIOR'),
       ('ARCHITECT');

INSERT INTO status (name)
VALUES ('PLANNING'),
       ('VISUAL'),
       ('DRAWING'),
       ('FINISHED');

insert into designers (firstName, lastName, experience, email, password, specialization_id, role_id)
values  ('Obi','Toppin', 2, 'obi1kenobi@mail.ru', 'ohaeb756bhj', (SELECT id FROM specializations sp WHERE sp.name = 'INTERIOR'), (SELECT id FROM roles r WHERE r.name = 'DESIGNER')),
		('Immanuel','Quikley', 2, 'fastest5nyk@gmail.com', 'sdfu70hjkhb', (SELECT id FROM specializations sp WHERE sp.name = 'ARCHITECT'), (SELECT id FROM roles r WHERE r.name = 'DESIGNER')),
		('RJ','Barrett', 3, 'knickshope9@mail.by', 'asd46565ytyt', (SELECT id FROM specializations sp WHERE sp.name = 'EXTERIOR'), (SELECT id FROM roles r WHERE r.name = 'DESIGNER')),
		('Quentin','Grimes', 1, 'newhope3pt@rambler.com', 'df1fgdhjzxc', (SELECT id FROM specializations sp WHERE sp.name = 'EXTERIOR'), (SELECT id FROM roles r WHERE r.name = 'DESIGNER')),
		('Miles','McBride', 1, 'bestlittlegeneral@gmail.com', 'dsgdgvklj56777', (SELECT id FROM specializations sp WHERE sp.name = 'ARCHITECT'), (SELECT id FROM roles r WHERE r.name = 'DESIGNER')),
		('Julius','Randle', 5, 'allstar@mail.com', 'ssf3030hgjhhkh', (SELECT id FROM specializations sp WHERE sp.name = 'INTERIOR'), (SELECT id FROM roles r WHERE r.name = 'DESIGNER')),
		('Cam','Reddish', 3, 'atlvsnyk@amail.us', 'lll569trug', (SELECT id FROM specializations sp WHERE sp.name = 'EXTERIOR'), (SELECT id FROM roles r WHERE r.name = 'DESIGNER')),
		('Mitchell','Robinson', 4, 'tallguy23@gmail.com', 'yuuuu766vbuybn86', (SELECT id FROM specializations sp WHERE sp.name = 'INTERIOR'), (SELECT id FROM roles r WHERE r.name = 'DESIGNER')),
		('Derrick','Rose', 14, 'drose111@mail.chi', 'der76bkjy8765', (SELECT id FROM specializations sp WHERE sp.name = 'ARCHITECT'), (SELECT id FROM roles r WHERE r.name = 'DESIGNER')),
		('Jericho','Simms', 1, 'bigman21@gmail.com', 'sim654khfjgb', (SELECT id FROM specializations sp WHERE sp.name = 'INTERIOR'), (SELECT id FROM roles r WHERE r.name = 'DESIGNER'));

insert into administrators (firstName, lastName, email, password, role_id)
values  ('Tom','Thibodeau', 'coach@gmail.ru', 'hgdsfiy99666',  (SELECT id FROM roles r WHERE r.name = 'ADMIN')),
		('Walt','Fraizer', 'comments@gmail.com', 'xkjyiu86908',  (SELECT id FROM roles r WHERE r.name = 'ADMIN')),
		('Mike','Breen', 'voiceofknicks@mail.ru', '465jjhfu',  (SELECT id FROM roles r WHERE r.name = 'ADMIN'));

insert into projects (admin_id, designer_id, price_for_m2, square)
values  ((SELECT ad.id FROM administrators ad WHERE ad.id = 1), (SELECT d.id FROM designers d WHERE d.id = 3), 10.2, 100),
		((SELECT ad.id FROM administrators ad WHERE ad.id = 2), (SELECT d.id FROM designers d WHERE d.id = 7), 7, 65.5),
		((SELECT ad.id FROM administrators ad WHERE ad.id = 1), (SELECT d.id FROM designers d WHERE d.id = 2), 5.6, 66),
		((SELECT ad.id FROM administrators ad WHERE ad.id = 3), (SELECT d.id FROM designers d WHERE d.id = 3), 7.7, 102.55),
		((SELECT ad.id FROM administrators ad WHERE ad.id = 1), (SELECT d.id FROM designers d WHERE d.id = 5), 8.8, 44),
		((SELECT ad.id FROM administrators ad WHERE ad.id = 3), (SELECT d.id FROM designers d WHERE d.id = 3), 6, 97.32),
		((SELECT ad.id FROM administrators ad WHERE ad.id = 1), (SELECT d.id FROM designers d WHERE d.id = 6), 11, 55),
		((SELECT ad.id FROM administrators ad WHERE ad.id = 2), (SELECT d.id FROM designers d WHERE d.id = 2), 12.2, 46.1),
		((SELECT ad.id FROM administrators ad WHERE ad.id = 2), (SELECT d.id FROM designers d WHERE d.id = 3), 5.8, 88.21),
		((SELECT ad.id FROM administrators ad WHERE ad.id = 1), (SELECT d.id FROM designers d WHERE d.id = 8), 9.9, 444);

insert into project_infos (designer_id, total_price, status_id)
values  ((SELECT d.id FROM designers d WHERE d.id = 2), (SELECT p.price_for_m2 * p.square  FROM projects p WHERE p.id = 3), (SELECT id FROM status s WHERE s.name = 'FINISHED')),
		((SELECT d.id FROM designers d WHERE d.id = 7), (SELECT p.price_for_m2 * p.square  FROM projects p WHERE p.id = 2), (SELECT id FROM status s WHERE s.name = 'DRAWING')),
		((SELECT d.id FROM designers d WHERE d.id = 3), (SELECT p.price_for_m2 * p.square  FROM projects p WHERE p.id = 1), (SELECT id FROM status s WHERE s.name = 'VISUAL')),
		((SELECT d.id FROM designers d WHERE d.id = 3), (SELECT p.price_for_m2 * p.square  FROM projects p WHERE p.id = 4), (SELECT id FROM status s WHERE s.name = 'PLANNING')),
		((SELECT d.id FROM designers d WHERE d.id = 5), (SELECT p.price_for_m2 * p.square  FROM projects p WHERE p.id = 5), (SELECT id FROM status s WHERE s.name = 'DRAWING')),
		((SELECT d.id FROM designers d WHERE d.id = 3), (SELECT p.price_for_m2 * p.square  FROM projects p WHERE p.id = 6), (SELECT id FROM status s WHERE s.name = 'VISUAL')),
		((SELECT d.id FROM designers d WHERE d.id = 6), (SELECT p.price_for_m2 * p.square  FROM projects p WHERE p.id = 7), (SELECT id FROM status s WHERE s.name = 'PLANNING')),
		((SELECT d.id FROM designers d WHERE d.id = 2), (SELECT p.price_for_m2 * p.square  FROM projects p WHERE p.id = 8), (SELECT id FROM status s WHERE s.name = 'FINISHED')),
		((SELECT d.id FROM designers d WHERE d.id = 3), (SELECT p.price_for_m2 * p.square  FROM projects p WHERE p.id = 9), (SELECT id FROM status s WHERE s.name = 'VISUAL')),
		((SELECT d.id FROM designers d WHERE d.id = 8), (SELECT p.price_for_m2 * p.square  FROM projects p WHERE p.id = 10), (SELECT id FROM status s WHERE s.name = 'VISUAL'));
