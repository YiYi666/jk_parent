package top.greathead.jk.web.cargo;

import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import top.greathead.jk.entity.Contract;
import top.greathead.jk.service.ContractService;
import top.greathead.jk.utils.Pagination;
import top.greathead.jk.web.BaseAction;

import java.util.List;

/**
 * @author coach tam
 * @date 2017/12/20
 */
@Controller("contractAction")
@Scope("prototype")
public class ContractAction extends BaseAction implements ModelDriven<Contract>{

    private Pagination page = new Pagination();

    @Autowired
    private ContractService contractService;

    private List<Contract> contractList;

    private Contract model = new Contract();

    public String insert(){
        contractService.insert(model);
        return "rlist";
    }

    public String list(){
        page = contractService.findByPage(page);
        page.setUrl("contractAction_list");
        push(page);
        return "list";
    }
    public String tocreate(){
        contractList = contractService.findAll();
        return "tocreate";
    }
    public String toupdate(){
        contractList = contractService.findAll();
        model = contractService.findById(model.getId());
        contractList.remove(model);
        push(model);
        return "toupdate";
    }
    public String update(){
        contractService.update(model);
        return "rlist";
    }

    public String delete(){
        contractService.delete(model.getId().split(", "));
        return "rlist";
    }

    public String toview(){
        model = contractService.findById(model.getId());
        push(model);
        return "toview";
    }

    public String submit(){
        Long state = 1L;
        contractService.updateState(model.getId(),state);
        return "rlist";
    }

    public Pagination getPage() {
        return page;
    }

    public void setPage(Pagination page) {
        this.page = page;
    }

    public List<Contract> getContractList() {
        return contractList;
    }

    @Override
    public Contract getModel() {
        return model;
    }
}
