package top.greathead.jk.web.cargo;

import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import top.greathead.jk.entity.Export;
import top.greathead.jk.service.ContractService;
import top.greathead.jk.service.ExportService;
import top.greathead.jk.utils.Pagination;
import top.greathead.jk.web.BaseAction;

import java.util.List;

/**
 * @author coach tam
 * @date 2017/12/20
 */
@Controller("exportAction")
@Scope("prototype")
public class ExportAction extends BaseAction implements ModelDriven<Export>{

    private Pagination page = new Pagination();

    @Autowired
    private ExportService exportService;
    @Autowired
    private ContractService contractService;

    private List<Export> exportList;

    private Export model = new Export();

    public String contractList(){
        Long state = 1L;
        page = contractService.findByPage(page,state);
        page.setUrl("exportAction_contractList");
        push(page);
        return "contractList";
    }

    public String tocreate(){
        return "tocreate";
    }
    public String insert(){
        exportService.insert(model);
        return contractList();
    }

    public String list(){
        page = exportService.findByPage(page);
        page.setUrl("exportAction_list");
        push(page);
        return "list";
    }

    public String toupdate(){
       // exportList = exportService.findAll();
        model = exportService.findById(model.getId());
       // exportList.remove(model);
       push(model);
        return "toupdate";
    }
    public String update(){
        exportService.update(model);
        return list();
    }

    public String delete(){
        exportService.delete(model.getId().split(", "));
        return list();
    }

    public String toview(){
        model = exportService.findById(model.getId());
        push(model);
        return "toview";
    }

    public String submit(){
        Long exportState = 1L;
        Long contractState = 2L;
        exportService.updateState(model,exportState,contractState);
        return list();
    }

    public String cancel(){
        Long exportState = 0L;
        Long contractState = 1L;
        exportService.updateState(model,exportState,contractState);
        return list();
    }

    public Pagination getPage() {
        return page;
    }

    public void setPage(Pagination page) {
        this.page = page;
    }

    public List<Export> getExportList() {
        return exportList;
    }

    @Override
    public Export getModel() {
        return model;
    }
}
