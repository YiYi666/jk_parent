package top.greathead.jk.web.sysadmin;

import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import top.greathead.jk.entity.Dept;
import top.greathead.jk.entity.User;
import top.greathead.jk.service.DeptService;
import top.greathead.jk.service.UserService;
import top.greathead.jk.utils.Pagination;
import top.greathead.jk.web.BaseAction;

import java.util.List;


@Controller("userAction")
public class UserAction extends BaseAction implements ModelDriven<User> {

    @Autowired
    private UserService  userService;

    @Autowired
    private DeptService deptService;

    private Pagination page = new Pagination();
    private User model = new User();
    private List<Dept> deptList;
    private List<User> userList;

    public String list(){
        page = userService.findlist(page);
        page.setUrl("userAction_list");
        push(page);
        return "list";
    }

    public String toview(){
        model = userService.findById(model.getId());
        push(model);
        return "toview";
    }

    public String tocreate(){
        deptList = deptService.findAll();
        userList = userService.findAll();
        return "tocreate";
    }

    public void setPage(Pagination page) {
        this.page = page;
    }

    public Pagination getPage() {
        return page;
    }

    @Override
    public User getModel() {
        return model;
    }

    public List<Dept> getDeptList() {
        return deptList;
    }

    public List<User> getUserList() {
        return userList;
    }
}
