package top.greathead.jk.service;

import top.greathead.jk.entity.Module;
import top.greathead.jk.utils.Pagination;

import java.util.List;

public interface ModuleService {
    void insert(Module model);

    Pagination findByPage(Pagination page);

    List<Module> findAll();

    Module findById(String id);

    void update(Module model);

    void delete(String[] split);

    Pagination findByPage(Pagination page, String parentId);

    List<Module> findParentModule();
}
