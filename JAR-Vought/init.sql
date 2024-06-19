USE Noctoramento;



CREATE TABLE Empresa (
    idEmpresa INT PRIMARY KEY AUTO_INCREMENT,
    razaoSocial VARCHAR(45),
    cnpjEmpresa CHAR(14),
    email VARCHAR(45),
    senha VARCHAR(45)
);




CREATE TABLE Cargo (
    idCargo INT PRIMARY KEY AUTO_INCREMENT,
    nomeCargo VARCHAR(45) UNIQUE NOT NULL
);

INSERT INTO Cargo (nomeCargo) VALUES
    ('CEO'),
    ('Gestor'),
    ('Gerente'),
    ('Analista'),
    ('Desenvolvedor'),
    ('Estagiário'),
    ('Suporte');



CREATE TABLE Usuario (
    idUsuario INT AUTO_INCREMENT,
    nomeUsuario VARCHAR(45),
    emailUsuario VARCHAR(45),
    senhaUsuario VARCHAR(45),
    fkEmpresa INT,
    fkCargo INT,
    CONSTRAINT fkEmpresa FOREIGN KEY (fkEmpresa) REFERENCES Empresa (idEmpresa),
    CONSTRAINT fkCargo FOREIGN KEY (fkCargo) REFERENCES Cargo (idCargo),
    PRIMARY KEY (idUsuario, fkEmpresa)
);



CREATE TABLE Notebook(
    idNotebook INT AUTO_INCREMENT,
    numeroSerie VARCHAR(45),
    fabricante VARCHAR(45),
    modelo VARCHAR(45),
    dtRegistro TIMESTAMP DEFAULT NOW(),
    fkEmpresa INT,
    CONSTRAINT fkEmpresaNotebook FOREIGN KEY (fkEmpresa) REFERENCES Empresa (idEmpresa),
    PRIMARY KEY (idNotebook, fkEmpresa)
);



CREATE TABLE InfoNotebook(
    idInfoNotebook INT AUTO_INCREMENT,
    sistemaOperacional VARCHAR(45),
    processador VARCHAR(45),
    capacidadeMaxCpu DOUBLE,
    maxDisco DOUBLE,
    maxMemoriaRam DOUBLE,
    fkNotebook INT,
    fkEmpresa INT,
    CONSTRAINT fkEmpresaInfoNotebook FOREIGN KEY (fkEmpresa) REFERENCES Empresa (idEmpresa),
    CONSTRAINT fkNotebook FOREIGN KEY (fkNotebook) REFERENCES Notebook (idNotebook),
    PRIMARY KEY (idInfoNotebook, fkEmpresa, fkNotebook)
);



CREATE TABLE Alocacao(
    dataUsoInicio DATE,
    dataUsoFim DATE,
    fkNotebook INT,
    fkEmpresaNotebook INT,
    fkUsuario INT,
    fkEmpresaUsuario INT,
    CONSTRAINT fkNotebookAlocacao FOREIGN KEY (fkNotebook) REFERENCES Notebook (idNotebook),
    CONSTRAINT fkEmpresaNotebookAlocacao FOREIGN KEY (fkEmpresaNotebook) REFERENCES Notebook (fkEmpresa),
    CONSTRAINT fkUsuarioAlocacao FOREIGN KEY (fkUsuario) REFERENCES Usuario (idUsuario),
    CONSTRAINT fkEmpresaUsuarioAlocacao FOREIGN KEY (fkEmpresaUsuario) REFERENCES Usuario (fkEmpresa),
    PRIMARY KEY (fkNotebook, fkEmpresaNotebook, fkUsuario, fkEmpresaUsuario)
);



CREATE TABLE Parametros(
    idParametros INT AUTO_INCREMENT,
    tempoSegCapturaDeDados INT,
    tempoSegAlertas INT,
    UsoCriticoCpu INT,
    UsoCriticoDisco INT,
    UsoCriticoMemoriaRam INT,
    UsoAlarmanteCpu INT,
    UsoAlarmanteDisco INT,
    UsoAlarmanteMemoriaRam INT,
    UsoNormalCpu INT,
    UsoNormalDisco INT,
    UsoNormalMemoriaRam INT,
    fkEmpresa INT,
    CONSTRAINT fkEmpresaParametros FOREIGN KEY (fkEmpresa) REFERENCES Empresa (idEmpresa),
    PRIMARY KEY (idParametros, fkEmpresa)
);





CREATE TABLE RegistroUsoNotebook(
idRegistroUsoNotebook INT AUTO_INCREMENT,
usoCpu DOUBLE,
usoDisco DOUBLE,
tempoAtividadeDisco VARCHAR(45),
usoMemoriaRam DOUBLE,
qtdJanelasEmUso INT,
dtHoraCaptura DATETIME,
fkNotebook INT,
fkEmpresa INT,
CONSTRAINT fkNotebookRegistroUsoNotebook FOREIGN KEY (fkNotebook) REFERENCES Notebook (idNotebook),
CONSTRAINT fkEmpresaRegistroUsoNotebook FOREIGN KEY (fkEmpresa) REFERENCES Notebook (fkEmpresa),
PRIMARY KEY (idRegistroUsoNotebook, fkNotebook, fkEmpresa)
);



CREATE TABLE Alerta(
    idAlerta INT AUTO_INCREMENT,
    fkParametros INT,
    fkEmpresaParametros INT,
    dtHoraAlerta TIMESTAMP DEFAULT NOW(),
    CONSTRAINT fkParametrosAlerta FOREIGN KEY (fkParametros) REFERENCES Parametros (idParametros),
    CONSTRAINT fkEmpresaParametrosAlerta FOREIGN KEY (fkEmpresaParametros) REFERENCES Parametros (fkEmpresa),
    PRIMARY KEY (idAlerta, fkParametros, fkEmpresaParametros)
);