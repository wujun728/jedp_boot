
CREATE TABLE `d_open_survey` (
  `f_id` varchar(32) NOT NULL,
  `f_allowreport` int(11) DEFAULT NULL,
  `f_createtime` datetime DEFAULT NULL,
  `f_description` varchar(255) DEFAULT NULL,
  `f_name` varchar(255) DEFAULT NULL,
  `f_pagecount` int(11) DEFAULT NULL,
  `f_questioncount` int(11) DEFAULT NULL,
  `f_samplecount` int(11) DEFAULT NULL,
  `f_samplesum` int(11) DEFAULT NULL,
  `f_show` int(11) DEFAULT NULL,
  `f_surveyurl` varchar(255) DEFAULT NULL,
  `f_tag` varchar(255) DEFAULT NULL,
  `f_type` int(11) DEFAULT NULL,
  `f_typename` varchar(255) DEFAULT NULL,
  `f_uid` varchar(255) DEFAULT NULL,
  `f_usersurveycount` int(11) DEFAULT NULL,
  `f_viewersum` int(11) DEFAULT NULL,
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
