package top.greathead.jk.web.baseinfo;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import top.greathead.jk.entity.Module;
import top.greathead.jk.service.ModuleService;
import top.greathead.jk.utils.Pagination;
import top.greathead.jk.web.BaseAction;

import java.util.List;

@Controller("systemInfoAction")
@Scope("prototype")
public class SystemInfoAction extends BaseAction implements ModelDriven<Module> {
    private Pagination page = new Pagination();

    @Autowired
    private ModuleService moduleService;

    private Module model = new Module();

    public String insert(){
        moduleService.insert(model);
        return "rlist";
    }

    public String list(){
        page = moduleService.findByPage(page,model.getId());
        page.setUrl("systemInfoAction_list");
        push(page);
        return "list";
    }
    public String tocreate(){
        List<Module> moduleList = moduleService.findParentModule();
        ActionContext.getContext().getValueStack().set("moduleList",moduleList);
        return "tocreate";
    }
    public String toupdate(){
        model = moduleService.findById(model.getId());
        push(model);
        List<Module> moduleList = moduleService.findParentModule();
        ActionContext.getContext().getValueStack().set("moduleList",moduleList);
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

    @Override
    public Module getModel() {
        return model;
    }

}
