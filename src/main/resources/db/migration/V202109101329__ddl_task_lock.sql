create table if not exists task_lock
(
    task varchar(100) not null,
    locked_time datetime not null,
    locked_until datetime not null,
    locked_by varchar(100) not null,
    primary key (task)
);
