package top.greathead.jk.web.cargo;

import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import top.greathead.jk.entity.Factory;
import top.greathead.jk.service.FactoryService;
import top.greathead.jk.utils.Pagination;
import top.greathead.jk.web.BaseAction;

import java.util.List;

/**
 * @author coach tam
 * @date 2017/12/20
 */
@Controller("factoryAction")
@Scope("prototype")
public class FactoryAction extends BaseAction implements ModelDriven<Factory>{

    private Pagination page = new Pagination();

    @Autowired
    private FactoryService factoryService;

    private List<Factory> factoryList;

    private Factory model = new Factory();

    public String insert(){
        factoryService.insert(model);
        return "rlist";
    }

    public String list(){
        page = factoryService.findByPage(page);
        page.setUrl("factoryAction_list");
        push(page);
        return "list";
    }
    public String tocreate(){
        factoryList = factoryService.findAll();
        return "tocreate";
    }
    public String toupdate(){
        factoryList = factoryService.findAll();
        model = factoryService.findById(model.getId());
        factoryList.remove(model);
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

    public List<Factory> getFactoryList() {
        return factoryList;
    }

    @Override
    public Factory getModel() {
        return model;
    }
}
