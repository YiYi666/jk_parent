package top.greathead.jk.web.cargo;

import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import top.greathead.jk.entity.ShippingOrder;
import top.greathead.jk.service.ShippingOrderService;
import top.greathead.jk.utils.Pagination;
import top.greathead.jk.web.BaseAction;

import java.util.List;

/**
 * @author coach tam
 * @date 2017/12/20
 */
@Controller("shippingOrderAction")
@Scope("prototype")
public class ShippingOrderAction extends BaseAction implements ModelDriven<ShippingOrder>{

    private Pagination page = new Pagination();

    @Autowired
    private ShippingOrderService shippingOrderService;

    private List<ShippingOrder> shippingOrderList;

    private ShippingOrder model = new ShippingOrder();

    public String insert(){
        shippingOrderService.insert(model);
        return "rlist";
    }

    public String list(){
        page = shippingOrderService.findByPage(page);
        page.setUrl("shippingOrderAction_list");
        push(page);
        return "list";
    }
    public String tocreate(){
        shippingOrderList = shippingOrderService.findAll();
        return "tocreate";
    }
    public String toupdate(){
        shippingOrderList = shippingOrderService.findAll();
        model = shippingOrderService.findById(model.getId());
        shippingOrderList.remove(model);
        push(model);
        return "toupdate";
    }
    public String update(){
        shippingOrderService.update(model);
        return "rlist";
    }

    public String delete(){
        shippingOrderService.delete(model.getId().split(", "));
        return "rlist";
    }

    public String toview(){
        model = shippingOrderService.findById(model.getId());
        push(model);
        return "toview";
    }

    public String submit(){
        Long state = 1L;
        shippingOrderService.updateState(model.getId(),state);
        return "rlist";
    }


    public Pagination getPage() {
        return page;
    }

    public void setPage(Pagination page) {
        this.page = page;
    }

    public List<ShippingOrder> getShippingOrderList() {
        return shippingOrderList;
    }

    @Override
    public ShippingOrder getModel() {
        return model;
    }
}
