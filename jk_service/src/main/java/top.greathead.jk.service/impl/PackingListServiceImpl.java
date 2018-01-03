package top.greathead.jk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.greathead.jk.dao.BaseDao;
import top.greathead.jk.entity.PackingList;
import top.greathead.jk.service.PackingListService;
import top.greathead.jk.utils.Pagination;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PackingListServiceImpl implements PackingListService {

    @Autowired
    private BaseDao<PackingList,String> packingListDao;
    @Override
    @Transactional(readOnly = true)
    public Pagination findByPage(Pagination page) {
        return packingListDao.pageByHql("from PackingList" ,page.getPageNo(),page.getPageSize());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PackingList> findAll() {
        return packingListDao.getListByHQL("from PackingList");
    }

    @Override
    public void insert(PackingList model) {
//        Long totalAmount = 0L;
//        Long state = 0L;
//
//        model.setTotalAmount(totalAmount);
//        model.setState(state);
//        packingListDao.save(model);
    }

    @Override
    public void update(PackingList model) {
//        PackingList oldModel = packingListDao.get(PackingList.class, model.getId());
//        model.setCreateTime(oldModel.getCreateTime());
//        model.setUpdateTime(new Date());
//        packingListDao.evict(oldModel);
//        packingListDao.update(model);
    }

    @Override
    @Transactional(readOnly = true)
    public PackingList findById(String id) {
        return packingListDao.get(PackingList.class,id);
    }

    @Override
    public void delete(String[] ids) {
        for (String id : ids) {
            packingListDao.deleteById(PackingList.class,id);
        }
    }

    @Override
    public void updateState(String id, Long state) {
        PackingList packingList = packingListDao.get(PackingList.class, id);
        packingList.setState(state);

        packingListDao.update(packingList);
    }

    @Override
    public Pagination findByPage(Pagination page, Long state) {
        return packingListDao.pageByHql("from PackingList where state = ?" ,page.getPageNo(),page.getPageSize() , state);
    }
}
