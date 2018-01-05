package top.greathead.jk.service.impl;

import top.greathead.jk.dao.BaseDao;
import top.greathead.jk.entity.Finance;
import top.greathead.jk.service.FinanceService;
import top.greathead.jk.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service
public class FinanceServiceImpl implements FinanceService {
    @Autowired
    private BaseDao<Finance,String> financeDao;

    @Override
    public Pagination findPage(Pagination page) {
    return financeDao.pageByHql("from Finance",page.getPageNo(),page.getPageSize(),null);
    }

    @Override
    public List<Finance> findAll() {
        return financeDao.getListByHQL("from Finance");
    }

    @Override
    public void save(Finance model) {
    financeDao.save(model);
    }

    @Override
    public void deleteById(String id) {
    financeDao.deleteById(Finance.class,id);
    }

    @Override
    public void update(Finance model) {
    financeDao.update(model);
    }

    @Override
    public Finance findById(String id) {
    return financeDao.get(Finance.class,id);
    }
}
