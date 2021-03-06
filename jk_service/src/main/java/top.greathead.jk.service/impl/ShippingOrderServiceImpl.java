package top.greathead.jk.service.impl;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.greathead.jk.dao.BaseDao;
import top.greathead.jk.entity.PackingList;
import top.greathead.jk.entity.ShippingOrder;
import top.greathead.jk.entity.User;
import top.greathead.jk.service.ShippingOrderService;
import top.greathead.jk.utils.Pagination;
import top.greathead.jk.utils.SysConstant;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ShippingOrderServiceImpl implements ShippingOrderService {

    @Autowired
    private BaseDao<ShippingOrder,String> shippingOrderDao;
    @Autowired
    private BaseDao<PackingList,String> packingListDao;

    @Override
    @Transactional(readOnly = true)
    public Pagination findByPage(Pagination page) {
        return shippingOrderDao.pageByHql("from ShippingOrder" ,page.getPageNo(),page.getPageSize());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ShippingOrder> findAll() {
        return shippingOrderDao.getListByHQL("from ShippingOrder");
    }

    @Override
    public void insert(ShippingOrder model) {
        Long state = 0L;
        model.setState(state);
        model.setCreateTime(new Date());
        User user = (User) ServletActionContext.getRequest().getSession().getAttribute(SysConstant.C_USER);
        model.setCreateBy(user.getId());
        model.setCreateDept(user.getDept().getId());

        PackingList packingList = packingListDao.get(PackingList.class, model.getId());
        packingList.setState(2L);
        packingListDao.update(packingList);

        model.setPackingList(packingList);
        model.setId(null);
        shippingOrderDao.save(model);
    }

    @Override
    public void update(ShippingOrder model) {
        ShippingOrder oldModel = shippingOrderDao.get(ShippingOrder.class, model.getId());
        model.setCreateTime(oldModel.getCreateTime());
        model.setCreateTime(oldModel.getCreateTime());
        model.setState(oldModel.getState());
        shippingOrderDao.evict(oldModel);
        shippingOrderDao.update(model);
    }

    @Override
    @Transactional(readOnly = true)
    public ShippingOrder findById(String id) {
        return shippingOrderDao.get(ShippingOrder.class,id);
    }

    @Override
    public void delete(String[] ids) {
        for (String id : ids) {
            shippingOrderDao.deleteById(ShippingOrder.class,id);
        }
    }

    @Override
    public void updateState(String id, Long state) {
        ShippingOrder shippingOrder = shippingOrderDao.get(ShippingOrder.class, id);
        shippingOrder.setState(state);

        shippingOrderDao.update(shippingOrder);
    }

    @Override
    public Pagination findByPage(Pagination page, Long state) {
        return shippingOrderDao.pageByHql("from ShippingOrder where state = ?" ,page.getPageNo(),page.getPageSize() , state);
    }

    @Override
    public List<ShippingOrder> findListbyDeliveryPeriod(String now) {
        return shippingOrderDao.getListByHQL("from ShippingOrder where state = 2 and to_char(deliveryPeriod,'yyyy-mm-dd')=?", now);
    }
}
