package top.greathead.jk.web.sysadmin;

import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import top.greathead.jk.entity.Module;
import top.greathead.jk.entity.Role;
import top.greathead.jk.entity.ZtreeVO;
import top.greathead.jk.service.ModuleService;
import top.greathead.jk.service.RoleService;
import top.greathead.jk.utils.FastJsonUtil;
import top.greathead.jk.utils.Pagination;
import top.greathead.jk.web.BaseAction;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    @Autowired
    private ModuleService moduleService;

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
    public String tomodule(){
        model = roleService.findById(model.getId());
        push(model);
        return "tomodule";
    }
    public String getJsonStr() throws IOException {

        List<Module> modudlList = moduleService.findAll();
        Set<Module> moduleSet = roleService.findById(model.getId()).getModuleSet();
        ArrayList<ZtreeVO> ztreeVOList = new ArrayList<>();
        for(Module module : modudlList){
            ZtreeVO ztreeVO = new ZtreeVO();
            ztreeVO.setId(module.getId());
            ztreeVO.setPId(module.getParentId());
            ztreeVO.setName(module.getName());
            ztreeVO.setOpen(true);
            if(moduleSet.contains(module)){
                ztreeVO.setChecked(true);
            }
            ztreeVOList.add(ztreeVO);
        }

        String json = FastJsonUtil.toJSONString(ztreeVOList);
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(json);
        writer.flush();
        writer.close();
        return null;
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
