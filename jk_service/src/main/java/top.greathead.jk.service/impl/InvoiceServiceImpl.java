package top.greathead.jk.service.impl;

import top.greathead.jk.dao.BaseDao;
import top.greathead.jk.entity.Invoice;
import top.greathead.jk.service.InvoiceService;
import top.greathead.jk.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private BaseDao<Invoice,String> invoiceDao;

    @Override
    public Pagination findPage(Pagination page) {
    return invoiceDao.pageByHql("from Invoice",page.getPageNo(),page.getPageSize(),null);
    }

    @Override
    public List<Invoice> findAll() {
        return invoiceDao.getListByHQL("from Invoice");
    }

    @Override
    public void save(Invoice model) {
    invoiceDao.save(model);
    }

    @Override
    public void deleteById(String id) {
    invoiceDao.deleteById(Invoice.class,id);
    }

    @Override
    public void update(Invoice model) {
    invoiceDao.update(model);
    }

    @Override
    public Invoice findById(String id) {
    return invoiceDao.get(Invoice.class,id);
    }
}
