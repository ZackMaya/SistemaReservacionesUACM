-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 27-05-2017 a las 03:27:27
-- Versión del servidor: 5.6.17
-- Versión de PHP: 5.5.12


SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `restaurante`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE IF NOT EXISTS `cliente` (
  `idcliente` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(70) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `email` varchar(70) NOT NULL,
  `comentario` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idcliente`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`idcliente`, `nombre`, `telefono`, `email`, `comentario`) VALUES
(1, 'Christian', '5517373377', 'kriztianfv97@gmail.com', 'Primera prueba de insertar clientes en la BD con su respectiva reservacion');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservacion`
--

CREATE TABLE IF NOT EXISTS `reservacion` (
  `idreservacion` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `num_personas` int(11) NOT NULL,
  `num_mesas` int(11) NOT NULL,
  `horario` varchar(20) NOT NULL,
  `idcliente` int(11) DEFAULT NULL,
  PRIMARY KEY (`idreservacion`),
  KEY `fk_reservacion_cliente1_idx` (`idcliente`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Volcado de datos para la tabla `reservacion`
--

INSERT INTO `reservacion` (`idreservacion`, `fecha`, `num_personas`, `num_mesas`, `horario`, `idcliente`) VALUES
(1, '2017-05-26', 2, 1, '12:30', 1);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `reservacion`
--
ALTER TABLE `reservacion`
  ADD CONSTRAINT `FKnuyw2dh3ob5gkkvumsgukqp33` FOREIGN KEY (`idcliente`) REFERENCES `cliente` (`idcliente`),
  ADD CONSTRAINT `fk_reserva_cliente` FOREIGN KEY (`idcliente`) REFERENCES `cliente` (`idcliente`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
