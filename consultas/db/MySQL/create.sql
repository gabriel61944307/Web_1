DROP DATABASE IF EXISTS Consultorio;

CREATE DATABASE Consultorio;

USE Consultorio;

CREATE TABLE Usuario (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(256) NOT NULL,
    email VARCHAR(256) NOT NULL,
    senha VARCHAR(64) NOT NULL,
    papel VARCHAR(10),
    PRIMARY KEY (id)
);

CREATE TABLE Paciente (
    id BIGINT NOT NULL PRIMARY KEY,
    cpf VARCHAR(11) NOT NULL,
    telefone VARCHAR(20),
    sexo VARCHAR(20),
    data_nascimento DATE,
    FOREIGN KEY (id) REFERENCES Usuario(id)
);

CREATE TABLE Medico (
    id BIGINT NOT NULL PRIMARY KEY,
    crm VARCHAR(10) NOT NULL,
    especialidade VARCHAR(256) NOT NULL,
    FOREIGN KEY (id) REFERENCES Usuario(id)
);

CREATE TABLE Consulta (
    id BIGINT NOT NULL AUTO_INCREMENT,
    cpf_paciente VARCHAR(11) NOT NULL,
    crm_medico VARCHAR(10) NOT NULL,
    data_hora DATETIME NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO Usuario(nome, email, senha, papel) VALUES
    ('Paciente Teste', 'paciente@email.com', 'senha', 'PACIENTE');

INSERT INTO Paciente(id, cpf, telefone, sexo, data_nascimento) VALUES
    (LAST_INSERT_ID(), '12345678901', '1234-5678', 'M', '1990-01-01');

INSERT INTO Usuario(nome, email, senha, papel) VALUES
    ('Doutor Teste', 'medico@email.com', 'senha', 'MEDICO');

INSERT INTO Medico(id, crm, especialidade) VALUES
    (LAST_INSERT_ID(), '12345', 'Cardiologia');

INSERT INTO Usuario(nome, email, senha, papel) VALUES
    ('Administrador', 'admin@email.com', 'admin', 'ADMIN');