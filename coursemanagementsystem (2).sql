-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 10, 2023 at 08:03 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `coursemanagementsystem`
--

-- --------------------------------------------------------

--
-- Table structure for table `administrator`
--

CREATE TABLE `administrator` (
  `id` int(11) NOT NULL,
  `firstname` varchar(100) NOT NULL,
  `lastname` varchar(100) NOT NULL,
  `phonenumber` varchar(20) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `administrator`
--

INSERT INTO `administrator` (`id`, `firstname`, `lastname`, `phonenumber`, `gender`, `username`, `password`) VALUES
(5, 'Raj', 'Shrestha', '9876765434', 'male', 'shrestharaj@gmail.com', 'raj123'),
(6, 'Subash', 'Bista', '9878657894', 'male', 'bista.subash@gmail.com', 'subash123');

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE `course` (
  `id` int(11) NOT NULL,
  `course` varchar(100) NOT NULL,
  `Years` varchar(10) NOT NULL,
  `isActive` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`id`, `course`, `Years`, `isActive`) VALUES
(6, 'BSC(Hons) Computer Science', '4', 'True'),
(8, 'Bsc it', '4', 'False'),
(11, 'BCA', '1', 'True');

-- --------------------------------------------------------

--
-- Table structure for table `instructor`
--

CREATE TABLE `instructor` (
  `id` int(11) NOT NULL,
  `firstname` varchar(100) NOT NULL,
  `lastname` varchar(100) NOT NULL,
  `phonenumber` varchar(20) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `course` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `instructor`
--

INSERT INTO `instructor` (`id`, `firstname`, `lastname`, `phonenumber`, `gender`, `username`, `password`, `course`) VALUES
(27, 'Pradip', 'Mani', '9876543234', 'male', 'pradip@gmail.com', '123', 'BSC(Hons) Computer Science'),
(28, 'Raj', 'Shrestha', '9876532456', 'male', 'raj@gmail.com', 'raj123', 'BSC(Hons) Computer Science'),
(29, 'Siman', 'Giri', '9876543212', 'male', 'siman@gmail.com', 'siman123', 'BSC(Hons) Computer Science'),
(30, 'Deepson', 'Shrestha', '9876785432', 'male', 'deepson@gmail.com', 'deepson', 'BIBM'),
(31, 'Dipen', 'Rai', '9876543212', 'male', 'dipen@gmail.com', 'dipen123', 'BSC(Hons) Computer Science');

-- --------------------------------------------------------

--
-- Table structure for table `marks`
--

CREATE TABLE `marks` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `course` varchar(30) NOT NULL,
  `level` varchar(20) NOT NULL,
  `marks` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `marks`
--

INSERT INTO `marks` (`id`, `name`, `course`, `level`, `marks`) VALUES
(1, 'Niraj', 'Bsc (Hons) Computer Science', '2', '40'),
(2, 'rakesh', 'BSC(Hons) Computer Science', '1', '40'),
(5, 'Zocken', 'BIBM', '3', '40'),
(7, 'Bibek', 'BSC(Hons) Computer Science', '1', '70'),
(10, 'Niraj', 'BCA', '3', '34');

-- --------------------------------------------------------

--
-- Table structure for table `module`
--

CREATE TABLE `module` (
  `id` int(11) NOT NULL,
  `course` varchar(100) NOT NULL,
  `Moduled` varchar(50) NOT NULL,
  `Title` varchar(50) NOT NULL,
  `level` varchar(10) NOT NULL,
  `Username` varchar(100) NOT NULL,
  `Phonenumber` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `module`
--

INSERT INTO `module` (`id`, `course`, `Moduled`, `Title`, `level`, `Username`, `Phonenumber`) VALUES
(1, 'BIBM', '4CI001', 'Programming', '3', 'prakesh@gmail.com', '9876543210'),
(6, 'BSC(Hons) Computer Science', '4CI001', 'Programming', '1', 'prakesh@gmail.com', '9876543210'),
(10, 'BSC(Hons) Computer Science', '42CI320', 'Program', '6', 'subash@gmail.com', '9876543212');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `id` int(11) NOT NULL,
  `firstname` varchar(100) NOT NULL,
  `lastname` varchar(100) NOT NULL,
  `phonenumber` varchar(20) NOT NULL,
  `gender` varchar(20) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `course` varchar(100) NOT NULL,
  `level` varchar(50) NOT NULL,
  `semester` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`id`, `firstname`, `lastname`, `phonenumber`, `gender`, `username`, `password`, `course`, `level`, `semester`) VALUES
(11, 'Bibek', 'Bohara', '9818633093', 'male', 'bohara@gmail.com', 'password', 'BSC(Hons) Computer Science', '3', 'SEM1'),
(13, 'Niraj', 'Tamang', '98765432345', 'male', 'niraj@gmail.com', 'niraj', 'BSC(Hons) Computer Science', '1', 'SEM1');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `administrator`
--
ALTER TABLE `administrator`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `instructor`
--
ALTER TABLE `instructor`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `marks`
--
ALTER TABLE `marks`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `module`
--
ALTER TABLE `module`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `administrator`
--
ALTER TABLE `administrator`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `course`
--
ALTER TABLE `course`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `instructor`
--
ALTER TABLE `instructor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT for table `marks`
--
ALTER TABLE `marks`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `module`
--
ALTER TABLE `module`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
