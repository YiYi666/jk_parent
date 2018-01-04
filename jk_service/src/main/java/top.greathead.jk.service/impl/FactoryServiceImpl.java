package top.greathead.jk.service.impl;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
    @Autowired
    private SessionFactory sessionFactory;

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

    @Override
    public List<Object[]> findFactorySale() {
        Session session = sessionFactory.openSession();

        SQLQuery query = session.createSQLQuery("SELECT FACTORY_NAME,sum(CNUMBER) FROM CONTRACT_PRODUCT_C GROUP BY FACTORY_NAME");
        List list = query.list();

        session.close();
        return list;
    }

    @Override
    public List<Object[]> findProductSale() {
        Session session = sessionFactory.openSession();

        SQLQuery query = session.createSQLQuery("SELECT product_no,sales from(\n" +
                "select p.* , rownum from (\n" +
                "  select product_no , sum(cnumber) sales from CONTRACT_PRODUCT_C GROUP BY product_no order by sum(cnumber) \n" +
                ") p where rownum<11)");
        List list = query.list();
        session.close();
        return list;
    }
}
