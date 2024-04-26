CREATE DATABASE  IF NOT EXISTS `apirest` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `apirest`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: apirest
-- ------------------------------------------------------
-- Server version	8.0.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tb_carro`
--

DROP TABLE IF EXISTS `tb_carro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_carro` (
  `cliente_id` bigint DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ano_modelo` varchar(255) DEFAULT NULL,
  `cor` varchar(255) DEFAULT NULL,
  `marca` varchar(255) DEFAULT NULL,
  `modelo` varchar(255) DEFAULT NULL,
  `placa` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKiv8ejv638u77x1oe7i25id263` (`cliente_id`),
  CONSTRAINT `FKiv8ejv638u77x1oe7i25id263` FOREIGN KEY (`cliente_id`) REFERENCES `tb_cliente` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_carro`
--

LOCK TABLES `tb_carro` WRITE;
/*!40000 ALTER TABLE `tb_carro` DISABLE KEYS */;
INSERT INTO `tb_carro` VALUES (1,1,'2020','Preto','Toyota','Corolla','ABC1234'),(1,2,'2019','Prata','Honda','Civic','DEF5678'),(2,3,'2018','Azul','Ford','Fiesta','GHI9012'),(2,4,'2021','Branco','Chevrolet','Onix','JKL3456'),(3,5,'2017','Vermelho','Volkswagen','Golf','MNO7890'),(4,6,'2016','Cinza','Hyundai','HB20','PQR2345'),(5,7,'2022','Verde','Renault','Sandero','STU6789'),(6,8,'2015','Amarelo','Fiat','Palio','VWX0123');
/*!40000 ALTER TABLE `tb_carro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_cliente`
--

DROP TABLE IF EXISTS `tb_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_cliente` (
  `data_nascimento` date NOT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cpf` varchar(11) DEFAULT NULL,
  `telefone` varchar(14) DEFAULT NULL,
  `senha` varchar(30) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_cliente`
--

LOCK TABLES `tb_cliente` WRITE;
/*!40000 ALTER TABLE `tb_cliente` DISABLE KEYS */;
INSERT INTO `tb_cliente` VALUES ('1990-01-01',1,'12345678901','3131234567','senha123','johndoe@example.com','John Doe'),('1985-12-05',2,'98765432109','3139876543','abc123','janesmith@example.com','Jane Smith'),('1976-08-10',3,'45678901234','3135678123','mypassword','alicejohnson@example.com','Alice Johnson'),('1982-03-22',4,'32165498778','3138765432','qwerty','bobbrown@example.com','Bob Brown'),('1995-07-15',5,'78901234567','3132345678','pass1234','emilytaylor@example.com','Emily Taylor'),('1988-11-03',6,'56789012345','3133456789','hello123','michaelclark@example.com','Michael Clark');
/*!40000 ALTER TABLE `tb_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_cotacao`
--

DROP TABLE IF EXISTS `tb_cotacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_cotacao` (
  `data_cotacao` date DEFAULT NULL,
  `max_parcelas_boleto` int DEFAULT NULL,
  `max_parcelas_cartao` int DEFAULT NULL,
  `max_parcelas_cartao_especial` int DEFAULT NULL,
  `max_parcelas_debito_conta` int DEFAULT NULL,
  `max_parcelas_pix` int DEFAULT NULL,
  `valor_cotacao` double DEFAULT NULL,
  `carro_id` bigint DEFAULT NULL,
  `cliente_id` bigint DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome_seguradora` varchar(255) DEFAULT NULL,
  `arquivo_cotacao` mediumblob,
  PRIMARY KEY (`id`),
  KEY `FKuarujvbsm7ufl6spqylwnjwu` (`carro_id`),
  KEY `FK6pntkxhutddodlhg2yvn25xrb` (`cliente_id`),
  CONSTRAINT `FK6pntkxhutddodlhg2yvn25xrb` FOREIGN KEY (`cliente_id`) REFERENCES `tb_cliente` (`id`),
  CONSTRAINT `FKuarujvbsm7ufl6spqylwnjwu` FOREIGN KEY (`carro_id`) REFERENCES `tb_carro` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_cotacao`
--

LOCK TABLES `tb_cotacao` WRITE;
/*!40000 ALTER TABLE `tb_cotacao` DISABLE KEYS */;
INSERT INTO `tb_cotacao` VALUES ('2024-04-01',NULL,NULL,NULL,NULL,1,1500,1,1,1,'Seguradora A',_binary '%PDF-1.3\n%“Œ‹ž ReportLab Generated PDF document http://www.reportlab.com\n1 0 obj\n<<\n/F1 2 0 R\n>>\nendobj\n2 0 obj\n<<\n/BaseFont /Helvetica /Encoding /WinAnsiEncoding /Name /F1 /Subtype /Type1 /Type /Font\n>>\nendobj\n3 0 obj\n<<\n/Contents 7 0 R /MediaBox [ 0 0 612 792 ] /Parent 6 0 R /Resources <<\n/Font 1 0 R /ProcSet [ /PDF /Text /ImageB /ImageC /ImageI ]\n>> /Rotate 0 /Trans <<\n\n>> \n  /Type /Page\n>>\nendobj\n4 0 obj\n<<\n/PageMode /UseNone /Pages 6 0 R /Type /Catalog\n>>\nendobj\n5 0 obj\n<<\n/Author (anonymous) /CreationDate (D:20240426135216+03\'00\') /Creator (ReportLab PDF Library - www.reportlab.com) /Keywords () /ModDate (D:20240426135216+03\'00\') /Producer (ReportLab PDF Library - www.reportlab.com) \n  /Subject (unspecified) /Title (untitled) /Trapped /False\n>>\nendobj\n6 0 obj\n<<\n/Count 1 /Kids [ 3 0 R ] /Type /Pages\n>>\nendobj\n7 0 obj\n<<\n/Filter [ /ASCII85Decode /FlateDecode ] /Length 287\n>>\nstream\nGasc?4\\rsL&-h(+^Z$7N>))/lG)45j0.*VIYW?>U;ATK_20+]+T9!\\\'(m;>5Cg#?&O3Ye=\\G1GP/MK+SSf\"!7m(n\"J@@j_ZU\\N,aV+cP),FB8,[;.@;JiESsm:GIn\'`[Tg9jg.nme[.;#tkTq.kFqe;X$;]jU)PHUm%$@I=^=:fLk1OO7@P:):<F&iN^S@`-OUAYn:?r7ZJn;S,-)Z$EW(\"?HD;^q719WE&S?]+L)J\"c6\'S_;1m#s<#NI&_83R\"5)2+jlPs8M$EN3RX_MQr3/i:d7,!uV~>endstream\nendobj\nxref\n0 8\n0000000000 65535 f \n0000000073 00000 n \n0000000104 00000 n \n0000000211 00000 n \n0000000404 00000 n \n0000000472 00000 n \n0000000768 00000 n \n0000000827 00000 n \ntrailer\n<<\n/ID \n[<aaba4417ea6dfbdeff1b47643093e2b9><aaba4417ea6dfbdeff1b47643093e2b9>]\n% ReportLab generated PDF document -- digest (http://www.reportlab.com)\n\n/Info 5 0 R\n/Root 4 0 R\n/Size 8\n>>\nstartxref\n1204\n%%EOF\n'),('2024-03-01',4,NULL,NULL,NULL,1,1800,2,2,2,'Seguradora B',_binary '%PDF-1.3\n%“Œ‹ž ReportLab Generated PDF document http://www.reportlab.com\n1 0 obj\n<<\n/F1 2 0 R\n>>\nendobj\n2 0 obj\n<<\n/BaseFont /Helvetica /Encoding /WinAnsiEncoding /Name /F1 /Subtype /Type1 /Type /Font\n>>\nendobj\n3 0 obj\n<<\n/Contents 7 0 R /MediaBox [ 0 0 612 792 ] /Parent 6 0 R /Resources <<\n/Font 1 0 R /ProcSet [ /PDF /Text /ImageB /ImageC /ImageI ]\n>> /Rotate 0 /Trans <<\n\n>> \n  /Type /Page\n>>\nendobj\n4 0 obj\n<<\n/PageMode /UseNone /Pages 6 0 R /Type /Catalog\n>>\nendobj\n5 0 obj\n<<\n/Author (anonymous) /CreationDate (D:20240426135216+03\'00\') /Creator (ReportLab PDF Library - www.reportlab.com) /Keywords () /ModDate (D:20240426135216+03\'00\') /Producer (ReportLab PDF Library - www.reportlab.com) \n  /Subject (unspecified) /Title (untitled) /Trapped /False\n>>\nendobj\n6 0 obj\n<<\n/Count 1 /Kids [ 3 0 R ] /Type /Pages\n>>\nendobj\n7 0 obj\n<<\n/Filter [ /ASCII85Decode /FlateDecode ] /Length 291\n>>\nstream\nGasc?Z#7E4&-h\'`Vtc#Wd\"1t.VeIpkkgUg+>+Ms(K0cIL>T$W&nS:[N%S8pO/^(a*e+j7n0\"RQQ$PWfe1P$gq%]&8+jom7kM=:(<8jXXn\"\\aj=g9h]#`k1(,GhtL-q7f\"K)9`o@I@m/n@EF)H_SQI/Q-SAi/r,OFd\"rH7NtBeQ+5Aj5A%^XFUCb?=LSld))XHWEa+f\"R)a:!dDX(.\\bHZ3q4-Inoo7^srE&S?m+L,;r/g!)(,Y&8#BIlfAdUsR?C`B9nLuZ&&&m]D^X5g[kC2S>W9R[#FFd?&~>endstream\nendobj\nxref\n0 8\n0000000000 65535 f \n0000000073 00000 n \n0000000104 00000 n \n0000000211 00000 n \n0000000404 00000 n \n0000000472 00000 n \n0000000768 00000 n \n0000000827 00000 n \ntrailer\n<<\n/ID \n[<d4c0f9c2f8f39d5ecfd747d7d158963d><d4c0f9c2f8f39d5ecfd747d7d158963d>]\n% ReportLab generated PDF document -- digest (http://www.reportlab.com)\n\n/Info 5 0 R\n/Root 4 0 R\n/Size 8\n>>\nstartxref\n1208\n%%EOF\n'),('2024-03-15',4,12,NULL,NULL,1,2149,3,3,3,'Seguradora C',_binary '%PDF-1.3\n%“Œ‹ž ReportLab Generated PDF document http://www.reportlab.com\n1 0 obj\n<<\n/F1 2 0 R\n>>\nendobj\n2 0 obj\n<<\n/BaseFont /Helvetica /Encoding /WinAnsiEncoding /Name /F1 /Subtype /Type1 /Type /Font\n>>\nendobj\n3 0 obj\n<<\n/Contents 7 0 R /MediaBox [ 0 0 612 792 ] /Parent 6 0 R /Resources <<\n/Font 1 0 R /ProcSet [ /PDF /Text /ImageB /ImageC /ImageI ]\n>> /Rotate 0 /Trans <<\n\n>> \n  /Type /Page\n>>\nendobj\n4 0 obj\n<<\n/PageMode /UseNone /Pages 6 0 R /Type /Catalog\n>>\nendobj\n5 0 obj\n<<\n/Author (anonymous) /CreationDate (D:20240426135216+03\'00\') /Creator (ReportLab PDF Library - www.reportlab.com) /Keywords () /ModDate (D:20240426135216+03\'00\') /Producer (ReportLab PDF Library - www.reportlab.com) \n  /Subject (unspecified) /Title (untitled) /Trapped /False\n>>\nendobj\n6 0 obj\n<<\n/Count 1 /Kids [ 3 0 R ] /Type /Pages\n>>\nendobj\n7 0 obj\n<<\n/Filter [ /ASCII85Decode /FlateDecode ] /Length 292\n>>\nstream\nGasc?Yti1j&-h(+:[nr(/OORq4,q>6ZqO:r?nl4Q;M0Ra)S&?&j5W$+$qW^M/^(a*POB>&(LPM\"\"4d[u4bH\\#2m?V\'Qj7HP/AC^O.2*lN#c>Lek;nNRi7YhoGr[i;`NMAG\"j@4qm?!ue@3\\&j6Ks)$9\':1E=+F7:3_:dN\'*S\'JMmUb?(f*(>&WPsTGCBHFf(40D^%g<iE^DY@2s4pQ@-<Pn,+jqYf<2NtrI`0\\M,J`22KDZ>WXUh#Cat_?CD.\\((T2>5j,)E\'_W&?1KC.pPer@$=m:#\\tYOIeL~>endstream\nendobj\nxref\n0 8\n0000000000 65535 f \n0000000073 00000 n \n0000000104 00000 n \n0000000211 00000 n \n0000000404 00000 n \n0000000472 00000 n \n0000000768 00000 n \n0000000827 00000 n \ntrailer\n<<\n/ID \n[<ebf1109ae85a0896db9b858d82415994><ebf1109ae85a0896db9b858d82415994>]\n% ReportLab generated PDF document -- digest (http://www.reportlab.com)\n\n/Info 5 0 R\n/Root 4 0 R\n/Size 8\n>>\nstartxref\n1209\n%%EOF\n'),('2024-04-15',4,12,NULL,12,NULL,7345.99,4,4,4,'Seguradora D',_binary '%PDF-1.3\n%“Œ‹ž ReportLab Generated PDF document http://www.reportlab.com\n1 0 obj\n<<\n/F1 2 0 R\n>>\nendobj\n2 0 obj\n<<\n/BaseFont /Helvetica /Encoding /WinAnsiEncoding /Name /F1 /Subtype /Type1 /Type /Font\n>>\nendobj\n3 0 obj\n<<\n/Contents 7 0 R /MediaBox [ 0 0 612 792 ] /Parent 6 0 R /Resources <<\n/Font 1 0 R /ProcSet [ /PDF /Text /ImageB /ImageC /ImageI ]\n>> /Rotate 0 /Trans <<\n\n>> \n  /Type /Page\n>>\nendobj\n4 0 obj\n<<\n/PageMode /UseNone /Pages 6 0 R /Type /Catalog\n>>\nendobj\n5 0 obj\n<<\n/Author (anonymous) /CreationDate (D:20240426135216+03\'00\') /Creator (ReportLab PDF Library - www.reportlab.com) /Keywords () /ModDate (D:20240426135216+03\'00\') /Producer (ReportLab PDF Library - www.reportlab.com) \n  /Subject (unspecified) /Title (untitled) /Trapped /False\n>>\nendobj\n6 0 obj\n<<\n/Count 1 /Kids [ 3 0 R ] /Type /Pages\n>>\nendobj\n7 0 obj\n<<\n/Filter [ /ASCII85Decode /FlateDecode ] /Length 292\n>>\nstream\nGasc?]l(\\@\'F!EcV`7$KkQ&?*6e[J1Qs,/HM^cgq\\5fO3=2mcnILYPg+\\@fGA6D*gHnRe(6+=ODJ;(gU9csc6!RamUo7E5F`I%H08jXXp\"AFI<`\\eM:`k213HTjF8fq1(k$-X40A!`il+m6XB@Hdh08uHYXQU0h<S5Mm@5Yt,?\\9>U(G^GNMM#R>M\\]qn/pN2g4bJ(>@T2:KUeYSXDfXR[saJZ`IKr]\'0G7[>!Z\"+qoKj9n@!CHGlH(bl)=2D^2YrTkWSS/ISFADjc#HQ=_/M!tcWl+$*0m&Z*~>endstream\nendobj\nxref\n0 8\n0000000000 65535 f \n0000000073 00000 n \n0000000104 00000 n \n0000000211 00000 n \n0000000404 00000 n \n0000000472 00000 n \n0000000768 00000 n \n0000000827 00000 n \ntrailer\n<<\n/ID \n[<6f8c80846ffedc5abffd362af78a4d0f><6f8c80846ffedc5abffd362af78a4d0f>]\n% ReportLab generated PDF document -- digest (http://www.reportlab.com)\n\n/Info 5 0 R\n/Root 4 0 R\n/Size 8\n>>\nstartxref\n1209\n%%EOF\n'),('2024-04-22',NULL,12,NULL,12,1,7345.99,5,5,5,'Seguradora E',_binary '%PDF-1.3\n%“Œ‹ž ReportLab Generated PDF document http://www.reportlab.com\n1 0 obj\n<<\n/F1 2 0 R\n>>\nendobj\n2 0 obj\n<<\n/BaseFont /Helvetica /Encoding /WinAnsiEncoding /Name /F1 /Subtype /Type1 /Type /Font\n>>\nendobj\n3 0 obj\n<<\n/Contents 7 0 R /MediaBox [ 0 0 612 792 ] /Parent 6 0 R /Resources <<\n/Font 1 0 R /ProcSet [ /PDF /Text /ImageB /ImageC /ImageI ]\n>> /Rotate 0 /Trans <<\n\n>> \n  /Type /Page\n>>\nendobj\n4 0 obj\n<<\n/PageMode /UseNone /Pages 6 0 R /Type /Catalog\n>>\nendobj\n5 0 obj\n<<\n/Author (anonymous) /CreationDate (D:20240426135216+03\'00\') /Creator (ReportLab PDF Library - www.reportlab.com) /Keywords () /ModDate (D:20240426135216+03\'00\') /Producer (ReportLab PDF Library - www.reportlab.com) \n  /Subject (unspecified) /Title (untitled) /Trapped /False\n>>\nendobj\n6 0 obj\n<<\n/Count 1 /Kids [ 3 0 R ] /Type /Pages\n>>\nendobj\n7 0 obj\n<<\n/Filter [ /ASCII85Decode /FlateDecode ] /Length 292\n>>\nstream\nGasc?4\\rsL&-h(+^Z$7N>)(&[O%*Yr)^_L5YaD^9WBigANEd_$kMnH/$qW^M/^(a\"Om`Q\"/\\7Nr#HTM_HNHdk2iq?\\fE[XaQJUl(P_;;>\"Dikhc?Ba\'`j:$l$@M>oMdL[m$eQ!BgEAlU_NlERKT2$t.@mB=Hi[Y2Tee-nSfjsZ?F]Z:^j*GQ7YpU4lE8GtVKt9VZ0+rqQ;=IAqrV-8$LI]<:#tonr7\\V4qL9&gPh9r2f1%C8C4]9W`3rQu?CU0;\\hopeRSL0LKLLB?#Nb4!eIAtih-p!d2e80!~>endstream\nendobj\nxref\n0 8\n0000000000 65535 f \n0000000073 00000 n \n0000000104 00000 n \n0000000211 00000 n \n0000000404 00000 n \n0000000472 00000 n \n0000000768 00000 n \n0000000827 00000 n \ntrailer\n<<\n/ID \n[<05a30827059f268c2991785adc282ca8><05a30827059f268c2991785adc282ca8>]\n% ReportLab generated PDF document -- digest (http://www.reportlab.com)\n\n/Info 5 0 R\n/Root 4 0 R\n/Size 8\n>>\nstartxref\n1209\n%%EOF\n'),('2024-04-22',4,12,NULL,NULL,NULL,1213.32,6,6,6,'Seguradora F',_binary '%PDF-1.3\n%“Œ‹ž ReportLab Generated PDF document http://www.reportlab.com\n1 0 obj\n<<\n/F1 2 0 R\n>>\nendobj\n2 0 obj\n<<\n/BaseFont /Helvetica /Encoding /WinAnsiEncoding /Name /F1 /Subtype /Type1 /Type /Font\n>>\nendobj\n3 0 obj\n<<\n/Contents 7 0 R /MediaBox [ 0 0 612 792 ] /Parent 6 0 R /Resources <<\n/Font 1 0 R /ProcSet [ /PDF /Text /ImageB /ImageC /ImageI ]\n>> /Rotate 0 /Trans <<\n\n>> \n  /Type /Page\n>>\nendobj\n4 0 obj\n<<\n/PageMode /UseNone /Pages 6 0 R /Type /Catalog\n>>\nendobj\n5 0 obj\n<<\n/Author (anonymous) /CreationDate (D:20240426135216+03\'00\') /Creator (ReportLab PDF Library - www.reportlab.com) /Keywords () /ModDate (D:20240426135216+03\'00\') /Producer (ReportLab PDF Library - www.reportlab.com) \n  /Subject (unspecified) /Title (untitled) /Trapped /False\n>>\nendobj\n6 0 obj\n<<\n/Count 1 /Kids [ 3 0 R ] /Type /Pages\n>>\nendobj\n7 0 obj\n<<\n/Filter [ /ASCII85Decode /FlateDecode ] /Length 295\n>>\nstream\nGasK8]l(_1\'F!DX:N;+!8V`Z@fN(/tDec>O7@h*H34*^;Q\\nTgO\'`lNa:M8n&46,=JdXl_6\'o\"DJX&O0,ERS(#O/(>H,S:Xi]6t&Oq9$_!RkQXm!=R7j4/VVGWn17e^Y_Q\"jA@<r@L<p_M/.\'_V,0rPqM)rY(A&WTee-n:(T5pDqm@&_g&_U8r2_ZnM\'*t@Qg9KfX#L4a!]C.I=&+5(%Oc&S.)T+B7h/o.Zr%)o5\"\'Ynq;Q:R5!-dZ)L_uLGh-E55+(g2C;m%_P7)!KC.pX2D\'-*Fo=%^r;oVcOR)~>endstream\nendobj\nxref\n0 8\n0000000000 65535 f \n0000000073 00000 n \n0000000104 00000 n \n0000000211 00000 n \n0000000404 00000 n \n0000000472 00000 n \n0000000768 00000 n \n0000000827 00000 n \ntrailer\n<<\n/ID \n[<fb51c7b37a8da7d8efe94a019e365ff7><fb51c7b37a8da7d8efe94a019e365ff7>]\n% ReportLab generated PDF document -- digest (http://www.reportlab.com)\n\n/Info 5 0 R\n/Root 4 0 R\n/Size 8\n>>\nstartxref\n1212\n%%EOF\n');
/*!40000 ALTER TABLE `tb_cotacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_endereco`
--

DROP TABLE IF EXISTS `tb_endereco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_endereco` (
  `cliente_id` bigint DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `bairro` varchar(255) DEFAULT NULL,
  `cep` varchar(255) DEFAULT NULL,
  `cidade` varchar(255) DEFAULT NULL,
  `complemento` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `numero` varchar(255) DEFAULT NULL,
  `rua` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtqe8u5ggrtf93dr80hiibdfd` (`cliente_id`),
  CONSTRAINT `FKtqe8u5ggrtf93dr80hiibdfd` FOREIGN KEY (`cliente_id`) REFERENCES `tb_cliente` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_endereco`
--

LOCK TABLES `tb_endereco` WRITE;
/*!40000 ALTER TABLE `tb_endereco` DISABLE KEYS */;
INSERT INTO `tb_endereco` VALUES (1,1,'Centro','30123-456','Belo Horizonte','Apto 101','Minas Gerais','123','Rua A'),(1,2,'Savassi','30234-567','Belo Horizonte','Casa','Minas Gerais','456','Rua B'),(2,3,'FuncionÃ¡rios','30345-678','Belo Horizonte','casa','Minas Gerais','789','Rua C'),(2,4,'Lourdes','30456-789','Belo Horizonte','Loja 02','Minas Gerais','1011','Rua D'),(3,5,'Barro Preto','30567-890','Belo Horizonte','casa','Minas Gerais','1213','Rua E'),(4,6,'Santa EfigÃªnia','30678-901','Belo Horizonte','ap 1002','Minas Gerais','1415','Rua F'),(5,7,'Barreiro','30789-012','Belo Horizonte','ap 102 bloco 3','Minas Gerais','1617','Rua G'),(6,8,'Cidade Nova','30890-123','Belo Horizonte','ap 500','Minas Gerais','1819','Rua H');
/*!40000 ALTER TABLE `tb_endereco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'apirest'
--

--
-- Dumping routines for database 'apirest'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-26 13:59:32
