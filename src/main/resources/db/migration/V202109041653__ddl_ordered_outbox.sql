create table if not exists ordered_outbox
(
    outbox_id binary(36) not null,
    payload varchar(255) not null,
    primary key (outbox_id)
);