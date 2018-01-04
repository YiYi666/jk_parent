package top.greathead.jk.service;


import top.greathead.jk.entity.User;
import top.greathead.jk.utils.Pagination;

import java.util.List;

public interface UserService {
    Pagination findlist(Pagination page, User user);

    User findById(String id);

    List<User> findAll();

    void insert(User model);

    void update(User model);

    void delete(String[] ids);

    void updateRoles(String id, String[] roleIds);

    User findUserByUserName(String username);

    List<Object[]> findOnlineInfo();
}
