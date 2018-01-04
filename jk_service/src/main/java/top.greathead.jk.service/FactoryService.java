package top.greathead.jk.service;

import top.greathead.jk.entity.Factory;
import top.greathead.jk.utils.Pagination;

import java.util.List;

public interface FactoryService {
    Pagination findByPage(Pagination page);

    List<Factory> findAll();

    void insert(Factory model);

    Factory findById(String id);

    void update(Factory model);

    void delete(String[] ids);

    List<Factory> findByType(String type);

    List<Object[]> findFactorySale();

    List<Object[]> findProductSale();
}
