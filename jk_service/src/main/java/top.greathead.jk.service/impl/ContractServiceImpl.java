package top.greathead.jk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.greathead.jk.dao.BaseDao;
import top.greathead.jk.entity.Contract;
import top.greathead.jk.service.ContractService;
import top.greathead.jk.utils.Pagination;

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
        contractDao.save(model);
    }

    @Override
    public void update(Contract model) {
        Contract oldModel = contractDao.get(Contract.class, model.getId());
        model.setCreateTime(oldModel.getCreateTime());
        model.setUpdateTime(new Date());
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
}
