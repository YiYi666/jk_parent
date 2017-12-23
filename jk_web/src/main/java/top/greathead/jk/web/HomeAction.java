package top.greathead.jk.web;

import org.springframework.stereotype.Controller;

@Controller("homeAction")
public class HomeAction extends BaseAction {
	
	private String moduleName;		//动态指定跳转的模块，在struts.xml中配置动态的result
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String fmain(){
		return "fmain";
	}
	public String title(){
		return "title";
	}
	//转向moduleName指向的模块
	public String tomain(){
		return "tomain";
	}
	public String toleft(){

		return "toleft";
	}
	
}
