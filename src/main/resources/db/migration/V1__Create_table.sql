drop table if exists `uninterested_link`;

create table if not exists  `uninterested_link`
(
    link VARCHAR(1000)
);

drop table if exists `interested_link`;

create table if not exists `interested_link`
(
    link VARCHAR(1000)
);

drop table if exists `news`;

create table if not exists `news`
(
    id         BIGINT auto_increment,
    title      VARCHAR(100),
    content    TEXT,
    link       VARCHAR(1000),
    created_at TIMESTAMP,
    update_at  TIMESTAMP,
    constraint NEWS_PK
        primary key (id)
);

