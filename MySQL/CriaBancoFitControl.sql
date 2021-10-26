create database Fitcontroldb;
use Fitcontroldb;
create table tbUsuarios(
	id int not null auto_increment,
    Nome varchar(50) unique not null,
    Telefone varchar(50) unique not null,
    Email varchar(50) unique not null,
    Senha varchar(200) not null,
    _Status int8,
    Pontos int default 0,
    NivelAcesso int8 not null,
    Primary key (id)
);
create table tbCatracas(
	id int not null auto_increment,
    Nome varchar(50) unique not null,
    Modelo varchar(50) unique not null,
    Tipo int8 not null,
    _Status int8,
    Primary key (id)
);
create table tbAcesso(
	id int not null auto_increment,
    _Data datetime not null,
    tipo int8 not null,
    idFuncionario int not null,
    idCliente int not null,
    idCatraca int not null,
    foreign key (idFuncionario) references tbUsuarios (id),
    foreign key (idCliente) references tbUsuarios (id),
	foreign key (idCatraca) references tbCatracas (id),
    Primary key (id)
);
create table tbRecompensa(
	id int not null auto_increment,
    Titulo  varchar(50) unique not null,
    Pontos int not null,
    Primary key (id)
);
create table tbItensRecompensa(
	id int not null auto_increment,
    Descricao  varchar(50) unique not null,
    Foto  varchar(50) unique not null,
    Primary key (id)
);
create table tbItensXRecompensa(
	idItens int not null,
    idRecompensa int not null,
    foreign key (idItens) references tbItensRecompensa (id),
    foreign key (idRecompensa) references tbRecompensa (id)
);
create table tbRecompensaHist(
	id int not null auto_increment,
    _Data datetime not null,
    Pontos int not null,
	Titulo  varchar(50) unique not null,
    IdCliente int not null,
    foreign key (IdCliente) references tbUsuarios (id),
	Primary key (id)
);
create table tbPontuacaoHist(
	id int not null auto_increment,
    Pontos int not null,
    idAcesso int,
    idCliente int not null,
	foreign key (idAcesso) references tbAcesso (id),
    foreign key (idCliente) references tbUsuarios (id),
    Primary key (id)
);
create table tbPagamentos(
	id int not null auto_increment,
    _Data datetime not null,
    idCliente int not null,
    idFuncionario int not null,
    Valor real not null,
    foreign key (idFuncionario) references tbUsuarios (id),
    foreign key (idCliente) references tbUsuarios (id),
    Primary key (id)
);
insert into tbUsuarios (Nome,Telefone,Email,Senha,_Status,NivelAcesso) Values ('RODRIGO','11','R','123',1,2); 
select * from tbUsuarios where Email = 'R' and Senha = '123'

select * from tbUsuarios and _Status <> 0
