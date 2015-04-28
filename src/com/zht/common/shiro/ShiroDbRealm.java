package com.zht.common.shiro;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.zht.framework.util.ConfigUtil;

import com.zht.common.rabc.model.RbacUser;
import com.zht.common.rabc.service.IRbacPermissionService;
import com.zht.common.rabc.service.IRbacRoleService;
import com.zht.common.rabc.service.IRbacUserService;

public class ShiroDbRealm extends AuthorizingRealm {

    @Autowired
    private IRbacUserService userService;
    @Autowired
    private IRbacRoleService roleService;
    @Autowired
    private IRbacPermissionService permissionService;
    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    	 if (principals == null) {
    	    throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
    	 }
    	 List<String> roles=new ArrayList<String>();
    	 List<String> perms=new ArrayList<String>();
         String userName = (String) principals.getPrimaryPrincipal();
         
         String rabc_pattern=ConfigUtil.getConfig("system.properties", "rabc_pattern","A");
        
         if("A".equals(rabc_pattern)){
        	roles=roleService.findRoleCodeUserHaveInPatternA(userName,true);
            perms=permissionService.findAllPermsUserHaveAndInDefaultRoleInPatternA(userName);	
         }else {
        	roles=roleService.findRoleCodeUserHaveInPatternB(userName,true);
            perms=permissionService.findAllPermsUserHaveAndInDefaultRoleInPatternB(userName);	
         }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRoles(roles);
        authorizationInfo.addStringPermissions(perms);
//
        return authorizationInfo;
    }

    private static final String OR_OPERATOR = " or ";
    private static final String AND_OPERATOR = " and ";
    private static final String NOT_OPERATOR = "not ";

    /**
     * 支持or and not 关键词  不支持and or混用
     *
     * @param principals
     * @param permission
     * @return
     */
    public boolean isPermitted(PrincipalCollection principals, String permission) {
        if (permission.contains(OR_OPERATOR)) {
            String[] permissions = permission.split(OR_OPERATOR);
            for (String orPermission : permissions) {
                if (isPermittedWithNotOperator(principals, orPermission)) {
                    return true;
                }
            }
            return false;
        } else if (permission.contains(AND_OPERATOR)) {
            String[] permissions = permission.split(AND_OPERATOR);
            for (String orPermission : permissions) {
                if (!isPermittedWithNotOperator(principals, orPermission)) {
                    return false;
                }
            }
            return true;
        } else {
            return isPermittedWithNotOperator(principals, permission);
        }
    }

    private boolean isPermittedWithNotOperator(PrincipalCollection principals, String permission) {
        if (permission.startsWith(NOT_OPERATOR)) {
            return !super.isPermitted(principals, permission.substring(NOT_OPERATOR.length()));
        } else {
            return super.isPermitted(principals, permission);
        }
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
    	UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
    	RbacUser user=userService.findUserByName(token.getUsername());
    	SimpleAuthenticationInfo info =null;
    	 if(user!=null){
    	 	 info = new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), getName());
        	 info.setCredentialsSalt(ByteSource.Util.bytes(user.getSalt()));
        	 return info;
    	 }
    	 return info;
    }

    /**
     * 清除用户授权信息缓存.
     */
    public void clearCachedAuthorizationInfo(String userName) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(userName, getName());
        super.clearCachedAuthorizationInfo(principals);
    }
    /**
     * 清除认证信息
     * @param username
     */
    public  void clearAuthorizationInfo(String userName){  
    	 SimplePrincipalCollection principals = new SimplePrincipalCollection(userName, getName());
         super.clearCachedAuthorizationInfo(principals);
    } 
    
    /**
     * 清除所有用户授权信息缓存.
     */
    public void clearAllCachedAuthorizationInfo() {
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if (cache != null) {
            for (Object key : cache.keys()) {
                cache.remove(key);
            }
        }
    }
 
}
