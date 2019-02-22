package com.furao.shiroProject.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {
    private Map<String,String> userName=new HashMap<>();
    private Map<String,HashSet<String>>userRoles=new HashMap<>();
    private Map<String, HashSet<String>>rolePermissions=new HashMap<>();

    {
        //this.userName.put("zhangsan","123456");明文时可以存123456，加密后需要存md5加密加盐后的值
        this.userName.put("zhangsan","61a310016ddcf3312aa004dc714f976f");
        this.userRoles.put("zhangsan",new HashSet<String>(){{add("admin");add("leader");}});
        this.rolePermissions.put("admin",new HashSet<String>(){{add("user:add");}});
        this.rolePermissions.put("leader",new HashSet<String>(){{add("user:delete");}});

        //设置当前自定义的realmName
        super.setName("CustomRealm");
    }

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String) authenticationToken.getCredentials();
        String password = getPasswordByUserName(userName);
        if(password==null){
            throw new UnknownAccountException("找不到用户名和密码");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userName,password,super.getName());
        info.setCredentialsSalt(ByteSource.Util.bytes("MARK"));
        return null;
    }

    //模拟从数据库中读取数据
    private String getPasswordByUserName(String userName){
        return this.userName.get(userName);
    }
    //模拟从数据库中读取数据
    private Set<String> getRolesByUserName(String userName){
        return this.userRoles.get(userName);
    }
    //模拟从数据库中读取数据
    private Set<String> getPermissionsByRoles(Set<String> roles){
        Set<String> permissions=new HashSet<>();
        roles.forEach(e->permissions.addAll(this.rolePermissions.get(e)));
        return permissions;
    }

}
