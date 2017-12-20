package top.greathead.jk.web.sysadmin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import top.greathead.jk.service.DeptService;
import top.greathead.jk.utils.Pagination;
import top.greathead.jk.web.BaseAction;

/**
 * @author coach tam
 * @date 2017/12/20
 */
@Controller("deptAction")
public class DeptAction extends BaseAction {

    private Pagination page = new Pagination();

    @Autowired
   private DeptService deptService;

    public String list(){
        page = deptService.findByPage(page);
        page.setUrl("deptAction_list");
        push(page);
        return "list";
    }

    public Pagination getPage() {
        return page;
    }

    public void setPage(Pagination page) {
        this.page = page;
    }
}
