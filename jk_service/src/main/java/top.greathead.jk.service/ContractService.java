package top.greathead.jk.service;

import top.greathead.jk.entity.Contract;
import top.greathead.jk.utils.Pagination;

import java.util.List;

public interface ContractService {
    Pagination findByPage(Pagination page);

    List<Contract> findAll();

    void insert(Contract model);

    Contract findById(String id);

    void update(Contract model);

    void delete(String[] ids);

    void updateState(String id, Long state);
}
