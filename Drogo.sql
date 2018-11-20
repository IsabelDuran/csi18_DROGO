-- phpMyAdmin SQL Dump
-- version 4.8.0
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 20-11-2018 a las 16:42:31
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
  `Id` int(11) NOT NULL,
  `Nombre` varchar(30) NOT NULL,
  `Edad` int(11) NOT NULL,
  `Dni` varchar(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `Prisionero`
--

INSERT INTO `Prisionero` (`Id`, `Nombre`, `Edad`, `Dni`) VALUES
(2, 'Isabel', 21, '12345678B'),
(14, 'Claudia', 21, '45339131J'),
(17, 'Carlos', 23, '12345678G'),
(91, 'Laura', 24, '12345670I'),
(93, 'Lola Flores', 24, '12345678W'),
(94, 'Lola', 12, '12345678H');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Prisionero`
--
ALTER TABLE `Prisionero`
  ADD PRIMARY KEY (`Id`),
  ADD UNIQUE KEY `DNI` (`Dni`) USING BTREE;

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `Prisionero`
--
ALTER TABLE `Prisionero`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=95;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
