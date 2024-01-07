-- DCL
use mysql;
create user 'sloopuser'@'localhost' identified by 'sloopuser';
create user 'sloopuser'@'%' identified by 'sloopuser';
select host, user, password from user;


create DATABASE sloop DEFAULT CHARACTER SET = 'utf8mb4';


grant all privileges on sloop.* to 'sloopuser'@'%';
grant all privileges on sloop.* to 'sloopuser'@'localhost';