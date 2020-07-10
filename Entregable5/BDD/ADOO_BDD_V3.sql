CREATE TABLE Usuario
(
  Nombre VARCHAR(30) NOT NULL,
  APaterno VARCHAR(30) NOT NULL,
  AMaterno VARCHAR(30) NOT NULL,
  Contrasena VARCHAR(50) NOT NULL,
  IdUsuario VARCHAR(8) NOT NULL,
  fechaRegistro DATE NOT NULL,
  Admin INT(1) NOT NULL,
  PRIMARY KEY (IdUsuario)
);

CREATE TABLE Lugar
(
  IdLugar INT NOT NULL,
  Nombre VARCHAR(50) NOT NULL,
  PRIMARY KEY (IdLugar)
);

CREATE TABLE LED
(
  IdLed INT NOT NULL,
  Intensidad INT,
  IntenCaracter CHAR NOT NULL,
  Fecha DATE NOT NULL,
  Hora TIME NOT NULL,
  IdLugar INT NOT NULL,
  PRIMARY KEY (IdLed),
  FOREIGN KEY (IdLugar) REFERENCES Lugar(IdLugar)
);

CREATE TABLE SensorPIR
(
  IdPir INT NOT NULL,
  Detectado INT(1) NOT NULL,
  Fecha DATE NOT NULL,
  Hora TIME NOT NULL,
  IdLugar INT NOT NULL,
  PRIMARY KEY (IdPir),
  FOREIGN KEY (IdLugar) REFERENCES Lugar(IdLugar)
);

CREATE TABLE SensorSonido
(
  IdSon INT NOT NULL,
  Detectado INT(1) NOT NULL,
  Fecha DATE NOT NULL,
  Hora TIME NOT NULL,
  IdLugar INT NOT NULL,
  PRIMARY KEY (IdSon),
  FOREIGN KEY (IdLugar) REFERENCES Lugar(IdLugar)
);

CREATE TABLE SensorHumo
(
  IdHumo INT NOT NULL,
  Detectado INT(1) NOT NULL,
  Fecha DATE NOT NULL,
  Hora TIME NOT NULL,
  IdLugar INT NOT NULL,
  PRIMARY KEY (IdHumo),
  FOREIGN KEY (IdLugar) REFERENCES Lugar(IdLugar)
);

CREATE TABLE TempHum
(
  IdTH INT NOT NULL,
  Temperatura INT(3) NOT NULL,
  Humedad INT(3) NOT NULL,
  Fecha DATE NOT NULL,
  Hora TIME NOT NULL,
  IdLugar INT NOT NULL,
  PRIMARY KEY (IdTH),
  FOREIGN KEY (IdLugar) REFERENCES Lugar(IdLugar)
);

CREATE TABLE Controla
(
  IdUsuario VARCHAR(8) NOT NULL,
  IdLugar INT NOT NULL,
  PRIMARY KEY (IdUsuario, IdLugar),
  FOREIGN KEY (IdUsuario) REFERENCES Usuario(IdUsuario),
  FOREIGN KEY (IdLugar) REFERENCES Lugar(IdLugar)
);

CREATE TABLE Ventilador
(
  IdVent INT NOT NULL,
  Temperatura INT(1) NOT NULL,
  Humedad INT(1) NOT NULL,
  Humo INT(1) NOT NULL,
  Usuario CHAR NOT NULL,
  Fecha DATE NOT NULL,
  Hora TIME NOT NULL,
  IdLugar INT NOT NULL,
  PRIMARY KEY (IdVent),
  FOREIGN KEY (IdLugar) REFERENCES Lugar(IdLugar)
);