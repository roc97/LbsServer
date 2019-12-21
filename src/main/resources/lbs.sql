/*
SQLyog Ultimate v11.42 (64 bit)
MySQL - 5.7.28 : Database - lbs
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`lbs` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `lbs`;

/*Table structure for table `experience` */

DROP TABLE IF EXISTS `experience`;

CREATE TABLE `experience` (
  `experience_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '心得id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `title` varchar(64) NOT NULL COMMENT '心得标题',
  `content` text NOT NULL COMMENT '心得内容',
  `like_num` int(11) NOT NULL DEFAULT '0' COMMENT '点赞数',
  `unlike_num` int(11) NOT NULL DEFAULT '0' COMMENT '不赞成数',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `image` varchar(128) DEFAULT NULL COMMENT '配图',
  PRIMARY KEY (`experience_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COMMENT='心得表';

/*Data for the table `experience` */

insert  into `experience`(`experience_id`,`user_id`,`title`,`content`,`like_num`,`unlike_num`,`create_time`,`image`) values (3,7,'十一月来了','你好，我是十一。不惧于心，不畏过往，不困于情，不念过往。',1,1,'2019-11-03 10:45:08',NULL),(5,5,'七十周年','这盛世如您所愿!',1,0,'2019-11-04 00:35:43',NULL),(6,13,'人相','人相就是人的人生价值观。确切的说，是一个人对生命和生活的态度看法。大的来说例如甲说人生的意义是什么，乙说人活着是为了什么;小的来说例如甲说这件事应该严肃处理才好，乙说这个人太坚强，丙说这种行为太愚蠢了等等;这些都属于“我见”即“人相”。',2,0,'2019-12-17 09:28:28',NULL),(8,13,'微笑地活着','用可怜的眼泪，洗不掉现实的悲催!唯有打起十二份的精神，过好美好的每一个今天的此时此刻。',2,0,'2019-12-17 09:36:26',NULL),(9,13,'微笑着，幸福才不会遥远','把每一个平凡的日子，梳理成诗意，用微笑将生命点亮，远离生活的阴霾，明媚向暖，如此，才能接近幸福。\n　　毕淑敏说，你要学会提醒幸福，那么每天清晨，在触手可及的阳光中，感受生活点滴的美好，在每一个黄昏到来前，收集温暖，心存感恩的过好每一天。',1,0,'2019-12-17 15:39:45',NULL),(10,16,'人生如旅','人这一生如同一次旅程，在前行中看风景，在过程中享受快乐。如何把握行程的节奏，让旅行的过程更完美，需要做好规划和调整，需要智慧和勤奋，需要总结和反思，需要舍弃和保留，需要目标，需要学习，需要激情，需要挑战，同样需要有一颗正确面对生活的平常心。',0,0,'2019-12-17 15:42:00',NULL),(11,7,'人生需要学习，需要充电','通过学习培训提高自己的工作技能和知识水平，就和旅途需要喝点水，吃点西一样，补充一下体能，积蓄一点能量，以便进行下一步的攀登，上一个台阶，看更美的风景，实现下一个目标。',1,0,'2019-12-17 18:05:50',NULL),(12,10,'笑话（测试最大长度）','讲个高中同学的笑话。有一天，教导主任拉着班主任去寝室查寝。这个班主任比较不讨喜，同学们背后都叫他「x狗」。同学A出去买饭了。班主任和教导主任在外面敲门。班主任：咚。咚。咚。寝室里面的人不知道是老师来了，对着门口喊：「报暗号！」教导主任咳嗽了两声。班主任加大了敲门的力度。班主任：砰！砰！砰！寝室里的同学：「傻逼说暗号！不说暗号不让进！」班主任无奈，只得暴露身份。寝室里几个人马上灰溜溜的给班主任开门了。教导主任和班主任关上寝室门对他们就是一顿训斥。然后高潮来了。同学A买完饭回来了。A手里提着东西，看到寝室门紧锁，哐哐哐的疯狂踹门。寝室里的人刚还在被训斥，教导主任班主任就站在面前哪敢吭声。A于是开始在门口大喊。「我说暗号了啊！」「草泥马草泥马草泥马」教导主任和班主任脸都黑了。几个同学在寝室低着头疯狂憋笑，没有一个人吭声。于是A看到没人吭声，更加来劲了。「那我说第二个暗号了啊！」「x狗傻逼x狗傻逼x狗傻逼」……\n\n作者：春人\n链接：https://www.zhihu.com/question/267104067/answer/554842639\n来源：知乎\n著作权归作者所有。商业转载请联系作者',0,0,'2019-12-18 02:34:09',NULL),(13,10,'无名之辈','无名之辈，我是谁，也许很累，忘了谁，也无所谓，谁不是拼了命走到生命的结尾！',0,0,'2019-12-19 12:19:45',NULL),(16,19,'啥','蛤蛤',0,0,'2019-12-20 08:08:51',NULL),(21,16,'测试发表图片测试发表图片','测试发表图片测试发表图片测试发表图片测试发表图片测试发表图片测试发表图片测试发表图片',0,0,'2019-12-21 10:22:24',NULL),(22,16,'测试发表图片111','测试发表图片测试发表图片测试发表图片111',0,0,'2019-12-21 10:30:02','http://lbs-pictures.oss-cn-beijing.aliyuncs.com/images/f159975a8c62f8ecb7584e36d033990e.jpg'),(23,16,'测试正常发表','测试正常发表',0,0,'2019-12-21 10:30:59',NULL),(24,16,'测试发表图片2332','测试发表图片测试发表图片测试发表图片测试发表图片',0,0,'2019-12-21 10:31:42','http://lbs-pictures.oss-cn-beijing.aliyuncs.com/images/eaeeaabca5051e79c3e5a4f3a8cc2aec.png');

/*Table structure for table `map_mark_message` */

DROP TABLE IF EXISTS `map_mark_message`;

CREATE TABLE `map_mark_message` (
  `mark_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '地图标注id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `lat` double(14,10) NOT NULL COMMENT '纬度',
  `lng` double(14,10) NOT NULL COMMENT '经度',
  `title` varchar(64) NOT NULL COMMENT '标题',
  `content` text NOT NULL COMMENT '内容',
  `status` tinyint(4) NOT NULL COMMENT '发布推广状态 0未申请推广 1审核通过 2未通过 3待审核',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`mark_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COMMENT='地图标注信息表';

/*Data for the table `map_mark_message` */

insert  into `map_mark_message`(`mark_id`,`user_id`,`lat`,`lng`,`title`,`content`,`status`,`create_time`) values (5,7,23.0474494217,113.1038244252,'测试发布消息呀','挖贝网资料显示，东土科技是专注于工业互联网技术及产业的上市公司，致力于工业互联网平台技术的研究，用工业互联网技术创新各行业解决方案，应用于智慧工业、智慧军事、智慧能源、智慧城市等领域。',1,'2019-10-29 22:51:05'),(7,13,22.3165540000,114.1743280000,'测试标题1','测试内容测试内容测试内容',0,'2019-12-15 12:50:15'),(8,13,22.3145290000,114.1776110000,'测试标题1','测试内容测试内容测试内容',0,'2019-12-15 12:51:36'),(9,13,22.5334698514,113.9438082044,'测试标题1','测试内容测试内容测试内容',0,'2019-12-15 15:44:33'),(10,13,22.5381471751,113.9085957165,'测试标题1','测试内容测试内容测试内容',0,'2019-12-15 15:52:07'),(11,13,22.5349860352,113.9201292153,'测试标题1','测试内容测试内容测试内容',0,'2019-12-15 15:53:13'),(12,13,22.5334004832,113.9202579613,'测试标题1','测试内容测试内容测试内容',0,'2019-12-15 15:53:29'),(13,13,22.3091099575,114.1751858349,'测试标题1','测试内容测试内容测试内容',0,'2019-12-15 16:24:21'),(14,13,22.3146161731,114.1702639814,'测试标题1','测试内容测试内容测试内容',0,'2019-12-15 16:31:47'),(15,13,22.4167035361,113.8130876179,'测试标题1','测试内容测试内容测试内容',1,'2019-12-15 18:04:14'),(16,13,22.3139735030,114.1727396603,'测试标题1','测试内容测试内容测试内容',0,'2019-12-15 19:50:05'),(17,13,22.3254468287,114.1664740200,'测试标题1','测试内容测试内容测试内容',1,'2019-12-15 20:02:55'),(18,13,22.3148072366,114.1605946179,'靠近海边','asda是打算阿三阿三阿三',2,'2019-12-15 20:04:28'),(20,10,22.5119437287,113.9129858945,'深圳测试hi11','阿斯达萨达撒打啊实打实大萨达撒打撒打撒打asdasd啊是',0,'2019-12-16 06:54:27'),(22,10,22.5432202386,113.9244442914,'哈哈哈','撒的撒的撒多撒多撒sad撒多啊是啊是啊是',0,'2019-12-16 07:15:45'),(24,16,22.5562796745,113.2502408203,'jim测试1','jim测试测试测试1',1,'2019-12-16 13:25:43'),(25,13,22.2468013603,113.9254597878,'香港凤凰山','测试一下凤凰山',0,'2019-12-16 18:11:57'),(27,16,21.2616744627,110.2814659287,'湛江-烧猪','烧猪\n关于烧猪的起源，可以追溯到两千多年前的西汉初年。广州发掘的南越王墓中出土了一炉具，在炉壁两侧铸有小猪4头，近旁一铜鼎内发现有乳猪的遗骸，证明此炉是用来烧乳猪的',1,'2019-12-18 07:00:24'),(28,16,22.2781238117,113.1708601792,'测试下','对的地址',1,'2019-12-19 18:57:19'),(29,16,22.5069008350,113.5187221871,'测试一下','22222',1,'2019-12-20 02:38:19');

/*Table structure for table `praise_or_disagree` */

DROP TABLE IF EXISTS `praise_or_disagree`;

CREATE TABLE `praise_or_disagree` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '点赞（踩）表id',
  `operate_object` int(11) NOT NULL COMMENT '操作对象,一般是用户id',
  `operate_target` int(11) NOT NULL COMMENT '操作目标,一般是心得id或者回复id',
  `operate_type` int(4) NOT NULL COMMENT '操作类型,0点赞，1踩',
  `target_type` int(4) NOT NULL COMMENT '目标类型 0心得，1回复',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=234 DEFAULT CHARSET=utf8mb4 COMMENT='点赞（踩）表';

/*Data for the table `praise_or_disagree` */

insert  into `praise_or_disagree`(`id`,`operate_object`,`operate_target`,`operate_type`,`target_type`,`create_time`) values (162,13,8,0,0,'2019-12-20 04:52:58'),(163,13,6,0,0,'2019-12-20 04:53:02'),(164,13,9,1,0,'2019-12-20 04:53:06'),(165,13,11,0,1,'2019-12-20 04:53:16'),(166,13,11,0,0,'2019-12-20 04:55:00'),(179,16,5,1,1,'2019-12-20 06:17:37'),(194,16,11,0,1,'2019-12-20 06:19:44'),(195,16,15,0,1,'2019-12-20 06:19:46'),(197,10,6,0,1,'2019-12-20 07:50:34'),(198,10,5,0,1,'2019-12-20 07:50:36'),(201,10,16,0,1,'2019-12-20 07:50:49'),(205,16,3,1,0,'2019-12-20 07:51:43'),(219,10,9,0,1,'2019-12-20 09:48:39'),(222,10,25,0,1,'2019-12-20 10:18:00'),(225,10,11,0,1,'2019-12-20 13:12:51'),(231,16,5,0,0,'2019-12-20 15:10:27'),(232,16,6,0,0,'2019-12-20 15:11:12'),(233,10,18,0,0,'2019-12-20 16:10:11');

/*Table structure for table `reply` */

DROP TABLE IF EXISTS `reply`;

CREATE TABLE `reply` (
  `reply_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '回复表id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `experience_id` int(11) NOT NULL COMMENT '心得id',
  `content` text NOT NULL COMMENT '内容',
  `like_num` int(11) NOT NULL DEFAULT '0' COMMENT '点赞数',
  `unlike_num` int(11) NOT NULL DEFAULT '0' COMMENT '不赞成数',
  `main_reply_id` int(11) NOT NULL COMMENT '主回复id',
  `parent_user_id` int(11) NOT NULL COMMENT '上级用户id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`reply_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COMMENT='回复表';

/*Data for the table `reply` */

insert  into `reply`(`reply_id`,`user_id`,`experience_id`,`content`,`like_num`,`unlike_num`,`main_reply_id`,`parent_user_id`,`create_time`) values (2,3,1,'明天就是国庆啦，祝中国70周岁快乐！！！',0,0,0,0,'2019-09-30 21:28:07'),(3,7,4,'是啊 谁不是一个无名之辈呢',0,0,0,0,'2019-11-03 13:28:58'),(4,4,1,'举国欢庆呀哈哈哈',0,0,1,1,'2019-11-04 00:35:44'),(5,13,3,'测试一下测试一下测试一下测试一下测试一下',1,1,0,0,'2019-12-17 15:27:54'),(6,13,3,'幸福是一种感受，是来自平凡生活中的积累，幸福的人都懂得活在当下，总是喜欢活在昨天的人会懊悔，总是活在明天的人会焦虑，只有活在今天的人才会幸福。',1,0,0,0,'2019-12-17 15:40:15'),(7,13,5,'感谢！',1,0,0,0,'2019-12-17 15:40:49'),(8,13,5,'再次感谢！！',1,0,0,0,'2019-12-17 15:41:01'),(9,16,5,'感恩！！',1,0,0,0,'2019-12-17 15:41:36'),(10,7,11,'给自己打气！',0,0,0,0,'2019-12-17 18:06:08'),(11,10,6,'我也觉得',3,0,0,0,'2019-12-18 02:12:39'),(12,10,5,'若你所愿',1,0,0,0,'2019-12-18 02:13:05'),(13,10,10,'好的',1,0,0,0,'2019-12-18 02:14:24'),(14,10,9,'好的',1,0,0,0,'2019-12-18 02:18:39'),(15,10,6,'哈哈\n',3,1,0,0,'2019-12-18 03:58:06'),(16,13,3,'十二月了！',1,0,0,0,'2019-12-18 07:02:45'),(17,13,11,'十二月打卡\n',0,0,0,0,'2019-12-18 07:03:03'),(18,16,12,'测试一下',1,0,0,0,'2019-12-19 13:16:42'),(19,16,12,'测试一下是多少',1,0,0,0,'2019-12-19 13:17:06'),(20,7,8,'测试111',0,0,0,0,'2019-12-19 18:47:51'),(21,7,8,'测试给你',0,0,0,0,'2019-12-19 18:47:56'),(22,7,8,'测试给你hi你你好',0,0,0,0,'2019-12-19 18:48:00'),(23,7,8,'测试给你hi你你好给做屁也软用肉上',0,0,0,0,'2019-12-19 18:48:04'),(24,16,8,'谢谢…',0,0,0,0,'2019-12-19 18:56:09'),(26,16,6,'测试一下',0,0,0,0,'2019-12-21 07:00:39');

/*Table structure for table `sys_permission` */

DROP TABLE IF EXISTS `sys_permission`;

CREATE TABLE `sys_permission` (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `permission_name` varchar(64) NOT NULL COMMENT '权限名称',
  `description` varchar(64) NOT NULL COMMENT '权限描述',
  `url` varchar(128) NOT NULL COMMENT '路径',
  `order_index` int(4) NOT NULL COMMENT '排序id',
  `parent_id` int(11) NOT NULL COMMENT '父节点',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='权限表';

/*Data for the table `sys_permission` */

insert  into `sys_permission`(`permission_id`,`permission_name`,`description`,`url`,`order_index`,`parent_id`,`create_time`) values (1,'admin','管理员权限','/admin/**',1,0,'2019-10-01 17:06:59'),(3,'markAuth','标注推广权限','/mark/**',1,0,'2019-10-29 20:29:51'),(4,'experienceAuth','心得交流权限','/experience/**',1,0,'2019-10-30 23:06:35'),(5,'user','用户权限','/user/**',1,0,'2019-11-04 00:35:45');

/*Table structure for table `sys_permission_role` */

DROP TABLE IF EXISTS `sys_permission_role`;

CREATE TABLE `sys_permission_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '关联表id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `permission_id` int(11) NOT NULL COMMENT '权限id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='角色-权限关联表';

/*Data for the table `sys_permission_role` */

insert  into `sys_permission_role`(`id`,`role_id`,`permission_id`,`create_time`) values (2,2,2,'2019-10-24 00:29:11'),(3,2,3,'2019-10-29 20:30:07'),(4,2,4,'2019-10-30 23:07:17'),(5,2,5,'2019-12-08 13:53:56'),(6,1,1,'2019-12-15 14:33:11');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(64) NOT NULL COMMENT '角色名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `role_name_zh` varchar(64) NOT NULL COMMENT '中文角色名称',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

/*Data for the table `sys_role` */

insert  into `sys_role`(`role_id`,`role_name`,`create_time`,`role_name_zh`) values (1,'ROLE_ADMIN','2019-12-15 14:29:35','管理员'),(2,'ROLE_BASIC','2019-10-23 20:16:15','普通用户');

/*Table structure for table `sys_role_user` */

DROP TABLE IF EXISTS `sys_role_user`;

CREATE TABLE `sys_role_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '关联表id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COMMENT='用户-角色关联表';

/*Data for the table `sys_role_user` */

insert  into `sys_role_user`(`id`,`user_id`,`role_id`,`create_time`) values (2,5,1,'2019-10-21 20:36:43'),(3,7,2,'2019-10-23 21:28:48'),(4,8,2,'2019-10-24 21:52:21'),(5,9,2,'2019-10-24 23:00:40'),(6,10,2,'2019-10-27 11:22:36'),(7,11,2,'2019-12-14 10:56:15'),(8,12,2,'2019-12-14 11:37:31'),(9,13,2,'2019-12-14 14:10:41'),(10,14,2,'2019-12-15 08:42:54'),(11,15,2,'2019-12-15 08:48:47'),(13,16,2,'2019-12-16 13:24:45'),(14,6,2,'2019-12-20 01:41:47'),(15,17,2,'2019-12-20 07:44:02'),(16,18,2,'2019-12-20 08:01:59'),(17,19,2,'2019-12-20 08:08:14'),(18,20,2,'2019-12-20 08:08:47'),(19,19,1,'2019-12-20 08:14:04'),(20,21,2,'2019-12-20 08:35:19'),(21,22,2,'2019-12-20 09:32:52'),(22,23,2,'2019-12-20 09:37:29'),(23,24,2,'2019-12-20 11:29:02');

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `name` varchar(64) NOT NULL COMMENT '昵称',
  `user_name` varchar(64) NOT NULL COMMENT '账号',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `sex` char(4) DEFAULT NULL COMMENT '性别',
  `head_image` varchar(128) DEFAULT NULL COMMENT '头像',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

/*Data for the table `sys_user` */

insert  into `sys_user`(`user_id`,`name`,`user_name`,`password`,`sex`,`head_image`,`create_time`) values (5,'管理员','admin','$2a$10$ErWumJuv7aA/CzhwOqpOYO1u.Msl2cIIaYPXONL6EJ3s0L2b5URTW',NULL,NULL,'2019-10-21 20:35:59'),(6,'小张','zhang','$2a$10$q8Xsip1q3bp38Dpuleyzu.ctkrYKw8S1aefjBtc.l8ZInx1pfMHPm',NULL,NULL,'2019-10-21 23:10:28'),(7,'测试','test','$2a$10$mvUq6egrg2neBXp1i1UHfupuJqJRHYBTsIICez4pSPibSesT.G30a',NULL,'http://lbs-pictures.oss-cn-beijing.aliyuncs.com/images/4cff2a3a98b84e6b8b2e945582078d0a.jpg','2019-10-23 21:28:48'),(8,'老王','laowang','$2a$10$bHBHX8rEGwADmksT9FBoYujr/eurqTajdSRUuu14/yPq3ftrcFir6',NULL,NULL,'2019-10-24 21:52:21'),(10,'测试大大','testDD','$2a$10$r.2DN8T7bwEHS9yGWkrIXeG/9kyKKnZilFAm1/NQWLXlJIYOhqN4K',NULL,'http://lbs-pictures.oss-cn-beijing.aliyuncs.com/images/a11ba4bf35184d90ae6ab3fcda47378b.jpg','2019-10-27 11:22:36'),(11,'jim','jimname','$2a$10$6DZaduUWf2At2WlP5y0ND.baRNTM9pDuuN4VGtQjbDAEO0iwsUQMG',NULL,NULL,'2019-12-14 10:56:15'),(12,'testJim','testJim','$2a$10$SczXIOnnuploUd.7mtUUXexVVOACW/lwAVN8Y14k0X4HrDwlCP8Ki',NULL,NULL,'2019-12-14 11:37:31'),(13,'test','testDDD','$2a$10$x38YaSeihKI/2jHL/9BODeEBxn9mGjJ5Lx7g5HYO8Z1uRX07XZhQe',NULL,'http://lbs-pictures.oss-cn-beijing.aliyuncs.com/images/70a2c4c5adeb4294ab14769d1c9a4e1d.jpg','2019-12-14 14:10:41'),(14,'sd','sdssd','$2a$10$YBYTeZtn.FZlpFYfwiYQY.1gp5Ij.xlXuPiyMp418zuU3zAM3BYCq',NULL,NULL,'2019-12-15 08:42:54'),(15,'123s1dsds','123156','$2a$10$raInP8HHrrOfMfIasDeB5ueMvecj8r6Buo.om7mOyZSw1ITURstxG',NULL,NULL,'2019-12-15 08:48:47'),(16,'jimjim','jim','$2a$10$.Q5KsrQAVSeApIOY9UWbSuJrRkN3zmDIIeq9ODMg1nkEXluJyYkly',NULL,'http://lbs-pictures.oss-cn-beijing.aliyuncs.com/images/c7f245d77017f387f5ec097fb277e674.jpg','2019-12-16 13:24:45'),(17,'xiaopeng','xiaopeng','$2a$10$Cq5NtYFRsIRsImvBGwPcr.69la69T2QLaeLcs1qDLrbajlCYeQTUC',NULL,NULL,'2019-12-20 07:44:02'),(18,'fishball','fishball','$2a$10$SiUDsq5xQYC8EvCXBosdNOMIUthLiySipRXyup5dwo2KrvM.htP7m',NULL,'http://lbs-pictures.oss-cn-beijing.aliyuncs.com/images/3fb44eb13e775883812a9657f558c9a4.png','2019-12-20 08:01:59'),(19,'sjihdbdndd','987654','$2a$10$F69zDMGRlWFnd.BIx3ec0uJ8sD4JK6XTJk1z3JCnYqVhxPJeiugCO',NULL,NULL,'2019-12-20 08:08:14'),(20,'xixi','xixi_595','$2a$10$lT64kV.P5SaM020biSOjjO0el8avklO6DatLt5SeTi26YNTfZp9T2',NULL,NULL,'2019-12-20 08:08:47'),(21,'leon','Leon','$2a$10$whV5hzGf4VUVigI3cz46.u3eivCJrpL.U0ztYc0ifxmaiLqugmWYK',NULL,'http://lbs-pictures.oss-cn-beijing.aliyuncs.com/images/af79724b41dbf5449aef307b2cae8d74.jpg','2019-12-20 08:35:19'),(22,'Aabbcc','ABC','$2a$10$e5Z3RQHjUZaPVnKrvroKnuxq04Tf892GlBru5hJLryCWl.eZFXD3W',NULL,'http://lbs-pictures.oss-cn-beijing.aliyuncs.com/images/81b89418b14fda989f73d4820b99cddd.png','2019-12-20 09:32:52'),(23,'吃屎啦梁非凡','吃屎啦梁非凡','$2a$10$75r3un7hhx6RhiTisLsJPevscrUsgDTEu8jSpCB5P850BH0X0IfXG',NULL,NULL,'2019-12-20 09:37:29'),(24,'顶顶顶','123','$2a$10$5100EzZYGLULVmzryauy1.41Pb.CrJdgnh1nPBHgujx9UcoqGHTzK',NULL,NULL,'2019-12-20 11:29:02');

/*Table structure for table `user_attention` */

DROP TABLE IF EXISTS `user_attention`;

CREATE TABLE `user_attention` (
  `attention_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '关注表id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `user_attention_id` int(11) NOT NULL COMMENT '用户关注对象的id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`attention_id`),
  KEY `idex_user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COMMENT='关注表';

/*Data for the table `user_attention` */

insert  into `user_attention`(`attention_id`,`user_id`,`user_attention_id`,`create_time`) values (1,1,3,'2019-09-28 23:38:35'),(2,1,4,'2019-09-28 23:39:15'),(4,4,7,'2019-11-03 10:30:47'),(5,3,4,'2019-11-04 00:35:46'),(14,7,13,'2019-12-16 12:13:08'),(37,16,13,'2019-12-17 15:01:21'),(40,10,16,'2019-12-18 02:13:51'),(41,10,13,'2019-12-18 02:18:25'),(44,13,7,'2019-12-19 16:22:24'),(45,13,10,'2019-12-19 16:22:27'),(46,7,16,'2019-12-19 18:48:32'),(47,7,10,'2019-12-19 18:48:38');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
