package top.greathead.jk.service.impl;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.greathead.jk.dao.BaseDao;
import top.greathead.jk.entity.Module;
import top.greathead.jk.entity.Role;
import top.greathead.jk.entity.User;
import top.greathead.jk.service.RoleService;
import top.greathead.jk.utils.Pagination;
import top.greathead.jk.utils.SysConstant;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private BaseDao<Role,String> roleDao;

    @Override
    @Transactional(readOnly = true)
    public List<Role> findAll() {
        return roleDao.getListByHQL("from Role");
    }

    @Override
    @Transactional(readOnly = true)
    public Role findRoleById(String roleId) {
        return roleDao.get(Role.class,roleId);
    }

    @Override
    @Transactional(readOnly = true)
    public Pagination findByPage(Pagination page) {
        return roleDao.pageByHql("from Role" ,page.getPageNo(),page.getPageSize());
    }

    @Override
    public void insert(Role model) {
        User user = (User) ServletActionContext.getRequest().getSession().getAttribute(SysConstant.C_USER);
        model.setCreateBy(user.getId());
        model.setCreateDept(user.getDept().getId());
        roleDao.save(model);
    }

    @Override
    public void update(Role model) {
        Role role = roleDao.get(Role.class, model.getId());
        role.setUpdateTime(new Date());
        role.setName(model.getName());
        role.setRemark(model.getRemark());

        User user = (User) ServletActionContext.getRequest().getSession().getAttribute(SysConstant.C_USER);
        role.setUpdateBy(user.getId());
        role.setUpdateTime(new Date());

        roleDao.update(role);
    }

    @Override
    @Transactional(readOnly = true)
    public Role findById(String id) {
        return roleDao.get(Role.class,id);
    }

    @Override
    public void delete(String[] ids) {
        for (String id : ids) {
            roleDao.deleteById(Role.class,id);
        }
    }

    @Override
    public void updateModule(String id, String moduleIds) {
        Role role = roleDao.get(Role.class, id);
        Set<Module> moduleSet = new HashSet<>();
        String[] split = moduleIds.split(",");
        if(!moduleIds.isEmpty() || moduleIds == null){
            for(String moduleId: split){
                Module module = new Module();
                module.setId(moduleId);
                moduleSet.add(module);
            }
        }
        role.setModuleSet(moduleSet);
        roleDao.update(role);
    }
}
