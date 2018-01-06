package top.greathead.jk.web.cargo;

import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import top.greathead.jk.entity.Finance;
import top.greathead.jk.service.FinanceService;
import top.greathead.jk.service.InvoiceService;
import top.greathead.jk.service.ShippingOrderService;
import top.greathead.jk.utils.Pagination;
import top.greathead.jk.web.BaseAction;

import java.util.List;

/**
 * @author coach tam
 * @date 2017/12/20
 */
@Controller("financeAction")
@Scope("prototype")
public class FinanceAction extends BaseAction implements ModelDriven<Finance>{

    private Pagination page = new Pagination();

    @Autowired
    private FinanceService financeService;
    @Autowired
    private InvoiceService invoiceService;

    private List<Finance> financeList;

    private Finance model = new Finance();
    
    //TODO 状态问题
    public String insert(){
        financeService.insert(model);
        return "rlist";
    }

    public String list(){
        page = financeService.findByPage(page);
        page.setUrl("financeAction_list");
        push(page);
        return "list";
    }
    public String tocreate(){
        Long state = 1L;
        page = invoiceService.findByPage(page,state);
        page.setUrl("financeAction_tocreate");
        push(page);
        return "tocreate";
    }
    public String toupdate(){
        financeList = financeService.findAll();
        model = financeService.findById(model.getId());
        financeList.remove(model);
        push(model);
        return "toupdate";
    }
    public String update(){
        financeService.update(model);
        return "rlist";
    }

    public String delete(){
        financeService.delete(model.getId().split(", "));
        return "rlist";
    }

    public String toview(){
        model = financeService.findById(model.getId());
        push(model);
        return "toview";
    }

    public String submit(){
        Long state = 1L;
        financeService.updateState(model.getId(),state);
        return "rlist";
    }


    public Pagination getPage() {
        return page;
    }

    public void setPage(Pagination page) {
        this.page = page;
    }

    public List<Finance> getFinanceList() {
        return financeList;

    }

    @Override
    public Finance getModel() {
        return model;
    }
}
