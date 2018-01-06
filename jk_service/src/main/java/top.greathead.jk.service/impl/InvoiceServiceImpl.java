package top.greathead.jk.service.impl;


import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.greathead.jk.dao.BaseDao;
import top.greathead.jk.entity.PackingList;
import top.greathead.jk.entity.Invoice;
import top.greathead.jk.entity.ShippingOrder;
import top.greathead.jk.entity.User;
import top.greathead.jk.service.InvoiceService;
import top.greathead.jk.utils.Pagination;
import top.greathead.jk.utils.SysConstant;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private BaseDao<Invoice,String> invoiceDao;
    @Autowired
    private BaseDao<PackingList,String> packingListDao;
    @Autowired
    private BaseDao<ShippingOrder,String> shippingOrderDao;

    @Override
    @Transactional(readOnly = true)
    public Pagination findByPage(Pagination page) {
        return invoiceDao.pageByHql("from Invoice" ,page.getPageNo(),page.getPageSize());
    }

    @Override
    @Transactional(readOnly = true)

    public List<Invoice> findAll() {
        return invoiceDao.getListByHQL("from Invoice");
    }

    @Override

    public void insert(Invoice model) {
        Long state = 0L;
        model.setState(state);
        model.setCreateTime(new Date());
        PackingList packingList = packingListDao.get(PackingList.class, model.getId());

        packingList.setInvoiceNo(model.getBlNo());
        packingList.setInvoiceDate(model.getCreateTime());
        ShippingOrder shippingOrder = packingList.getShippingOrder();
        shippingOrder.setState(2L);


        model.setPackingList(packingList);
        model.setId(null);

        User user = (User) ServletActionContext.getRequest().getSession().getAttribute(SysConstant.C_USER);
        model.setCreateBy(user.getId());
        model.setCreateDept(user.getDept().getId());

        invoiceDao.save(model);
        packingListDao.update(packingList);
        shippingOrderDao.update(shippingOrder);

    }

    @Override
    public void update(Invoice model) {

        Invoice oldModel = invoiceDao.get(Invoice.class, model.getId());
        model.setCreateTime(oldModel.getCreateTime());
        model.setState(oldModel.getState());
        invoiceDao.evict(oldModel);
        invoiceDao.update(model);
    }

    @Override
    @Transactional(readOnly = true)
    public Invoice findById(String id) {
        return invoiceDao.get(Invoice.class,id);
    }

    @Override
    public void delete(String[] ids) {
        for (String id : ids) {
            invoiceDao.deleteById(Invoice.class,id);
        }
    }

    @Override
    public void updateState(String id, Long state) {
        Invoice invoice = invoiceDao.get(Invoice.class, id);
        invoice.setState(state);

        invoiceDao.update(invoice);
    }

    @Override
    public Pagination findByPage(Pagination page, Long state) {
        return invoiceDao.pageByHql("from Invoice where state = ?" ,page.getPageNo(),page.getPageSize() , state);
    }

    @Override
    public List<Invoice> findListbyDeliveryPeriod(String now) {
        return invoiceDao.getListByHQL("from Invoice where state = 2 and to_char(deliveryPeriod,'yyyy-mm-dd')=?", now);
    }
}
