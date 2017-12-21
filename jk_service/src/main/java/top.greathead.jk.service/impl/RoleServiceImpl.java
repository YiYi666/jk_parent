package top.greathead.jk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.greathead.jk.dao.BaseDao;
import top.greathead.jk.entity.Role;
import top.greathead.jk.entity.Role;
import top.greathead.jk.entity.User;
import top.greathead.jk.service.RoleService;
import top.greathead.jk.utils.Pagination;

import java.util.Date;
import java.util.List;
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
        roleDao.save(model);
    }

    @Override
    public void update(Role model) {
        Role role = roleDao.get(Role.class, model.getId());
        role.setUpdateTime(new Date());
        role.setName(model.getName());
        role.setRemark(model.getRemark());
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

    
}
