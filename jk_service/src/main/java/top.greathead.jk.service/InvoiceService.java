package top.greathead.jk.service;

import top.greathead.jk.entity.Invoice;
import top.greathead.jk.utils.Pagination;

import java.util.List;

public interface InvoiceService {
Pagination findPage(Pagination pageNo);

List<Invoice> findAll();

    void save(Invoice model);

    Invoice findById(String id);

    void update(Invoice model);

    void deleteById(String id);
}
