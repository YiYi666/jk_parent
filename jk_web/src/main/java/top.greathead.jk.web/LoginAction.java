package top.greathead.jk.web;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("loginAction")
@Scope("prototype")
public class LoginAction extends ActionSupport {
	

	public String login(){
		return "success";
	}

}
