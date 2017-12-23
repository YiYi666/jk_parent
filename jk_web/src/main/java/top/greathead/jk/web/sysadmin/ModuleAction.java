package top.greathead.jk.web.sysadmin;

import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import top.greathead.jk.entity.Module;
import top.greathead.jk.service.ModuleService;
import top.greathead.jk.utils.Pagination;
import top.greathead.jk.web.BaseAction;

import java.util.List;

/**
 * @author coach tam
 * @date 2017/12/20
 */
@Controller("moduleAction")
@Scope("prototype")
public class ModuleAction extends BaseAction implements ModelDriven<Module>{

    private Pagination page = new Pagination();

    @Autowired
    private ModuleService moduleService;

    private List<Module> moduleList;

    private Module model = new Module();

    public String insert(){
        moduleService.insert(model);
        return "rlist";
    }

    public String list(){
        page = moduleService.findByPage(page);
        page.setUrl("moduleAction_list");
        push(page);
        return "list";
    }
    public String tocreate(){
        moduleList = moduleService.findAll();
        return "tocreate";
    }
    public String toupdate(){
        moduleList = moduleService.findAll();
        model = moduleService.findById(model.getId());
        moduleList.remove(model);
        push(model);
        return "toupdate";
    }
    public String update(){
        moduleService.update(model);
        return "rlist";
    }

    public String delete(){
        moduleService.delete(model.getId().split(", "));
        return "rlist";
    }

    public String toview(){
        model = moduleService.findById(model.getId());
        push(model);
        return "toview";
    }

    public Pagination getPage() {
        return page;
    }

    public void setPage(Pagination page) {
        this.page = page;
    }

    public List<Module> getModuleList() {
        return moduleList;
    }

    @Override
    public Module getModel() {
        return model;
    }
}
