package top.greathead.jk.web.cargo;

import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import top.greathead.jk.entity.ContractProduct;
import top.greathead.jk.entity.Factory;
import top.greathead.jk.service.ContractProductService;
import top.greathead.jk.service.FactoryService;
import top.greathead.jk.utils.Pagination;
import top.greathead.jk.web.BaseAction;

import java.util.List;

/**
 * @author coach tam
 * @date 2017/12/20
 */
@Controller("contractProductAction")
@Scope("prototype")
public class ContractProductAction extends BaseAction implements ModelDriven<ContractProduct>{

    private Pagination page = new Pagination();

    @Autowired
    private ContractProductService contractProductService;
    @Autowired
    private FactoryService factoryService;

    private List<ContractProduct> contractProductList;
    private List<Factory> factoryList;

    private ContractProduct model = new ContractProduct();

    public String insert(){
        contractProductService.insert(model);
        return tocreate();
    }

    public String list(){
        page = contractProductService.findByPage(page, model.getContract().getId());
        page.setUrl("contractProductAction_list");
        push(page);
        return "list";
    }
    public String tocreate(){
        String type = "货物";
        factoryList = factoryService.findByType(type);
        page = contractProductService.findByPage(page,model.getContract().getId());
        page.setUrl("contractProductAction_tocreate");
        push(page);
        return "tocreate";
    }
    public String toupdate(){
        String type = "货物";
        factoryList = factoryService.findByType(type);
        contractProductList = contractProductService.findAll();
        model = contractProductService.findById(model.getId());
        contractProductList.remove(model);
        push(model);
        return "toupdate";
    }
    public String update(){
        contractProductService.update(model);
        return tocreate();
    }

    public String delete(){
        contractProductService.delete(model.getId().split(", "));
        return tocreate();
    }

    public String toview(){
        model = contractProductService.findById(model.getId());
        push(model);
        return "toview";
    }

    public Pagination getPage() {
        return page;
    }

    public void setPage(Pagination page) {
        this.page = page;
    }

    public List<ContractProduct> getContractProductList() {
        return contractProductList;
    }

    public List<Factory> getFactoryList() {
        return factoryList;
    }

    @Override
    public ContractProduct getModel() {
        return model;
    }
}
