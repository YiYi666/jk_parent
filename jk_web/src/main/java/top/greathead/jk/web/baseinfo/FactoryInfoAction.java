package top.greathead.jk.web.baseinfo;

import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import top.greathead.jk.entity.Factory;
import top.greathead.jk.entity.Factory;
import top.greathead.jk.service.FactoryService;
import top.greathead.jk.utils.Pagination;
import top.greathead.jk.web.BaseAction;

import java.util.List;
@Controller("factoryInfoAction")
@Scope("prototype")
public class FactoryInfoAction extends BaseAction implements ModelDriven<Factory> {
    private Pagination page = new Pagination();

    @Autowired
    private FactoryService factoryService;

    private Factory model = new Factory();

    public String insert(){
        factoryService.insert(model);
        return "rlist";
    }

    public String list(){
        page = factoryService.findByPage(page);
        page.setUrl("factoryInfoAction_list");
        push(page);
        return "list";
    }
    public String tocreate(){
        return "tocreate";
    }
    public String toupdate(){
        model = factoryService.findById(model.getId());
        push(model);
        return "toupdate";
    }
    public String update(){
        factoryService.update(model);
        return "rlist";
    }

    public String delete(){
        factoryService.delete(model.getId().split(", "));
        return "rlist";
    }

    public String toview(){
        model = factoryService.findById(model.getId());
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
    public Factory getModel() {
        return model;
    }
}
