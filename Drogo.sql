-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 27-11-2018 a las 17:41:39
-- Versión del servidor: 10.1.36-MariaDB
-- Versión de PHP: 7.2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `drogo`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `casa`
--

CREATE TABLE `casa` (
  `Id` int(11) NOT NULL,
  `Nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `casa`
--

INSERT INTO `casa` (`Id`, `Nombre`) VALUES
(1, 'Martell'),
(2, 'Lannister'),
(3, 'Stark');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `prisionero`
--

CREATE TABLE `prisionero` (
  `Id` int(11) NOT NULL,
  `Id_casa` int(11) NOT NULL,
  `Nombre` varchar(30) NOT NULL,
  `Edad` int(11) NOT NULL,
  `Dni` varchar(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `prisionero`
--

INSERT INTO `prisionero` (`Id`, `Id_casa`, `Nombre`, `Edad`, `Dni`) VALUES
(2, 2, 'Isabel', 21, '12345678B'),
(11, 1, 'Pedro', 24, '12345678J'),
(14, 3, 'Claudia', 21, '45339131J'),
(17, 2, 'Carlos', 23, '12345678G'),
(39, 1, 'Laura', 24, '12345670I'),
(41, 3, 'Lola Flores', 24, '12345678W');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `casa`
--
ALTER TABLE `casa`
  ADD PRIMARY KEY (`Id`);

--
-- Indices de la tabla `prisionero`
--
ALTER TABLE `prisionero`
  ADD PRIMARY KEY (`Id`),
  ADD UNIQUE KEY `ID` (`Dni`),
  ADD KEY `Id_casa` (`Id_casa`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `casa`
--
ALTER TABLE `casa`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `prisionero`
--
ALTER TABLE `prisionero`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `prisionero`
--
ALTER TABLE `prisionero`
  ADD CONSTRAINT `prisionero_ibfk_1` FOREIGN KEY (`Id_casa`) REFERENCES `casa` (`Id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
