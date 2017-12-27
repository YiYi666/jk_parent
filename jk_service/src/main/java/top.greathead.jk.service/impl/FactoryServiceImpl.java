package top.greathead.jk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.greathead.jk.dao.BaseDao;
import top.greathead.jk.entity.Factory;
import top.greathead.jk.service.FactoryService;
import top.greathead.jk.utils.Pagination;

import java.util.List;

@Service
@Transactional
public class FactoryServiceImpl implements FactoryService {

    @Autowired
    private BaseDao<Factory,String> factoryDao;
    @Override
    @Transactional(readOnly = true)
    public Pagination findByPage(Pagination page) {
        return factoryDao.pageByHql("from Factory" ,page.getPageNo(),page.getPageSize());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Factory> findAll() {
        return factoryDao.getListByHQL("from Factory");
    }

    @Override
    public void insert(Factory model) {
        factoryDao.save(model);
    }

    @Override
    public void update(Factory model) {
        factoryDao.update(model);
    }

    @Override
    @Transactional(readOnly = true)
    public Factory findById(String id) {
        return factoryDao.get(Factory.class,id);
    }

    @Override
    public void delete(String[] ids) {
        for (String id : ids) {
            factoryDao.deleteById(Factory.class,id);
        }
    }

    @Override
    public List<Factory> findByType(String type) {
        return factoryDao.getListByHQL("from Factory where ctype = ?" , type);
    }
}
