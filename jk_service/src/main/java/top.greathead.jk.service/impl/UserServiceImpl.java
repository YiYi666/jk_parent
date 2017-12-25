package top.greathead.jk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.greathead.jk.dao.BaseDao;
import top.greathead.jk.entity.Role;
import top.greathead.jk.entity.User;
import top.greathead.jk.service.RoleService;
import top.greathead.jk.service.UserService;
import top.greathead.jk.utils.Pagination;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private BaseDao<User,String> userDao;

    @Autowired
    private RoleService roleService;

    @Override
    @Transactional(readOnly = true)
    public Pagination findlist(Pagination page, User user) {
        String hql = "from User where 1=1";
        if(user.getUserInfo().getDegree()==2){
            hql = hql + " and create_dept = '" + user.getDept().getDeptName() +"'";
        }else if(user.getUserInfo().getDegree()==3){
            hql = hql + " and user_name = '" + user.getUserName() +"'";
        }
        page = userDao.pageByHql(hql, page.getPageNo(), page.getPageSize());
        return page;
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(String id) {
        return userDao.get(User.class,id);
    }

    @Override
    public void insert(User model) {
        model.getUserInfo().setUser(model);
        userDao.save(model);
    }

    @Override
    @Transactional
    public void update(User model) {
        User user = userDao.get(User.class, model.getId());
        user.setDept(model.getDept());
        user.setUserName(model.getUserName());
        user.setState(model.getState());
        user.setUpdateTime(new Date());
        userDao.update(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userDao.getListByHQL("from User");
    }

    @Override
    public void delete(String[] ids) {
        for (String id:ids) {
            userDao.deleteById(User.class,id);
        }
    }

    @Override
    public void updateRoles(String id, String[] roleIds) {
        User user = userDao.get(User.class, id);
        Set<Role> roleSet = new HashSet<Role>();
        for (String roleId:roleIds ) {
            Role role = roleService.findRoleById(roleId);
            roleSet.add(role);
        }
        user.setRoleSet(roleSet);
        userDao.update(user);
    }

    @Override
    public User findUserByUserName(String username) {
        return  userDao.getByHQL("from User where user_name = ?", username);
    }
}
