package top.greathead.jk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.greathead.jk.dao.BaseDao;
import top.greathead.jk.entity.Contract;
import top.greathead.jk.entity.ContractProduct;
import top.greathead.jk.service.ContractProductService;
import top.greathead.jk.utils.Pagination;

import java.util.List;

@Service
@Transactional
public class ContractProductServiceImpl implements ContractProductService {

    @Autowired
    private BaseDao<ContractProduct,String> contractProductDao;
    @Autowired
    private BaseDao<Contract,String> contractDao;

    @Override
    @Transactional(readOnly = true)
    public Pagination findByPage(Pagination page, String contractId) {
        return contractProductDao.pageByHql("from ContractProduct where contract.id=?" ,page.getPageNo(),page.getPageSize(),contractId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContractProduct> findAll() {
        return contractProductDao.getListByHQL("from ContractProduct");
    }

    @Override
    public void insert(ContractProduct model) {
        Long insertAmount = model.getPrice()*model.getCnumber();
        model.setAmount(insertAmount);
        contractProductDao.save(model);
        calculatePrice(model, insertAmount);
    }

    private void calculatePrice(ContractProduct contractProduct, Long amount) {
        Contract contract = contractDao.get(Contract.class, contractProduct.getContract().getId());
        contract.setTotalAmount(contract.getTotalAmount()+amount);
        contractDao.update(contract);
    }

    @Override
    public void update(ContractProduct model) {
        Long updateAmount = model.getPrice()*model.getCnumber();
        ContractProduct contractProduct = contractProductDao.get(ContractProduct.class, model.getId());
        model.setAmount(updateAmount);
        Long cutAmount = updateAmount - contractProduct.getAmount();
        contractProductDao.evict(contractProduct);
        contractProductDao.update(model);
        calculatePrice(model,cutAmount);

    }

    @Override
    @Transactional(readOnly = true)
    public ContractProduct findById(String id) {
        return contractProductDao.get(ContractProduct.class,id);
    }

    @Override
    public void delete(String[] ids) {
        for (String id : ids) {
            contractProductDao.deleteById(ContractProduct.class,id);
        }
    }


}
