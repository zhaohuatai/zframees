/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50509
Source Host           : localhost:3306
Source Database       : zhtframework

Target Server Type    : MYSQL
Target Server Version : 50509
File Encoding         : 65001

Date: 2015-04-15 08:40:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for demo
-- ----------------------------
DROP TABLE IF EXISTS `demo`;
CREATE TABLE `demo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Z_bigDecimal` decimal(20,10) DEFAULT NULL,
  `z_integer` int(11) NOT NULL,
  `name` varchar(0) NOT NULL,
  `z_boolean` bit(1) DEFAULT NULL,
  `z_byte` tinyint(4) DEFAULT NULL,
  `z_date` date DEFAULT NULL,
  `z_dateTime` datetime DEFAULT NULL,
  `z_double` double DEFAULT NULL,
  `z_timeStamp` datetime DEFAULT NULL,
  `z_bytes` tinyblob NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_56eyw6xwcwb4bi5y6e54pqnys` (`z_integer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of demo
-- ----------------------------

-- ----------------------------
-- Table structure for demomany
-- ----------------------------
DROP TABLE IF EXISTS `demomany`;
CREATE TABLE `demomany` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(0) NOT NULL,
  `demo_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_4siwhljy662kwsi7xvjmajmgw` (`demo_id`),
  CONSTRAINT `FK_4siwhljy662kwsi7xvjmajmgw` FOREIGN KEY (`demo_id`) REFERENCES `demo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of demomany
-- ----------------------------

-- ----------------------------
-- Table structure for demotree
-- ----------------------------
DROP TABLE IF EXISTS `demotree`;
CREATE TABLE `demotree` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(0) NOT NULL,
  `PDemoTree_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_8axlsxcgpyssh4c990mbadrxj` (`PDemoTree_id`),
  CONSTRAINT `FK_8axlsxcgpyssh4c990mbadrxj` FOREIGN KEY (`PDemoTree_id`) REFERENCES `demotree` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of demotree
-- ----------------------------

-- ----------------------------
-- Table structure for demo_user
-- ----------------------------
DROP TABLE IF EXISTS `demo_user`;
CREATE TABLE `demo_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `description` varchar(30) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `full_time` datetime DEFAULT NULL,
  `loginname` varchar(60) NOT NULL,
  `password` varchar(60) NOT NULL,
  `time_only` time DEFAULT NULL,
  `time_stamp` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_t9ehegotddnmogclyd5ch25xr` (`loginname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of demo_user
-- ----------------------------

-- ----------------------------
-- Table structure for demo_user_2
-- ----------------------------
DROP TABLE IF EXISTS `demo_user_2`;
CREATE TABLE `demo_user_2` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `description` varchar(30) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `full_time` datetime DEFAULT NULL,
  `loginname` varchar(60) NOT NULL,
  `password` varchar(60) NOT NULL,
  `time_only` time DEFAULT NULL,
  `time_stamp` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_5r2h5l2lmx0p2mpaa8n8nbd0o` (`loginname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of demo_user_2
-- ----------------------------

