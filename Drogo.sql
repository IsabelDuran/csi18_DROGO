-- phpMyAdmin SQL Dump
-- version 4.8.0
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 06-11-2018 a las 16:42:56
-- Versión del servidor: 10.1.31-MariaDB
-- Versión de PHP: 7.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `Drogo`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Prisionero`
--

CREATE TABLE `Prisionero` (
  `Nombre` varchar(30) NOT NULL,
  `Edad` int(11) NOT NULL,
  `ID` char(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `Prisionero`
--

INSERT INTO `Prisionero` (`Nombre`, `Edad`, `ID`) VALUES
('Claudia', 21, '12345678A'),
('Isabel', 21, '12345678B'),
('Pedro', 24, '12345678J');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Prisionero`
--
ALTER TABLE `Prisionero`
  ADD PRIMARY KEY (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
