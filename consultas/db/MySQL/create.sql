-- Limpar banco de dados existente
DROP DATABASE IF EXISTS Consultorio;

-- Criar novo banco de dados
CREATE DATABASE Consultorio;

-- Selecionar o banco de dados
USE Consultorio;

-- Criação da tabela Paciente
CREATE TABLE Paciente (
    id BIGINT NOT NULL AUTO_INCREMENT,
    email VARCHAR(256) NOT NULL,
    senha VARCHAR(64) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    nome VARCHAR(256) NOT NULL,
    telefone VARCHAR(20),
    sexo VARCHAR(20),
    data_nascimento DATE,
    PRIMARY KEY (id),
    INDEX (cpf) -- Adiciona um índice na coluna cpf para criar a chave estrangeira corretamente
);

-- Criação da tabela Médico
CREATE TABLE Medico (
    id BIGINT NOT NULL AUTO_INCREMENT,
    email VARCHAR(256) NOT NULL,
    senha VARCHAR(64) NOT NULL,
    crm VARCHAR(10) NOT NULL,
    nome VARCHAR(256) NOT NULL,
    especialidade VARCHAR(256) NOT NULL,
    PRIMARY KEY (id),
    INDEX (crm) -- Adiciona um índice na coluna crm para criar a chave estrangeira corretamente
);

-- Criação da tabela Consulta
CREATE TABLE Consulta (
    id BIGINT NOT NULL AUTO_INCREMENT,
    cpf_paciente VARCHAR(11) NOT NULL,
    crm_medico VARCHAR(10) NOT NULL,
    data_hora DATETIME NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (cpf_paciente) REFERENCES Paciente(cpf),
    FOREIGN KEY (crm_medico) REFERENCES Medico(crm)
);

CREATE TABLE Usuario (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(256) NOT NULL,
    login VARCHAR(20) NOT NULL UNIQUE,
    senha VARCHAR(64) NOT NULL,
    papel VARCHAR(10),
    PRIMARY KEY (id)
);

-- Inserção de dados aleatórios na tabela Paciente
INSERT INTO Paciente (email, senha, cpf, nome, telefone, sexo, data_nascimento) VALUES
    ('lucas@example.com', 'senha1', '12345678901', 'Lucas Costa', '123456789', 'Masculino', '1990-01-01'),
    ('sofia@example.com', 'senha2', '23456789012', 'Sofia Oliveira', '987654321', 'Feminino', '1995-02-15'),
    ('gabriel@example.com', 'senha3', '34567890123', 'Gabriel Santos', '456789012', 'Masculino', '1985-06-30'),
    ('isabella@example.com', 'senha4', '45678901234', 'Isabella Silva', '789012345', 'Feminino', '1998-11-22'),
    ('enzo@example.com', 'senha5', '56789012345', 'Enzo Rodrigues', '321654987', 'Masculino', '1992-09-10');

-- Inserção de dados aleatórios na tabela Médico
INSERT INTO Medico (email, senha, crm, nome, especialidade) VALUES
    ('pedro@example.com', 'senha1', '12345', 'Dr. Pedro', 'Cardiologia'),
    ('ana@example.com', 'senha2', '23456', 'Dra. Ana', 'Dermatologia'),
    ('beatriz@example.com', 'senha3', '34567', 'Dra. Beatriz', 'Ortopedia');

-- Inserção de dados aleatórios na tabela Consulta
INSERT INTO Consulta (cpf_paciente, crm_medico, data_hora) VALUES
    ('12345678901', '12345', '2023-06-10 14:00:00'),
    ('23456789012', '23456', '2023-06-11 16:30:00'),
    ('34567890123', '34567', '2023-06-12 11:00:00'),
    ('45678901234', '12345', '2023-06-13 09:30:00'),
    ('56789012345', '34567', '2023-06-14 15:00:00');

INSERT INTO Usuario (nome, login, senha, papel) VALUES 
    ('Administrador', 'admin', 'admin', 'ADMIN'),
    ('Usuario', 'user', 'user', 'USER');