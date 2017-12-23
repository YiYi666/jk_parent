package top.greathead.jk.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import top.greathead.jk.entity.User;
import top.greathead.jk.utils.SysConstant;

@Controller("loginAction")
@Scope("prototype")
public class LoginAction extends ActionSupport {
	
	private String username;
	private String password;
	private String errorInfo = null;

	public String login(){
		if(null == username || null == password){
			return "login";
		}else {
			try {
				Subject subject = SecurityUtils.getSubject();
				UsernamePasswordToken token = new UsernamePasswordToken(username, password);
				subject.login(token);

				User user = (User) subject.getPrincipal();
				ServletActionContext.getRequest().getSession().setAttribute(SysConstant.C_USER,user);
				return "success";
			}catch (Exception e){
				errorInfo="用户名或密码错误！";
				e.printStackTrace();
				return "login";
			}

		}
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getErrorInfo() {
		return errorInfo;
	}
}
