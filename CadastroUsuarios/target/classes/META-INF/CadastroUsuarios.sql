create table usuario
(
nome varchar(100) not null,
email varchar(100) not null primary key,
senha varchar(1100) not null
);

create table telefone
(
id int not null primary key,
ddd int not null,
numero varchar(100) not null,
tipo varchar(100) not null,
usuario varchar(100) not null,
foreign key (usuario) references usuario(email)
);


create sequence s_telefone minvalue 1 maxvalue 9999999 increment by 1 start with 1;
