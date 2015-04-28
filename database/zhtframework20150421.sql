/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50509
Source Host           : localhost:3306
Source Database       : zhtframework

Target Server Type    : MYSQL
Target Server Version : 50509
File Encoding         : 65001

Date: 2015-04-21 18:48:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for rbac_dataaccess_exp
-- ----------------------------
DROP TABLE IF EXISTS `rbac_dataaccess_exp`;
CREATE TABLE `rbac_dataaccess_exp` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `authExp` varchar(100) NOT NULL,
  `calledTime` int(11) NOT NULL,
  `columnName` varchar(50) DEFAULT NULL,
  `daoAcessMethed` varchar(30) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `fieldName` varchar(50) DEFAULT NULL,
  `rbac_dataPrivilege_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_96ugm6kkr4dtw3j0tw0jjytdt` (`rbac_dataPrivilege_id`),
  CONSTRAINT `FK_96ugm6kkr4dtw3j0tw0jjytdt` FOREIGN KEY (`rbac_dataPrivilege_id`) REFERENCES `rbac_dataprivilege` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rbac_dataaccess_exp
-- ----------------------------
INSERT INTO `rbac_dataaccess_exp` VALUES ('1', '{id}{#f# in (select id from DepartmentUser du where du.department=#v#)}{@did}', '1', null, 'loadDataSetFromOneEntity', '', null, '1');

-- ----------------------------
-- Table structure for rbac_dataprivilege
-- ----------------------------
DROP TABLE IF EXISTS `rbac_dataprivilege`;
CREATE TABLE `rbac_dataprivilege` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(60) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `name` varchar(30) NOT NULL,
  `rbac_permission_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sisnvd0ndo5n4nb0nijygogsa` (`code`),
  KEY `FK_9myw7mqygsmxp3n0yu8nnak2f` (`rbac_permission_id`),
  CONSTRAINT `FK_9myw7mqygsmxp3n0yu8nnak2f` FOREIGN KEY (`rbac_permission_id`) REFERENCES `rbac_permission` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rbac_dataprivilege
-- ----------------------------
INSERT INTO `rbac_dataprivilege` VALUES ('1', 'asdas', '', 'sadad', '59');

-- ----------------------------
-- Table structure for rbac_dataprivilege_role
-- ----------------------------
DROP TABLE IF EXISTS `rbac_dataprivilege_role`;
CREATE TABLE `rbac_dataprivilege_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rbac_dataPrivilege_id` bigint(20) DEFAULT NULL,
  `rbac_role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_36q91riwpr5bp7bae8glea5jd` (`rbac_dataPrivilege_id`),
  KEY `FK_r1cihlcp00vuo6mw5u3481a5i` (`rbac_role_id`),
  CONSTRAINT `FK_r1cihlcp00vuo6mw5u3481a5i` FOREIGN KEY (`rbac_role_id`) REFERENCES `rbac_role` (`id`),
  CONSTRAINT `FK_36q91riwpr5bp7bae8glea5jd` FOREIGN KEY (`rbac_dataPrivilege_id`) REFERENCES `rbac_dataprivilege` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rbac_dataprivilege_role
-- ----------------------------
INSERT INTO `rbac_dataprivilege_role` VALUES ('1', '1', '1');

