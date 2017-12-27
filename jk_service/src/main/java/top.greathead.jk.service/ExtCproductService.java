package top.greathead.jk.service;

import top.greathead.jk.entity.ExtCproduct;
import top.greathead.jk.utils.Pagination;

import java.util.List;

public interface ExtCproductService {
    Pagination findByPage(Pagination page, String contractProductId);

    List<ExtCproduct> findAll();

    void insert(ExtCproduct model);

    ExtCproduct findById(String id);

    void update(ExtCproduct model);

    void delete(String id);
}
