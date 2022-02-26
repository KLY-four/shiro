package com.example.demo.realm;

import com.example.demo.config.MyByteSource;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.RoleEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.vo.UserRoleVo;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    public UserDao userDao;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println(2222);
        final SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        final UserRoleVo roleVo = userDao.selectUserRoles((String) principalCollection.getPrimaryPrincipal());
        if(roleVo != null){
            final List<String> list = roleVo.getRoles().stream().map(RoleEntity::getRoleName).collect(Collectors.toList());
             authorizationInfo.addRoles(list);
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println(1111);
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        final UserEntity userEntity = userDao.selectUser((String) token.getPrincipal());
        if(userEntity != null){
            //这里使用自定义的ByteSource来处理盐
            return new SimpleAuthenticationInfo(userEntity.getUsername(),userEntity.getPassword(), new MyByteSource(userEntity.getSalt()),this.getName());
        }
        return null;
    }
}
