package top.greathead.jk.web.sysadmin;

import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import top.greathead.jk.entity.Dept;
import top.greathead.jk.service.DeptService;
import top.greathead.jk.utils.Pagination;
import top.greathead.jk.web.BaseAction;

import java.util.List;

/**
 * @author coach tam
 * @date 2017/12/20
 */
@Controller("deptAction")
@Scope("prototype")
public class DeptAction extends BaseAction implements ModelDriven<Dept>{

    private Pagination page = new Pagination();

    @Autowired
    private DeptService deptService;

    private List<Dept> deptList;

    private Dept model = new Dept();

    public String insert(){
        deptService.insert(model);
        return "rlist";
    }

    public String list(){
        page = deptService.findByPage(page);
        page.setUrl("deptAction_list");
        push(page);
        return "list";
    }
    public String tocreate(){
        deptList = deptService.findAll();
        return "tocreate";
    }
    public String toupdate(){
        deptList = deptService.findAll();
        model = deptService.findById(model.getId());
        deptList.remove(model);
        push(model);
        return "toupdate";
    }
    public String update(){
        deptService.update(model);
        return "rlist";
    }

    public String delete(){
        deptService.delete(model.getId().split(", "));
        return "rlist";
    }

    public String toview(){
        model = deptService.findById(model.getId());
        push(model);
        return "toview";
    }

    public Pagination getPage() {
        return page;
    }

    public void setPage(Pagination page) {
        this.page = page;
    }

    public List<Dept> getDeptList() {
        return deptList;
    }

    @Override
    public Dept getModel() {
        return model;
    }
}