-- ----------------------------
-- Table structure for rbac_dataprivilege_user_reject
-- ----------------------------
DROP TABLE IF EXISTS `rbac_dataprivilege_user_reject`;
CREATE TABLE `rbac_dataprivilege_user_reject` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rbac_dataPrivilege_id` bigint(20) DEFAULT NULL,
  `rbac_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_k4avcj9l7e10vt51hd022tk3v` (`rbac_dataPrivilege_id`),
  KEY `FK_n22l7mqe2l9coaq9di8g112v9` (`rbac_user_id`),
  CONSTRAINT `FK_n22l7mqe2l9coaq9di8g112v9` FOREIGN KEY (`rbac_user_id`) REFERENCES `rbac_user` (`id`),
  CONSTRAINT `FK_k4avcj9l7e10vt51hd022tk3v` FOREIGN KEY (`rbac_dataPrivilege_id`) REFERENCES `rbac_dataprivilege` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rbac_dataprivilege_user_reject
-- ----------------------------

-- ----------------------------
-- Table structure for rbac_group
-- ----------------------------
DROP TABLE IF EXISTS `rbac_group`;
CREATE TABLE `rbac_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `creater` varchar(30) DEFAULT NULL,
  `description` varchar(60) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `name` varchar(30) NOT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r3vsun5cwxpoulikrecy6n08x` (`name`),
  KEY `FK_n6ua1pidq1wl6rnvh4g66y43t` (`parent_id`),
  CONSTRAINT `FK_n6ua1pidq1wl6rnvh4g66y43t` FOREIGN KEY (`parent_id`) REFERENCES `rbac_group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rbac_group
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rbac_menu
-- ----------------------------
INSERT INTO `rbac_menu` VALUES ('1', '权限管理', '1', '权限管理', 'icon-add', '', 'G', null, null, null);
INSERT INTO `rbac_menu` VALUES ('2', '许可维护', '2', '许可维护', '', '许可维护', 'M', '1', null, '29');
INSERT INTO `rbac_menu` VALUES ('3', '菜单维护', '1', '菜单维护', '', '菜单维护', 'M', '1', null, '18');
INSERT INTO `rbac_menu` VALUES ('4', '角色维护', '3', '角色维护', '', '角色维护', 'M', '1', null, '35');
INSERT INTO `rbac_menu` VALUES ('5', '用户维护', '4', '用户维护', '', '用户维护', 'M', '1', null, '54');
INSERT INTO `rbac_menu` VALUES ('6', '用户组维护', '5', '用户组维护', '', '用户组维护', 'M', '1', null, '9');
INSERT INTO `rbac_menu` VALUES ('7', '部门维护', '6', '部门维护', '', '部门维护', 'M', '1', null, '67');
INSERT INTO `rbac_menu` VALUES ('8', '代码生成', '7', '代码生成', '', '代码生成', 'M', '1', null, '82');
INSERT INTO `rbac_menu` VALUES ('10', '系统管理', '2', '系统管理', '', '系统管理', 'G', null, null, null);
INSERT INTO `rbac_menu` VALUES ('11', '职位维护', '5', '职位维护', '', '职位维护', 'M', '1', null, '83');

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
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_fy47um26fsxmpqb9t03sxkuc4` (`display`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rbac_module
-- ----------------------------

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
  UNIQUE KEY `UK_3v94kwepfosn7jqeo9hluid6f` (`code`),
  UNIQUE KEY `UK_hw5k6esl3e4ibi3t0g5xnsdst` (`url`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rbac_permission
-- ----------------------------
INSERT INTO `rbac_permission` VALUES ('1', 'RbacGroup:addGroup', '', '', 'SCAN', 'P', '/rbac/group/addGroup');
INSERT INTO `rbac_permission` VALUES ('2', 'RbacGroup:removeRolesFromGroupRole', null, '', 'SCAN', 'P', '/rbac/group/removeRolesFromGroupRole');
INSERT INTO `rbac_permission` VALUES ('3', 'RbacGroup:loadUserGridForUserAssign', null, '', 'SCAN', 'P', '/rbac/group/loadUserGridForUserAssign');
INSERT INTO `rbac_permission` VALUES ('4', 'RbacGroup:loadRoleGridForGroupRoleAssign', null, '', 'SCAN', 'P', '/rbac/group/loadRoleGridForGroupRoleAssign');
INSERT INTO `rbac_permission` VALUES ('5', 'RbacGroup:deleteGroup', null, '', 'SCAN', 'P', '/rbac/group/deleteGroup');
INSERT INTO `rbac_permission` VALUES ('6', 'RbacGroup:enterAddGroup', null, '', 'SCAN', 'P', '/rbac/group/enterAddGroup');
INSERT INTO `rbac_permission` VALUES ('7', 'RbacGroup:updateGroup', null, '', 'SCAN', 'P', '/rbac/group/updateGroup');
INSERT INTO `rbac_permission` VALUES ('8', 'RbacGroup:loadGroupCombotree', null, '', 'SCAN', 'P', '/rbac/group/loadGroupCombotree');
INSERT INTO `rbac_permission` VALUES ('9', 'RbacGroup:enterGroupPage', null, '', 'SCAN', 'P', '/rbac/group/enterGroupPage');
INSERT INTO `rbac_permission` VALUES ('10', 'RbacGroup:enterEidtGroup', null, '', 'SCAN', 'P', '/rbac/group/enterEidtGroup');
INSERT INTO `rbac_permission` VALUES ('11', 'RbacGroup:addUserToGroup', null, '', 'SCAN', 'P', '/rbac/group/addUserToGroup');
INSERT INTO `rbac_permission` VALUES ('12', 'RbacGroup:removeUserFromGroup', null, '', 'SCAN', 'P', '/rbac/group/removeUserFromGroup');
INSERT INTO `rbac_permission` VALUES ('13', 'RbacGroup:loadGroupTreeGrid', null, '', 'SCAN', 'P', '/rbac/group/loadGroupTreeGrid');
INSERT INTO `rbac_permission` VALUES ('14', 'RbacGroup:addRolesToGroupRole', null, '', 'SCAN', 'P', '/rbac/group/addRolesToGroupRole');
INSERT INTO `rbac_permission` VALUES ('15', 'RbacGroup:enterUserAssignment', null, '', 'SCAN', 'P', '/rbac/group/enterUserAssignment');
INSERT INTO `rbac_permission` VALUES ('16', 'RbacGroup:enterRoleAssignment', null, '', 'SCAN', 'P', '/rbac/group/enterRoleAssignment');
INSERT INTO `rbac_permission` VALUES ('17', 'RbacMenu:addMenu', null, '', 'SCAN', 'P', '/rbac/menu/addMenu');
INSERT INTO `rbac_permission` VALUES ('18', 'RbacMenu:enterMenuPage', null, '', 'SCAN', 'P', '/rbac/menu/enterMenuPage');
INSERT INTO `rbac_permission` VALUES ('19', 'RbacMenu:enterAddMenu', null, '', 'SCAN', 'P', '/rbac/menu/enterAddMenu');
INSERT INTO `rbac_permission` VALUES ('20', 'RbacMenu:enterEidtMenu', null, '', 'SCAN', 'P', '/rbac/menu/enterEidtMenu');
INSERT INTO `rbac_permission` VALUES ('21', 'RbacMenu:updateMenu', null, '', 'SCAN', 'P', '/rbac/menu/updateMenu');
INSERT INTO `rbac_permission` VALUES ('22', 'RbacMenu:deleteMenu', null, '', 'SCAN', 'P', '/rbac/menu/deleteMenu');
INSERT INTO `rbac_permission` VALUES ('23', 'RbacMenu:loadMenuCombotree', null, '', 'SCAN', 'P', '/rbac/menu/loadMenuCombotree');
INSERT INTO `rbac_permission` VALUES ('24', 'RbacMenu:loadMenuTreeGrid', null, '', 'SCAN', 'P', '/rbac/menu/loadMenuTreeGrid');
INSERT INTO `rbac_permission` VALUES ('25', 'RbacPermission:addPermission', null, '', 'SCAN', 'P', '/rbac/permission/addPermission');
INSERT INTO `rbac_permission` VALUES ('26', 'RbacPermission:loadPermissionGridView', null, '', 'SCAN', 'P', '/rbac/permission/loadPermissionGridView');
INSERT INTO `rbac_permission` VALUES ('27', 'RbacPermission:deletePermission', null, '', 'SCAN', 'P', '/rbac/permission/deletePermission');
INSERT INTO `rbac_permission` VALUES ('28', 'RbacPermission:enterUpdatePermission', null, '', 'SCAN', 'P', '/rbac/permission/enterUpdatePermission');
INSERT INTO `rbac_permission` VALUES ('29', 'RbacPermission:enterPermissionPage', null, '', 'SCAN', 'P', '/rbac/permission/enterPermissionPage');
INSERT INTO `rbac_permission` VALUES ('30', 'RbacPermission:updatePermission', null, '', 'SCAN', 'P', '/rbac/permission/updatePermission');
INSERT INTO `rbac_permission` VALUES ('31', 'RbacPermission:enterAddPermission', null, '', 'SCAN', 'P', '/rbac/permission/enterAddPermission');
INSERT INTO `rbac_permission` VALUES ('32', 'RbacRole:addRole', null, '', 'SCAN', 'P', '/rbac/role/addRole');
INSERT INTO `rbac_permission` VALUES ('33', 'RbacRole:loadPermissionForRoleAssign', null, '', 'SCAN', 'P', '/rbac/role/loadPermissionForRoleAssign');
INSERT INTO `rbac_permission` VALUES ('34', 'RbacRole:enterAddRole', null, '', 'SCAN', 'P', '/rbac/role/enterAddRole');
INSERT INTO `rbac_permission` VALUES ('35', 'RbacRole:enterRolePage', null, '', 'SCAN', 'P', '/rbac/role/enterRolePage');
INSERT INTO `rbac_permission` VALUES ('36', 'RbacRole:deleteRole', null, '', 'SCAN', 'P', '/rbac/role/deleteRole');
INSERT INTO `rbac_permission` VALUES ('37', 'RbacRole:updateRole', null, '', 'SCAN', 'P', '/rbac/role/updateRole');
INSERT INTO `rbac_permission` VALUES ('38', 'RbacRole:addPermissionsToRole', null, '', 'SCAN', 'P', '/rbac/role/addPermissionsToRole');
INSERT INTO `rbac_permission` VALUES ('39', 'RbacRole:loadRoleGridView', null, '', 'SCAN', 'P', '/rbac/role/loadRoleGridView');
INSERT INTO `rbac_permission` VALUES ('40', 'RbacRole:enterUpdateRole', null, '', 'SCAN', 'P', '/rbac/role/enterUpdateRole');
INSERT INTO `rbac_permission` VALUES ('41', 'RbacRole:removePermissionsFromRole', null, '', 'SCAN', 'P', '/rbac/role/removePermissionsFromRole');
INSERT INTO `rbac_permission` VALUES ('42', 'RbacRole:enterPermissionAssignment', null, '', 'SCAN', 'P', '/rbac/role/enterPermissionAssignment');
INSERT INTO `rbac_permission` VALUES ('43', 'RbacUser:addRolesToUserRoleReject', null, '', 'SCAN', 'P', '/rbac/user/addRolesToUserRoleReject');
INSERT INTO `rbac_permission` VALUES ('44', 'RbacUser:removeRolesFromUserRole', null, '', 'SCAN', 'P', '/rbac/user/removeRolesFromUserRole');
INSERT INTO `rbac_permission` VALUES ('45', 'RbacUser:removeRolesFromUserRoleReject', null, '', 'SCAN', 'P', '/rbac/user/removeRolesFromUserRoleReject');
INSERT INTO `rbac_permission` VALUES ('46', 'RbacUser:addPermissionsToUserPermissionReject', null, '', 'SCAN', 'P', '/rbac/user/addPermissionsToUserPermissionReject');
INSERT INTO `rbac_permission` VALUES ('47', 'RbacUser:addPermissionsToUserPermission', null, '', 'SCAN', 'P', '/rbac/user/addPermissionsToUserPermission');
INSERT INTO `rbac_permission` VALUES ('48', 'RbacUser:removePermissionsFromUserPermission', null, '', 'SCAN', 'P', '/rbac/user/removePermissionsFromUserPermission');
INSERT INTO `rbac_permission` VALUES ('49', 'RbacUser:removePermissionsFromUserPermissionReject', null, '', 'SCAN', 'P', '/rbac/user/removePermissionsFromUserPermissionReject');
INSERT INTO `rbac_permission` VALUES ('50', 'RbacUser:kickOutUser', null, '', 'SCAN', 'P', '/rbac/user/kickOutUser');
INSERT INTO `rbac_permission` VALUES ('51', 'RbacUser:updateUser', null, '', 'SCAN', 'P', '/rbac/user/updateUser');
INSERT INTO `rbac_permission` VALUES ('52', 'RbacUser:deleteUser', null, '', 'SCAN', 'P', '/rbac/user/deleteUser');
INSERT INTO `rbac_permission` VALUES ('53', 'RbacUser:enterAddUser', null, '', 'SCAN', 'P', '/rbac/user/enterAddUser');
INSERT INTO `rbac_permission` VALUES ('54', 'RbacUser:enterUserPage', null, '', 'SCAN', 'P', '/rbac/user/enterUserPage');
INSERT INTO `rbac_permission` VALUES ('55', 'RbacUser:addUser', null, '', 'SCAN', 'P', '/rbac/user/addUser');
INSERT INTO `rbac_permission` VALUES ('56', 'RbacUser:addRolesToUserRole', null, '', 'SCAN', 'P', '/rbac/user/addRolesToUserRole');
INSERT INTO `rbac_permission` VALUES ('57', 'RbacUser:enterRoleAssignment', null, '', 'SCAN', 'P', '/rbac/user/enterRoleAssignment');
INSERT INTO `rbac_permission` VALUES ('58', 'RbacUser:enterUpdateUser', null, '', 'SCAN', 'P', '/rbac/user/enterUpdateUser');
INSERT INTO `rbac_permission` VALUES ('59', 'RbacUser:loadUserGridView', null, '', 'SCAN', 'P', '/rbac/user/loadUserGridView');
INSERT INTO `rbac_permission` VALUES ('60', 'RbacUser:loadPermissionGridForUserPermissionAssign', null, '', 'SCAN', 'P', '/rbac/user/loadPermissionGridForUserPermissionAssign');
INSERT INTO `rbac_permission` VALUES ('61', 'RbacUser:loadRoleCodeUserHaveForCombox', null, '', 'SCAN', 'P', '/rbac/user/loadRoleCodeUserHaveForCombox');
INSERT INTO `rbac_permission` VALUES ('62', 'RbacUser:loadRoleGridForUserRoleAssign', null, '', 'SCAN', 'P', '/rbac/user/loadRoleGridForUserRoleAssign');
INSERT INTO `rbac_permission` VALUES ('63', 'RbacUser:enterUserPermissionAssignment', null, '', 'SCAN', 'P', '/rbac/user/enterUserPermissionAssignment');
INSERT INTO `rbac_permission` VALUES ('65', 'Department:simpleBatchDeleteDepartment', null, '', 'SCAN', 'P', '/common/sys/department/simpleBatchDeleteDepartment');
INSERT INTO `rbac_permission` VALUES ('66', 'Department:addDepartment', null, '', 'SCAN', 'P', '/common/sys/department/addDepartment');
INSERT INTO `rbac_permission` VALUES ('67', 'Department:enterDepartmentPage', null, '', 'SCAN', 'P', '/common/sys/department/enterDepartmentPage');
INSERT INTO `rbac_permission` VALUES ('68', 'Department:enterDepartmentDetail', null, '', 'SCAN', 'P', '/common/sys/department/enterDepartmentDetail');
INSERT INTO `rbac_permission` VALUES ('69', 'Department:enterAddDepartment', null, '', 'SCAN', 'P', '/common/sys/department/enterAddDepartment');
INSERT INTO `rbac_permission` VALUES ('70', 'Department:updateDepartment', null, '', 'SCAN', 'P', '/common/sys/department/updateDepartment');
INSERT INTO `rbac_permission` VALUES ('71', 'Department:enterUpdateDepartment', null, '', 'SCAN', 'P', '/common/sys/department/enterUpdateDepartment');
INSERT INTO `rbac_permission` VALUES ('72', 'Department:loadDepartmentGridView', null, '', 'SCAN', 'P', '/common/sys/department/loadDepartmentGridView');
INSERT INTO `rbac_permission` VALUES ('73', 'Department:simpleDeleteDepartment', null, '', 'SCAN', 'P', '/common/sys/department/simpleDeleteDepartment');
INSERT INTO `rbac_permission` VALUES ('74', 'Position:simpleBatchDeletePosition', null, '', 'SCAN', 'P', '/common/sys/position/simpleBatchDeletePosition');
INSERT INTO `rbac_permission` VALUES ('75', 'Position:addPosition', null, '', 'SCAN', 'P', '/common/sys/position/addPosition');
INSERT INTO `rbac_permission` VALUES ('76', 'Position:loadPositionGridView', null, '', 'SCAN', 'P', '/common/sys/position/loadPositionGridView');
INSERT INTO `rbac_permission` VALUES ('77', 'Position:enterUpdatePosition', null, '', 'SCAN', 'P', '/common/sys/position/enterUpdatePosition');
INSERT INTO `rbac_permission` VALUES ('78', 'Position:updatePosition', null, '', 'SCAN', 'P', '/common/sys/position/updatePosition');
INSERT INTO `rbac_permission` VALUES ('79', 'Position:simpleDeletePosition', null, '', 'SCAN', 'P', '/common/sys/position/simpleDeletePosition');
INSERT INTO `rbac_permission` VALUES ('80', 'Position:enterAddPosition', null, '', 'SCAN', 'P', '/common/sys/position/enterAddPosition');
INSERT INTO `rbac_permission` VALUES ('81', 'Position:enterPositionDetail', null, '', 'SCAN', 'P', '/common/sys/position/enterPositionDetail');
INSERT INTO `rbac_permission` VALUES ('82', 'GenEntity:enterGenEntityPage', null, '', 'SCAN', 'P', '/common/generator/genEntity/enterGenEntityPage');
INSERT INTO `rbac_permission` VALUES ('83', 'Position:enterPositionPage', null, '', 'SCAN', 'P', '/common/sys/position/enterPositionPage');
INSERT INTO `rbac_permission` VALUES ('84', 'Log:simpleBatchDeleteLog', null, '', 'SCAN', 'P', '/sys/common/log/simpleBatchDeleteLog');
INSERT INTO `rbac_permission` VALUES ('85', 'Log:addLog', null, '', 'SCAN', 'P', '/sys/common/log/addLog');
INSERT INTO `rbac_permission` VALUES ('86', 'Log:updateLog', null, '', 'SCAN', 'P', '/sys/common/log/updateLog');
INSERT INTO `rbac_permission` VALUES ('87', 'Log:enterAddLog', null, '', 'SCAN', 'P', '/sys/common/log/enterAddLog');
INSERT INTO `rbac_permission` VALUES ('88', 'Log:enterLogPage', null, '', 'SCAN', 'P', '/sys/common/log/enterLogPage');
INSERT INTO `rbac_permission` VALUES ('89', 'Log:simpleDeleteLog', null, '', 'SCAN', 'P', '/sys/common/log/simpleDeleteLog');
INSERT INTO `rbac_permission` VALUES ('90', 'Log:loadLogGridView', null, '', 'SCAN', 'P', '/sys/common/log/loadLogGridView');
INSERT INTO `rbac_permission` VALUES ('91', 'Log:enterLogDetail', null, '', 'SCAN', 'P', '/sys/common/log/enterLogDetail');
INSERT INTO `rbac_permission` VALUES ('92', 'Log:enterUpdateLog', null, '', 'SCAN', 'P', '/sys/common/log/enterUpdateLog');
INSERT INTO `rbac_permission` VALUES ('93', 'Department:loadDepartmentCombotree', null, '', 'SCAN', 'P', '/common/sys/department/loadDepartmentCombotree');

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rbac_role
-- ----------------------------
INSERT INTO `rbac_role` VALUES ('1', 'admin', 'admin', '', 'admin');
INSERT INTO `rbac_role` VALUES ('2', 'VVV', '', '', 'VVV');
INSERT INTO `rbac_role` VALUES ('3', 'VVVV', '', '', 'AAA');
INSERT INTO `rbac_role` VALUES ('4', 'VVVVV', '', '', 'AAA');

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
) ENGINE=InnoDB AUTO_INCREMENT=212 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rbac_role_permission
-- ----------------------------
INSERT INTO `rbac_role_permission` VALUES ('64', '66', '1');
INSERT INTO `rbac_role_permission` VALUES ('65', '69', '1');
INSERT INTO `rbac_role_permission` VALUES ('66', '68', '1');
INSERT INTO `rbac_role_permission` VALUES ('67', '67', '1');
INSERT INTO `rbac_role_permission` VALUES ('68', '71', '1');
INSERT INTO `rbac_role_permission` VALUES ('69', '72', '1');
INSERT INTO `rbac_role_permission` VALUES ('70', '65', '1');
INSERT INTO `rbac_role_permission` VALUES ('71', '73', '1');
INSERT INTO `rbac_role_permission` VALUES ('72', '70', '1');
INSERT INTO `rbac_role_permission` VALUES ('73', '75', '1');
INSERT INTO `rbac_role_permission` VALUES ('74', '80', '1');
INSERT INTO `rbac_role_permission` VALUES ('75', '81', '1');
INSERT INTO `rbac_role_permission` VALUES ('76', '77', '1');
INSERT INTO `rbac_role_permission` VALUES ('77', '76', '1');
INSERT INTO `rbac_role_permission` VALUES ('78', '74', '1');
INSERT INTO `rbac_role_permission` VALUES ('79', '79', '1');
INSERT INTO `rbac_role_permission` VALUES ('80', '78', '1');
INSERT INTO `rbac_role_permission` VALUES ('81', '1', '1');
INSERT INTO `rbac_role_permission` VALUES ('82', '14', '1');
INSERT INTO `rbac_role_permission` VALUES ('83', '11', '1');
INSERT INTO `rbac_role_permission` VALUES ('84', '5', '1');
INSERT INTO `rbac_role_permission` VALUES ('85', '6', '1');
INSERT INTO `rbac_role_permission` VALUES ('86', '10', '1');
INSERT INTO `rbac_role_permission` VALUES ('87', '9', '1');
INSERT INTO `rbac_role_permission` VALUES ('88', '16', '1');
INSERT INTO `rbac_role_permission` VALUES ('89', '15', '1');
INSERT INTO `rbac_role_permission` VALUES ('90', '8', '1');
INSERT INTO `rbac_role_permission` VALUES ('91', '13', '1');
INSERT INTO `rbac_role_permission` VALUES ('92', '4', '1');
INSERT INTO `rbac_role_permission` VALUES ('93', '3', '1');
INSERT INTO `rbac_role_permission` VALUES ('94', '2', '1');
INSERT INTO `rbac_role_permission` VALUES ('95', '12', '1');
INSERT INTO `rbac_role_permission` VALUES ('96', '7', '1');
INSERT INTO `rbac_role_permission` VALUES ('97', '17', '1');
INSERT INTO `rbac_role_permission` VALUES ('98', '22', '1');
INSERT INTO `rbac_role_permission` VALUES ('99', '19', '1');
INSERT INTO `rbac_role_permission` VALUES ('100', '20', '1');
INSERT INTO `rbac_role_permission` VALUES ('101', '18', '1');
INSERT INTO `rbac_role_permission` VALUES ('102', '23', '1');
INSERT INTO `rbac_role_permission` VALUES ('103', '24', '1');
INSERT INTO `rbac_role_permission` VALUES ('104', '21', '1');
INSERT INTO `rbac_role_permission` VALUES ('105', '25', '1');
INSERT INTO `rbac_role_permission` VALUES ('106', '27', '1');
INSERT INTO `rbac_role_permission` VALUES ('107', '31', '1');
INSERT INTO `rbac_role_permission` VALUES ('108', '29', '1');
INSERT INTO `rbac_role_permission` VALUES ('109', '28', '1');
INSERT INTO `rbac_role_permission` VALUES ('110', '26', '1');
INSERT INTO `rbac_role_permission` VALUES ('111', '30', '1');
INSERT INTO `rbac_role_permission` VALUES ('112', '38', '1');
INSERT INTO `rbac_role_permission` VALUES ('113', '32', '1');
INSERT INTO `rbac_role_permission` VALUES ('114', '36', '1');
INSERT INTO `rbac_role_permission` VALUES ('115', '34', '1');
INSERT INTO `rbac_role_permission` VALUES ('116', '42', '1');
INSERT INTO `rbac_role_permission` VALUES ('117', '35', '1');
INSERT INTO `rbac_role_permission` VALUES ('118', '40', '1');
INSERT INTO `rbac_role_permission` VALUES ('119', '33', '1');
INSERT INTO `rbac_role_permission` VALUES ('120', '39', '1');
INSERT INTO `rbac_role_permission` VALUES ('121', '41', '1');
INSERT INTO `rbac_role_permission` VALUES ('122', '37', '1');
INSERT INTO `rbac_role_permission` VALUES ('123', '47', '1');
INSERT INTO `rbac_role_permission` VALUES ('124', '46', '1');
INSERT INTO `rbac_role_permission` VALUES ('125', '56', '1');
INSERT INTO `rbac_role_permission` VALUES ('126', '43', '1');
INSERT INTO `rbac_role_permission` VALUES ('127', '55', '1');
INSERT INTO `rbac_role_permission` VALUES ('128', '52', '1');
INSERT INTO `rbac_role_permission` VALUES ('129', '53', '1');
INSERT INTO `rbac_role_permission` VALUES ('130', '57', '1');
INSERT INTO `rbac_role_permission` VALUES ('131', '58', '1');
INSERT INTO `rbac_role_permission` VALUES ('132', '54', '1');
INSERT INTO `rbac_role_permission` VALUES ('133', '63', '1');
INSERT INTO `rbac_role_permission` VALUES ('134', '50', '1');
INSERT INTO `rbac_role_permission` VALUES ('135', '60', '1');
INSERT INTO `rbac_role_permission` VALUES ('136', '61', '1');
INSERT INTO `rbac_role_permission` VALUES ('137', '62', '1');
INSERT INTO `rbac_role_permission` VALUES ('138', '59', '1');
INSERT INTO `rbac_role_permission` VALUES ('139', '48', '1');
INSERT INTO `rbac_role_permission` VALUES ('140', '49', '1');
INSERT INTO `rbac_role_permission` VALUES ('141', '44', '1');
INSERT INTO `rbac_role_permission` VALUES ('142', '45', '1');
INSERT INTO `rbac_role_permission` VALUES ('143', '51', '1');
INSERT INTO `rbac_role_permission` VALUES ('191', '82', '1');
INSERT INTO `rbac_role_permission` VALUES ('192', '83', '1');
INSERT INTO `rbac_role_permission` VALUES ('202', '84', '1');
INSERT INTO `rbac_role_permission` VALUES ('203', '85', '1');
INSERT INTO `rbac_role_permission` VALUES ('204', '86', '1');
INSERT INTO `rbac_role_permission` VALUES ('205', '87', '1');
INSERT INTO `rbac_role_permission` VALUES ('206', '88', '1');
INSERT INTO `rbac_role_permission` VALUES ('207', '89', '1');
INSERT INTO `rbac_role_permission` VALUES ('208', '90', '1');
INSERT INTO `rbac_role_permission` VALUES ('209', '91', '1');
INSERT INTO `rbac_role_permission` VALUES ('210', '92', '1');
INSERT INTO `rbac_role_permission` VALUES ('211', '93', '1');

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
  `userName` varchar(30) NOT NULL,
  `defaultPosition_id` bigint(20) DEFAULT NULL,
  `defaultRole_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_by5js5d3ceusq6w4xth38hck2` (`userName`),
  KEY `FK_6f2a29s01b2t90xfgh17fbc7x` (`defaultPosition_id`),
  KEY `FK_lcwxh82k4x0l9yymkb5yuiu17` (`defaultRole_id`),
  CONSTRAINT `FK_6f2a29s01b2t90xfgh17fbc7x` FOREIGN KEY (`defaultPosition_id`) REFERENCES `sys_position` (`id`),
  CONSTRAINT `FK_lcwxh82k4x0l9yymkb5yuiu17` FOREIGN KEY (`defaultRole_id`) REFERENCES `rbac_role` (`id`),
  CONSTRAINT `FK_n27w7ng0foliyt2hmjnudh14c` FOREIGN KEY (`id`) REFERENCES `sys_user_detail` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rbac_user
-- ----------------------------
INSERT INTO `rbac_user` VALUES ('1', '\0', '\0', null, '\0', null, '', '362f149d489fa64f8471d53f247f3511', '4789281971717EuocUgBvyTN7frWJyhHcgs', 'admin', null, '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rbac_user_role
-- ----------------------------
INSERT INTO `rbac_user_role` VALUES ('1', '1', '1');

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
  `creaater` varchar(40) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `name` varchar(40) NOT NULL,
  `remark` varchar(60) DEFAULT NULL,
  `parent_department_id` bigint(20) DEFAULT NULL,
  `dfs` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_f9yurb4qdef0c4dsq87t4xayt` (`parent_department_id`),
  CONSTRAINT `FK_f9yurb4qdef0c4dsq87t4xayt` FOREIGN KEY (`parent_department_id`) REFERENCES `sys_department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_department
-- ----------------------------
INSERT INTO `sys_department` VALUES ('1', 'ROOT', '2015-04-16 13:20:22', 'ROOT', 'ROOT', null, null);
INSERT INTO `sys_department` VALUES ('2', 'asdsa', '2015-04-17 09:43:33', 'fgfhg', 'sdads', '3', null);
INSERT INTO `sys_department` VALUES ('3', 'asdsad', '2015-04-16 19:36:36', 'sdasdsa', 'sadasd', '1', null);
INSERT INTO `sys_department` VALUES ('4', '', '2015-04-17 09:44:29', 'AAA', 'dfdsf', '3', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_department_user
-- ----------------------------
INSERT INTO `sys_department_user` VALUES ('1', '1', '1');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(30) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `deptment_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_i9aslsj0i2dvjqvgoiihvkjkk` (`code`),
  KEY `FK_esbqamfas9wnhpbpbbhc7jbyn` (`deptment_id`),
  CONSTRAINT `FK_esbqamfas9wnhpbpbbhc7jbyn` FOREIGN KEY (`deptment_id`) REFERENCES `sys_department` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_position
-- ----------------------------
DROP TABLE IF EXISTS `sys_position`;
CREATE TABLE `sys_position` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `creater` varchar(40) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `name` varchar(40) DEFAULT NULL,
  `remark` varchar(60) DEFAULT NULL,
  `depatment_id` bigint(20) DEFAULT NULL,
  `rbac_role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_8lg6kb1c23crnx3jcg8pbjpuu` (`depatment_id`),
  KEY `FK_46pqaq6q9bpsyujkwn4qruegq` (`rbac_role_id`),
  CONSTRAINT `FK_46pqaq6q9bpsyujkwn4qruegq` FOREIGN KEY (`rbac_role_id`) REFERENCES `rbac_role` (`id`),
  CONSTRAINT `FK_8lg6kb1c23crnx3jcg8pbjpuu` FOREIGN KEY (`depatment_id`) REFERENCES `sys_department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_position
