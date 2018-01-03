package top.greathead.jk.web.cargo;

import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import top.greathead.jk.entity.PackingList;
import top.greathead.jk.service.ExportService;
import top.greathead.jk.service.PackingListService;
import top.greathead.jk.utils.Pagination;
import top.greathead.jk.web.BaseAction;

import java.util.List;

/**
 * @author coach tam
 * @date 2017/12/20
 */
@Controller("packingListAction")
@Scope("prototype")
public class PackingListAction extends BaseAction implements ModelDriven<PackingList>{

    private Pagination page = new Pagination();

    @Autowired
    private PackingListService packingListService;

    @Autowired
    private ExportService exportService;

    private List<PackingList> packingListList;

    private PackingList model = new PackingList();

    public String insert(){

        packingListService.insert(model);
        return list();
    }

    public String list(){
        page = packingListService.findByPage(page);
        page.setUrl("packingListAction_list");
        push(page);
        return "list";
    }
    public String tocreate(){
        Long state = 1L;
        page = exportService.findByPage(page,state);
        page.setUrl("packingListAction_tocreate");
        push(page);
        return "tocreate";
    }
    public String toupdate(){
       // packingListList = packingListService.findAll();
        model = packingListService.findById(model.getId());
       // packingListList.remove(model);
        push(model);
        return "toupdate";
    }
    public String update(){
        packingListService.update(model);
        return "rlist";
    }

    public String delete(){
        packingListService.delete(model.getId().split(", "));
        return "rlist";
    }

    public String toview(){
        model = packingListService.findById(model.getId());
        push(model);
        return "toview";
    }

    public String submit(){
        Long state = 1L;
        packingListService.updateState(model.getId(),state);
        return "rlist";
    }

    public Pagination getPage() {
        return page;
    }

    public void setPage(Pagination page) {
        this.page = page;
    }

    public List<PackingList> getPackingListList() {
        return packingListList;
    }

    @Override
    public PackingList getModel() {
        return model;
    }
}
