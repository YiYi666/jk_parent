package top.greathead.jk.web.cargo;


import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import top.greathead.jk.entity.Invoice;
import top.greathead.jk.service.PackingListService;
import top.greathead.jk.service.InvoiceService;
import top.greathead.jk.service.ShippingOrderService;
import top.greathead.jk.utils.Pagination;
import top.greathead.jk.web.BaseAction;

import java.util.List;

/**
 * @author coach tam
 * @date 2017/12/20
 */
@Controller("invoiceAction")
@Scope("prototype")
public class InvoiceAction extends BaseAction implements ModelDriven<Invoice>{

    private Pagination page = new Pagination();

    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private ShippingOrderService shippingOrderService;

    private List<Invoice> invoiceList;

    private Invoice model = new Invoice();
    

    public String insert(){
        invoiceService.insert(model);
        return "rlist";
    }

    public String list(){
        page = invoiceService.findByPage(page);
        page.setUrl("invoiceAction_list");
        push(page);
        return "list";
    }
    public String tocreate(){
        Long state = 1L;
        page = shippingOrderService.findByPage(page,state);
        page.setUrl("invoiceAction_tocreate");
        push(page);
        return "tocreate";
    }
    public String toupdate(){
        invoiceList = invoiceService.findAll();
        model = invoiceService.findById(model.getId());
        invoiceList.remove(model);
        push(model);
        return "toupdate";
    }
    public String update(){
        invoiceService.update(model);
        return "rlist";
    }

    public String delete(){
        invoiceService.delete(model.getId().split(", "));
        return "rlist";
    }

    public String toview(){
        model = invoiceService.findById(model.getId());
        push(model);
        return "toview";
    }

    public String submit(){
        Long state = 1L;
        invoiceService.updateState(model.getId(),state);
        return "rlist";
    }


    public Pagination getPage() {
        return page;
    }

    public void setPage(Pagination page) {
        this.page = page;
    }

    public List<Invoice> getInvoiceList() {
        return invoiceList;

    }

    @Override
    public Invoice getModel() {
        return model;

    }
}
