package top.greathead.jk.service;

import top.greathead.jk.entity.Finance;
import top.greathead.jk.utils.Pagination;

import java.util.List;

public interface FinanceService {
    Pagination findByPage(Pagination page);

    List<Finance> findAll();

    void insert(Finance model);

    Finance findById(String id);

    void update(Finance model);

    void delete(String[] ids);

    void updateState(String id, Long state);

    Pagination findByPage(Pagination page, Long state);

    List<Finance> findListbyDeliveryPeriod(String now);
}
