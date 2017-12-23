package top.greathead.jk.web.sysadmin;

import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import top.greathead.jk.entity.Role;
import top.greathead.jk.service.RoleService;
import top.greathead.jk.utils.Pagination;
import top.greathead.jk.web.BaseAction;

import java.util.List;

/**
 * @author coach tam
 * @date 2017/12/20
 */
@Controller("roleAction")
@Scope("prototype")
public class RoleAction extends BaseAction implements ModelDriven<Role>{

    private Pagination page = new Pagination();

    @Autowired
    private RoleService roleService;

    private List<Role> roleList;

    private Role model = new Role();

    public String insert(){
        roleService.insert(model);
        return "rlist";
    }

    public String list(){
        page = roleService.findByPage(page);
        page.setUrl("roleAction_list");
        push(page);
        return "list";
    }
    public String tocreate(){
        roleList = roleService.findAll();
        return "tocreate";
    }
    public String toupdate(){
        roleList = roleService.findAll();
        model = roleService.findById(model.getId());
        roleList.remove(model);
        push(model);
        return "toupdate";
    }
    public String update(){
        roleService.update(model);
        return "rlist";
    }

    public String delete(){
        roleService.delete(model.getId().split(", "));
        return "rlist";
    }

    public String toview(){
        model = roleService.findById(model.getId());
        push(model);
        return "toview";
    }

    public Pagination getPage() {
        return page;
    }

    public void setPage(Pagination page) {
        this.page = page;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    @Override
    public Role getModel() {
        return model;
    }
}
