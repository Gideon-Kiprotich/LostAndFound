-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 21, 2023 at 07:49 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `lost_and_found`
--

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

CREATE TABLE `items` (
  `Item_ID` int(10) NOT NULL,
  `Item` text NOT NULL,
  `Location` varchar(50) NOT NULL,
  `Date` date NOT NULL,
  `Color` text NOT NULL,
  `Type` text NOT NULL,
  `Additional_info` longtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `items`
--

INSERT INTO `items` (`Item_ID`, `Item`, `Location`, `Date`, `Color`, `Type`, `Additional_info`) VALUES
(5678, 'hoodie', 'library', '2023-12-19', 'red', 'clothes', 'large size'),
(5679, 'hoodie', 'library', '2023-12-19', 'red', 'clothes', 'large size'),
(7645, 'Water bottle', 'stc', '2023-07-14', 'pink', 'personal', '500ml'),
(12345, 'Book', 'stmb', '2023-04-22', 'Black', 'Stationery', 'OOP II'),
(45678, 'phone', 'stc', '2023-04-03', 'white', 'personal items', 'oppo'),
(151413, 'Laptop', 'library', '2023-10-06', 'Black', 'Electronic Devices', 'Lenovo');

-- --------------------------------------------------------

--
-- Table structure for table `registration`
--

CREATE TABLE `registration` (
  `Name` text NOT NULL,
  `School_ID` int(10) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Password` varchar(20) NOT NULL,
  `Faculty` varchar(10) NOT NULL,
  `National_ID` int(10) NOT NULL,
  `Address` varchar(30) NOT NULL,
  `Gender` varchar(10) NOT NULL,
  `Phone` int(15) NOT NULL,
  `Role` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `registration`
--

INSERT INTO `registration` (`Name`, `School_ID`, `Email`, `Password`, `Faculty`, `National_ID`, `Address`, `Gender`, `Phone`, `Role`) VALUES
('Edwin', 123456, 'edwin@gmail.com', '1245', 'llb', 40579543, 'box 1 nairobi', 'male', 789654323, 'student'),
('Edwin', 150462, 'mwaiedwin@gmail.com', '45678', 'sces', 40578657, '12 kiambu road', 'male', 745678912, 'student'),
('Gideon Kiprotich', 151417, 'gideon@gmail.com', '12345', 'sces', 40578659, 'Box 1 Limuru', 'Male', 769386317, 'admin'),
('gideon', 151418, 'giddy@yahoo.com', '1234', 'sces', 4056789, '123 limuru', 'male', 769386318, 'admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`Item_ID`);

--
-- Indexes for table `registration`
--
ALTER TABLE `registration`
  ADD PRIMARY KEY (`School_ID`),
  ADD UNIQUE KEY `Email` (`Email`),
  ADD UNIQUE KEY `National_ID` (`National_ID`),
  ADD UNIQUE KEY `Phone` (`Phone`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
