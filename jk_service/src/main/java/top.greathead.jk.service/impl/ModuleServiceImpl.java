package top.greathead.jk.service.impl;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.greathead.jk.dao.BaseDao;
import top.greathead.jk.entity.Module;
import top.greathead.jk.entity.User;
import top.greathead.jk.service.ModuleService;
import top.greathead.jk.utils.Pagination;
import top.greathead.jk.utils.SysConstant;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private BaseDao<Module,String> moduleDao;
    @Override
    @Transactional(readOnly = true)
    public Pagination findByPage(Pagination page) {
        return moduleDao.pageByHql("from Module" ,page.getPageNo(),page.getPageSize());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Module> findAll() {
        return moduleDao.getListByHQL("from Module");
    }

    @Override
    public void insert(Module model) {
        Module module = moduleDao.getByHQL("from Module where name = ?",model.getParentName());
        model.setParentId(module.getId());
        User user = (User) ServletActionContext.getRequest().getSession().getAttribute(SysConstant.C_USER);
        model.setCreateBy(user.getId());
        model.setCreateDept(user.getDept().getId());
        moduleDao.save(model);
    }

    @Override
    public void update(Module model) {
        Module module = moduleDao.get(Module.class, model.getId());
        model.setUpdateTime(new Date());
        model.setCreateTime(module.getCreateTime());
        moduleDao.evict(module);
        Module module2 = moduleDao.getByHQL("from Module where name = ?",model.getParentName());
        model.setParentId(module2.getId());

        User user = (User) ServletActionContext.getRequest().getSession().getAttribute(SysConstant.C_USER);
        model.setUpdateBy(user.getId());
        model.setUpdateTime(new Date());

        moduleDao.update(model);
    }

    @Override
    @Transactional(readOnly = true)
    public Module findById(String id) {
        return moduleDao.get(Module.class,id);
    }

    @Override
    public void delete(String[] ids) {
        for (String id : ids) {
            moduleDao.deleteById(Module.class,id);
        }
    }
}
