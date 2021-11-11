create database Fitcontroldb;
use Fitcontroldb;
create table tbFuncionarios(
	id varchar(50) not null ,
    Nome varchar(50) unique not null,
    Telefone varchar(50)  not null,
    Email varchar(50) unique not null,
    Senha varchar(200) not null,
    _Status int8,
    NivelAcesso int8 not null,
    Primary key (id)
);
create table tbClientes(
	id varchar(50) not null ,
    Nome varchar(50) unique not null,
    Telefone varchar(50)  not null,
    Email varchar(50) unique not null,
    _Status int8,
    Pontos int default 0,
    Primary key (id)
);
create table tbCatracas(
	id varchar(50) not null,
    Nome varchar(50) unique not null,
    _Status int8,
    Primary key (id)
);
create table tbAcesso(
	id varchar(50) not null,
    _Data datetime not null,
    tipo int8 not null,
    idFuncionario varchar(50) ,
    idCliente varchar(50) ,
    idCatraca varchar(50) not null,
    foreign key (idFuncionario) references tbFuncionarios (id),
    foreign key (idCliente) references tbClientes (id),
	foreign key (idCatraca) references tbCatracas (id),
    Primary key (id)
);
create table tbRecompensa(
	id varchar(50) not null,
    Titulo  varchar(50) unique not null,
    Pontos int not null,
    Primary key (id)
);
create table tbItensRecompensa(
	id varchar(50) not null,
    Descricao  varchar(50) unique not null,
    Foto  varchar(50) unique not null,
    Primary key (id)
);
create table tbItensXRecompensa(
	idItens varchar(50) not null ,
    idRecompensa varchar(50) not null,
    foreign key (idItens) references tbItensRecompensa (id),
    foreign key (idRecompensa) references tbRecompensa (id)
);
create table tbRecompensaHist(
	id varchar(50) not null,
    _Data datetime not null,
    Pontos int not null,
	Titulo  varchar(50) unique not null,
    IdCliente varchar(50) not null,
    foreign key (IdCliente) references tbClientes (id),
	Primary key (id)
);
create table tbPontuacaoHist(
	id varchar(50) not null,
    Pontos int not null,
    idAcesso varchar(50),
    idCliente varchar(50) not null,
	foreign key (idAcesso) references tbAcesso (id),
    foreign key (idCliente) references tbClientes (id),
    Primary key (id)
);
create table tbPagamentos(
	id varchar(50) not null,
    _Data datetime not null,
    idCliente varchar(50) not null,
    idFuncionario varchar(50) not null,
    Valor real not null,
    foreign key (idFuncionario) references tbFuncionarios (id),
    foreign key (idCliente) references tbClientes (id),
    Primary key (id)
);

