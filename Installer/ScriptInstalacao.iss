[Setup]
AppName=FitControl
AppVerName=FitControl
DefaultGroupName=FitControl
; nota debe cambiar el camino hacia el directorio    \InstaladorParaMysql\
OutputDir=C:\Users\rodri\Desktop\Instalador script
AppPublisher=Andromeda FitControl
AppVersion=1.0
AllowNoIcons=false
AppCopyright=
PrivilegesRequired=admin

; Este es el nombre del archivo exe que se va a generar
OutputBaseFilename=FitControl_Setup
; Esta es la carpeta de instalación por defecto. OJO: {pf} es una variable propia de
; innosetup y significa la carpeta de Archivos de programa (o Program files si es
; un windows en inglés)
DefaultDirName={pf}\FitControl
[Languages]
;Name: "English"; MessagesFile: "compiler: Languages \ English.isl";

[Tasks]
; Esto no se toca. Es la indicación para innosetup de que debe crear los íconos necesarios
; para iniciar el programa y para desinstalarlo
Name: desktopicon; Description: Create a &desktop icon; GroupDescription: Additional icons:

[Files]
; OJO: antes que todo. Los parámetros: regserver restartreplace shared file, etc. son
; parámetros que tienen que ir tal y como aparecen acá. Cuesta un poco comprenderlos.
; Por ahora los dejamos tal y como están acá.
; Otra cosa: {sys} = carpeta system de windows
;            {win} = carpeta windows de windows
;            {cf} = carpeta archivos comunes de windows
;            {tmp} = carpeta temporal de windows
;            {app} = carpeta donde se va a instalar el programa (fue definida arriba en el parámetro: DefaultDirName=
; -------------------------------------------------------------------------------------
; Aquí van los archivos de la aplicación: el .exe y otros que ocupe el programa    everyone-full

;C:\Users\sebasw7\Desktop\youtube\InstaladorParaMysql
; NOTA NOTA NOTA IMPORTANTE importante cambiar el Source a su camino personal de su pc hasta el directorio de \InstaladorParaMysql
Source: "C:\Users\rodri\Desktop\Instalador script\Bin\FitControl.exe"; DestDir: "{app}"; Flags: "ignoreversion" ; Permissions: "everyone-modify"; 

;source: "C:\Users\rodri\Desktop\Teste\Bin\mysql*"; DestDir: "{app}"; Flags: "ignoreversion recursesubdirs createallsubdirs"; Permissions: "everyone-modify"; 


;Source: "C:\Users\rodri\Desktop\Instalador script\Teste\Bin\mysql\*"; DestDir: "{tmp}"; Flags: "ignoreversion deleteafterinstall";Permissions: "everyone-modify"; 

[Registry]
Root: HKCU; Subkey: "Environment"; ValueType:string; ValueName: "FIT_CONTROL_DB_USER"; \
    ValueData: "fitcontrol"; Flags: preservestringtype
    Root: HKCU; Subkey: "Environment"; ValueType:string; ValueName: "FIT_CONTROL_DB_PSW"; \
    ValueData: "fitcontrol"; Flags: preservestringtype

[Setup]
; Tell Windows Explorer to reload the environment
ChangesEnvironment=yes

[INI]

[Icons]
; Estos son los íconos que el instalador va a crear en el grupo de programas.
; Aquí se incluye: el ícono para abrir el programa, el ícono para desinstalar el programa
; y el ícono que se ubica en el escritorio
; OJO: {group} = nombre del grupo de programa que se definió arriba en el parámetro: DefaultGroupName=
;Name: {group}\EjecutaHeidisql; Filename: {app}\heidisql.exe; WorkingDir: {app}; IconIndex: 0
;Name: {group}\Desinstalar EjecutaHeidisql; Filename: {uninstallexe}
;Name: {userdesktop}\EjecutaHeidisql; Filename: {app}\heidisql.exe; Tasks: desktopicon; WorkingDir: {app}; IconIndex: 0

[Run]
;Filename: msiexec ;Parameters: "/i ""{tmp}\mysql-installer-web-community-8.0.27.0.msi"" /qn  INSTALLDIR=""{pf}\FitControl\mysql""";WorkingDir:{tmp}; StatusMsg: "Instalando Motor de Base de Datos"; Description: "Instalar Motor de Base de Datos"; Flags: runhidden; 

;Filename: C:\mysql\bin\mysqld.exe; Parameters: --install; WorkingDir: C:\mysql\bin; StatusMsg: Instalando Servicio MySQL; Description: Instalando  MySQL Server ; Flags: runhidden



;Filename: net.exe; Parameters: start mysql; StatusMsg: Iniciando Servicio MySQL; Description: Iniciar Servicio MySQL; Flags: runhidden

;Filename: C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe; Parameters: "-uroot -p 123456";     WorkingDir: C:\Program Files\MySQL\MySQL Server 8.0\bin; StatusMsg: Creando usuario; Flags: runhidden

                            
                            
Filename: C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe; Parameters: "--user=root --password=123456 -e ""create database FitControlDB;" ;  WorkingDir: C:\Program Files\MySQL\MySQL Server 8.0\bin; StatusMsg: Criando Database; Flags: runhidden
Filename: C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe; Parameters: "--user=root --password=123456 -e ""CREATE USER 'fitcontrol' IDENTIFIED BY 'fitcontrol'" ;  WorkingDir: C:\Program Files\MySQL\MySQL Server 8.0\bin; StatusMsg: Criando usuário; Flags: runhidden                                                    
Filename: C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe; Parameters: "--user=root --password=123456 -e ""GRANT ALL PRIVILEGES ON * . * TO 'fitcontrol'";  WorkingDir: C:\Program Files\MySQL\MySQL Server 8.0\bin; StatusMsg: Criando usuário; Flags: runhidden 
Filename: C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe; Parameters: "--user=root --password=123456 -e ""grant all on FitControlDB.* to 'fitcontrol'";  WorkingDir: C:\Program Files\MySQL\MySQL Server 8.0\bin; StatusMsg: Criando usuário; Flags: runhidden
Filename: C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe; Parameters: "--user=root --password=123456 -e ""FLUSH PRIVILEGES";  WorkingDir: C:\Program Files\MySQL\MySQL Server 8.0\bin; StatusMsg: Criando usuário; Flags: runhidden

;Filename: C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe; Parameters: "create database FitControlDB ";  WorkingDir: C:\Program Files\MySQL\MySQL Server 8.0\bin; StatusMsg: Creando la Base dedatos; Flags: runhidden
;Filename: C:\mysql\bin\mysql.exe; Parameters: "-uroot -hlocalhost -pPasswordRoot -e ""CREATE USER 'sebas'@'localhost' IDENTIFIED BY '1024864'";   WorkingDir: {tmp}; StatusMsg: Creando usuario; Flags: runhidden
;Filename: C:\mysql\bin\mysql.exe; Parameters: "-uroot -hlocalhost -pPasswordRoot -e ""GRANT ALL PRIVILEGES ON * . * TO 'sebas'@'localhost'";   WorkingDir: {tmp}; StatusMsg:  usuario privilegios; Flags: runhidden
;Filename: C:\mysql\bin\mysql.exe; Parameters: "-uroot -hlocalhost -pPasswordRoot -e ""grant all on congreso.* to 'sebas'@'localhost'";  WorkingDir: {tmp}; StatusMsg: Otorgando permisos a base de dato; Flags: runhidden
;Filename: C:\mysql\bin\mysql.exe; Parameters: "-uroot -hlocalhost -pPasswordRoot -e ""FLUSH PRIVILEGES";   WorkingDir: {tmp}; StatusMsg: terminado usuario; Flags: runhidden




                                                                                                                                                                             


;Filename: C:\mysql\bin\mysql.exe; Parameters: "-usebas -hlocalhost -p1024864  -e ""use congreso; source congreso.sql;";  WorkingDir: {tmp}; StatusMsg: Base de Dato Congreso ingresando mas de 3000 registro paciencia; Flags: runhidden
Filename: C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe; Parameters: "--user=root --password=123456 -e ""use FitControlDB; create table tbFuncionarios( id varchar(50) not null , Nome varchar(50) unique not null, Telefone varchar(50) not null, Email varchar(50) unique not null, Senha varchar(200) not null, _Status int8, NivelAcesso int8 not null, Primary key (id) ); create table tbClientes( id varchar(50) not null , Nome varchar(50) unique not null, Telefone varchar(50) not null, Email varchar(50) unique not null, _Status int8, Pontos int default 0, Primary key (id) ); create table tbCatracas( id varchar(50) not null, Nome varchar(50) unique not null, _Status int8, Primary key (id) ); create table tbAcesso( id varchar(50) not null, _Data datetime not null, tipo int8 not null, idFuncionario varchar(50) , idCliente varchar(50) , idCatraca varchar(50) not null, foreign key (idFuncionario) references tbFuncionarios (id), foreign key (idCliente) references tbClientes (id), foreign key (idCatraca) references tbCatracas (id), Primary key (id) ); create table tbRecompensa( id varchar(50) not null, Titulo varchar(50) unique not null, Pontos int not null, Primary key (id) ); create table tbItensRecompensa( id varchar(50) not null, Descricao varchar(50) unique not null, Foto varchar(50) unique not null, Primary key (id) ); create table tbItensXRecompensa( idItens varchar(50) not null , idRecompensa varchar(50) not null, foreign key (idItens) references tbItensRecompensa (id), foreign key (idRecompensa) references tbRecompensa (id) ); create table tbRecompensaHist( id varchar(50) not null, _Data datetime not null, Pontos int not null, Titulo varchar(50) unique not null, IdCliente varchar(50) not null, foreign key (IdCliente) references tbClientes (id), Primary key (id) ); create table tbPontuacaoHist( id varchar(50) not null, Pontos int not null, idAcesso varchar(50), idCliente varchar(50) not null, foreign key (idAcesso) references tbAcesso (id), foreign key (idCliente) references tbClientes (id), Primary key (id) ); create table tbPagamentos( id varchar(50) not null, _Data datetime not null, idCliente varchar(50) not null, idFuncionario varchar(50) not null, Valor real not null, foreign key (idFuncionario) references tbFuncionarios (id), foreign key (idCliente) references tbClientes (id), Primary key (id) ); insert into tbFuncionarios (id,Nome,Telefone,Email,Senha,_Status,NivelAcesso) Values ('1','FitControl','tel','admin','admin',1,3);";   WorkingDir: C:\Program Files\MySQL\MySQL Server 8.0\bin; StatusMsg: Criando tabelas, relacionamentos e dados; Flags: runhidden


;[Messages]
; Estos mensajes simplemente son un override de los mensajes de Innosetup ya que vienen
; en inglés.
;WelcomeLabel1=Instalación de gestor de base de datos para hacer purebas de tablas y usuarios creados con sus respectivas password.
;WelcomeLabel2=Este proceso instalará Punto de venta CONGRESO en otros videos tutoriales .%n%nSe recomienda cerrar todas las aplicaciones abiertas%nantes de continuar.

