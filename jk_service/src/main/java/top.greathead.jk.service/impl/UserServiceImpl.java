package top.greathead.jk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.greathead.jk.dao.BaseDao;
import top.greathead.jk.entity.User;
import top.greathead.jk.service.UserService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BaseDao baseDao;


    @Override
    public List<User> findlist() {
        List<User> list = baseDao.getListByHQL("from User");
        return list;
    }
}