-- ----------------------------
INSERT INTO `sys_position` VALUES ('1', 'ssd', '2015-04-17 11:06:17', 'ssd', 'ssd', '1', '1');

-- ----------------------------
-- Table structure for sys_position_rbac_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_position_rbac_user`;
CREATE TABLE `sys_position_rbac_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `position_id` bigint(20) DEFAULT NULL,
  `rbac_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cnta8jd3wka3lw7ujd1v16417` (`position_id`),
  KEY `FK_2956leeukt97587x0hh0d98gj` (`rbac_user_id`),
  CONSTRAINT `FK_2956leeukt97587x0hh0d98gj` FOREIGN KEY (`rbac_user_id`) REFERENCES `rbac_user` (`id`),
  CONSTRAINT `FK_cnta8jd3wka3lw7ujd1v16417` FOREIGN KEY (`position_id`) REFERENCES `sys_position` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_position_rbac_user
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_detail
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_detail`;
CREATE TABLE `sys_user_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `birth` date DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  `per_id_num` varchar(18) DEFAULT NULL,
  `phone` varchar(30) DEFAULT NULL,
  `qq_num` varchar(20) DEFAULT NULL,
  `sex` smallint(6) DEFAULT NULL,
  `userName` varchar(40) NOT NULL,
  `user_num` varchar(40) NOT NULL,
  `weixin_Num` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_eff2uytmdnhuw05nkhhh21ca1` (`user_num`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_detail
-- ----------------------------
INSERT INTO `sys_user_detail` VALUES ('1', null, null, null, null, null, null, 'admin', 'admin', null);

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
  `oneToOneMappedBy` varchar(255) DEFAULT NULL,
  `oneToOneDisplayField` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_bce2jtqan06c72s1bh1gco97b` (`genEntity_id`),
  CONSTRAINT `FK_bce2jtqan06c72s1bh1gco97b` FOREIGN KEY (`genEntity_id`) REFERENCES `zht_gen_entity` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=585 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zht_entity_property
