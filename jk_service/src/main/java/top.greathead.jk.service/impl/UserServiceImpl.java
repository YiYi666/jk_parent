package top.greathead.jk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.greathead.jk.dao.BaseDao;
import top.greathead.jk.entity.User;
import top.greathead.jk.service.UserService;

import java.util.List;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private BaseDao<User,String> baseDao;

    @Override
    @Transactional
    public List<User> findlist() {
        List<User> list = baseDao.getListByHQL("from User");
        return list;
    }
}
