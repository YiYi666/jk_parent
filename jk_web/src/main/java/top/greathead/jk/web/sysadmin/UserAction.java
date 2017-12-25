package top.greathead.jk.web.sysadmin;

import com.opensymphony.xwork2.ModelDriven;
import org.apache.shiro.web.filter.mgt.DefaultFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import top.greathead.jk.entity.Dept;
import top.greathead.jk.entity.Role;
import top.greathead.jk.entity.User;
import top.greathead.jk.service.DeptService;
import top.greathead.jk.service.RoleService;
import top.greathead.jk.service.UserService;
import top.greathead.jk.utils.Pagination;
import top.greathead.jk.web.BaseAction;

import java.util.List;
import java.util.Set;

import static org.apache.shiro.web.filter.mgt.DefaultFilter.user;


@Controller("userAction")
@Scope("prototype")
public class UserAction extends BaseAction implements ModelDriven<User> {

    @Autowired
    private UserService  userService;

    @Autowired
    private DeptService deptService;

    @Autowired
    private RoleService roleService;

    private Pagination page = new Pagination();
    private User model = new User();
    private List<Dept> deptList;
    private List<User> userList;
    private String userRoleStr;
    private List<Role> roleList;
    private String[] roleIds;

    public String list(){
        User user = getUser();
        page = userService.findlist(page, user);
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

    public String insert(){
        userService.insert(model);
        return "rlist";
    }

    public String toupdate(){
        deptList = deptService.findAll();
        model = userService.findById(model.getId());
        deptList.remove(model);
        push(model);
        return "toupdate";
    }

    public String update(){
        userService.update(model);
        return "rlist";
    }

    public String torole(){
        model = userService.findById(model.getId());
        roleList = roleService.findAll();
        Set<Role> roleSet = model.getRoleSet();
        for (Role role: roleSet) {
            userRoleStr = userRoleStr +role.getName()+",";
        }
        push(model);
        return "torole";
    }

    public String role(){
        userService.updateRoles(model.getId(),roleIds);
        return "rlist";
    }

    public String delete(){
        userService.delete(model.getId().split(", "));
        return "rlist";
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

    public String getUserRoleStr() {
        return userRoleStr;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleIds(String[] roleIds) {
        this.roleIds = roleIds;
    }
}
