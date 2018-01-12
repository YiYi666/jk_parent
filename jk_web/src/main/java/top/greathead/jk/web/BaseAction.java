package top.greathead.jk.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import org.springframework.context.annotation.Scope;
import top.greathead.jk.entity.User;
import top.greathead.jk.utils.SysConstant;

import javax.servlet.http.HttpServletResponse;


//通过RequestAware, SessionAware, ApplicationAware实行接口获得request,session,application对象，action中就可直接调用


@Scope("prototype")
public  class BaseAction extends ActionSupport implements  RequestAware, SessionAware, ApplicationAware{
	private static Logger log = Logger.getLogger(BaseAction.class);
	
	private static final long serialVersionUID = 1L;

	protected Map<String, Object> request;
	protected Map<String, Object> session;
	protected Map<String, Object> application;

	public Map<String, Object> getRequest() {
		return request;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public Map<String, Object> getApplication() {
		return application;
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}


	/**
	 * 将对象放入值栈栈顶-->方便取值
	 * @param o
	 */
	protected  void push(Object o)
	{
		ActionContext.getContext().getValueStack().push(o);
	}


	protected User getUser(){
		return (User) ServletActionContext.getRequest().getSession().getAttribute(SysConstant.C_USER);
	}

	public void printJS(String msg){
		PrintWriter writer =null;
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		writer.write(msg);
		writer.flush();
		writer.close();
	}



}
