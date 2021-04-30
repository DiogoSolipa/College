--TABELAS TRABALHO1(BD)

Create table motorista (
    Nome varchar(50),
    NCartaCond char(5) primary key,
    DataNasc char(7),
    Nbi char(4)
);

insert into motorista values('Manuel Duarte' , 'L-123' , '14/1/76' , '1234');
insert into motorista values('Fernando Nobre' , 'L-124' , '14/1/77' , '1235');
insert into motorista values('Anibal Silva' , 'L-125' , '14/1/78' , '1236');
insert into motorista values('Francisco Lopes' , 'L-126' , '14/1/79' , '1237');
insert into motorista values('João Frade' , 'L-127' , '14/1/86' , '1238');
insert into motorista values('Josefina Manela' , 'L-128' , '14/1/75' , '1239');

Create table telefone (
    Nbi char(4) primary key,
    Telefone char(9),
    Telemovel char(9)
);

insert into telefone values('1234' , '266262626', '939393939');
insert into telefone values('1235' , '266262627', '939393940');
insert into telefone values('1236' , '266262628', '939393941');
insert into telefone values('1237' , '266262629', null);
insert into telefone values('1238' , '266262630', null);
insert into telefone values('1239' , '266262631', null);


Create table modelo (
    Marca varchar(20),
    Modelo varchar(30) primary key,
    Nlugares char(2),
    Consumo varchar(12)
); 

insert into modelo values('Renault' , 'Espace' , '7' , '7.1 aos 100');
insert into modelo values('Mercedes' , 'CLK' , '7' , '9.1 aos 100');
insert into modelo values('Honda' , 'Civic' , '5' , '5.1 aos 100');
insert into modelo values('Mercedes' , 'classe S' , '5' , '6.51 aos 100');
insert into modelo values('Audi' , 'R8' , '2' , '16 aos 100');
insert into modelo values('Seat' , 'Ibiza' , '5' , '20 aos 100');



Create table taxi (
    Modelo varchar(30),
    Ano char(4),
    Kms char(8),
    Matricula char(8) primary key,
    foreign key (Modelo) references modelo on delete restrict 
);

insert into taxi values('Espace' , '2015' , '123098' , '22-AA-22');
insert into taxi values('CLK' , '2014' , '234554' , '21-AA-22');
insert into taxi values('Civic' , '2012' , '89764' , '20-AA-22');
insert into taxi values('classe S' , '2015' , '79744' , '19-AA-22');
insert into taxi values('R8' , '2019' , '100' , '13-AA-23');
insert into taxi values('Ibiza' , '2004' , '1000000' , '12-AA-12');


Create table servico (
    DataInicioS varchar(20),
    DataFimS varchar(20),
    Kms char(8),
    Valor varchar(10),
    Matricula char(8),
    CoordGPSInic varchar(50),
    CordGPSfim varchar(50),
    primary key (Matricula,DataInicio),
    foreign key (Matricula) references taxi on delete restrict
);

insert into servico values('2/1/2016 as 8:12' , '2/1/2016 as 8:32' , '12' , '5.25€' , '19-AA-22' , '0,75' , '0,76');
insert into servico values('2/1/2016 as 8:43' , '2/1/2016 as 8:52' , '7' , '3.25€' , '19-AA-22' , '0.80' , '0.81');
insert into servico values('2/1/2016 as 8:53' , '2/1/2016 as 9:59' , '98' , '53.25€' , '19-AA-22' , '0.85' , '0.86');
insert into servico values('2/1/2016 as 10:13' , '2/1/2016 as 10:29' , '18' , '19.25€' , '19-AA-22' , '0.90' , '0.91');
insert into servico values('2/1/2016 as 11:10' , '2/1/2016 as 11:29' , '23' , '22.25€' , '19-AA-22' , '0.95' , '0.96');
insert into servico values('2/1/2016 as 12:00' , '2/1/2016 as 13:29' , '21' , '42.25€' , '19-AA-22' , '1.0' , '1..01');
insert into servico values('2/1/2016 as 15:20' , '2/1/2016 as 15:29' , '9' , '12.25€' , '19-AA-22' , '1.05' , '1.06');



