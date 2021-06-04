-- phpMyAdmin SQL Dump
-- version 3.2.3
-- http://www.phpmyadmin.net
--
-- 호스트: localhost
-- 처리한 시간: 21-04-05 02:22 
-- 서버 버전: 5.1.41
-- PHP 버전: 5.2.12

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 데이터베이스: `capstone_final`
--

-- --------------------------------------------------------

--
-- 테이블 구조 `employee_checkintime`
--

CREATE TABLE IF NOT EXISTS `employee_checkintime` (
  `User_Number` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '유저번호',
  `Checkin_Time` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '출근시간',
  PRIMARY KEY (`User_Number`),
  UNIQUE KEY `User_Number` (`User_Number`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='출근시간 저장 테이블';

--
-- 테이블의 덤프 데이터 `employee_checkintime`
--


-- --------------------------------------------------------

--
-- 테이블 구조 `employee_checkouttime`
--

CREATE TABLE IF NOT EXISTS `employee_checkouttime` (
  `User_Number` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Checkout_Time` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`User_Number`),
  UNIQUE KEY `User_Number` (`User_Number`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='퇴근시간 저장 테이블';

--
-- 테이블의 덤프 데이터 `employee_checkouttime`
--


-- --------------------------------------------------------

--
-- 테이블 구조 `employee_labor_contract`
--

CREATE TABLE IF NOT EXISTS `employee_labor_contract` (
  `User_Number` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '유저번호',
  `Labor_Contract` mediumblob NOT NULL COMMENT '근로계약서',
  PRIMARY KEY (`User_Number`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='근로계약서 테이블';

--
-- 테이블의 덤프 데이터 `employee_labor_contract`
--


-- --------------------------------------------------------

--
-- 테이블 구조 `employee_takingover`
--

CREATE TABLE IF NOT EXISTS `employee_takingover` (
  `User_Number_Informer` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '유저번호 (인계자)',
  `User_Number_Receiver` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '유저번호 (인수자)',
  `Wrtie_Time` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '인수인계 작성시간',
  `Context` varchar(500) COLLATE utf8_unicode_ci NOT NULL COMMENT '인수인계 내용',
  PRIMARY KEY (`User_Number_Informer`,`User_Number_Receiver`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='인수인계 테이블';

--
-- 테이블의 덤프 데이터 `employee_takingover`
--


-- --------------------------------------------------------

--
-- 테이블 구조 `employee_workinghours`
--

CREATE TABLE IF NOT EXISTS `employee_workinghours` (
  `User_Number` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '유저번호',
  `Date` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '해당날짜',
  `WorkingHours` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '근무시간',
  PRIMARY KEY (`User_Number`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='일별 근무시간 저장 테이블';

--
-- 테이블의 덤프 데이터 `employee_workinghours`
--


-- --------------------------------------------------------

--
-- 테이블 구조 `login_track`
--

CREATE TABLE IF NOT EXISTS `login_track` (
  `User_Number` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Time` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`User_Number`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='로그인기록 테이블';

--
-- 테이블의 덤프 데이터 `login_track`
--

INSERT INTO `login_track` (`User_Number`, `Time`) VALUES
('00001', ' 2021-04-05 02:17:35');

-- --------------------------------------------------------

--
-- 테이블 구조 `todo_complete`
--

CREATE TABLE IF NOT EXISTS `todo_complete` (
  `Task_Number` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '작업 고유번호',
  `Complete_Time` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '작업 완료시간',
  PRIMARY KEY (`Task_Number`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='완료된 작업 테이블';

--
-- 테이블의 덤프 데이터 `todo_complete`
--


-- --------------------------------------------------------

--
-- 테이블 구조 `todo_daily`
--

CREATE TABLE IF NOT EXISTS `todo_daily` (
  `Task_Number` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '업무 고유번호',
  `WorkPlace_Number` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '사업장 번호',
  `Task_Implementer_Number` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '업무 담당직원 유저번호',
  `Assign_Time` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '업무 등록 시간(수정시 변경됨)',
  `Fix_Or_Flow` varchar(10) COLLATE utf8_unicode_ci NOT NULL COMMENT '고정/유동 업무 설정',
  `Task_Type` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '업무 항목',
  `Task_Alarm` varchar(10) COLLATE utf8_unicode_ci NOT NULL COMMENT '업무 알람 설정',
  PRIMARY KEY (`Task_Number`,`WorkPlace_Number`,`Task_Implementer_Number`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='To_Do 리스트 (실시간 용)';

--
-- 테이블의 덤프 데이터 `todo_daily`
--


-- --------------------------------------------------------

--
-- 테이블 구조 `todo_incomplete`
--

CREATE TABLE IF NOT EXISTS `todo_incomplete` (
  `Task_Number` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '작업 고유번호',
  `Assign_Time` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '작업 등록시간',
  PRIMARY KEY (`Task_Number`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='미완료 작업 테이블';

--
-- 테이블의 덤프 데이터 `todo_incomplete`
--


-- --------------------------------------------------------

--
-- 테이블 구조 `todo_main`
--

CREATE TABLE IF NOT EXISTS `todo_main` (
  `Task_Number` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '작업 고유번호',
  `WorkPlace_Number` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '사업장 번호',
  `Assign_User_Number` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '작업 등록자 유저번호',
  PRIMARY KEY (`Task_Number`,`WorkPlace_Number`,`Assign_User_Number`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Todo 등록 사전 테이블';

--
-- 테이블의 덤프 데이터 `todo_main`
--


-- --------------------------------------------------------

--
-- 테이블 구조 `todo_monthly`
--

CREATE TABLE IF NOT EXISTS `todo_monthly` (
  `Task_Number` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '업무 고유번호',
  `WorkPlace_Number` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '사업장 번호',
  `Task_Implementer_Number` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '업무 담당직원 유저번호',
  `Assign_Time` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '업무 등록 시간(수정시 변경됨)',
  `Fix_Or_Flow` varchar(10) COLLATE utf8_unicode_ci NOT NULL COMMENT '고정/유동 업무 설정',
  `Task_Type` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '업무 항목',
  `Task_Alarm` varchar(10) COLLATE utf8_unicode_ci NOT NULL COMMENT '업무 알람 설정',
  PRIMARY KEY (`Task_Number`,`WorkPlace_Number`,`Task_Implementer_Number`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='To_Do 리스트 (실시간 용)';

--
-- 테이블의 덤프 데이터 `todo_monthly`
--


-- --------------------------------------------------------

--
-- 테이블 구조 `todo_weekly`
--

CREATE TABLE IF NOT EXISTS `todo_weekly` (
  `Task_Number` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '업무 고유번호',
  `WorkPlace_Number` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '사업장 번호',
  `Task_Implementer_Number` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '업무 담당직원 유저번호',
  `Assign_Time` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '업무 등록 시간(수정시 변경됨)',
  `Fix_Or_Flow` varchar(10) COLLATE utf8_unicode_ci NOT NULL COMMENT '고정/유동 업무 설정',
  `Task_Type` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '업무 항목',
  `Task_Alarm` varchar(10) COLLATE utf8_unicode_ci NOT NULL COMMENT '업무 알람 설정',
  PRIMARY KEY (`Task_Number`,`WorkPlace_Number`,`Task_Implementer_Number`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='To_Do 리스트 (실시간 용)';

--
-- 테이블의 덤프 데이터 `todo_weekly`
--


-- --------------------------------------------------------

--
-- 테이블 구조 `users_identi`
--

CREATE TABLE IF NOT EXISTS `users_identi` (
  `User_Number` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '유저번호(PK)',
  `User_ID` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '유저ID(유니크)',
  `User_PW` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '유저PW(유니크)',
  PRIMARY KEY (`User_Number`),
  UNIQUE KEY `User_ID` (`User_ID`,`User_PW`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='유저 아이덴티 테이블 ( 유저번호 + 유저 ID + 유저 PW )';

--
-- 테이블의 덤프 데이터 `users_identi`
--

INSERT INTO `users_identi` (`User_Number`, `User_ID`, `User_PW`) VALUES
('00001', 'sjot', '$2a$10$p6Z2uFkEhB2bvBT8arDq6u/VX3i25lFiCC8u1GpU2z602kBOSE3B2');

-- --------------------------------------------------------

--
-- 테이블 구조 `users_isonline`
--

CREATE TABLE IF NOT EXISTS `users_isonline` (
  `User_Number` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `inWorking` tinyint(1) NOT NULL,
  PRIMARY KEY (`User_Number`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='접속중인지 아닌지 판단';

--
-- 테이블의 덤프 데이터 `users_isonline`
--

INSERT INTO `users_isonline` (`User_Number`, `inWorking`) VALUES
('00001', 1);

-- --------------------------------------------------------

--
-- 테이블 구조 `users_name`
--

CREATE TABLE IF NOT EXISTS `users_name` (
  `User_Number` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '유저번호(PK+FK)',
  `User_Name` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '유저이름',
  PRIMARY KEY (`User_Number`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='유저 이름 테이블';

--
-- 테이블의 덤프 데이터 `users_name`
--

INSERT INTO `users_name` (`User_Number`, `User_Name`) VALUES
('00001', 'KangOwnTae');

-- --------------------------------------------------------

--
-- 테이블 구조 `users_permission`
--

CREATE TABLE IF NOT EXISTS `users_permission` (
  `User_Number` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '유저번호',
  `User_Permission` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '유저권한',
  PRIMARY KEY (`User_Number`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='유저권한 테이블';

--
-- 테이블의 덤프 데이터 `users_permission`
--

INSERT INTO `users_permission` (`User_Number`, `User_Permission`) VALUES
('00001', 'CEO');

-- --------------------------------------------------------

--
-- 테이블 구조 `user_workplace`
--

CREATE TABLE IF NOT EXISTS `user_workplace` (
  `User_Number` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '유저번호(PK+FK)',
  `User_WorkPlace_Number` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '유저 해당 사업장번호',
  PRIMARY KEY (`User_Number`),
  UNIQUE KEY `User_WorkPlace_Number` (`User_WorkPlace_Number`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='유저 사업장 번호 테이블';

--
-- 테이블의 덤프 데이터 `user_workplace`
--

INSERT INTO `user_workplace` (`User_Number`, `User_WorkPlace_Number`) VALUES
('00001', '99999');

-- --------------------------------------------------------

--
-- 테이블 구조 `workplace_list`
--

CREATE TABLE IF NOT EXISTS `workplace_list` (
  `WorkPlace_Number` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '사업장번호(PK)',
  `WorkPlace_Name` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '사업장이름(유니크)',
  PRIMARY KEY (`WorkPlace_Number`),
  UNIQUE KEY `WorkPlace_Name` (`WorkPlace_Name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='등록된 사업장 목록 테이블';

--
-- 테이블의 덤프 데이터 `workplace_list`
--

INSERT INTO `workplace_list` (`WorkPlace_Number`, `WorkPlace_Name`) VALUES
('99999', '서울01');
