package top.greathead.jk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.greathead.jk.dao.BaseDao;
import top.greathead.jk.entity.Contract;
import top.greathead.jk.entity.ExtCproduct;
import top.greathead.jk.service.ExtCproductService;
import top.greathead.jk.utils.Pagination;

import java.util.List;

@Service
@Transactional
public class ExtCproductServiceImpl implements ExtCproductService {

    @Autowired
    private BaseDao<ExtCproduct,String> extCproductDao;
    @Autowired
    private BaseDao<Contract,String> contractDao;


    @Override
    @Transactional(readOnly = true)
    public Pagination findByPage(Pagination page, String contractProductId) {
        return extCproductDao.pageByHql("from ExtCproduct where CONTRACT_PRODUCT_ID = ?" ,page.getPageNo(),page.getPageSize(),contractProductId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExtCproduct> findAll() {
        return extCproductDao.getListByHQL("from ExtCproduct");
    }

    @Override
    public void insert(ExtCproduct model) {
        Long insertAmount = model.getPrice()*model.getCnumber();
        model.setAmount(insertAmount);
        extCproductDao.save(model);

        calculatePrice(model, insertAmount);
    }

    private void calculatePrice(ExtCproduct extCproduct, Long amount) {
        Contract contract = contractDao.get(Contract.class, extCproduct.getContractProduct().getContract().getId());
        contract.setTotalAmount(contract.getTotalAmount()+amount);
        contractDao.update(contract);
    }

    @Override
    public void update(ExtCproduct model) {
        Long updateAmount = model.getPrice()*model.getCnumber();
        ExtCproduct extCproduct = extCproductDao.get(ExtCproduct.class, model.getId());
        model.setAmount(updateAmount);

        Long cutAmount = updateAmount - extCproduct.getAmount();  //增加的总价
        extCproductDao.evict(extCproduct);
        extCproductDao.update(model);

        calculatePrice(model,cutAmount);
    }

    @Override
    @Transactional(readOnly = true)
    public ExtCproduct findById(String id) {
        return extCproductDao.get(ExtCproduct.class,id);
    }

    @Override
    public void delete(String id) {
        ExtCproduct extCproduct = extCproductDao.get(ExtCproduct.class, id);
        Long amount = extCproduct.getAmount();
        Contract contract = contractDao.get(Contract.class, extCproduct.getContractProduct().getContract().getId());
        contract.setTotalAmount(contract.getTotalAmount()-amount);
        extCproductDao.deleteById(ExtCproduct.class,id);
        contractDao.update(contract);
    }
}
