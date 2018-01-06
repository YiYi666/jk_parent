package top.greathead.jk.service;

import top.greathead.jk.entity.Invoice;
import top.greathead.jk.utils.Pagination;

import java.util.List;

public interface InvoiceService {
    Pagination findByPage(Pagination page);

    List<Invoice> findAll();

    void insert(Invoice model);

    Invoice findById(String id);

    void update(Invoice model);

    void delete(String[] ids);

    void updateState(String id, Long state);

    Pagination findByPage(Pagination page, Long state);

    List<Invoice> findListbyDeliveryPeriod(String now);
}
