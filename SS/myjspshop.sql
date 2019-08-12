-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: 2019-04-10 06:09:38
-- 服务器版本： 5.7.19
-- PHP Version: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `myjspshop`
--

-- --------------------------------------------------------

--
-- 表的结构 `userinfo`
--

DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE IF NOT EXISTS `userinfo` (
  `username` varchar(20) NOT NULL,
  `nickname` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `sex` varchar(2) NOT NULL,
  `tellphone` varchar(20) NOT NULL,
  `email` varchar(45) NOT NULL,
  `poto` mediumblob,
  `brithday` date NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `userinfo`
--

INSERT INTO `userinfo` (`username`, `nickname`, `password`, `sex`, `tellphone`, `email`, `poto`, `brithday`) VALUES
('breakL', '风', '123456azz', '男', '13333333333', 'abc@lzfmail.my', NULL, '1997-04-22');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
