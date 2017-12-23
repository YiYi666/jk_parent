package top.greathead.jk.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import top.greathead.jk.utils.Encrypt;

public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {

        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String password = Encrypt.md5(new String(upToken.getPassword()), upToken.getUsername());
        SimpleAuthenticationInfo sInfo = (SimpleAuthenticationInfo)info;
        String dbPassword = (String) sInfo.getCredentials();

        return password.equals(dbPassword);
    }
}
