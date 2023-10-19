-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ttswap
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `gator_apply_result`
--

DROP TABLE IF EXISTS `gator_apply_result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `gator_apply_result` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `jc` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `qc` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `gj` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `gwdz` varchar(50) DEFAULT NULL,
  `ip` varchar(45) DEFAULT NULL,
  `qbdz` varchar(45) DEFAULT NULL,
  `tb` varchar(50) DEFAULT NULL,
  `zt` int(11) DEFAULT '0' COMMENT '0未审核\\n1已通过\\n2未通过',
  `yj` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '审核意见',
  `shy` varchar(45) DEFAULT NULL COMMENT '审核人',
  `ljsc` int(11) DEFAULT '0',
  `cjsj` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `gxsj` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='门户申请结果';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `gator_info`
--

DROP TABLE IF EXISTS `gator_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `gator_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `jc` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '简称',
  `qc` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '全称',
  `gj` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '国家',
  `gwdz` varchar(50) DEFAULT NULL COMMENT '官网地址',
  `ip` varchar(45) DEFAULT NULL COMMENT '官网IP',
  `qbdz` varchar(45) DEFAULT NULL COMMENT '钱包地址',
  `tb` varchar(50) DEFAULT NULL COMMENT '图标名称',
  `zt` int(11) DEFAULT '0' COMMENT '0待审核1通过（解冻）2未通过3冻结',
  `cjsj` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `gxsj` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `ljsc` int(11) DEFAULT '0',
  `hzhb` int(11) DEFAULT '1' COMMENT '合作伙伴 0不是 1是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='门户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `gator_marketor`
--

DROP TABLE IF EXISTS `gator_marketor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `gator_marketor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `zh` varchar(45) DEFAULT NULL,
  `mc` varchar(45) DEFAULT NULL,
  `ljsc` int(11) DEFAULT '0',
  `cjsj` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='门户管理员';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `gator_otc_income_draw_record`
--

DROP TABLE IF EXISTS `gator_otc_income_draw_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `gator_otc_income_draw_record` (
  `bzjid` int(11) NOT NULL COMMENT '保证金id',
  `sxfje` double DEFAULT '0' COMMENT '手续费金额',
  `wgfje` double DEFAULT '0' COMMENT '违规费金额',
  `czr` varchar(45) DEFAULT NULL COMMENT '操作账号',
  `cjsj` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `fbid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='门户OTC收益提取记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `icon_info`
--

DROP TABLE IF EXISTS `icon_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `icon_info` (
  `tbid` varchar(50) NOT NULL,
  `mc` varchar(50) NOT NULL COMMENT '图片名称',
  `fl` int(11) DEFAULT NULL COMMENT '分类\\\\n0 虚拟币\\\\n1 物品\\\\n2 logo、\\n 3门户图标',
  `cjsj` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `xgsj` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`tbid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图标信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `legalcurrencylist`
--

DROP TABLE IF EXISTS `legalcurrencylist`;
/*!50001 DROP VIEW IF EXISTS `legalcurrencylist`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `legalcurrencylist` AS SELECT 
 1 AS `fbid`,
 1 AS `name`,
 1 AS `fh`,
 1 AS `dm`,
 1 AS `bzjed`,
 1 AS `zt`,
 1 AS `sfsxf`,
 1 AS `fl`,
 1 AS `cjsj`,
 1 AS `zffs`,
 1 AS `zffsID`,
 1 AS `zffslb`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `otc_coin_dealer`
--

DROP TABLE IF EXISTS `otc_coin_dealer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `otc_coin_dealer` (
  `bsid` int(11) NOT NULL AUTO_INCREMENT,
  `bsqb` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '币商钱包地址',
  `jc` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '简称',
  `qc` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '全称',
  `dptp` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图片地址',
  `lxfs` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '联系方式',
  `yysj` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '上班时间',
  `zt` int(11) DEFAULT '0' COMMENT '状态\\\\\\\\n0冻结\\\\\\\\n1流通\\n2 待审核',
  `cjsj` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gxsj` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `ljsc` int(11) DEFAULT '0',
  PRIMARY KEY (`bsid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='币商信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `otc_coin_dealer_apply`
--

DROP TABLE IF EXISTS `otc_coin_dealer_apply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `otc_coin_dealer_apply` (
  `sqid` int(11) NOT NULL AUTO_INCREMENT,
  `bsqb` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '币商钱包地址',
  `sqlb` int(11) DEFAULT NULL COMMENT '申请类型\\n1 新增币商\\n2 新增法币\\n3 续交保证金\\n4 退保证金',
  `fbid` int(11) DEFAULT NULL COMMENT '法币id',
  `zfr` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '支付人',
  `skzhid` int(11) DEFAULT NULL COMMENT '收款账户id',
  `zt` int(11) DEFAULT '0' COMMENT '状态\\n0 待审核\\n1 通过\\n2 未通过',
  `shyj` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '审核意见',
  `sqsj` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `bgsj` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `ljsc` int(11) DEFAULT '0',
  `bzj` double DEFAULT NULL COMMENT '保证金',
  `bsid` int(11) DEFAULT NULL COMMENT '币商id',
  `jc` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '简称',
  `qc` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '全称',
  `lxfs` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '联系方式',
  `yysj` varchar(45) DEFAULT NULL COMMENT '营业时间',
  `dptp` varchar(50) DEFAULT NULL COMMENT '店铺图片',
  `bzjid` int(11) DEFAULT NULL COMMENT '保证金id',
  PRIMARY KEY (`sqid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='币商申请表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `otc_coin_order`
--

DROP TABLE IF EXISTS `otc_coin_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `otc_coin_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tb` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图标地址',
  `hydz` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '合约地址',
  `mc` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  `lz` int(11) DEFAULT NULL COMMENT '类型\n0 虚拟币\n1 物品',
  `cjsj` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `ljsc` int(11) DEFAULT '0',
  `zt` int(11) DEFAULT '0',
  `lx` int(11) DEFAULT NULL,
  `ly` int(11) DEFAULT NULL COMMENT '来源\n0 市场\n1 门户',
  `qc` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '全称',
  `xgsj` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `sxfjb` double DEFAULT NULL COMMENT '手续费级别',
  `cjr` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='虚拟币、物品图标对应表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `otc_commodity`
--

DROP TABLE IF EXISTS `otc_commodity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `otc_commodity` (
  `spid` int(11) NOT NULL AUTO_INCREMENT,
  `fbid` int(11) DEFAULT NULL COMMENT '法币id',
  `fbmc` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '法币名称',
  `bzhy` varchar(45) DEFAULT NULL COMMENT '币种合约地址',
  `bzmc` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '币种名称',
  `xnblj` varchar(45) DEFAULT NULL COMMENT '币种外部链接',
  `bzdj` double DEFAULT '0' COMMENT '币种单价',
  `bzsl` double DEFAULT '0' COMMENT '币种数量',
  `zgxe` double DEFAULT '0' COMMENT '单笔最高成交额限制',
  `zdxe` double DEFAULT '0' COMMENT '单笔最低成交额限制',
  `jzsj` timestamp NULL DEFAULT NULL COMMENT '截止时间',
  `cjsl` double DEFAULT '0' COMMENT '当前成交数量',
  `sdsl` double DEFAULT '0' COMMENT '当前被锁定数量',
  `kcsl` double DEFAULT '0' COMMENT '当前库存数量',
  `cslx` int(11) DEFAULT '0' COMMENT '商品出售类型\\n0 买入\\n1 卖出',
  `zt` int(11) DEFAULT '1' COMMENT '状态\\\\n1 上架\\\\n0 下架',
  `ljsc` int(11) DEFAULT '0',
  `cjsj` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `gxsj` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `zffsid` varchar(45) DEFAULT NULL COMMENT '支付方式id',
  `cjr` varchar(45) DEFAULT NULL COMMENT '创建人，币商钱包地址',
  PRIMARY KEY (`spid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='商品';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `otc_deposit`
--

DROP TABLE IF EXISTS `otc_deposit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `otc_deposit` (
  `bzjid` int(11) NOT NULL AUTO_INCREMENT,
  `bsid` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '币商id',
  `fbid` int(11) DEFAULT NULL COMMENT '法币id',
  `bzj` double DEFAULT '0' COMMENT '保证金缴纳金额',
  `sded` double DEFAULT '0' COMMENT '保证金锁定金额',
  `kced` double DEFAULT '0' COMMENT '被扣额度',
  `ksyed` double DEFAULT '0' COMMENT '可使用额度',
  `zt` int(11) DEFAULT '0' COMMENT '状态\\\\\\\\n0冻结\\\\\\\\n1流通',
  `ljsc` int(11) DEFAULT '0' COMMENT '逻辑删除',
  `cjsj` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `xgsj` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `kcsxf` double DEFAULT '0' COMMENT '扣除手续费',
  `tqsxf` double DEFAULT '0' COMMENT '提取手续费金额',
  `kcwgfy` double DEFAULT '0' COMMENT '扣除违规费用',
  `tqwgfy` double DEFAULT '0' COMMENT '提取违规费',
  PRIMARY KEY (`bzjid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='保证金信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `otc_deposit_history`
--

DROP TABLE IF EXISTS `otc_deposit_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `otc_deposit_history` (
  `bzjid` int(11) NOT NULL,
  `bsid` varchar(45) DEFAULT NULL COMMENT '币商地址',
  `fbid` int(11) DEFAULT NULL COMMENT '法币',
  `bzj` double DEFAULT NULL COMMENT '当前保证金',
  `sded` double DEFAULT NULL COMMENT '已锁定金额',
  `sdyy` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '锁定原因',
  `kced` double DEFAULT NULL COMMENT '扣除额度',
  `kcyy` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '原因',
  `jsed` double DEFAULT NULL COMMENT '解锁额度',
  `ksyed` double DEFAULT NULL COMMENT '可使用额度',
  `jned` double DEFAULT NULL COMMENT '缴纳额度',
  `ljsc` int(11) DEFAULT '0',
  `cjsj` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `zt` int(11) DEFAULT '0',
  `tcbzj` double DEFAULT NULL COMMENT '退还保证金',
  `kcsxf` double DEFAULT NULL COMMENT '手续费',
  `tqsxf` double DEFAULT '0' COMMENT '提取手续费金额'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='保证金历史记录信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `otc_legal_currency`
--

DROP TABLE IF EXISTS `otc_legal_currency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `otc_legal_currency` (
  `fbid` int(11) NOT NULL AUTO_INCREMENT,
  `tb` varchar(45) DEFAULT NULL COMMENT '图标',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  `fh` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '符号',
  `dm` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '代码',
  `bzjed` double DEFAULT NULL COMMENT '保证金额度',
  `zt` int(11) DEFAULT '0' COMMENT '状态\\n0冻结\\n1流通',
  `cjsj` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `bgsj` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `ljsc` int(11) DEFAULT '0',
  `sfsxf` int(11) DEFAULT '0' COMMENT '是否收取手续费\\\\n0不收\\\\n1收',
  `fl` double DEFAULT '0' COMMENT '费率',
  PRIMARY KEY (`fbid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='法币信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `otc_legal_currency_payment_method`
--

DROP TABLE IF EXISTS `otc_legal_currency_payment_method`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `otc_legal_currency_payment_method` (
  `fbid` int(11) DEFAULT NULL COMMENT '法币id',
  `zffsid` int(11) DEFAULT NULL COMMENT '支付方式id',
  `spid` int(11) DEFAULT NULL COMMENT '商品id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='法币、支付方式、商品映射表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `otc_merchant_legalcurrency`
--

DROP TABLE IF EXISTS `otc_merchant_legalcurrency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `otc_merchant_legalcurrency` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fbid` int(11) DEFAULT NULL,
  `bzjid` int(11) DEFAULT NULL,
  `zt` int(11) DEFAULT '0',
  `cjsj` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `gxsj` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `ljsc` int(11) DEFAULT '0',
  `bsid` varchar(45) DEFAULT NULL COMMENT '币商钱包',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='币商法币表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `otc_order`
--

DROP TABLE IF EXISTS `otc_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `otc_order` (
  `ddid` int(11) NOT NULL,
  `spid` int(11) DEFAULT NULL COMMENT '商品id',
  `bsdz` varchar(45) DEFAULT NULL COMMENT '币商地址',
  `yhdz` varchar(45) DEFAULT NULL COMMENT '用户地址',
  `zt` int(11) DEFAULT '0' COMMENT '状态',
  `bzmc` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '币种名称',
  `sl` double DEFAULT NULL COMMENT '币种数量',
  `fbmc` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '法币名称',
  `je` double DEFAULT NULL COMMENT '金额',
  `ddlx` int(11) DEFAULT NULL COMMENT '订单类型\\n0 买入\\n1 卖出',
  `shzt` int(11) DEFAULT NULL COMMENT '审核状态\n0 待审核\n1 审核完成',
  `ljsc` int(11) DEFAULT '0',
  `cjsj` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '交易时间',
  `gxsj` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '状态更新时间',
  `zdsx` timestamp NULL DEFAULT NULL COMMENT '最大限制时间',
  `bzhy` varchar(45) DEFAULT NULL COMMENT '币种合约',
  `shyj` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '审核意见',
  `skr` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '收款人',
  `skzh` int(11) DEFAULT NULL COMMENT '收款账户id',
  `dkzh` int(11) DEFAULT NULL COMMENT '打款账户id',
  `hxz` varchar(100) DEFAULT NULL COMMENT '支付币种交易哈希值',
  `dkr` varchar(20) DEFAULT NULL,
  `fbid` int(11) DEFAULT NULL COMMENT '法币id',
  `sxf` double DEFAULT '0' COMMENT '手续费',
  `fl` double DEFAULT '0' COMMENT '费率',
  PRIMARY KEY (`ddid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `otc_order_change`
--

DROP TABLE IF EXISTS `otc_order_change`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `otc_order_change` (
  `spid` int(11) DEFAULT NULL COMMENT '商品id',
  `ddid` int(11) NOT NULL COMMENT '订单id',
  `ddzt` int(11) DEFAULT NULL COMMENT '订单状态',
  `js` int(11) DEFAULT NULL COMMENT '角色\n1 币商\n2 用户',
  `dz` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '地址',
  `ztsj` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '状态更新时间',
  `sfsj` timestamp NULL DEFAULT NULL COMMENT '申述时间',
  `sfyy` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '申诉原因',
  `ljsc` int(11) DEFAULT '0',
  `cjsj` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `ztbgyy` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '状态变更原因'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单变更信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `otc_payment_account`
--

DROP TABLE IF EXISTS `otc_payment_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `otc_payment_account` (
  `zhid` int(11) NOT NULL AUTO_INCREMENT,
  `fbid` int(11) DEFAULT NULL COMMENT '法币id',
  `zffsid` int(11) DEFAULT NULL COMMENT '支付方式id',
  `zhmc` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '账户名称',
  `khmc` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '开户姓名',
  `zhm` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '支付账户或收款码地址',
  `cjr` varchar(45) DEFAULT NULL COMMENT '创建人',
  `js` int(11) DEFAULT NULL COMMENT '角色\n0 门户\n1 币商\n2 用户',
  `dz` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '交易地址',
  `lxfs` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '联系方式',
  `lxr` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '联系人',
  `yysj` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '营业时间段',
  `wztp` varchar(45) DEFAULT NULL COMMENT '交易位置图片',
  `ljsc` int(11) DEFAULT '0',
  `cjsj` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `zt` int(11) DEFAULT '0',
  `zflx` int(11) DEFAULT NULL COMMENT '支付类型',
  `skm` varchar(45) DEFAULT NULL COMMENT '收款码',
  `bgsj` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`zhid`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='收款账户配置信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `otc_payment_method`
--

DROP TABLE IF EXISTS `otc_payment_method`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `otc_payment_method` (
  `zffsid` int(11) NOT NULL AUTO_INCREMENT,
  `mc` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '支付名称',
  `lb` int(11) DEFAULT NULL COMMENT '支付类别\\n0账号\\n1 收款码\\n2 现金交易',
  `tl` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图例',
  `zt` int(11) DEFAULT '0' COMMENT '状态\\\\n0冻结\\\\n1流通',
  `cjsj` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `bgsj` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `ljsc` int(11) DEFAULT '0',
  PRIMARY KEY (`zffsid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='支付方式信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `otc_voucher`
--

DROP TABLE IF EXISTS `otc_voucher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `otc_voucher` (
  `ddid` int(11) NOT NULL COMMENT '订单id',
  `ddzt` int(11) DEFAULT NULL COMMENT '状态',
  `js` int(11) DEFAULT NULL COMMENT '角色\\n1 币商\\n2 用户',
  `tppz` varchar(45) DEFAULT NULL COMMENT '图片凭据',
  `sppz` varchar(45) DEFAULT NULL COMMENT '视频凭据',
  `cjsj` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `ljsc` int(11) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='交易凭据';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_email`
--

DROP TABLE IF EXISTS `user_email`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_email` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mail` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `cjsj` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户邮箱';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Final view structure for view `legalcurrencylist`
--

/*!50001 DROP VIEW IF EXISTS `legalcurrencylist`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `legalcurrencylist` AS select `a`.`fbid` AS `fbid`,`a`.`name` AS `name`,`a`.`fh` AS `fh`,`a`.`dm` AS `dm`,`a`.`bzjed` AS `bzjed`,`a`.`zt` AS `zt`,`a`.`sfsxf` AS `sfsxf`,`a`.`fl` AS `fl`,`a`.`cjsj` AS `cjsj`,group_concat(`c`.`mc` separator ',') AS `zffs`,group_concat(`c`.`id` separator ',') AS `zffsID`,group_concat(`c`.`lb` separator ',') AS `zffslb` from ((`otc_legal_currency` `a` left join `otc_legal_currency_payment_method` `b` on((`a`.`fbid` = `b`.`fbid`))) left join (select `otc_payment_method`.`zffsid` AS `id`,`otc_payment_method`.`mc` AS `mc`,`otc_payment_method`.`lb` AS `lb`,`otc_payment_method`.`tl` AS `tl`,`otc_payment_method`.`zt` AS `zt`,`otc_payment_method`.`cjsj` AS `cjsj`,`otc_payment_method`.`bgsj` AS `bgsj`,`otc_payment_method`.`ljsc` AS `ljsc` from `otc_payment_method` where ((`otc_payment_method`.`ljsc` = 0) and (`otc_payment_method`.`zt` = 1))) `c` on((`b`.`zffsid` = `c`.`id`))) where (`a`.`ljsc` = 0) group by `a`.`fbid` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-25 15:29:05
