package top.greathead.jk.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import top.greathead.jk.entity.Role;
import top.greathead.jk.entity.User;
import top.greathead.jk.service.UserService;


public class AuthRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        User user = (User) principalCollection.getPrimaryPrincipal();
        for (Role role: user.getRoleSet() ) {
            role.getModuleSet().forEach(module -> info.addStringPermission(module.getCpermission()));
        }
        return info;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken upToken = (UsernamePasswordToken)authenticationToken;
        String username = upToken.getUsername();
        User user = userService.findUserByUserName(username);
        //未防止 no session，将延迟加载的数据加载出来
        user.getDept().getDeptName();
        user.getUserInfo().getName();

        for (Role role: user.getRoleSet()) {
            role.getModuleSet().forEach(module -> module.getName());
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());
        return info;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