-- ----------------------------
-- Table structure for info_personinfo
-- ----------------------------
DROP TABLE IF EXISTS `info_personinfo`;
CREATE TABLE `info_personinfo` (
  `personId` bigint(20) NOT NULL,
  `cardTypeCode` varchar(3) DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `famPostcalCode` varchar(6) DEFAULT NULL,
  `famTelephone` varchar(15) DEFAULT NULL,
  `famillyAddress` varchar(50) DEFAULT NULL,
  `genderCode` varchar(5) NOT NULL,
  `isMarried` int(11) DEFAULT NULL,
  `mobilePhone` varchar(15) DEFAULT NULL,
  `MSN` varchar(50) DEFAULT NULL,
  `nameSpell` varchar(60) DEFAULT NULL,
  `nationId` bigint(20) DEFAULT NULL,
  `nativeCity` varchar(30) DEFAULT NULL,
  `nativeCounty` varchar(30) DEFAULT NULL,
  `nativeProvince` varchar(20) DEFAULT NULL,
  `peopleId` bigint(20) DEFAULT NULL,
  `perAddress` varchar(50) DEFAULT NULL,
  `perBirthday` date DEFAULT NULL,
  `perEnglishName` varchar(60) DEFAULT NULL,
  `perIdCard` varchar(18) DEFAULT NULL,
  `perName` varchar(50) NOT NULL,
  `perNum` varchar(20) NOT NULL,
  `perPostalCode` varchar(6) DEFAULT NULL,
  `perTelephone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`personId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of info_personinfo
-- ----------------------------

-- ----------------------------
-- Table structure for many2manymaster
-- ----------------------------
DROP TABLE IF EXISTS `many2manymaster`;
CREATE TABLE `many2manymaster` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(0) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of many2manymaster
-- ----------------------------

-- ----------------------------
-- Table structure for many2manyslaver
-- ----------------------------
DROP TABLE IF EXISTS `many2manyslaver`;
CREATE TABLE `many2manyslaver` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(0) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of many2manyslaver
-- ----------------------------

-- ----------------------------
-- Table structure for one2onemarster
-- ----------------------------
DROP TABLE IF EXISTS `one2onemarster`;
CREATE TABLE `one2onemarster` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of one2onemarster
-- ----------------------------

-- ----------------------------
-- Table structure for one2oneslaver
-- ----------------------------
DROP TABLE IF EXISTS `one2oneslaver`;
CREATE TABLE `one2oneslaver` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(0) NOT NULL,
  `marster_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_bnk7p4vrvw06emw3fabauunsv` (`marster_id`),
  CONSTRAINT `FK_bnk7p4vrvw06emw3fabauunsv` FOREIGN KEY (`marster_id`) REFERENCES `one2onemarster` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of one2oneslaver
-- ----------------------------

-- ----------------------------
-- Table structure for rbac_group
-- ----------------------------
DROP TABLE IF EXISTS `rbac_group`;
CREATE TABLE `rbac_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `briefname` varchar(30) DEFAULT NULL,
  `description` varchar(60) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `name` varchar(30) NOT NULL,
  `creater` varchar(30) DEFAULT NULL,
  `rbac_group_id` bigint(20) DEFAULT NULL,
  `rbac_role_id` bigint(20) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_kwgco1fnx3ufdttlfaxyb67o3` (`rbac_group_id`),
  KEY `FK_r6qungqkes8wu8b9cx5l3rvcn` (`rbac_role_id`),
  KEY `FK_n6ua1pidq1wl6rnvh4g66y43t` (`parent_id`),
  CONSTRAINT `FK_kwgco1fnx3ufdttlfaxyb67o3` FOREIGN KEY (`rbac_group_id`) REFERENCES `rbac_group` (`id`),
  CONSTRAINT `FK_n6ua1pidq1wl6rnvh4g66y43t` FOREIGN KEY (`parent_id`) REFERENCES `rbac_group` (`id`),
  CONSTRAINT `FK_r6qungqkes8wu8b9cx5l3rvcn` FOREIGN KEY (`rbac_role_id`) REFERENCES `rbac_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rbac_group
-- ----------------------------
INSERT INTO `rbac_group` VALUES ('6', '2015-03-30 16:08:55', null, '', '', 'AAA', 'AAA', null, null, null);
INSERT INTO `rbac_group` VALUES ('7', '2015-03-30 16:11:39', null, '', '', 'AAAAA', 'ssss', null, null, '6');
INSERT INTO `rbac_group` VALUES ('8', '2015-04-12 16:27:07', null, '', '', 'VVV', 'VVV', null, null, '6');

-- ----------------------------
-- Table structure for rbac_group_role
-- ----------------------------
DROP TABLE IF EXISTS `rbac_group_role`;
CREATE TABLE `rbac_group_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rbac_group_id` bigint(20) DEFAULT NULL,
  `rbac_role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_4vjgjkn2be1qo20gu3wiwl1it` (`rbac_group_id`),
  KEY `FK_70ok1ehn84gavlujb11svnx0n` (`rbac_role_id`),
  CONSTRAINT `FK_4vjgjkn2be1qo20gu3wiwl1it` FOREIGN KEY (`rbac_group_id`) REFERENCES `rbac_group` (`id`),
  CONSTRAINT `FK_70ok1ehn84gavlujb11svnx0n` FOREIGN KEY (`rbac_role_id`) REFERENCES `rbac_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rbac_group_role
-- ----------------------------

-- ----------------------------
-- Table structure for rbac_group_user
-- ----------------------------
DROP TABLE IF EXISTS `rbac_group_user`;
CREATE TABLE `rbac_group_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rbac_group_id` bigint(20) DEFAULT NULL,
  `rbac_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_67ks4n9atg55gn76yef48u84p` (`rbac_group_id`),
  KEY `FK_5ut3lud8anq3fj19cbkwe8mj` (`rbac_user_id`),
  CONSTRAINT `FK_5ut3lud8anq3fj19cbkwe8mj` FOREIGN KEY (`rbac_user_id`) REFERENCES `rbac_user` (`id`),
  CONSTRAINT `FK_67ks4n9atg55gn76yef48u84p` FOREIGN KEY (`rbac_group_id`) REFERENCES `rbac_group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rbac_group_user
-- ----------------------------

-- ----------------------------
-- Table structure for rbac_menu
-- ----------------------------
DROP TABLE IF EXISTS `rbac_menu`;
CREATE TABLE `rbac_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(60) DEFAULT NULL,
  `dis_index` int(11) DEFAULT NULL,
  `display` varchar(40) NOT NULL,
  `iconCls` varchar(40) NOT NULL,
  `tab_title` varchar(40) DEFAULT NULL,
  `type` varchar(1) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `module_id` bigint(20) DEFAULT NULL,
  `permission_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ju6jky12gr1by7l33ovxjsq9v` (`parent_id`),
  KEY `FK_1rnf48c8w233wo383tap4tk65` (`module_id`),
  KEY `FK_byhoy18k197a1p7pietbfkdb4` (`permission_id`),
  CONSTRAINT `FK_1rnf48c8w233wo383tap4tk65` FOREIGN KEY (`module_id`) REFERENCES `rbac_module` (`id`),
  CONSTRAINT `FK_byhoy18k197a1p7pietbfkdb4` FOREIGN KEY (`permission_id`) REFERENCES `rbac_permission` (`id`),
  CONSTRAINT `FK_ju6jky12gr1by7l33ovxjsq9v` FOREIGN KEY (`parent_id`) REFERENCES `rbac_menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rbac_menu
-- ----------------------------
INSERT INTO `rbac_menu` VALUES ('0', '系统菜单', '1', '系统菜单', '', '', 'M', null, null, null);
INSERT INTO `rbac_menu` VALUES ('1', '权限管理', '1', '权限管理', 'icon-add', '', 'G', '0', '1', null);
INSERT INTO `rbac_menu` VALUES ('2', '许可维护', '2', '许可维护', '', '许可维护', 'M', '1', '1', '8');
INSERT INTO `rbac_menu` VALUES ('3', '菜单维护', '1', '菜单维护', '', '菜单维护', 'M', '1', '1', '2');
INSERT INTO `rbac_menu` VALUES ('4', '角色维护', '3', '角色维护', '', '角色维护', 'M', '1', '1', '14');
INSERT INTO `rbac_menu` VALUES ('5', '用户维护', '4', '用户维护', '', '用户维护', 'M', '1', '1', '17');
INSERT INTO `rbac_menu` VALUES ('6', '用户组维护', '5', '用户组维护', '', '用户组维护', 'M', '1', '1', '15');
INSERT INTO `rbac_menu` VALUES ('7', '部门维护', '6', '部门维护', '', '部门维护', 'M', '1', '1', '16');
INSERT INTO `rbac_menu` VALUES ('8', '代码生成', '7', '代码生成', '', '代码生成', 'M', '1', '1', '18');
INSERT INTO `rbac_menu` VALUES ('9', '代码生成2', '8', '代码生成2', '', '代码生成2', 'M', '1', '1', '19');
INSERT INTO `rbac_menu` VALUES ('10', '系统管理', '2', '系统管理', '', '系统管理', 'G', '0', '1', null);

-- ----------------------------
-- Table structure for rbac_module
-- ----------------------------
DROP TABLE IF EXISTS `rbac_module`;
CREATE TABLE `rbac_module` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dis_index` int(11) NOT NULL,
  `display` varchar(32) NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  `icon` varchar(20) DEFAULT NULL,
  `remark` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rbac_module
-- ----------------------------
INSERT INTO `rbac_module` VALUES ('1', '0', '模块1', '1', null, '模块1');
INSERT INTO `rbac_module` VALUES ('2', '1', '模块2', '1', null, '模块2');

-- ----------------------------
-- Table structure for rbac_module_menu
-- ----------------------------
DROP TABLE IF EXISTS `rbac_module_menu`;
CREATE TABLE `rbac_module_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rbac_menu_id` bigint(20) DEFAULT NULL,
  `rbac_module_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_lnmh36m9o9o3fkjltvck7bim8` (`rbac_menu_id`),
  KEY `FK_65qssxay1k00rjngexjdt09f5` (`rbac_module_id`),
  CONSTRAINT `FK_65qssxay1k00rjngexjdt09f5` FOREIGN KEY (`rbac_module_id`) REFERENCES `rbac_module` (`id`),
  CONSTRAINT `FK_lnmh36m9o9o3fkjltvck7bim8` FOREIGN KEY (`rbac_menu_id`) REFERENCES `rbac_menu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rbac_module_menu
-- ----------------------------

-- ----------------------------
-- Table structure for rbac_permission
-- ----------------------------
DROP TABLE IF EXISTS `rbac_permission`;
CREATE TABLE `rbac_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(128) NOT NULL,
  `description` varchar(60) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `name` varchar(40) NOT NULL,
  `type` varchar(1) NOT NULL,
  `url` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_3v94kwepfosn7jqeo9hluid6f` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rbac_permission
-- ----------------------------
INSERT INTO `rbac_permission` VALUES ('2', 'RbacMenu:enterMenuPage', '菜单维护', '', '资源维护', 'M', 'rbac/menu/enterMenuPage');
INSERT INTO `rbac_permission` VALUES ('8', 'enterPermissionPage', '权限维护', '', '权限维护', 'M', 'rbac/permission/enterPermissionPage');
INSERT INTO `rbac_permission` VALUES ('14', 'enterRolePage', '角色维护', '', '角色维护', 'M', 'rbac/role/enterRolePage');
INSERT INTO `rbac_permission` VALUES ('15', 'enterGroupPage', '用户组维护', '', '用户组维护', 'M', 'rbac/group/enterGroupPage');
INSERT INTO `rbac_permission` VALUES ('16', 'enterDepartmentPage', '部门维护', '', '部门维护', 'M', 'rbac/sysDepartment/enterDepartmentPage');
INSERT INTO `rbac_permission` VALUES ('17', 'enterUserPage', '用户维护', '', '用户维护', 'M', 'rbac/user/enterUserPage');
INSERT INTO `rbac_permission` VALUES ('18', 'enterGenEntityPage', '代码生成', '', '代码生成', 'M', 'common/generator/genEntity/enterGenEntityPage');
INSERT INTO `rbac_permission` VALUES ('19', 'enterEntityPage', '代码生成2', '', '代码生成2', 'M', 'generator/genEntity/enterEntityPage');
INSERT INTO `rbac_permission` VALUES ('23', 'RbacGroup:addGroup', null, '', 'SCAN', 'P', '/rbac/group/addGroup');
INSERT INTO `rbac_permission` VALUES ('24', 'RbacGroup:loadRoleGridForGroupRoleAssign', null, '', 'SCAN', 'P', '/rbac/group/loadRoleGridForGroupRoleAssign');
INSERT INTO `rbac_permission` VALUES ('25', 'RbacGroup:loadUserGridForUserAssign', null, '', 'SCAN', 'P', '/rbac/group/loadUserGridForUserAssign');
INSERT INTO `rbac_permission` VALUES ('26', 'RbacGroup:loadGroupCombotree', null, '', 'SCAN', 'P', '/rbac/group/loadGroupCombotree');
INSERT INTO `rbac_permission` VALUES ('27', 'RbacGroup:removeRolesFromGroupRole', null, '', 'SCAN', 'P', '/rbac/group/removeRolesFromGroupRole');
INSERT INTO `rbac_permission` VALUES ('28', 'RbacGroup:removeUserFromGroup', null, '', 'SCAN', 'P', '/rbac/group/removeUserFromGroup');
INSERT INTO `rbac_permission` VALUES ('29', 'RbacGroup:addRolesToGroupRole', null, '', 'SCAN', 'P', '/rbac/group/addRolesToGroupRole');
INSERT INTO `rbac_permission` VALUES ('30', 'RbacGroup:loadGroupTreeGrid', null, '', 'SCAN', 'P', '/rbac/group/loadGroupTreeGrid');
INSERT INTO `rbac_permission` VALUES ('31', 'RbacGroup:enterRoleAssignment', null, '', 'SCAN', 'P', '/rbac/group/enterRoleAssignment');
INSERT INTO `rbac_permission` VALUES ('32', 'RbacGroup:enterUserAssignment', null, '', 'SCAN', 'P', '/rbac/group/enterUserAssignment');
INSERT INTO `rbac_permission` VALUES ('33', 'RbacGroup:addUserToGroup', null, '', 'SCAN', 'P', '/rbac/group/addUserToGroup');
INSERT INTO `rbac_permission` VALUES ('34', 'RbacGroup:enterAddGroup', null, '', 'SCAN', 'P', '/rbac/group/enterAddGroup');
INSERT INTO `rbac_permission` VALUES ('35', 'RbacGroup:enterGroupPage', null, '', 'SCAN', 'P', '/rbac/group/enterGroupPage');
INSERT INTO `rbac_permission` VALUES ('36', 'RbacGroup:enterEidtGroup', null, '', 'SCAN', 'P', '/rbac/group/enterEidtGroup');
INSERT INTO `rbac_permission` VALUES ('37', 'RbacGroup:updateGroup', null, '', 'SCAN', 'P', '/rbac/group/updateGroup');
INSERT INTO `rbac_permission` VALUES ('38', 'RbacGroup:deleteGroup', null, '', 'SCAN', 'P', '/rbac/group/deleteGroup');
INSERT INTO `rbac_permission` VALUES ('39', 'RbacPermission:addPermission', null, '', 'SCAN', 'P', '/rbac/permission/addPermission');
INSERT INTO `rbac_permission` VALUES ('40', 'RbacPermission:loadPermissionGridView', null, '', 'SCAN', 'P', '/rbac/permission/loadPermissionGridView');
INSERT INTO `rbac_permission` VALUES ('41', 'RbacPermission:enterAddPermission', null, '', 'SCAN', 'P', '/rbac/permission/enterAddPermission');
INSERT INTO `rbac_permission` VALUES ('42', 'RbacPermission:enterUpdatePermission', null, '', 'SCAN', 'P', '/rbac/permission/enterUpdatePermission');
INSERT INTO `rbac_permission` VALUES ('43', 'RbacPermission:enterPermissionPage', null, '', 'SCAN', 'P', '/rbac/permission/enterPermissionPage');
INSERT INTO `rbac_permission` VALUES ('44', 'RbacPermission:deletePermission', null, '', 'SCAN', 'P', '/rbac/permission/deletePermission');
INSERT INTO `rbac_permission` VALUES ('45', 'RbacPermission:updatePermission', null, '', 'SCAN', 'P', '/rbac/permission/updatePermission');
INSERT INTO `rbac_permission` VALUES ('46', 'RbacRole:addRole', null, '', 'SCAN', 'P', '/rbac/role/addRole');
INSERT INTO `rbac_permission` VALUES ('47', 'RbacRole:loadPermissionForRoleAssign', null, '', 'SCAN', 'P', '/rbac/role/loadPermissionForRoleAssign');
INSERT INTO `rbac_permission` VALUES ('48', 'RbacRole:removePermissionsFromRole', null, '', 'SCAN', 'P', '/rbac/role/removePermissionsFromRole');
INSERT INTO `rbac_permission` VALUES ('49', 'RbacRole:enterPermissionAssignment', null, '', 'SCAN', 'P', '/rbac/role/enterPermissionAssignment');
INSERT INTO `rbac_permission` VALUES ('50', 'RbacRole:addPermissionsToRole', null, '', 'SCAN', 'P', '/rbac/role/addPermissionsToRole');
INSERT INTO `rbac_permission` VALUES ('51', 'RbacRole:enterAddRole', null, '', 'SCAN', 'P', '/rbac/role/enterAddRole');
INSERT INTO `rbac_permission` VALUES ('52', 'RbacRole:updateRole', null, '', 'SCAN', 'P', '/rbac/role/updateRole');
INSERT INTO `rbac_permission` VALUES ('53', 'RbacRole:loadRoleGridView', null, '', 'SCAN', 'P', '/rbac/role/loadRoleGridView');
INSERT INTO `rbac_permission` VALUES ('54', 'RbacRole:deleteRole', null, '', 'SCAN', 'P', '/rbac/role/deleteRole');
INSERT INTO `rbac_permission` VALUES ('55', 'RbacRole:enterRolePage', null, '', 'SCAN', 'P', '/rbac/role/enterRolePage');
INSERT INTO `rbac_permission` VALUES ('56', 'RbacRole:enterUpdateRole', null, '', 'SCAN', 'P', '/rbac/role/enterUpdateRole');
INSERT INTO `rbac_permission` VALUES ('57', 'RbacUser:addPermissionsToUserPermission', null, '', 'SCAN', 'P', '/rbac/user/addPermissionsToUserPermission');
INSERT INTO `rbac_permission` VALUES ('58', 'RbacUser:removeRolesFromUserRoleReject', null, '', 'SCAN', 'P', '/rbac/user/removeRolesFromUserRoleReject');
INSERT INTO `rbac_permission` VALUES ('59', 'RbacUser:enterUserPermissionAssignment', null, '', 'SCAN', 'P', '/rbac/user/enterUserPermissionAssignment');
INSERT INTO `rbac_permission` VALUES ('60', 'RbacUser:loadRoleGridForUserRoleAssign', null, '', 'SCAN', 'P', '/rbac/user/loadRoleGridForUserRoleAssign');
INSERT INTO `rbac_permission` VALUES ('61', 'RbacUser:loadRoleCodeUserHaveForCombox', null, '', 'SCAN', 'P', '/rbac/user/loadRoleCodeUserHaveForCombox');
INSERT INTO `rbac_permission` VALUES ('62', 'RbacUser:addUser', null, '', 'SCAN', 'P', '/rbac/user/addUser');
INSERT INTO `rbac_permission` VALUES ('63', 'RbacUser:addPermissionsToUserPermissionReject', null, '', 'SCAN', 'P', '/rbac/user/addPermissionsToUserPermissionReject');
INSERT INTO `rbac_permission` VALUES ('64', 'RbacUser:removePermissionsFromUserPermission', null, '', 'SCAN', 'P', '/rbac/user/removePermissionsFromUserPermission');
INSERT INTO `rbac_permission` VALUES ('65', 'RbacUser:removePermissionsFromUserPermissionReject', null, '', 'SCAN', 'P', '/rbac/user/removePermissionsFromUserPermissionReject');
INSERT INTO `rbac_permission` VALUES ('66', 'RbacUser:loadPermissionGridForUserPermissionAssign', null, '', 'SCAN', 'P', '/rbac/user/loadPermissionGridForUserPermissionAssign');
INSERT INTO `rbac_permission` VALUES ('67', 'RbacUser:removeRolesFromUserRole', null, '', 'SCAN', 'P', '/rbac/user/removeRolesFromUserRole');
INSERT INTO `rbac_permission` VALUES ('68', 'RbacUser:addRolesToUserRoleReject', null, '', 'SCAN', 'P', '/rbac/user/addRolesToUserRoleReject');
INSERT INTO `rbac_permission` VALUES ('69', 'RbacUser:addRolesToUserRole', null, '', 'SCAN', 'P', '/rbac/user/addRolesToUserRole');
INSERT INTO `rbac_permission` VALUES ('70', 'RbacUser:enterRoleAssignment', null, '', 'SCAN', 'P', '/rbac/user/enterRoleAssignment');
INSERT INTO `rbac_permission` VALUES ('71', 'RbacUser:loadUserGridView', null, '', 'SCAN', 'P', '/rbac/user/loadUserGridView');
INSERT INTO `rbac_permission` VALUES ('72', 'RbacUser:enterUpdateUser', null, '', 'SCAN', 'P', '/rbac/user/enterUpdateUser');
INSERT INTO `rbac_permission` VALUES ('73', 'RbacUser:updateUser', null, '', 'SCAN', 'P', '/rbac/user/updateUser');
INSERT INTO `rbac_permission` VALUES ('74', 'RbacUser:enterAddUser', null, '', 'SCAN', 'P', '/rbac/user/enterAddUser');
INSERT INTO `rbac_permission` VALUES ('75', 'RbacUser:enterUserPage', null, '', 'SCAN', 'P', '/rbac/user/enterUserPage');
INSERT INTO `rbac_permission` VALUES ('76', 'RbacUser:deleteUser', null, '', 'SCAN', 'P', '/rbac/user/deleteUser');
INSERT INTO `rbac_permission` VALUES ('77', 'SysDepartment:addRolesToSysDepartmentRole', null, '', 'SCAN', 'P', '/rbac/sysDepartment/addRolesToSysDepartmentRole');
INSERT INTO `rbac_permission` VALUES ('78', 'SysDepartment:removeRolesFromSysDepartmentRole', null, '', 'SCAN', 'P', '/rbac/sysDepartment/removeRolesFromSysDepartmentRole');
INSERT INTO `rbac_permission` VALUES ('79', 'SysDepartment:removeUserFromSysDepartment', null, '', 'SCAN', 'P', '/rbac/sysDepartment/removeUserFromSysDepartment');
INSERT INTO `rbac_permission` VALUES ('80', 'SysDepartment:loadSysDepartmentTreeGrid', null, '', 'SCAN', 'P', '/rbac/sysDepartment/loadSysDepartmentTreeGrid');
INSERT INTO `rbac_permission` VALUES ('81', 'SysDepartment:loadSysDepartmentCombotree', null, '', 'SCAN', 'P', '/rbac/sysDepartment/loadSysDepartmentCombotree');
INSERT INTO `rbac_permission` VALUES ('82', 'SysDepartment:loadRoleGridForSysDepartmentRoleAssign', null, '', 'SCAN', 'P', '/rbac/sysDepartment/loadRoleGridForSysDepartmentRoleAssign');
INSERT INTO `rbac_permission` VALUES ('83', 'SysDepartment:loadUserGridViewIsInSysDepartment', null, '', 'SCAN', 'P', '/rbac/sysDepartment/loadUserGridViewIsInSysDepartment');
INSERT INTO `rbac_permission` VALUES ('84', 'SysDepartment:addUserToSysDepartment', null, '', 'SCAN', 'P', '/rbac/sysDepartment/addUserToSysDepartment');
INSERT INTO `rbac_permission` VALUES ('85', 'SysDepartment:deleteSysDepartment', null, '', 'SCAN', 'P', '/rbac/sysDepartment/deleteSysDepartment');
INSERT INTO `rbac_permission` VALUES ('86', 'SysDepartment:enterRoleAssignment', null, '', 'SCAN', 'P', '/rbac/sysDepartment/enterRoleAssignment');
INSERT INTO `rbac_permission` VALUES ('87', 'SysDepartment:enterUserAssignment', null, '', 'SCAN', 'P', '/rbac/sysDepartment/enterUserAssignment');
INSERT INTO `rbac_permission` VALUES ('88', 'SysDepartment:enterAddSysDepartment', null, '', 'SCAN', 'P', '/rbac/sysDepartment/enterAddSysDepartment');
INSERT INTO `rbac_permission` VALUES ('89', 'SysDepartment:enterEidtSysDepartment', null, '', 'SCAN', 'P', '/rbac/sysDepartment/enterEidtSysDepartment');
INSERT INTO `rbac_permission` VALUES ('90', 'SysDepartment:enterSysDepartmentPage', null, '', 'SCAN', 'P', '/rbac/sysDepartment/enterSysDepartmentPage');
INSERT INTO `rbac_permission` VALUES ('91', 'SysDepartment:updateSysDepartment', null, '', 'SCAN', 'P', '/rbac/sysDepartment/updateSysDepartment');
INSERT INTO `rbac_permission` VALUES ('92', 'SysDepartment:addSysDepartment', null, '', 'SCAN', 'P', '/rbac/sysDepartment/addSysDepartment');
INSERT INTO `rbac_permission` VALUES ('93', 'RbacMenu:loadMenuTreeGrid', '', '', '加载菜单数据', 'P', '/rbac/menu/enterMenuPage');

-- ----------------------------
-- Table structure for rbac_role
-- ----------------------------
DROP TABLE IF EXISTS `rbac_role`;
CREATE TABLE `rbac_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(40) NOT NULL,
  `description` varchar(60) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `name` varchar(40) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_i1vjocjdvpqum2aihn4e7g9f0` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of rbac_role
-- ----------------------------
INSERT INTO `rbac_role` VALUES ('1', 'admin', 'admin', '', '管理员');

-- ----------------------------
-- Table structure for rbac_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `rbac_role_permission`;
CREATE TABLE `rbac_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `permission_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_plmmpet3fn54pbdvqxdi46pjn` (`permission_id`),
  KEY `FK_4u42et26qiarjienjpr2fxark` (`role_id`),
  CONSTRAINT `FK_4u42et26qiarjienjpr2fxark` FOREIGN KEY (`role_id`) REFERENCES `rbac_role` (`id`),
  CONSTRAINT `FK_plmmpet3fn54pbdvqxdi46pjn` FOREIGN KEY (`permission_id`) REFERENCES `rbac_permission` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=164 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rbac_role_permission
-- ----------------------------
INSERT INTO `rbac_role_permission` VALUES ('36', '16', '1');
INSERT INTO `rbac_role_permission` VALUES ('37', '19', '1');
INSERT INTO `rbac_role_permission` VALUES ('38', '18', '1');
INSERT INTO `rbac_role_permission` VALUES ('39', '15', '1');
INSERT INTO `rbac_role_permission` VALUES ('40', '8', '1');
INSERT INTO `rbac_role_permission` VALUES ('41', '2', '1');
INSERT INTO `rbac_role_permission` VALUES ('42', '14', '1');
INSERT INTO `rbac_role_permission` VALUES ('43', '17', '1');
INSERT INTO `rbac_role_permission` VALUES ('44', '23', '1');
INSERT INTO `rbac_role_permission` VALUES ('45', '29', '1');
INSERT INTO `rbac_role_permission` VALUES ('46', '33', '1');
INSERT INTO `rbac_role_permission` VALUES ('47', '38', '1');
INSERT INTO `rbac_role_permission` VALUES ('48', '34', '1');
INSERT INTO `rbac_role_permission` VALUES ('49', '36', '1');
INSERT INTO `rbac_role_permission` VALUES ('50', '35', '1');
INSERT INTO `rbac_role_permission` VALUES ('51', '31', '1');
INSERT INTO `rbac_role_permission` VALUES ('52', '32', '1');
INSERT INTO `rbac_role_permission` VALUES ('53', '26', '1');
INSERT INTO `rbac_role_permission` VALUES ('54', '30', '1');
INSERT INTO `rbac_role_permission` VALUES ('55', '24', '1');
INSERT INTO `rbac_role_permission` VALUES ('56', '25', '1');
INSERT INTO `rbac_role_permission` VALUES ('57', '27', '1');
INSERT INTO `rbac_role_permission` VALUES ('58', '28', '1');
INSERT INTO `rbac_role_permission` VALUES ('59', '37', '1');
INSERT INTO `rbac_role_permission` VALUES ('60', '39', '1');
INSERT INTO `rbac_role_permission` VALUES ('61', '44', '1');
INSERT INTO `rbac_role_permission` VALUES ('62', '41', '1');
INSERT INTO `rbac_role_permission` VALUES ('63', '43', '1');
INSERT INTO `rbac_role_permission` VALUES ('64', '42', '1');
INSERT INTO `rbac_role_permission` VALUES ('65', '40', '1');
INSERT INTO `rbac_role_permission` VALUES ('66', '45', '1');
INSERT INTO `rbac_role_permission` VALUES ('67', '50', '1');
INSERT INTO `rbac_role_permission` VALUES ('68', '46', '1');
INSERT INTO `rbac_role_permission` VALUES ('69', '54', '1');
INSERT INTO `rbac_role_permission` VALUES ('70', '51', '1');
INSERT INTO `rbac_role_permission` VALUES ('71', '49', '1');
INSERT INTO `rbac_role_permission` VALUES ('72', '55', '1');
INSERT INTO `rbac_role_permission` VALUES ('73', '56', '1');
INSERT INTO `rbac_role_permission` VALUES ('74', '47', '1');
INSERT INTO `rbac_role_permission` VALUES ('75', '53', '1');
INSERT INTO `rbac_role_permission` VALUES ('76', '48', '1');
INSERT INTO `rbac_role_permission` VALUES ('77', '52', '1');
INSERT INTO `rbac_role_permission` VALUES ('78', '57', '1');
INSERT INTO `rbac_role_permission` VALUES ('79', '63', '1');
INSERT INTO `rbac_role_permission` VALUES ('80', '69', '1');
INSERT INTO `rbac_role_permission` VALUES ('81', '68', '1');
INSERT INTO `rbac_role_permission` VALUES ('82', '62', '1');
INSERT INTO `rbac_role_permission` VALUES ('83', '76', '1');
INSERT INTO `rbac_role_permission` VALUES ('84', '74', '1');
INSERT INTO `rbac_role_permission` VALUES ('85', '70', '1');
INSERT INTO `rbac_role_permission` VALUES ('86', '72', '1');
INSERT INTO `rbac_role_permission` VALUES ('87', '75', '1');
INSERT INTO `rbac_role_permission` VALUES ('88', '59', '1');
INSERT INTO `rbac_role_permission` VALUES ('89', '66', '1');
INSERT INTO `rbac_role_permission` VALUES ('90', '61', '1');
INSERT INTO `rbac_role_permission` VALUES ('91', '60', '1');
INSERT INTO `rbac_role_permission` VALUES ('92', '71', '1');
INSERT INTO `rbac_role_permission` VALUES ('93', '64', '1');
INSERT INTO `rbac_role_permission` VALUES ('94', '65', '1');
INSERT INTO `rbac_role_permission` VALUES ('95', '67', '1');
INSERT INTO `rbac_role_permission` VALUES ('96', '58', '1');
INSERT INTO `rbac_role_permission` VALUES ('97', '73', '1');
INSERT INTO `rbac_role_permission` VALUES ('98', '77', '1');
INSERT INTO `rbac_role_permission` VALUES ('99', '92', '1');
INSERT INTO `rbac_role_permission` VALUES ('100', '84', '1');
INSERT INTO `rbac_role_permission` VALUES ('101', '85', '1');
INSERT INTO `rbac_role_permission` VALUES ('102', '88', '1');
INSERT INTO `rbac_role_permission` VALUES ('103', '89', '1');
INSERT INTO `rbac_role_permission` VALUES ('104', '86', '1');
INSERT INTO `rbac_role_permission` VALUES ('105', '90', '1');
INSERT INTO `rbac_role_permission` VALUES ('106', '87', '1');
INSERT INTO `rbac_role_permission` VALUES ('107', '82', '1');
INSERT INTO `rbac_role_permission` VALUES ('108', '81', '1');
INSERT INTO `rbac_role_permission` VALUES ('109', '80', '1');
INSERT INTO `rbac_role_permission` VALUES ('110', '83', '1');
INSERT INTO `rbac_role_permission` VALUES ('111', '78', '1');
INSERT INTO `rbac_role_permission` VALUES ('112', '79', '1');
INSERT INTO `rbac_role_permission` VALUES ('113', '91', '1');
INSERT INTO `rbac_role_permission` VALUES ('163', '93', '1');

-- ----------------------------
-- Table structure for rbac_user
-- ----------------------------
DROP TABLE IF EXISTS `rbac_user`;
CREATE TABLE `rbac_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `accountNonExpired` bit(1) NOT NULL,
  `accountNonLocked` bit(1) NOT NULL,
  `alias` varchar(30) DEFAULT NULL,
  `credentialsNonExpired` bit(1) NOT NULL,
  `description` varchar(30) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `password` varchar(36) DEFAULT NULL,
  `salt` varchar(70) DEFAULT NULL,
  `defaultRoleId` bigint(20) DEFAULT NULL,
  `userName` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_by5js5d3ceusq6w4xth38hck2` (`userName`),
  KEY `FK_eob4je1x1u4dxljrmxpb5ob6t` (`defaultRoleId`),
  CONSTRAINT `FK_eob4je1x1u4dxljrmxpb5ob6t` FOREIGN KEY (`defaultRoleId`) REFERENCES `rbac_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户';

-- ----------------------------
-- Records of rbac_user
-- ----------------------------
INSERT INTO `rbac_user` VALUES ('1', '\0', '\0', null, '\0', 'admin', '', '692662a239ff966411a04d3284e09629', '3296315903979458szaisRTKzxqLFfcMf5mkS', '1', 'admin');
INSERT INTO `rbac_user` VALUES ('2', '\0', '\0', null, '\0', 'shiro', '', 'fcebae64f7eb438d32e4b2acaa32e7b0', '344311410257492LwjFqXdDLnqkpnK9zYuVya', null, 'shiro');

-- ----------------------------
-- Table structure for rbac_user_permission
-- ----------------------------
DROP TABLE IF EXISTS `rbac_user_permission`;
CREATE TABLE `rbac_user_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rbac_rbacPermission_id` bigint(20) DEFAULT NULL,
  `rbac_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_q4h1nse4qsyxgusi9ne1v173w` (`rbac_rbacPermission_id`),
  KEY `FK_6vwcup29h9982xq2uux8g7g35` (`rbac_user_id`),
  CONSTRAINT `FK_6vwcup29h9982xq2uux8g7g35` FOREIGN KEY (`rbac_user_id`) REFERENCES `rbac_user` (`id`),
  CONSTRAINT `FK_q4h1nse4qsyxgusi9ne1v173w` FOREIGN KEY (`rbac_rbacPermission_id`) REFERENCES `rbac_permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rbac_user_permission
-- ----------------------------

-- ----------------------------
-- Table structure for rbac_user_permission_reject
-- ----------------------------
DROP TABLE IF EXISTS `rbac_user_permission_reject`;
CREATE TABLE `rbac_user_permission_reject` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rbac_rbacPermission_id` bigint(20) DEFAULT NULL,
  `rbac_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_1q1w7amolu1eyibmpni2wk60u` (`rbac_rbacPermission_id`),
  KEY `FK_9aa640a758snmr0l0u6lot1wt` (`rbac_user_id`),
  CONSTRAINT `FK_1q1w7amolu1eyibmpni2wk60u` FOREIGN KEY (`rbac_rbacPermission_id`) REFERENCES `rbac_permission` (`id`),
  CONSTRAINT `FK_9aa640a758snmr0l0u6lot1wt` FOREIGN KEY (`rbac_user_id`) REFERENCES `rbac_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rbac_user_permission_reject
-- ----------------------------

-- ----------------------------
-- Table structure for rbac_user_role
-- ----------------------------
DROP TABLE IF EXISTS `rbac_user_role`;
CREATE TABLE `rbac_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rbac_role_id` bigint(20) DEFAULT NULL,
  `rbac_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_9qxtkrj9nmr62hbtctm59dnyb` (`rbac_role_id`),
  KEY `FK_mbg62g47x0agpnj6yx80knkow` (`rbac_user_id`),
  CONSTRAINT `FK_9qxtkrj9nmr62hbtctm59dnyb` FOREIGN KEY (`rbac_role_id`) REFERENCES `rbac_role` (`id`),
  CONSTRAINT `FK_mbg62g47x0agpnj6yx80knkow` FOREIGN KEY (`rbac_user_id`) REFERENCES `rbac_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rbac_user_role
-- ----------------------------
INSERT INTO `rbac_user_role` VALUES ('2', '1', '1');

-- ----------------------------
-- Table structure for rbac_user_role_reject
-- ----------------------------
DROP TABLE IF EXISTS `rbac_user_role_reject`;
CREATE TABLE `rbac_user_role_reject` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rbac_role_id` bigint(20) DEFAULT NULL,
  `rbac_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_nkq0sogimaefmruvfxlo4lvsu` (`rbac_role_id`),
  KEY `FK_4mow4hknv27f583d32cyayy5c` (`rbac_user_id`),
  CONSTRAINT `FK_4mow4hknv27f583d32cyayy5c` FOREIGN KEY (`rbac_user_id`) REFERENCES `rbac_user` (`id`),
  CONSTRAINT `FK_nkq0sogimaefmruvfxlo4lvsu` FOREIGN KEY (`rbac_role_id`) REFERENCES `rbac_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rbac_user_role_reject
-- ----------------------------

-- ----------------------------
-- Table structure for sys_department
-- ----------------------------
DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `creater` varchar(30) DEFAULT NULL,
  `description` varchar(60) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `name` varchar(30) NOT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_bsv9wlrnmw7p10eor0h6brdb` (`parent_id`),
  CONSTRAINT `FK_bsv9wlrnmw7p10eor0h6brdb` FOREIGN KEY (`parent_id`) REFERENCES `sys_department` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_department
-- ----------------------------

-- ----------------------------
-- Table structure for sys_department_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_department_role`;
CREATE TABLE `sys_department_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rbac_role_id` bigint(20) DEFAULT NULL,
  `sys_department_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_25dylyw4dqxaoauabfbu7l6r7` (`rbac_role_id`),
  KEY `FK_9hbq0p0a7mf8x7hq5r99i2rhy` (`sys_department_id`),
  CONSTRAINT `FK_25dylyw4dqxaoauabfbu7l6r7` FOREIGN KEY (`rbac_role_id`) REFERENCES `rbac_role` (`id`),
  CONSTRAINT `FK_9hbq0p0a7mf8x7hq5r99i2rhy` FOREIGN KEY (`sys_department_id`) REFERENCES `sys_department` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_department_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_department_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_department_user`;
CREATE TABLE `sys_department_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sys_department_id` bigint(20) DEFAULT NULL,
  `rbac_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_p5erns42mww82w9u9t498rwcy` (`sys_department_id`),
  KEY `FK_6qll1xgf8uyv6buuv81w026o2` (`rbac_user_id`),
  CONSTRAINT `FK_6qll1xgf8uyv6buuv81w026o2` FOREIGN KEY (`rbac_user_id`) REFERENCES `rbac_user` (`id`),
  CONSTRAINT `FK_p5erns42mww82w9u9t498rwcy` FOREIGN KEY (`sys_department_id`) REFERENCES `sys_department` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_department_user
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(32) DEFAULT 'xxxx',
  `enabled` bit(1) NOT NULL,
  `full_time` datetime DEFAULT NULL,
  `loginname` varchar(60) NOT NULL,
  `password` varchar(60) NOT NULL,
  `start_date` date DEFAULT NULL,
  `start_time` time DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_i3rcdsc1j905jadocmdj4ikyq` (`loginname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------

-- ----------------------------
-- Table structure for zht_entity_detail
-- ----------------------------
DROP TABLE IF EXISTS `zht_entity_detail`;
CREATE TABLE `zht_entity_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `M2O_O2O_DisplayField` varchar(60) DEFAULT NULL,
  `column_definition` varchar(30) DEFAULT NULL,
  `columnName` varchar(60) DEFAULT NULL,
  `condtionDisplay` varchar(30) DEFAULT NULL,
  `display_z` varchar(30) DEFAULT NULL,
  `editType` varchar(30) DEFAULT NULL,
  `isList` bit(1) DEFAULT NULL,
  `isLookUpDisplay` bit(1) DEFAULT NULL,
  `isQueryCondtion` bit(1) DEFAULT NULL,
  `isSorter` bit(1) DEFAULT NULL,
  `isTreeProperty` bit(1) DEFAULT NULL,
  `length_z` int(11) DEFAULT NULL,
  `lookUpType` varchar(30) DEFAULT NULL,
  `nullable_z` bit(1) DEFAULT NULL,
  `precision_z` int(11) DEFAULT NULL,
  `propertyName` varchar(100) DEFAULT NULL,
  `propertyType` varchar(100) DEFAULT NULL,
  `queryType` varchar(30) DEFAULT NULL,
  `relationType` varchar(30) DEFAULT NULL,
  `scale_z` int(11) DEFAULT NULL,
  `simpleFormat` varchar(100) DEFAULT NULL,
  `table_name` varchar(30) DEFAULT NULL,
  `unique_z` bit(1) DEFAULT NULL,
  `validateContent` varchar(30) DEFAULT NULL,
  `genEntity_id` bigint(20) DEFAULT NULL,
  `lineWidth` int(11) DEFAULT NULL,
  `columnWidth` int(11) DEFAULT NULL,
  `editContent` varchar(100) DEFAULT NULL,
  `editFinalContentStr` longtext,
  `validateType` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_8jd92sxjd2jmutxlsu5yei1np` (`genEntity_id`),
  CONSTRAINT `FK_8jd92sxjd2jmutxlsu5yei1np` FOREIGN KEY (`genEntity_id`) REFERENCES `zht_gen_entity` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=216 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zht_entity_detail
-- ----------------------------
INSERT INTO `zht_entity_detail` VALUES ('1', null, null, 'dis_index', null, null, null, null, null, null, null, null, '255', null, '\0', '19', 'disIndex', 'integer', null, 'property', '2', null, 'rbac_module', '\0', null, '1', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('2', null, null, 'display', null, null, null, null, null, null, null, null, '32', null, '\0', '19', 'display', 'string', null, 'property', '2', null, 'rbac_module', '', null, '1', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('3', null, null, 'enabled', null, null, null, null, null, null, null, null, '255', null, '\0', '19', 'enabled', 'boolean', null, 'property', '2', null, 'rbac_module', '\0', null, '1', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('4', null, null, 'icon', null, null, null, null, null, null, null, null, '20', null, '', '19', 'icon', 'string', null, 'property', '2', null, 'rbac_module', '\0', null, '1', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('5', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'rbacMenus', 'Set', null, 'OneToMany', null, null, 'rbac_module', null, null, '1', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('6', null, null, 'remark', null, null, null, null, null, null, null, null, '30', null, '', '19', 'remark', 'string', null, 'property', '2', null, 'rbac_module', '\0', null, '1', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('7', null, null, 'date', null, null, null, null, null, null, null, null, '255', null, '', '19', 'date', 'date', null, 'property', '2', null, 'demo_user', '\0', null, '2', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('8', null, null, 'description', null, null, null, null, null, null, null, null, '30', null, '\0', '19', 'description', 'string', null, 'property', '2', null, 'demo_user', '\0', null, '2', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('9', null, null, 'enabled', null, null, null, null, null, null, null, null, '255', null, '\0', '19', 'enabled', 'boolean', null, 'property', '2', null, 'demo_user', '\0', null, '2', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('10', null, null, 'full_time', null, null, null, null, null, null, null, null, '255', null, '', '19', 'fullTime', 'timestamp', null, 'property', '2', null, 'demo_user', '\0', null, '2', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('11', null, null, 'loginname', null, null, null, null, null, null, null, null, '60', null, '\0', '19', 'loginname', 'string', null, 'property', '2', null, 'demo_user', '', null, '2', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('12', null, null, 'password', null, null, null, null, null, null, null, null, '60', null, '\0', '19', 'password', 'string', null, 'property', '2', null, 'demo_user', '\0', null, '2', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('13', null, null, 'time_only', null, null, null, null, null, null, null, null, '255', null, '', '19', 'timeOnly', 'time', null, 'property', '2', null, 'demo_user', '\0', null, '2', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('14', null, null, 'time_stamp', null, null, null, null, null, null, null, null, '255', null, '', '19', 'timestamp', 'timestamp', null, 'property', '2', null, 'demo_user', '\0', null, '2', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('15', null, null, 'date', null, null, null, null, null, null, null, null, '255', null, '', '19', 'date', 'date', null, 'property', '2', null, 'demo_user_2', '\0', null, '3', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('16', null, null, 'description', null, null, null, null, null, null, null, null, '30', null, '\0', '19', 'description', 'string', null, 'property', '2', null, 'demo_user_2', '\0', null, '3', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('17', null, null, 'enabled', null, null, null, null, null, null, null, null, '255', null, '\0', '19', 'enabled', 'boolean', null, 'property', '2', null, 'demo_user_2', '\0', null, '3', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('18', null, null, 'full_time', null, null, null, null, null, null, null, null, '255', null, '', '19', 'fullTime', 'timestamp', null, 'property', '2', null, 'demo_user_2', '\0', null, '3', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('19', null, null, 'loginname', null, null, null, null, null, null, null, null, '60', null, '\0', '19', 'loginname', 'string', null, 'property', '2', null, 'demo_user_2', '', null, '3', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('20', null, null, 'password', null, null, null, null, null, null, null, null, '60', null, '\0', '19', 'password', 'string', null, 'property', '2', null, 'demo_user_2', '\0', null, '3', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('21', null, null, 'time_only', null, null, null, null, null, null, null, null, '255', null, '', '19', 'timeOnly', 'time', null, 'property', '2', null, 'demo_user_2', '\0', null, '3', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('22', null, null, 'time_stamp', null, null, null, null, null, null, null, null, '255', null, '', '19', 'timestamp', 'timestamp', null, 'property', '2', null, 'demo_user_2', '\0', null, '3', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('23', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'childRbacMenus', 'Set', null, 'OneToMany', null, null, 'rbac_menu', null, null, '4', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('24', null, null, 'description', null, null, null, null, null, null, null, null, '60', null, '', '19', 'description', 'string', null, 'property', '2', null, 'rbac_menu', '\0', null, '4', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('25', null, null, 'dis_index', null, null, null, null, null, null, null, null, '255', null, '', '19', 'disIndex', 'integer', null, 'property', '2', null, 'rbac_menu', '\0', null, '4', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('26', null, null, 'display', null, null, null, null, null, null, null, null, '40', null, '\0', '19', 'display', 'string', null, 'property', '2', null, 'rbac_menu', '\0', null, '4', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('27', null, null, 'iconCls', null, null, null, null, null, null, null, null, '40', null, '\0', '19', 'iconCls', 'string', null, 'property', '2', null, 'rbac_menu', '\0', null, '4', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('28', null, null, 'parent_id', null, null, null, null, null, null, null, null, '255', null, '', '19', 'parentRbacMenu', 'RbacMenu', null, 'ManyToOne', '2', null, 'rbac_menu', '\0', null, '4', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('29', null, null, 'module_id', null, null, null, null, null, null, null, null, '255', null, '', '19', 'rbacModule', 'RbacModule', null, 'ManyToOne', '2', null, 'rbac_menu', '\0', null, '4', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('30', null, null, 'permission_id', null, null, null, null, null, null, null, null, '255', null, '', '19', 'rbacPermission', 'RbacPermission', null, 'ManyToOne', '2', null, 'rbac_menu', '\0', null, '4', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('31', null, null, 'tab_title', null, null, null, null, null, null, null, null, '40', null, '', '19', 'tabTitle', 'string', null, 'property', '2', null, 'rbac_menu', '\0', null, '4', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('32', null, null, 'type', null, null, null, null, null, null, null, null, '1', null, '', '19', 'type', 'string', null, 'property', '2', null, 'rbac_menu', '\0', null, '4', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('33', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'childRbacResources', 'Set', null, 'OneToMany', null, null, 'rbac_resource', null, null, '5', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('34', null, null, 'description', null, null, null, null, null, null, null, null, '40', null, '', '19', 'description', 'string', null, 'property', '2', null, 'rbac_resource', '\0', null, '5', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('35', null, null, 'dis_index', null, null, null, null, null, null, null, null, '255', null, '', '19', 'disIndex', 'integer', null, 'property', '2', null, 'rbac_resource', '\0', null, '5', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('36', null, null, 'icon', null, null, null, null, null, null, null, null, '10', null, '', '19', 'icon', 'string', null, 'property', '2', null, 'rbac_resource', '\0', null, '5', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('37', null, null, 'name', null, null, null, null, null, null, null, null, '40', null, '\0', '19', 'name', 'string', null, 'property', '2', null, 'rbac_resource', '\0', null, '5', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('38', null, null, 'parent_id', null, null, null, null, null, null, null, null, '255', null, '', '19', 'parentRbacResource', 'RbacResource', null, 'ManyToOne', '2', null, 'rbac_resource', '\0', null, '5', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('39', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'rbacPermissions', 'Set', null, 'OneToMany', null, null, 'rbac_resource', null, null, '5', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('40', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'SysDepartRoles', 'Set', null, 'OneToMany', null, null, 'sys_department', null, null, '6', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('41', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'childSysDepartments', 'Set', null, 'OneToMany', null, null, 'sys_department', null, null, '6', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('42', '', null, 'create_time', 'text', '创建时间', 'date', '', null, '\0', '', '\0', '255', '', '\0', '19', 'createTime', 'timestamp', '', 'property', '2', '', 'sys_department', '\0', null, '6', null, '100', '', '<input type=\"text\" name=\"createTime\" value=\"${createTime}\"  class=\"easyui-datebox\"  data-options=\" required:true\"/>', 'text');
INSERT INTO `zht_entity_detail` VALUES ('43', '', null, 'creater', 'text', '创建者', 'text', '', null, '', '', null, '30', '', '\0', '19', 'creater', 'string', '', 'property', '2', '', 'sys_department', '\0', null, '6', null, '100', '', '<input type=\"text\" name=\"creater\" value=\"${creater}\"  class=\"easyui-validatebox textbox\"  data-options=\" required:true, validType:\'length[0,30]\'\"/>', 'text');
INSERT INTO `zht_entity_detail` VALUES ('44', '', null, 'description', 'text', '描述', 'textarea', '', null, '\0', '\0', null, '60', '', '\0', '19', 'description', 'string', '', 'property', '2', '', 'sys_department', '\0', null, '6', null, '120', 'rows:3,cols:20', '<textarea name=\"description\" value=\"${description}\"  rows=\"3\" cols=\"20\"  class=\"easyui-validatebox textbox\"  data-options=\" required:true, validType:\'length[0,60]\'\"></textarea>', 'text');
INSERT INTO `zht_entity_detail` VALUES ('45', '', null, 'enabled', 'text', '是否启用', 'select', '', null, '', '', '\0', '255', '', '\0', '19', 'enabled', 'boolean', '=', 'property', '2', '', 'sys_department', '\0', null, '6', null, '60', '(true-是),(false-否)', '<select  name=\"enabled\" class=\"easyui-combobox\"   data-options=\" required:true\"><option value=\"true\" <c:if test=\"${enabled eq true }\">selected=\'selected\' </c:if> >是</option><option value=\"false\" <c:if test=\"${enabled eq false }\">selected=\'selected\' </c:if> >否</option></select>', 'text');
INSERT INTO `zht_entity_detail` VALUES ('46', '', null, 'name', 'text', '部门名称', 'text', '', null, '', '', '\0', '30', '', '\0', '19', 'name', 'string', 'LIKE', 'property', '2', '', 'sys_department', '', null, '6', null, '120', '', '<input type=\"text\" name=\"name\" value=\"${name}\"  class=\"easyui-validatebox textbox\"  data-options=\" required:true, validType:\'length[0,30]\'\"/>', 'text');
INSERT INTO `zht_entity_detail` VALUES ('47', 'parentSysDepartment.name', null, 'parent_id', 'text', '父级部门', 'combotree', '', null, '', '\0', '', '255', '', '', '19', 'parentSysDepartment', 'SysDepartment', '=', 'ManyToOne', '2', '', 'sys_department', '\0', null, '6', null, '130', '{url:http;//www.baidu.com,valueField:id,textField:name}', '<input type=\"text\" name=\"parentSysDepartment.id\" value=\"${parentSysDepartment.id}\"  class=\"easyui-combotree\"  data-options=\" url:\'${pageContext.request.contextPath}/http;//www.baidu.com\',method:\'post\',required:true,valueField:\'id\',textField:\'name\'\"/>', 'text');
INSERT INTO `zht_entity_detail` VALUES ('48', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'sysDepartUsers', 'Set', null, 'OneToMany', null, null, 'sys_department', null, null, '6', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('49', null, null, 'code', null, null, null, null, null, null, null, null, '40', null, '\0', '19', 'code', 'string', null, 'property', '2', null, 'rbac_role', '\0', null, '7', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('50', null, null, 'description', null, null, null, null, null, null, null, null, '60', null, '', '19', 'description', 'string', null, 'property', '2', null, 'rbac_role', '\0', null, '7', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('51', null, null, 'enabled', null, null, null, null, null, null, null, null, '255', null, '\0', '19', 'enabled', 'boolean', null, 'property', '2', null, 'rbac_role', '\0', null, '7', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('52', null, null, 'name', null, null, null, null, null, null, null, null, '40', null, '\0', '19', 'name', 'string', null, 'property', '2', null, 'rbac_role', '\0', null, '7', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('53', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'rbacGroupRoles', 'Set', null, 'OneToMany', null, null, 'rbac_role', null, null, '7', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('54', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'rbacRolePermission', 'Set', null, 'ManyToMany', null, null, 'rbac_role', null, null, '7', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('55', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'sysDepartments', 'Set', null, 'OneToMany', null, null, 'rbac_role', null, null, '7', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('56', null, null, 'code', null, null, null, null, null, null, null, null, '40', null, '\0', '19', 'code', 'string', null, 'property', '2', null, 'rbac_permission', '\0', null, '8', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('57', null, null, 'description', null, null, null, null, null, null, null, null, '60', null, '', '19', 'description', 'string', null, 'property', '2', null, 'rbac_permission', '\0', null, '8', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('58', null, null, 'enabled', null, null, null, null, null, null, null, null, '255', null, '\0', '19', 'enabled', 'boolean', null, 'property', '2', null, 'rbac_permission', '\0', null, '8', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('59', null, null, 'name', null, null, null, null, null, null, null, null, '40', null, '\0', '19', 'name', 'string', null, 'property', '2', null, 'rbac_permission', '\0', null, '8', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('60', null, null, 'resource_id', null, null, null, null, null, null, null, null, '255', null, '', '19', 'rbacResource', 'RbacResource', null, 'ManyToOne', '2', null, 'rbac_permission', '\0', null, '8', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('61', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'rbacRoles', 'Set', null, 'ManyToMany', null, null, 'rbac_permission', null, null, '8', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('62', null, null, 'type', null, null, null, null, null, null, null, null, '1', null, '\0', '19', 'type', 'string', null, 'property', '2', null, 'rbac_permission', '\0', null, '8', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('63', null, null, 'url', null, null, null, null, null, null, null, null, '255', null, '\0', '19', 'url', 'string', null, 'property', '2', null, 'rbac_permission', '\0', null, '8', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('64', null, null, 'rbac_role_id', null, null, null, null, null, null, null, null, '255', null, '', '19', 'rbacRole', 'RbacRole', null, 'ManyToOne', '2', null, 'sys_department_role', '\0', null, '9', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('65', null, null, 'sys_department_id', null, null, null, null, null, null, null, null, '255', null, '', '19', 'sysDepartment', 'SysDepartment', null, 'ManyToOne', '2', null, 'sys_department_role', '\0', null, '9', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('66', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'childRbacGroups', 'Set', null, 'OneToMany', null, null, 'rbac_group', null, null, '10', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('67', null, null, 'create_time', null, null, null, null, null, null, null, null, '255', null, '', '19', 'createTime', 'timestamp', null, 'property', '2', null, 'rbac_group', '\0', null, '10', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('68', null, null, 'creater', null, null, null, null, null, null, null, null, '30', null, '', '19', 'creater', 'string', null, 'property', '2', null, 'rbac_group', '\0', null, '10', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('69', null, null, 'description', null, null, null, null, null, null, null, null, '60', null, '', '19', 'description', 'string', null, 'property', '2', null, 'rbac_group', '\0', null, '10', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('70', null, null, 'enabled', null, null, null, null, null, null, null, null, '255', null, '\0', '19', 'enabled', 'boolean', null, 'property', '2', null, 'rbac_group', '\0', null, '10', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('71', null, null, 'name', null, null, null, null, null, null, null, null, '30', null, '\0', '19', 'name', 'string', null, 'property', '2', null, 'rbac_group', '', null, '10', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('72', null, null, 'parent_id', null, null, null, null, null, null, null, null, '255', null, '', '19', 'parentRbacGroup', 'RbacGroup', null, 'ManyToOne', '2', null, 'rbac_group', '\0', null, '10', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('73', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'rbacGroupRoles', 'Set', null, 'OneToMany', null, null, 'rbac_group', null, null, '10', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('74', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'rbacGroupUsers', 'Set', null, 'OneToMany', null, null, 'rbac_group', null, null, '10', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('75', null, null, 'rbac_group_id', null, null, null, null, null, null, null, null, '255', null, '', '19', 'rbacGroup', 'RbacGroup', null, 'ManyToOne', '2', null, 'rbac_group_role', '\0', null, '11', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('76', null, null, 'rbac_role_id', null, null, null, null, null, null, null, null, '255', null, '', '19', 'rbacRole', 'RbacRole', null, 'ManyToOne', '2', null, 'rbac_group_role', '\0', null, '11', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('77', null, null, 'loginname', null, null, null, null, null, null, null, null, '18', null, '', '19', 'ipAddress', 'string', null, 'property', '2', null, 'rbac_user_session', '\0', null, '12', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('78', null, null, 'end_time', null, null, null, null, null, null, null, null, '255', null, '', '19', 'loginTime', 'timestamp', null, 'property', '2', null, 'rbac_user_session', '\0', null, '12', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('79', null, null, 'rbac_user_id', null, null, null, null, null, null, null, null, '255', null, '', '19', 'rbacUser', 'RbacUser', null, 'ManyToOne', '2', null, 'rbac_user_session', '\0', null, '12', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('80', null, null, 'rbac_role_id', null, null, null, null, null, null, null, null, '255', null, '', '19', 'rbacRole', 'RbacRole', null, 'ManyToOne', '2', null, 'rbac_user_role_reject', '\0', null, '13', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('81', null, null, 'rbac_user_id', null, null, null, null, null, null, null, null, '255', null, '', '19', 'rbacUser', 'RbacUser', null, 'ManyToOne', '2', null, 'rbac_user_role_reject', '\0', null, '13', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('82', null, null, 'description', null, null, null, null, null, null, null, null, '255', null, '', '19', 'description', 'string', null, 'property', '2', null, 'user', '\0', null, '14', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('83', null, null, 'enabled', null, null, null, null, null, null, null, null, '255', null, '\0', '19', 'enabled', 'boolean', null, 'property', '2', null, 'user', '\0', null, '14', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('84', null, null, 'full_time', null, null, null, null, null, null, null, null, '255', null, '', '19', 'fullTime', 'timestamp', null, 'property', '2', null, 'user', '\0', null, '14', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('85', null, null, 'loginname', null, null, null, null, null, null, null, null, '60', null, '\0', '19', 'loginname', 'string', null, 'property', '2', null, 'user', '', null, '14', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('86', null, null, 'password', null, null, null, null, null, null, null, null, '60', null, '\0', '19', 'password', 'string', null, 'property', '2', null, 'user', '\0', null, '14', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('87', null, null, 'start_date', null, null, null, null, null, null, null, null, '255', null, '', '19', 'startDate', 'date', null, 'property', '2', null, 'user', '\0', null, '14', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('88', null, null, 'start_time', null, null, null, null, null, null, null, null, '255', null, '', '19', 'startTime', 'time', null, 'property', '2', null, 'user', '\0', null, '14', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('89', null, null, 'M2O_O2O_DisplayField', null, null, null, null, null, null, null, null, '60', null, '', '19', 'M2OO2ODisplayField', 'string', null, 'property', '2', null, 'zht_entity_detail', '\0', null, '15', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('90', null, null, 'column_definition', null, null, null, null, null, null, null, null, '30', null, '', '19', 'columnDefinition', 'string', null, 'property', '2', null, 'zht_entity_detail', '\0', null, '15', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('91', null, null, 'columnName', null, null, null, null, null, null, null, null, '30', null, '', '19', 'columnName', 'string', null, 'property', '2', null, 'zht_entity_detail', '\0', null, '15', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('92', null, null, 'condtionDisplay', null, null, null, null, null, null, null, null, '30', null, '', '19', 'condtionDisplay', 'string', null, 'property', '2', null, 'zht_entity_detail', '\0', null, '15', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('93', null, null, 'display_z', null, null, null, null, null, null, null, null, '30', null, '', '19', 'display', 'string', null, 'property', '2', null, 'zht_entity_detail', '\0', null, '15', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('94', null, null, 'editType', null, null, null, null, null, null, null, null, '30', null, '', '19', 'editType', 'string', null, 'property', '2', null, 'zht_entity_detail', '\0', null, '15', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('95', null, null, 'genEntity_id', null, null, null, null, null, null, null, null, '255', null, '', '19', 'genEntity', 'GenEntity', null, 'ManyToOne', '2', null, 'zht_entity_detail', '\0', null, '15', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('96', null, null, 'isList', null, null, null, null, null, null, null, null, '255', null, '', '19', 'isList', 'boolean', null, 'property', '2', null, 'zht_entity_detail', '\0', null, '15', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('97', null, null, 'isLookUpDisplay', null, null, null, null, null, null, null, null, '255', null, '', '19', 'isLookUpDisplay', 'boolean', null, 'property', '2', null, 'zht_entity_detail', '\0', null, '15', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('98', null, null, 'isQueryCondtion', null, null, null, null, null, null, null, null, '255', null, '', '19', 'isQueryCondtion', 'boolean', null, 'property', '2', null, 'zht_entity_detail', '\0', null, '15', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('99', null, null, 'isSorter', null, null, null, null, null, null, null, null, '255', null, '', '19', 'isSorter', 'boolean', null, 'property', '2', null, 'zht_entity_detail', '\0', null, '15', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('100', null, null, 'isTreeProperty', null, null, null, null, null, null, null, null, '255', null, '', '19', 'isTreeProperty', 'boolean', null, 'property', '2', null, 'zht_entity_detail', '\0', null, '15', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('101', null, null, 'length_z', null, null, null, null, null, null, null, null, '255', null, '', '19', 'length', 'integer', null, 'property', '2', null, 'zht_entity_detail', '\0', null, '15', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('102', null, null, 'lookUpType', null, null, null, null, null, null, null, null, '30', null, '', '19', 'lookUpType', 'string', null, 'property', '2', null, 'zht_entity_detail', '\0', null, '15', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('103', null, null, 'nullable_z', null, null, null, null, null, null, null, null, '255', null, '', '19', 'nullable', 'boolean', null, 'property', '2', null, 'zht_entity_detail', '\0', null, '15', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('104', null, null, 'precision_z', null, null, null, null, null, null, null, null, '255', null, '', '19', 'precision', 'integer', null, 'property', '2', null, 'zht_entity_detail', '\0', null, '15', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('105', null, null, 'propertyName', null, null, null, null, null, null, null, null, '100', null, '', '19', 'propertyName', 'string', null, 'property', '2', null, 'zht_entity_detail', '\0', null, '15', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('106', null, null, 'propertyType', null, null, null, null, null, null, null, null, '100', null, '', '19', 'propertyType', 'string', null, 'property', '2', null, 'zht_entity_detail', '\0', null, '15', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('107', null, null, 'queryType', null, null, null, null, null, null, null, null, '30', null, '', '19', 'queryType', 'string', null, 'property', '2', null, 'zht_entity_detail', '\0', null, '15', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('108', null, null, 'relationType', null, null, null, null, null, null, null, null, '30', null, '', '19', 'relationType', 'string', null, 'property', '2', null, 'zht_entity_detail', '\0', null, '15', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('109', null, null, 'scale_z', null, null, null, null, null, null, null, null, '255', null, '', '19', 'scale', 'integer', null, 'property', '2', null, 'zht_entity_detail', '\0', null, '15', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('110', null, null, 'simpleFormat', null, null, null, null, null, null, null, null, '100', null, '', '19', 'simpleFormat', 'string', null, 'property', '2', null, 'zht_entity_detail', '\0', null, '15', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('111', null, null, 'table_name', null, null, null, null, null, null, null, null, '30', null, '', '19', 'tableName', 'string', null, 'property', '2', null, 'zht_entity_detail', '\0', null, '15', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('112', null, null, 'unique_z', null, null, null, null, null, null, null, null, '255', null, '', '19', 'unique', 'boolean', null, 'property', '2', null, 'zht_entity_detail', '\0', null, '15', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('113', null, null, 'validateContent', null, null, null, null, null, null, null, null, '30', null, '', '19', 'validateContent', 'string', null, 'property', '2', null, 'zht_entity_detail', '\0', null, '15', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('114', null, null, 'accountNonExpired', null, null, null, null, null, null, null, null, '255', null, '\0', '19', 'accountNonExpired', 'boolean', null, 'property', '2', null, 'rbac_user', '\0', null, '16', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('115', null, null, 'accountNonLocked', null, null, null, null, null, null, null, null, '255', null, '\0', '19', 'accountNonLocked', 'boolean', null, 'property', '2', null, 'rbac_user', '\0', null, '16', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('116', null, null, 'alias', null, null, null, null, null, null, null, null, '30', null, '', '19', 'alias', 'string', null, 'property', '2', null, 'rbac_user', '\0', null, '16', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('117', null, null, 'credentialsNonExpired', null, null, null, null, null, null, null, null, '255', null, '\0', '19', 'credentialsNonExpired', 'boolean', null, 'property', '2', null, 'rbac_user', '\0', null, '16', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('118', null, null, 'defaultRoleId', null, null, null, null, null, null, null, null, '255', null, '', '19', 'defaultRbacRole', 'RbacRole', null, 'ManyToOne', '2', null, 'rbac_user', '\0', null, '16', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('119', null, null, 'description', null, null, null, null, null, null, null, null, '30', null, '', '19', 'description', 'string', null, 'property', '2', null, 'rbac_user', '\0', null, '16', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('120', null, null, 'enabled', null, null, null, null, null, null, null, null, '255', null, '\0', '19', 'enabled', 'boolean', null, 'property', '2', null, 'rbac_user', '\0', null, '16', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('121', null, null, 'loginname', null, null, null, null, null, null, null, null, '30', null, '\0', '19', 'loginname', 'string', null, 'property', '2', null, 'rbac_user', '', null, '16', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('122', null, null, 'password', null, null, null, null, null, null, null, null, '36', null, '', '19', 'password', 'string', null, 'property', '2', null, 'rbac_user', '\0', null, '16', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('123', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'rbacGroupUsers', 'Set', null, 'OneToMany', null, null, 'rbac_user', null, null, '16', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('124', null, null, 'salt', null, null, null, null, null, null, null, null, '36', null, '', '19', 'salt', 'string', null, 'property', '2', null, 'rbac_user', '\0', null, '16', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('125', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'sysDepartmentUsers', 'Set', null, 'OneToMany', null, null, 'rbac_user', null, null, '16', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('126', null, null, 'rbac_group_id', null, null, null, null, null, null, null, null, '255', null, '', '19', 'rbacGroup', 'RbacGroup', null, 'ManyToOne', '2', null, 'rbac_group_user', '\0', null, '17', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('127', null, null, 'rbac_user_id', null, null, null, null, null, null, null, null, '255', null, '', '19', 'rbacUser', 'RbacUser', null, 'ManyToOne', '2', null, 'rbac_group_user', '\0', null, '17', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('128', null, null, 'cardTypeCode', null, null, null, null, null, null, null, null, '3', null, '', '19', 'cardTypeCode', 'string', null, 'property', '2', null, 'info_personinfo', '\0', null, '18', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('129', null, null, 'EMAIL', null, null, null, null, null, null, null, null, '50', null, '', '19', 'email', 'string', null, 'property', '2', null, 'info_personinfo', '\0', null, '18', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('130', null, null, 'famPostcalCode', null, null, null, null, null, null, null, null, '6', null, '', '19', 'famPostcalCode', 'string', null, 'property', '2', null, 'info_personinfo', '\0', null, '18', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('131', null, null, 'famTelephone', null, null, null, null, null, null, null, null, '15', null, '', '19', 'famTelephone', 'string', null, 'property', '2', null, 'info_personinfo', '\0', null, '18', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('132', null, null, 'famillyAddress', null, null, null, null, null, null, null, null, '50', null, '', '19', 'famillyAddress', 'string', null, 'property', '2', null, 'info_personinfo', '\0', null, '18', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('133', null, null, 'genderCode', null, null, null, null, null, null, null, null, '5', null, '\0', '19', 'genderCode', 'string', null, 'property', '2', null, 'info_personinfo', '\0', null, '18', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('134', null, null, 'isMarried', null, null, null, null, null, null, null, null, '255', null, '', '19', 'isMarried', 'integer', null, 'property', '2', null, 'info_personinfo', '\0', null, '18', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('135', null, null, 'mobilePhone', null, null, null, null, null, null, null, null, '15', null, '', '19', 'mobilePhone', 'string', null, 'property', '2', null, 'info_personinfo', '\0', null, '18', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('136', null, null, 'MSN', null, null, null, null, null, null, null, null, '50', null, '', '19', 'msn', 'string', null, 'property', '2', null, 'info_personinfo', '\0', null, '18', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('137', null, null, 'nameSpell', null, null, null, null, null, null, null, null, '60', null, '', '19', 'nameSpell', 'string', null, 'property', '2', null, 'info_personinfo', '\0', null, '18', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('138', null, null, 'nationId', null, null, null, null, null, null, null, null, '255', null, '', '19', 'nationId', 'long', null, 'property', '2', null, 'info_personinfo', '\0', null, '18', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('139', null, null, 'nativeCity', null, null, null, null, null, null, null, null, '30', null, '', '19', 'nativeCity', 'string', null, 'property', '2', null, 'info_personinfo', '\0', null, '18', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('140', null, null, 'nativeCounty', null, null, null, null, null, null, null, null, '30', null, '', '19', 'nativeCounty', 'string', null, 'property', '2', null, 'info_personinfo', '\0', null, '18', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('141', null, null, 'nativeProvince', null, null, null, null, null, null, null, null, '20', null, '', '19', 'nativeProvince', 'string', null, 'property', '2', null, 'info_personinfo', '\0', null, '18', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('142', null, null, 'peopleId', null, null, null, null, null, null, null, null, '255', null, '', '19', 'peopleId', 'long', null, 'property', '2', null, 'info_personinfo', '\0', null, '18', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('143', null, null, 'perAddress', null, null, null, null, null, null, null, null, '50', null, '', '19', 'perAddress', 'string', null, 'property', '2', null, 'info_personinfo', '\0', null, '18', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('144', null, null, 'perBirthday', null, null, null, null, null, null, null, null, '10', null, '', '19', 'perBirthday', 'date', null, 'property', '2', null, 'info_personinfo', '\0', null, '18', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('145', null, null, 'perEnglishName', null, null, null, null, null, null, null, null, '60', null, '', '19', 'perEnglishName', 'string', null, 'property', '2', null, 'info_personinfo', '\0', null, '18', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('146', null, null, 'perIdCard', null, null, null, null, null, null, null, null, '18', null, '', '19', 'perIdCard', 'string', null, 'property', '2', null, 'info_personinfo', '\0', null, '18', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('147', null, null, 'perName', null, null, null, null, null, null, null, null, '50', null, '\0', '19', 'perName', 'string', null, 'property', '2', null, 'info_personinfo', '\0', null, '18', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('148', null, null, 'perNum', null, null, null, null, null, null, null, null, '20', null, '\0', '19', 'perNum', 'string', null, 'property', '2', null, 'info_personinfo', '', null, '18', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('149', null, null, 'perPostalCode', null, null, null, null, null, null, null, null, '6', null, '', '19', 'perPostalCode', 'string', null, 'property', '2', null, 'info_personinfo', '\0', null, '18', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('150', null, null, 'perTelephone', null, null, null, null, null, null, null, null, '20', null, '', '19', 'perTelephone', 'string', null, 'property', '2', null, 'info_personinfo', '\0', null, '18', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('151', null, null, 'rbac_role_id', null, null, null, null, null, null, null, null, '255', null, '', '19', 'rbacRole', 'RbacRole', null, 'ManyToOne', '2', null, 'rbac_user_role', '\0', null, '19', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('152', null, null, 'rbac_user_id', null, null, null, null, null, null, null, null, '255', null, '', '19', 'rbacUser', 'RbacUser', null, 'ManyToOne', '2', null, 'rbac_user_role', '\0', null, '19', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('153', null, null, 'rejected', null, null, null, null, null, null, null, null, '255', null, '\0', '19', 'rejected', 'boolean', null, 'property', '2', null, 'rbac_user_role', '\0', null, '19', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('154', null, null, 'rbac_user_id', null, null, null, null, null, null, null, null, '255', null, '', '19', 'rbacUser', 'RbacUser', null, 'ManyToOne', '2', null, 'sys_department_user', '\0', null, '20', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('155', null, null, 'sys_department_id', null, null, null, null, null, null, null, null, '255', null, '', '19', 'sysDepartment', 'SysDepartment', null, 'ManyToOne', '2', null, 'sys_department_user', '\0', null, '20', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('156', null, null, 'email', null, null, null, null, null, null, null, null, '50', null, '', '19', 'email', 'string', null, 'property', '2', null, 'gen_demo', '\0', null, '21', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('157', null, null, 'enable', null, null, null, null, null, null, null, null, '255', null, '', '19', 'enable', 'boolean', null, 'property', '2', null, 'gen_demo', '\0', null, '21', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('158', null, null, 'mobilePhone', null, null, null, null, null, null, null, null, '15', null, '', '19', 'mobilePhone', 'string', null, 'property', '2', null, 'gen_demo', '\0', null, '21', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('159', null, null, 'perName', null, null, null, null, null, null, null, null, '50', null, '\0', '19', 'perName', 'string', null, 'property', '2', null, 'gen_demo', '\0', null, '21', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('160', null, null, 'perNum', null, null, null, null, null, null, null, null, '20', null, '\0', '19', 'perNum', 'string', null, 'property', '2', null, 'gen_demo', '', null, '21', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('161', null, null, 'create_time', null, null, null, null, null, null, null, null, '255', null, '', '19', 'careateTime', 'timestamp', null, 'property', '2', null, 'zht_gen_entity', '\0', null, '22', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('162', null, null, 'controllerNameSpace', null, null, null, null, null, null, null, null, '60', null, '', '19', 'controllerNameSpace', 'string', null, 'property', '2', null, 'zht_gen_entity', '\0', null, '22', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('163', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'genEntityDetails', 'Set', null, 'OneToMany', null, null, 'zht_gen_entity', null, null, '22', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('164', null, null, 'gen_info', null, null, null, null, null, null, null, null, '60', null, '', '19', 'genInfo', 'string', null, 'property', '2', null, 'zht_gen_entity', '\0', null, '22', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('165', null, null, 'isTree', null, null, null, null, null, null, null, null, '255', null, '', '19', 'isTree', 'boolean', null, 'property', '2', null, 'zht_gen_entity', '\0', null, '22', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('166', null, null, 'last_modify_time', null, null, null, null, null, null, null, null, '255', null, '', '19', 'lastModifyTime', 'timestamp', null, 'property', '2', null, 'zht_gen_entity', '\0', null, '22', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('167', null, null, 'module_name', null, null, null, null, null, null, null, null, '60', null, '', '19', 'moduleName', 'string', null, 'property', '2', null, 'zht_gen_entity', '\0', null, '22', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('168', null, null, 'name', null, null, null, null, null, null, null, null, '100', null, '\0', '19', 'name', 'string', null, 'property', '2', null, 'zht_gen_entity', '', null, '22', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('169', null, null, 'remark', null, null, null, null, null, null, null, null, '60', null, '', '19', 'remark', 'string', null, 'property', '2', null, 'zht_gen_entity', '\0', null, '22', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('170', null, null, 'version', null, null, null, null, null, null, null, null, '10', null, '', '19', 'version', 'string', null, 'property', '2', null, 'zht_gen_entity', '\0', null, '22', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('171', null, null, 'M2O_O2O_DisplayField', null, null, null, null, null, null, null, null, '60', null, '', '19', 'mTOOTOField', 'string', null, null, '2', null, 'zht_entity_detail', '\0', null, '15', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('172', null, null, 'entityDisName', null, null, null, null, null, null, null, null, '60', null, '', '19', 'entityDisName', 'string', null, 'property', '2', null, 'zht_gen_entity', '\0', null, '22', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('173', null, null, 'lineWidth', null, null, null, null, null, null, null, null, '255', null, '', '19', 'lineWidth', 'integer', null, null, '2', null, 'zht_entity_detail', '\0', null, '15', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('174', null, null, 'columnWidth', null, null, null, null, null, null, null, null, '255', null, '', '19', 'columnWidth', 'integer', null, null, '2', null, 'zht_entity_detail', '\0', null, '15', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('175', null, null, 'editContent', null, null, null, null, null, null, null, null, '100', null, '', '19', 'editContent', 'string', null, 'property', '2', null, 'zht_entity_detail', '\0', null, '15', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('176', null, null, 'editFinalContentStr', null, null, null, null, null, null, null, null, '300', null, '', '19', 'editFinalContentStr', 'string', null, 'property', '2', null, 'zht_entity_detail', '\0', null, '15', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('177', null, null, 'validateType', null, null, null, null, null, null, null, null, '30', null, '', '19', 'validateType', 'string', null, 'property', '2', null, 'zht_entity_detail', '\0', null, '15', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('178', null, null, 'tableName', null, null, null, null, null, null, null, null, '60', null, '', '19', 'tableName', 'string', null, 'property', '2', null, 'zht_gen_entity', '\0', null, '22', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('179', null, null, 'columnName', null, null, null, null, null, null, null, null, '30', null, '', '19', 'columnName', 'string', null, 'property', '2', null, 'zht_entity_property', '\0', null, '25', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('180', null, null, 'columnWidth', null, null, null, null, null, null, null, null, '255', null, '', '19', 'columnWidth', 'integer', null, 'property', '2', null, 'zht_entity_property', '\0', null, '25', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('181', null, null, 'condtionDisplay', null, null, null, null, null, null, null, null, '30', null, '', '19', 'condtionDisplay', 'string', null, 'property', '2', null, 'zht_entity_property', '\0', null, '25', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('182', null, null, 'display_z', null, null, null, null, null, null, null, null, '30', null, '', '19', 'display', 'string', null, 'property', '2', null, 'zht_entity_property', '\0', null, '25', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('183', null, null, 'editContent', null, null, null, null, null, null, null, null, '100', null, '', '19', 'editContent', 'string', null, 'property', '2', null, 'zht_entity_property', '\0', null, '25', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('184', null, null, 'editFinalContentStr', null, null, null, null, null, null, null, null, '300', null, '', '19', 'editFinalContentStr', 'string', null, 'property', '2', null, 'zht_entity_property', '\0', null, '25', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('185', null, null, 'editType', null, null, null, null, null, null, null, null, '30', null, '', '19', 'editType', 'string', null, 'property', '2', null, 'zht_entity_property', '\0', null, '25', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('186', null, null, 'genEntity_id', null, null, null, null, null, null, null, null, '255', null, '', '19', 'genEntity', 'GenEntity', null, 'ManyToOne', '2', null, 'zht_entity_property', '\0', null, '25', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('187', null, null, 'isList', null, null, null, null, null, null, null, null, '255', null, '', '19', 'isList', 'boolean', null, 'property', '2', null, 'zht_entity_property', '\0', null, '25', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('188', null, null, 'isLookUpDisplay', null, null, null, null, null, null, null, null, '255', null, '', '19', 'isLookUpDisplay', 'boolean', null, 'property', '2', null, 'zht_entity_property', '\0', null, '25', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('189', null, null, 'isQueryCondtion', null, null, null, null, null, null, null, null, '255', null, '', '19', 'isQueryCondtion', 'boolean', null, 'property', '2', null, 'zht_entity_property', '\0', null, '25', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('190', null, null, 'isSorter', null, null, null, null, null, null, null, null, '255', null, '', '19', 'isSorter', 'boolean', null, 'property', '2', null, 'zht_entity_property', '\0', null, '25', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('191', null, null, 'isTreeProperty', null, null, null, null, null, null, null, null, '255', null, '', '19', 'isTreeProperty', 'boolean', null, 'property', '2', null, 'zht_entity_property', '\0', null, '25', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('192', null, null, 'length_z', null, null, null, null, null, null, null, null, '255', null, '', '19', 'length', 'integer', null, 'property', '2', null, 'zht_entity_property', '\0', null, '25', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('193', null, null, 'lookUpType', null, null, null, null, null, null, null, null, '30', null, '', '19', 'lookUpType', 'string', null, 'property', '2', null, 'zht_entity_property', '\0', null, '25', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('194', null, null, 'M2O_O2O_DisplayField', null, null, null, null, null, null, null, null, '60', null, '', '19', 'mTOOTOField', 'string', null, null, '2', null, 'zht_entity_property', '\0', null, '25', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('195', null, null, 'nullable_z', null, null, null, null, null, null, null, null, '255', null, '', '19', 'nullable', 'boolean', null, 'property', '2', null, 'zht_entity_property', '\0', null, '25', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('196', null, null, 'precision_z', null, null, null, null, null, null, null, null, '255', null, '', '19', 'precision', 'integer', null, 'property', '2', null, 'zht_entity_property', '\0', null, '25', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('197', null, null, 'propertyName', null, null, null, null, null, null, null, null, '40', null, '', '19', 'propertyName', 'string', null, 'property', '2', null, 'zht_entity_property', '\0', null, '25', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('198', null, null, 'propertyType', null, null, null, null, null, null, null, null, '40', null, '', '19', 'propertyType', 'string', null, 'property', '2', null, 'zht_entity_property', '\0', null, '25', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('199', null, null, 'queryType', null, null, null, null, null, null, null, null, '30', null, '', '19', 'queryType', 'string', null, 'property', '2', null, 'zht_entity_property', '\0', null, '25', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('200', null, null, 'relationType', null, null, null, null, null, null, null, null, '30', null, '', '19', 'relationType', 'string', null, 'property', '2', null, 'zht_entity_property', '\0', null, '25', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('201', null, null, 'scale_z', null, null, null, null, null, null, null, null, '255', null, '', '19', 'scale', 'integer', null, 'property', '2', null, 'zht_entity_property', '\0', null, '25', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('202', null, null, 'simpleFormat', null, null, null, null, null, null, null, null, '100', null, '', '19', 'simpleFormat', 'string', null, 'property', '2', null, 'zht_entity_property', '\0', null, '25', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('203', null, null, 'unique_z', null, null, null, null, null, null, null, null, '255', null, '', '19', 'unique', 'boolean', null, 'property', '2', null, 'zht_entity_property', '\0', null, '25', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('204', null, null, 'validateContent', null, null, null, null, null, null, null, null, '30', null, '', '19', 'validateContent', 'string', null, 'property', '2', null, 'zht_entity_property', '\0', null, '25', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('205', null, null, 'validateType', null, null, null, null, null, null, null, null, '30', null, '', '19', 'validateType', 'string', null, 'property', '2', null, 'zht_entity_property', '\0', null, '25', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('208', null, null, 'generatedEditOfPropertyStr', null, null, null, null, null, null, null, null, '200', null, '', '19', 'generatedEditOfPropertyStr', 'string', null, 'property', '2', null, 'zht_entity_property', '\0', null, '25', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('209', null, null, 'generatedHibernateModelOfPropertyStr', null, null, null, null, null, null, null, null, '200', null, '', '19', 'generatedHibernateModelOfPropertyStr', 'string', null, 'property', '2', null, 'zht_entity_property', '\0', null, '25', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('210', null, null, 'generatedListDisplayOfPropertyStr', null, null, null, null, null, null, null, null, '200', null, '', '19', 'generatedListDisplayOfPropertyStr', 'string', null, 'property', '2', null, 'zht_entity_property', '\0', null, '25', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('211', null, null, 'generatedQueryOfPropertyStr', null, null, null, null, null, null, null, null, '200', null, '', '19', 'generatedQueryOfPropertyStr', 'string', null, 'property', '2', null, 'zht_entity_property', '\0', null, '25', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('212', null, null, 'maxLength', null, null, null, null, null, null, null, null, '255', null, '', '19', 'maxLength', 'integer', null, 'property', '2', null, 'zht_entity_property', '\0', null, '25', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('213', null, null, 'minLength', null, null, null, null, null, null, null, null, '255', null, '', '19', 'minLength', 'integer', null, 'property', '2', null, 'zht_entity_property', '\0', null, '25', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('214', null, null, 'max_value', null, null, null, null, null, null, null, null, '255', null, '', '19', 'maxValue', 'long', null, 'property', '2', null, 'zht_entity_property', '\0', null, '25', null, null, null, null, null);
INSERT INTO `zht_entity_detail` VALUES ('215', null, null, 'min_value', null, null, null, null, null, null, null, null, '255', null, '', '19', 'minValue', 'long', null, 'property', '2', null, 'zht_entity_property', '\0', null, '25', null, null, null, null, null);

-- ----------------------------
-- Table structure for zht_entity_property
-- ----------------------------
DROP TABLE IF EXISTS `zht_entity_property`;
CREATE TABLE `zht_entity_property` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `columnName` varchar(60) DEFAULT NULL,
  `columnWidth` int(11) DEFAULT NULL,
  `condtionDisplay` varchar(30) DEFAULT NULL,
  `display_z` varchar(30) DEFAULT NULL,
  `editContent` varchar(100) DEFAULT NULL,
  `editFinalContentStr` longtext,
  `editType` varchar(30) DEFAULT NULL,
  `isList` bit(1) DEFAULT NULL,
  `isLookUpDisplay` bit(1) DEFAULT NULL,
  `isQueryCondtion` bit(1) DEFAULT NULL,
  `isSorter` bit(1) DEFAULT NULL,
  `isTreeProperty` bit(1) DEFAULT NULL,
  `length_z` int(11) DEFAULT NULL,
  `lookUpType` varchar(30) DEFAULT NULL,
  `M2O_O2O_DisplayField` varchar(60) DEFAULT NULL,
  `nullable_z` bit(1) DEFAULT NULL,
  `precision_z` int(11) DEFAULT NULL,
  `propertyName` varchar(40) DEFAULT NULL,
  `propertyType` varchar(200) DEFAULT NULL,
  `queryType` varchar(30) DEFAULT NULL,
  `relationType` varchar(30) DEFAULT NULL,
  `scale_z` int(11) DEFAULT NULL,
  `simpleFormat` varchar(1000) DEFAULT NULL,
  `unique_z` bit(1) DEFAULT NULL,
  `validateContent` varchar(30) DEFAULT NULL,
  `validateType` varchar(30) DEFAULT NULL,
  `genEntity_id` bigint(20) DEFAULT NULL,
  `generatedEditOfPropertyStr` varchar(1000) DEFAULT NULL,
  `generatedHibernateModelOfPropertyStr` varchar(1000) DEFAULT NULL,
  `generatedListDisplayOfPropertyStr` varchar(1000) DEFAULT NULL,
  `generatedQueryOfPropertyStr` varchar(1000) DEFAULT NULL,
  `maxLength` int(11) DEFAULT NULL,
  `minLength` int(11) DEFAULT NULL,
  `max_length` int(11) DEFAULT NULL,
  `max_value` bigint(20) DEFAULT NULL,
  `min_length` int(11) DEFAULT NULL,
  `min_value` bigint(20) DEFAULT NULL,
  `isDateFutrue` bit(1) DEFAULT NULL,
  `isDatePost` bit(1) DEFAULT NULL,
  `manyToOneDisplayField` varchar(255) DEFAULT NULL,
  `oneTomanyMappedBy` varchar(255) DEFAULT NULL,
  `isManytomanyMasterControl` bit(1) DEFAULT NULL,
  `manytomanyJoinColumnPpposite` varchar(255) DEFAULT NULL,
  `manytomanyJoinColumnSelf` varchar(255) DEFAULT NULL,
  `manytomanyJoinTable` varchar(255) DEFAULT NULL,
  `manytomanyJoinColumnOpposite` varchar(255) DEFAULT NULL,
  `manytomanyMappedBy` varchar(255) DEFAULT NULL,
  `isOneToOneMasterControl` bit(1) DEFAULT NULL,
  `oneToOneDisplayField` varchar(255) DEFAULT NULL,
  `oneToOneMappedBy` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_bce2jtqan06c72s1bh1gco97b` (`genEntity_id`),
  CONSTRAINT `FK_bce2jtqan06c72s1bh1gco97b` FOREIGN KEY (`genEntity_id`) REFERENCES `zht_gen_entity` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=519 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zht_entity_property
-- ----------------------------
INSERT INTO `zht_entity_property` VALUES ('9', 'zString', '200', null, 'zString', '', null, 'text', '', null, '\0', '\0', null, '200', null, null, '', null, 'zString', 'java.lang.String', '', 'property', null, '', '\0', null, '', '26', '<input type=\"text\" name=\"zString\" value=\"${zString}\" class=\"easyui-validatebox textbox\"  data-options=\"  validType:\'length[0,200]\'\"/>', '@javax.persistence.Column(name = \"zString\",unique = false,nullable = true,length = 200)', null, null, null, null, '200', null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('10', 'zLong', '60', null, 'zLong', '', null, 'text', '', null, '', '', null, null, null, null, '', null, 'zLong', 'java.lang.Long', '=', 'property', null, '', '\0', null, null, '26', '<input type=\"text\" name=\"zLong\" value=\"${zLong}\" class=\"easyui-numberbox textbox\"  data-options=\" min:0.0,max:9999999.0 \"/>', '@javax.validation.constraints.Min(0) \r	@javax.validation.constraints.Max(9999999) \r	@javax.persistence.Column(name = \"zLong\",unique = false,nullable = true)', '{field:\'zLong\',title:\'zLong\',width:60,},', '<input type=\"text\" name=\"zLong\" value=\"${zLong}\" class=\"easyui-numberbox textbox\"  />', null, null, null, '9999999', null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('11', 'zInteger', '60', null, 'zInteger', '', null, 'text', '', null, '', '', null, null, null, null, '\0', null, 'zInteger', 'java.lang.Integer', '>', 'property', null, '', '', null, null, '26', '<input type=\"text\" name=\"zInteger\" value=\"${zInteger}\" class=\"easyui-numberbox textbox\"  data-options=\" required:true,min:0.0,max:999999.0 \"/>', '@javax.validation.constraints.Min(0) \r	@javax.validation.constraints.Max(999999) \r	@javax.validation.constraints.NotNull \r	@javax.persistence.Column(name = \"zInteger\",unique = true,nullable = false)', '{field:\'zInteger\',title:\'zInteger\',width:60,},', '<input type=\"text\" name=\"zInteger\" value=\"${zInteger}\" class=\"easyui-numberbox textbox\"  />', null, null, null, '999999', null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('12', 'zShort', '50', null, 'zShort', '', null, 'text', '', null, '', '', null, null, null, null, '', null, 'zShort', 'java.lang.Short', '=', 'property', null, '', '\0', null, null, '26', '<input type=\"text\" name=\"zShort\" value=\"${zShort}\" class=\"easyui-numberbox textbox\"  data-options=\" min:0.0,max:999999.0 \"/>', '@javax.validation.constraints.Min(0) \r	@javax.validation.constraints.Max(999999) \r	@javax.persistence.Column(name = \"zShort\",unique = false,nullable = true)', '{field:\'zShort\',title:\'zShort\',width:50,},', '<input type=\"text\" name=\"zShort\" value=\"${zShort}\" class=\"easyui-numberbox textbox\"  />', null, null, null, '999999', null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('13', 'zByte', '100', null, 'zByte', '', null, 'text', '', null, '', '\0', null, null, null, null, '', null, 'zByte', 'java.lang.Byte', '=', 'property', null, '', '\0', null, null, '26', '<input type=\"text\" name=\"zByte\" value=\"${zByte}\" class=\"easyui-numberbox textbox\"  data-options=\" min:0.0,max:127.0 \"/>', '@javax.validation.constraints.Min(0) \r	@javax.validation.constraints.Max(127) \r	@javax.persistence.Column(name = \"zByte\",unique = false,nullable = true)', '{field:\'zByte\',title:\'zByte\',width:100,},', '<input type=\"text\" name=\"zByte\" value=\"${zByte}\" class=\"easyui-numberbox textbox\"  />', null, null, null, '127', null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('14', 'tBoolean', '200', null, 'tBoolean', '{true-是}{false-否}', null, 'select', '', null, '', '', null, null, null, null, '', null, 'tBoolean', 'java.lang.Boolean', '=', 'property', null, 'formatter: function(value,row){  if(true==value){   return 启用;   }else if(false==value){   return 禁用;   } }', '\0', null, null, '26', '<select  name=\"tBoolean\" class=\"easyui-combobox\"  >\r\n<option value=\"true\" <c:if test=\"${demoEntity.tBoolean eq true }\">selected=\'selected\' </c:if> >是</option>\r\n<option value=\"false\" <c:if test=\"${demoEntity.tBoolean eq false }\">selected=\'selected\' </c:if> >否</option>\r\n</select>', '@javax.persistence.Column(name = \"tBoolean\",nullable = true )', '{field:\'tBoolean\',title:\'tBoolean\',width:200,formatter: function(value,row){  if(true==value){   return 启用;   }else if(false==value){   return 禁用;   } }},', '<select  name=\"tBoolean\" class=\"easyui-combobox\"  >\r\n<option value=\"true\">是</option>\r\n<option value=\"false\">否</option>\r\n</select>', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('15', 'tDouble', '200', null, 'tDouble', '', null, 'text', '', null, '', '', null, null, null, null, '', '20', 'tDouble', 'java.lang.Double', '=', 'property', '9', '', '\0', null, null, '26', '<input type=\"text\" name=\"tDouble\" value=\"${tDouble}\" class=\"easyui-numberbox textbox\"  data-options=\" min:0.0,max:99999.0,scale:9 \"/>', '@javax.validation.constraints.DecimalMin(\"0.0\") \r	@javax.validation.constraints.DecimalMax(\"99999.0\") \r	@javax.persistence.Column(name = \"tDouble\",precision = 20,scale = 9,unique = false,nullable = true)', '{field:\'tDouble\',title:\'tDouble\',width:200,},', '<input type=\"text\" name=\"tDouble\" value=\"${tDouble}\" class=\"easyui-numberbox textbox\"  />', null, null, null, '99999', null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('16', 'tFloat', '200', null, 'tFloat', '', null, 'text', '', null, '', '', null, null, null, null, '', '10', 'tFloat', 'java.lang.Float', '=', 'property', '6', '', '\0', null, null, '26', '<input type=\"text\" name=\"tFloat\" value=\"${tFloat}\" class=\"easyui-numberbox textbox\"  data-options=\" min:0.0,max:9999.0,scale:6 \"/>', '@javax.validation.constraints.DecimalMin(\"0.0\") \r	@javax.validation.constraints.DecimalMax(\"9999.0\") \r	@javax.persistence.Column(name = \"tFloat\",precision = 10,scale = 6,unique = false,nullable = true)', '{field:\'tFloat\',title:\'tFloat\',width:200,},', '<input type=\"text\" name=\"tFloat\" value=\"${tFloat}\" class=\"easyui-numberbox textbox\"  />', null, null, null, '9999', null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('17', 'tBigDecimal', '100', null, 'tBigDecimal', '', null, 'text', '', null, '', '', null, null, null, null, '', '20', 'tBigDecimal', 'java.math.BigDecimal', '=', 'property', '10', '', '\0', null, null, '26', '<input type=\"text\" name=\"tBigDecimal\" value=\"${tBigDecimal}\" class=\"easyui-numberbox textbox\"  data-options=\" min:0.0,max:99999.0,scale:10 \"/>', '@javax.validation.constraints.DecimalMin(\"0.0\") \r	@javax.validation.constraints.DecimalMax(\"99999.0\") \r	@javax.persistence.Column(name = \"tBigDecimal\",precision = 20,scale = 10,unique = false,nullable = true)', '{field:\'tBigDecimal\',title:\'tBigDecimal\',width:100,},', '<input type=\"text\" name=\"tBigDecimal\" value=\"${tBigDecimal}\" class=\"easyui-numberbox textbox\"  />', null, null, null, '99999', null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('18', 'tDate', '200', null, 'tDate', null, null, 'date', '', null, '', '', null, null, null, null, '', null, 'tDate', 'java.util.Date', '>', 'property', null, '', '\0', null, null, '26', '<input type=\"text\" name=\"tDate\" value=\"${tDate}\" easyui-datebox  data-options=\" \"/>', '@javax.validation.constraints.Past\r	@org.springframework.format.annotation.DateTimeFormat(pattern = \"yyyy-MM-dd\")\r	@javax.persistence.Temporal(javax.persistence.TemporalType.DATE)\r	@javax.persistence.Column(name = \"tDate\")', '{field:\'tDate\',title:\'tDate\',width:200,},', '<input type=\"text\" name=\"tDate\" value=\"${tDate}\" easyui-datebox  />', null, null, null, null, null, null, '\0', '', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('200', 'tDateTime', '200', null, 'tDateTime', null, null, 'datetime', '', null, '', '', null, null, null, null, '', null, 'tDateTime', 'java.util.Date', '>', 'property', null, '', '\0', null, null, '26', '<input type=\"text\" name=\"tDateTime\" value=\"${tDateTime}\" easyui-datetimebox  data-options=\" \"/>', '@javax.validation.constraints.Future\r	@org.springframework.format.annotation.DateTimeFormat(pattern = \"yyyy-MM-dd HH:mm:ss\")\r	@javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)\r	@javax.persistence.Column(name = \"tDateTime\")', '{field:\'tDateTime\',title:\'tDateTime\',width:200,},', '<input type=\"text\" name=\"tDateTime\" value=\"${tDateTime}\" easyui-datetimebox  />', null, null, null, null, null, null, '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('201', 'tDateCurrent', '200', null, 'tDateCurrent', null, null, 'autoCurrentTime', '', null, '', '\0', null, null, null, null, '', null, 'tDateCurrent', 'java.util.Date', '=', 'property', null, '', '\0', null, null, '26', '<input type=\"text\" name=\"tDateCurrent\" value=\"${tDateCurrent}\" easyui-datetimebox  data-options=\" \"/>', '@com.zht.framework.core.annos.CurrentTimeStamp\r	@org.springframework.format.annotation.DateTimeFormat(pattern = \"yyyy-MM-dd HH:mm:ss\")\r	@javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)\r	@javax.persistence.Column(name = \"tDateCurrent\")', '{field:\'tDateCurrent\',title:\'tDateCurrent\',width:200,},', '<input type=\"text\" name=\"tDateCurrent\" value=\"${tDateCurrent}\" easyui-datetimebox  />', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('202', 'dis_index', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '\0', '19', 'disIndex', 'integer', null, 'property', '2', null, '\0', null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('203', 'display', null, null, null, null, null, null, null, null, null, null, null, '32', null, null, '\0', '19', 'display', 'string', null, 'property', '2', null, '', null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('204', 'enabled', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '\0', '19', 'enabled', 'boolean', null, 'property', '2', null, '\0', null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('205', 'icon', null, null, null, null, null, null, null, null, null, null, null, '20', null, null, '', '19', 'icon', 'string', null, 'property', '2', null, '\0', null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('206', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'rbacMenus', 'Set', null, 'OneToMany', null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('207', 'remark', null, null, null, null, null, null, null, null, null, null, null, '30', null, null, '', '19', 'remark', 'string', null, 'property', '2', null, '\0', null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('208', 'date', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'date', 'date', null, 'property', '2', null, '\0', null, null, '2', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('209', 'description', null, null, null, null, null, null, null, null, null, null, null, '30', null, null, '\0', '19', 'description', 'string', null, 'property', '2', null, '\0', null, null, '2', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('210', 'enabled', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '\0', '19', 'enabled', 'boolean', null, 'property', '2', null, '\0', null, null, '2', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('211', 'full_time', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'fullTime', 'timestamp', null, 'property', '2', null, '\0', null, null, '2', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('212', 'loginname', null, null, null, null, null, null, null, null, null, null, null, '60', null, null, '\0', '19', 'loginname', 'string', null, 'property', '2', null, '', null, null, '2', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('213', 'password', null, null, null, null, null, null, null, null, null, null, null, '60', null, null, '\0', '19', 'password', 'string', null, 'property', '2', null, '\0', null, null, '2', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('214', 'time_only', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'timeOnly', 'time', null, 'property', '2', null, '\0', null, null, '2', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('215', 'time_stamp', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'timestamp', 'timestamp', null, 'property', '2', null, '\0', null, null, '2', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('216', 'date', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'date', 'date', null, 'property', '2', null, '\0', null, null, '3', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('217', 'description', null, null, null, null, null, null, null, null, null, null, null, '30', null, null, '\0', '19', 'description', 'string', null, 'property', '2', null, '\0', null, null, '3', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('218', 'enabled', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '\0', '19', 'enabled', 'boolean', null, 'property', '2', null, '\0', null, null, '3', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('219', 'full_time', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'fullTime', 'timestamp', null, 'property', '2', null, '\0', null, null, '3', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('220', 'loginname', null, null, null, null, null, null, null, null, null, null, null, '60', null, null, '\0', '19', 'loginname', 'string', null, 'property', '2', null, '', null, null, '3', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('221', 'password', null, null, null, null, null, null, null, null, null, null, null, '60', null, null, '\0', '19', 'password', 'string', null, 'property', '2', null, '\0', null, null, '3', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('222', 'time_only', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'timeOnly', 'time', null, 'property', '2', null, '\0', null, null, '3', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('223', 'time_stamp', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'timestamp', 'timestamp', null, 'property', '2', null, '\0', null, null, '3', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('224', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'childRbacMenus', 'Set', null, 'OneToMany', null, null, null, null, null, '4', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('225', 'description', null, null, null, null, null, null, null, null, null, null, null, '60', null, null, '', '19', 'description', 'string', null, 'property', '2', null, '\0', null, null, '4', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('226', 'dis_index', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'disIndex', 'integer', null, 'property', '2', null, '\0', null, null, '4', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('227', 'display', null, null, null, null, null, null, null, null, null, null, null, '40', null, null, '\0', '19', 'display', 'string', null, 'property', '2', null, '\0', null, null, '4', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('228', 'iconCls', null, null, null, null, null, null, null, null, null, null, null, '40', null, null, '\0', '19', 'iconCls', 'string', null, 'property', '2', null, '\0', null, null, '4', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('229', 'parent_id', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'parentRbacMenu', 'RbacMenu', null, 'ManyToOne', '2', null, '\0', null, null, '4', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('230', 'module_id', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'rbacModule', 'RbacModule', null, 'ManyToOne', '2', null, '\0', null, null, '4', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('231', 'permission_id', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'rbacPermission', 'RbacPermission', null, 'ManyToOne', '2', null, '\0', null, null, '4', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('232', 'tab_title', null, null, null, null, null, null, null, null, null, null, null, '40', null, null, '', '19', 'tabTitle', 'string', null, 'property', '2', null, '\0', null, null, '4', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('233', 'type', null, null, null, null, null, null, null, null, null, null, null, '1', null, null, '', '19', 'type', 'string', null, 'property', '2', null, '\0', null, null, '4', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('234', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'childRbacResources', 'Set', null, 'OneToMany', null, null, null, null, null, '5', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('235', 'description', null, null, null, null, null, null, null, null, null, null, null, '40', null, null, '', '19', 'description', 'string', null, 'property', '2', null, '\0', null, null, '5', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('236', 'dis_index', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'disIndex', 'integer', null, 'property', '2', null, '\0', null, null, '5', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('237', 'icon', null, null, null, null, null, null, null, null, null, null, null, '10', null, null, '', '19', 'icon', 'string', null, 'property', '2', null, '\0', null, null, '5', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('238', 'name', null, null, null, null, null, null, null, null, null, null, null, '40', null, null, '\0', '19', 'name', 'string', null, 'property', '2', null, '\0', null, null, '5', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('239', 'parent_id', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'parentRbacResource', 'RbacResource', null, 'ManyToOne', '2', null, '\0', null, null, '5', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('240', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'rbacPermissions', 'Set', null, 'OneToMany', null, null, null, null, null, '5', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('241', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'SysDepartRoles', 'Set', null, 'OneToMany', null, null, null, null, null, '6', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('242', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'childSysDepartments', 'Set', null, 'OneToMany', null, null, null, null, null, '6', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('243', 'create_time', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'createTime', 'timestamp', null, 'property', '2', null, '\0', null, null, '6', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('244', 'creater', null, null, null, null, null, null, null, null, null, null, null, '30', null, null, '', '19', 'creater', 'string', null, 'property', '2', null, '\0', null, null, '6', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('245', 'description', null, null, null, null, null, null, null, null, null, null, null, '60', null, null, '', '19', 'description', 'string', null, 'property', '2', null, '\0', null, null, '6', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('246', 'enabled', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '\0', '19', 'enabled', 'boolean', null, 'property', '2', null, '\0', null, null, '6', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('247', 'name', null, null, null, null, null, null, null, null, null, null, null, '30', null, null, '\0', '19', 'name', 'string', null, 'property', '2', null, '', null, null, '6', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('248', 'parent_id', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'parentSysDepartment', 'SysDepartment', null, 'ManyToOne', '2', null, '\0', null, null, '6', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('249', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'sysDepartUsers', 'Set', null, 'OneToMany', null, null, null, null, null, '6', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('250', 'code', null, null, null, null, null, null, null, null, null, null, null, '40', null, null, '\0', '19', 'code', 'string', null, 'property', '2', null, '\0', null, null, '7', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('251', 'description', null, null, null, null, null, null, null, null, null, null, null, '60', null, null, '', '19', 'description', 'string', null, 'property', '2', null, '\0', null, null, '7', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('252', 'enabled', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '\0', '19', 'enabled', 'boolean', null, 'property', '2', null, '\0', null, null, '7', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('253', 'name', null, null, null, null, null, null, null, null, null, null, null, '40', null, null, '\0', '19', 'name', 'string', null, 'property', '2', null, '\0', null, null, '7', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('254', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'rbacGroupRoles', 'Set', null, 'OneToMany', null, null, null, null, null, '7', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('255', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'rbacRolePermission', 'Set', null, 'ManyToMany', null, null, null, null, null, '7', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('256', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'sysDepartments', 'Set', null, 'OneToMany', null, null, null, null, null, '7', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('257', 'code', null, null, null, null, null, null, null, null, null, null, null, '40', null, null, '\0', '19', 'code', 'string', null, 'property', '2', null, '\0', null, null, '8', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('258', 'description', null, null, null, null, null, null, null, null, null, null, null, '60', null, null, '', '19', 'description', 'string', null, 'property', '2', null, '\0', null, null, '8', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('259', 'enabled', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '\0', '19', 'enabled', 'boolean', null, 'property', '2', null, '\0', null, null, '8', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('260', 'name', null, null, null, null, null, null, null, null, null, null, null, '40', null, null, '\0', '19', 'name', 'string', null, 'property', '2', null, '\0', null, null, '8', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('261', 'resource_id', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'rbacResource', 'RbacResource', null, 'ManyToOne', '2', null, '\0', null, null, '8', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('262', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'rbacRoles', 'Set', null, 'ManyToMany', null, null, null, null, null, '8', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('263', 'type', null, null, null, null, null, null, null, null, null, null, null, '1', null, null, '\0', '19', 'type', 'string', null, 'property', '2', null, '\0', null, null, '8', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('264', 'url', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '\0', '19', 'url', 'string', null, 'property', '2', null, '\0', null, null, '8', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('265', 'rbac_role_id', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'rbacRole', 'RbacRole', null, 'ManyToOne', '2', null, '\0', null, null, '9', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('266', 'sys_department_id', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'sysDepartment', 'SysDepartment', null, 'ManyToOne', '2', null, '\0', null, null, '9', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('267', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'childRbacGroups', 'Set', null, 'OneToMany', null, null, null, null, null, '10', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('268', 'create_time', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'createTime', 'timestamp', null, 'property', '2', null, '\0', null, null, '10', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('269', 'creater', null, null, null, null, null, null, null, null, null, null, null, '30', null, null, '', '19', 'creater', 'string', null, 'property', '2', null, '\0', null, null, '10', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('270', 'description', null, null, null, null, null, null, null, null, null, null, null, '60', null, null, '', '19', 'description', 'string', null, 'property', '2', null, '\0', null, null, '10', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('271', 'enabled', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '\0', '19', 'enabled', 'boolean', null, 'property', '2', null, '\0', null, null, '10', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('272', 'name', null, null, null, null, null, null, null, null, null, null, null, '30', null, null, '\0', '19', 'name', 'string', null, 'property', '2', null, '', null, null, '10', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('273', 'parent_id', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'parentRbacGroup', 'RbacGroup', null, 'ManyToOne', '2', null, '\0', null, null, '10', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('274', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'rbacGroupRoles', 'Set', null, 'OneToMany', null, null, null, null, null, '10', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('275', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'rbacGroupUsers', 'Set', null, 'OneToMany', null, null, null, null, null, '10', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('276', 'rbac_group_id', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'rbacGroup', 'RbacGroup', null, 'ManyToOne', '2', null, '\0', null, null, '11', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('277', 'rbac_role_id', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'rbacRole', 'RbacRole', null, 'ManyToOne', '2', null, '\0', null, null, '11', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('278', 'loginname', null, null, null, null, null, null, null, null, null, null, null, '18', null, null, '', '19', 'ipAddress', 'string', null, 'property', '2', null, '\0', null, null, '12', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('279', 'end_time', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'loginTime', 'timestamp', null, 'property', '2', null, '\0', null, null, '12', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('280', 'rbac_user_id', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'rbacUser', 'RbacUser', null, 'ManyToOne', '2', null, '\0', null, null, '12', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('281', 'columnName', null, null, null, null, null, null, null, null, null, null, null, '30', null, null, '', '19', 'columnName', 'string', null, 'property', '2', null, '\0', null, null, '25', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('282', 'columnWidth', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'columnWidth', 'integer', null, 'property', '2', null, '\0', null, null, '25', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('283', 'display_z', null, null, null, null, null, null, null, null, null, null, null, '30', null, null, '', '19', 'display', 'string', null, 'property', '2', null, '\0', null, null, '25', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('284', 'editContent', null, null, null, null, null, null, null, null, null, null, null, '100', null, null, '', '19', 'editContent', 'string', null, 'property', '2', null, '\0', null, null, '25', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('285', 'editFinalContentStr', null, null, null, null, null, null, null, null, null, null, null, '300', null, null, '', '19', 'editFinalContentStr', 'string', null, 'property', '2', null, '\0', null, null, '25', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('286', 'editType', null, null, null, null, null, null, null, null, null, null, null, '30', null, null, '', '19', 'editType', 'string', null, 'property', '2', null, '\0', null, null, '25', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('287', 'genEntity_id', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'genEntity', 'GenEntity', null, 'ManyToOne', '2', null, '\0', null, null, '25', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('288', 'generatedEditOfPropertyStr', null, null, null, null, null, null, null, null, null, null, null, '1000', null, null, '', '19', 'generatedEditOfPropertyStr', 'string', null, 'property', '2', null, '\0', null, null, '25', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('289', 'generatedHibernateModelOfPropertyStr', null, null, null, null, null, null, null, null, null, null, null, '1000', null, null, '', '19', 'generatedHibernateModelOfPropertyStr', 'string', null, 'property', '2', null, '\0', null, null, '25', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('290', 'generatedListDisplayOfPropertyStr', null, null, null, null, null, null, null, null, null, null, null, '1000', null, null, '', '19', 'generatedListDisplayOfPropertyStr', 'string', null, 'property', '2', null, '\0', null, null, '25', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('291', 'generatedQueryOfPropertyStr', null, null, null, null, null, null, null, null, null, null, null, '1000', null, null, '', '19', 'generatedQueryOfPropertyStr', 'string', null, 'property', '2', null, '\0', null, null, '25', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('292', 'isList', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'isList', 'boolean', null, 'property', '2', null, '\0', null, null, '25', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('293', 'isLookUpDisplay', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'isLookUpDisplay', 'boolean', null, 'property', '2', null, '\0', null, null, '25', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('294', 'isQueryCondtion', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'isQueryCondtion', 'boolean', null, 'property', '2', null, '\0', null, null, '25', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('295', 'isSorter', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'isSorter', 'boolean', null, 'property', '2', null, '\0', null, null, '25', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('296', 'isTreeProperty', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'isTreeProperty', 'boolean', null, 'property', '2', null, '\0', null, null, '25', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('297', 'length_z', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'length', 'integer', null, 'property', '2', null, '\0', null, null, '25', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('298', 'lookUpType', null, null, null, null, null, null, null, null, null, null, null, '30', null, null, '', '19', 'lookUpType', 'string', null, 'property', '2', null, '\0', null, null, '25', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('299', 'M2O_O2O_DisplayField', null, null, null, null, null, null, null, null, null, null, null, '60', null, null, '', '19', 'mTOOTOField', 'string', null, null, '2', null, '\0', null, null, '25', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('300', 'max_length', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'maxLength', 'integer', null, 'property', '2', null, '\0', null, null, '25', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('301', 'max_value', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'maxValue', 'long', null, 'property', '2', null, '\0', null, null, '25', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('302', 'min_length', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'minLength', 'integer', null, 'property', '2', null, '\0', null, null, '25', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('303', 'min_value', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'minValue', 'long', null, 'property', '2', null, '\0', null, null, '25', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('304', 'nullable_z', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'nullable', 'boolean', null, 'property', '2', null, '\0', null, null, '25', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('305', 'precision_z', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'precision', 'integer', null, 'property', '2', null, '\0', null, null, '25', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('306', 'propertyName', null, null, null, null, null, null, null, null, null, null, null, '40', null, null, '', '19', 'propertyName', 'string', null, 'property', '2', null, '\0', null, null, '25', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('307', 'propertyType', null, null, null, null, null, null, null, null, null, null, null, '40', null, null, '', '19', 'propertyType', 'string', null, 'property', '2', null, '\0', null, null, '25', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('308', 'queryType', null, null, null, null, null, null, null, null, null, null, null, '30', null, null, '', '19', 'queryType', 'string', null, 'property', '2', null, '\0', null, null, '25', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('309', 'relationType', null, null, null, null, null, null, null, null, null, null, null, '30', null, null, '', '19', 'relationType', 'string', null, 'property', '2', null, '\0', null, null, '25', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('310', 'scale_z', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'scale', 'integer', null, 'property', '2', null, '\0', null, null, '25', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('311', 'simpleFormat', null, null, null, null, null, null, null, null, null, null, null, '1000', null, null, '', '19', 'simpleFormat', 'string', null, 'property', '2', null, '\0', null, null, '25', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('312', 'unique_z', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'unique', 'boolean', null, 'property', '2', null, '\0', null, null, '25', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('313', 'validateContent', null, null, null, null, null, null, null, null, null, null, null, '30', null, null, '', '19', 'validateContent', 'string', null, 'property', '2', null, '\0', null, null, '25', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('314', 'validateType', null, null, null, null, null, null, null, null, null, null, null, '30', null, null, '', '19', 'validateType', 'string', null, 'property', '2', null, '\0', null, null, '25', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('315', 'rbac_role_id', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'rbacRole', 'RbacRole', null, 'ManyToOne', '2', null, '\0', null, null, '13', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('316', 'rbac_user_id', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'rbacUser', 'RbacUser', null, 'ManyToOne', '2', null, '\0', null, null, '13', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('317', 'description', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'description', 'string', null, 'property', '2', null, '\0', null, null, '14', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('318', 'enabled', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '\0', '19', 'enabled', 'boolean', null, 'property', '2', null, '\0', null, null, '14', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('319', 'full_time', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'fullTime', 'timestamp', null, 'property', '2', null, '\0', null, null, '14', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('320', 'loginname', null, null, null, null, null, null, null, null, null, null, null, '60', null, null, '\0', '19', 'loginname', 'string', null, 'property', '2', null, '', null, null, '14', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('321', 'password', null, null, null, null, null, null, null, null, null, null, null, '60', null, null, '\0', '19', 'password', 'string', null, 'property', '2', null, '\0', null, null, '14', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('322', 'start_date', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'startDate', 'date', null, 'property', '2', null, '\0', null, null, '14', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('323', 'start_time', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'startTime', 'time', null, 'property', '2', null, '\0', null, null, '14', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('324', 'accountNonExpired', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '\0', '19', 'accountNonExpired', 'boolean', null, 'property', '2', null, '\0', null, null, '16', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('325', 'accountNonLocked', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '\0', '19', 'accountNonLocked', 'boolean', null, 'property', '2', null, '\0', null, null, '16', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('326', 'alias', null, null, null, null, null, null, null, null, null, null, null, '30', null, null, '', '19', 'alias', 'string', null, 'property', '2', null, '\0', null, null, '16', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('327', 'credentialsNonExpired', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '\0', '19', 'credentialsNonExpired', 'boolean', null, 'property', '2', null, '\0', null, null, '16', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('328', 'defaultRoleId', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'defaultRbacRole', 'RbacRole', null, 'ManyToOne', '2', null, '\0', null, null, '16', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('329', 'description', null, null, null, null, null, null, null, null, null, null, null, '30', null, null, '', '19', 'description', 'string', null, 'property', '2', null, '\0', null, null, '16', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('330', 'enabled', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '\0', '19', 'enabled', 'boolean', null, 'property', '2', null, '\0', null, null, '16', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('331', 'loginname', null, null, null, null, null, null, null, null, null, null, null, '30', null, null, '\0', '19', 'loginname', 'string', null, 'property', '2', null, '', null, null, '16', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('332', 'password', null, null, null, null, null, null, null, null, null, null, null, '36', null, null, '', '19', 'password', 'string', null, 'property', '2', null, '\0', null, null, '16', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('333', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'rbacGroupUsers', 'Set', null, 'OneToMany', null, null, null, null, null, '16', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('334', 'salt', null, null, null, null, null, null, null, null, null, null, null, '36', null, null, '', '19', 'salt', 'string', null, 'property', '2', null, '\0', null, null, '16', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('335', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'sysDepartmentUsers', 'Set', null, 'OneToMany', null, null, null, null, null, '16', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('336', 'rbac_group_id', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'rbacGroup', 'RbacGroup', null, 'ManyToOne', '2', null, '\0', null, null, '17', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('337', 'rbac_user_id', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'rbacUser', 'RbacUser', null, 'ManyToOne', '2', null, '\0', null, null, '17', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('338', 'cardTypeCode', null, null, null, null, null, null, null, null, null, null, null, '3', null, null, '', '19', 'cardTypeCode', 'string', null, 'property', '2', null, '\0', null, null, '18', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('339', 'EMAIL', null, null, null, null, null, null, null, null, null, null, null, '50', null, null, '', '19', 'email', 'string', null, 'property', '2', null, '\0', null, null, '18', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('340', 'famPostcalCode', null, null, null, null, null, null, null, null, null, null, null, '6', null, null, '', '19', 'famPostcalCode', 'string', null, 'property', '2', null, '\0', null, null, '18', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('341', 'famTelephone', null, null, null, null, null, null, null, null, null, null, null, '15', null, null, '', '19', 'famTelephone', 'string', null, 'property', '2', null, '\0', null, null, '18', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('342', 'famillyAddress', null, null, null, null, null, null, null, null, null, null, null, '50', null, null, '', '19', 'famillyAddress', 'string', null, 'property', '2', null, '\0', null, null, '18', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('343', 'genderCode', null, null, null, null, null, null, null, null, null, null, null, '5', null, null, '\0', '19', 'genderCode', 'string', null, 'property', '2', null, '\0', null, null, '18', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('344', 'isMarried', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'isMarried', 'integer', null, 'property', '2', null, '\0', null, null, '18', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('345', 'mobilePhone', null, null, null, null, null, null, null, null, null, null, null, '15', null, null, '', '19', 'mobilePhone', 'string', null, 'property', '2', null, '\0', null, null, '18', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('346', 'MSN', null, null, null, null, null, null, null, null, null, null, null, '50', null, null, '', '19', 'msn', 'string', null, 'property', '2', null, '\0', null, null, '18', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('347', 'nameSpell', null, null, null, null, null, null, null, null, null, null, null, '60', null, null, '', '19', 'nameSpell', 'string', null, 'property', '2', null, '\0', null, null, '18', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('348', 'nationId', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'nationId', 'long', null, 'property', '2', null, '\0', null, null, '18', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('349', 'nativeCity', null, null, null, null, null, null, null, null, null, null, null, '30', null, null, '', '19', 'nativeCity', 'string', null, 'property', '2', null, '\0', null, null, '18', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('350', 'nativeCounty', null, null, null, null, null, null, null, null, null, null, null, '30', null, null, '', '19', 'nativeCounty', 'string', null, 'property', '2', null, '\0', null, null, '18', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('351', 'nativeProvince', null, null, null, null, null, null, null, null, null, null, null, '20', null, null, '', '19', 'nativeProvince', 'string', null, 'property', '2', null, '\0', null, null, '18', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('352', 'peopleId', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'peopleId', 'long', null, 'property', '2', null, '\0', null, null, '18', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('353', 'perAddress', null, null, null, null, null, null, null, null, null, null, null, '50', null, null, '', '19', 'perAddress', 'string', null, 'property', '2', null, '\0', null, null, '18', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('354', 'perBirthday', null, null, null, null, null, null, null, null, null, null, null, '10', null, null, '', '19', 'perBirthday', 'date', null, 'property', '2', null, '\0', null, null, '18', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('355', 'perEnglishName', null, null, null, null, null, null, null, null, null, null, null, '60', null, null, '', '19', 'perEnglishName', 'string', null, 'property', '2', null, '\0', null, null, '18', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('356', 'perIdCard', null, null, null, null, null, null, null, null, null, null, null, '18', null, null, '', '19', 'perIdCard', 'string', null, 'property', '2', null, '\0', null, null, '18', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('357', 'perName', null, null, null, null, null, null, null, null, null, null, null, '50', null, null, '\0', '19', 'perName', 'string', null, 'property', '2', null, '\0', null, null, '18', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('358', 'perNum', null, null, null, null, null, null, null, null, null, null, null, '20', null, null, '\0', '19', 'perNum', 'string', null, 'property', '2', null, '', null, null, '18', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('359', 'perPostalCode', null, null, null, null, null, null, null, null, null, null, null, '6', null, null, '', '19', 'perPostalCode', 'string', null, 'property', '2', null, '\0', null, null, '18', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('360', 'perTelephone', null, null, null, null, null, null, null, null, null, null, null, '20', null, null, '', '19', 'perTelephone', 'string', null, 'property', '2', null, '\0', null, null, '18', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('361', 'rbac_role_id', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'rbacRole', 'RbacRole', null, 'ManyToOne', '2', null, '\0', null, null, '19', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('362', 'rbac_user_id', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'rbacUser', 'RbacUser', null, 'ManyToOne', '2', null, '\0', null, null, '19', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('363', 'rejected', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '\0', '19', 'rejected', 'boolean', null, 'property', '2', null, '\0', null, null, '19', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('364', 'rbac_user_id', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'rbacUser', 'RbacUser', null, 'ManyToOne', '2', null, '\0', null, null, '20', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('365', 'sys_department_id', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'sysDepartment', 'SysDepartment', null, 'ManyToOne', '2', null, '\0', null, null, '20', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('366', 'email', null, null, null, null, null, null, null, null, null, null, null, '50', null, null, '', '19', 'email', 'string', null, 'property', '2', null, '\0', null, null, '21', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('367', 'enable', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'enable', 'boolean', null, 'property', '2', null, '\0', null, null, '21', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('368', 'mobilePhone', null, null, null, null, null, null, null, null, null, null, null, '15', null, null, '', '19', 'mobilePhone', 'string', null, 'property', '2', null, '\0', null, null, '21', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('369', 'perName', null, null, null, null, null, null, null, null, null, null, null, '50', null, null, '\0', '19', 'perName', 'string', null, 'property', '2', null, '\0', null, null, '21', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('370', 'perNum', null, null, null, null, null, null, null, null, null, null, null, '20', null, null, '\0', '19', 'perNum', 'string', null, 'property', '2', null, '', null, null, '21', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('371', 'create_time', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'careateTime', 'timestamp', null, 'property', '2', null, '\0', null, null, '22', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('372', 'controllerNameSpace', null, null, null, null, null, null, null, null, null, null, null, '60', null, null, '', '19', 'controllerNameSpace', 'string', null, 'property', '2', null, '\0', null, null, '22', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('373', 'entityDisName', null, null, null, null, null, null, null, null, null, null, null, '60', null, null, '', '19', 'entityDisName', 'string', null, 'property', '2', null, '\0', null, null, '22', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('374', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'genEntityPropertyList', 'Set', null, 'OneToMany', null, null, null, null, null, '22', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('375', 'gen_info', null, null, null, null, null, null, null, null, null, null, null, '60', null, null, '', '19', 'genInfo', 'string', null, 'property', '2', null, '\0', null, null, '22', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('376', 'isTree', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'isTree', 'boolean', null, 'property', '2', null, '\0', null, null, '22', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('377', 'last_modify_time', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'lastModifyTime', 'timestamp', null, 'property', '2', null, '\0', null, null, '22', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('378', 'module_name', null, null, null, null, null, null, null, null, null, null, null, '60', null, null, '', '19', 'moduleName', 'string', null, 'property', '2', null, '\0', null, null, '22', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('379', 'name', null, null, null, null, null, null, null, null, null, null, null, '100', null, null, '\0', '19', 'name', 'string', null, 'property', '2', null, '', null, null, '22', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('380', 'remark', null, null, null, null, null, null, null, null, null, null, null, '60', null, null, '', '19', 'remark', 'string', null, 'property', '2', null, '\0', null, null, '22', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('381', 'tableName', null, null, null, null, null, null, null, null, null, null, null, '60', null, null, '', '19', 'tableName', 'string', null, 'property', '2', null, '\0', null, null, '22', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('382', 'version', null, null, null, null, null, null, null, null, null, null, null, '10', null, null, '', '19', 'version', 'string', null, 'property', '2', null, '\0', null, null, '22', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('383', 'isDateFutrue', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'isDateFutrue', 'boolean', null, 'property', '2', null, '\0', null, null, '25', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('384', 'isDatePost', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'isDatePost', 'boolean', null, 'property', '2', null, '\0', null, null, '25', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('385', 'tManyToOne', '4124', null, 'tManyToOne', '', null, 'text', '', null, '\0', '\0', null, '2542', null, null, '', null, 'tManyToOne', 'com.zht.common.rabc.easyui.model.RbacModule', '', 'manytoone', null, '', '\0', null, '', '26', null, '@javax.persistence.ManyToOne(fetch=javax.persistence.FetchType.LAZY)\r	@javax.persistence.JoinColumn(name=\"tManyToOne\")', null, null, null, null, '2542', null, '0', null, null, null, 'display', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('386', 'tOneToMany', null, null, 'tOneToMany', null, null, null, null, null, null, null, null, null, null, null, null, null, 'tOneToMany', 'com.zht.common.rabc.easyui.model.RbacMenu', null, 'onetomany', null, null, null, null, null, '26', null, '@javax.persistence.OneToMany(fetch=javax.persistence.FetchType.LAZY,cascade=javax.persistence.CascadeType.ALL, mappedBy=\"rbacModule\")', null, null, null, null, null, null, null, null, null, null, null, 'rbacModule', null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('387', 'manyToOneDisplayField', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'manyToOneDisplayField', 'string', null, 'property', '2', null, '\0', null, null, '25', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('388', 'oneTomanyMappedBy', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'oneTomanyMappedBy', 'string', null, 'property', '2', null, '\0', null, null, '25', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('389', 'childTreeDemoEntirys', null, null, '子节点', null, null, null, null, null, null, null, null, null, null, null, null, null, 'childTreeDemoEntirys', 'com.zht.projeect.test.model.TreeDemoEntiry', null, 'onetomany', null, null, null, null, null, '27', null, '@javax.persistence.OneToMany(fetch=javax.persistence.FetchType.LAZY,cascade=javax.persistence.CascadeType.ALL, mappedBy=\"parentTreeDemoEntiry\")', null, null, null, null, null, null, null, null, null, null, null, 'parentTreeDemoEntiry', null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('390', 'parentTreeDemoEntiry', '200', null, '父节点', '', null, 'text', '', null, '\0', '\0', '', null, null, null, null, null, 'parentTreeDemoEntiry', 'com.zht.projeect.test.model.TreeDemoEntiry', '', 'manytoone', null, '', null, null, null, '27', null, '@javax.persistence.ManyToOne(fetch=javax.persistence.FetchType.LAZY)\r	@javax.persistence.JoinColumn(name=\"parentTreeDemoEntiry\")\r	@com.zht.framework.core.annos.TreeParentFied()', null, null, null, null, null, null, null, null, null, null, 'display', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('391', 'display', '100', null, '显示', '', null, 'text', '', null, '\0', '\0', null, '30', null, null, '', null, 'display', 'java.lang.String', '', 'property', null, '', '\0', null, '', '27', '<input type=\"text\" name=\"display\" value=\"${display}\" class=\"easyui-validatebox textbox\"  data-options=\"  validType:\'length[0,30]\'\"/>', '@javax.persistence.Column(name = \"display\",unique = false,nullable = true,length = 30)', null, null, null, null, '30', null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('392', 'isManytomanyMasterControl', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'isManytomanyMasterControl', 'boolean', null, null, '2', null, '\0', null, null, '25', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('393', 'manytomanyJoinColumnOpposite', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'manytomanyJoinColumnOpposite', 'string', null, null, '2', null, '\0', null, null, '25', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('394', 'manytomanyJoinColumnSelf', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'manytomanyJoinColumnSelf', 'string', null, null, '2', null, '\0', null, null, '25', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('395', 'manytomanyJoinTable', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'manytomanyJoinTable', 'string', null, null, '2', null, '\0', null, null, '25', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('396', 'tManyToMany', null, null, 'tManyToMany', null, null, null, null, null, null, null, null, null, null, null, null, null, 'tManyToMany', 'com.zht.common.rabc.easyui.model.RbacRole', null, 'manytomany', null, null, null, null, null, '26', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('397', 'sdasd', null, null, 'sfsd', null, null, null, null, null, null, null, null, null, null, null, null, null, 'MayToMaySlavers', 'com.zht.project.model.MayToMaySlaver', null, 'manytomany', null, null, null, null, null, '28', null, '@javax.persistence.ManyToMany(fetch=javax.persistence.FetchType.LAZY)\r	@javax.persistence.JoinTable(name=\"maste_rslaver\", joinColumns = {\r		@javax.persistence.JoinColumn(name=\"master_id\", nullable=false, updatable=false) },\r	inverseJoinColumns = { @javax.persistence.JoinColumn(name=\"slaver_id\", nullable=false, updatable=false) })', null, null, null, null, null, null, null, null, null, null, null, '', '', null, 'master_id', 'maste_rslaver', 'slaver_id', '', null, null, null);
INSERT INTO `zht_entity_property` VALUES ('398', 'sManyTomany', null, null, 'sManyTomany', null, null, null, null, null, null, null, null, null, null, null, null, null, 'sManyTomany', 'com.zht.project.model.MayToMayMaster', null, 'manytomany', null, null, null, null, null, '29', null, '@javax.persistence.ManyToMany(cascade=javax.persistence.CascadeType.ALL,mappedBy=\"MayToMaySlavers\")', null, null, null, null, null, null, null, null, null, null, null, 'MayToMaySlavers', '\0', null, '', '', '', 'MayToMaySlavers', null, null, null);
INSERT INTO `zht_entity_property` VALUES ('399', 'manytomanyMappedBy', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'manytomanyMappedBy', 'string', null, 'property', '2', null, '\0', null, null, '25', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('400', 'cardTypeCode', null, null, null, null, null, null, null, null, null, null, null, '3', null, null, '', '19', 'cardTypeCode', 'string', null, 'property', '2', null, '\0', null, null, '30', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('401', 'EMAIL', null, null, null, null, null, null, null, null, null, null, null, '50', null, null, '', '19', 'email', 'string', null, 'property', '2', null, '\0', null, null, '30', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('402', 'famPostcalCode', null, null, null, null, null, null, null, null, null, null, null, '6', null, null, '', '19', 'famPostcalCode', 'string', null, 'property', '2', null, '\0', null, null, '30', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('403', 'famTelephone', null, null, null, null, null, null, null, null, null, null, null, '15', null, null, '', '19', 'famTelephone', 'string', null, 'property', '2', null, '\0', null, null, '30', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('404', 'famillyAddress', null, null, null, null, null, null, null, null, null, null, null, '50', null, null, '', '19', 'famillyAddress', 'string', null, 'property', '2', null, '\0', null, null, '30', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('405', 'genderCode', null, null, null, null, null, null, null, null, null, null, null, '5', null, null, '\0', '19', 'genderCode', 'string', null, 'property', '2', null, '\0', null, null, '30', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('406', 'isMarried', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'isMarried', 'integer', null, 'property', '2', null, '\0', null, null, '30', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('407', 'mobilePhone', null, null, null, null, null, null, null, null, null, null, null, '15', null, null, '', '19', 'mobilePhone', 'string', null, 'property', '2', null, '\0', null, null, '30', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('408', 'MSN', null, null, null, null, null, null, null, null, null, null, null, '50', null, null, '', '19', 'msn', 'string', null, 'property', '2', null, '\0', null, null, '30', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('409', 'nameSpell', null, null, null, null, null, null, null, null, null, null, null, '60', null, null, '', '19', 'nameSpell', 'string', null, 'property', '2', null, '\0', null, null, '30', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('410', 'nationId', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'nationId', 'long', null, 'property', '2', null, '\0', null, null, '30', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('411', 'nativeCity', null, null, null, null, null, null, null, null, null, null, null, '30', null, null, '', '19', 'nativeCity', 'string', null, 'property', '2', null, '\0', null, null, '30', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('412', 'nativeCounty', null, null, null, null, null, null, null, null, null, null, null, '30', null, null, '', '19', 'nativeCounty', 'string', null, 'property', '2', null, '\0', null, null, '30', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('413', 'nativeProvince', null, null, null, null, null, null, null, null, null, null, null, '20', null, null, '', '19', 'nativeProvince', 'string', null, 'property', '2', null, '\0', null, null, '30', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('414', 'peopleId', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'peopleId', 'long', null, 'property', '2', null, '\0', null, null, '30', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('415', 'perAddress', null, null, null, null, null, null, null, null, null, null, null, '50', null, null, '', '19', 'perAddress', 'string', null, 'property', '2', null, '\0', null, null, '30', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('416', 'perBirthday', null, null, null, null, null, null, null, null, null, null, null, '10', null, null, '', '19', 'perBirthday', 'date', null, 'property', '2', null, '\0', null, null, '30', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('417', 'perEnglishName', null, null, null, null, null, null, null, null, null, null, null, '60', null, null, '', '19', 'perEnglishName', 'string', null, 'property', '2', null, '\0', null, null, '30', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('418', 'perIdCard', null, null, null, null, null, null, null, null, null, null, null, '18', null, null, '', '19', 'perIdCard', 'string', null, 'property', '2', null, '\0', null, null, '30', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('419', 'perName', null, null, null, null, null, null, null, null, null, null, null, '50', null, null, '\0', '19', 'perName', 'string', null, 'property', '2', null, '\0', null, null, '30', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('420', 'perNum', null, null, null, null, null, null, null, null, null, null, null, '20', null, null, '\0', '19', 'perNum', 'string', null, 'property', '2', null, '', null, null, '30', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('421', 'perPostalCode', null, null, null, null, null, null, null, null, null, null, null, '6', null, null, '', '19', 'perPostalCode', 'string', null, 'property', '2', null, '\0', null, null, '30', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('422', 'perTelephone', null, null, null, null, null, null, null, null, null, null, null, '20', null, null, '', '19', 'perTelephone', 'string', null, 'property', '2', null, '\0', null, null, '30', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('423', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'childRbacResources', 'Set', null, 'OneToMany', null, null, null, null, null, '31', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('424', 'description', null, null, null, null, null, null, null, null, null, null, null, '40', null, null, '', '19', 'description', 'string', null, 'property', '2', null, '\0', null, null, '31', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('425', 'dis_index', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'disIndex', 'integer', null, 'property', '2', null, '\0', null, null, '31', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('426', 'icon', null, null, null, null, null, null, null, null, null, null, null, '10', null, null, '', '19', 'icon', 'string', null, 'property', '2', null, '\0', null, null, '31', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('427', 'name', null, null, null, null, null, null, null, null, null, null, null, '40', null, null, '\0', '19', 'name', 'string', null, 'property', '2', null, '\0', null, null, '31', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('428', 'parent_id', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'parentRbacResource', 'RbacResource', null, 'ManyToOne', '2', null, '\0', null, null, '31', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('429', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'rbacPermissions', 'Set', null, 'OneToMany', null, null, null, null, null, '31', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('430', 'rbac_role_id', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'rbacRole', 'RbacRole', null, 'ManyToOne', '2', null, '\0', null, null, '32', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('431', 'rbac_user_id', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'rbacUser', 'RbacUser', null, 'ManyToOne', '2', null, '\0', null, null, '32', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('432', 'rbac_role_id', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'rbacRole', 'RbacRole', null, 'ManyToOne', '2', null, '\0', null, null, '33', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('433', 'sys_department_id', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'sysDepartment', 'SysDepartment', null, 'ManyToOne', '2', null, '\0', null, null, '33', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('434', 'dis_index', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '\0', '19', 'disIndex', 'integer', null, 'property', '2', null, '\0', null, null, '34', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('435', 'display', null, null, null, null, null, null, null, null, null, null, null, '32', null, null, '\0', '19', 'display', 'string', null, 'property', '2', null, '', null, null, '34', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('436', 'enabled', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '\0', '19', 'enabled', 'boolean', null, 'property', '2', null, '\0', null, null, '34', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('437', 'icon', null, null, null, null, null, null, null, null, null, null, null, '20', null, null, '', '19', 'icon', 'string', null, 'property', '2', null, '\0', null, null, '34', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('438', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'rbacMenus', 'Set', null, 'OneToMany', null, null, null, null, null, '34', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('439', 'remark', null, null, null, null, null, null, null, null, null, null, null, '30', null, null, '', '19', 'remark', 'string', null, 'property', '2', null, '\0', null, null, '34', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('440', 'accountNonExpired', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '\0', '19', 'accountNonExpired', 'boolean', null, 'property', '2', null, '\0', null, null, '35', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('441', 'accountNonLocked', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '\0', '19', 'accountNonLocked', 'boolean', null, 'property', '2', null, '\0', null, null, '35', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('442', 'alias', null, null, null, null, null, null, null, null, null, null, null, '30', null, null, '', '19', 'alias', 'string', null, 'property', '2', null, '\0', null, null, '35', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('443', 'credentialsNonExpired', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '\0', '19', 'credentialsNonExpired', 'boolean', null, 'property', '2', null, '\0', null, null, '35', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('444', 'defaultRoleId', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'defaultRbacRole', 'RbacRole', null, 'ManyToOne', '2', null, '\0', null, null, '35', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('445', 'description', null, null, null, null, null, null, null, null, null, null, null, '30', null, null, '', '19', 'description', 'string', null, 'property', '2', null, '\0', null, null, '35', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('446', 'enabled', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '\0', '19', 'enabled', 'boolean', null, 'property', '2', null, '\0', null, null, '35', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('447', 'loginname', null, null, null, null, null, null, null, null, null, null, null, '30', null, null, '\0', '19', 'loginname', 'string', null, 'property', '2', null, '', null, null, '35', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('448', 'password', null, null, null, null, null, null, null, null, null, null, null, '36', null, null, '', '19', 'password', 'string', null, 'property', '2', null, '\0', null, null, '35', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('449', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'rbacGroupUsers', 'Set', null, 'OneToMany', null, null, null, null, null, '35', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('450', 'salt', null, null, null, null, null, null, null, null, null, null, null, '36', null, null, '', '19', 'salt', 'string', null, 'property', '2', null, '\0', null, null, '35', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('451', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'sysDepartmentUsers', 'Set', null, 'OneToMany', null, null, null, null, null, '35', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('452', 'rbac_role_id', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'rbacRole', 'RbacRole', null, 'ManyToOne', '2', null, '\0', null, null, '36', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('453', 'rbac_user_id', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'rbacUser', 'RbacUser', null, 'ManyToOne', '2', null, '\0', null, null, '36', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('454', 'rejected', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '\0', '19', 'rejected', 'boolean', null, 'property', '2', null, '\0', null, null, '36', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('455', 'code', null, null, null, null, null, null, null, null, null, null, null, '40', null, null, '\0', '19', 'code', 'string', null, 'property', '2', null, '\0', null, null, '37', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('456', 'description', null, null, null, null, null, null, null, null, null, null, null, '60', null, null, '', '19', 'description', 'string', null, 'property', '2', null, '\0', null, null, '37', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('457', 'enabled', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '\0', '19', 'enabled', 'boolean', null, 'property', '2', null, '\0', null, null, '37', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('458', 'name', null, null, null, null, null, null, null, null, null, null, null, '40', null, null, '\0', '19', 'name', 'string', null, 'property', '2', null, '\0', null, null, '37', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('459', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'rbacGroupRoles', 'Set', null, 'OneToMany', null, null, null, null, null, '37', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('460', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'rbacRolePermission', 'Set', null, 'ManyToMany', null, null, null, null, null, '37', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('461', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'sysDepartments', 'Set', null, 'OneToMany', null, null, null, null, null, '37', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('462', 'loginname', null, null, null, null, null, null, null, null, null, null, null, '18', null, null, '', '19', 'ipAddress', 'string', null, 'property', '2', null, '\0', null, null, '38', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('463', 'end_time', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'loginTime', 'timestamp', null, 'property', '2', null, '\0', null, null, '38', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('464', 'rbac_user_id', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'rbacUser', 'RbacUser', null, 'ManyToOne', '2', null, '\0', null, null, '38', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('465', 'rbac_user_id', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'rbacUser', 'RbacUser', null, 'ManyToOne', '2', null, '\0', null, null, '39', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('466', 'sys_department_id', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'sysDepartment', 'SysDepartment', null, 'ManyToOne', '2', null, '\0', null, null, '39', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('467', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'childRbacGroups', 'Set', null, 'OneToMany', null, null, null, null, null, '40', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('468', 'create_time', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'createTime', 'timestamp', null, 'property', '2', null, '\0', null, null, '40', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('469', 'creater', null, null, null, null, null, null, null, null, null, null, null, '30', null, null, '', '19', 'creater', 'string', null, 'property', '2', null, '\0', null, null, '40', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('470', 'description', null, null, null, null, null, null, null, null, null, null, null, '60', null, null, '', '19', 'description', 'string', null, 'property', '2', null, '\0', null, null, '40', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('471', 'enabled', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '\0', '19', 'enabled', 'boolean', null, 'property', '2', null, '\0', null, null, '40', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('472', 'name', null, null, null, null, null, null, null, null, null, null, null, '30', null, null, '\0', '19', 'name', 'string', null, 'property', '2', null, '', null, null, '40', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('473', 'parent_id', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'parentRbacGroup', 'RbacGroup', null, 'property', '2', null, '\0', null, null, '40', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('474', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'rbacGroupRoles', 'Set', null, 'OneToMany', null, null, null, null, null, '40', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('475', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'rbacGroupUsers', 'Set', null, 'OneToMany', null, null, null, null, null, '40', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('476', 'rbac_group_id', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'rbacGroup', 'RbacGroup', null, 'ManyToOne', '2', null, '\0', null, null, '41', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('477', 'rbac_role_id', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'rbacRole', 'RbacRole', null, 'ManyToOne', '2', null, '\0', null, null, '41', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('478', 'code', null, null, null, null, null, null, null, null, null, null, null, '40', null, null, '\0', '19', 'code', 'string', null, 'property', '2', null, '\0', null, null, '42', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('479', 'description', null, null, null, null, null, null, null, null, null, null, null, '60', null, null, '', '19', 'description', 'string', null, 'property', '2', null, '\0', null, null, '42', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('480', 'enabled', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '\0', '19', 'enabled', 'boolean', null, 'property', '2', null, '\0', null, null, '42', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('481', 'name', null, null, null, null, null, null, null, null, null, null, null, '40', null, null, '\0', '19', 'name', 'string', null, 'property', '2', null, '\0', null, null, '42', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('482', 'resource_id', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'rbacResource', 'RbacResource', null, 'ManyToOne', '2', null, '\0', null, null, '42', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('483', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'rbacRoles', 'Set', null, 'ManyToMany', null, null, null, null, null, '42', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('484', 'type', null, null, null, null, null, null, null, null, null, null, null, '1', null, null, '\0', '19', 'type', 'string', null, 'property', '2', null, '\0', null, null, '42', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('485', 'url', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '\0', '19', 'url', 'string', null, 'property', '2', null, '\0', null, null, '42', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('486', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'childRbacMenus', 'Set', null, 'OneToMany', null, null, null, null, null, '43', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('487', 'description', null, null, null, null, null, null, null, null, null, null, null, '60', null, null, '', '19', 'description', 'string', null, 'property', '2', null, '\0', null, null, '43', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('488', 'dis_index', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'disIndex', 'integer', null, 'property', '2', null, '\0', null, null, '43', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('489', 'display', null, null, null, null, null, null, null, null, null, null, null, '40', null, null, '\0', '19', 'display', 'string', null, 'property', '2', null, '\0', null, null, '43', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('490', 'iconCls', null, null, null, null, null, null, null, null, null, null, null, '40', null, null, '\0', '19', 'iconCls', 'string', null, 'property', '2', null, '\0', null, null, '43', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('491', 'parent_id', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'parentRbacMenu', 'RbacMenu', null, 'ManyToOne', '2', null, '\0', null, null, '43', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('492', 'module_id', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'rbacModule', 'RbacModule', null, 'ManyToOne', '2', null, '\0', null, null, '43', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('493', 'permission_id', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'rbacPermission', 'RbacPermission', null, 'ManyToOne', '2', null, '\0', null, null, '43', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('494', 'tab_title', null, null, null, null, null, null, null, null, null, null, null, '40', null, null, '', '19', 'tabTitle', 'string', null, 'property', '2', null, '\0', null, null, '43', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('495', 'type', null, null, null, null, null, null, null, null, null, null, null, '1', null, null, '', '19', 'type', 'string', null, 'property', '2', null, '\0', null, null, '43', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('496', 'rbac_group_id', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'rbacGroup', 'RbacGroup', null, 'ManyToOne', '2', null, '\0', null, null, '44', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('497', 'rbac_user_id', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'rbacUser', 'RbacUser', null, 'ManyToOne', '2', null, '\0', null, null, '44', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('498', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'SysDepartRoles', 'Set', null, 'OneToMany', null, null, null, null, null, '45', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('499', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'childSysDepartments', 'Set', null, 'OneToMany', null, null, null, null, null, '45', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('500', 'create_time', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'createTime', 'timestamp', null, 'property', '2', null, '\0', null, null, '45', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('501', 'creater', null, null, null, null, null, null, null, null, null, null, null, '30', null, null, '', '19', 'creater', 'string', null, 'property', '2', null, '\0', null, null, '45', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('502', 'description', null, null, null, null, null, null, null, null, null, null, null, '60', null, null, '', '19', 'description', 'string', null, 'property', '2', null, '\0', null, null, '45', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('503', 'enabled', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '\0', '19', 'enabled', 'boolean', null, 'property', '2', null, '\0', null, null, '45', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('504', 'name', null, null, null, null, null, null, null, null, null, null, null, '30', null, null, '\0', '19', 'name', 'string', null, 'property', '2', null, '', null, null, '45', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('505', 'parent_id', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'parentSysDepartment', 'SysDepartment', null, 'property', '2', null, '\0', null, null, '45', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('506', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'sysDepartUsers', 'Set', null, 'OneToMany', null, null, null, null, null, '45', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('507', 'rbac_menu_id', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'rbacMenu', 'RbacMenu', null, 'ManyToOne', '2', null, '\0', null, null, '46', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('508', 'rbac_module_id', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'rbacModule', 'RbacModule', null, 'ManyToOne', '2', null, '\0', null, null, '46', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('509', 'rbac_rbacPermission_id', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'rbacPermission', 'RbacPermission', null, 'ManyToOne', '2', null, '\0', null, null, '47', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('510', 'rbac_user_id', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'rbacUser', 'RbacUser', null, 'ManyToOne', '2', null, '\0', null, null, '47', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('511', 'userName', null, null, null, null, null, null, null, null, null, null, null, '30', null, null, '\0', '19', 'userName', 'string', null, 'property', '2', null, '', null, null, '35', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('512', 'rbac_rbacPermission_id', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'rbacPermission', 'RbacPermission', null, 'ManyToOne', '2', null, '\0', null, null, '48', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('513', 'rbac_user_id', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'rbacUser', 'RbacUser', null, 'ManyToOne', '2', null, '\0', null, null, '48', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('514', 'permission_id', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'rbacPermission', 'RbacPermission', null, 'ManyToOne', '2', null, '\0', null, null, '49', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('515', 'role_id', null, null, null, null, null, null, null, null, null, null, null, '255', null, null, '', '19', 'rbacRole', 'RbacRole', null, 'ManyToOne', '2', null, '\0', null, null, '49', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('516', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'rbacRolePermissions', 'Set', null, 'OneToMany', null, null, null, null, null, '37', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('517', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'rbacRolePermissions', 'Set', null, 'OneToMany', null, null, null, null, null, '42', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('518', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'rbacUserPermissions', 'Set', null, 'OneToMany', null, null, null, null, null, '42', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for zht_gen_entity
-- ----------------------------
DROP TABLE IF EXISTS `zht_gen_entity`;
CREATE TABLE `zht_gen_entity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `controllerNameSpace` varchar(60) DEFAULT NULL,
  `gen_info` varchar(60) DEFAULT NULL,
  `isTree` bit(1) DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL,
  `module_name` varchar(60) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `remark` varchar(60) DEFAULT NULL,
  `version` varchar(10) DEFAULT NULL,
  `entityDisName` varchar(60) DEFAULT NULL,
  `tableName` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zht_gen_entity
-- ----------------------------
INSERT INTO `zht_gen_entity` VALUES ('1', '2015-02-03 09:24:22', null, null, null, '2015-02-03 09:24:22', null, 'com.zht.common.rabc.easyui.model.RbacModule', null, 'v0.0', null, null);
INSERT INTO `zht_gen_entity` VALUES ('2', '2015-02-03 09:24:22', null, null, null, '2015-02-03 09:24:22', null, 'com.zht.common.demo.model.DemoUser', null, 'v0.0', null, null);
INSERT INTO `zht_gen_entity` VALUES ('3', '2015-02-03 09:24:22', null, null, null, '2015-02-03 09:24:22', null, 'com.zht.common.demo.model.DemoUser2', null, 'v0.0', null, null);
INSERT INTO `zht_gen_entity` VALUES ('4', '2015-02-03 09:24:22', null, null, null, '2015-02-03 09:24:22', null, 'com.zht.common.rabc.easyui.model.RbacMenu', null, 'v0.0', null, null);
INSERT INTO `zht_gen_entity` VALUES ('5', '2015-02-03 09:24:22', null, null, null, '2015-02-03 09:24:22', null, 'com.zht.common.rabc.easyui.model.RbacResource', null, 'v0.0', null, null);
INSERT INTO `zht_gen_entity` VALUES ('6', '2015-02-03 09:24:22', '/common/rbac/sysDepartment', '', '', '2015-02-03 09:24:22', null, 'com.zht.common.rabc.easyui.model.SysDepartment', '', 'v0.0', '部门', null);
INSERT INTO `zht_gen_entity` VALUES ('7', '2015-02-03 09:24:22', null, null, null, '2015-02-03 09:24:22', null, 'com.zht.common.rabc.easyui.model.RbacRole', null, 'v0.0', null, null);
INSERT INTO `zht_gen_entity` VALUES ('8', '2015-02-03 09:24:22', null, null, null, '2015-02-03 09:24:22', null, 'com.zht.common.rabc.easyui.model.RbacPermission', null, 'v0.0', null, null);
INSERT INTO `zht_gen_entity` VALUES ('9', '2015-02-03 09:24:22', null, null, null, '2015-02-03 09:24:22', null, 'com.zht.common.rabc.easyui.model.SysDepartmentRole', null, 'v0.0', null, null);
INSERT INTO `zht_gen_entity` VALUES ('10', '2015-02-03 09:24:22', null, null, null, '2015-02-03 09:24:22', null, 'com.zht.common.rabc.easyui.model.RbacGroup', null, 'v0.0', null, null);
INSERT INTO `zht_gen_entity` VALUES ('11', '2015-02-03 09:24:22', null, null, null, '2015-02-03 09:24:22', null, 'com.zht.common.rabc.easyui.model.RbacGroupRole', null, 'v0.0', null, null);
INSERT INTO `zht_gen_entity` VALUES ('12', '2015-02-03 09:24:22', null, null, null, '2015-02-03 09:24:22', null, 'com.zht.common.rabc.easyui.model.RbacUserSession', null, 'v0.0', null, null);
INSERT INTO `zht_gen_entity` VALUES ('13', '2015-02-03 09:24:22', null, null, null, '2015-02-03 09:24:22', null, 'com.zht.common.rabc.easyui.model.RbacUserRoleReject', null, 'v0.0', null, null);
INSERT INTO `zht_gen_entity` VALUES ('14', '2015-02-03 09:24:22', null, null, null, '2015-02-03 09:24:22', null, 'com.zht.project.user.model.User', null, 'v0.0', null, null);
INSERT INTO `zht_gen_entity` VALUES ('15', '2015-02-03 09:24:22', null, null, null, '2015-02-03 09:24:22', null, 'com.zht.common.generator.model.GenEntityDetail', null, 'v0.0', null, null);
INSERT INTO `zht_gen_entity` VALUES ('16', '2015-02-03 09:24:22', null, null, null, '2015-02-03 09:24:22', null, 'com.zht.common.rabc.easyui.model.RbacUser', null, 'v0.0', null, null);
INSERT INTO `zht_gen_entity` VALUES ('17', '2015-02-03 09:24:22', null, null, null, '2015-02-03 09:24:22', null, 'com.zht.common.rabc.easyui.model.RbacGroupUser', null, 'v0.0', null, null);
INSERT INTO `zht_gen_entity` VALUES ('18', '2015-02-03 09:24:22', null, null, null, '2015-02-03 09:24:22', null, 'com.zht.common.rabc.easyui.model.InfoPersoninfo', null, 'v0.0', null, null);
INSERT INTO `zht_gen_entity` VALUES ('19', '2015-02-03 09:24:22', null, null, null, '2015-02-03 09:24:22', null, 'com.zht.common.rabc.easyui.model.RbacUserRole', null, 'v0.0', null, null);
INSERT INTO `zht_gen_entity` VALUES ('20', '2015-02-03 09:24:22', null, null, null, '2015-02-03 09:24:22', null, 'com.zht.common.rabc.easyui.model.SysDepartmentUser', null, 'v0.0', null, null);
INSERT INTO `zht_gen_entity` VALUES ('21', '2015-02-03 09:24:22', null, null, null, '2015-02-03 09:24:22', null, 'com.zht.project.demo.model.GenDemo', null, 'v0.0', null, null);
INSERT INTO `zht_gen_entity` VALUES ('22', '2015-02-03 09:24:22', null, null, null, '2015-02-03 09:24:22', null, 'com.zht.common.generator.model.GenEntity', null, 'v0.0', null, null);
INSERT INTO `zht_gen_entity` VALUES ('24', '2015-02-06 09:42:24', '/common/test/model/User', null, '\0', null, null, 'com.zht.common.test.model.User', '测试用户', null, '测试用户', 'test_user');
INSERT INTO `zht_gen_entity` VALUES ('25', '2015-02-06 11:07:21', null, null, null, '2015-02-06 11:07:21', null, 'com.zht.common.generator.model.GenEntityProperty', null, 'v0.0', null, null);
INSERT INTO `zht_gen_entity` VALUES ('26', null, 'common/test/demoentity', '', '\0', '2015-03-03 14:07:48', null, 'com.zht.project.test.model.DemoEntity', ' 测试实体', '', ' 测试实体', 'z_demo_entiry');
INSERT INTO `zht_gen_entity` VALUES ('27', null, 'common/test/demoeTree', null, '', '2015-03-04 15:20:44', null, 'com.zht.projeect.test.model.TreeDemoEntiry', '', null, '树形DEMO', 'z_tree_demo_entiry');
INSERT INTO `zht_gen_entity` VALUES ('28', null, 'common/test/maytomany', null, '\0', '2015-03-05 09:47:59', null, 'com.zht.project.model.MayToMayMaster', '', null, '多对多主控A', 'MayToMayMaster');
INSERT INTO `zht_gen_entity` VALUES ('29', null, 'common/test/maytomay', null, '\0', '2015-03-05 09:49:14', null, 'com.zht.project.model.MayToMaySlaver', '', null, '多对多从B', 'MayToMaySlaver');
INSERT INTO `zht_gen_entity` VALUES ('30', '2015-03-17 09:47:50', null, null, null, '2015-03-17 09:47:50', null, 'com.zht.common.rabc.model.InfoPersoninfo', null, 'v0.0', null, null);
INSERT INTO `zht_gen_entity` VALUES ('31', '2015-03-17 09:47:50', null, null, null, '2015-03-17 09:47:50', null, 'com.zht.common.rabc.model.RbacResource', null, 'v0.0', null, null);
INSERT INTO `zht_gen_entity` VALUES ('32', '2015-03-17 09:47:50', null, null, null, '2015-03-17 09:47:50', null, 'com.zht.common.rabc.model.RbacUserRoleReject', null, 'v0.0', null, null);
INSERT INTO `zht_gen_entity` VALUES ('33', '2015-03-17 09:47:50', null, null, null, '2015-03-17 09:47:50', null, 'com.zht.common.rabc.model.SysDepartmentRole', null, 'v0.0', null, null);
INSERT INTO `zht_gen_entity` VALUES ('34', '2015-03-17 09:47:50', null, null, null, '2015-03-17 09:47:50', null, 'com.zht.common.rabc.model.RbacModule', null, 'v0.0', null, null);
INSERT INTO `zht_gen_entity` VALUES ('35', '2015-03-17 09:47:50', null, null, null, '2015-03-17 09:47:50', null, 'com.zht.common.rabc.model.RbacUser', null, 'v0.0', null, null);
INSERT INTO `zht_gen_entity` VALUES ('36', '2015-03-17 09:47:50', null, null, null, '2015-03-17 09:47:50', null, 'com.zht.common.rabc.model.RbacUserRole', null, 'v0.0', null, null);
INSERT INTO `zht_gen_entity` VALUES ('37', '2015-03-17 09:47:50', null, null, null, '2015-03-17 09:47:50', null, 'com.zht.common.rabc.model.RbacRole', null, 'v0.0', null, null);
INSERT INTO `zht_gen_entity` VALUES ('38', '2015-03-17 09:47:50', null, null, null, '2015-03-17 09:47:50', null, 'com.zht.common.rabc.model.RbacUserSession', null, 'v0.0', null, null);
INSERT INTO `zht_gen_entity` VALUES ('39', '2015-03-17 09:47:50', null, null, null, '2015-03-17 09:47:50', null, 'com.zht.common.rabc.model.SysDepartmentUser', null, 'v0.0', null, null);
INSERT INTO `zht_gen_entity` VALUES ('40', '2015-03-17 09:47:50', null, null, null, '2015-03-17 09:47:50', null, 'com.zht.common.rabc.model.RbacGroup', null, 'v0.0', null, null);
INSERT INTO `zht_gen_entity` VALUES ('41', '2015-03-17 09:47:50', null, null, null, '2015-03-17 09:47:50', null, 'com.zht.common.rabc.model.RbacGroupRole', null, 'v0.0', null, null);
INSERT INTO `zht_gen_entity` VALUES ('42', '2015-03-17 09:47:50', null, null, null, '2015-03-17 09:47:50', null, 'com.zht.common.rabc.model.RbacPermission', null, 'v0.0', null, null);
INSERT INTO `zht_gen_entity` VALUES ('43', '2015-03-17 09:47:50', null, null, null, '2015-03-17 09:47:50', null, 'com.zht.common.rabc.model.RbacMenu', null, 'v0.0', null, null);
INSERT INTO `zht_gen_entity` VALUES ('44', '2015-03-17 09:47:50', null, null, null, '2015-03-17 09:47:50', null, 'com.zht.common.rabc.model.RbacGroupUser', null, 'v0.0', null, null);
INSERT INTO `zht_gen_entity` VALUES ('45', '2015-03-17 09:47:50', null, null, null, '2015-03-17 09:47:50', null, 'com.zht.common.rabc.model.SysDepartment', null, 'v0.0', null, null);
INSERT INTO `zht_gen_entity` VALUES ('46', '2015-03-18 09:32:34', null, null, null, '2015-03-18 09:32:34', null, 'com.zht.common.rabc.model.RbacModuleMenu', null, 'v0.0', null, null);
INSERT INTO `zht_gen_entity` VALUES ('47', '2015-04-01 11:26:24', null, null, null, '2015-04-01 11:26:24', null, 'com.zht.common.rabc.model.RbacUserPermission', null, 'v0.0', null, null);
INSERT INTO `zht_gen_entity` VALUES ('48', '2015-04-01 11:26:24', null, null, null, '2015-04-01 11:26:24', null, 'com.zht.common.rabc.model.RbacUserPermissionReject', null, 'v0.0', null, null);
INSERT INTO `zht_gen_entity` VALUES ('49', '2015-04-01 15:35:24', null, null, null, '2015-04-01 15:35:24', null, 'com.zht.common.rabc.model.RbacRolePermission', null, 'v0.0', null, null);
