DROP TABLE IF EXISTS `uninterested_link`;

CREATE TABLE IF NOT EXISTS `uninterested_link`
(
    link VARCHAR(1000)
);

DROP TABLE IF EXISTS `interested_link`;

CREATE TABLE IF NOT EXISTS `interested_link`
(
    link VARCHAR(1000)
);

DROP TABLE IF EXISTS `news`;

CREATE TABLE IF NOT EXISTS `news`
(
    id         BIGINT AUTO_INCREMENT,
    title      VARCHAR(100),
    content    TEXT,
    link       VARCHAR(1000),
    created_at TIMESTAMP,
    update_at  TIMESTAMP,
    CONSTRAINT NEWS_PK
        PRIMARY KEY (id)
);

