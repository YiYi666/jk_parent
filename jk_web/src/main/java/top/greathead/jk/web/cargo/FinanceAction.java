package top.greathead.jk.web.cargo;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import top.greathead.jk.entity.Finance;
import top.greathead.jk.service.FinanceService;
import top.greathead.jk.utils.Pagination;
import top.greathead.jk.web.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;


@Controller("financeAction")
@Scope("prototype")
public class FinanceAction extends BaseAction implements ModelDriven<Finance>  {

    @Autowired
    private FinanceService financeService;

    private Pagination page=new Pagination();

    private List<Finance> financeList;

    private Finance model= new Finance();

    public String list(){

    Pagination pagination =  financeService.findPage(page);
    pagination.setUrl("financeAction_list");
    ActionContext.getContext().getValueStack().push(pagination);
    return "list";
    }

    public String tocreate(){
    financeList = financeService.findAll();
    //        ActionContext.getContext().getValueStack().set("financeList",financeList);
    return "tocreate";
    }

    public String insert(){
        financeService.save(model);
        return list();
    }

    public String toupdate(){
    Finance finance = financeService.findById(model.getId());
    super.push(finance);

    financeList = financeService.findAll();
    financeList.remove(finance);
    return "toupdate";
    }

    public String update(){
    financeService.update(model);
    return list();
    }

    /**
    * @return
    */
    public String delete(){
    String[] ids = model.getId().split(", ");
    for (String id:ids) {
    financeService.deleteById(model.getId());
    }

    return list();
    }

    public String toview()
    {
    Finance finance = financeService.findById(model.getId());
    push(finance);
    return "toview";
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
