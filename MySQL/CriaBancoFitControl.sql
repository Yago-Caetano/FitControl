create database Fitcontroldb;
use Fitcontroldb;
create table tbUsuarios(
	id varchar(50) not null ,
    Nome varchar(50) unique not null,
    Telefone varchar(50)  not null,
    Email varchar(50) unique not null,
    Senha varchar(200) not null,
    _Status int8,
    Pontos int default 0,
    NivelAcesso int8 not null,
    Primary key (id)
);
create table tbCatracas(
	id varchar(50) not null,
    Nome varchar(50) unique not null,
    Modelo varchar(50) unique not null,
    Tipo int8 not null,
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
    foreign key (idFuncionario) references tbUsuarios (id),
    foreign key (idCliente) references tbUsuarios (id),
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
    foreign key (IdCliente) references tbUsuarios (id),
	Primary key (id)
);
create table tbPontuacaoHist(
	id varchar(50) not null,
    Pontos int not null,
    idAcesso varchar(50),
    idCliente varchar(50) not null,
	foreign key (idAcesso) references tbAcesso (id),
    foreign key (idCliente) references tbUsuarios (id),
    Primary key (id)
);
create table tbPagamentos(
	id varchar(50) not null,
    _Data datetime not null,
    idCliente varchar(50) not null,
    idFuncionario varchar(50) not null,
    Valor real not null,
    foreign key (idFuncionario) references tbUsuarios (id),
    foreign key (idCliente) references tbUsuarios (id),
    Primary key (id)
);
select * from tbUsuarios
insert into tbUsuarios (id,Nome,Telefone,Email,Senha,_Status,NivelAcesso) Values ('111','Cliente 1','11111','R132','1212313',1,1); 
insert into tbUsuarios (id,Nome,Telefone,Email,Senha,_Status,NivelAcesso) Values ('123','Funcionario','213124','rodrigo','1212313',1,2); 
select * from tbUsuarios where Email = 'R' and Senha = '123'

insert into tbCatracas(id ,Nome ,Modelo ,Tipo ,_Status) Values ('11','Catraca 1','Modelo 1',1,1);

Select tbA._Data as Data, tbU2.Nome as Funcionario,tbc.Nome as Catraca,tbU.Nome as Cliente from tbAcesso tbA inner join tbUsuarios tbU on tbA.idCliente=tbU.id inner join tbUsuarios tbU2 on tbA.idFuncionario=tbU2.Nome inner join tbCatracas tbc on tbc.id=tbA.idCatraca where tbA._Data >= '2021-11-09 00:00:00' and tbA._Data <= '2021-11-20 00:00:00'

Select tbA._Data as Data,tbU.Nome as Cliente,tbU2.Nome as Funcionario,tbc.Nome as Catraca from tbAcesso tbA 
inner join tbUsuarios tbU on tbA.idCliente=tbU.id
inner join tbUsuarios tbU2 on tbA.idFuncionario=tbU2.id inner join tbCatracas tbc on tbc.id=tbA.idCatraca where 
tbA._Data >='2021-11-09' and tbA._Data <='2021-11-11 '  and tbU.NivelAcesso=1 

update tbUsuarios Set Nome='Cliente 1' where id='111'


Select * from tbAcesso
delete  from tbAcesso where id ='4'
insert into tbAcesso (id,_Data,tipo,idFuncionario,idCliente,idCatraca) Values ('2','2021-11-10',1,'123','111','11')
insert into tbAcesso (id,_Data,tipo,idFuncionario,idCatraca) Values ('4','2021-11-10',1,'123','11')
select * from tbUsuarios and _Status <> 0
