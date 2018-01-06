package top.greathead.jk.service.impl;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.greathead.jk.dao.BaseDao;
import top.greathead.jk.entity.Contract;
import top.greathead.jk.entity.ContractProduct;
import top.greathead.jk.entity.ExtCproduct;
import top.greathead.jk.entity.User;
import top.greathead.jk.service.ContractProductService;
import top.greathead.jk.utils.Pagination;
import top.greathead.jk.utils.SysConstant;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ContractProductServiceImpl implements ContractProductService {

    @Autowired
    private BaseDao<ContractProduct,String> contractProductDao;
    @Autowired
    private BaseDao<Contract,String> contractDao;
    @Autowired
    private  BaseDao<ExtCproduct,String> extCproductDao;

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
        model.setExtCproducts(contractProduct.getExtCproducts());
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
    public void delete(String id) {
        ContractProduct contractProduct = contractProductDao.get(ContractProduct.class, id);

        Contract contract = contractDao.get(Contract.class, contractProduct.getContract().getId());

        Set<ExtCproduct> extCproducts = contractProduct.getExtCproducts();
        Long amount = 0L;
        for(ExtCproduct extCproduct : extCproducts){
            amount+=extCproduct.getAmount();
        }
        contract.setTotalAmount(contract.getTotalAmount()-contractProduct.getAmount()-amount);

        contractProductDao.deleteById(ContractProduct.class,id);
        contractDao.update(contract);
    }

    @Override
    public List<ContractProduct> findOutProduct(String inputDate) {

        return contractProductDao.getListByHQL("from ContractProduct where contract.state = 1 and to_char(contract.shipTime,'yyyy-mm') = ?",inputDate);
    }
}
