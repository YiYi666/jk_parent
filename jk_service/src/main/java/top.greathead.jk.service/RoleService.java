package top.greathead.jk.service;

import top.greathead.jk.entity.Role;
import top.greathead.jk.utils.Pagination;

import java.util.List;

public interface RoleService {
    List<Role> findAll();

    Role findRoleById(String roleId);

    void insert(Role model);

    Pagination findByPage(Pagination page);

    Role findById(String id);

    void update(Role model);

    void delete(String[] split);

    void updateModule(String id, String moduleIds);
}
