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
}
