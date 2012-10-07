-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 07, 2012 at 10:03 PM
-- Server version: 5.5.16
-- PHP Version: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `jt_sc`
--

-- --------------------------------------------------------

--
-- Table structure for table `citations`
--

CREATE TABLE IF NOT EXISTS `citations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Keycode` varchar(45) NOT NULL,
  `Journal` text NOT NULL,
  `Year` int(11) NOT NULL,
  `Volume` text NOT NULL,
  `Page` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=61 ;

-- --------------------------------------------------------

--
-- Table structure for table `headnotes`
--

CREATE TABLE IF NOT EXISTS `headnotes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Keycode` varchar(45) NOT NULL,
  `Held` text NOT NULL,
  `Headnote` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=60 ;

-- --------------------------------------------------------

--
-- Table structure for table `judgements`
--

CREATE TABLE IF NOT EXISTS `judgements` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Keycode` varchar(45) NOT NULL,
  `COURT` text NOT NULL,
  `Judges` text NOT NULL,
  `Bench` int(11) NOT NULL,
  `CaseNo` text NOT NULL,
  `Appellant` text NOT NULL,
  `Respondent` text NOT NULL,
  `Date` datetime NOT NULL,
  `Advocates` text NOT NULL,
  `CasesReferred` text NOT NULL,
  `Judgement` longtext NOT NULL,
  `CreatedDate` date NOT NULL,
  `ModifiedDate` date NOT NULL,
  `Is_Verified` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=64 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
