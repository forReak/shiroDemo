package com.furao.shiroProject.realm;

import com.furao.shiroProject.bean.User;
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

    {
        //设置当前自定义的realmName
        super.setName("CustomRealm");
    }

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String) authenticationToken.getPrincipal();
        String password = new User().getPassword();
        if(password==null){
            throw new UnknownAccountException("找不到用户名和密码");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userName,password,super.getName());
        info.setCredentialsSalt(ByteSource.Util.bytes(new User().getSalt()));
        return info;
    }

}
