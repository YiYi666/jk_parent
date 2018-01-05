package top.greathead.jk.service;

import top.greathead.jk.entity.Finance;
import top.greathead.jk.utils.Pagination;

import java.util.List;

public interface FinanceService {
Pagination findPage(Pagination pageNo);

List<Finance> findAll();

    void save(Finance model);

    Finance findById(String id);

    void update(Finance model);

    void deleteById(String id);
}