Create table turno (
    DataInicioT varchar(20),
    DataFimT varchar(20),
    KmInicio varchar(8),
    KmFim varchar(8),
    Matricula char(8),
    Nbi char(4),
    primary key (Nbi,DataInicio),
    foreign key (Matricula) references taxi on delete restrict
);

insert into turno values('2/1/2016 as 8:00', '2/1/2016 as 17:00' , '79744' , '79944' , '19-AA-22' , '1234');
insert into turno values('2/1/2016 as 8:00', '2/1/2016 as 17:00' , '89764' , '89964' , '20-AA-22' , '1235');
insert into turno values('3/1/2016 as 8:00', '3/1/2016 as 17:00' , '234554' , '234954' , '21-AA-22' , '1236');
insert into turno values('3/1/2016 as 8:00', '3/1/2016 as 17:00' , '123098' , '123498' , '22-AA-22' , '1237');
insert into turno values('4/1/2016 as 8:00', '4/1/2016 as 17:00' , '100' , '150' , '13-AA-23' , '1238');
insert into turno values('4/1/2016 as 8:00', '4/1/2016 as 17:00' , '1000000' , '1001000' , '12-AA-12' , '1239');


Create table cliente1 (
    Nome varchar(50),
    Morada varchar(100),
    CodigoPostal varchar(20),
    Nif char(12) primary key
); 

insert into cliente1 values('Jose Silva' , 'Rua Antonio Silva 23' , '7100-434 Evora' , '600700800900');
insert into cliente1 values('Francisco Passos' , 'Rua Manuel Passos 12' , '7000-131 Evora' , '600700800901');
insert into cliente1 values('Pedro Sousa' , 'Rua Joaquim Sousa 21' , '7500-313 Evora' , '600700800902');
insert into cliente1 values('Gonçalo Cardoso' , 'Rua do Giraldo' , '7500-512 Evora' , '600700800903');
insert into cliente1 values('Filipe Sousa' , 'Rua Joaquim Sousa 21' , '7500-131 Evora' , '600700800904');
insert into cliente1 values('Mandela carvalho' , 'Rua Serpa Pinto 2' , '7500-400 Evora' , '600700800905');
insert into cliente1 values('André Postiga' , 'Rua da Piedade' , '7500-001 Evora' , '600700800906');
insert into cliente1 values('Teddy Fast' , 'Rua Internacional' , '7500-747 Evora' , '600700800907');



Create table pedido (
    Nif char(12) ,
    MoradaInicio varchar(100),
    CodigoPostalInicio varchar(20),
    DataPedido varchar(20),
    Matricula char(8),
    DataInicio varchar(20),
    primary key (Nif, DataPedido),
    foreign key (Nif) references cliente1 on  delete restrict
);

insert into pedido values('600700800900' , 'Rua Silva Pais 33' , '7120-212 Evora' , '2/1/2016 as 9:00' , '19-AA-22' , '2/1/2016 as 9:43');
insert into pedido values('600700800901' , 'Rua Pais Silva 12' , '7120-213 Evora' , '2/1/2016 as 10:00' , '20-AA-22' , '2/1/2016 as 10:43');
insert into pedido values('600700800902' , 'Rua Castro Oliveira 5' , '7120-214 Evora' , '2/1/2016 as 11:00' , '21-AA-22' , '2/1/2016 as 11:43');
insert into pedido values('600700800903' , 'Rua Ideias Zero 3' , '7120-215 Evora' , '2/1/2016 as 12:00' , '22-AA-22' , '2/1/2016 as 12:43');
insert into pedido values('600700800904' , 'Rua do Bolso 2' , '7120-216 Evora' , '2/1/2016 as 13:00' , '13-AA-23' , '2/1/2016 as 13:43');
insert into pedido values('600700800905' , 'Rua da Telepizza 5' , '7120-217 Evora' , '2/1/2016 as 14:00' , '12-AA-12' , '2/1/2016 as 14:43');
insert into pedido values('600700800906' , 'Rua do McDonaldo 6' , '7120-218 Evora' , '2/1/2016 as 15:00' , '19-AA-22' , '2/1/2016 as 15:43');
insert into pedido values('600700800907' , 'Rua do Desespero 1111' , '7120-000 Evora' , '2/1/2016 as 16:00' , '22-AA-22' , '2/1/2016 as 16:43');
