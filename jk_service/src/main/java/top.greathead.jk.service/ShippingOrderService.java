package top.greathead.jk.service;

import top.greathead.jk.entity.ShippingOrder;
import top.greathead.jk.utils.Pagination;

import java.util.List;

public interface ShippingOrderService {
    Pagination findByPage(Pagination page);

    List<ShippingOrder> findAll();

    void insert(ShippingOrder model);

    ShippingOrder findById(String id);

    void update(ShippingOrder model);

    void delete(String[] ids);

    void updateState(String id, Long state);

    Pagination findByPage(Pagination page, Long state);

    List<ShippingOrder> findListbyDeliveryPeriod(String now);
}