-- ----------------------------
INSERT INTO `zht_entity_property` VALUES ('519', 'name', '100', null, '名称', '', null, 'text', '', null, '', '\0', null, '100', null, null, '\0', null, 'name', 'java.lang.String', 'LIKE', 'property', null, '', '\0', null, '', '50', '<input type=\"text\" name=\"name\" value=\"${demo.name}\" class=\"easyui-textbox validatebox\"  data-options=\" required:true, validType:\'length[0,100]\'\"/>', '@org.hibernate.validator.constraints.NotBlank\r	@org.hibernate.validator.constraints.Length(min=100,max=0)\r	@javax.validation.constraints.NotNull \r	@javax.persistence.Column(name = \"name\",unique = false,nullable = false,length = 100)', '{field:\'name\',title:\'名称\',width:100,},', '<input type=\"text\" name=\"name\" value=\"${demo.name}\" class=\"easyui-textbox validatebox\"  />', null, null, '100', null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('520', 'z_integer', '100', null, 'ZInteger', '', null, 'text', '', null, '', '\0', null, null, null, null, '\0', null, 'ZInteger', 'java.lang.Integer', '', 'property', null, '', '', null, null, '50', '<input type=\"text\" name=\"ZInteger\" value=\"${demo.ZInteger}\" class=\"easyui-numberbox textbox\"  data-options=\" required:true,min:0.0,max:100.0 \"/>', '@javax.validation.constraints.Min(0) \r	@javax.validation.constraints.Max(100) \r	@javax.validation.constraints.NotNull \r	@javax.persistence.Column(name = \"z_integer\",unique = true,nullable = false)', '{field:\'ZInteger\',title:\'ZInteger\',width:100,},', '<input type=\"text\" name=\"ZInteger\" value=\"${demo.ZInteger}\" class=\"easyui-numberbox textbox\"  />', null, null, null, '100', null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('521', 'z_byte', '100', null, 'zByte', '', null, 'text', '', null, '', '\0', null, null, null, null, '', null, 'zByte', 'java.lang.Byte', '=', 'property', null, '', '\0', null, null, '50', '<input type=\"text\" name=\"zByte\" value=\"${demo.zByte}\" class=\"easyui-numberbox textbox\"  data-options=\" min:-128.0,max:127.0 \"/>', '@javax.validation.constraints.Min(-128) \r	@javax.validation.constraints.Max(127) \r	@javax.persistence.Column(name = \"z_byte\",unique = false,nullable = true)', '{field:\'zByte\',title:\'zByte\',width:100,},', '<input type=\"text\" name=\"zByte\" value=\"${demo.zByte}\" class=\"easyui-numberbox textbox\"  />', null, null, null, '127', null, '-128', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('522', 'z_double', '100', null, 'zDouble', '', null, 'text', '', null, '', '\0', null, null, null, null, '', '20', 'zDouble', 'java.lang.Double', '', 'property', '10', '', '\0', null, null, '50', '<input type=\"text\" name=\"zDouble\" value=\"${demo.zDouble}\" class=\"easyui-numberbox textbox\"  data-options=\" min:-20.0,max:100.0,scale:10 \"/>', '@javax.validation.constraints.DecimalMin(\"-20.0\") \r	@javax.validation.constraints.DecimalMax(\"100.0\") \r	@javax.persistence.Column(name = \"z_double\",precision = 20,scale = 10,unique = false,nullable = true)', '{field:\'zDouble\',title:\'zDouble\',width:100,},', '<input type=\"text\" name=\"zDouble\" value=\"${demo.zDouble}\" class=\"easyui-numberbox textbox\"  />', null, null, null, '100', null, '-20', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('523', 'z_bigDecimal', '100', null, 'ZBigDecimal', '', null, 'text', '', null, '', '\0', null, null, null, null, '', '20', 'ZBigDecimal', 'java.math.BigDecimal', '', 'property', '10', '', '\0', null, null, '50', '<input type=\"text\" name=\"ZBigDecimal\" value=\"${demo.ZBigDecimal}\" class=\"easyui-numberbox textbox\"  data-options=\" min:1.0,max:100.0,scale:10 \"/>', '@javax.validation.constraints.DecimalMin(\"1.0\") \r	@javax.validation.constraints.DecimalMax(\"100.0\") \r	@javax.persistence.Column(name = \"z_bigDecimal\",precision = 20,scale = 10,unique = false,nullable = true)', '{field:\'ZBigDecimal\',title:\'ZBigDecimal\',width:100,},', '<input type=\"text\" name=\"ZBigDecimal\" value=\"${demo.ZBigDecimal}\" class=\"easyui-numberbox textbox\"  />', null, null, null, '100', null, '1', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('524', 'z_boolean', '100', null, 'zBoolean', '{true-是}{false-否}', null, 'select', '', null, '', '\0', null, null, null, null, '', null, 'zBoolean', 'java.lang.Boolean', '', 'property', null, '', '\0', null, null, '50', '\r	<select  name=\"zBoolean\" class=\"easyui-combobox\"  style=\"width: 170px;\"   >\r	<option value=\"true\" <c:if test=\"${demo.zBoolean eq true }\">selected=\'selected\' </c:if> >是</option>\r	<option value=\"false\" <c:if test=\"${demo.zBoolean eq false }\">selected=\'selected\' </c:if> >否</option>\r	</select>\r	', '@javax.persistence.Column(name = \"z_boolean\",nullable = true )', '{field:\'zBoolean\',title:\'zBoolean\',width:100,},', '\r	<select  name=\"zBoolean\" class=\"easyui-combobox\"  style=\"width: 170px;\"   >\r	<option value=\"true\" <c:if test=\"${demo.zBoolean eq true }\">selected=\'selected\' </c:if> >是</option>\r	<option value=\"false\" <c:if test=\"${demo.zBoolean eq false }\">selected=\'selected\' </c:if> >否</option>\r	</select>\r	', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('525', 'z_date', '100', null, 'zDate', null, null, 'date', '', null, '\0', '\0', null, null, null, null, '\0', null, 'zDate', 'java.util.Date', '', 'property', null, '', '\0', null, null, '50', '<input type=\"text\" name=\"zDate\" value=\"${demo.zDate}\" class=\"easyui-datebox\"   data-options=\" required:true,\"/>', '@javax.validation.constraints.Future\r	@org.springframework.format.annotation.DateTimeFormat(pattern = \"yyyy-MM-dd\")\r	@javax.persistence.Temporal(javax.persistence.TemporalType.DATE)\r	@javax.persistence.Column(name = \"z_date\")', '{field:\'zDate\',title:\'zDate\',width:100,},', null, null, null, null, null, null, null, '', '\0', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('526', 'z_dateTime', '100', null, 'zDateTime', null, null, 'datetime', '', null, '\0', '\0', null, null, null, null, '\0', null, 'zDateTime', 'java.util.Date', '', 'property', null, '', '\0', null, null, '50', '<input type=\"text\" name=\"zDateTime\" value=\"${demo.zDateTime}\" class=\"easyui-datetimebox\"   data-options=\" required:true,\"/>', '@javax.validation.constraints.Past\r	@org.springframework.format.annotation.DateTimeFormat(pattern = \"yyyy-MM-dd HH:mm:ss\")\r	@javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)\r	@javax.persistence.Column(name = \"z_dateTime\")', '{field:\'zDateTime\',title:\'zDateTime\',width:100,},', null, null, null, null, null, null, null, '\0', '', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('527', 'z_timeStamp', '100', null, 'zTimeStamp', null, null, 'autoCurrentTime', '', null, '\0', '\0', null, null, null, null, '', null, 'zTimeStamp', 'java.util.Date', '', 'property', null, '', '\0', null, null, '50', '<input type=\"text\" name=\"zTimeStamp\" value=\"${demo.zTimeStamp}\" class=\"easyui-datetimebox\"  data-options=\" \"/>', '@org.zht.framework.annos.CurrentTimeStamp\r	\r	@javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)\r	@javax.persistence.Column(name = \"z_timeStamp\")', '{field:\'zTimeStamp\',title:\'zTimeStamp\',width:100,},', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('528', 'z_bytes', null, null, 'zbytes', null, null, null, null, null, null, null, null, null, null, null, '', null, 'zbytes', 'java.lang.Byte[]', null, 'property', null, null, null, null, null, '50', null, '@javax.persistence.Column(name = \"z_bytes\",nullable = true )', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('529', 'name', '100', null, 'name', '', null, 'text', '', null, '\0', '\0', null, '100', null, null, '', null, 'name', 'java.lang.String', '', 'property', null, '', '\0', null, '', '51', '<input type=\"text\" name=\"name\" value=\"${demomany.name}\" class=\"easyui-textbox validatebox\"  data-options=\"  validType:\'length[0,100]\'\"/>', '@org.hibernate.validator.constraints.NotBlank\r	@org.hibernate.validator.constraints.Length(min=100,max=0)\r	@javax.persistence.Column(name = \"name\",unique = false,nullable = true,length = 100)', '{field:\'name\',title:\'name\',width:100,},', null, null, null, '100', null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('530', 'demo_id', '100', null, 'Demo', '{url:/core/abc.do,valueField:val,textField:text}', null, 'combobox', '', null, '\0', '\0', '\0', null, null, null, null, null, 'Demo', 'com.zht.project.test.model.Demo', '', 'manytoone', null, '', null, null, null, '51', '<input type=\"text\" name=\"Demo\" value=\"${demomany.Demo}\"  class=\"easyui-combobox\"  data-options=\" url:\'${ctx}/core/abc.do\',method:\'post\',valueField:\'val\',textField:\'text\'\"/>', '@javax.persistence.ManyToOne(fetch=javax.persistence.FetchType.LAZY)\r	@javax.persistence.JoinColumn(name=\"demo_id\")', '{field:\'Demo\',title:\'Demo\',width:100,},', null, null, null, null, null, null, null, null, null, 'name', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('531', 'name', '100', null, 'name', '', null, 'text', '', null, '\0', '\0', null, '100', null, null, '', null, 'name', 'java.lang.String', '', 'property', null, '', '\0', null, '', '52', '<input type=\"text\" name=\"name\" value=\"${name}\" class=\"easyui-validatebox textbox\"  data-options=\"  validType:\'length[0,100]\'\"/>', '@org.hibernate.validator.constraints.NotBlank\r	@org.hibernate.validator.constraints.Length(min=100,max=0)\r	@javax.persistence.Column(name = \"name\",unique = false,nullable = true,length = 100)', '{field:\'name\',title:\'name\',width:100,},', null, null, null, '100', null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('532', 'PDemoTree_id', '100', null, 'DemoTree', '', null, 'text', '', null, '', '\0', '', null, null, null, null, null, 'PDemoTree', 'com.zht.project.test.model.DemoTree', '=', 'manytoone', null, '', null, null, null, '52', null, '@javax.persistence.ManyToOne(fetch=javax.persistence.FetchType.LAZY)\r	@javax.persistence.JoinColumn(name=\"PDemoTree_id\")\r	@org.zht.framework.annos.TreeParentFied()', null, null, null, null, null, null, null, null, null, null, '', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('534', 'ss', null, null, 'oneTomany', null, null, null, null, null, null, null, null, null, null, null, null, null, 'oneTomany', 'com.zht.project.test.model.Demomany', null, 'onetomany', null, null, null, null, null, '50', null, '@javax.persistence.OneToMany(fetch=javax.persistence.FetchType.LAZY,cascade=javax.persistence.CascadeType.ALL, mappedBy=\"Demo\")', null, null, null, null, null, null, null, null, null, null, null, 'Demo', null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('535', 'childDemoTrees', null, null, 'childDemoTrees', null, null, null, null, null, null, null, null, null, null, null, null, null, 'childDemoTrees', 'com.zht.project.test.model.DemoTree', null, 'onetomany', null, null, null, null, null, '52', null, '@javax.persistence.OneToMany(fetch=javax.persistence.FetchType.LAZY,cascade=javax.persistence.CascadeType.ALL, mappedBy=\"PDemoTree\")', null, null, null, null, null, null, null, null, null, null, null, 'PDemoTree', null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('536', 'name', '122', null, 'name', '', null, 'text', '', null, '\0', '\0', null, '122', null, null, '', null, 'name', 'java.lang.String', '', 'property', null, '', '\0', null, '', '53', '<input type=\"text\" name=\"name\" value=\"${name}\" class=\"easyui-validatebox textbox\"  data-options=\"  validType:\'length[0,122]\'\"/>', '@org.hibernate.validator.constraints.NotBlank\r	@org.hibernate.validator.constraints.Length(min=122,max=0)\r	@javax.persistence.Column(name = \"name\",unique = false,nullable = true,length = 122)', '{field:\'name\',title:\'name\',width:122,},', null, null, null, '122', null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('538', 'name', '23', null, 'name', '', null, 'text', '', null, '\0', '\0', null, '23', null, null, '', null, 'name', 'java.lang.String', '', 'property', null, '', '\0', null, '', '54', '<input type=\"text\" name=\"name\" value=\"${name}\" class=\"easyui-validatebox textbox\"  data-options=\"  validType:\'length[0,23]\'\"/>', '@org.hibernate.validator.constraints.NotBlank\r	@org.hibernate.validator.constraints.Length(min=23,max=0)\r	@javax.persistence.Column(name = \"name\",unique = false,nullable = true,length = 23)', '{field:\'name\',title:\'name\',width:23,},', null, null, null, '23', null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('542', 'mmslavers', null, null, 'mmslavers', null, null, null, null, null, null, null, null, null, null, null, null, null, 'mmslavers', 'com.zht.project.test.model.Many2manySlaver', null, 'manytomanySlaver', null, null, null, null, null, '53', null, '@javax.persistence.ManyToMany(fetch=javax.persistence.FetchType.LAZY)\r	@javax.persistence.JoinTable(name=\"m-m\", joinColumns = {\r		@javax.persistence.JoinColumn(name=\"m-m-marster_id\", nullable=false, updatable=false) },\r		inverseJoinColumns = { @javax.persistence.JoinColumn(name=\"m-m-slaver_id\", nullable=false, updatable=false) })', null, null, null, null, null, null, null, null, null, null, null, null, '', null, 'm-m-marster_id', 'm-m', 'm-m-slaver_id', null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('543', 'mmmarster', null, null, 'mmmarster', null, null, null, null, null, null, null, null, null, null, null, null, null, 'mmmarster', 'com.zht.project.test.model.Many2manyMaster', null, 'manytomanyMarster', null, null, null, null, null, '54', null, '@javax.persistence.ManyToMany(cascade=javax.persistence.CascadeType.ALL,mappedBy=\"mmslavers\")', null, null, null, null, null, null, null, null, null, null, null, null, '\0', null, null, null, null, 'mmslavers', null, null, null);
INSERT INTO `zht_entity_property` VALUES ('544', 'name', '100', null, 'name', '', null, 'text', '', null, '\0', '\0', null, '100', null, null, '', null, 'name', 'java.lang.String', '', 'property', null, '', '\0', null, '', '55', '<input type=\"text\" name=\"name\" value=\"${name}\" class=\"easyui-validatebox textbox\"  data-options=\"  validType:\'length[0,100]\'\"/>', '@org.hibernate.validator.constraints.NotBlank\r	@org.hibernate.validator.constraints.Length(min=100,max=0)\r	@javax.persistence.Column(name = \"name\",unique = false,nullable = true,length = 100)', '{field:\'name\',title:\'name\',width:100,},', null, null, null, '100', null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('545', 'slaver', null, null, 'slaver', null, null, null, '', null, null, null, null, null, null, null, null, null, 'slaver', 'com.zht.project.test.model.One2OneSlaver', null, 'onetoonefk', null, null, null, null, null, '55', null, '@javax.persistence.OneToOne(mappedBy=\"marster\")', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'marster', 'name');
INSERT INTO `zht_entity_property` VALUES ('546', 'name', '12', null, 'name', '', null, 'text', '', null, '\0', '\0', null, '12', null, null, '', null, 'name', 'java.lang.String', '', 'property', null, '', '\0', null, '', '56', '<input type=\"text\" name=\"name\" value=\"${name}\" class=\"easyui-validatebox textbox\"  data-options=\"  validType:\'length[0,12]\'\"/>', '@org.hibernate.validator.constraints.NotBlank\r	@org.hibernate.validator.constraints.Length(min=12,max=0)\r	@javax.persistence.Column(name = \"name\",unique = false,nullable = true,length = 12)', '{field:\'name\',title:\'name\',width:12,},', null, null, null, '12', null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('547', 'marster_id', null, null, 'marster', null, null, null, '', null, null, null, null, null, null, null, null, null, 'marster', 'com.zht.project.test.model.One2OneMarster', null, 'onetoonefk', null, null, null, null, null, '56', null, '@javax.persistence.OneToOne(cascade=javax.persistence.CascadeType.ALL)\r	@javax.persistence.JoinColumn(name=\"marster_id\")', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '', 'name');
INSERT INTO `zht_entity_property` VALUES ('548', 'textPassword', '100', null, 'textPassword', '', null, 'password', '', null, '\0', '\0', null, '40', null, null, '\0', null, 'textPassword', 'java.lang.String', '', 'property', null, '', '\0', null, '', '50', '<input type=\"password\" name=\"textPassword\" value=\"${demo.textPassword}\" class=\"easyui-textbox validatebox\" data-options=\" required:true,\"/>', '@org.hibernate.validator.constraints.NotBlank\r	@org.hibernate.validator.constraints.Length(min=40,max=0)\r	@javax.validation.constraints.NotNull \r	@javax.persistence.Column(name = \"textPassword\",unique = false,nullable = false,length = 40)', '{field:\'textPassword\',title:\'textPassword\',width:100,},', null, null, null, '40', null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('549', 'texttextarea', '100', null, 'texttextarea', '{rows-3}{cols-20}', null, 'textarea', '', null, '\0', '\0', null, '300', null, null, '', null, 'texttextarea', 'java.lang.String', '', 'property', null, '', '\0', null, '', '50', '<textarea name=\"texttextarea\" value=\"${demo.texttextarea}\" rows=\"3\" cols=\"20\" class=\"easyui-textbox validatebox\" data-options=\" \"></textarea>', '@org.hibernate.validator.constraints.NotBlank\r	@org.hibernate.validator.constraints.Length(min=300,max=0)\r	@javax.persistence.Column(name = \"texttextarea\",unique = false,nullable = true,length = 300)', '{field:\'texttextarea\',title:\'texttextarea\',width:100,},', null, null, null, '300', null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('550', 'textSelect', '200', null, 'textSelect', '{1-北京}{2-上海}{3-深圳}', null, 'select', '', null, '\0', '\0', null, '200', null, null, '', null, 'textSelect', 'java.lang.String', '', 'property', null, '', '\0', null, '', '50', '\r	<select  name=\"textSelect\" class=\"easyui-combobox\" style=\"width: 170px;\" >\r	<option value=\"1\" <c:if test=\"${demo.textSelect eq \'1\' }\">selected=\'selected\' </c:if> >北京</option>\r	<option value=\"2\" <c:if test=\"${demo.textSelect eq \'2\' }\">selected=\'selected\' </c:if> >上海</option>\r	<option value=\"3\" <c:if test=\"${demo.textSelect eq \'3\' }\">selected=\'selected\' </c:if> >深圳</option>\r	</select>\r	', '@org.hibernate.validator.constraints.NotBlank\r	@org.hibernate.validator.constraints.Length(min=200,max=0)\r	@javax.persistence.Column(name = \"textSelect\",unique = false,nullable = true,length = 200)', '{field:\'textSelect\',title:\'textSelect\',width:200,},', null, null, null, '200', null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('551', 'textcombox', '200', null, 'textcombox', '{url:/core/abc.do,valueField:val,textField:text}', null, 'combobox', '', null, '', '\0', null, '200', null, null, '\0', null, 'textcombox', 'java.lang.String', '=', 'property', null, '', '\0', null, '', '50', '<input type=\"text\" name=\"textcombox\" value=\"${demo.textcombox}\"  class=\"easyui-combobox\"  data-options=\" required:true,url:\'${ctx}/core/abc.do\',method:\'post\',valueField:\'val\',textField:\'text\'\"/>', '@org.hibernate.validator.constraints.NotBlank\r	@org.hibernate.validator.constraints.Length(min=200,max=0)\r	@javax.validation.constraints.NotNull \r	@javax.persistence.Column(name = \"textcombox\",unique = false,nullable = false,length = 200)', '{field:\'textcombox\',title:\'textcombox\',width:200,},', '<input type=\"text\" name=\"textcombox\" value=\"${demo.textcombox}\"  class=\"easyui-combobox\"  data-options=\"  url:\'${ctx}/core/abc.do\',method:\'post\',valueField:\'val\',textField:\'text\'\"/>', null, null, '200', null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('552', 'textcheckbox', '100', null, 'textcheckbox', '{1-北京}{2-上海}{3-深圳}', null, 'checkbox', '', null, '\0', '\0', null, '100', null, null, '', null, 'textcheckbox', 'java.lang.String', '', 'property', null, '', '\0', null, '', '50', '\r	<input type=\"checkbox\"  name=\"textcheckbox\" value=\"1\" <c:if test=\"${demo.textcheckbox eq \'1\' }\">checked=\"checked\" </c:if> >北京\r	<input type=\"checkbox\"  name=\"textcheckbox\" value=\"2\" <c:if test=\"${demo.textcheckbox eq \'2\' }\">checked=\"checked\" </c:if> >上海\r	<input type=\"checkbox\"  name=\"textcheckbox\" value=\"3\" <c:if test=\"${demo.textcheckbox eq \'3\' }\">checked=\"checked\" </c:if> >深圳\r	', '@org.hibernate.validator.constraints.NotBlank\r	@org.hibernate.validator.constraints.Length(min=100,max=0)\r	@javax.persistence.Column(name = \"textcheckbox\",unique = false,nullable = true,length = 100)', '{field:\'textcheckbox\',title:\'textcheckbox\',width:100,},', null, null, null, '100', null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('553', 'ZIntegerSelect', '100', null, 'ZIntegerSelect', '{1-北京}{2-上海}{3-深圳}', null, 'select', '', null, '\0', '\0', null, '100', null, null, '\0', null, 'ZIntegerSelect', 'java.lang.String', '', 'property', null, '', '\0', null, '', '50', '\r	<select  name=\"ZIntegerSelect\" class=\"easyui-combobox\" style=\"width: 170px;\"  data-options=\" required:true\">\r	<option value=\"1\" <c:if test=\"${demo.ZIntegerSelect eq \'1\' }\">selected=\'selected\' </c:if> >北京</option>\r	<option value=\"2\" <c:if test=\"${demo.ZIntegerSelect eq \'2\' }\">selected=\'selected\' </c:if> >上海</option>\r	<option value=\"3\" <c:if test=\"${demo.ZIntegerSelect eq \'3\' }\">selected=\'selected\' </c:if> >深圳</option>\r	</select>\r	', '@org.hibernate.validator.constraints.NotBlank\r	@org.hibernate.validator.constraints.Length(min=100,max=0)\r	@javax.validation.constraints.NotNull \r	@javax.persistence.Column(name = \"ZIntegerSelect\",unique = false,nullable = false,length = 100)', '{field:\'ZIntegerSelect\',title:\'ZIntegerSelect\',width:100,},', null, null, null, '100', null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('554', 'ZDoubleSelect', '100', null, 'ZDoubleSelect', '{1-北京}{2-上海}{3-深圳}', null, 'select', '', null, '', '\0', null, null, null, null, '\0', null, 'ZDoubleSelect', 'java.lang.Double', '', 'property', null, '', '\0', null, null, '50', '\r	<select  name=\"ZDoubleSelect\" class=\"easyui-combobox\"  style=\"width: 170px;\"   data-options=\" required:true\">\r	<option value=\"1\" <c:if test=\"${demo.ZDoubleSelect eq \'1\' }\">selected=\'selected\' </c:if> >北京</option>\r	<option value=\"2\" <c:if test=\"${demo.ZDoubleSelect eq \'2\' }\">selected=\'selected\' </c:if> >上海</option>\r	<option value=\"3\" <c:if test=\"${demo.ZDoubleSelect eq \'3\' }\">selected=\'selected\' </c:if> >深圳</option>\r	</select>\r	', '@javax.validation.constraints.NotNull \r	@javax.persistence.Column(name = \"ZDoubleSelect\",unique = false,nullable = false)', '{field:\'ZDoubleSelect\',title:\'ZDoubleSelect\',width:100,},', '\r	<select  name=\"ZDoubleSelect\" class=\"easyui-combobox\"  style=\"width: 170px;\"   data-options=\" \">\r	<option value=\"1\" <c:if test=\"${demo.ZDoubleSelect eq \'1\' }\">selected=\'selected\' </c:if> >北京</option>\r	<option value=\"2\" <c:if test=\"${demo.ZDoubleSelect eq \'2\' }\">selected=\'selected\' </c:if> >上海</option>\r	<option value=\"3\" <c:if test=\"${demo.ZDoubleSelect eq \'3\' }\">selected=\'selected\' </c:if> >深圳</option>\r	</select>\r	', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('555', 'zcombotree', '100', null, 'zcombotree', '{url:/core/abc.do,valueField:val,textField:text}', null, 'combotree', '', null, '', '\0', null, '100', null, null, '', null, 'zcombotree', 'java.lang.String', '=', 'property', null, '', '', null, '', '50', '<input type=\"text\" name=\"zcombotree\" value=\"${demo.zcombotree}\"  class=\"easyui-combotree\"  data-options=\" url:\'${ctx}/core/abc.do\',method:\'post\',valueField:\'val\',textField:\'text\'\"/>', '@org.hibernate.validator.constraints.NotBlank\r	@org.hibernate.validator.constraints.Length(min=100,max=0)\r	@javax.persistence.Column(name = \"zcombotree\",unique = true,nullable = true,length = 100)', '{field:\'zcombotree\',title:\'zcombotree\',width:100,},', '<input type=\"text\" name=\"zcombotree\" value=\"${demo.zcombotree}\"  class=\"easyui-combotree\"  data-options=\" url:\'${ctx}/core/abc.do\',method:\'post\',valueField:\'val\',textField:\'text\'\"/>', null, null, '100', null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('561', 'name', '120', null, '名称', '', null, 'text', '', null, '', '\0', null, '40', null, null, '\0', null, 'name', 'java.lang.String', 'LIKE', 'property', null, '', '\0', null, '', '58', '<input type=\"text\" name=\"name\" value=\"${department.name}\" class=\"easyui-textbox validatebox\"  data-options=\" required:true, validType:\'length[0,40]\'\"/>', '@org.hibernate.validator.constraints.Length(min=0,max=40)\r	@javax.validation.constraints.NotNull \r	@org.hibernate.validator.constraints.NotBlank\r	@javax.persistence.Column(name = \"name\",unique = false,nullable = false,length = 40)', '{field:\'name\',title:\'名称\',width:120,},', '<input type=\"text\"  name=\"webParams[name]\"    class=\"easyui-textbox validatebox\"  />', null, null, '40', null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('562', 'remark', '120', null, '备注', '', null, 'text', '', null, '\0', '\0', null, '60', null, null, '', null, 'remark', 'java.lang.String', '', 'property', null, '', '\0', null, '', '58', '<input type=\"text\" name=\"remark\" value=\"${department.remark}\" class=\"easyui-textbox validatebox\"  data-options=\"  validType:\'length[0,60]\'\"/>', '@org.hibernate.validator.constraints.Length(min=0,max=60)\r	@javax.persistence.Column(name = \"remark\",unique = false,nullable = true,length = 60)', '{field:\'remark\',title:\'备注\',width:120,},', null, null, null, '60', null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('563', 'modify_time', '100', null, '修改时间', null, null, 'autoCurrentTime', '', null, '\0', '\0', null, null, null, null, '', null, 'modifyTime', 'java.util.Date', '', 'property', null, '', '\0', null, null, '58', '', '@org.zht.framework.annos.CurrentTimeStamp\r	@javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)\r	@javax.persistence.Column(name = \"modify_time\")', '{field:\'modifyTime\',title:\'修改时间\',width:100,},', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('564', 'parent_department_id', null, null, '父级部门', '{url:/common/sys/department/loadDepartmentCombotree,valueField:id,textField:text}', null, 'combotree', '', null, '', '\0', '', null, null, null, null, null, 'parentDepartment', 'com.zht.common.sys.model.Department', '=', 'manytoone', null, '', null, null, null, '58', '<input type=\"text\" name=\"parentDepartment.id\" value=\"${department.parentDepartment.id}\"  class=\"easyui-combotree\"  data-options=\" url:\'${ctx}/common/sys/department/loadDepartmentCombotree\',method:\'post\',valueField:\'id\',textField:\'text\'\"/>', '@javax.persistence.ManyToOne(fetch=javax.persistence.FetchType.LAZY)\r	@javax.persistence.JoinColumn(name=\"parent_department_id\")\r	@org.zht.framework.annos.TreeParentFied()', '{field:\'parentDepartment\',title:\'父级部门\',width:null,},', '<input type=\"text\"  name=\"webParams[parentDepartment.id]\"     class=\"easyui-combotree\"  />', null, null, null, null, null, null, null, null, 'name', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('565', 'creaater', '100', null, '创建者', '', null, 'text', '', null, '\0', '\0', null, '40', null, null, '', null, 'creater', 'java.lang.String', '', 'property', null, '', '\0', null, '', '58', '<input type=\"text\" name=\"creater\" value=\"${department.creater}\" class=\"easyui-textbox validatebox\"  data-options=\"  validType:\'length[0,40]\'\"/>', '@org.hibernate.validator.constraints.Length(min=0,max=40)\r	@javax.persistence.Column(name = \"creaater\",unique = false,nullable = true,length = 40)', '{field:\'creater\',title:\'创建者\',width:100,},', null, null, null, '40', null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('566', 'vv', null, null, '子部门', null, null, null, null, null, null, null, null, null, null, null, null, null, 'childDepartments', 'com.zht.common.sys.model.Department', null, 'onetomany', null, null, null, null, null, '58', null, '@javax.persistence.OneToMany(fetch=javax.persistence.FetchType.LAZY,cascade=javax.persistence.CascadeType.ALL, mappedBy=\"parentDepartment\")', null, null, null, null, null, null, null, null, null, null, null, 'parentDepartment', null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('567', 'name', '120', null, '职位名称', '', null, 'text', '', null, '', '\0', null, '40', null, null, '', null, 'name', 'java.lang.String', 'LIKE', 'property', null, '', '\0', null, '', '59', '<input type=\"text\" name=\"name\" value=\"${position.name}\" class=\"easyui-textbox validatebox\"  data-options=\"  validType:\'length[0,40]\'\"/>', '@org.hibernate.validator.constraints.Length(min=0,max=40)\r	@javax.persistence.Column(name = \"name\",unique = false,nullable = true,length = 40)', '{field:\'name\',title:\'职位名称\',width:120,},', '<input type=\"text\"  name=\"webParams[name]\"    class=\"easyui-textbox validatebox\"  />', null, null, '40', null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('568', 'modify_time', '100', null, '修改时间', null, null, 'autoCurrentTime', '', null, '\0', '\0', null, null, null, null, '', null, 'modifyTime', 'java.util.Date', '', 'property', null, '', '\0', null, null, '59', '', '@org.zht.framework.annos.CurrentTimeStamp\r	@javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)\r	@javax.persistence.Column(name = \"modify_time\")', '{field:\'modifyTime\',title:\'修改时间\',width:100,},', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('569', 'creater', '100', null, '创建者', '', null, 'text', '', null, '\0', '\0', null, '40', null, null, '', null, 'creater', 'java.lang.String', '', 'property', null, '', '\0', null, '', '59', '<input type=\"text\" name=\"creater\" value=\"${position.creater}\" class=\"easyui-textbox validatebox\"  data-options=\"  validType:\'length[0,40]\'\"/>', '@org.hibernate.validator.constraints.Length(min=0,max=40)\r	@javax.persistence.Column(name = \"creater\",unique = false,nullable = true,length = 40)', '{field:\'creater\',title:\'创建者\',width:100,},', null, null, null, '40', null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('570', 'remark', '100', null, '备注', '', null, 'text', '', null, '\0', '\0', null, '60', null, null, '', null, 'remark', 'java.lang.String', '', 'property', null, '', '\0', null, '', '59', '<input type=\"text\" name=\"remark\" value=\"${position.remark}\" class=\"easyui-textbox validatebox\"  data-options=\"  validType:\'length[0,60]\'\"/>', '@org.hibernate.validator.constraints.Length(min=0,max=60)\r	@javax.persistence.Column(name = \"remark\",unique = false,nullable = true,length = 60)', '{field:\'remark\',title:\'备注\',width:100,},', null, null, null, '60', null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('571', 'depatment_id', null, null, '所属部门', '{url:/common/sys/department/loadDepartmentCombotree,valueField:id,textField:text}', null, 'combotree', '', null, '', '\0', '\0', null, null, null, null, null, 'department', 'com.zht.common.sys.model.Department', '=', 'manytoone', null, '', null, null, null, '59', '<input type=\"text\" name=\"department.id\" value=\"${position.department.id}\"  class=\"easyui-combotree\"  data-options=\" url:\'${ctx}/common/sys/department/loadDepartmentCombotree\',method:\'post\',valueField:\'id\',textField:\'text\'\"/>', '@javax.persistence.ManyToOne(fetch=javax.persistence.FetchType.LAZY)\r	@javax.persistence.JoinColumn(name=\"depatment_id\")', '{field:\'department\',title:\'所属部门\',width:null,},', '<input type=\"text\"  name=\"webParams[department.id]\"     class=\"easyui-combotree\"  />', null, null, null, null, null, null, null, null, 'name', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('572', 'user_name', '100', null, '用户名称', '', null, 'text', '', null, '\0', '\0', null, '40', null, null, '\0', null, 'userName', 'java.lang.String', '', 'property', null, '', '\0', null, '', '60', '<input type=\"text\" name=\"userName\" value=\"${user.userName}\" class=\"easyui-textbox validatebox\"  data-options=\" required:true, validType:\'length[0,40]\'\"/>', '@org.hibernate.validator.constraints.Length(min=0,max=40)\r	@javax.validation.constraints.NotNull \r	@org.hibernate.validator.constraints.NotBlank\r	@javax.persistence.Column(name = \"userName\",unique = false,nullable = false,length = 40)', '{field:\'userName\',title:\'用户名称\',width:100,},', null, null, null, '40', null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('573', 'user_num', '100', null, '用户编码', '', null, 'text', '', null, '\0', '\0', null, '40', null, null, '\0', null, 'userNum', 'java.lang.String', '', 'property', null, '', '', null, '', '60', '<input type=\"text\" name=\"userNum\" value=\"${user.userNum}\" class=\"easyui-textbox validatebox\"  data-options=\" required:true, validType:\'length[0,40]\'\"/>', '@org.hibernate.validator.constraints.Length(min=0,max=40)\r	@javax.validation.constraints.NotNull \r	@org.hibernate.validator.constraints.NotBlank\r	@javax.persistence.Column(name = \"user_num\",unique = true,nullable = false,length = 40)', '{field:\'userNum\',title:\'用户编码\',width:100,},', null, null, null, '40', null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('574', 'birth', '100', null, '出生日期', null, null, 'date', '', null, '\0', '\0', null, null, null, null, '', null, 'birth', 'java.util.Date', '', 'property', null, '', '\0', null, null, '60', '<input type=\"text\" name=\"birth\" value=\"${user.birth}\" class=\"easyui-datebox\"   data-options=\" \"/>', '@org.springframework.format.annotation.DateTimeFormat(pattern = \"yyyy-MM-dd\")\r	@javax.persistence.Temporal(javax.persistence.TemporalType.DATE)\r	@javax.persistence.Column(name = \"birth\")', '{field:\'birth\',title:\'出生日期\',width:100,},', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('575', 'sex', '20', null, '性别', ' {1-男}{2-女}', null, 'select', '', null, '\0', '\0', null, null, null, null, '', null, 'sex', 'java.lang.Short', '', 'property', null, '', '\0', null, null, '60', '\r	<select  name=\"sex\" class=\"easyui-combobox\"  style=\"width: 170px;\"  >\r	<option value=\" 1\" <c:if test=\"${user.sex eq \' 1\' }\">selected=\'selected\' </c:if> >男</option>\r	<option value=\"2\" <c:if test=\"${user.sex eq \'2\' }\">selected=\'selected\' </c:if> >女</option>\r	</select>\r	', '@javax.persistence.Column(name = \"sex\",unique = false,nullable = true)', '{field:\'sex\',title:\'性别\',width:20,},', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('576', 'email', '40', null, '邮箱', '', null, 'text', '', null, '\0', '\0', null, '40', null, null, '', null, 'email', 'java.lang.String', '', 'property', null, '', '\0', null, 'email', '60', '<input type=\"text\" name=\"email\" value=\"${user.email}\" class=\"easyui-textbox validatebox\"  data-options=\"  validType:\'email\'\"/>', '@org.hibernate.validator.constraints.Length(min=0,max=40)\r	@javax.persistence.Column(name = \"email\",unique = false,nullable = true,length = 40)', '{field:\'email\',title:\'邮箱\',width:40,},', null, null, null, '40', null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('577', 'phone', '40', null, '电话', '', null, 'text', '', null, '\0', '\0', null, '30', null, null, '', null, 'phone', 'java.lang.String', '', 'property', null, '', '\0', null, 'phone', '60', '<input type=\"text\" name=\"phone\" value=\"${user.phone}\" class=\"easyui-textbox validatebox\"  data-options=\"  validType:\'phone\'\"/>', '@org.hibernate.validator.constraints.Length(min=0,max=30)\r	@javax.persistence.Column(name = \"phone\",unique = false,nullable = true,length = 30)', '{field:\'phone\',title:\'电话\',width:40,},', null, null, null, '30', null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('578', 'per_id_num', '60', null, '身份证号码', '', null, 'text', '', null, '\0', '\0', null, '18', null, null, '', null, 'perIdNum', 'java.lang.String', '', 'property', null, '', '\0', null, '', '60', '<input type=\"text\" name=\"perIdNum\" value=\"${user.perIdNum}\" class=\"easyui-textbox validatebox\"  data-options=\"  validType:\'length[0,18]\'\"/>', '@org.hibernate.validator.constraints.Length(min=0,max=18)\r	@javax.persistence.Column(name = \"per_id_num\",unique = false,nullable = true,length = 18)', '{field:\'perIdNum\',title:\'身份证号码\',width:60,},', null, null, null, '18', null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('579', 'qq_num', '30', null, 'QQ号码', '', null, 'text', '', null, '\0', '\0', null, '20', null, null, '', null, 'qqNum', 'java.lang.String', '', 'property', null, '', '\0', null, '', '60', '<input type=\"text\" name=\"qqNum\" value=\"${user.qqNum}\" class=\"easyui-textbox validatebox\"  data-options=\"  validType:\'length[0,20]\'\"/>', '@org.hibernate.validator.constraints.Length(min=0,max=20)\r	@javax.persistence.Column(name = \"qq_num\",unique = false,nullable = true,length = 20)', '{field:\'qqNum\',title:\'QQ号码\',width:30,},', null, null, null, '20', null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('580', 'weixin_Num', '30', null, '微信号', '', null, 'text', '', null, '\0', '\0', null, '30', null, null, '', null, 'weixinNum', 'java.lang.String', '', 'property', null, '', '\0', null, '', '60', '<input type=\"text\" name=\"weixinNum\" value=\"${user.weixinNum}\" class=\"easyui-textbox validatebox\"  data-options=\"  validType:\'length[0,30]\'\"/>', '@org.hibernate.validator.constraints.Length(min=0,max=30)\r	@javax.persistence.Column(name = \"weixin_Num\",unique = false,nullable = true,length = 30)', '{field:\'weixinNum\',title:\'微信号\',width:30,},', null, null, null, '30', null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('581', 'name', '100', null, '名称', '', null, 'text', '', null, '\0', '\0', null, '30', null, null, '', null, 'name', 'java.lang.String', '', 'property', null, '', '\0', null, '', '61', '<input type=\"text\" name=\"name\" value=\"${log.name}\" class=\"easyui-textbox validatebox\"  data-options=\"  validType:\'length[0,30]\'\"/>', '@org.hibernate.validator.constraints.Length(min=0,max=30)\r	@javax.persistence.Column(name = \"name\",unique = false,nullable = true,length = 30)', '{field:\'name\',title:\'名称\',width:100,},', null, null, null, '30', null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('582', 'code', '100', null, '编码', '', null, 'text', '', null, '', '\0', null, '30', null, null, '\0', null, 'code', 'java.lang.String', '=', 'property', null, '', '', null, '', '61', '<input type=\"text\" name=\"code\" value=\"${log.code}\" class=\"easyui-textbox validatebox\"  data-options=\" required:true, validType:\'length[0,30]\'\"/>', '@org.hibernate.validator.constraints.Length(min=0,max=30)\r	@javax.validation.constraints.NotNull \r	@org.hibernate.validator.constraints.NotBlank\r	@javax.persistence.Column(name = \"code\",unique = true,nullable = false,length = 30)', '{field:\'code\',title:\'编码\',width:100,},', '<input type=\"text\"  name=\"webParams[code]\"    class=\"easyui-textbox validatebox\"  />', null, null, '30', null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('583', 'deptment_id', '100', null, '所属机构', '{url:/common/sys/department/loadDepartmentCombotree,valueField:id,textField:text}', null, 'combotree', '', null, '\0', '\0', '\0', null, null, null, null, null, 'depetment', 'com.zht.common.sys.model.Department', '', 'manytoone', null, '', null, null, null, '61', '<input type=\"text\" name=\"depetment.id\" value=\"${log.depetment.id}\"  class=\"easyui-combotree\"  data-options=\" url:\'${ctx}/common/sys/department/loadDepartmentCombotree\',method:\'post\',valueField:\'id\',textField:\'text\'\"/>', '@javax.persistence.ManyToOne(fetch=javax.persistence.FetchType.LAZY)\r	@javax.persistence.JoinColumn(name=\"deptment_id\")', '{field:\'depetment\',title:\'所属机构\',width:100,},', null, null, null, null, null, null, null, null, null, 'name', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `zht_entity_property` VALUES ('584', 'dsf', null, null, 'df', null, null, null, null, null, null, null, null, null, null, null, null, null, 'dfs', 'java.util.Date', null, 'property', null, null, null, null, null, '58', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zht_gen_entity
-- ----------------------------
INSERT INTO `zht_gen_entity` VALUES ('50', null, '/project/demo', '', '\0', '2015-04-08 16:41:02', '', 'com.zht.project.test.model.Demo', '', '', '测试实体类', 'z_demo');
INSERT INTO `zht_gen_entity` VALUES ('51', null, '/project/demomany', '', '\0', '2015-04-08 16:41:19', '', 'com.zht.project.test.model.Demomany', '', '', '测试Many', 'z_demo_many');
INSERT INTO `zht_gen_entity` VALUES ('52', null, '/project/demoTree', null, '', null, null, 'com.zht.project.test.model.DemoTree', '', null, '测试树', 'z_demo_tree');
INSERT INTO `zht_gen_entity` VALUES ('53', null, '/project/demoMany2manyMaster', null, '\0', '2015-04-08 17:00:02', null, 'com.zht.project.test.model.Many2manyMaster', '', null, 'demoMany2manyMaster', 'z_demo_Many2manyMaster');
INSERT INTO `zht_gen_entity` VALUES ('54', null, '/project/demoMany2manySlaver', null, '\0', '2015-04-08 17:00:49', null, 'com.zht.project.test.model.Many2manySlaver', '', null, 'Many2manyMaster', 'z_demo_Many2manyMaster');
INSERT INTO `zht_gen_entity` VALUES ('55', null, '/project/one2one', null, '\0', '2015-04-08 18:14:36', null, 'com.zht.project.test.model.One2OneMarster', '', null, 'One2OneMarster', 'z_demo_One2OneMarster');
INSERT INTO `zht_gen_entity` VALUES ('56', null, '/project/onetoe', null, '\0', '2015-04-08 18:15:23', null, 'com.zht.project.test.model.One2OneSlaver', '', null, 'One2OneSlaver', 'z_demo_One2OneSlaver');
INSERT INTO `zht_gen_entity` VALUES ('58', null, '/common/sys/department', '', '', '2015-04-15 15:34:47', '', 'com.zht.common.sys.model.Department', '', '', '部门机构', 'sys_department');
INSERT INTO `zht_gen_entity` VALUES ('59', null, '/common/sys/position', '', '\0', '2015-04-15 16:10:38', '', 'com.zht.common.sys.model.Position', '', '', '职位', 'sys_position');
INSERT INTO `zht_gen_entity` VALUES ('60', null, '/common/sys/user', null, '\0', '2015-04-15 16:15:05', null, 'com.zht.common.sys.model.User', '', null, '系统用户', 'sys_user');
INSERT INTO `zht_gen_entity` VALUES ('61', null, '/sys/common/log', null, '\0', '2015-04-16 19:41:53', null, 'com.zht.common.sys.model.Log', '', null, '岗位', 'sys_log');
