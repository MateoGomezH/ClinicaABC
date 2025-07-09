package DB_Script;

/*-- Crear la base de datos
CREATE DATABASE ClinicaVeterinariaABC;
USE ClinicaVeterinariaABC;

-- Tabla Persona
CREATE TABLE Persona (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         documento VARCHAR(20) NOT NULL UNIQUE,
                         nombre VARCHAR(100) NOT NULL,
                         apellido VARCHAR(100) NOT NULL,
                         telefono VARCHAR(20)
);

-- Tabla Mascota
CREATE TABLE Mascota (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         nombre VARCHAR(100) NOT NULL,
                         raza VARCHAR(100),
                         sexo VARCHAR(10),
                         idDueño VARCHAR(20) NOT NULL,
                         FOREIGN KEY (idDueño) REFERENCES Persona(documento)
);

ALTER TABLE Mascota ADD COLUMN especie VARCHAR(100);
*/