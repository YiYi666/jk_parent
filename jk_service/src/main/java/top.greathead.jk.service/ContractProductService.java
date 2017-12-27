package top.greathead.jk.service;

import top.greathead.jk.entity.ContractProduct;
import top.greathead.jk.utils.Pagination;

import java.util.List;

public interface ContractProductService {
    Pagination findByPage(Pagination page, String contractId);

    List<ContractProduct> findAll();

    void insert(ContractProduct model);

    ContractProduct findById(String id);

    void update(ContractProduct model);

    void delete(String[] ids);


}
