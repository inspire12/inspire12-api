create table inspire12_api.User (id varchar(255) not null auto_increment, created_date datetime(6), modified_date datetime(6), email varchar(255) not null, name varchar(255) not null, picture varchar(255), role varchar(255) not null, primary key (id)) engine=InnoDB

create table inspire12_community.PostContent
(
    id      int auto_increment
        primary key,
    pos     int          null,
    postId  int          null,
    content varchar(255) null
);

create table inspire12_community.Posts
(
    id           int auto_increment
        primary key,
    title        varchar(255) null,
    content      varchar(255) null,
    author       varchar(127) null,
    createdDate  datetime     null,
    modifiedDate datetime     null
);
