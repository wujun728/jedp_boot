-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: activiti
-- ------------------------------------------------------
-- Server version	5.7.13-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `act_evt_log`
--

DROP TABLE IF EXISTS `act_evt_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_evt_log` (
  `LOG_NR_` bigint(20) NOT NULL AUTO_INCREMENT,
  `TYPE_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TIME_STAMP_` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DATA_` longblob,
  `LOCK_OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LOCK_TIME_` timestamp(3) NULL DEFAULT NULL,
  `IS_PROCESSED_` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`LOG_NR_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_evt_log`
--

LOCK TABLES `act_evt_log` WRITE;
/*!40000 ALTER TABLE `act_evt_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_evt_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ge_bytearray`
--

DROP TABLE IF EXISTS `act_ge_bytearray`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_ge_bytearray` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BYTES_` longblob,
  `GENERATED_` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_BYTEARR_DEPL` (`DEPLOYMENT_ID_`),
  CONSTRAINT `ACT_FK_BYTEARR_DEPL` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `act_re_deployment` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ge_bytearray`
--

LOCK TABLES `act_ge_bytearray` WRITE;
/*!40000 ALTER TABLE `act_ge_bytearray` DISABLE KEYS */;
INSERT INTO `act_ge_bytearray` VALUES ('2',1,'D:\\apache-tomcat-8.0.36\\webapps\\Spring-activiti\\WEB-INF\\classes\\process\\leave.bpmn','1','<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:activiti=\"http://activiti.org/bpmn\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:omgdi=\"http://www.omg.org/spec/DD/20100524/DI\" typeLanguage=\"http://www.w3.org/2001/XMLSchema\" expressionLanguage=\"http://www.w3.org/1999/XPath\" targetNamespace=\"http://www.activiti.org/test\">\n  <process id=\"leave\" name=\"My process\" isExecutable=\"true\">\n    <userTask id=\"deptleaderaudit\" name=\"部门领导审批\" activiti:candidateGroups=\"部门经理\"></userTask>\n    <exclusiveGateway id=\"exclusivegateway1\" name=\"Exclusive Gateway\"></exclusiveGateway>\n    <userTask id=\"hraudit\" name=\"人事审批\" activiti:candidateGroups=\"人事\"></userTask>\n    <sequenceFlow id=\"flow3\" name=\"同意\" sourceRef=\"exclusivegateway1\" targetRef=\"hraudit\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${deptleaderapprove==\'true\'}]]></conditionExpression>\n    </sequenceFlow>\n    <userTask id=\"modifyapply\" name=\"调整申请\" activiti:assignee=\"${applyuserid}\"></userTask>\n    <sequenceFlow id=\"flow4\" name=\"拒绝\" sourceRef=\"exclusivegateway1\" targetRef=\"modifyapply\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${deptleaderapprove==\'false\'}]]></conditionExpression>\n    </sequenceFlow>\n    <sequenceFlow id=\"flow6\" sourceRef=\"deptleaderaudit\" targetRef=\"exclusivegateway1\"></sequenceFlow>\n    <exclusiveGateway id=\"exclusivegateway2\" name=\"Exclusive Gateway\"></exclusiveGateway>\n    <sequenceFlow id=\"flow7\" sourceRef=\"modifyapply\" targetRef=\"exclusivegateway2\"></sequenceFlow>\n    <sequenceFlow id=\"flow8\" name=\"重新申请\" sourceRef=\"exclusivegateway2\" targetRef=\"deptleaderaudit\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${reapply==\'true\'}]]></conditionExpression>\n    </sequenceFlow>\n    <endEvent id=\"endevent1\" name=\"End\"></endEvent>\n    <sequenceFlow id=\"flow9\" name=\"结束流程\" sourceRef=\"exclusivegateway2\" targetRef=\"endevent1\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${reapply==\'false\'}]]></conditionExpression>\n    </sequenceFlow>\n    <exclusiveGateway id=\"exclusivegateway3\" name=\"Exclusive Gateway\"></exclusiveGateway>\n    <sequenceFlow id=\"flow10\" sourceRef=\"hraudit\" targetRef=\"exclusivegateway3\"></sequenceFlow>\n    <sequenceFlow id=\"flow11\" name=\"拒绝\" sourceRef=\"exclusivegateway3\" targetRef=\"modifyapply\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${hrapprove==\'false\'}]]></conditionExpression>\n    </sequenceFlow>\n    <userTask id=\"reportback\" name=\"销假\" activiti:assignee=\"${applyuserid}\"></userTask>\n    <sequenceFlow id=\"flow12\" name=\"同意\" sourceRef=\"exclusivegateway3\" targetRef=\"reportback\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${hrapprove==\'true\'}]]></conditionExpression>\n    </sequenceFlow>\n    <sequenceFlow id=\"flow13\" sourceRef=\"reportback\" targetRef=\"endevent1\"></sequenceFlow>\n    <startEvent id=\"startevent1\" name=\"Start\" activiti:initiator=\"${applyuserid}\"></startEvent>\n    <sequenceFlow id=\"flow14\" sourceRef=\"startevent1\" targetRef=\"deptleaderaudit\"></sequenceFlow>\n  </process>\n  <bpmndi:BPMNDiagram id=\"BPMNDiagram_leave\">\n    <bpmndi:BPMNPlane bpmnElement=\"leave\" id=\"BPMNPlane_leave\">\n      <bpmndi:BPMNShape bpmnElement=\"deptleaderaudit\" id=\"BPMNShape_deptleaderaudit\">\n        <omgdc:Bounds height=\"55.0\" width=\"105.0\" x=\"250.0\" y=\"220.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"exclusivegateway1\" id=\"BPMNShape_exclusivegateway1\">\n        <omgdc:Bounds height=\"40.0\" width=\"40.0\" x=\"535.0\" y=\"227.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"hraudit\" id=\"BPMNShape_hraudit\">\n        <omgdc:Bounds height=\"55.0\" width=\"105.0\" x=\"620.0\" y=\"220.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"modifyapply\" id=\"BPMNShape_modifyapply\">\n        <omgdc:Bounds height=\"55.0\" width=\"105.0\" x=\"503.0\" y=\"310.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"exclusivegateway2\" id=\"BPMNShape_exclusivegateway2\">\n        <omgdc:Bounds height=\"40.0\" width=\"40.0\" x=\"535.0\" y=\"410.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"endevent1\" id=\"BPMNShape_endevent1\">\n        <omgdc:Bounds height=\"35.0\" width=\"35.0\" x=\"890.0\" y=\"413.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"exclusivegateway3\" id=\"BPMNShape_exclusivegateway3\">\n        <omgdc:Bounds height=\"40.0\" width=\"40.0\" x=\"770.0\" y=\"228.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"reportback\" id=\"BPMNShape_reportback\">\n        <omgdc:Bounds height=\"55.0\" width=\"105.0\" x=\"855.0\" y=\"221.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"startevent1\" id=\"BPMNShape_startevent1\">\n        <omgdc:Bounds height=\"35.0\" width=\"35.0\" x=\"140.0\" y=\"230.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNEdge bpmnElement=\"flow3\" id=\"BPMNEdge_flow3\">\n        <omgdi:waypoint x=\"575.0\" y=\"247.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"620.0\" y=\"247.0\"></omgdi:waypoint>\n        <bpmndi:BPMNLabel>\n          <omgdc:Bounds height=\"14.0\" width=\"24.0\" x=\"575.0\" y=\"247.0\"></omgdc:Bounds>\n        </bpmndi:BPMNLabel>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow4\" id=\"BPMNEdge_flow4\">\n        <omgdi:waypoint x=\"555.0\" y=\"267.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"555.0\" y=\"310.0\"></omgdi:waypoint>\n        <bpmndi:BPMNLabel>\n          <omgdc:Bounds height=\"14.0\" width=\"24.0\" x=\"555.0\" y=\"267.0\"></omgdc:Bounds>\n        </bpmndi:BPMNLabel>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow6\" id=\"BPMNEdge_flow6\">\n        <omgdi:waypoint x=\"355.0\" y=\"247.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"535.0\" y=\"247.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow7\" id=\"BPMNEdge_flow7\">\n        <omgdi:waypoint x=\"555.0\" y=\"365.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"555.0\" y=\"410.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow8\" id=\"BPMNEdge_flow8\">\n        <omgdi:waypoint x=\"535.0\" y=\"430.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"302.0\" y=\"429.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"302.0\" y=\"275.0\"></omgdi:waypoint>\n        <bpmndi:BPMNLabel>\n          <omgdc:Bounds height=\"14.0\" width=\"48.0\" x=\"361.0\" y=\"438.0\"></omgdc:Bounds>\n        </bpmndi:BPMNLabel>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow9\" id=\"BPMNEdge_flow9\">\n        <omgdi:waypoint x=\"575.0\" y=\"430.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"890.0\" y=\"430.0\"></omgdi:waypoint>\n        <bpmndi:BPMNLabel>\n          <omgdc:Bounds height=\"14.0\" width=\"48.0\" x=\"659.0\" y=\"437.0\"></omgdc:Bounds>\n        </bpmndi:BPMNLabel>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow10\" id=\"BPMNEdge_flow10\">\n        <omgdi:waypoint x=\"725.0\" y=\"247.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"770.0\" y=\"248.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow11\" id=\"BPMNEdge_flow11\">\n        <omgdi:waypoint x=\"790.0\" y=\"268.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"789.0\" y=\"337.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"608.0\" y=\"337.0\"></omgdi:waypoint>\n        <bpmndi:BPMNLabel>\n          <omgdc:Bounds height=\"14.0\" width=\"24.0\" x=\"672.0\" y=\"319.0\"></omgdc:Bounds>\n        </bpmndi:BPMNLabel>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow12\" id=\"BPMNEdge_flow12\">\n        <omgdi:waypoint x=\"810.0\" y=\"248.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"855.0\" y=\"248.0\"></omgdi:waypoint>\n        <bpmndi:BPMNLabel>\n          <omgdc:Bounds height=\"14.0\" width=\"24.0\" x=\"810.0\" y=\"248.0\"></omgdc:Bounds>\n        </bpmndi:BPMNLabel>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow13\" id=\"BPMNEdge_flow13\">\n        <omgdi:waypoint x=\"907.0\" y=\"276.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"907.0\" y=\"413.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow14\" id=\"BPMNEdge_flow14\">\n        <omgdi:waypoint x=\"175.0\" y=\"247.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"250.0\" y=\"247.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n    </bpmndi:BPMNPlane>\n  </bpmndi:BPMNDiagram>\n</definitions>',0),('3',1,'D:\\apache-tomcat-8.0.36\\webapps\\Spring-activiti\\WEB-INF\\classes\\process\\purchase.bpmn','1','<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:activiti=\"http://activiti.org/bpmn\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:omgdi=\"http://www.omg.org/spec/DD/20100524/DI\" typeLanguage=\"http://www.w3.org/2001/XMLSchema\" expressionLanguage=\"http://www.w3.org/1999/XPath\" targetNamespace=\"http://www.activiti.org/test\">\n  <process id=\"purchase\" name=\"purchaseprocess\" isExecutable=\"true\">\n    <startEvent id=\"startevent1\" name=\"Start\" activiti:initiator=\"${starter}\"></startEvent>\n    <userTask id=\"purchaseAuditi\" name=\"采购经理审批\" activiti:candidateGroups=\"采购经理\"></userTask>\n    <sequenceFlow id=\"flow1\" sourceRef=\"startevent1\" targetRef=\"purchaseAuditi\"></sequenceFlow>\n    <exclusiveGateway id=\"exclusivegateway1\" name=\"Exclusive Gateway\"></exclusiveGateway>\n    <sequenceFlow id=\"flow2\" sourceRef=\"purchaseAuditi\" targetRef=\"exclusivegateway1\"></sequenceFlow>\n    <userTask id=\"updateapply\" name=\"调整申请\" activiti:assignee=\"${starter}\"></userTask>\n    <sequenceFlow id=\"flow4\" name=\"不通过\" sourceRef=\"exclusivegateway1\" targetRef=\"updateapply\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${purchaseauditi==\'false\'}]]></conditionExpression>\n    </sequenceFlow>\n    <exclusiveGateway id=\"exclusivegateway2\" name=\"是否重新申请\"></exclusiveGateway>\n    <sequenceFlow id=\"flow5\" sourceRef=\"updateapply\" targetRef=\"exclusivegateway2\"></sequenceFlow>\n    <endEvent id=\"endevent1\" name=\"End\"></endEvent>\n    <sequenceFlow id=\"flow6\" name=\"不重新申请\" sourceRef=\"exclusivegateway2\" targetRef=\"endevent1\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${updateapply==\'false\'}]]></conditionExpression>\n    </sequenceFlow>\n    <sequenceFlow id=\"flow7\" name=\"重新申请\" sourceRef=\"exclusivegateway2\" targetRef=\"purchaseAuditi\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${updateapply==\'true\'}]]></conditionExpression>\n    </sequenceFlow>\n    <subProcess id=\"pay\" name=\"付费子流程\">\n      <startEvent id=\"startevent2\" name=\"Start\"></startEvent>\n      <userTask id=\"financeaudit\" name=\"财务审批\" activiti:candidateGroups=\"财务管理员\"></userTask>\n      <sequenceFlow id=\"flow9\" sourceRef=\"startevent2\" targetRef=\"financeaudit\"></sequenceFlow>\n      <exclusiveGateway id=\"exclusivegateway3\" name=\"Exclusive Gateway\"></exclusiveGateway>\n      <sequenceFlow id=\"flow10\" sourceRef=\"financeaudit\" targetRef=\"exclusivegateway3\"></sequenceFlow>\n      <exclusiveGateway id=\"exclusivegateway4\" name=\"Exclusive Gateway\"></exclusiveGateway>\n      <sequenceFlow id=\"flow11\" name=\"通过\" sourceRef=\"exclusivegateway3\" targetRef=\"exclusivegateway4\">\n        <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${finance==\'true\'}]]></conditionExpression>\n      </sequenceFlow>\n      <userTask id=\"manageraudit\" name=\"总经理审批\" activiti:candidateGroups=\"总经理\"></userTask>\n      <sequenceFlow id=\"flow12\" name=\"金额大于1万\" sourceRef=\"exclusivegateway4\" targetRef=\"manageraudit\">\n        <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${money>10000}]]></conditionExpression>\n      </sequenceFlow>\n      <userTask id=\"paymoney\" name=\"出纳付款\" activiti:candidateGroups=\"出纳员\"></userTask>\n      <sequenceFlow id=\"flow13\" name=\"金额小于1万\" sourceRef=\"exclusivegateway4\" targetRef=\"paymoney\">\n        <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${money<10000}]]></conditionExpression>\n      </sequenceFlow>\n      <endEvent id=\"endevent2\" name=\"End\"></endEvent>\n      <sequenceFlow id=\"flow14\" sourceRef=\"paymoney\" targetRef=\"endevent2\"></sequenceFlow>\n      <exclusiveGateway id=\"exclusivegateway5\" name=\"Exclusive Gateway\"></exclusiveGateway>\n      <sequenceFlow id=\"flow15\" sourceRef=\"manageraudit\" targetRef=\"exclusivegateway5\"></sequenceFlow>\n      <endEvent id=\"errorendevent1\" name=\"总经理不同意\">\n        <errorEventDefinition errorRef=\"payment_reject\"></errorEventDefinition>\n      </endEvent>\n      <sequenceFlow id=\"flow17\" name=\"通过\" sourceRef=\"exclusivegateway5\" targetRef=\"paymoney\">\n        <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${manager==\'true\'}]]></conditionExpression>\n      </sequenceFlow>\n      <endEvent id=\"errorendevent2\" name=\"财务不同意\">\n        <errorEventDefinition errorRef=\"payment_reject\"></errorEventDefinition>\n      </endEvent>\n      <sequenceFlow id=\"flow18\" sourceRef=\"exclusivegateway3\" targetRef=\"errorendevent2\">\n        <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${finance==\'false\'}]]></conditionExpression>\n      </sequenceFlow>\n      <sequenceFlow id=\"flow23\" sourceRef=\"exclusivegateway5\" targetRef=\"errorendevent1\">\n        <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${manager==\'false\'}]]></conditionExpression>\n      </sequenceFlow>\n    </subProcess>\n    <boundaryEvent id=\"boundaryerror1\" name=\"Error\" attachedToRef=\"pay\">\n      <errorEventDefinition errorRef=\"payment_reject\"></errorEventDefinition>\n    </boundaryEvent>\n    <sequenceFlow id=\"flow19\" name=\"捕获子流程异常\" sourceRef=\"boundaryerror1\" targetRef=\"updateapply\"></sequenceFlow>\n    <sequenceFlow id=\"flow20\" name=\"进入付费子流程\" sourceRef=\"exclusivegateway1\" targetRef=\"pay\">\n      <conditionExpression xsi:type=\"tFormalExpression\"><![CDATA[${purchaseauditi==\'true\'}]]></conditionExpression>\n    </sequenceFlow>\n    <userTask id=\"receiveitem\" name=\"收货确认\" activiti:assignee=\"${starter}\"></userTask>\n    <sequenceFlow id=\"flow21\" sourceRef=\"pay\" targetRef=\"receiveitem\"></sequenceFlow>\n    <endEvent id=\"endevent3\" name=\"End\"></endEvent>\n    <sequenceFlow id=\"flow22\" sourceRef=\"receiveitem\" targetRef=\"endevent3\"></sequenceFlow>\n  </process>\n  <bpmndi:BPMNDiagram id=\"BPMNDiagram_purchase\">\n    <bpmndi:BPMNPlane bpmnElement=\"purchase\" id=\"BPMNPlane_purchase\">\n      <bpmndi:BPMNShape bpmnElement=\"startevent1\" id=\"BPMNShape_startevent1\">\n        <omgdc:Bounds height=\"35.0\" width=\"35.0\" x=\"55.0\" y=\"170.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"purchaseAuditi\" id=\"BPMNShape_purchaseAuditi\">\n        <omgdc:Bounds height=\"55.0\" width=\"105.0\" x=\"135.0\" y=\"160.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"exclusivegateway1\" id=\"BPMNShape_exclusivegateway1\">\n        <omgdc:Bounds height=\"40.0\" width=\"40.0\" x=\"285.0\" y=\"168.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"updateapply\" id=\"BPMNShape_updateapply\">\n        <omgdc:Bounds height=\"55.0\" width=\"105.0\" x=\"253.0\" y=\"240.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"exclusivegateway2\" id=\"BPMNShape_exclusivegateway2\">\n        <omgdc:Bounds height=\"40.0\" width=\"40.0\" x=\"167.0\" y=\"247.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"endevent1\" id=\"BPMNShape_endevent1\">\n        <omgdc:Bounds height=\"35.0\" width=\"35.0\" x=\"74.0\" y=\"250.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"pay\" id=\"BPMNShape_pay\">\n        <omgdc:Bounds height=\"321.0\" width=\"588.0\" x=\"480.0\" y=\"21.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"startevent2\" id=\"BPMNShape_startevent2\">\n        <omgdc:Bounds height=\"35.0\" width=\"35.0\" x=\"540.0\" y=\"101.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"financeaudit\" id=\"BPMNShape_financeaudit\">\n        <omgdc:Bounds height=\"55.0\" width=\"105.0\" x=\"620.0\" y=\"91.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"exclusivegateway3\" id=\"BPMNShape_exclusivegateway3\">\n        <omgdc:Bounds height=\"40.0\" width=\"40.0\" x=\"770.0\" y=\"99.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"exclusivegateway4\" id=\"BPMNShape_exclusivegateway4\">\n        <omgdc:Bounds height=\"40.0\" width=\"40.0\" x=\"855.0\" y=\"100.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"manageraudit\" id=\"BPMNShape_manageraudit\">\n        <omgdc:Bounds height=\"55.0\" width=\"105.0\" x=\"940.0\" y=\"93.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"paymoney\" id=\"BPMNShape_paymoney\">\n        <omgdc:Bounds height=\"55.0\" width=\"105.0\" x=\"823.0\" y=\"181.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"endevent2\" id=\"BPMNShape_endevent2\">\n        <omgdc:Bounds height=\"35.0\" width=\"35.0\" x=\"858.0\" y=\"281.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"exclusivegateway5\" id=\"BPMNShape_exclusivegateway5\">\n        <omgdc:Bounds height=\"40.0\" width=\"40.0\" x=\"972.0\" y=\"188.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"errorendevent1\" id=\"BPMNShape_errorendevent1\">\n        <omgdc:Bounds height=\"35.0\" width=\"35.0\" x=\"975.0\" y=\"281.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"errorendevent2\" id=\"BPMNShape_errorendevent2\">\n        <omgdc:Bounds height=\"35.0\" width=\"35.0\" x=\"773.0\" y=\"191.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"boundaryerror1\" id=\"BPMNShape_boundaryerror1\">\n        <omgdc:Bounds height=\"30.0\" width=\"30.0\" x=\"458.0\" y=\"253.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"receiveitem\" id=\"BPMNShape_receiveitem\">\n        <omgdc:Bounds height=\"55.0\" width=\"105.0\" x=\"721.0\" y=\"400.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"endevent3\" id=\"BPMNShape_endevent3\">\n        <omgdc:Bounds height=\"35.0\" width=\"35.0\" x=\"871.0\" y=\"410.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNEdge bpmnElement=\"flow1\" id=\"BPMNEdge_flow1\">\n        <omgdi:waypoint x=\"90.0\" y=\"187.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"135.0\" y=\"187.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow2\" id=\"BPMNEdge_flow2\">\n        <omgdi:waypoint x=\"240.0\" y=\"187.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"285.0\" y=\"188.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow4\" id=\"BPMNEdge_flow4\">\n        <omgdi:waypoint x=\"305.0\" y=\"208.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"305.0\" y=\"240.0\"></omgdi:waypoint>\n        <bpmndi:BPMNLabel>\n          <omgdc:Bounds height=\"14.0\" width=\"36.0\" x=\"305.0\" y=\"208.0\"></omgdc:Bounds>\n        </bpmndi:BPMNLabel>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow5\" id=\"BPMNEdge_flow5\">\n        <omgdi:waypoint x=\"253.0\" y=\"267.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"207.0\" y=\"267.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow6\" id=\"BPMNEdge_flow6\">\n        <omgdi:waypoint x=\"167.0\" y=\"267.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"109.0\" y=\"267.0\"></omgdi:waypoint>\n        <bpmndi:BPMNLabel>\n          <omgdc:Bounds height=\"14.0\" width=\"60.0\" x=\"109.0\" y=\"274.0\"></omgdc:Bounds>\n        </bpmndi:BPMNLabel>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow7\" id=\"BPMNEdge_flow7\">\n        <omgdi:waypoint x=\"187.0\" y=\"247.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"187.0\" y=\"215.0\"></omgdi:waypoint>\n        <bpmndi:BPMNLabel>\n          <omgdc:Bounds height=\"14.0\" width=\"48.0\" x=\"192.0\" y=\"228.0\"></omgdc:Bounds>\n        </bpmndi:BPMNLabel>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow9\" id=\"BPMNEdge_flow9\">\n        <omgdi:waypoint x=\"575.0\" y=\"118.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"620.0\" y=\"118.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow10\" id=\"BPMNEdge_flow10\">\n        <omgdi:waypoint x=\"725.0\" y=\"118.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"770.0\" y=\"119.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow11\" id=\"BPMNEdge_flow11\">\n        <omgdi:waypoint x=\"810.0\" y=\"119.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"855.0\" y=\"120.0\"></omgdi:waypoint>\n        <bpmndi:BPMNLabel>\n          <omgdc:Bounds height=\"14.0\" width=\"24.0\" x=\"806.0\" y=\"101.0\"></omgdc:Bounds>\n        </bpmndi:BPMNLabel>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow12\" id=\"BPMNEdge_flow12\">\n        <omgdi:waypoint x=\"895.0\" y=\"120.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"940.0\" y=\"120.0\"></omgdi:waypoint>\n        <bpmndi:BPMNLabel>\n          <omgdc:Bounds height=\"14.0\" width=\"66.0\" x=\"879.0\" y=\"86.0\"></omgdc:Bounds>\n        </bpmndi:BPMNLabel>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow13\" id=\"BPMNEdge_flow13\">\n        <omgdi:waypoint x=\"875.0\" y=\"140.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"875.0\" y=\"181.0\"></omgdi:waypoint>\n        <bpmndi:BPMNLabel>\n          <omgdc:Bounds height=\"14.0\" width=\"66.0\" x=\"821.0\" y=\"163.0\"></omgdc:Bounds>\n        </bpmndi:BPMNLabel>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow14\" id=\"BPMNEdge_flow14\">\n        <omgdi:waypoint x=\"875.0\" y=\"236.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"875.0\" y=\"281.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow15\" id=\"BPMNEdge_flow15\">\n        <omgdi:waypoint x=\"992.0\" y=\"148.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"992.0\" y=\"188.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow17\" id=\"BPMNEdge_flow17\">\n        <omgdi:waypoint x=\"972.0\" y=\"208.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"928.0\" y=\"208.0\"></omgdi:waypoint>\n        <bpmndi:BPMNLabel>\n          <omgdc:Bounds height=\"14.0\" width=\"100.0\" x=\"903.0\" y=\"187.0\"></omgdc:Bounds>\n        </bpmndi:BPMNLabel>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow18\" id=\"BPMNEdge_flow18\">\n        <omgdi:waypoint x=\"790.0\" y=\"139.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"790.0\" y=\"191.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow23\" id=\"BPMNEdge_flow23\">\n        <omgdi:waypoint x=\"992.0\" y=\"228.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"992.0\" y=\"281.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow19\" id=\"BPMNEdge_flow19\">\n        <omgdi:waypoint x=\"458.0\" y=\"268.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"358.0\" y=\"267.0\"></omgdi:waypoint>\n        <bpmndi:BPMNLabel>\n          <omgdc:Bounds height=\"14.0\" width=\"84.0\" x=\"331.0\" y=\"196.0\"></omgdc:Bounds>\n        </bpmndi:BPMNLabel>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow20\" id=\"BPMNEdge_flow20\">\n        <omgdi:waypoint x=\"325.0\" y=\"188.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"480.0\" y=\"181.0\"></omgdi:waypoint>\n        <bpmndi:BPMNLabel>\n          <omgdc:Bounds height=\"14.0\" width=\"84.0\" x=\"350.0\" y=\"189.0\"></omgdc:Bounds>\n        </bpmndi:BPMNLabel>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow21\" id=\"BPMNEdge_flow21\">\n        <omgdi:waypoint x=\"774.0\" y=\"342.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"773.0\" y=\"400.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"flow22\" id=\"BPMNEdge_flow22\">\n        <omgdi:waypoint x=\"826.0\" y=\"427.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"871.0\" y=\"427.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n    </bpmndi:BPMNPlane>\n  </bpmndi:BPMNDiagram>\n</definitions>',0),('4',1,'D:\\apache-tomcat-8.0.36\\webapps\\Spring-activiti\\WEB-INF\\classes\\process\\leave.leave.png','1','�PNG\r\n\Z\n\0\0\0\rIHDR\0\0\�\0\0\�\0\0\0\r�Q\0\0\0:IDATx\�\�\��\\e�\'�E���0˰���˲�.�.�f]FAA��ө\\ 1\�@`0KC\�\� K\�\�	xAP@\�\�u��*H&CV�a�\�\0�Kb ��\��t��R\�N��\�\�\�\�\���y>u�\�T7�\��\��{�sii\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0`�U*�~�\�k֬�]�X�\�|�\�Z�\�\�\�\�ʕ+�\�L�Ju\\\0\Z(:W�V���[����\�/kM\�֯__��\�[�M��\���\n�\�\�8�4�Z@�D�ֹj\�N\�˩�\�\�_*���\�\�\�j\r#�\nds�\��zſT@W\�1��\�\Z$9Q���U�/P\�\�qji��&\�`��7OV��\�\�}�gf-�\�9EUP\�5u�Z�U`\�\�\�Y[��G+�t\�I��x.^SXmC\�P\�\�qu�Z�}hx\�~��\�6\�\\\�\�W�]��*\�0VJ�R}T\�\�quܠ�A-;\n4�h\�_��A;X�ªh\�80�gR[R}<DW\�\�q�Z�4�*а�}\�͝�\�xMaU�a�B�!5����5,�\�\�A-�Z�}�	����˗/�\�E�\�X�p\���s\�n�1cFeڴiY�3gΫ_��9�\�3\�:����|\�\�\�j:X�60@H\�\��\�:��Բ�j�U�\�\�ŋ?q�\��\�̙3�r\�k��\�}�\�ٍ\�_}�\�JnÆ\r�x��\���r\�\�Vb��O\�3eʔl\�\��\�`E;^SXmh�����\�\'\�\�\�:nPKP\�쫌S\�\�\�vꩧ\���/|\��+V�Ȃ�plܸ��z�\�\�W��\�ϝ;����>�Ӷj\�����\rZ�\�5�Uц�䭅\�I\�	W\�\�q�Z�4�*\�\�\�u\�Q�|��߭��\��\�u�\�WRP\�x\�1\�\\\�\�\��[x����wW\���雏l�\�\�5�UцQv\�0;\�\�]_Wk\�q�Z�4}.\Z\�SN�Ʊ\��\�O�\�\�Hz\�\�*˗/\�8{�\�\'RX�[zt�v�G\�d��\�)��64($�\r\�{~2�eu\\7�ePK��2\�B�y�~\���\�/��w3g\�|6�\��\�\�\��z\�ʃ�p�\�#�\�xMaU��\�B�H�_\�\�q�Z�\�0r\�p\�I͐���\�k_Jay��\�\�)\�qs�_�\��0�x-\�Q\\ma[;ǱѿG\�\�q�Z�쫰\�\�\�]qN�]w\�Ui�\�}\�{OL�>�:W\�������*���\�\\\�-։upEF8$�\�ӄ\�\�A-�Z�}�q\��SN�\'.\�\�h�\�\����m�O`���\�\n��h\�(�?g�R;��8�\�B�\�\�A-�Z�}�&�I�[@�\�խ�k\�ڵ�N�>�_\n�\�\�>�\�+\�C)\�[*\�\n��\r#\�I���\�\�*\�ǡ�\��\�7!ò:��\�2��\�Wir��~�?^u\�U��r�\�gߗ��Y>�\�+\�\�ڹʛB�h\��\�j\�Rӆ�kCrގ�y}\�ީ��\��ji�U�Xgg\�;fΜ��\r6�YP^�v\��\�\\\�\�+ښ�\r\r6Xx(�n�>\�����:�SW\�\rj\�\�\�4�.�ଅV\�ڗ�������_}\":X�64N��7oc\�\�\�9�C\r\�C\r\�����\��6U\�\�q�Z�\\�}�&�hѢ;����1\�K�.]\��k,E\Zkڴi�hQ{\�\�\�>4ķ\r5�n�>ܐ\\��\�7k\'|�\�TW\�\rj\�si�\\4�����\�\�<(�Y�\�Ա(\�\�`)\�\���S\�n\�J\�q�3E�u\�_��!���\�xݦ\�:nPK�K\�碉͝;w\�u\�\�<(?�\�SO�b��N���\rcZs�;�Ԟm=�r�N�Ϸ#$o\�OSlSu\\7��ϥ\�s\�\�f̘Qy�\�W+\� ��N���\rc_sj;�ՙ�\�\�\�\�	ߞ�<\���ۦ\�:nPK�K\�\�\�;I\�BP\���Hm�����v�\�\�\�%\�Y\�^7@��\�\�\�ǒ\���+̘9&\�U\��6��ϥ	ʘQrPֆ\�F��\�\�7��\���\�va���\�SOe\��\�\'?\�\�^xa�\�}�\�l�x\�w\�Q��\�k+?��*��\�g�\�x\�\�+�\�_�#�H\���D\���{lg-�\����\�\�\�\�\�:\'�xb�r\\\�._\��I\�f�\�ݞ:��\�ܶokڞ\��p\�ߺ\�\�w{Oi���⬎\������|z����\���\�\�\�1��(�\�/-\�רm\�,�\��\�Ν;\���~�\�}\�\�也�\�g\�.O�O�Z�eA\�r����\�p�r\�;X�W���\�\�9眓}\�t\�MYh\�s\�=�P�Ԟ��l�_|1{|\�\�P\�w\�}+=�Pj?��O��\�#�8b��A��o}k\�\�.\�}׻\�5\�\�.�\�R�\�\�>6`G\�\�.\�\�[o�5�]�7\�q�\�v�\�\�~\�r\�\���l�{.��\��\�\�/�|�eE�I\\s\�r}(}�et\�ܖ��\�\��\�\��q\�7���\��ڻK\�j�\�\�sW�����\�ŏ�׿\�\�]^~K#�i3\�C=t\�ף\�G\�\�쮻�\�~�\�_\�~ǯ~����\�tF٠\�$\�\Z\��|\�y\�m�܊+*{\�G��\�Ol�ZL`p������\�����\�y睕\�ӧg?\��\�2�\�i���\��Y�z�J����\�\�\�h��\�?��(�~\�1:4\�ׯ\�dD?f\r\�\�\�yh�mo~�\����\�3\�d\�\�;�uݙ3gf\�\�\�\�u\�]�\�0�\�N;U,X�u��\�V�?^��B�\�D\�\�Y0\��@ꩧf\�������=���w\�+�\0_\Z�6��\�\�V($ \�\�\�\�N��-_ h8��/�0�x��[w��v��\�\�G,\�}\��\��ɱα\�z߹���ɬS�Br���\���\�\�w���c��i3\��[Z\'\�\��\���͂��\�˳�_����.O��lPk�\rj\ru�}&�m������د��\�\�\��\�\�v\�m\�\�#�<ҿ�\�G\�}7A�I)\�|\�76\�}�S���Nk\�;XQgϞ�\�X\�sb��\�\�\�Hd��]�A9֍Ώ�\�\�\�\�\�\�s��㕯s�\�W��\'f\Z�\��\�~w��\�\�\�\�}�\�l\�3�\�_�Y�X�\�:�3�㋤�:���\�$�9[\�l�\�;̐��[\���O\� ��\�\�p{wyF>;5��\�V��\�\�t\�\��\�be\�\�=�Q۴:\�QWc���\�o\�\�z\�Q��>�\�\�\�\�b96\�g���.O��lPk�jm�E+\�SE_*߿c@+\�r�)���O�\���\�>Y�ς2�J\�y\�]�x�\�y�\�ݛ\n\��\�\�u�b�0��s\�\"G�p�\�E\'%\�\�Ԟ�\��\\߭\�)���B{\�%�l2\��љ�/\��\�{o�\\�\�8(�{ⰽx�V���\"�;�ѡ�\�8ǭ~�#_?�_�/�|  ��\�{oE���;��ܲ�a9�);l���\�\�\�����ҩY^\���\�\�wK\�B�\�	ˊ\�N\��wгNzWiN\���{����\�0Em\�(��š�q�O\�֨�\�b9j�@�\�Z�\�yP6�5���:����n\�N��S\�b�)�S\�b?l��Q\��b\0+���\�\�?���\�\�\�k&��N:\�O�<�\�\�\�E�\��\�\�\n�\�oS\0z�O����\��0-:\'�d\�<,\�\�\'(G�\�x\�\����]-d\�!߃�^V����/d\�\�\�:/\��W\�x\�g>���V�Z��s\�\�ۯ�搢����)ڰ͝�\�\�\��\�\r�c��/���N\�ё��\�2����\�X�@}xGOi\�ҷ\�c\�ގ���\�\�X,,G\�{�s�\��\��ѱuy�\�q�Z`_\�Z�~V>\��Z>H��\�|g�Ɏ�\�8���z�_K�4�h_�\�����f��̤v�\�\'��-�\�2fAyٲew���}\�\'\����\���\�b^hk�:�wZ��\�\�=\'��\�p����\���7o\�&\�\'Gg.�;�\�k�\�:d���X?)�=�0���\�\�Ծ7���fUm�^?ctd\��o)3P\'��at�[\�\�hq��8�3��p\0�٫\�\�r��Qt�cV\�\�\�=\�w\�e�����\�\�ڣ}\��w-�����\�V\�\�\��O�i��<�\�A�	�c\"!\�\�\�>�hѢl��\�￐^~\r�X?��\��j�b\���}�蓕\�\�MNw���N9\�\'�����MT\�I_�>}��̘1\�O}�/\���c\��âc$1/�y0nP�\�.�(;�,By^̢W\�;\�mE\�o\�\�\�g)Z�p{�x���|\�b\�w\�:p[;.�\�\�\�-ՙ�|�@цa�\�\�s!k;\�ù\�\�`\�ҹ�q\�b\\���/��j�\�\�@=�o���:\��\��\�\�\�<4GH�\�:���\�\�\���\��F���\��y(3\�Qg\�V\�.O�:nPk\�\�>]\�@S\�r3�r\�\�轖.\�\���\�ũq(v~��\�f���\�Ν�T\�h�-�{ڴi=>��)\�qѕ(�q�Yޡɯz��\�q�\�\�.\��<T\�ߏ3?\���3\�\�~��w~\���r�\�|v#���sg�\��\�g.\�[\\�;\�pŗH>��i�Ԝ3�\�\�Sц!�����j\�y�Wǭߐou����\�\�\�Ύ\�\������s\��|�\�\��tnυ�\�S\�;\�\\�<\�=�㵘Q\�7kg�\�1f~�x\\�\"�+\�K]V\�\rjM䠜\�3�)o�\\ۇ�?��>(G�+�}T�\Z\�ɜ9s>~�\�Go�/\�FI_~\n�nmm}�O`l�r~�\�ڙ\�\�\�/�P�\�[G\�\�\�8�!�\�\�x��\�b�\��{j\����{\�����|A䇌\�ⵖ�\�\�v\�⵸xXb�\�Tц\�\n\�#ѩ_?��oB\��\���g\�\�:0ح��A\��Su��\�\�O?}X�f�\�\�A�\��[��K\��\��A��n\'\�R3?�|�Ƀ��e&�c�9\�\�.]�J#�N;\�q\��ԩS�\�\'v\�n�9w�6L��<ҡ[W�\�q�Z~_�ɇ�d���\�\�p���A9���\�\�G\�3͵�A��2i\�\��?��\�?r\�%�l͐�nݺWgϞ�D�Ph�\�u�&jS��Ć<�4J\�W\�5uܠ�}u�w?��\����Y�6\\u\�U/�\�Lr5$�ek+\�:X0\�\��p;\�C:\�Q\�\�q�Z�UM����+PϜ9s\�E]�\�H���[�IV�m��;\�4\�g�\�quܠ�}U��2\�\�\�r\n�\�\�հ}�ї��Pqu\�p�s�mE�\�\�\��-�\�wU\�\�q�Z�U;JSikk���\�_�\�W\�y衇�5\��\�SO�[�|�\�1�<mڴ\\\�Z\�V�aR:�\Z�\�\�\�\�\'SHV\�\�q�Z�U;\�7\�\�\��3g\�\�\�9\�\�\�\��=�أ��xÆ\rO�Y�殥K���7o޽�B\�)h+\�mE&��:\�jfJW\�\rj\�W5�*T\n���\�R{$�WR�Tۓ�ݖ\�\�R�2{�\�7\�b���\r�\'mHV\�\�q�Z�U;\n(ڊ6P\�\�^2�C�:��Բ�j�U@\�V��Z\�QH-\�\�;;;;_�-�����oT\�5uܠ�}U\�\�mE&�jPδw\�;���y\�y\�\�\�/\�}W\�h\�\�M\�0Y����r�\�./�����L\�\��\�\rj\�si�U@\�\�mhTP\�)}\��\�{x\�c���3o�l�\�_�\����Q�#T\�\�.�\�\�\�.?��8O\�\��\�\�\�������\�e_�����hC���	=\�=\"�w��\��J\�\��ݾd\��\�\�Y\�!Bq,wt�\�H��-\�}c\�x��\��\�qMy|��i_}I�˾j_mMц\��@\�]:�}Iy\��ωí����vt�\�\�\�1c�\�� �\��hr\�\�]�x��]\�K\�qMy�f\�zs\�W�\�e_��\�F�XT������+��\�\�A9��@\�\�U>-�K�wQx\�*\�\�g��^\�=`��[wςs\n\�}\�O;��Ʒź긦���\�\�ַ�}�7���,(\�\�ʕ+�\\�n�\�ؤm\�ڵ������\�\�A9f�\�\�\����ècf�F��|]H\�)\�xAW\�q�r�S��\�\��0lu\\S\�G%(�Q\�W7��0�eP7J�\�gn�\�\�<��\���@6W��\�Uoo}οT<(\�c�\�\�\�w�>\�\�S>�?//����\�n��\�qѯ�\�\�ٵ�k��\���\�:>�f͚�\�i_}\�\�A-�Z�����I�\��E�CN\�k�\�+*\�i\��5->���dZP��\�1f�\�\�^�\�q\�\�\���\�uO���{��\�u�\�y²\�c\�|�\���\�H�\�:>b�\�Im�\�	�Z�\0\�\���\�4O�2e[&�Baz%f�;zJs���\�pN\�\�zʇ\�լ\�P\�\�\�\�C��gg��J-\�SNa���(�\���\�=fKL�A�	2AaP��\�\�\�>���+Q��\�A�L���@�kmm}O\�W�%\�`\0\Z���b]m�\�\"��4�\�ӧ�����P�hl�\�\'�M\�[\n·\�2�������Ӿ�3[B\r��E��ڐ�\�*;WtҀ�\�{�}�[B\r�qz�\�\�\n�\�l!\�I\�\��\�\��Na�m	5�\�\�\n\�\�v�-:i��\���~�\Z@c�x_\�BPv�2\�c��\�\�\Z[B\r�1\���-�dW��4`\�M�:u�����-��`�y )\nM�\�-��\Z��\r\�灾����\�kK��\0(؀}h\�n���ȖP�P��<В\�\�),\�`K��\0(؀}\�\�O?�ڵ��\Z��\r\�灾����]eK��\0(؀}h\�\�Qn-\n߷%\�`\0l�>�d��*�}�r[B\r@�\��@\�~:+�Km	5\0�\�}�\�\�\�.�%\�`\0l�>�d�^���\�[��\Z��\r\�灾���\�.�%\�`\0l�>�\�Ǧ�̖P�P��<В\�j~�PXjK��\0(؀}h\�\�Q>!�\�sm	5\0����\�Ƿ\�n���\��SP�[B�\0��{�\�^\�RPN��Cl)h�}��S[lK\�w�`���ߴ��|۔)Sv���������B�p�-�\���\r�\�>�\�`A9u\����)�\�3S[hK\�w�`���t��\�f��i�\�\�q��-�\���\r�\�~�ٹ\�\�M�\��z\��]\0(\��\�v��6�\�C\n\��+\�l	�.\0l`t��j\�M>\���\�K\�>;ߖ\�\�@�F\�\�U6�M��.K\�X[B�\0��}�<4�����\n�/\��]\0(\�Д*�\�?���5k\���X,Vn��f�	Zoooe\�ʕ�Nm��L�\�\�S;ʖ\�\�@���!yժU�u\�\�U^~�e��\���\�+�\�z\�)4οT&\�w�ũͶ%��\0P��)\�L��\�\�a�\�o��~�R�`\�͗\nGK\�w�`Cs�í\�\�n)(�\�_*I\n\�W���M�%��\0P��)\���\�h\�eu������r�-�\���\r\�:(��7OV��\�\�}�gf-�\�9AVP�m�n�*�\�\��]\0(\�0n���Ϭ�\������\�MZ<�	��2G[[\�u�B\�P[B�\0\�mP~��\�6\�y�\�}\���2���\���%��\0P�a\�\��\�3hP�ׄYA��\����?nK\�w�`ø\r\��\�\�9hP�ׄYA�Q:;;_�m\���m���n.\n��\��]\0(\� (k��vj\�.�w,\����\�_\����\�:�\�֮7Y�����A�<wy�-\�ݥe�^�H!�֩S�~Ė\�\�@��q�\�*׃\�xM���{J\�8�{\�.)�A�\�e�\�\�[>\����W\�4��|T\�\��\�\'\���K\�\�\�.γ�\��\�SP\�ߖ\�\�@��q�\\�lР�	���h;���G\��\�\�b�x~W\�\�ðۗ�\�5;;\�;D(�\�\�\�)0����o�\�w^\\~���)��פ��-�\���\r\�6(oX{w\�\����a\�\�xM��%�\��ۗ��N!��8\�:Z{OiaGWiN�3\�\�\�q\n\�i��\�!\�\�ŏ��\�U�\�Vl�\�\�;�O��A[B�\0\�mP��\��l�\�9AVPn�8�:q{W��\�\�\����\�U��\�(��\�{��\�\�\���\�)�v\\��o�u\��M�\�|W[[\��%��\0P�a�\�^�<�l~\�uz.^f\�\��\�q\�t\n\��\�a\�13\�?�\�]��?$���p����\�8W9\�)D\�\�r~���)��\�IAy/[B�\0\�eP~����_�\��\�x-\�h\�\��7B��]����\�\�\�\�\�\�_>��[<\�\�q\\����xv\�\�X���}�dS|7\�_(��%��\0P�a|\�^�<�`�r\��zА��X\'\�5�,(��8\�8cf8.\�?W�Ξ��\\�??��\�z\\w\�\',+�;\�\�\�\�^���c�\��`kk\�{m	�.\0l7Ayk�\�f�\�F\�Or�쎞\�\�,0�0�S����aq5\�8Ժvv�\�\�\�\�٭�R��SX~���)��NA�=��~\0\n6���<�Y\�-\�.��2l\���\�v�%��\0P�a\�\�m\r\�yne\�\�w�3g\�\�Ֆ\�\�@��q�5AF��yݬY��ؖ\�\�@�AY���\�\�>mmmW�\�\�8�n\�\�\�\��G��~\0\n6\�ڈ\�ȩ]�\�+j\�\��\�3f�͖\�\�@�AY��\\��\�_3\�\���C=�m	�.\0l��\�\n\�mmm�C�\���\�8�n~\�\��\�і\�\�@�AYۦv\�WT�A�\�\�ͯ����ޖ\�\�@�AYۦ��p�i㵩��]\0(\� (׵\r6ľYy\�g*/��b\�\�;\�̞�m\�ry��\�{\�9\�\�SO=\���\�ŋ������\�ʮ�\�:\�\�?�\�\�*w\�u׀\�\�.k�C��N�z`�i�	�~\0\n6L��\���}\�Ȃr\�\��x\�\��\�|ݷ���Y��\�n��\�\��\�__��\�{���Tn�\�ly����\�y�������\�ʻ\��\���W�Z5`(�\�u\�\r7T�8\�\�=�\�E]T��~�-\�},/\�U(J�m��\�w�`\�\�ў}�\���z\�%�d��\��׋`\�\�Օ=��\�|\'{�>}z\��\�/\���W^�Y���>�ޅVv\�i�\�i��\��\�g��`�}���\�$(��\�\��`���\�0���.\0l\�A9f}W�^��Ϙ�\�Cm>c3\�<��&A9���:fcf8���\�\�,(\�\�~F�����y(�\��=y\��^�|\�w4MP\�Ug�o�\�\�@��q\Z�k\�j�;�/���T��/�2;\��\�?��&A�\�G�\\x\�\�s�\�o��b8��ԧ>5\�\�\�Ҍr~t\� \�\�\�y\�q\�u�\�b�\�\�bV9��\�\0�]\0(\�0A�r�<\�\�\�(\��\�ٹ\��ܯ~��\��\�ؿ\�\�\�x}\�ܹ\�kf�;�\\�܊+���\�o�M\�U\��[e\0�.\0\�Q\�q��#�<\��q~��B]�\�O<�=F�\�hq\��G>�l9��\��\�/lӌr\��w\�}w塇ʖ>�\�M��@\0���\0l�G4(�\�\ro\�.Ε\�\�,o<ơ\�-53ʵ\�=�8<\��6[7~\�`W��Y\�~��;\�\�֧L�\"(\�w(\� (76(ǬnK��N�}��r\r�cַ>�\�\�<\�Z�\�W\�Η��\��-(\�w(\� (�Z�s�\�0\�\�\�\�P\�\�E�joaw�]v\�\�\�^q\�y�\�e?�\�\�ޓ\�n��E\�\�s\�=��\��\�3\�l6s\�:\�3��\�lA@�@�AY\�&(�]\0(\� (k�2�\���\r��&(�]\0(\� (k�2�\���\r��&(�]\0(\� (k�2�\���\r��&(�]\0(\� (k�2�\���\r��&(�]�]\0\n6ʚ��\��`���	\�\0�]\0\n6ʚ��\��`���	\�\0�]\0\n6ʚ��\��`\�\�U,�\�\�n/������~\0\n64\�ʕ+�\\�n�@ڤm\�ڵ�������]\0(\�\� �R\�3�\�r\�o�~�\�L�k&9Broo}οT@�\0\Z(�Cn��\�\�\�\�8v<�\�\�\���C�\�gq��\�w�`�y\05\0�\���\0(؀}@\r@�\��\0j0\0\n6`�P�P��<�\Z��\r\�\�\�`\0l�>�\Z�`�y\0\�`\0�\��(؀}\05@�\��\0��\0\n6`�@\rP��<\0j0��\r\�\�P�l�>�\Z�`+\�`�@\r@��<\0j0\0\n6`�P�P��<�\Z��\r\�\�\�`\0l�>��`�y\05\0�\���\0(؀}@\r@�\��\0j0\0\n6`�P�P��<�\Z��\r\�\�\�`\0l�>��`�y\05X\rP��<\0j0��\r\�\�P�l�>�\Z�`;_��V\�\��~\0\n6L��{�\�^\�RPnkk;Ė\�\�@��ɴ�ߴ��|۔)Sv��\0��\0P�a2\�\����\�a���\�_��\0\��8\�\�n6`Tk�\�_\0e`<u\�t\�\0\ZR�� (\�*\�\�&4��\��\0Ah\����\�\�A�@\��ZN��f\�\�4��:�@P���v�\�\�\�\Z\�\�/\0�2м\�\�J��\�e��\0\�@:0�gR[R}t\�@c�\\N��&\�y8�ٌ�\�2�\�+\n;����.ִ��VN\��y=��J\�|+�����g�\Z���mH\�\��\0l��S�X=�\�-\�;y���-�\�f�e`���0��\�|9�m\�K\�U�of8�\��̶*���nH\�Z6���R�=��\�O3f̨�u\�Y�+VT\�\�ʓO>Yy�\�+a\�ƍ�u\�\�U~�_T��\�l�Y�f\r�oJ�\�Oma\0A9�o�x�\�Lj���o��\�ngϞ]�\�˲ </��B\�k�����W\�\��Lz�bk\��ȅ\�C�\�}\�\�\0C\nɫjC\���\�\��ꫯVn��\�lF�\�w?�\�\�lu\0Ah|H��L��\�&3\�q�u�ܑ�v\�\�\�I\'�T�_lkkS�e`l\�\�]��=\0N��\���f͚\�h�s�\��\�ڙ\�����	\0\���\�\�H6mf`�>\�>�\�\��Z�x�&�_�{4�$\0e`\�6\��ڑ\�\�\��|\�hkk�\�\�s�G�p\��<�\�s���>��_K|\0�20��|HM\��vj�\�\�P\��`\����S�X{u\�\�p\�p���?���ן�D\0e`h!9Y\r��a�\�ڐ��#j^w\�2��4m\��yX�[@5ڹ\�[��\���\�\r^\n�[\n\��\�\0\�;g��\�\��t��\�\�MýO�Hx衇6���\�ٳ\�\���\�C�A\���塆\���\�0l`\"�\��S��[Z\'\�iyH�+Q�����������\�\���\�\�\�\��pCr\�\�_\�bf�@��j�9\�\r�\�\�\Zu�\�-��\�+]\�@P	\�C\r���\�\�mcH\�9g�pA��\�T?Ü�+\�?��c�\�͵�ާ (���m\�t����\�\�\��\�\�\�A�?�\�9��x�|#�v]\�\�\�\�\�>=�\�|A�>�WS{%�����\�KՋW�kj/��\�\�m	�M\�7q1���/�mH\�ש�Om]jO��TjO��6�_U�dK\�\�\�~�\�#�=�\�C�=�\�/R�y�Rz =ޟ\�\�\��}\��\�\�\�I\�\���O\�{ޙ\�OS�#�\�S�Ij�\'�5�ݖ\�?�����Jme�=��\�[bD8-�\�c1�\��L�?J��1��ڍi�����\�\�i���xmjפvUj?H\��]z\\�\����xej���H�����\�.M\��\�w\�\�wR�8-_���څ\��\�J�\�L�_O��v~Z^���֓��\�cWz\�\�8�*=���xn���\�I\���\�N\�g�\���\�\��\���(=��;S;=��?\�\�\�Ծ��OK��^?5-����OJϝ��OH\�\�rGZnO\��\���i���xlz����RZ>&-1=\�I\�\����xTj�\�\���i�#\��<3-\�H\�\�\�r�znWzhkM��KO�eZ><-6αJ˟I˟Nˇ�\�C�O��\��xp����������\�[s�������\�\�\�G\�\�G\�sDg&-�KZ�p>���K\���\�\�>\��\��\��/-�sZ\��3f\��\��^i�?���\��H��!=���޿O\�\�M\��.-�yZ~Oܒ#-�;=\�\���\�\�\��9s\�i�O\��\�5k֬?N˻\�\�_\�\���w����巧��\�ӟ��[\��[\�zo>�\�C�0-�?|��\��s\�\�yÔ)SvH˯\�\�\�|]5�n\�\�@ay{Br}Xv\�20\�r\�s\�\�/��\�\�7n�\�\�\�h�?�\�ytң���輧���#�\�\�G?:�\�\�\���Gq\�;��`�\n\"<D��0�\"\�EZ��ՠ�g<�\�J\"�DH�����c5���2h\"\�Dȉ��\'\�O����o5\�)�R��O�\"LE�J\�S�\�c�\"xE�0�,\�Y���ڧ\"�Ep�\0W\rr��p!/\�^���<�\Z\0�E �Ù#4Fx���?_\r�GU\�\�\�\�yL�\��J\��j@=>k5�.�0�6\�m�ܴ|r5�\Z�\Z��R\rǧGX��\�3�:��\�\��#lG\��!<\�xZ>�\Z̗FP��e\�~��j��F5\�G࿰:\0pq\nT.����|Yu\�\��\�@\�\�VVT~�i�\�\�@ĵՁ�뫃7F\�:��\�~\\\�荁�\�\�-\�A��\�A�Y](��:p���)wTW\��\�Ux��{b@�:0su�\�\�\���\��\�\�\�\�\��_V}���\n=Y z�:`��:��/\�A�\�\� S��\�U�~[��\�\�\�\�\�A�W��V��`Egh{\�E�\�]?���\��\�c\�:���i��5A\0�\�֮r�\�A���g��r�\���Qƽ-�\�\�c\�<&\�(\04��Ϸ�\�9\�\����LԠ\��k\�q�2\0@�\�\�\�\�v���z=��\�\��Dʷ\�u3jr\�:�z\r\00\��\�B\�\���,f��Q&VP\�ly�uz�\�>\��k�}�O\0\�5[�\�\�ZHn\�ư��}��\0�L�\�\�\�Yg�5fA���N�\rʟ�\�\0\0lZ\n\�C\r\�\�\r\��\���I�z玸]f%-W�{>?�ŸӈO\0`s1�\\��-ÿO�@a����\�\�&\�\���7nm�/��j������(]\�y\�W]uUÃ�\\P�/��\0\��Nh\�\�F\��\��\��8�_\�JX���Wm\�JHn\�\��\�j\�\�.߱��\��\�)��/)･\Z\�\�U�4���\�\�6wy�-}\�+w\�\�.}4�}\�\�]ۻʧ\�r\�\�8aY�\�]\�#ۻ�\'\�_ڻoGOin�n�\��\�o\�\�\'\�dT{��ܹsz���k\�f3\��ߟ>}�\�>\0FE��\�wt����|Ht��Z\���SYd�3�ϾDgq�\�\�~�\�\�U\�k�\�mO�����v@�\�/�ߝ:�\�\Z��ΤSv�\�#Z�˧��m\���\�Q�:z�\��ա�Q��\�E�I=��c\�8jF�\�σ삮\�\'\�=�\��O\�)\�\�_���K�:�\�ݲ�󬨃\�=\�Î]\��\��o����׿��!�W]��|2LV\��)ߓ�\�k���aA�\�3\�\�\�mmmW�4\0ݽ\�-��\�)����(5�������\�\�]�v\�\�A�F�,p\���{ۻ��\�p\���N�r\�n��ˋbF&:�Y��|]\�{\�Gu�W\�ͧ\��\�\�5\�a\�4Pԟ<�F�\�\�U��\�wQ7�jUԜ\�Qò�\�]>��\��b�\���E\�f�S�\��;\�\�����N_]*��դ\�=��}�;=\�U:\�\'\�dV;�3���\�/G=$\�x㍵�\\����^>	\0FEt,\�}_6C�:�\�9̖���zy\�t~O\�\�1����\�\\\�\�\�4ƌN���:��r�wk\�	��-f�\�\�F\�\�\'C��\�\�;$[#\�\�\�n\�E͊p�����{m\�ҧ�:�\�G�\�\�s�N6�\�]<;\�\�1�u+\�N{w�=f��j\��\��a�\�󽬶-\��p\�Q6�~��]\n�\�\��u���^xa\�B�\�ի79\�:�c|\0����\�\�e�\�]\�K�f��ْ�\�wIy\�\���1+<��8/:�1\�\�h�Esb9f��\��+�z\\w\�\�\��\�٬s����\�\�p﾿W�\�\�S�|\�r��\�>�\��.\�p\�f�Q�.\�`Z��\�k>��\�&Wg������\����Y\�\�6�}\�wD���\�y@�=o9�}�\�,r>��ײ\�P\�SG`2jmm��X\�\�\�u�\�ŕ�7�JH�={�x\�8\�\�J�5q(tv\�q\n�����u\ZK�\�M\��\�ó�:�}\��e\�w��\r�\\���x���;\�\�\��ή@\�(�\�\�\��pW�\�;w�xvӸW�\��\0\�S�FԬ��\�S<&�K�|w\�QǢ�\�_�+��\�U�\��c�<�v\�8�\��\�\�Ԑ\���G\�y��\0}\�\�\�>�\�3y��s�7l\�0��[\�\�$ߔ�[ly\0F�\�]:<�q�a_X--�i�NvXtv��\�\�\�񌙗8;\�p\�]\�&�\�k\�\�\�Y��9�����\�a�}\�\��Ta\�=|\�\�\�M䵐�]�\�]n�\�WOia\�}\�*/\�f�c8��{��zv�B\�@_\�ԨgQעv�\��\��D@\�)~>ޓ]d0.v3\�\�=ّ0��\0�\n�\�a����\�\�\�5k��\�֧�~zm@�h�\�)\�i\�\'\�A/\�\��o�\�\�\�G�}e�\\��\�7+n�T{\�,T׼/:��rf�I緫xivNt\�\�Ƭ�k\���N0�AX\�\'�\�S;\�&{�m��f�{\�\�I\�.-~m`\�z�]��47f���ʇ\�&\�\�o\��8�d\�\�*͉\��;��\�lty�<\�O%�?&\�U��\�I��\�5S�N=0\�\�k�\�Yg�Uy\����{\�\�>\�u�\�\�\�\�B2\0\r�>�Ϫ\�\�\�Q\��\�)}�\�\�P�S�:�\�y}����u\�\�\�ow�0\�k\�9\�ݥ���ݮ\�\���\\�b�O�QrP5��\�\�\�\�T�\�;\�\�\�\�@\\�+\���5&\�M�\�\�Ǜ\�\n�z�����\�u�5\�\�\�c~�����\�^*?%Bt6��jb\\k�\'�*\n{�@�x]��w\�q�+����jժ\�\�\��y\���8f�W�XQ9�\�\�\�qvuk\��a�\�۸�M~ht\�|F\�2�9fc\�1:���s�R\�0��J_\'�<c��nEGrKW��\�\�jG���׸�X\�!\�0\n�Yv�5��Y\��Ϫ�]ޖ�Iv(\0��	\�B2��1cƟ�����B_Clq��\�ӧO\�\�V\0���DH=�g\�~S��\�\�Ÿ84�\�B�psz\�I�zZZ\�\�V\0;qNr�\�-�\0\0\0�\�;l\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0&���\�\�K3��e\0\0\0\0IEND�B`�',1),('5',1,'D:\\apache-tomcat-8.0.36\\webapps\\Spring-activiti\\WEB-INF\\classes\\process\\purchase.purchase.png','1','�PNG\r\n\Z\n\0\0\0\rIHDR\0\06\0\0\�\0\0\0��9\�\0\0�\0IDATx\�\�	�Tՙ��[�1�\�7q\�8&�\�q��\Zu\�\�$:j4\Z\�44�\"�4(\"\"�\�\r��\"*��tU-��\�Ⱦom�	���\��\�}\�[\�U�/�<\�\�w~u\�\�soU׭�}\�\�\���\r\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0��w�v\�\��ۿ�\�	視\�h4\Z�FK�����\�K.�d���\�\0\0\\jx��\�;\�\�ѣG\r\0\0\0@:s\�\�	�b\�\ns\��~s\�9\�Cn\0\0\0$0=�\�FI\r\0\0\0\0(\�\�~��=\�-S\�\Z\0\0��(R\0\0\0\0J�jժC\�-\�z\�\Z\0\0\�O\0\0\0\0⠴�\�u7\0\0\0 Q\�\0\0\0\0\��\0\0�bbC�4k\�,b\�̙3\�y\�W\�>˖-����|��qs\�ԩR�t�6mڔj3f̰�<x0\�w\�޽f׮]��q\�殻\��>�\�K/�d��z�j�?�\�dee�_\�?�\�?�{\0\0\0\�\0\0@��\r\�\�3f̘��S�N5��O��N\����\�6\�#��\�[o\�e�N\"\�ϑ#Glȧ�ʨ\�O�\�R\"C\�O�n��9t\�P+X�<a\��]ǓH�?�9�\�͝w\�~\�:�\��\�\�a\0\0\0�\�\0\0\0HA�1i\�$ۢ�$������\�G�	\�ر�QR#Zl,Y�$\�k��\�\�\�~��\�\�G����\�6I�\�ݻ�ٲeK\��zݟ��\��=\��V�\�\0\0\0\0\0\0�(6\\\�RJ\Z6lhSR�\���\�O�s\�=\�0�\�ر\����P�q\�\�\�\�ѣGG��)S��)7\�v	�\"/\�ƍ\�6��(\nC\"\�k�\�\�f�\�\����Ŷm\�\��U��\�Oڨ��\�:\�t\�\�5\"\�C�\0\0\0\0�\0\0�bbCRBR\���\�׮]\�+E�4�>.#\'\'\'�*�Ȍ[n�\�\�{ｦE��x������\�sE���\��ɐ\��\��W�`��\��\�<�\�6rCi0~)\�1��E\�+J���\�С�}l@1U\0\0\0@l\0\0\0��\�P\�\�ܹsm�M5��E<\��i�h�\����cǎ��\�[�HI���\'K�o4$7\��\���\�\�{\���\�؂�~\\�\�\����p��\�}��\�\'?���!�\0\0\0b\0\0\0�Xl����\�\�\��C���$�D���\�\'E\Z�\�ܨ�\�P���.tI\�f\"�q\�m�Y�r\�uו\'��\��l�;\�뺢���\�/m�fU\0\0\0@l\0\0\0��\�pD���$���p50%\�n\�rUĆ��\�mײ\�~\��ņ�\�Du3��`D�P���gРA\�4\0\0\0\0\�\0\0@\n�\rEF\\y啥\�+ZB\�A�qQ�������|x\�\�\r�j`\�Bu9Դ=\�1Jq���m#-\\\��Ć��U��������J3q�\�?�PEs8\�\0\0\0�\�\0\0\0HQ��ZJ���7��+\�X�z���P�d��!\���1\�L*��$:�\".�$J5Q=͂\�\":���\��n��f+0tl�Eu\���vY�E�C\0\0\0\0\0\0\0)(6\��\�&E8\0\0\0�\0\0\0I!6\0\0\0\0\0\0\0�\�\0\0\0\0@l\0\0\0\0b\0\0\0\0�\0\0�\�\0\0\0\0@l 6\0\0\0\0\0\0\0�\r\0\0\0@l\0\0\0\0 6\0\0\0\0�\0\0\0�\�\0\0\0@l\0\0\0\0 6\0\0\0\0�\0\0\0�\�\0\0\0\0\�\0\0\0\0b\0\0\0�\0\0\0\0�\r\0\0\0\�\0\0\0\0b\0\0\0\0\0\0\0�\r\0\0\0@l\0\0\0\0 6\0\0\0\0\0\0\0�\r\0\0\0@l\0\0\0\0 6\0\0\0\0�\0\0\0�\�\0\0\0@l\0\0\0\0\0b\0\0\0�\0\0\0�\�\0\0\0\0\�\0\0\0\0b\0\0\0\0\0\0\0�\r\0\0\0\�\0\0\0\0b\0\0\0\0\0\0\0�\r\0\0\0@l\0\0\0\0 6\0\0\0\0\0\0\0�\�\0\0\0@l\0\0\0\0 6\0\0\0\0�\0\0\0�\�\0\0\0\0\�\0\0\0\0b\0\0\0�\0\0\0�\�\0\0\0\0\�\0\0\0\0b\0\0\0\0\0\0\0�\r\0\0\0\�\0\0\0\0 6\0\0\0\0\0\0\0�\r\0\0\0@l\0\0\0\0 6\0\0\0\0�\0\0\0�\�\0\0\0@l\0\0\0\0 6\0\0\0\0�\0\0\0�\�\0\0\0\0\�\0\0\0\0b\0\0\0�\0\0\0\0�\r\0\0\0\�\0\0\0\0b\0\0\0\0\0\0\0�\r\0\0\0@l\0\0\0\0 6\0\0\0\0\0\0\0�\r\0\0\0@l\0\0\0\0 6\0\0\0\0�\0\0\0�\�\0\0\0\0\�V\0\0\0\0\0\0\0�\r\0\0\0\0\�\0\0\0 6\0\0\0\0\0\0\0�\�\0\0��\�ĉSq��:u�\�ǫ\�>\0�\r\0\0\0\�\0\0@�yu\�\n�V`u\�\�G��0/��\�.O��\�>\�f^yo�\�_�Ō�-տ\�#&o\�\�Ǜ�t�\�WǙ�I��^��\�,^�y�s\����L@l\0\0\0 6\0\0 ��\�]VZL��>��p�x�\�J3x\�\"�y�f�\�]\�-�\�\�\�cS�C�<�m\�S�8�mk\����\�m�\�E��K�\�ˮ\� 6\0\0\0\0\0�&,\\]hFN[a��:�\�\�f\���\�\�\�Y�1�VB��)RC�;��4\��\�\�5I�S:i�Y����sEo(�#�I\�\�\�\�[K�\�{\�\�\r\�EvH�8\�	\0\�\0\0\0b\0\0R���>}eD\�È���>#瘿�>\�\�\���\�7x��VZ佳\�?~\�|s�\�W\�B�c\�o�|\�7�����l	�\�ӣ\�S[���\�6�RK��8;\��MZj�;q\�g\�\\��\�\0\0\0@l@��m\�63o\�<̌3h	\����\�\�ٳ\���\���B�!� �\�$��0�<r<\�O\���\�^d#8$C���}\�́\�Gm��DEy<3���\�ޙ�֊�X�1\'\�y�1�9��\�\0\0\0@l@:H���STTd�?NK��g\�3s\�L�n\�:��P\'HJ���jO��^�UZ��7�!\� �EL(\"#V�Q�Q��\n��G,1\�PZ�$J,\��\�P��j\0 6\0\0\0�\�(R��\�rC�4\0u�jf\�Y�\�\�\�P\�P�r��$�]\�B\�T4T��\�\Z\�PRgC�\�}�=\�ܕ;̸\�\Z���?�^VQ �P\Zʔ�#\�\�?bSb�E�^�\"r����XCl\0\0\0 6 E\�\�<!��n�꒞\�?��@BC�ᨯ6��t��\��)EER\�I	��\"9Tԏ�=T�T���>\�\��kJ�w�¡j�V�R\\$W�bFQ\'O��q8���5\"\�\0\0\0�\rHa4h\�&\Z��(7�Ā\�=\��x�mZ�\�Fܴ��5E2C�%��U��\"74�I�\�\Z*0\�\n�:$)�� B���f])X��\�z�WM;K\�\Zk�\r\0\0\0\� 6\�\�\�/v�MG�U�O٦e�\�\��@\�\Z-��\r\0\0\0\���\�8|`�Y9��Y6�KD\�:m\�\��@\�\Z\r�\0\0\0�\rH\��\�\�+%5\\۱j\n7߈�Zg˖-��k\\�\0\0\0�\r��\�د=Wlh7߈�ڤo߾v\0�G@�\���\0\0�\�\0���\��\�\rm\�\��Q[<�\�3\�\�N3W^y�}|�\�\'��E�\���\0\0�\�H_8`&N�h`z�\�iڵkg�4ib��\�֦MӵkW�׿�ռ�\�fӦM\�\�#6�,5�\�\� ��\�O�W�==)���5�Q�\r\0\0\0@l\�	��eeeY��\�\�\�˗\�i�N�<\�o\�>�f\�\Z�\�����M��nݺ�w\�y\�\�`�덽r\�\�\�\�k7߈�ږ\Z孯(��\�M1�)I5M�\�+\�-3ç,�Ӵ��W\�*��\�~�,B\�\"v\0\0\0�ب;6n\�ho\�\��~3~�x+.*\�\�_m\�̙ce\�C=d&M���7�\�\��{c�m\�|#6j:M�,y\�W%��p��»KÑ\�>\�l�Kd>\Z)/���\���\�^d��\�\�	db��\�\0\0\0\0\�F\��\�[o�V�Z�Q�F�#G�T�x.4�[���*JiI��};����*}S\�\�6n�5-5ʓ\����p�Y�u��h�3>�\�\�(�\�<=j^�~sW\�Gv�1c�ɛ�\�\�\�\rdkD�!6\0\0\0\0�Qk<���6\�\�\�O?�\�\�:t\�:\�\�w\�}f\�֭isc��y\�\�R7�ZǍ7b��#\n*:��l�\�k?7�N]a\��x��Ɛ\�x}�J�^�b\���RP\�\n��M�C&.�\�\�\�3=YD\�\Z\r�\0\0\0��\Z�\Z\�۷��3jU�\r\�\�H�q\�Y?��\�7�\�:m\�\��Q[i5-7u!Y\�\�i(�\�EfL��9\�oR�:�l\���\�\�\�ԩSf\�%�Y�^b��5\�\0\0\0 6\�<�����c�\�ɦY�fI�Qލ�\�;ͺ9yq�c�m\�\�\r8b���FU���n�\�N�^�\�\r=�\�}�m\�Z\�;�KI�C��\� b\r�\0\0\0��:C�BUSc\�ҥu��cǎ5>�`RϘ�\��\�1�{}Ь���R\�5�Q_�7UM��n�CE��\�O6l\�o#2\��7��e\�M\�\�\�FJ�	\�J��\�O\�4\�dQ\Z�\r\"\�\0\0\0�ب+�x\�	[(��\�\�\�6�\rJ���4�\�@lԤԨ�Ȁ�\�W\�Z�m\�g�MZj>�l�MI��8p��*&��\�Q3\��\�f\�\�6mE\�êߜ;\�v͜�T�E\�!6�XCl\0\0\0\0b�\���\�픮51�Ieٹs�iҤ�ٵkW\�\�\�W$J��\�\rn\��MwPڇ~�+[\�\"z�xi�\�\���̐\�<�S�\�\�\�ٴ��o-�۷�}���}\�F[DT�\��$f�J�ʢ�D�!6\0\0\0\0�Q7�\'N��\�8p`\�\��Ǻ���\�p��q\�Fe\��\\r�d豢\� \�~\�V\0��dQ��\r\"\�\0\0\0�بs8`���̾}�\�\�=(j#333)kmT�x\r�Q[\0\ZD�0*,7�Rõ6mڤU\Z���5��\r\0\0\0\�F��H��={\���\�l,}�b��\�(\'M\"Z6Ēe\r\�+\�?]\n`\"��X\���\�\0\0\0@l$90S�L���1lذ��\�\���\"���ب�\�U<�\�?���� \�i�w\�^db��\�\0\0\0�t�\�X�|y���\�\�f���\�/�i \Z��\�ƾN�WU�\�\r�+;X���駟���q�\��\�ѣ҂Y�\�\���\�\0\0\0@l�\�ڵ3EEE��>\nM\�ƍn\0\�Z\�֭c\�\�sc�ب�\�UU�\�o�}\�ĉ*I�dH�ЬJ\��|�\�m\� db��\�\0\0\0@l�\Z�<y2an�u\0\�\��D��Gl\�\���:i�\�\��\�w�,5=�B���?ϗ^z)n1bdb�\�b\0\0\0��b$�LH���F_�\�\�\��Q߫\�zc\rƫ#59�\"\�g��\�i��E�]��\r\0\0\0\��:\�K\�V_7�s\�̱`���\�g�\�m\�Խ��\�oϞ=v\�\�o�^�cǎr_O�\�\�\�\�\�\�G\r/�(�[V\r����O�\�\�=�\�c?�+���Z�O�(�|\�\�W��\����	��\�z\"�дiSӼysӢEs\�}�\��fi۶�y衇L�Lvv�y\�GL�.]L�\�\�\�O<az�\�e�I���\�s\�=g\r\Zd�b#0�nF�m\�|�M3a\�3y�d����y6k\�\�\�,B\�\"6\0\0\0��%Qjlh��\�56\\\�`mܸq\�0����\���\�W_5��˯��Zx�jh���\Zk��\�?\�\n\�4�\�:\�\�\�7k\�,�]�B=^x\�\�\�k�\r療��J��5�t\�cǎ-�L\�FžW\��L�A�\�\���m\�Nɱ�7�\�>�\�,Y�Ċ,�5\r~%\�wF�ArB\�]\�\nI�����Z��\Z�{���C�\�\���C\�Ӈ~\�J\�\r�%K$MRU!vi�\r\0\0\0@lT\r V�ZU\�\�C��-[&\�\04z\�Yߡ\���.^�\��\�\�\�.�_�\�\�|�\�v\09~�x�\�\�/6k֬	\�3o\�<;���\�K\��\�7iҤ�\��ˈ\�~��vj`����βO-k@�\�\�>7\�t��Ѳ�\�X�ygѢE��\n~�H���,���6mZ���,���\�\�͛#D�>s\'ע���\�~\�\��暮G�\��:�\���kh2F�!6\0\0\0i������bذav\�\�D��W(��\��a}�\r��Zˊx�oS��߾}�]w�\�Q�\\R��k�\'^\�ߵk�m.\ZC˺ѿ\��b�\'\�\�y\�g�K�e�\�^�z�\�(�{U\�Ao��\��3z�ݭ[7���I9�\�(Į>c�	�j\�ڥmN�*eO\�F�O�F�\��t-3f����\��}�\�âW��r�X�$Y#\�\0\0\0���@7n��}�P�D,�����Hb�s\�\�vy\�֭v��C��\�K���s���C�ڛq�\0(�\�\�g�\r�\�\�\�\r�Kw\�wE\���\�\�g���w�\�p����+� �߫�~kkjR}ujR}\�]�	\r\rVk:B\"�gEI��\�R\�~��_Yy�k�/�\�RQ.Z#:�B\�w7\�pC\�k��疗.]\ZN\�\�uU\��^%k$b\0\0\0��\�M�-\r|\�\�\�B;0�\��PU\�Sl过�U\�n�\�z\'�E�v��l����^\�^˪o\�f�\��\�\�\\���h��\�+\�O�TE}\�\�\�D�[�+Mg�QU\�WTZTVn8	 i��h\�٧O[\�6\"%\�Q%�\�U���A��s\�=7\�:$�\�\�\�e�]fS�^����()�\�Xb\�	\\Eq�#6�\�i\�Ɇ\�\0\0\0@l�\r�?���9sf����/�h:uꔔ�]}�\rEA�B!�\�d�n�\�\r�n��\�\�	uC~\����+W����\��\�\�\��\�	�_MW�\n4�{}��_��XQ!��\�\�+;��h�D�\ZȢ���UTlHr����It�\��\�?�\����.\�Di(������9y\�!\�Ɇ\�\0\0\0@l�\r\Z���Y}L��A���\�M(7���<����W��{/\�b#\�~J��W(Կ\�$�Gl���?�\�?\�&���%�2z�TAlTo0��~�g���㚎5\�$�E\�\'6\\T�K�q\�$W�\�5E�5(�9$)��/��6u\�\�p\�^�T\';TGC˒\�\���)�\r�\0\0�\�H+4��n\�\�\ZM�)����;\�\�\�wVED]\�\�7�JEqM34�8	R^(��\�?�\����\�M�\"3\Z�\n�6(�k �بz���\�TW�\�C��q�dQ�^�4S���\�g�qF�\0�$��\�\\?w\�Q\���(5�k׮6Z�AI\�Ɏ詭]t��a.�\�\�^wmK\�H6\�\0\0\0b#�P\�\�֭[ۛ��bɒ%&++�N_3Ćn���暈\�n�\0\�\�\�;|��*���2�\�)��A\�Le�\r7Ma��m\r|a\�~	�m��\�\�\�m�\�1Al\�\\��\�\ZDWvV�\���;%*�(�dQm_�t]\��㪫������Pj��\�Z\"\�/Yݲj�EE�\�]��\�\�;\�\�zz-���\r	_	�d�dCl\0\0\0 6\��Gk���HI\�Δ��Bn\��\�W}\�F\�\�(j2r!�@%\��ϥ�H�s\�9�>P�0��p�a�Z\�l)�؈�ۉ\r-K��)�cE�EGY\��\�Ɇ\�\0\0\0@l��	R�\n�\�&EEE��\\�\�߸��!6@nԴ$A��,��\�\n��k\�\�\�R�%:�\�pˊ�д\���Уڍ7\��,�K�TT���ky\\��e\�+J�H6\�\0\0\0b#-Q$�Bp\'N�XkǗ\�H�}\�b#\�\�(\�z\�F�ʢt��%C$b\0\0\0������\�\�ȑ#k�������T�\�\�\����r���\�t+�,\��\���\0\0�\�H�ȍ|\�Ζ�<\�\�\���D�B���7�\�اJ�\�\'\�\"�\\�\0\0\0���`РA6\�BbbӦM�\�Wy\�/���\�_\�ђy�n칱O%��::꧂��,\��\���\0\0�\�Hy$$��\�c\Z7nl�84sʬY�̶m\�\"�\�۷\�V�6l���.33\�t\�\�ɦ�pcOCl$F\Z�+h�\�@q�\���\�\0\0\0@l�%�\ZO��u\�\�6C\�\�]��P%�=z�iӦ\�n\�i��\���~�,\��\���\0\0�\�\0\�ƞ���H\rd\�?��\r\0\0\0\�\07�\�\�\'\�`�\�+�Dj ���q�Cl\0\0\0 6\0��\�\�>9\�(�*Ɣ�\�\"�\\�\0\0\0�\r\0n칱OJ$6\0Y\���\�b\0\0\0���=7��\r\�3\��\���\0\0�\�\0n\�i�\r\�|�\\�h�\r\0\0\0@l@��\�o�yFl0\�3El\�\0\0\0�؀�����q�����\�\�\'\�\��L�\r\�\0\0\0b 7n43g\�4��97\�	*5֭[\� �A8�)k�\r\�\0\0\0b�,�\�\r$-q\ZR�A8�)k\�\�ڋXCl\0\0\0 6\0\0��&�\�%b-}#\�\0\0\0�\r\0\0\�|�)!7R%bM甈5\�\0\0\0b\0�A8�)pN\�\0\�\0\0\0b\0��)pN\0\0\0�\�\0\0`�\�g\n�S\�\0\0\0 6\0��rN�\0\0�\�\0\0`�\�g\n�S\�\0\0\0 6\0\0��\��\0\0\0�\r\0`�ƀ�\�8��\r\0\0\0\�\0\06\�3\�b\0\0\0�\0���\�8��\r\0\0\0@l\0\00`\�3\�)b\0\0\0\0��\r�L9��S\�\�����6m\Z?��S�@�̘1��\0-??\�̞={�ײ�� 6\0\0��\��I���STTd�?NK��g\�3k֬����\�\n�\�\0\0`�\�g\n�S\�@��\�Hh�q|ƌ��� 6\0\0��\���� �͘1\�\�T\0\�\0\0�؜8q*\�S�N�\����\�9 ^l���\�\0\0`��W��0oV\�\�~��	�»K\�����M\�\�m\������[\��\�ژ���q\�\�\�^�\�\�\�L���x/N^f���\�?C�6�\�ރG�r�{�\�\0�m��\��ͦ�#ͪ��lӲ\�!\0�\�\0\0l��\'�\�\�b\�\��1ŀ��\�\�\�W��\��ͻ�0�W\�2�m\�g=�Ǐ�0�\�Z\�k/۰\'�ڶ��P����6\���\���\�\�e�\�m�\�\r�=Al\0Ԣ\�8|`�Y9��Y6�KD\�:mC> 6\0\0�\0\�ꒅ�\�\�i+\�\�M���lؾ?�}{\�!+1��\�J�u\�6Ej\�qGїf��D\��W\�Ŝ;\�{o�\�\�bR�:3s�\�R\��^�s��\�B�!\�\�`a\�\�	b�u 6��z��\�pmǪ)\�\�F]�|�\�\0\0`�&�\n����Q#\�_n���c���<�k�\"/	��\�b�1�ͅvߥ\�?���<3�ɡH���\�\�wf~��u-\�~��G\���(%E\�]=��tqv\��>l\�R�]rE�\�ȹ�q\���b�{�\�\0�m��:�L\\��m\�\�F-sk\�\�\�V~c�\0�\�6		\'�\�\�\��\�\�~���\���\"�!�\�\�H�B���c\�ۨ�XH�\�CBD�#�G̉x�\�`\�\�	b�u 6V\�\�Wlh��Q�\�\�^T�x���\�\0\0H\����+ک�\�9\�eQ��(zC\Z\r ��P�\�Ԕ�e\�\��)�ác*e%~1�\�$J,\�\��(�∭�� �\�\�b�R�\�\����t߾}�\�\�sO�v\�nҤ��⩵i\�\�\�c�=v੧�Z:lذ��x\����\�@lԻԸ\�����\r\0����f�jaHJ�p�R:�L\\\�.q�u*\Z*�OYn�n(���GWCc��.�P�_�Ī��\�e��L��1b]\��#\�ĉ��0\����ب����C�^��dee}ӳgO�\�\�\�˗���\"s�\�\���o\�>�f\�\Z�\���\Z�\�Ƕ{���馛\�LW��YP\�\rmC> 6\�@j��\0�\0�F��\�?��@�CC�AS�:T�s\�%V��RT$5���h����\�c\�B�1\�\�_c��\�\�U;^�~>j\�x\��L\�}\�L�v]̓}F���\�o͛7\�\�\�	b�%;;��ݻw_z���3~�x+.*\�\�_m\�̙c�x\�oڵkWغu\�?��\�X?gH\\��m\�\�F\rsk9�\�m�\� 6\0\0\�h��\�	���)E\���DM\�4ۈ�6U��Hf���\�?DC)*~᡺J)I�\�\�.��(e��W��.�g϶�a�G���mc��q\�\��E\���\��\�ٳ}�V�N�\Z5\�9r�\��M\�֭�n۶\�\r6�A:��};����*��\�\�6\�b��Fy҂\�\r@l\0\00`KJ�1EL��֢E{��� 6�A�n\�^z衇N~�\�5��:t\�:�k�aÆ?N���y\�\�RbC\���\Z\�JFbT�?\0b\0�[�\�6b�7\�|��\�\�K��\�۟R��\�b\�ر����6l\��i!6�3\�\�P:\r\�[�m\�\�F\rJ�;��\�a\� 6\0\0�%����\�\Z�� 6���(R�6��c�\�\�ǲ��v&{\�Fyb\����fݜ��56�M}��z�\Z5�?\0b\0�[�+j�\�\Z�� 6�\nUM��K�\�\�	x\�7v5n\���d�1%�\�8v\�\�^4+>|<�\�pM}ԗ\�\r\�F(�Ph]\0�\0���Nh۶-\�\Z��\0b#�nݺ�P�к�cǎ�222ڧ�\�(/J�\�\r\�F\rK��J#!r\0\0ؒ�@ \'N\�IRb�1�/b�J�\�\�\��ҵ&f?�,;w\�<ٸq\�����?L�Q�(���7���JH\'5N�Z�\�\��E�\0�\�H�A1\�\Z\�KY\�`I3BlT�^�z-�O\�9`��U����SElTUj���@lT2�BRbD\�mD%\�F����\�\0\0@l$ՠ���ElT�޽{�CVV\�7����s\�΢\�\�̽\�Xk��ӽ\���.Ҽ\�bf*!7�Rõf�\�\�܀Z�I�&?\�\�\�xE��\r\0He9\�~۶mf޼y6�C�J��o���VԬ_�>mS�\"6�\�/�пgϞ�~2|��������ؠ!6\�O6ĒeɍX��\�\�O\�\r������ܬ��\�z�\�\0�t�\�g���FAA�\�L�\�+&V۳g��9s�Y�n\"�����\�зo\�ES�L���1x�\�9���⽗-�\r\�Fm|�ʐ\Z�VBVĒ�\Z\rHK�ڢE�ߋ�q�\�ٸq\�+\0�j$b��\"5�\Z�-7I���b��\�\�s\��\�\�\��d̟?��\�\r%\�\�/��\�!6u����\\(OnTVj�_�P�\�-��\�n\0R6j��B\�5h\�1�\�_\���ElT�v\�\�}-c[\�\���������X�iGl 6j\�{U�t�xr\��*J��\�`\0jTl�7J\�w+\0��$Z�=����D�)|U�I�&\�\�ɓ	qB�N��&��QK����\�(Kn���Ԩ\��߹iӦ\�YJ\�hڴ\�\�\r6<\�k��\�\�\�\�ʺ@\�47n|�\�~\�\r\�/�ڿy\�r�hx\�\�^�/\���\�^�\�k7y\���\���k�\�}\����\�k���[F�5�ZK\�yk���\�C^{\�[\�\�k]�\�\�{��\�zy\�)��\�[��\�zm��<\�kü��\�Fx\�5��\�֛^\�-O�ڻ^{_\"\�ky\�^�\�-\��^[\�\�\��{\�5\�\Z\���\n��o���\0��Q�b_\��\�/�\�m6-iV\�?e�����K\�F]�@�5�h%\n\� 6����\�@l\�\���!KnTGj�J��\�gx\�;w\�y\�\���<\����7��\�~\���쵋�\�?�\�/���\���گ��Wz?\�5�95jt��\�^�\�k��ڭ\��;�v�\�\��7\�,&\�ۯ�\�Zy���\�Ao]�e{\�o]W�=\��>^{Z5{�\�\�y\�y�\�xm��ߋ��\�\����|�\�\��\�[޺w�6\�k\�ym��n�\��\��\��\�\'^�\�kJ�[\�ϼ�\�k��\�k;u\�\"�\��\�A�}\�c%5$�)i_{\�hɶ/���d�]^\�^r,sm\�k��\�\�\�qQ\�{�\�\�%\�-\�\���޳޻�|�\�\�\�g�~\�1^��U?sI�\�!�,�u�ڳ%�Q_��.�\�{\�1}��l��\�\�\�g\���u\�u.�u2\'\ru�t��m�\�\�\�z\�����[o��^�Z\�I�&�\�}.\�wA߉2~>\�q��\�w�\0�B\"�\�W\������f\���f\�\�.M봍{\�귣G�!6\��{�b#�#6��Ն\�8|��Y�h��4i�y�嗭�ԗu�\�\�\�>;v\�//]�\�>9r\��3fL\�\�.^�\�nW?M\�\�\�g�}f�H���6o\�����f͚5q�\�urrr\�<��<�\�\�e�u\�,��\�3پS���\\�U�\����\ZkK\�W�A%\���׎{\�7�=\�\rFx\�{�V赝^\�\�M�\�|\�\�Wzm��{\�^�W�_��^\��%���R�h���6VQ*.\�\�%\�ü\�\�UT���\��\�OQ\��\'�\�C\��J�\�uR������\�\�/�bh\�Ɗn�\�\�뵻��\�o�\�\r��E\�x\�zEKx\��\�*�B\�޺_*�BQ\� �B��X\�\���k\�\�+*C\�%SY�\�\0��ow\�w_R�E�i��[�� �B\�+r��}\�{���k;VMALxmˮ�f\�_\�\�?7�6\�6c�&���\�{\�\��2\�\�-��ہ\�1\�۷��o\�# 6��\�ƞ={v%x�\r\�\�?\�\�滦Ć\n�N�:\�dee\��E]d6n\�h�=j%���+W����\�~��\��\�DȄ	̏~��p́-�w\�n�\�̙c+.Klh}�F�\��v\�\�e\�\��\�_oe�\��H�\����\�7\�ǏK\�3k\�,3y�dӬY3�x\��k��\�.�\��\�g?+�~�^;vl�\�4�؈�^5(�\�Dl�����d����^���\�}ze�)���3\�\\ll.�\�\�VdHÆ\r�+w+\0�2$J�}E\�_W���+6�\r�q\�J�\�oG^,]_h�Kd>�UD\���ե��4k�ٳ���ꫣ���y\�s\�i��+��\�>>�\�\\8�G�{W�ZU\�\'�dV���F<k�Ɔ���/{�^�\�o~S��d��\r�����\�o\�=�\�W#�5��)\�?�|s\�\r7\�}?:�[Vd�_{\�5�u\�V�,	KF|��\�5??묳\�RE\�ƽ?��o���]V\�;�D�dM\Z���\�+�D8\\K56�\"7\�\�F�\�i\�)7�[�<;\'0X�޺>��w\�	�_vn��DE�\�\�8\',J�۴\�\��_\�\�\�oT�k\�\�A�?�o8\'ty�\�f�}�k�s���cn�߷?\�\��#~�\�	��>\�#Cw\�	5�\�ϣ\����S^�]����&��κ(E\�F�\�]\�RNW\�V\0 )\�=\�ȑ#�w�mZ�je\Z7nq�\�\�S�Nfذa66�\�\�\��\�\rmCl|\�f.\�bv}aVl�\�\�_�\�\�=�3}e�~\�\�	Gv,\�fޟ�\�\�\�\r\�ƷR\�\�El�~�\�\�\r\�F\��0��M�V\�\'\�\�+o���\�S�z��g��\rEd\�K�}�v;ȗ�в\�E��h�\"<$.\\Ć$�ޏRY\\I�\'.�\�2+?�]\�Z\�\�\�g�m_+�\�\��\\��?b\�.�=x�\'�#�\�1��?\����\"6\�;\�<١e��\�)*6\��^U#B\"ޔ��fE��ܨ\�YQ��NH8 �\�ih\���w\���\')DǼP�\�\�\�k�q�\�v�C�D��SN��קgvn \�{�pY\�Sn\���\��\�qqkvnp��ؾy��\Z���.��Sn�\�\�\�q\��~��\���|>U��T໏\�\0��\�\��1ן8q\�6\�;�\�iT�y�Vt��\�K��\�8|�r�\�o�w\�7�O��O\�\�\����l�\�%*B��GPo\�k\�,\�f�\"3�\�\�P�Oǵ�c����؈�\Z\�\�F)�:�_�~�~2ڷo����h\0Z\�\�kEl*ES��\"#\\�?J\�_�\r	WcC\���Ѳ�+1�\���\�8�\�s\��\�?>�Ĉ�91\�RM�>ݡe��\"A\�\�_�4(�\�D���e�O��Xb\�K.	����\�+��\"USQ*���b�D<�qZ\�+\"7��ڞ�<\�\�9�_g\�;K.t\�4TĆ��?\�\",\0r��$ b�\�\r\r��\���\"(��m4Dp��\�\"\":\�\�w\�ed\�zIj\��Ia�/�\"\�ua\�mpX�y���\�.\��TSt�헛c�d	4�\��++:r�\�$_�\0�~^�+�%�\�i�g��\0�&\�^ov}i\�[�\�\��\�\�o6\�CkK��\�G\��N�MfVsӤyk�ժ}��h\��aӸ\�}\�ּ]׈\�fm:\�~\�\�d�V��2}�2�O�E�!�\��\��\�\'L\�;�\�����jDlh�xbCۈ\�8n�.$+\\=\r���Ȍ\�\�\���\�i�\�>`�\�W�cǎ�i�6\�G\�FqdFY�\�m�\�b�L�t\��\�\�͛�Aq}QXXX����eB���Jq53$\��SQZ�(	5I\n4�䇖U/C����BbC\�.UE�$%t�%\\S�;v�T\';�\�e�:���9\\�\�}�\�g��q\�]w}�G����_:��^]�I*��jP^\�DyR�A\�K����\"5nP*��K4H<(��\�\�൱SWl\�G�Cz<,�Q:\�$�J��^�\�U��$�\�Q\�c^\�\�N9�\�UoCM�\�5[C\�zsC]\�\�%���:�\"7t	\�u��s�#4\��\�{\�7\�{\Z���\�Al\0\0\�\rC\'-1{5\��I\�\�\�%9����o\�\��Ӧ\�`��\�ض\�]�f�?d��\�o\�o���{�n�,���3!�Ҭ[�\�U��M�Z�ѲCO+;�ܺuk����\�H�g\�~�\�&-5o\��\�[\�ӣ\�\�u���\�X?gH\\��m��bI�\�\�?\\i\�K��\�wg�5�~\�7\�\���$�\�1\�kť���\nѵk\�\r3gά�1dȐE��0?���\�PJ�Kː8\�s\�ֿP\�\r��\�\�X�\��\�/�tH���J\'Qj�w\�m�%53$;T�4�5�����$q$�D�_N8)���\�\�\�\�\� �`�_�7����r#7**5*+7\�\�\�]�?�\�J:ɿD\��o���N��\�+�\"V�Fq]�\�z�)\"�7J�h\'����4�\���\��R\���(\�\����s�\���;\��\\\�E���=��Mt����T��\�=*���G��� 6\0\0\�I5I\�EB(Z�˰�f�\�\�͆\��\�S�ڛu7�M���:�1<��y鵷M�\��\'N�2ώ]`v\�9�^�G\���\�~z�=V�\���Dt�2/���Y�u�yq�2\�WbE�#z_z\�k̈���i��\�3r��!\��v.7+?\�U:\r\�[�m��\�v��\�{ڈ=\'�Ƭ\�RdF�\"��ji�:j\�P$?\�]l8YQ\�H�\��Gl�!ݺu\�|�\�GO\�Ǵ�\�E�D\�ƍ�7i\�\�\'\�.6\\*�f,\�\"��D\�Z\�\�fD��o(r�	M��\�nf����~Q+:B\�ն;\�Ӧ\�(�\�Eb(bC�/y�~T�#:b\�?��\�\�\�.�Ɔ޳�y]qR�L0��p\�FtZH�JH��\�F�JD�\�]\�FI�В\�G���\�77\�D�����`EvDGs(R\�EHt\�\r��4� Q}�\�C�I�\���(DQ�\�k��\�(.:�R]t|DF�\��\�JR^nuEm߼P+ɚXi7�\r\0��G\�@��N]a^yo�9u\�]?f�*!$5Z�li�Z\�t\"	�؀�\��z���{쉳֙��k\�ɓ���n+:�|�`�Q��V{�\�m��e�\'\�믿n���?��Rd\n�m/�\�\��\�\�E�K�\r�Kw��q\�>[+c\�\�_�\�m4�\�))���C\�D:b�6�՛���jzX�[�u�ݮ�S4�J:���F` 7\�Ү]�B\rT\�\Z\�K�\��c����[M�\rW$T\�\rz���\����*Iq3�8�\�vu\�(�J;q�\'z*<�eM!��\r�\���\r-\�ر#�^4\�z,ᏲP��fT�n.\�\�Ei����Y[\�,.�>�(b#~Zʭ1$EE�F�r���)]kj�h\'-\\\�\nW@42\Z#�[\�\����\�}\���͗̈Lo	�\nO\�Z�����\�\�nSQ\�\�\�t\�,���z*~�(�\�\��\�+\�EI����E\�L+4�-���K�\�o-K� 6\0\0jE>|�`�y\�ݥf�Evݲ\r{\�;3׆\�A4\�I�\�hi��\�b^|}�Y��\�8b\��\�33�\�Ufњ\�N_�\���\�\�\�\'m�\�_}\�\��\�ō�rӥK{�\�{�&\�Z��/N�ۼ��ƌ\�\�: j/\�\��q\�Y?��\�i(޺\�eԆH�i^]j�d�D\�\�uf\�Uv6�	3\�\�\�E�۾\�u�yߏݶ��\";\������b��i%�� 6ʤM�6�oݺ�ײ\�u�7x]�����aÆ?Hg����\�M���\�ۚ�(�%57��iZ�#,����[�Ϫr\�UW�k��֊E^\�Q\�\�o�8��͡B��\�v�\�?\�ح\�믿>,bb�\rEy\�������`�_�h\�\�~�;���\�I���Xi)��D\\Tv\n\�\��n�	�+zA\�5�jPĒ\Z\���\0�.?�4s�W����\�nJUo�C\�\�\�\�*.\Z=����\�\�G,\�g�r�D@�f&	O�:8�\�\�\�)U�}�\�>�<���4ߘ��N�}�\�ʋ6/M�;��1�̈́b��unJZ����Q$���\�P\���mu����MT�}Vt�\��@l\0\0\�>�Ґ\�PʉjXH\Z(\ZB�6���\�kg\�Pޝ>׼[�\�\�]�Ӭݶ߼?Wu1���őc\'\�\'�\�\"CL��m͎-�Z�!�<����J)/��Y��s�\��Q�\���\��\�t2K�,�}\��x��\�ʈ��~�\�W\�\�+\������fݜ��56�M}HG�\�4�\�\n�\�\� 6R��m۾3x�\�u���k׮#JAiԨQRë\�\Z�\�j�>56*%7j#ͥ\�(\�C�xE<ؖ|GB�#$$/lBq\r��\�OsC\�\�1��yѻT\\g�54�\�\'7�Jq�\Z�Ό����\�h\�\�O]�O��>�)U\�TU�\�Rc�2\�|\���4C����5<\�{�-����]\�<!�-m�uw?��\�K�\�6�٨�\�`��h<���\�l(nJWI��\�\�N\�\�p5<\�T��6�#/�\�x\�\�\"6\0\0j\�T���4v\��2<\�Ȕ�\�\�w�#f;Q��\",�\�\�\�_\���T=\r��L��>\��$>\�g��ڶi\�V��-�\��W\�\��0�\n��6�\"3$YVl\�cFN[Q���}Ջ\�8v\�\�^4+>|<�\�pM}\�7ݣ7��5�FB\�b#.���e˖�G��um~�EEE\'[�h�+333;\�?3\�Fr�aH\�_ۚ�5&Il�KEV\�zIdH\�iUsCM\\\�A8�$�C�{��at��rQ\�S�J��\�LX\�x�\n]\�~�\�τ���b	\"	a\�Pr\�Z\�_\�\0$���G͈�\�T��Ȏf\�Z��FϞ=ͱ\�_���儢-�)(�J�#i�x\�\�1_w\�m�jn\��|a\��r�u;>5\�ʗ�\�?�}$3$5��\�ks͉�*-6ʋ\� z�Q\�I\rխь��~ME�ޏ����dff��iӦ�&N�x��\"5J�F�T������\�#5�~�<^+� q`��-�\�U\�4����\nwJHB\�\�LP]�\�\�ק��T�\�K@\�\�\�l4F^��}\�Cg]T�f�UR\�\�YT�/�i�O\�\0$5�]�i^\� j\�\�°\�\�1djX.�ly�Y�r�M=q\�/\�jFL]a#8�\�y��vF:ǆ\�\�}_\�|\�\�\�m�\�\�X\�\�d&L�o_73��i֦�MeV�S\�LY���%6*�QV������FV\��\�K.��q=VTn\�\���؈�f(\�\�\�\��ꫯ\�\���\ZJ?I�H\r\�b#A\�Fe#.j�P�-�|\�N��iKU�B\�.�\�\�v+J�?��P}[K#78Dv\�7I=O��Z.���X�\�\�L�\��T��^�X\�\�	b\0R�x ,6T��<T�]};w\�l*�\�\�H<~�=zT�\�u�ZU�\�\Z�����H\r������*\"7�Rõ6mڄ��{\�\r\�F9��7~O��lݺ�Xu>hM\�\�OT(4\�kj 6)��R\�5:J��v�%\"�gID_�\�ŠT��y���h��.�\"n4HM��\���\"R�\�\�蓼@[I���V!Ғ\�zV�H��L�\r\0H~T\�\�I�&M�(��\�}\r\Zd#6�\�ĉ\�\�i\�\�b\�ƍ�=�\�\�[cb��بi\�ɆX��,�Q\��\�\\s�Q222\�7n\���~z�w1�TGaaa\�СC*Jû���̳� 6)&7��\�a�\�]o¥�ק\�\�)\'�k��6�B\�\Z\�\�\�I�\\,\rZ�ul7���ڜR��xhIM�8ӽ�\�g�_�x\�?M, 6\0 �9rdX(�\�߿\��\Zt�l\�2�OVV�ٽ{w\��˖-3���Z�\�\�\�\��\�7\�Dl��ؘ={�i۶m\�K�x\�!��� 隖�ب \�ų�׶�i\�f\�\�>;/\n-޶m\�V��o߾\�\�ϟ�t�\��sڷo�233�ˌ��W�ڒ��\r	��r���7�\�ݵ�T\�\�J�\�\�\�v��\�/\�B}�\�(v&��ԏ\�\�\��\�舒)5>�j�\'ߦ��ȓҲ��xJ��i4J\�\�\r��h(b\0R�~�gB)�u\�\�EDW(r#\�`k���6�޻�.��o��v��ݺuCl��ؐ\��D%\"�\�I�\�J\r�\�~�\�i��بZ\�u%�\��m�\�	\�E{�\�\�y\�9�\�ԢE�\��\��\�F�En\�J�ɷi\��^�nE^�\�,N�(N\��ׯ�<�o\�t�!���ckr����\�C�1g)\�#f\nLN�yMM������\�{��\��)-\��twlI\rDu)/�\�@l\0@\�ЪU��`\�_4\��N8\r\�\�40�\��\�\�\��=x�`�Ǖ�p\�R�\�Fz��h���b��\� �\�ŉ\'�$5�#F\�En 6\0��\�H\'�Q�R\0�\0�L\�ƍÃɲRJ�\n6Zl\�\�\�Ç\��Ǝk�=�\�z۶mO\�	��\�bCh�r-4QlT�\�E,��\�|�\�R��\���\r\Zb#q\�\� � 6\0\0��0y�h\�S�9r$f���/���\��駟����GE��믫=�\��5�\�Ċ\�HT�Q\��Xr�:R#Zn�z\�\r\� 6\����\�נ�t-մ\�\�\0�\r\0HU�Q�|�IDT�?\�õ�3gF]�`A��\�Fr��F\"�+���Z�cJ?��\�\�s��z_:֖-[\0�\r\�F2S\"6\Zآ�%Ӓ�\Z\�e՗\�\r�V�T\�B8��\�\�S�\�Z�f\�;�\�\�b���\�F�^tqN\0\�\0�S\�\�+����\"7\�:��?=%]\"6n�\�s\�m��٧���\�4��\�/~a\��θ�\�o�f���<\��\���\�#�+�(RQ&O�lZ�h�4U�� b��\r\Zb�\�bÏ\nm\�*�)4���}�ȋ\�)Q�\�%24;I�\�\�	�\�\�;\�*���F�J\0b\0R�\�\�\�h߾}�\�[o�e�������ҥK�\n��d��\n�\�\�?�я\�\�7\�!-ZTJ:hP\�_w\�皻\�+\��%����\���� 8���#�]~�\�v����\��q\�ʕ�q\�ԩ	U<t\�ĉ>\�ب���\�\0\�\r��$bCӚf\��*2���:\�\�i�\�f��jb�;r#KEhɿ\�Evh\�T	\��L$\0�\r\0Hf*:+���\�׽{ws�\�I��I�&q\�ƨQ�*�>�}V]\�G�m�/��b�\��&b���u\�?�\�\�֯_oE\�Yg�eE\�\�g�m.��Ұ���p�h*^�\�\�U_E�t\�\�\�\�@�~O˗/��C�w\�e�\��z]m3fL\�L���\��Fe#$��	�*#���0�@lD�\rEYd\�_RD��B�\�o��cn�aM�j�\r�}A����+$*:\�\�\�(���k��-\�\�q�B�\��y��c��\0 6\0 �y\�\�I\r�\�1}�tۧy�\��*\Z/jCQ\nE�\��\�{JF�\�\�E,�\�\��\�\�\�}��\Z$zܼy��\�w�k~��\�U�\�\�q/^l\��^��\"YѧO\�ѯ_?{���裏̄	\�g�}f&M�d�:\�̙>/��D���$\"���\'5��(o{<$\�N?����\Z�\r�fϞ�������m\�ΝoyV#6�\�y��6�\"/����\Z�Aǜ\�J?q}uQ,=�\�i(�\�Eft\�	�߷�\r�\�Rr\�\�޽{��\�둫 6\0 �1bDX(�\�\�?n�\�{\��р؏jh\�?��UAEQڊ\���\�_OJ����m�!.�\�\"s��\�\�e\��ix\��+���?�)S���u�\"�7Zl(�BRC��8q/EM\�O��ݦ�\�\�̛7\��u�DJEI6ʋ������\�pR�[�niu}Bl@�	�w͜9�\�?��+DBbEjHj\�\�\�o�ڟ1+8O\�ND�\�\�\����n��nTI\�\��\�D\Z�������o�\�\�:��\�\0�Tc\�ƍa����C��ꣴ�}РA1��\�{\ZJ��(��\Zj�v\�J*��c\��gϞ��\�\rI��[��\�Ç\���\�o�\�\�Q\�\"��вĐ\�Ϛ5\�\�\�p�7����XbC\�\���LQS$����ꪫ��QYYQ\��\�*5Pcx\�;��\�B�<\�\"��M�e\�d2���s�\Z�QLf\�&\�_OC\�s�\���H|�\�FN\�\�N����+>���r�o\�x=Rb|[|\�\�\�\�\�-��\'�\r\0HE�u6�\nͪU�L�v\�̗_~s\�h�\�CĔR���^x!���z�*�%6���\�\�L⥢4j\�(����{�b�������\��\�\�~�+\��\�\\�tï\r\�JQkPR<TQ5�$z����\�\�۷�Q�\�i!�\rӠ�\�%�\�F�6m*!�\�\0H��\�O\�ʛn�\�L>�ԋ\��?\�\�\�\�1/�\�\�\�\�U+㑼Х\�9�\�_#	\"i\�!w\�\�Kt���$Hvn �sN��*mEҤ�\�F�m\�\�)�A�O\0�J�\�Q$0��}\�(�e\�\�7��Za\�ΝHݐlbCQ��ǋ\�жf͚��;��h\rEch{NNN�\���h�QV*��\�\�\�gcqD�.5F\��Q}�\�/(\��)\Zo�t�\��P222���H�(�\���|\"�-6:\�\�!7��\�~��\�RK$3$2:\��e\�^\�l(s���\�Y����c^��JsQdGvnpX�\0\�\0$;\Zp6m\�4,\�}�݈\��\r\�|�y��\�\�_}�U�^����\n�n۶m��s\�g\�\�g�Q!���z~\�9\�\�¡\�\�E��Hz�\�\���+�(�ڊ�PT�{^XXh�U:O��YQTdT\�J;r\�\�L*��\���\")���\�N\�\Z�_�4Al\0 6&��{��O$�\�\0b\0��Q��زeKx�jlT�?h	��0mڴ�(\r��Ql(\�\�k��\�&JG�7II7lt�O\'\Zy\���\�\�\�\�/�G\�s��\�\�늊�Ih�H\'6�\�\�\�_z�\�ب�Q� \�6�	b�\�A\�\�.Zõ���;�d\0�\r\0�\�h�\�\�:w\�\\�YM��\�7\�(��RW�)(J��.�9+J}5W�#YZ*����5-I\0�1\�cN��\�\�@l\0 6\0\0J�8�Ӷ�\�\�\���w\�	\�I\rͲRC\�]l$[KU�\�\"-��>B�	b�B\�\Z�)\�\�\�Bl\0 6\0\0J�B���&\��P�˚B\�\'�H�֭[� 6)%7*qA�P\�@�\���FI�\�\'�\�\0@l\0\0\�\�\�7ߌ�wRt\����uL\�~���\�֤\�@l 6�ʦ��~�\�\0�t��6\0�\r\0��Q�IZ�Z���m�\�ʰm\�6�\�/DDi�����\Z�\r\�F2\�\r�U�nݺq\�Al\0�\Z\�N)Kj0C\nb\0�\0P>K�,1�7.uաC\�QPP`gOqu8�(��\�M_ڵkט�a5Q(��\�Htʫ�\�f?Aj 6\0\0s^9��\�\0\0�EU��:GGoT��m۶\�S�\"6��A�	b�0p^�\0P\�lܸ�T���6��̛7�\�\�#b��r��\�\0`\0�W@l\0\0\�#G���7z�\�a�F��\����\�o{\�13r\�H�k׮:{o�\r\�F�ˍ+����\�\0�\�p\�޽O��\�n�Ҕ���k��g��\r\0\0@l 6R\�\�н+S�\"6\0jm\0�=$��N��[:	ݑ�jR܂\�\�\r\r� Z<d\�z�\�:\�\�7h��\��pN\��X\�\��k�#Cw\�	\�\Z\�77\�O\�\�88�G5%V\0�\r\0\0\�\r�Q�\�\�\0�2\0�k�B\�y�^\�yvN\�Q��\�\��}��@\�\�\�\���\�\�K\�Q���~��Ȕ(Q�	/\��F�v�o硳.�\�P�\�\�\�{V�\�Zuz>p!g.\�2\�\r 6\0\0\0��\�@l 6\0\���^\�!��HPH^t��+�\\�\��uȝ����T���*:\�\Z���\�\�\�)7�[-w\�	����9�[\�rq�Gqԇ\�罾��A��^���V\�^\�e�����;��\0�\0�ؠ!6�\r��\�\�!���\�\�	�^��%;\'\�9,+��(��xTQs\�sي�\�\��ం�ld���(��x\�\�\�!7���\�\�\r\��\�pT���\�௕�R�z����-��C=$R8s�\�\�e��y7\�tә|J�\�\0\0@l\��\r\�@Z�\r[\�\"/\�N�A�!�nƐ\��芰\�\�	vW\����/]\�J�\�\�\����!��K�ӣ��DIq�J\�\�\��Gs@����b#33�n>!@l\0\0�/�@\0y�\�@l 6\0\�Sld\�\�qb�cN��b�\�\�!w����M�E#%<��7(-\�Fh\���Ob\�Fh�D��%\�1#���\�Ǽ@C-;�\�)7؇3V湌Ukc!\�\Z�\�\0\0Ho\n\n\nLQQ!A[aa��O�\r@l\0԰ؐ�p�;��w^qi!�s��\���[WXԦ�^�>�\�U\�D�\��\�u\�J;��\�\�	�nkz\�Z)j\�[�+7j\��5\0�\0\0~6n\�hfΜi>��sDB�J�u\�\�!6\0�PnZUEa�\�O\�]�G�*��c�T��\�phn�}�\���8h4VV\�2cm�58r�\�\�\�\�	ˎ\�Jvp\�\�&##c\�\Z�\�\0\0�Xr#ڔ�Th�\�I����\�@l\0Ԓذ\�\�.��\���d�{�\�M�����JQ\�O\r#7\�$^�Oɐ�f7Q���y��\��\�U\�ԟq\�\�u�\�\Z��\0b\0\0\������\�\�\�\� Z\0\0�@8��\r\0\�$\�9���\n�\r\0\0` �O\�\0b#�FRgnڴi����O)\�1U�O��\�\�\�ٳg\��Z\�R\�\0\0\0a\�\' 6\0q�\�`*�\�l{�\�1�f\�:����g���\r\0\0\0O\�b\0�Ej 5Zn�1c\�j���\r\0\0\0O\�b\0��� �͘1\�\�T\�\0\0\0a\�\'b��؈�\�9 ^l�]El\0\0\00\�|\"6\0��ꈍ/�\�m6-iV\�?e����\�\0\�\0\00\�\'b\0��\�b\����f\���f\�\�.M\�\r��\�\0\�\0\00\�\'b�n��Dl܊\�(_ll_�^)�\�ڎUS��\r@l\0\0\0a\�|\"6\0\�[�v�k�J\�@l�=�^z&�\�\�6\�b\0\0�@8���&33�\�w�/\�y-\�\���\�n�\����\���\�[�)�Z�K\r\'3��\'�\�FE\�\�\��\�\rmC> 6\0�\0\0���\�HA\Z5jtKFF\�$\�\�z4Jd�\�NHtx�\�M7\�t&�d�I��\�#6�\r@l\0\0\0a\�|\"6\�\�\�y�\�VRf\�k�\���Zmn-G^�\�iWs�\"bC��\�چ|@l\0b\0\0\���\"dff�/��ˉ&M�������\�ǛU�V�ݻw��G�\���\�_���\"�n\�:3q\�DۯiӦ�\�\�q~\�\'\\-�Q��H\�ȍ����s�\�چ|@l\0b\0\0\���\�4l\��%�3\�2�E�f̘1V\\T�#G��w\�}\�dggGȍ���\�\�M|ڕ\�JFbT�Z��};����*��\�\�6\�bJ�\�M�6�\�q\��\�^۾s\�Γ��gee}�\�#�\�\�\�]����\0�\0\0�9��\�H�Q\��={���\�\�\�ɓƻ1��c�ڟ�\�+%)\�\�~�\�EnTDl�m^4��\�\�:\�b\"�ƤI�^\�ݻ��v\�\�Y��\"�$��m\�^�hѢ��\�_\�\�>\0\0\0a\�\' 6\�\�H\r��\�\�\�\�Ν;M�.]�r\�hFF\�|�V�6i)ǎ��s_(��\�\�6\�b�Ը\�k�sss\����+�w@\�]�v�R|˖-ܵ\0\0\0a\�\' 6ꎒ�\Za頛\�\�@�\�S�\r\��ڗrbR^�к>NR��\�v�us�\�\�\�\�6�A@ 6\�Xh�y\���Q5q���\�M\�֭��\0\0���؀�Fj\\\�/�H�\�D�F��\�\��333ș�)#j*�$\�#7⊍c\�\�\��A�\�\�\�\�J\r\�\�G}�\�@l��\�3f\�g�z�*u\�>q\�=w�\�w���\�\����=�\�\�\\z\�\���\�\�\�\�ϡC��k\0\0O@l\�\rK�55\�\"7Z7��o���\� \�D)	\�\�i^k^�X��K邢�\�FyQ\ZDo 6���\�?�C���D��\�\�����\�k\����\�\nφ%��p\�B�\�sϙ뮻\��S�X��4\0\0\0a\�\' 6j�F�\Z\�\���.Æ?��\�\�b�?匔����Q�\�Q	�o����\�FE�4ʊ\�@F 6ҁ��\�ߧ��\"�V^deeو�>��{�\�,W����\�\�\�_n�}�\�W\�\�~�\�\0\00\�|\"6�&\�P�8��\�}m3p\�@�\�x�Q*]�����\��K\rך\�HsI���Xb��R\�5db#\rRP�\�\�*\�\�w\�q��\�[̞={\�A��f͚\�k��q\�\�^\�>l����\�ÑNz\�UD \0\00\�\'b#�P]\�L�/��b�A�6n\�\�OG9ТE�H\�\�O6ĒeɍX��\�\�r57*:\�+\r�b\�\�\�\"5$5$)ĩS�l}�aÆ���\�\�~\�\�ŋͽ�\�[\�\��\���+\0\0\0�9��\r\�F�����\'4SI}�e�>233\�.#�\�j\�=OJa�qk%dE,�QQ�Ѡ:i)�z�\r�|�Ɩ-[��(T#C\�\'.RCh\nW]�\�̙c8`%G�>}\�7\�|S\�z��~ӦM3s\�L��⯹�j\�*�|�r\�d\0\0��0\���ب\�4�\�\�j&��x�\�\�,\"Z2k\�7sK����\'ʓ��\Z�\�\�S�ȍD?��\�),/�iӦuUgW3C\�@U\0T55���\�;�\�\�\�����Σ��P(��\";$<\\_�\��gK\0\0` \��Dl 6jRl��PX�fM��\���\��#6f\�H���R��\� �\�\�\�U�\Z\��$\�y@l$�\�Hqy��>�c���\�:?W\\qE�k��O>i�w\�~�H\r�\�4�Nv�%u�.]\�\�4[J�(\0\0` \��Dl 6jRllw�Ժ�\r%\ZU\��\r�wgdd\\��\�F�SLlT�\�E,���\ZR#\��I��\�H>���2�y\�羐Xv�o\�\��\�\�7\���iZ7nl�yݰa�	�v��\�\�mm��C�\�\�J1Q��\�\�ȑ#\��k*X	��>ޔ�\0\0�@8��\r��\�$�\�\�\�\Z�AG\r�c�Sh\�S\�YIbɍ\�H���/��/b\r6��i\"/��c\�T\�\�\�W_m\n\n\n\"�ϟ|�=\'���\�6<�@x*W\�m\�>�����z�`����*����Gt\0\0\0a\�|\"6�\�\�F\"�\"�S�>�A\�<}�ǈ\�X[��:\�}�{\�\')?W\�Ab7��>�Y�\�]z#�_�y晥$�+��bZ�lirssmD��RN��vş\�gȐ!v��ME��F�K�\�7mڔ�\0\0` \��Dl@jGl4j\�\�\�q^\ZDl\�Z\��ˊ\�Q݈\r���\�<\�W\�ƾ}�\�g��|\��HS]j���\�b�~\�\�\�g�5���\�\����+��&M�d.�\���\�?�\�3�t\�Ҙ�7f̘z\�&0��E���FQ\Z͚5�}%��z�\��^�+V\�ms\�εi,]�v����\0\0` \��\�FZ\�\�p\�/33�V\�yA\n\�\�8\\K56�\"7\�\��H��P��(g�q���\��\�\�9�\�\�\���\�\�\��=\�s�|кY�f�)S���+W\�\�\n\�W�E-k�M�\���\�\�˓\'O6_|q��\�\0bIk\�ԩv�}�\�\�W_5&L�\�N�\�G*J\Z\�ˤ \��O�\Z���\�<��\�F}�\�\�k#.�\r\�4	j�5jT\����\r\0\0` \��\�Fm����\�Q2*H\�YQ*\Z�oJ\�X��TFnT�\�G�������\�!�T=z�}�\�\�`>\�O\"#\'\'Ǯ{\�\�죊3�;6��B��E\�UW]eEϞ=\�y\�gz�\�ёN�hy�\�G����܈.�\��2\�y�����vt��\�ӧ\��Ѽy���\�\r��lٲ%�nժU�\�E�\"�\�\�\�\0\0\0O@lԶ\�\�s��\�\�\�\�I\�k�5y�\�o\� h^����ΎOj�V\���ȍ����2�#�z\�Cl(�bΜ9��V$��.\"C���~�\�\�_�(�BJ�\\5\n�ņ�\��mz]\'1��\�\�^\�-k\0�(ӽ���Lh��J[rDϊ�Mt>�J\�G\�CU,�駟���Kvh?ѳ�\��\r\Z\�\�\0@��\�\�\�GJ�\���ƻ	\�r_����\�\�I\�\�|�N\�SQ^\�DyR�A\�K��;>\���\�\�g�\�n9//\�\�{\�6\r䣏>�[�n5Ç�뮹\��~��к\�o�=\��ˊ\�p5<�\��k\0�TI�@ n�\�У�\�L��\��2a����S�\��\\q\�vYi\'*\ZOB�h�%K�\�\�z�2ݻw/\�\�뮳\�U���������\�]�\�\�U\�vS�����l��+\�J���\�\�U\�]Re]镻��=\�HDaI+\�!�K�]\\���7�\�+Pj��A2�\�ʋ����\�(�\��\�a���7��yk\���W�t?\�\�̿{���\���G�G,]`pY�j\�	\�\�w\�}~P\�S�A�UUU}���k�\'^�|r\���>�\�6�5��olN�\�\�Y\�FWC�\�\��]�i��`#F:��\�8\�\�\�\�\Zq٫����\�\����\�Ǐ�^\�Cw+6�\�\'\�\��s\�9�U��\��`��5a�q\�=���jD?�3\�8#}.nܸ1}.:t�\�\�\�|L�<9�\�+\�\n�\�\���;\�huL\�w\�^\�.\0�3�\�7�#G�L�V�|\n6��\�QJ}6/^\�\�:����`AO��f̘�\�{\�v.�u\�\��}\��z�r��K�j/\��A7*D� ؈e\'���J�F\�44k\Z\�\�޽;=�\�\"*4b\����ۿM\�\��:����Tl\�\���[��\�/�\��V�FG\rG�S�\���\��N�\n�\�M@cǓ\���{\�ܹ\�i4\�\�Ē�\�`p;ާ���0��\rzl9J|:ח۾\�ڵ+�\�\���\�g\�c��i\����q՗�1�\�K\�k�aRM\�\�e�?��T\�x^zLMcձ\�p\���?\�6x��\�x�����\��5�ᜉ5�\��x^\�\�;�\�L�m\\\�\�\��tS�W\'\�kl\��sbMì�\�sW|\�$���[�\�C���\Z��\���۰T�\�\�`\�3��L\�4�ƈ*�8��\'�����\�{bd�7\�pC�c\�6;\�%�>:�\�\�~���peȐ!�\r:6��8Zm�\��$*�9sf��;�\�Q�q\��k\"K\��>\0�WG���O�ͧ`��R|\�sj�	�b�D\\�ti�M\�\�^[z\n�%=�&\�4\�\�\�I5+\�\�\�\�\�\�Bum\��\�\�\�y�W\��¤���=��~\\uMï\�6�knm[e1\�\�\�3�k\�+#(�\�\�\��y�#Z\�w\�u�o~\�D�\�T\�4>�,���M���\�=�,\�ߵT\\tw׶\�w��5\��FTM�ҲMk���˳&��QU\�6�\�Wvt4::��ێ7�\����i\�e*�\r\�̙3o{\�GJ��#\��`\��\�K�\�\�\�\�\�QMC��(\�s>�\�|P\�\�\�P\�2����O��b0��\rz�j#*(�[���\�˗\�_\0G�B���\Z����ṉsW|�\�\�\�\�\'ߴ\�E@\�E��o9-�#�Y��\"�j���������C\�\�^񘋲j�\��\Z�O�4Ę\�x~v�X�Ǳ����\��ǈj��\�\��VMKWwK9�e.��\�K#��\�/��%��4	\�o�\Z\�\�駟��F#\�s\�=7ݑ\"��\�/~�~O�}l\�!\�Yg�u\�\�oF\�V�D�\��t=��7��\r2C�=cԨQ\�Fϣ|���,%\���#555ɝwޙ\�o,?�\�\��P#���u�}�\0߸q\�|�o>��\�Q|\".˞�\��\�}=)�\�\�/A)�q=�\�y�s�ʊ��� �]\�Ƽ��\�*�y�w�\�h<�z^\�\�\�m\�\�xvK\�\�qk\�T]S_�k\Z�\��\�[V�\�r�ƩQ��.{�\�pՄ�g����ÛV�\�RUG�\�\�k�\��\�\��Տ�T[\��WG�2��I�`\�S\�?�Aa̘1\�B\�\�\Z\�h\0\Z��\�V���)-b\�I�وm_��\�\�\�u��6�5V�\\\�/\0R\�k�?{�a>�\�O\�X|2\�˞��f\�J�9\�+�Ft\�=Q\�\�\�چii�P\�pk|=3&\�k�sBͪӳc\�^�\r\�\�X\Z��\�\�WW|�4\\�/\"\�(�\�-W9���\�_K\�\�4\�\�\����o�\�D\"(9�\\��\�ב�\�\��J��Y>�^~\"\�l\�s���Fm۶mQ\�\�\�`\�\�EU\�7��\�\�\�SO-5&�0c�\�\�iU�\�O\0説7�O�ͧ`�W(�]��\�!z`D���\�O\�Tj<<t\�\�VM>#,�ꇬ�gumCMv]4�l8�\�,X�\�\�X�\�0+~�\�D҆��\"�(\�\�wbYJz{�\Zn\�\�wY�\�,(IÒ\�m�>�qjzZ[?4\�g�Fg�@z0\�\�n\�ŧ�Q�`C�A\�)����СCo|\�~�*\�Z�\0\�7l��i/�\�.�,\��\�\�_Ǉ��&MJn�\�ti/\�T��I�������l\�\�A\�\�\�tR�]�O�޶�L�P#��\��i��2ڄٲ�/�Ɲǚw\��2[2yފ\�\�0�.k,�.A�p�xLl\�\Z\�D���|�\�NZ/��#\�\�Q\�xYT�|�\������\�I\����`�^[�2c\�ر�=��\�\�ݨ\�k~\�G\�\�\�w��\Z\0�W�\r\��^\�ꈆ\���\�\�[o�\�/9z�ò�Ӄ͟�\�`�6\�\��`�	\��u7m�Ft�\�\�o[��.?ijt�\�; tv}��j�_~�O��g���\�6�ñ\rW��Ck\�t,\���9h[iXQS_\�\�ui�����-o��[\n;n����k�\�,��\�q\�`z\�\n6�x\�}qq��\�K\�|�\���X;���x�\��\�\�7Oo-~9r��\�����}\�׾�5��\0R�KF4�\�\�ߎF4��O�9y�WmI6\�|+=˒\�W�jŵ\�/&\��\��\�\�\�\���䶤�\�\�e�=\�\�nN���4�x\��\�qWǔ�tT�̤�jDz��1?.3�4.?5=���c�soOV=�3�\�5/��\�װ)���\�\�\�v�8\��hR{�3\�������\�3�Oʝm�L�0!m\r\�\�Ӹ\��i�\���\�ԩS;�u��F��j�h\�\�.��\�\�\\Z*2w\�7\"\�Ⱦ�\�M\�4\Z~F�KD�\�g4�xKcUgM>#9\�\�&Q\�>�\��K�Ut4~=��\�����[B��s���j6�\��#G�,5o\��\��ӷTUU}��j\0�k���\�\���g|@\Z\�6lؐ5���{�\�M6oޜ6��\�F�\�\�6bg�\�эO\����&\�ߚ���\�x\�\�ɯ����~�\�\�6m#���\�\�ʋ��Ӏ\��ܘ̘sk2\�e鎀�׿�\�i\�3;:�ӑ��8=eN�s\Z�F\��b��\����ɿ\��D�\�#\�P\�\���\�%��۵\�e\�.\�\n�F���\���\�Vo|�Q(�t��+=Z�1薟6H��D��R�Z\��\�K.\\��\�\�ؙq\�ҥIuuu��ѯ�\�yɱʇ\�my==�P !G�a\�]O�/n~5�\�\�L�.\�nN\�x���\�ek���i\�[ɒU[\�\�G�x�\�}�\�\�\�g����=\�\���\�\�i�����\�\�����\�\�\�\���\�\�ѦW\�#g�!�~\�d�\�.��\���6�P|\ZW|\�]�o,\�\��:>�l?\�^7u�!\�l \�\0�B�K/��\�{�iӦ%m�\�X\�\�v\�\�\�p\�w��wӍ�>\�\�\�X\�1\�s���%�7��(<�\�MɘI3ҟ���3�Q��9�ӗ�lO6n{3Y�zK�\�דU\�[N�|\�\�\�G}��|vG��^�\�\�\�ކM\���0ybî\�߷�\�ˮ�\�tN5���%�̈́�\�\�V���߿\�j�J|\�\�\�P��(J�[�ỏ\�,U\�S��SW<�-\n\�����I�q\�`5�\r\0����\Z�\�\'�,\�&S�Liu�\�w�|E5ƌ\�<\�*���,%/�5��cԘ\�?�.�$�\�]K\�9��\�W\�H��\���|t<T���v�\�\�=�\�+\�;�\�O���ޑ�\�ِlyyGiN/�imzz\�M�\�\�~\�P\Zl\�|\�@r�\�?�n\�=o\'w\�m,Uu\�X�\�R\�����[^\���`C��`��Ӷ�\�\�\�ؙ\�\��\Z�\�>��	\�X\��\�K�[q>ߗ\��G֧\�\Z�dڴ\�\�ŗ�K�4�\�\�G֤�D&�}F�E�p#B����\'~�Q\�\�\�M\�3��\�=��tݶ=羅#\�t\�\�\�$�N��\\<\�\�\�+\�$w=�Ǵ�$z�D�\�x��Ng>��u{SYύ`\��+?��`�\0}%v?i[�ћ�)\�Y�>ޕc��d�\�\�&\�8���\ncӆ�\��u_KC�7\�i.\�ǜVV[&2\�\'s\�\�iT_ܶ|}�e\�[\��\�\�I�5�\�F�6<�=9p����Ʈ}K\�!Ȓ��%�\�#��\�d�\�nN~pyu\�\�#\���U/�Ke��F��iTfD�ȋ/\�K+N\�K\��U�6��\r\0>��\�%\��/��>p\�@��Fv�?�\�OLD\'�*\�\�O\�\�^\�\�\��\��\�ڭ\�r�h\��G\\6\�G?)�l��lA:�\�=������\�Ȫ5\�<��\������\�ȼ��\�\�ͯw�\��\�\��v�9�r\�5\�(�\��L\\�39\"Ԉ\�\�\�\�v�� \�06\08�xC�\�)\�d�v\�\�O?\�jIʦM�L\�	D�\��w�KG\��Ϥ\�F&�6\" �9�%!���\"=\�\��g�\�x\�o|�\�\�\�\�MϿ���\�+�GV?��\�xa\�d\�\�ﶻ\�X��\�\�C>�\���9��\�>Vj \Z�/��DE\�\���j5\"�)\�\�P�`C��`�~6v\�\�\�\�\����]������\�MH?\�i}}}z\�\�ɓ�T��8̩`\��+<У���\�g�`��}-�7��m\�޽{��1lݺ�U�7\�x\�\���\�x\�iuE|\�\�ŋOx��5k̩`\�U�V\�_\���9v\�\�uO]]\�F\�T�\0\�k���7��SI���.=��\�\�\��\�Ʋ�K/���=_|�q�#=���\�m�\�fN@W444|o\�ʕo���\�\�\nV�F�\Z+V�\�^\��Ll\0P�b�@_\�r<�\�{�&��4��7onU]�\�]w]�Ǿ�\�[\�\�_ޥ��ۜ\n6�N\�8_XWW�.�<D?c@����B\r�\0\�\�\�.+��\�\�Ɲ�F9{�G�61}8��\�iJ~\�v�yQ\�1}���\�w\�yǜ\n6\0@�@�>|x\�\�g_\�\�֎;J�#}7���o\�`#���\�\����\�\�\���+��\n6\0@����3\�\�\�[m9r�\�c�o\���\�\�Vkd\�\�\�����\�\�5;.zx�S�\06\0po�=��7�O<�D���|�G6V�\\٪�\�O>iN\0 \�\0��>\�\�M*6�gN�\�_��*7�\���\�)\�T�\0�\r\0���{N�����\�{���;\�Ҕ)S��S�\06\08\�2x\�4B�츫��*�\��\�˪��:\r6~�\�ߚS�\06\08���\�қ\�E��\�\��\�G<&zN}�\���K.��UeG4\�j㩧�2��\r\0l\00p��׿.���={v�=�X\�=�;\�\�\����Ι3\'=����\�\�\�C#��v�l\�bN\0 \�\0`�غuk\�\�g,A8p\�@�?�|/��w\�61�<��\�d\�ȑɍ7\�\�\�m<�\��\�B��S��S�\06\0x�=/^\�\��?��\��WWW��>�\�\r6$\�ǏO:\�\��ǎ&?�\�[\�ҝ���8��\r\0l\0\�O�K\�\�n_n��k׮V\�*׮]kB�`Nc�7�g͚5��.�y\�s*\�\0\0�\0\��￟�1��Ft\�ҥ}v\�\�^{m\�~Ǎg2�hN�6|�\�G\�O�\�R\�ƻ\�kN\0 \�\0`\�\���o۶�\�\�s��\�*�|�I\�Gs\Z=6�\"���Ü\n6\0@���6v\�\�\�\�ɓ\'wk�\�e�\�\n���&`�\�\�\r7ܐ\�y\�\�T�\0�\r\0�\�۷�\�\�s֬Yi\�\�5bG\rC\�cN�\�ٓ�_�ޜ\n6\0@���W__\�j)A�Kx�\�7{\��c�B�S�1c\�$��\���7��\r\0@�@ϸ�\�[��O⛚�N\�6c��\�ӧ��]��9l\0\0�\r\0z\�<\�j	C�ٳg�\�$�cǎ\����[}��-Uj�S�\0 \�\0�\�<�\�\��\�\�[�y�1a�`�\�\�\�NYφ8�7�Q	�hѢd\�ԩ\�W�Ps*\�\0\0\0�����k�i�I�\'\�ƍ���9l\0\0�\r\0�\�֭[\��S\�\�%\nk׮�C4��\r\0@�@�:|�pګ\�ꫯNE�]\�_�=:��\�+�$�w\��C3��\r\0@�\0 \�\0\0\0\0�\r\0l\0\0 \�\0\0�\0�`\0l\0\06\0\0�\0�`\0\0\0�\r\0@�\0 \�\0\0\0\0�\r\0l\0\0 \�\0\0�\0�`\0l\0\06\0\0�\0�`\0\0\0�\r\0@�\0 \�\0\0\0\0�\r\0l\0\0 \�\0\0�\0�`\0l\0\06\0\0�\0�`\0>��Fss��X\0\0:p�\�\���\r\0��\�o�\�\�u\��\�\0Ё��~��\�L[�\�\0ԙg���.�\�?[\0\0\�9�\�/~���\'\�l5�����\r\�{���G�\�8\0�A�f=�?*5ZB�����?`��\�1�\�?\�\�0�?GEE���aa\��9B\r\0\0�[Z�\r\0\0\0��#\�\0\0\0\0ʖ`\0\0\0([�\r\0\0\0�l	6\0\0\0��%\�\0\0\0\0ʖ`\0\0\0([�\r\0\0\0�l	6\0\0\0��%\�\0\0\0\0ʖ`\0\0\0([�\r\0\0\0�l	6\0\0\0��%\�\0\0\0\0ʖ`\0\0\0([�\r\0\0\0�l	6\0\0\0��%\�\0\0\0\0ʖ`\0\0\0([�\r\0\0\0�l	6\0\0\0��%\�\0\0\0\0ʖ`\0\0\0([�\r\0\0\0�l	6\0\0\0��%\�\0\0\0\0ʖ`\0\0\0([�\r\0\0\0�l	6\0\0\0��%\�\0\0\0\0ʖ`\0\0\0([�\r\0\0\0�l	6\0\0\0��%\�\0\0\0\0ʖ`\0\0\0([�\r\0\0\0�l	6\0\0\0��%\�\0\0\0\0ʖ`\0\0\0([�\r\0\0\0�l	6\0\0\0��%\�\0\0\0\0ʖ`\0\0\0([�\r\0\0\0�l	6\0\0\0��%\�\0\0\0\0ʖ`\0\0\0(\�\"\�8�X\�\'\0\0\08\�,��\�6\n�~R\0\0\0��TQQ��q���C�9\�O	\0\0\0�Z�6:6*++/�\0\0\0�Nzm�S�\0\0\0x�\�\�[\0\0\0(�Ba�j\r\0\0\0�,\n�o\�zk�\�\'\0\0\0���׆j\r\0\0\0�\�d;��I\0\0\0� �$ɩ/��򢦦�\�\�듺�:c\0�+V$�V�z�8.�,\0\0�ND��z�\�d\�޽\��\�o��o߾\�\�{gŊ\��L\0\0�D��Pc@�\�\�\�\�m�L\0\0�\��\��uuuG=S\0\0�\�\�Ax0\��\r\�P\0\0\�d��Co\�I^^� ٰ\�\�t\���L� \�\0\0\0�lܿ+Y�\�\��\�Oi5ⲸN� \�\0\0\0�l\�\��`�P#�nX&|l\0\0\0��\r666�K��F\\\'|l\0\0\0��\r6\�\�\�\�4؈\����QYYyZ�P�(�S�ű�8�\�\�Sk�\���x\�\�\����\�\0\0@�!\�\�WÆ\r;�P(,���hnd�h���8F2\�T�b\0\0\0�\�qG\�\�Y�\�	\�QQQ�\�\�X\�\�0���1�8��\0\0��-kn\�4؈\��������\�RqQ\n\'����ٳg\'�-J6lؐ\�ٳ\'innN#G��{�&�7oN/^�7bĈ�������h\0\0\0�F��\����\�~J�N� \�8��C�~��F)�9rd�p\�\�4�\�Ç\'K�.M���[��Ba�t�W5\0\0�`�\�x\�\�\�q�\�A�\�\�Pcu>��6mZZ�q2>�\��d��\�i\�G\�\����\0\0��\�K�<>��2�\�eq��A�q<m+5b\�I�=e׮]ɔ)S�\�Fs�P�Ы\0\0@��ܿ+ټ��\�q]#�lt���F)thjjJzC�\���ʍ\�}�\�\0\00X���\�K�liH^|䧝�\Zوc\�X\�����\�O�\�+5zS4\Z�5kV>\�\�SYYy�W9\0\0� 6NT��zC�\��B\�\�|O��\\~ҙ$cƌ\�7�ѫ\0\0`�]�\�8^��0B�1lذ�򻟜l�\�\�x�\�\�6�k�t\0\0�Al|\�P#\��FEEŲ,\\�-]�\��\�_�7\�x�\0\0�`\�l��\�k;�D�[�\�ݻ�σ��[�旣\�9r\�_y�\0\06�\�	\n��,T��J�Kuuu)ܨ���ȫ\0\0@�a�`�����8�\�񎉥}�\��\�{ｚ�\0\06�F�\�\"���+\n\�\�\�\�\�M�6�[�\�\�Ԕ�ب�j\0\0l���\�x�mG�\��}�J[;v\�\�?\�=^\�\0\0\0�\rC��t4�KT�777�[�q\�ȑV�ѫ\0\0@�a6����_�7�\0\0�`�WG�!$��\�/J\�N�z\�\�_y\����;w��7\�|szyl\�y���뮻�\�O?�\�\�{\�V_>|8��իW�\�Ϩa*6\0\0\0�\�/��B)D�]4:;����J��կ~U�\�����4Fv\�k���\�Η���d\�\�uqY����\�o\��\�\�2���F\\����\��_�\�w�S�\�\�8!ƺB�p\�!CN\�c\0\0�O}���3\�<S\n6\"T8�\�3�\����ɖ-[\�`\"�۾}{���\�g��^�\��?�pz��.HC��͸����dɒ\����#�\�,�\�F\�O��\�\�w\�y\�a�`\�\�@#w�]Q\0\0\0��h\�\���\�\�\�x�_��8\�/�0��cǎMv\�ޝ��ꫭ�6\�|�\�Ғ�1b	\�9眓��\�K�j���1\�,$ɖ�\�1<��`�}������|��;�6;.B����{��^\�\0\0\0��^	6\�1D�,hhy#\�\�{��\"**b�Hr|��O�\r�466���˿�\�\�k_�ZZ	�/|!\r\'��o���D�\�G\�N�(���Y�J�\�sJ��C���\�*4ڪ���8fϞ\�o�Ɣ)S��\���\�\0\0�l\�\�֖.˪$�\��#[~��;\��N2jԨR���\�F�7F\�s\�\��E ˖-K�˖��}L�\�Ɏ�\�lt]UU\�W***�#P(�O8\�\��5�+++O�j\0\0l�h�1s\�\��4ۍ$��L�6�����`\�3��L\����\���\��Y�\�}\�{\�\�!C\�ʍ6�\�)-\�\\:\n6�:c͚5�\�l\�\����F7\��l,^��σ���\�烍^\�\0\0\0��8daB~בS\�\�pr�`#\�k#\�26췾��\��\�o/\�JGKQ��KX�\n$�3�q\�\�屔%�>0&0#\0\0IDAT]�_�2~��>\��u׮]i�Hv�Ç?\�+\0\0@�\�k�ٮ#q��\�+Umd��Y0�ETWD(r\�i��۹$�cD�C�\�-�\�뉂�ؙ%�3>�\�϶�\�ltQ˶�/f\�\�ҥK�,ظ�\�kK�F�PX\�U\0\0 \�\�\�`c�\�\�i�aCvy�T$\�!F�_�\�WK��dc߾}鱱�IvY~+\�SZzed;�D�E���������5\�*�ƥ��E�\�5�����ضm[��\Z˗/\�/A9Z(�\�U\0\0 \�\�\�aD~\�\��6\�5\�˲�a�\�\�\�\\��荒_�R\�\�\0\0�`\�:t\�+**�eaCl�{\�ȑ^	5F��a(\0\0�`\�l��B��\�\�؟�\�#v�\�\�\�\'m*5:t\�缺\0\0�`�GTVV^TQQќ�Q]\�\�\�tһ�L�>=h5\0\0\0�`�w6켊���� b�\�\�ɦM��h\�ر#�?~\�*�t��P\0\0@�a6zMee\�;\�Ʉ	��\�;\�!\'vO\��p\�iQ\��\�L�:�m��\�~�Q(\0\0�`\�l􉨪����ݶz㓌B��Ė�\0\0\0�\rC�\�窪��RQQq]��hG�\�X0|�𳽂\0\0\��F�\Z9r\�_��ߘ\�?;X�������xZ[(*�\�O�\�\0\0@�!\�\0\0\0\0��!\�\0\0\0\0��!\�\0\0\0\0��`\0\0\0�`\0\0\0���z\���\�uuuG=S\0\0��V�ڳw\�^\�\0�v���n�g*\0\0\0t���\�{+W�|��\�_W�0�*5\"\�X�b\��\���g*\0\0\0t��\��º��u�\�!�9b\�\\lj\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\�\��?��rv,��\�\0\0\0\0IEND�B`�',1);
/*!40000 ALTER TABLE `act_ge_bytearray` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ge_property`
--

DROP TABLE IF EXISTS `act_ge_property`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_ge_property` (
  `NAME_` varchar(64) COLLATE utf8_bin NOT NULL,
  `VALUE_` varchar(300) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  PRIMARY KEY (`NAME_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ge_property`
--

LOCK TABLES `act_ge_property` WRITE;
/*!40000 ALTER TABLE `act_ge_property` DISABLE KEYS */;
INSERT INTO `act_ge_property` VALUES ('next.dbid','15001',7),('schema.history','create(5.18.0.0)',1),('schema.version','5.18.0.0',1);
/*!40000 ALTER TABLE `act_ge_property` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_hi_actinst`
--

DROP TABLE IF EXISTS `act_hi_actinst`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_hi_actinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `ACT_ID_` varchar(255) COLLATE utf8_bin NOT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CALL_PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ACT_TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_ACT_INST_START` (`START_TIME_`),
  KEY `ACT_IDX_HI_ACT_INST_END` (`END_TIME_`),
  KEY `ACT_IDX_HI_ACT_INST_PROCINST` (`PROC_INST_ID_`,`ACT_ID_`),
  KEY `ACT_IDX_HI_ACT_INST_EXEC` (`EXECUTION_ID_`,`ACT_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_hi_actinst`
--

LOCK TABLES `act_hi_actinst` WRITE;
/*!40000 ALTER TABLE `act_hi_actinst` DISABLE KEYS */;
INSERT INTO `act_hi_actinst` VALUES ('10004','leave:1:6','10001','10001','startevent1',NULL,NULL,'Start','startEvent',NULL,'2016-11-02 09:38:16.403','2016-11-02 09:38:16.410',7,''),('10006','leave:1:6','10001','10001','deptleaderaudit','10007',NULL,'部门领导审批','userTask','xiaomi','2016-11-02 09:38:16.410','2016-11-02 09:46:07.006',470596,''),('11','purchase:1:7','8','8','startevent1',NULL,NULL,'Start','startEvent',NULL,'2016-11-02 08:31:44.512','2016-11-02 08:31:44.516',4,''),('12502','leave:1:6','10001','10001','exclusivegateway1',NULL,NULL,'Exclusive Gateway','exclusiveGateway',NULL,'2016-11-02 09:46:07.007','2016-11-02 09:46:07.008',1,''),('12503','leave:1:6','10001','10001','hraudit','12504',NULL,'人事审批','userTask',NULL,'2016-11-02 09:46:07.008',NULL,NULL,''),('12509','purchase:1:7','12506','12506','startevent1',NULL,NULL,'Start','startEvent',NULL,'2016-11-02 09:47:42.238','2016-11-02 09:47:42.238',0,''),('12511','purchase:1:7','12506','12506','purchaseAuditi','12512',NULL,'采购经理审批','userTask','xiaomi','2016-11-02 09:47:42.238','2016-11-02 09:47:50.595',8357,''),('12515','purchase:1:7','12506','12506','exclusivegateway1',NULL,NULL,'Exclusive Gateway','exclusiveGateway',NULL,'2016-11-02 09:47:50.595','2016-11-02 09:47:50.595',0,''),('12517','purchase:1:7','12506','12516','pay',NULL,NULL,'付费子流程','subProcess',NULL,'2016-11-02 09:47:50.596',NULL,NULL,''),('12518','purchase:1:7','12506','12516','financeaudit','12519',NULL,'财务审批','userTask','xiaomi','2016-11-02 09:47:50.596','2016-11-02 09:47:57.472',6876,''),('12523','purchase:1:7','12506','12516','exclusivegateway3',NULL,NULL,'Exclusive Gateway','exclusiveGateway',NULL,'2016-11-02 09:47:57.472','2016-11-02 09:47:57.472',0,''),('12524','purchase:1:7','12506','12516','exclusivegateway4',NULL,NULL,'Exclusive Gateway','exclusiveGateway',NULL,'2016-11-02 09:47:57.472','2016-11-02 09:47:57.472',0,''),('12525','purchase:1:7','12506','12516','manageraudit','12526',NULL,'总经理审批','userTask',NULL,'2016-11-02 09:47:57.472',NULL,NULL,''),('12531','purchase:1:7','12528','12528','startevent1',NULL,NULL,'Start','startEvent',NULL,'2016-11-02 09:52:14.467','2016-11-02 09:52:14.467',0,''),('12533','purchase:1:7','12528','12528','purchaseAuditi','12534',NULL,'采购经理审批','userTask',NULL,'2016-11-02 09:52:14.467',NULL,NULL,''),('13','purchase:1:7','8','8','purchaseAuditi','14',NULL,'采购经理审批','userTask','xiaomi','2016-11-02 08:31:44.517','2016-11-02 08:32:05.096',20579,''),('17','purchase:1:7','8','8','exclusivegateway1',NULL,NULL,'Exclusive Gateway','exclusiveGateway',NULL,'2016-11-02 08:32:05.096','2016-11-02 08:32:05.096',0,''),('19','purchase:1:7','8','18','pay',NULL,NULL,'付费子流程','subProcess',NULL,'2016-11-02 08:32:05.097','2016-11-02 08:32:31.959',26862,''),('20','purchase:1:7','8','18','financeaudit','21',NULL,'财务审批','userTask','xiaomi','2016-11-02 08:32:05.097','2016-11-02 08:32:31.956',26859,''),('24','purchase:1:7','8','18','exclusivegateway3',NULL,NULL,'Exclusive Gateway','exclusiveGateway',NULL,'2016-11-02 08:32:31.956','2016-11-02 08:32:31.956',0,''),('25','purchase:1:7','8','18','errorendevent2',NULL,NULL,'财务不同意','endEvent',NULL,'2016-11-02 08:32:31.956',NULL,NULL,''),('2502','purchase:1:7','8','35','exclusivegateway5',NULL,NULL,'Exclusive Gateway','exclusiveGateway',NULL,'2016-11-02 08:47:22.805','2016-11-02 08:47:22.805',0,''),('2503','purchase:1:7','8','35','errorendevent1',NULL,NULL,'总经理不同意','endEvent',NULL,'2016-11-02 08:47:22.805',NULL,NULL,''),('2504','purchase:1:7','8','35','boundaryerror1',NULL,NULL,'Error','boundaryError',NULL,'2016-11-02 08:47:22.805','2016-11-02 08:47:22.805',0,''),('2505','purchase:1:7','8','8','updateapply','2506',NULL,'调整申请','userTask','xiaomi','2016-11-02 08:47:22.821','2016-11-02 08:47:42.887',20066,''),('2507','purchase:1:7','8','8','exclusivegateway2',NULL,NULL,'是否重新申请','exclusiveGateway',NULL,'2016-11-02 08:47:42.887','2016-11-02 08:47:42.887',0,''),('2508','purchase:1:7','8','8','purchaseAuditi','2509',NULL,'采购经理审批','userTask','xiaomi','2016-11-02 08:47:42.887','2016-11-02 08:47:49.460',6573,''),('2511','purchase:1:7','8','8','exclusivegateway1',NULL,NULL,'Exclusive Gateway','exclusiveGateway',NULL,'2016-11-02 08:47:49.460','2016-11-02 08:47:49.460',0,''),('2513','purchase:1:7','8','2512','pay',NULL,NULL,'付费子流程','subProcess',NULL,'2016-11-02 08:47:49.461','2016-11-02 08:55:14.336',444875,''),('2514','purchase:1:7','8','2512','financeaudit','2515',NULL,'财务审批','userTask','xiaomi','2016-11-02 08:47:49.461','2016-11-02 08:47:53.292',3831,''),('2517','purchase:1:7','8','2512','exclusivegateway3',NULL,NULL,'Exclusive Gateway','exclusiveGateway',NULL,'2016-11-02 08:47:53.293','2016-11-02 08:47:53.293',0,''),('2518','purchase:1:7','8','2512','exclusivegateway4',NULL,NULL,'Exclusive Gateway','exclusiveGateway',NULL,'2016-11-02 08:47:53.293','2016-11-02 08:47:53.293',0,''),('2519','purchase:1:7','8','2512','manageraudit','2520',NULL,'总经理审批','userTask','xiaomi','2016-11-02 08:47:53.293','2016-11-02 08:47:57.461',4168,''),('2522','purchase:1:7','8','2512','exclusivegateway5',NULL,NULL,'Exclusive Gateway','exclusiveGateway',NULL,'2016-11-02 08:47:57.461','2016-11-02 08:47:57.461',0,''),('2523','purchase:1:7','8','2512','paymoney','2524',NULL,'出纳付款','userTask','xiaomi','2016-11-02 08:47:57.461','2016-11-02 08:55:14.330',436869,''),('26','purchase:1:7','8','18','boundaryerror1',NULL,NULL,'Error','boundaryError',NULL,'2016-11-02 08:32:31.958','2016-11-02 08:32:31.958',0,''),('27','purchase:1:7','8','8','updateapply','28',NULL,'调整申请','userTask','xiaomi','2016-11-02 08:32:31.960','2016-11-02 08:32:49.998',18038,''),('30','purchase:1:7','8','8','exclusivegateway2',NULL,NULL,'是否重新申请','exclusiveGateway',NULL,'2016-11-02 08:32:49.998','2016-11-02 08:32:49.998',0,''),('31','purchase:1:7','8','8','purchaseAuditi','32',NULL,'采购经理审批','userTask','xiaomi','2016-11-02 08:32:49.998','2016-11-02 08:33:01.914',11916,''),('34','purchase:1:7','8','8','exclusivegateway1',NULL,NULL,'Exclusive Gateway','exclusiveGateway',NULL,'2016-11-02 08:33:01.914','2016-11-02 08:33:01.914',0,''),('36','purchase:1:7','8','35','pay',NULL,NULL,'付费子流程','subProcess',NULL,'2016-11-02 08:33:01.914','2016-11-02 08:47:22.821',860907,''),('37','purchase:1:7','8','35','financeaudit','38',NULL,'财务审批','userTask','xiaomi','2016-11-02 08:33:01.915','2016-11-02 08:33:12.293',10378,''),('41','purchase:1:7','8','35','exclusivegateway3',NULL,NULL,'Exclusive Gateway','exclusiveGateway',NULL,'2016-11-02 08:33:12.293','2016-11-02 08:33:12.293',0,''),('42','purchase:1:7','8','35','exclusivegateway4',NULL,NULL,'Exclusive Gateway','exclusiveGateway',NULL,'2016-11-02 08:33:12.293','2016-11-02 08:33:12.293',0,''),('43','purchase:1:7','8','35','manageraudit','44',NULL,'总经理审批','userTask','xiaomi','2016-11-02 08:33:12.293','2016-11-02 08:47:22.805',850512,''),('49','purchase:1:7','46','46','startevent1',NULL,NULL,'Start','startEvent',NULL,'2016-11-02 08:39:09.819','2016-11-02 08:39:09.819',0,''),('5001','purchase:1:7','8','2512','endevent2',NULL,NULL,'End','endEvent',NULL,'2016-11-02 08:55:14.334','2016-11-02 08:55:14.334',0,''),('5002','purchase:1:7','8','8','receiveitem','5003',NULL,'收货确认','userTask','xiaomi','2016-11-02 08:55:14.338','2016-11-02 09:06:28.874',674536,''),('5004','purchase:1:7','46','56','endevent2',NULL,NULL,'End','endEvent',NULL,'2016-11-02 08:55:32.228','2016-11-02 08:55:32.228',0,''),('5005','purchase:1:7','46','46','receiveitem','5006',NULL,'收货确认','userTask','xiaomi','2016-11-02 08:55:32.231','2016-11-02 09:06:42.011',669780,''),('51','purchase:1:7','46','46','purchaseAuditi','52',NULL,'采购经理审批','userTask','xiaomi','2016-11-02 08:39:09.819','2016-11-02 08:39:15.826',6007,''),('55','purchase:1:7','46','46','exclusivegateway1',NULL,NULL,'Exclusive Gateway','exclusiveGateway',NULL,'2016-11-02 08:39:15.826','2016-11-02 08:39:15.826',0,''),('57','purchase:1:7','46','56','pay',NULL,NULL,'付费子流程','subProcess',NULL,'2016-11-02 08:39:15.842','2016-11-02 08:55:32.229',976387,''),('58','purchase:1:7','46','56','financeaudit','59',NULL,'财务审批','userTask','xiaomi','2016-11-02 08:39:15.842','2016-11-02 08:39:19.976',4134,''),('63','purchase:1:7','46','56','exclusivegateway3',NULL,NULL,'Exclusive Gateway','exclusiveGateway',NULL,'2016-11-02 08:39:19.976','2016-11-02 08:39:19.976',0,''),('64','purchase:1:7','46','56','exclusivegateway4',NULL,NULL,'Exclusive Gateway','exclusiveGateway',NULL,'2016-11-02 08:39:19.976','2016-11-02 08:39:19.976',0,''),('65','purchase:1:7','46','56','paymoney','66',NULL,'出纳付款','userTask','xiaomi','2016-11-02 08:39:19.976','2016-11-02 08:55:32.228',972252,''),('7501','purchase:1:7','8','8','endevent3',NULL,NULL,'End','endEvent',NULL,'2016-11-02 09:06:28.890','2016-11-02 09:06:28.890',0,''),('7502','purchase:1:7','46','46','endevent3',NULL,NULL,'End','endEvent',NULL,'2016-11-02 09:06:42.011','2016-11-02 09:06:42.011',0,'');
/*!40000 ALTER TABLE `act_hi_actinst` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_hi_attachment`
--

DROP TABLE IF EXISTS `act_hi_attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_hi_attachment` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `URL_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CONTENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TIME_` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_hi_attachment`
--

LOCK TABLES `act_hi_attachment` WRITE;
/*!40000 ALTER TABLE `act_hi_attachment` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_hi_attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_hi_comment`
--

DROP TABLE IF EXISTS `act_hi_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_hi_comment` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TIME_` datetime(3) NOT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACTION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `MESSAGE_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `FULL_MSG_` longblob,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_hi_comment`
--

LOCK TABLES `act_hi_comment` WRITE;
/*!40000 ALTER TABLE `act_hi_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_hi_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_hi_detail`
--

DROP TABLE IF EXISTS `act_hi_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_hi_detail` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VAR_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TIME_` datetime(3) NOT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_DETAIL_PROC_INST` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_DETAIL_ACT_INST` (`ACT_INST_ID_`),
  KEY `ACT_IDX_HI_DETAIL_TIME` (`TIME_`),
  KEY `ACT_IDX_HI_DETAIL_NAME` (`NAME_`),
  KEY `ACT_IDX_HI_DETAIL_TASK_ID` (`TASK_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_hi_detail`
--

LOCK TABLES `act_hi_detail` WRITE;
/*!40000 ALTER TABLE `act_hi_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_hi_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_hi_identitylink`
--

DROP TABLE IF EXISTS `act_hi_identitylink`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_hi_identitylink` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `GROUP_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_USER` (`USER_ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_TASK` (`TASK_ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_PROCINST` (`PROC_INST_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_hi_identitylink`
--

LOCK TABLES `act_hi_identitylink` WRITE;
/*!40000 ALTER TABLE `act_hi_identitylink` DISABLE KEYS */;
INSERT INTO `act_hi_identitylink` VALUES ('10',NULL,'starter','xiaomi',NULL,'8'),('10003',NULL,'starter','xiaomi',NULL,'10001'),('10008','部门经理','candidate',NULL,'10007',NULL),('12505','人事','candidate',NULL,'12504',NULL),('12508',NULL,'starter','xiaomi',NULL,'12506'),('12513','采购经理','candidate',NULL,'12512',NULL),('12520','财务管理员','candidate',NULL,'12519',NULL),('12527','总经理','candidate',NULL,'12526',NULL),('12530',NULL,'starter','xiaomi',NULL,'12528'),('12535','采购经理','candidate',NULL,'12534',NULL),('15','采购经理','candidate',NULL,'14',NULL),('22','财务管理员','candidate',NULL,'21',NULL),('2510','采购经理','candidate',NULL,'2509',NULL),('2516','财务管理员','candidate',NULL,'2515',NULL),('2521','总经理','candidate',NULL,'2520',NULL),('2525','出纳员','candidate',NULL,'2524',NULL),('33','采购经理','candidate',NULL,'32',NULL),('39','财务管理员','candidate',NULL,'38',NULL),('45','总经理','candidate',NULL,'44',NULL),('48',NULL,'starter','xiaomi',NULL,'46'),('53','采购经理','candidate',NULL,'52',NULL),('60','财务管理员','candidate',NULL,'59',NULL),('67','出纳员','candidate',NULL,'66',NULL);
/*!40000 ALTER TABLE `act_hi_identitylink` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_hi_procinst`
--

DROP TABLE IF EXISTS `act_hi_procinst`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_hi_procinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `BUSINESS_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `START_USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `END_ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUPER_PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `PROC_INST_ID_` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_PRO_INST_END` (`END_TIME_`),
  KEY `ACT_IDX_HI_PRO_I_BUSKEY` (`BUSINESS_KEY_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_hi_procinst`
--

LOCK TABLES `act_hi_procinst` WRITE;
/*!40000 ALTER TABLE `act_hi_procinst` DISABLE KEYS */;
INSERT INTO `act_hi_procinst` VALUES ('10001','10001','7','leave:1:6','2016-11-02 09:38:16.403',NULL,NULL,'xiaomi','startevent1',NULL,NULL,NULL,'',NULL),('12506','12506','20','purchase:1:7','2016-11-02 09:47:42.238',NULL,NULL,'xiaomi','startevent1',NULL,NULL,NULL,'',NULL),('12528','12528','21','purchase:1:7','2016-11-02 09:52:14.467',NULL,NULL,'xiaomi','startevent1',NULL,NULL,NULL,'',NULL),('46','46','19','purchase:1:7','2016-11-02 08:39:09.819','2016-11-02 09:06:42.011',1652192,'xiaomi','startevent1','endevent3',NULL,NULL,'',NULL),('8','8','18','purchase:1:7','2016-11-02 08:31:44.512','2016-11-02 09:06:28.890',2084378,'xiaomi','startevent1','endevent3',NULL,NULL,'',NULL);
/*!40000 ALTER TABLE `act_hi_procinst` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_hi_taskinst`
--

DROP TABLE IF EXISTS `act_hi_taskinst`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_hi_taskinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_DEF_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `CLAIM_TIME_` datetime(3) DEFAULT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `PRIORITY_` int(11) DEFAULT NULL,
  `DUE_DATE_` datetime(3) DEFAULT NULL,
  `FORM_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_TASK_INST_PROCINST` (`PROC_INST_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_hi_taskinst`
--

LOCK TABLES `act_hi_taskinst` WRITE;
/*!40000 ALTER TABLE `act_hi_taskinst` DISABLE KEYS */;
INSERT INTO `act_hi_taskinst` VALUES ('10007','leave:1:6','deptleaderaudit','10001','10001','部门领导审批',NULL,NULL,NULL,'xiaomi','2016-11-02 09:38:16.410','2016-11-02 09:46:06.985','2016-11-02 09:46:07.000',470590,'completed',50,NULL,NULL,NULL,''),('12504','leave:1:6','hraudit','10001','10001','人事审批',NULL,NULL,NULL,NULL,'2016-11-02 09:46:07.008',NULL,NULL,NULL,NULL,50,NULL,NULL,NULL,''),('12512','purchase:1:7','purchaseAuditi','12506','12506','采购经理审批',NULL,NULL,NULL,'xiaomi','2016-11-02 09:47:42.238','2016-11-02 09:47:50.582','2016-11-02 09:47:50.593',8355,'completed',50,NULL,NULL,NULL,''),('12519','purchase:1:7','financeaudit','12506','12516','财务审批',NULL,NULL,NULL,'xiaomi','2016-11-02 09:47:50.596','2016-11-02 09:47:57.461','2016-11-02 09:47:57.471',6875,'completed',50,NULL,NULL,NULL,''),('12526','purchase:1:7','manageraudit','12506','12516','总经理审批',NULL,NULL,NULL,NULL,'2016-11-02 09:47:57.472',NULL,NULL,NULL,NULL,50,NULL,NULL,NULL,''),('12534','purchase:1:7','purchaseAuditi','12528','12528','采购经理审批',NULL,NULL,NULL,NULL,'2016-11-02 09:52:14.467',NULL,NULL,NULL,NULL,50,NULL,NULL,NULL,''),('14','purchase:1:7','purchaseAuditi','8','8','采购经理审批',NULL,NULL,NULL,'xiaomi','2016-11-02 08:31:44.517','2016-11-02 08:32:05.083','2016-11-02 08:32:05.094',20577,'completed',50,NULL,NULL,NULL,''),('21','purchase:1:7','financeaudit','8','18','财务审批',NULL,NULL,NULL,'xiaomi','2016-11-02 08:32:05.097','2016-11-02 08:32:31.946','2016-11-02 08:32:31.954',26857,'completed',50,NULL,NULL,NULL,''),('2506','purchase:1:7','updateapply','8','8','调整申请',NULL,NULL,NULL,'xiaomi','2016-11-02 08:47:22.821','2016-11-02 08:47:42.879','2016-11-02 08:47:42.885',20064,'completed',50,NULL,NULL,NULL,''),('2509','purchase:1:7','purchaseAuditi','8','8','采购经理审批',NULL,NULL,NULL,'xiaomi','2016-11-02 08:47:42.887','2016-11-02 08:47:49.449','2016-11-02 08:47:49.459',6572,'completed',50,NULL,NULL,NULL,''),('2515','purchase:1:7','financeaudit','8','2512','财务审批',NULL,NULL,NULL,'xiaomi','2016-11-02 08:47:49.461','2016-11-02 08:47:53.275','2016-11-02 08:47:53.290',3829,'completed',50,NULL,NULL,NULL,''),('2520','purchase:1:7','manageraudit','8','2512','总经理审批',NULL,NULL,NULL,'xiaomi','2016-11-02 08:47:53.293','2016-11-02 08:47:57.451','2016-11-02 08:47:57.460',4167,'completed',50,NULL,NULL,NULL,''),('2524','purchase:1:7','paymoney','8','2512','出纳付款',NULL,NULL,NULL,'xiaomi','2016-11-02 08:47:57.461','2016-11-02 08:55:14.313','2016-11-02 08:55:14.322',436861,'completed',50,NULL,NULL,NULL,''),('28','purchase:1:7','updateapply','8','8','调整申请',NULL,NULL,NULL,'xiaomi','2016-11-02 08:32:31.960','2016-11-02 08:32:49.988','2016-11-02 08:32:49.997',18037,'completed',50,NULL,NULL,NULL,''),('32','purchase:1:7','purchaseAuditi','8','8','采购经理审批',NULL,NULL,NULL,'xiaomi','2016-11-02 08:32:49.998','2016-11-02 08:33:01.904','2016-11-02 08:33:01.912',11914,'completed',50,NULL,NULL,NULL,''),('38','purchase:1:7','financeaudit','8','35','财务审批',NULL,NULL,NULL,'xiaomi','2016-11-02 08:33:01.915','2016-11-02 08:33:12.280','2016-11-02 08:33:12.292',10377,'completed',50,NULL,NULL,NULL,''),('44','purchase:1:7','manageraudit','8','35','总经理审批',NULL,NULL,NULL,'xiaomi','2016-11-02 08:33:12.293','2016-11-02 08:47:22.790','2016-11-02 08:47:22.805',850512,'completed',50,NULL,NULL,NULL,''),('5003','purchase:1:7','receiveitem','8','8','收货确认',NULL,NULL,NULL,'xiaomi','2016-11-02 08:55:14.338','2016-11-02 09:06:28.795','2016-11-02 09:06:28.874',674536,'completed',50,NULL,NULL,NULL,''),('5006','purchase:1:7','receiveitem','46','46','收货确认',NULL,NULL,NULL,'xiaomi','2016-11-02 08:55:32.231','2016-11-02 09:06:41.995','2016-11-02 09:06:42.011',669780,'completed',50,NULL,NULL,NULL,''),('52','purchase:1:7','purchaseAuditi','46','46','采购经理审批',NULL,NULL,NULL,'xiaomi','2016-11-02 08:39:09.819','2016-11-02 08:39:15.826','2016-11-02 08:39:15.826',6007,'completed',50,NULL,NULL,NULL,''),('59','purchase:1:7','financeaudit','46','56','财务审批',NULL,NULL,NULL,'xiaomi','2016-11-02 08:39:15.842','2016-11-02 08:39:19.960','2016-11-02 08:39:19.976',4134,'completed',50,NULL,NULL,NULL,''),('66','purchase:1:7','paymoney','46','56','出纳付款',NULL,NULL,NULL,'xiaomi','2016-11-02 08:39:19.976','2016-11-02 08:55:32.219','2016-11-02 08:55:32.226',972250,'completed',50,NULL,NULL,NULL,'');
/*!40000 ALTER TABLE `act_hi_taskinst` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_hi_varinst`
--

DROP TABLE IF EXISTS `act_hi_varinst`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_hi_varinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VAR_TYPE_` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` datetime(3) DEFAULT NULL,
  `LAST_UPDATED_TIME_` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_PROCVAR_PROC_INST` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_PROCVAR_NAME_TYPE` (`NAME_`,`VAR_TYPE_`),
  KEY `ACT_IDX_HI_PROCVAR_TASK_ID` (`TASK_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_hi_varinst`
--

LOCK TABLES `act_hi_varinst` WRITE;
/*!40000 ALTER TABLE `act_hi_varinst` DISABLE KEYS */;
INSERT INTO `act_hi_varinst` VALUES ('10002','10001','10001',NULL,'${applyuserid}','string',0,NULL,NULL,NULL,'xiaomi',NULL,'2016-11-02 09:38:16.402','2016-11-02 09:38:16.402'),('10005','10001','10001',NULL,'applyuserid','string',0,NULL,NULL,NULL,'xiaomi',NULL,'2016-11-02 09:38:16.403','2016-11-02 09:38:16.403'),('12','8','8',NULL,'starter','string',1,NULL,NULL,NULL,'xiaomi',NULL,'2016-11-02 08:31:44.512','2016-11-02 09:06:28.890'),('12501','10001','10001',NULL,'deptleaderapprove','string',0,NULL,NULL,NULL,'true',NULL,'2016-11-02 09:46:06.997','2016-11-02 09:46:06.997'),('12507','12506','12506',NULL,'${starter}','string',0,NULL,NULL,NULL,'xiaomi',NULL,'2016-11-02 09:47:42.237','2016-11-02 09:47:42.237'),('12510','12506','12506',NULL,'starter','string',0,NULL,NULL,NULL,'xiaomi',NULL,'2016-11-02 09:47:42.238','2016-11-02 09:47:42.238'),('12514','12506','12506',NULL,'purchaseauditi','string',0,NULL,NULL,NULL,'true',NULL,'2016-11-02 09:47:50.591','2016-11-02 09:47:50.591'),('12521','12506','12506',NULL,'money','string',0,NULL,NULL,NULL,'20000',NULL,'2016-11-02 09:47:57.470','2016-11-02 09:47:57.470'),('12522','12506','12506',NULL,'finance','string',0,NULL,NULL,NULL,'true',NULL,'2016-11-02 09:47:57.470','2016-11-02 09:47:57.470'),('12529','12528','12528',NULL,'${starter}','string',0,NULL,NULL,NULL,'xiaomi',NULL,'2016-11-02 09:52:14.466','2016-11-02 09:52:14.466'),('12532','12528','12528',NULL,'starter','string',0,NULL,NULL,NULL,'xiaomi',NULL,'2016-11-02 09:52:14.467','2016-11-02 09:52:14.467'),('16','8','8',NULL,'purchaseauditi','string',3,NULL,NULL,NULL,'true',NULL,'2016-11-02 08:32:05.092','2016-11-02 09:06:28.890'),('23','8','8',NULL,'finance','string',3,NULL,NULL,NULL,'true',NULL,'2016-11-02 08:32:31.953','2016-11-02 09:06:28.890'),('2501','8','8',NULL,'manager','string',2,NULL,NULL,NULL,'true',NULL,'2016-11-02 08:47:22.805','2016-11-02 09:06:28.890'),('29','8','8',NULL,'updateapply','boolean',2,NULL,NULL,1,NULL,NULL,'2016-11-02 08:32:49.994','2016-11-02 09:06:28.890'),('40','8','8',NULL,'money','string',2,NULL,NULL,NULL,'20000',NULL,'2016-11-02 08:33:12.290','2016-11-02 09:06:28.890'),('47','46','46',NULL,'${starter}','string',1,NULL,NULL,NULL,'xiaomi',NULL,'2016-11-02 08:39:09.819','2016-11-02 09:06:42.011'),('50','46','46',NULL,'starter','string',1,NULL,NULL,NULL,'xiaomi',NULL,'2016-11-02 08:39:09.819','2016-11-02 09:06:42.011'),('54','46','46',NULL,'purchaseauditi','string',1,NULL,NULL,NULL,'true',NULL,'2016-11-02 08:39:15.826','2016-11-02 09:06:42.011'),('61','46','46',NULL,'money','string',1,NULL,NULL,NULL,'9',NULL,'2016-11-02 08:39:19.960','2016-11-02 09:06:42.011'),('62','46','46',NULL,'finance','string',1,NULL,NULL,NULL,'true',NULL,'2016-11-02 08:39:19.960','2016-11-02 09:06:42.011'),('9','8','8',NULL,'${starter}','string',1,NULL,NULL,NULL,'xiaomi',NULL,'2016-11-02 08:31:44.510','2016-11-02 09:06:28.890');
/*!40000 ALTER TABLE `act_hi_varinst` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_id_group`
--

DROP TABLE IF EXISTS `act_id_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_id_group` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_id_group`
--

LOCK TABLES `act_id_group` WRITE;
/*!40000 ALTER TABLE `act_id_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_id_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_id_info`
--

DROP TABLE IF EXISTS `act_id_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_id_info` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `USER_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `VALUE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PASSWORD_` longblob,
  `PARENT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_id_info`
--

LOCK TABLES `act_id_info` WRITE;
/*!40000 ALTER TABLE `act_id_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_id_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_id_membership`
--

DROP TABLE IF EXISTS `act_id_membership`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_id_membership` (
  `USER_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `GROUP_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`USER_ID_`,`GROUP_ID_`),
  KEY `ACT_FK_MEMB_GROUP` (`GROUP_ID_`),
  CONSTRAINT `ACT_FK_MEMB_GROUP` FOREIGN KEY (`GROUP_ID_`) REFERENCES `act_id_group` (`ID_`),
  CONSTRAINT `ACT_FK_MEMB_USER` FOREIGN KEY (`USER_ID_`) REFERENCES `act_id_user` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_id_membership`
--

LOCK TABLES `act_id_membership` WRITE;
/*!40000 ALTER TABLE `act_id_membership` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_id_membership` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_id_user`
--

DROP TABLE IF EXISTS `act_id_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_id_user` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `FIRST_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LAST_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EMAIL_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PWD_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PICTURE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_id_user`
--

LOCK TABLES `act_id_user` WRITE;
/*!40000 ALTER TABLE `act_id_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_id_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_re_deployment`
--

DROP TABLE IF EXISTS `act_re_deployment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_re_deployment` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `DEPLOY_TIME_` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_re_deployment`
--

LOCK TABLES `act_re_deployment` WRITE;
/*!40000 ALTER TABLE `act_re_deployment` DISABLE KEYS */;
INSERT INTO `act_re_deployment` VALUES ('1','SpringAutoDeployment',NULL,'','2016-11-02 00:31:12.274');
/*!40000 ALTER TABLE `act_re_deployment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_re_model`
--

DROP TABLE IF EXISTS `act_re_model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_re_model` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LAST_UPDATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `VERSION_` int(11) DEFAULT NULL,
  `META_INFO_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EDITOR_SOURCE_VALUE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EDITOR_SOURCE_EXTRA_VALUE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_MODEL_SOURCE` (`EDITOR_SOURCE_VALUE_ID_`),
  KEY `ACT_FK_MODEL_SOURCE_EXTRA` (`EDITOR_SOURCE_EXTRA_VALUE_ID_`),
  KEY `ACT_FK_MODEL_DEPLOYMENT` (`DEPLOYMENT_ID_`),
  CONSTRAINT `ACT_FK_MODEL_DEPLOYMENT` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `act_re_deployment` (`ID_`),
  CONSTRAINT `ACT_FK_MODEL_SOURCE` FOREIGN KEY (`EDITOR_SOURCE_VALUE_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_MODEL_SOURCE_EXTRA` FOREIGN KEY (`EDITOR_SOURCE_EXTRA_VALUE_ID_`) REFERENCES `act_ge_bytearray` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_re_model`
--

LOCK TABLES `act_re_model` WRITE;
/*!40000 ALTER TABLE `act_re_model` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_re_model` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_re_procdef`
--

DROP TABLE IF EXISTS `act_re_procdef`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_re_procdef` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VERSION_` int(11) NOT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `RESOURCE_NAME_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DGRM_RESOURCE_NAME_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `HAS_START_FORM_KEY_` tinyint(4) DEFAULT NULL,
  `HAS_GRAPHICAL_NOTATION_` tinyint(4) DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `ACT_UNIQ_PROCDEF` (`KEY_`,`VERSION_`,`TENANT_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_re_procdef`
--

LOCK TABLES `act_re_procdef` WRITE;
/*!40000 ALTER TABLE `act_re_procdef` DISABLE KEYS */;
INSERT INTO `act_re_procdef` VALUES ('leave:1:6',1,'http://www.activiti.org/test','My process','leave',1,'1','D:\\apache-tomcat-8.0.36\\webapps\\Spring-activiti\\WEB-INF\\classes\\process\\leave.bpmn','D:\\apache-tomcat-8.0.36\\webapps\\Spring-activiti\\WEB-INF\\classes\\process\\leave.leave.png',NULL,0,1,1,''),('purchase:1:7',1,'http://www.activiti.org/test','purchaseprocess','purchase',1,'1','D:\\apache-tomcat-8.0.36\\webapps\\Spring-activiti\\WEB-INF\\classes\\process\\purchase.bpmn','D:\\apache-tomcat-8.0.36\\webapps\\Spring-activiti\\WEB-INF\\classes\\process\\purchase.purchase.png',NULL,0,1,1,'');
/*!40000 ALTER TABLE `act_re_procdef` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ru_event_subscr`
--

DROP TABLE IF EXISTS `act_ru_event_subscr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_ru_event_subscr` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `EVENT_TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EVENT_NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACTIVITY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CONFIGURATION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATED_` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_EVENT_SUBSCR_CONFIG_` (`CONFIGURATION_`),
  KEY `ACT_FK_EVENT_EXEC` (`EXECUTION_ID_`),
  CONSTRAINT `ACT_FK_EVENT_EXEC` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ru_event_subscr`
--

LOCK TABLES `act_ru_event_subscr` WRITE;
/*!40000 ALTER TABLE `act_ru_event_subscr` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_ru_event_subscr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ru_execution`
--

DROP TABLE IF EXISTS `act_ru_execution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_ru_execution` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BUSINESS_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `SUPER_EXEC_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `IS_ACTIVE_` tinyint(4) DEFAULT NULL,
  `IS_CONCURRENT_` tinyint(4) DEFAULT NULL,
  `IS_SCOPE_` tinyint(4) DEFAULT NULL,
  `IS_EVENT_SCOPE_` tinyint(4) DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `CACHED_ENT_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LOCK_TIME_` timestamp(3) NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_EXEC_BUSKEY` (`BUSINESS_KEY_`),
  KEY `ACT_FK_EXE_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_EXE_PARENT` (`PARENT_ID_`),
  KEY `ACT_FK_EXE_SUPER` (`SUPER_EXEC_`),
  KEY `ACT_FK_EXE_PROCDEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_EXE_PARENT` FOREIGN KEY (`PARENT_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_EXE_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`),
  CONSTRAINT `ACT_FK_EXE_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ACT_FK_EXE_SUPER` FOREIGN KEY (`SUPER_EXEC_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ru_execution`
--

LOCK TABLES `act_ru_execution` WRITE;
/*!40000 ALTER TABLE `act_ru_execution` DISABLE KEYS */;
INSERT INTO `act_ru_execution` VALUES ('10001',2,'10001','7',NULL,'leave:1:6',NULL,'hraudit',1,0,1,0,1,2,'',NULL,NULL),('12506',2,'12506','20',NULL,'purchase:1:7',NULL,NULL,0,0,1,0,1,0,'',NULL,NULL),('12516',2,'12506',NULL,'12506','purchase:1:7',NULL,'manageraudit',1,0,1,0,1,2,'',NULL,NULL),('12528',1,'12528','21',NULL,'purchase:1:7',NULL,'purchaseAuditi',1,0,1,0,1,2,'',NULL,NULL);
/*!40000 ALTER TABLE `act_ru_execution` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ru_identitylink`
--

DROP TABLE IF EXISTS `act_ru_identitylink`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_ru_identitylink` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `GROUP_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_IDENT_LNK_USER` (`USER_ID_`),
  KEY `ACT_IDX_IDENT_LNK_GROUP` (`GROUP_ID_`),
  KEY `ACT_IDX_ATHRZ_PROCEDEF` (`PROC_DEF_ID_`),
  KEY `ACT_FK_TSKASS_TASK` (`TASK_ID_`),
  KEY `ACT_FK_IDL_PROCINST` (`PROC_INST_ID_`),
  CONSTRAINT `ACT_FK_ATHRZ_PROCEDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`),
  CONSTRAINT `ACT_FK_IDL_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_TSKASS_TASK` FOREIGN KEY (`TASK_ID_`) REFERENCES `act_ru_task` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ru_identitylink`
--

LOCK TABLES `act_ru_identitylink` WRITE;
/*!40000 ALTER TABLE `act_ru_identitylink` DISABLE KEYS */;
INSERT INTO `act_ru_identitylink` VALUES ('10003',1,NULL,'starter','xiaomi',NULL,'10001',NULL),('12505',1,'人事','candidate',NULL,'12504',NULL,NULL),('12508',1,NULL,'starter','xiaomi',NULL,'12506',NULL),('12527',1,'总经理','candidate',NULL,'12526',NULL,NULL),('12530',1,NULL,'starter','xiaomi',NULL,'12528',NULL),('12535',1,'采购经理','candidate',NULL,'12534',NULL,NULL);
/*!40000 ALTER TABLE `act_ru_identitylink` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ru_job`
--

DROP TABLE IF EXISTS `act_ru_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_ru_job` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `LOCK_EXP_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LOCK_OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXCLUSIVE_` tinyint(1) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `RETRIES_` int(11) DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DUEDATE_` timestamp(3) NULL DEFAULT NULL,
  `REPEAT_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_JOB_EXCEPTION` (`EXCEPTION_STACK_ID_`),
  CONSTRAINT `ACT_FK_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `act_ge_bytearray` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ru_job`
--

LOCK TABLES `act_ru_job` WRITE;
/*!40000 ALTER TABLE `act_ru_job` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_ru_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ru_task`
--

DROP TABLE IF EXISTS `act_ru_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_ru_task` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TASK_DEF_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DELEGATION_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PRIORITY_` int(11) DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `DUE_DATE_` datetime(3) DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `FORM_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_TASK_CREATE` (`CREATE_TIME_`),
  KEY `ACT_FK_TASK_EXE` (`EXECUTION_ID_`),
  KEY `ACT_FK_TASK_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_TASK_PROCDEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_TASK_EXE` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_TASK_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`),
  CONSTRAINT `ACT_FK_TASK_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ru_task`
--

LOCK TABLES `act_ru_task` WRITE;
/*!40000 ALTER TABLE `act_ru_task` DISABLE KEYS */;
INSERT INTO `act_ru_task` VALUES ('12504',1,'10001','10001','leave:1:6','人事审批',NULL,NULL,'hraudit',NULL,NULL,NULL,50,'2016-11-02 01:46:07.008',NULL,NULL,1,'',NULL),('12526',1,'12516','12506','purchase:1:7','总经理审批',NULL,NULL,'manageraudit',NULL,NULL,NULL,50,'2016-11-02 01:47:57.472',NULL,NULL,1,'',NULL),('12534',1,'12528','12528','purchase:1:7','采购经理审批',NULL,NULL,'purchaseAuditi',NULL,NULL,NULL,50,'2016-11-02 01:52:14.467',NULL,NULL,1,'',NULL);
/*!40000 ALTER TABLE `act_ru_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ru_variable`
--

DROP TABLE IF EXISTS `act_ru_variable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_ru_variable` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_VARIABLE_TASK_ID` (`TASK_ID_`),
  KEY `ACT_FK_VAR_EXE` (`EXECUTION_ID_`),
  KEY `ACT_FK_VAR_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_VAR_BYTEARRAY` (`BYTEARRAY_ID_`),
  CONSTRAINT `ACT_FK_VAR_BYTEARRAY` FOREIGN KEY (`BYTEARRAY_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_VAR_EXE` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_VAR_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ru_variable`
--

LOCK TABLES `act_ru_variable` WRITE;
/*!40000 ALTER TABLE `act_ru_variable` DISABLE KEYS */;
INSERT INTO `act_ru_variable` VALUES ('10002',1,'string','${applyuserid}','10001','10001',NULL,NULL,NULL,NULL,'xiaomi',NULL),('10005',1,'string','applyuserid','10001','10001',NULL,NULL,NULL,NULL,'xiaomi',NULL),('12501',1,'string','deptleaderapprove','10001','10001',NULL,NULL,NULL,NULL,'true',NULL),('12507',1,'string','${starter}','12506','12506',NULL,NULL,NULL,NULL,'xiaomi',NULL),('12510',1,'string','starter','12506','12506',NULL,NULL,NULL,NULL,'xiaomi',NULL),('12514',1,'string','purchaseauditi','12506','12506',NULL,NULL,NULL,NULL,'true',NULL),('12521',1,'string','money','12506','12506',NULL,NULL,NULL,NULL,'20000',NULL),('12522',1,'string','finance','12506','12506',NULL,NULL,NULL,NULL,'true',NULL),('12529',1,'string','${starter}','12528','12528',NULL,NULL,NULL,NULL,'xiaomi',NULL),('12532',1,'string','starter','12528','12528',NULL,NULL,NULL,NULL,'xiaomi',NULL);
/*!40000 ALTER TABLE `act_ru_variable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `leaveapply`
--

DROP TABLE IF EXISTS `leaveapply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `leaveapply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `process_instance_id` varchar(45) DEFAULT NULL,
  `user_id` varchar(20) DEFAULT NULL,
  `start_time` varchar(45) DEFAULT NULL,
  `end_time` varchar(45) DEFAULT NULL,
  `leave_type` varchar(45) DEFAULT NULL,
  `reason` varchar(400) DEFAULT NULL,
  `apply_time` varchar(100) DEFAULT NULL,
  `reality_start_time` varchar(45) DEFAULT NULL,
  `reality_end_time` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leaveapply`
--

LOCK TABLES `leaveapply` WRITE;
/*!40000 ALTER TABLE `leaveapply` DISABLE KEYS */;
INSERT INTO `leaveapply` VALUES (7,'10001','xiaomi','2016-11-09','2016-11-17','病假','肚子痛','Wed Nov 02 09:38:16 CST 2016',NULL,NULL);
/*!40000 ALTER TABLE `leaveapply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `permissionname` varchar(45) NOT NULL,
  PRIMARY KEY (`pid`),
  UNIQUE KEY `permissionname_UNIQUE` (`permissionname`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (2,'人事审批'),(9,'出纳付款'),(8,'总经理审批'),(3,'财务审批'),(1,'部门领导审批'),(15,'采购审批');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase`
--

DROP TABLE IF EXISTS `purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchase` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `itemlist` text NOT NULL,
  `total` float NOT NULL,
  `applytime` varchar(45) DEFAULT NULL,
  `applyer` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase`
--

LOCK TABLES `purchase` WRITE;
/*!40000 ALTER TABLE `purchase` DISABLE KEYS */;
INSERT INTO `purchase` VALUES (18,'买个灯笼',20000,'2016-11-02 08:31:44.501','xiaomi'),(19,'买个月亮							',9,'2016-11-02 08:39:09.803','xiaomi'),(20,'1采购忘情水一瓶	\r\n2白云一朵						',20000,'2016-11-02 09:47:42.229','xiaomi'),(21,'1.给我买个鸡蛋							',333,'2016-11-02 09:52:14.46','xiaomi');
/*!40000 ALTER TABLE `purchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(45) NOT NULL,
  PRIMARY KEY (`rid`),
  UNIQUE KEY `rolename_UNIQUE` (`rolename`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (9,'人事'),(10,'出纳员'),(11,'总经理'),(16,'财务管理员'),(1,'部门经理'),(13,'采购经理');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permission`
--

DROP TABLE IF EXISTS `role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_permission` (
  `rpid` int(11) NOT NULL AUTO_INCREMENT,
  `roleid` int(11) NOT NULL,
  `permissionid` int(11) NOT NULL,
  PRIMARY KEY (`rpid`),
  KEY `a_idx` (`roleid`),
  KEY `b_idx` (`permissionid`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission`
--

LOCK TABLES `role_permission` WRITE;
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
INSERT INTO `role_permission` VALUES (24,1,3),(25,1,1),(27,9,2),(28,10,9),(34,11,2),(35,11,8),(36,11,1),(37,13,15),(39,16,3);
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) COLLATE utf8_bin NOT NULL,
  `password` varchar(45) COLLATE utf8_bin NOT NULL,
  `tel` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (31,'xiaomi','1234','110',20),(33,'jon','1234','123',23),(34,'xiaocai','1234','111',32);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `urid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `roleid` int(11) NOT NULL,
  PRIMARY KEY (`urid`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (47,33,9),(48,33,1),(81,31,9),(82,31,10),(83,31,16),(84,31,1),(85,31,13);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-02 10:40:58
