package top.greathead.jk.service.impl;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.greathead.jk.dao.BaseDao;
import top.greathead.jk.entity.Finance;
import top.greathead.jk.entity.PackingList;
import top.greathead.jk.entity.User;
import top.greathead.jk.service.FinanceService;
import top.greathead.jk.utils.Pagination;
import top.greathead.jk.utils.SysConstant;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class FinanceServiceImpl implements FinanceService {

    @Autowired
    private BaseDao<Finance,String> financeDao;
    @Autowired
    private BaseDao<PackingList,String> packingListDao;

    @Override
    @Transactional(readOnly = true)
    public Pagination findByPage(Pagination page) {
        return financeDao.pageByHql("from Finance" ,page.getPageNo(),page.getPageSize());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Finance> findAll() {
        return financeDao.getListByHQL("from Finance");
    }

    @Override
    public void insert(Finance model) {
        Long state = 0L;
        model.setState(state);
        model.setCreateTime(new Date());
        PackingList packingList = packingListDao.get(PackingList.class, model.getId());
        model.setPackingList(packingList);
        model.setId(null);

        User user = (User) ServletActionContext.getRequest().getSession().getAttribute(SysConstant.C_USER);
        model.setCreateBy(user.getId());
        model.setCreateDept(user.getDept().getId());

        financeDao.save(model);
    }

    @Override
    public void update(Finance model) {
        Finance oldModel = financeDao.get(Finance.class, model.getId());
        model.setCreateTime(oldModel.getCreateTime());
        model.setState(oldModel.getState());
        financeDao.evict(oldModel);
        financeDao.update(model);
    }

    @Override
    @Transactional(readOnly = true)
    public Finance findById(String id) {
        return financeDao.get(Finance.class,id);
    }

    @Override
    public void delete(String[] ids) {
        for (String id : ids) {
            financeDao.deleteById(Finance.class,id);
        }
    }

    @Override
    public void updateState(String id, Long state) {
        Finance finance = financeDao.get(Finance.class, id);
        finance.setState(state);

        financeDao.update(finance);
    }

    @Override
    public Pagination findByPage(Pagination page, Long state) {
        return financeDao.pageByHql("from Finance where state = ?" ,page.getPageNo(),page.getPageSize() , state);
    }

    @Override
    public List<Finance> findListbyDeliveryPeriod(String now) {
        return financeDao.getListByHQL("from Finance where state = 2 and to_char(deliveryPeriod,'yyyy-mm-dd')=?", now);
    }
}
