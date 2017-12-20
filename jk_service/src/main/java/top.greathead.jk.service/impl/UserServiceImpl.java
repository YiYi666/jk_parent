package top.greathead.jk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.greathead.jk.dao.BaseDao;
import top.greathead.jk.entity.User;
import top.greathead.jk.service.UserService;
import top.greathead.jk.utils.Pagination;

import java.util.List;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private BaseDao<User,String> userDao;

    @Override
    @Transactional
    public Pagination findlist(Pagination page) {
        page = userDao.pageByHql("from User", page.getPageNo(), page.getPageSize());
        return page;
    }

    @Override
    public User findById(String id) {
        return userDao.get(User.class,id);
    }

    @Override
    public List<User> findAll() {
        return userDao.getListByHQL("from User");
    }
}
