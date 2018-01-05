package top.greathead.jk.service;

import top.greathead.jk.entity.ShippingOrder;
import top.greathead.jk.utils.Pagination;

import java.util.List;

public interface ShippingOrderService {
Pagination findPage(Pagination pageNo);

List<ShippingOrder> findAll();

    void save(ShippingOrder model);

    ShippingOrder findById(String id);

    void update(ShippingOrder model);

    void deleteById(String id);
}
