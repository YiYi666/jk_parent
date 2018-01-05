package top.greathead.jk.web.cargo;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import top.greathead.jk.entity.Invoice;
import top.greathead.jk.service.InvoiceService;
import top.greathead.jk.utils.Pagination;
import top.greathead.jk.web.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;


@Controller("invoiceAction")
@Scope("prototype")
public class InvoiceAction extends BaseAction implements ModelDriven<Invoice>  {

    @Autowired
    private InvoiceService invoiceService;

    private Pagination page=new Pagination();

    private List<Invoice> invoiceList;

    private Invoice model= new Invoice();

    public String list(){

    Pagination pagination =  invoiceService.findPage(page);
    pagination.setUrl("invoiceAction_list");
    ActionContext.getContext().getValueStack().push(pagination);
    return "list";
    }

    public String tocreate(){
    invoiceList = invoiceService.findAll();
    //        ActionContext.getContext().getValueStack().set("invoiceList",invoiceList);
    return "tocreate";
    }

    public String insert(){
        invoiceService.save(model);
        return list();
    }

    public String toupdate(){
    Invoice invoice = invoiceService.findById(model.getId());
    super.push(invoice);

    invoiceList = invoiceService.findAll();
    invoiceList.remove(invoice);
    return "toupdate";
    }

    public String update(){
    invoiceService.update(model);
    return list();
    }

    /**
    * @return
    */
    public String delete(){
    String[] ids = model.getId().split(", ");
    for (String id:ids) {
    invoiceService.deleteById(model.getId());
    }

    return list();
    }

    public String toview()
    {
    Invoice invoice = invoiceService.findById(model.getId());
    push(invoice);
    return "toview";
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
