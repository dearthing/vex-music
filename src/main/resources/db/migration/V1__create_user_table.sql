create table user (
    id varchar(32) not null primary key commit '用户id',
    username varchar(64) not null commit '用户名',
    password varchar(64) not null commit '加密后的密码',
    gender varchar(255) null commit '性别',
    email varchar(255) null commit  '邮箱'
)