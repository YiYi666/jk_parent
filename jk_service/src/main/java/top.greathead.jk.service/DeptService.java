package top.greathead.jk.service;

import top.greathead.jk.utils.Pagination;

public interface DeptService {
    Pagination findByPage(Pagination page);
}
