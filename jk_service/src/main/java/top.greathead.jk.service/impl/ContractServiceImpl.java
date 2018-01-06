package top.greathead.jk.service.impl;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.greathead.jk.dao.BaseDao;
import top.greathead.jk.entity.Contract;
import top.greathead.jk.entity.User;
import top.greathead.jk.service.ContractService;
import top.greathead.jk.utils.Pagination;
import top.greathead.jk.utils.SysConstant;

import java.util.Date;
import java.util.List;

import static oracle.net.aso.C01.l;
import static oracle.net.aso.C01.s;

@Service
@Transactional
public class ContractServiceImpl implements ContractService {

    @Autowired
    private BaseDao<Contract,String> contractDao;
    @Override
    @Transactional(readOnly = true)
    public Pagination findByPage(Pagination page) {
        return contractDao.pageByHql("from Contract" ,page.getPageNo(),page.getPageSize());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contract> findAll() {
        return contractDao.getListByHQL("from Contract");
    }

    @Override
    public void insert(Contract model) {
        Long totalAmount = 0L;
        Long state = 0L;

        model.setTotalAmount(totalAmount);
        model.setState(state);

        User user = (User) ServletActionContext.getRequest().getSession().getAttribute(SysConstant.C_USER);
        model.setCreateBy(user.getId());
        model.setCreateDept(user.getDept().getId());

        contractDao.save(model);
    }

    @Override
    public void update(Contract model) {
        Contract oldModel = contractDao.get(Contract.class, model.getId());
        model.setCreateTime(oldModel.getCreateTime());

        User user = (User) ServletActionContext.getRequest().getSession().getAttribute(SysConstant.C_USER);
        model.setUpdateBy(user.getId());
        model.setUpdateTime(new Date());
        model.setState(oldModel.getState());
        contractDao.evict(oldModel);
        contractDao.update(model);
    }

    @Override
    @Transactional(readOnly = true)
    public Contract findById(String id) {
        return contractDao.get(Contract.class,id);
    }

    @Override
    public void delete(String[] ids) {
        for (String id : ids) {
            contractDao.deleteById(Contract.class,id);
        }
    }

    @Override
    public void updateState(String id, Long state) {
        Contract contract = contractDao.get(Contract.class, id);
        contract.setState(state);

        contractDao.update(contract);
    }

    @Override
    public Pagination findByPage(Pagination page, Long state) {
        return contractDao.pageByHql("from Contract where state = ?" ,page.getPageNo(),page.getPageSize() , state);
    }

    @Override
    public List<Contract> findListbyDeliveryPeriod(String now) {
        return contractDao.getListByHQL("from Contract where state = 2 and to_char(deliveryPeriod,'yyyy-mm-dd')=?", now);
    }
}
