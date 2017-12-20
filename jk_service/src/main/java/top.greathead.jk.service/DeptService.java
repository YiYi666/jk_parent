package top.greathead.jk.service;

import top.greathead.jk.entity.Dept;
import top.greathead.jk.utils.Pagination;

import java.util.List;

public interface DeptService {
    Pagination findByPage(Pagination page);

    List<Dept> findAll();
}
