package top.greathead.jk.service.impl;

import top.greathead.jk.dao.BaseDao;
import top.greathead.jk.entity.ShippingOrder;
import top.greathead.jk.service.ShippingOrderService;
import top.greathead.jk.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service
public class ShippingOrderServiceImpl implements ShippingOrderService {
    @Autowired
    private BaseDao<ShippingOrder,String> shippingOrderDao;

    @Override
    public Pagination findPage(Pagination page) {
    return shippingOrderDao.pageByHql("from ShippingOrder",page.getPageNo(),page.getPageSize(),null);
    }

    @Override
    public List<ShippingOrder> findAll() {
        return shippingOrderDao.getListByHQL("from ShippingOrder");
    }

    @Override
    public void save(ShippingOrder model) {
    shippingOrderDao.save(model);
    }

    @Override
    public void deleteById(String id) {
    shippingOrderDao.deleteById(ShippingOrder.class,id);
    }

    @Override
    public void update(ShippingOrder model) {
    shippingOrderDao.update(model);
    }

    @Override
    public ShippingOrder findById(String id) {
    return shippingOrderDao.get(ShippingOrder.class,id);
    }
}
