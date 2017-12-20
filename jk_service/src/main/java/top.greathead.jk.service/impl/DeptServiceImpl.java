package top.greathead.jk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.greathead.jk.dao.BaseDao;
import top.greathead.jk.entity.Dept;
import top.greathead.jk.service.DeptService;
import top.greathead.jk.utils.Pagination;

import java.util.List;

@Service
@Transactional
public class DeptServiceImpl implements DeptService {

    @Autowired
    private BaseDao<Dept,String> deptDao;
    @Override
    @Transactional(readOnly = true)
    public Pagination findByPage(Pagination page) {
        return deptDao.pageByHql("from Dept" ,page.getPageNo(),page.getPageSize());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Dept> findAll() {
        return deptDao.getListByHQL("from Dept");
    }

    @Override
    public void insert(Dept model) {
        deptDao.save(model);
    }

    @Override
    public void update(Dept model) {
        deptDao.update(model);
    }

    @Override
    @Transactional(readOnly = true)
    public Dept findById(String id) {
        return deptDao.get(Dept.class,id);
    }

    @Override
    public void delete(String[] ids) {
        for (String id : ids) {
            deptDao.deleteById(Dept.class,id);
        }
    }
}
