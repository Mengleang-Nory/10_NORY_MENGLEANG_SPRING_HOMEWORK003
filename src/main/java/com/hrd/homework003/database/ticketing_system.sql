create database ticketing_system;

create table attendees
(
    attendee_id   serial primary key,
    attendee_name varchar(50) not null,
    email         varchar(50) not null unique
);

create table venues
(
    venue_id   serial primary key,
    venue_name varchar not null,
    location   varchar not null
);

create table events
(
    event_id   serial primary key,
    event_name varchar not null,
    event_date varchar not null,
    venue_id   int references venues (venue_id) on update cascade on delete cascade
);

create table evant_attendee
(
    id          serial primary key,
    event_id    int references events (event_id) on update cascade on delete cascade,
    attendee_id int references attendees (attendee_id) on update cascade on delete cascade
);

-- Insert attendees
INSERT INTO attendees (attendee_name, email)
VALUES ('Sokha Phan', 'sokha.phan@example.com'),
       ('David Chan', 'david.chan@example.com'),
       ('Linda Wong', 'linda.wong@example.com'),
       ('Kimheng Sok', 'kimheng.sok@example.com'),
       ('Sophia Heng', 'sophia.heng@example.com');

-- Insert venues
INSERT INTO venues (venue_name, location)
VALUES ('Olympic Stadium', 'Phnom Penh, Cambodia'),
       ('AEON Mall Hall', 'Phnom Penh, Cambodia'),
       ('Sihanoukville Convention Center', 'Sihanoukville, Cambodia');

-- Insert events
INSERT INTO events (event_name, event_date, venue_id)
VALUES ('Music Festival 2025', '2025-04-20', 1),
       ('Tech Conference', '2025-06-15', 2),
       ('Food & Drink Expo', '2025-07-10', 3);

-- Insert event attendees (associating attendees with events)
INSERT INTO evant_attendee (event_id, attendee_id)
VALUES (1, 1), -- Sokha Phan attending Music Festival 2025
       (1, 3), -- Linda Wong attending Music Festival 2025
       (2, 2), -- David Chan attending Tech Conference
       (2, 4), -- Kimheng Sok attending Tech Conference
       (3, 5), -- Sophia Heng attending Food & Drink Expo
       (3, 1); -- Sokha Phan attending Food & Drink Expo

select *
from attendees
         limit 3 offset 0;

select *
from attendees
where attendee_id = 8;

insert into attendees (attendee_name, email)
values ('koko', 'koko@gmail.com')
    returning *;

delete
from attendees
where attendee_id = 7;

update attendees
set attendee_name = 'leang',
    email='leang@gmail.com'
where attendee_id = 10
    returning *;

select *
from venues
         limit 3 offset 0;

select *
from venues
where venue_id = 5;

insert into venues (venue_name, location)
values ('kaka', 'sr')
    returning *;

update venues
set venue_name = 'jojo',
    location='kp'
where venue_id = 5
    returning *;

delete
from venues
where venue_id = 5;

select *
from events
         limit 3 offset 0;

select *
from attendees a
         inner join evant_attendee ea on a.attendee_id = ea.attendee_id
where ea.event_id = 1;

select * from events where event_id = 3;

insert into events (event_name, event_date, venue_id)
values ('sing','22-11-2026',2)
    returning event_id;

insert into evant_attendee (event_id, attendee_id)
values (1,2);

insert into events (event_id,event_name, event_date, venue_id)
values (1,'sing','22-11-2026',2)
    returning event_id;