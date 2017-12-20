package top.greathead.jk.service;

import top.greathead.jk.entity.Dept;
import top.greathead.jk.utils.Pagination;

import java.util.List;

public interface DeptService {
    Pagination findByPage(Pagination page);

    List<Dept> findAll();

    void insert(Dept model);

    Dept findById(String id);

    void update(Dept model);

    void delete(String[] ids);
}
