-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 17, 2025 at 06:31 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mathkids_quiz`
--

-- --------------------------------------------------------

--
-- Table structure for table `questions`
--

CREATE TABLE `questions` (
  `id` int(11) NOT NULL,
  `question_text` varchar(1000) NOT NULL,
  `option_a` varchar(500) NOT NULL,
  `option_b` varchar(500) NOT NULL,
  `option_c` varchar(500) NOT NULL,
  `option_d` varchar(500) NOT NULL,
  `correct_answer` char(1) NOT NULL,
  `difficulty` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `questions`
--

INSERT INTO `questions` (`id`, `question_text`, `option_a`, `option_b`, `option_c`, `option_d`, `correct_answer`, `difficulty`) VALUES
(2, '1 + 1', '1', '2', '0', '3', 'B', 'easy'),
(4, '64 - 25', '27', '41', '39', '32', 'C', 'easy'),
(5, '45 x 2', '52', '82', '90', '80', 'C', 'medium'),
(6, '16 - 5', '9', '14', '15', '11', 'D', 'easy'),
(7, '25 / 5', '5', '2', '8', '1', 'A', 'easy');

-- --------------------------------------------------------

--
-- Table structure for table `quiz_results`
--

CREATE TABLE `quiz_results` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `score` int(11) NOT NULL,
  `total_questions` int(11) NOT NULL,
  `quiz_date` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `quiz_results`
--

INSERT INTO `quiz_results` (`id`, `user_id`, `score`, `total_questions`, `quiz_date`) VALUES
(1, 9, 1, 1, '2025-06-12 06:02:14'),
(2, 9, 1, 1, '2025-06-12 06:02:56'),
(3, 9, 1, 1, '2025-06-12 06:06:57'),
(4, 9, 0, 1, '2025-06-12 07:10:49'),
(5, 5, 1, 1, '2025-06-12 07:38:09'),
(6, 5, 1, 1, '2025-06-12 07:58:11'),
(7, 5, 2, 2, '2025-06-12 08:11:49'),
(8, 1, 2, 2, '2025-06-12 08:24:05'),
(9, 1, 2, 2, '2025-06-12 08:32:24'),
(10, 1, 2, 2, '2025-06-12 09:04:20'),
(11, 1, 2, 2, '2025-06-12 09:05:18'),
(13, 1, 2, 2, '2025-06-12 09:35:49'),
(14, 1, 2, 2, '2025-06-12 09:37:18'),
(15, 5, 2, 2, '2025-06-12 10:02:21'),
(16, 5, 1, 2, '2025-06-12 10:07:33'),
(17, 5, 1, 2, '2025-06-12 11:25:34'),
(18, 5, 5, 6, '2025-06-12 11:40:04'),
(19, 5, 3, 6, '2025-06-12 12:22:41'),
(20, 5, 4, 5, '2025-06-12 14:16:05'),
(21, 5, 2, 5, '2025-06-12 14:19:37'),
(22, 5, 0, 5, '2025-06-12 15:26:33');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `role` varchar(20) DEFAULT 'student'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `email`, `role`) VALUES
(1, 'naddyie09', 'vVLWITFZXNi94XFNCNxkS18jL8fsbTaE1DJqREwbszU=', 'nadiahlatif09@gmail.com', 'student'),
(4, 'nadhirahbaki', 'ewP6iV/kRgV/vp3FQebattziYHRu8UQ31FRhQ8Si0G4=', 'nadhirahbaki01@gmail.com', 'student'),
(5, 'munirahazman', 'ji7eC2DNkaLRPs+TBTPCYkNvazCiWs5bEwmap3Q4Ulo=', 'munirah45@gmail.com', 'student'),
(7, 'admin', 'lrp+cD0ET6+akYT6Sz1UwZ+oRFBzq3dCwM4E8vWgD3Q=', 'mathkidsquiz@gmail.com', 'admin'),
(9, 'nadhirah', 'Inz1QQLHnHxPDH5QEQD3IP/eM27Zc15tyvfbZfELPmA=', 'nadhirah17@gmail.com', 'student');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `questions`
--
ALTER TABLE `questions`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `quiz_results`
--
ALTER TABLE `quiz_results`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `questions`
--
ALTER TABLE `questions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `quiz_results`
--
ALTER TABLE `quiz_results`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `quiz_results`
--
ALTER TABLE `quiz_results`
  ADD CONSTRAINT `quiz_results_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
