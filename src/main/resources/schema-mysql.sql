
create table User (id varchar(255) not null auto_increment, created_date datetime(6), modified_date datetime(6), email varchar(255) not null, name varchar(255) not null, picture varchar(255), role varchar(255) not null, primary key (id)) engine=InnoDB

create table PostContent
(
    id      int auto_increment
        primary key,
    pos     int          null,
    postId  int          null,
    content varchar(255) null
);

create table Posts
(
    id           int auto_increment
        primary key,
    title        varchar(255) null,
    content      varchar(255) null,
    author       varchar(127) null,
    createdDate  datetime     null,
    modifiedDate datetime     null
);
INSERT INTO Posts (title, content, author, createdDate, modifiedDate) VALUES ('안녕', '디지몬', '서영학', '2021-11-25 11:08:00', '2021-11-25 11:08:04');

