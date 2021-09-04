create table if not exists outbox
(
    outbox_id binary(36) not null,
    aggregate_id bigint not null,
    aggregate_type varchar(50) not null,
    payload varchar(255) not null,
    event_type varchar(50) not null,
    primary key (outbox_id)
);
