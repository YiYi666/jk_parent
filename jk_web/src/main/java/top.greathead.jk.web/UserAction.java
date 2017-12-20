package top.greathead.jk.web;

import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import top.greathead.jk.entity.User;
import top.greathead.jk.service.UserService;

import java.util.List;

@Controller("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport {


    private List<User> list;

    @Autowired
    private UserService userService;


    public String execute(){
        list = userService.findlist();
        return "list";
    }

    public List<User> getList() {
        return list;
    }
}
