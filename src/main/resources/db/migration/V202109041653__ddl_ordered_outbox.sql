create table if not exists outbox
(
    outbox_id bigint not null auto_increment,
    aggregate_id bigint not null,
    aggregate_type varchar(50) not null,
    payload varchar(255) not null,
    event_type varchar(50) not null,
    primary key (outbox_id)
);
