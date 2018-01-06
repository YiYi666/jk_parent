package top.greathead.jk.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import top.greathead.jk.entity.LoginLog;
import top.greathead.jk.entity.User;
import top.greathead.jk.service.UserService;
import top.greathead.jk.utils.SysConstant;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller("loginAction")
@Scope("prototype")
public class LoginAction extends ActionSupport {
	
	private String username;
	private String password;
	private String errorInfo = null;
	@Resource(name = "userService")
	private UserService userService;

	public String login(){
		if(null == username || null == password){
			return "login";
		}else {
			try {
				Subject subject = SecurityUtils.getSubject();
				UsernamePasswordToken token = new UsernamePasswordToken(username, password);
				subject.login(token);

				User user = (User) subject.getPrincipal();

				HttpServletRequest request = ServletActionContext.getRequest();
				request.getSession().setAttribute(SysConstant.C_USER,user);
				userService.recordLoginLog(user.getUserName(),getIpAddr(request));

				ServletActionContext.getRequest().getSession().setAttribute(SysConstant.C_USER,user);

				return "success";
			}catch (Exception e){
				errorInfo="用户名或密码错误！";
				HttpServletRequest request = ServletActionContext.getRequest();
				userService.recordLoginLog("anonymous",getIpAddr(request));
				e.printStackTrace();
				return "login";
			}

		}
	}

	/**
	 * 获取登录用户IP地址
	 *
	 * @param request
	 * @return
	 */
	private String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip.equals("0:0:0:0:0:0:0:1")) {
			ip = "127.0.0.1";
		}
		return ip;
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
