------ DELETE EVERYTHING FROM THE DATABASE ------
BEGIN;
DO
$$
    DECLARE
        r RECORD;
    BEGIN
        FOR r IN (SELECT tablename FROM pg_tables WHERE schemaname = 'public')
            LOOP
                EXECUTE 'DROP TABLE ' || r.tablename || ' CASCADE';
            END LOOP;
    END
$$;
COMMIT;
DROP FUNCTION IF EXISTS lock_account_on_failed_attempts();

-------------------------------------------------

create table user_types
(
    user_type_id serial primary key,
    name         varchar(50) not null unique
);
INSERT INTO user_types(user_type_id, name)
VALUES (1, 'USER');
INSERT INTO user_types(user_type_id, name)
VALUES (2, 'ADMIN');

create table animal_types
(
    id          serial primary key,
    name        varchar(50) not null,
    description text
);

create table breeds
(
    id           serial primary key,
    name         varchar(55)           not null,
    archive      boolean default false not null,
    animal_types_is integer
        constraint breeds_animal_types_id_fk references animal_types
);

create table training_levels
(
    training_level_id serial primary key,
    name              varchar(50) not null,
    description       varchar(255)
);

create table lessons
(
    lesson_id         serial primary key,
    name              varchar(50) not null,
    training_level_id integer     not null references training_levels,
    description       text,
    image_location    varchar
);

create table lesson_steps
(
    lesson_step_id serial primary key,
    name           varchar(50) not null,
    lesson_id      integer     not null references lessons,
    step_number    integer     not null,
    description    text
);
create index idx_lesson_steps_lesson_id on lesson_steps (lesson_id);

create table tags
(
    name varchar(50) not null primary key
);

create table care_announcement_types
(
    care_announcement_type_id serial primary key,
    name                      varchar(255) not null unique
);

create table time_unit
(
    time_unit_id serial primary key,
    name         varchar(10) not null unique
);

create table voivodeship
(
    id       serial
        constraint voivodeship_pk primary key,
    teryt_id varchar(2)            not null
        constraint voivodeship_pk2 unique,
    name     varchar(20)           not null,
    archive  boolean default false not null
);

create table provinces
(
    id             serial
        constraint county_pk primary key,
    teryt_id       varchar(4),
    name           varchar(25)           not null,
    voivodeship_id integer               not null
        constraint county___fk references voivodeship,
    archive        boolean default false not null
);

create table cities
(
    id          serial primary key,
    name        varchar(50)           not null,
    province_id integer               not null
        constraint cities_province_id_fk references provinces,
    archive     boolean default false not null
);

create table users
(
    user_id                  serial primary key,
    name                     varchar(50)  not null
        constraint users_user_name_key unique,
    email                    varchar(255) not null unique,
    password                 text         not null,
    profile_picture_location varchar,
    avatar_small_location    varchar,
    user_type_id             integer                  default 1 references user_types,
    city_id                  integer references cities,
    description              varchar(255),
    is_locked                boolean                  default false,
    is_banned                boolean                  default false,
    ban_expiration           timestamp with time zone,
    is_active                boolean                  default true,
    created_at               timestamp with time zone default now()
);
create index idx_users_email on users (email);
create index idx_users_user_name on users (name);

create table login_attempts
(
    login_attempt_id serial primary key,
    successful       boolean,
    ip               varchar(39),
    user_id          integer references users,
    created_at       timestamp with time zone default now()
);
create index idx_login_attempts_user_id on login_attempts (user_id);
create index idx_login_attempts_ip on login_attempts (ip);
create index idx_login_attempts_created_at on login_attempts (created_at);
create index idx_login_attempts_successful on login_attempts (successful);

------ lock appUser account after 3 failed attempts ------
CREATE OR REPLACE FUNCTION lock_account_on_failed_attempts() RETURNS TRIGGER AS
$$
BEGIN
    IF NOT NEW.successful THEN
        UPDATE users
        SET is_locked = true
        WHERE user_id = NEW.user_id
          AND (SELECT COUNT(*) FROM login_attempts WHERE user_id = NEW.user_id AND successful = false) >= 3;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER lock_account_on_failed_attempts
    AFTER INSERT
    ON login_attempts
    FOR EACH ROW
EXECUTE FUNCTION lock_account_on_failed_attempts();
-------------------------------------------------------

create table animals
(
    id               serial primary key,
    name             varchar(50) not null,
    animal_type_id   integer not null references animal_types (id),
    breed_id         integer references breeds,
    user_id          integer references users,
    birth_year       integer     not null,
    is_male          boolean     not null,
    picture_location varchar,
    description      text
);
create index idx_animals_user_id on animals (user_id);

create table lessons_animals
(
    animal_id integer not null references animals (id),
    lesson_id integer not null references lessons,
    progress  integer default 0,
    finished  boolean default false,
    primary key (animal_id, lesson_id)
);
create index idx_lessons_animals_animal_id_lesson_id on lessons_animals (animal_id, lesson_id);

create table care_announcements
(
    care_announcement_id      serial primary key,
    user_id                   integer      not null references users,
    care_announcement_type_id integer      not null references care_announcement_types,
    name                      varchar(50)  not null,
    description               varchar(255) not null,
    city_id                   integer      not null references cities,
    price                     numeric      not null,
    time_unit_id              integer      not null references time_unit
);

create table care_announcements_tags
(
    care_announcement_id integer     not null references care_announcements,
    tag_name             varchar(50) not null references tags (name),
    primary key (care_announcement_id, tag_name)
);
create index idx_care_announcements_tags_care_announcement_id_tag_name on care_announcements_tags (care_announcement_id, tag_name);

create table reviews
(
    review_id            serial primary key,
    care_announcement_id integer      not null references care_announcements,
    user_id              integer      not null references users,
    description          varchar(255) not null,
    stars                integer      not null
);
create index idx_reviews_care_announcement_id on reviews (care_announcement_id);

create table conversations
(
    conversation_id      serial primary key,
    original_sender_id   integer not null references users,
    original_receiver_id integer not null references users,
    care_announcement_id integer references care_announcements
);
create index idx_conversations_sender_id on conversations (original_sender_id);
create index idx_conversations_receiver_id on conversations (original_receiver_id);

create table messages
(
    message_id      serial primary key,
    created_at      timestamp with time zone default now(),
    conversation_id integer      not null references conversations,
    sender_id       integer      not null references users,
    receiver_id     integer      not null references users,
    message_content varchar(255) not null
);
create index idx_messages_conversation_id on messages (conversation_id);
create index idx_messages_created_at on messages (created_at);
