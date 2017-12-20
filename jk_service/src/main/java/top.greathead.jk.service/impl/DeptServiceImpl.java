package top.greathead.jk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.greathead.jk.dao.BaseDao;
import top.greathead.jk.service.DeptService;
import top.greathead.jk.utils.Pagination;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private BaseDao deptDao;
    @Override
    @Transactional
    public Pagination findByPage(Pagination page) {
        return deptDao.pageByHql("from Dept" ,page.getPageNo(),page.getPageSize());
    }
}
