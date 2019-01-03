-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: 03-Jan-2019 às 15:19
-- Versão do servidor: 10.2.18-MariaDB
-- versão do PHP: 7.2.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `u422723836_db`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `testusers`
--

CREATE TABLE `testusers` (
  `id` int(11) NOT NULL,
  `user` varchar(20) NOT NULL,
  `pass` varchar(20) NOT NULL,
  `score` int(4) NOT NULL,
  `achievements` varchar(20) NOT NULL DEFAULT '000000000000',
  `date` date NOT NULL,
  `numPlay` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `testusers`
--

INSERT INTO `testusers` (`id`, `user`, `pass`, `score`, `achievements`, `date`, `numPlay`) VALUES
(1, 'q', 'q', 40, '000000000000', '2018-12-11', 0),
(3, 'r', 'r', 35, '000000000000', '2018-12-12', 0),
(4, 'a', 'a', 195, '100100100000', '2018-12-30', 2),
(5, 'renatovalente5', 'ren123', 196, '000010000000', '2018-12-30', 1),
(6, 'w', 'w', 126, '000000100000', '2018-12-30', 1),
(7, 'tiago', 'tiago', 56, '000000000000', '2018-12-30', 0),
(8, 'andreandreandre', 'andre', 261, '000000000000', '2019-01-02', 0),
(9, 'paulo', 'paulo', 142, '000000100000', '2019-01-02', 1),
(10, '13`d', '´1', 0, '000000000000', '2019-01-02', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `testusers`
--
ALTER TABLE `testusers`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `testusers`
--
ALTER TABLE `testusers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
