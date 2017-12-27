package top.greathead.jk.web.cargo;

import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import top.greathead.jk.entity.ExtCproduct;
import top.greathead.jk.entity.Factory;
import top.greathead.jk.service.ExtCproductService;
import top.greathead.jk.service.FactoryService;
import top.greathead.jk.utils.Pagination;
import top.greathead.jk.web.BaseAction;

import java.util.List;

/**
 * @author coach tam
 * @date 2017/12/20
 */
@Controller("extCproductAction")
@Scope("prototype")
public class ExtCproductAction extends BaseAction implements ModelDriven<ExtCproduct>{

    private Pagination page = new Pagination();

    @Autowired
    private ExtCproductService extCproductService;
    @Autowired
    private FactoryService factoryService;

    private List<ExtCproduct> extCproductList;
    private List<Factory> factoryList;

    private ExtCproduct model = new ExtCproduct();

    public String insert(){
        extCproductService.insert(model);
        return tocreate();
    }

/*    public String list(){
        page = extCproductService.findByPage(page);
        page.setUrl("extCproductAction_list");
        push(page);
        return "list";
    }*/
    public String tocreate(){
        //extCproductList = extCproductService.findAll();
        String type = "附件";
        factoryList = factoryService.findByType(type);
        page = extCproductService.findByPage(page,model.getContractProduct().getId());
        page.setUrl("extCproductAction_list");
        push(page);
        return "tocreate";
    }
    public String toupdate(){
        extCproductList = extCproductService.findAll();
        String type = "附件";
        factoryList = factoryService.findByType(type);
        model = extCproductService.findById(model.getId());
        extCproductList.remove(model);
        push(model);
        return "toupdate";
    }
    public String update(){
        extCproductService.update(model);
        return tocreate();
    }

    public String delete(){
        extCproductService.delete(model.getId());
        return tocreate();
    }

/*    public String toview(){
        model = extCproductService.findById(model.getId());
        push(model);
        return "toview";
    }*/

    public Pagination getPage() {
        return page;
    }

    public void setPage(Pagination page) {
        this.page = page;
    }

    public List<ExtCproduct> getExtCproductList() {
        return extCproductList;
    }

    @Override
    public ExtCproduct getModel() {
        return model;
    }

    public List<Factory> getFactoryList() {
        return factoryList;
    }
}
