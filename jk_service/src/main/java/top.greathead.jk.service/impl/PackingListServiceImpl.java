package top.greathead.jk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.greathead.jk.dao.BaseDao;
import top.greathead.jk.entity.Export;
import top.greathead.jk.entity.PackingList;
import top.greathead.jk.service.PackingListService;
import top.greathead.jk.utils.DateFormatUtils;
import top.greathead.jk.utils.Pagination;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PackingListServiceImpl implements PackingListService {

    @Autowired
    private BaseDao<PackingList,String> packingListDao;
    @Autowired
    private  BaseDao<Export,String> exportDao;

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
        //TODO  exportNos
        String[] exportIds = model.getExportIds().split(", ");
        String exportNos = "";
        for(String id : exportIds){
            Export export = exportDao.get(Export.class, id);
            exportNos = exportNos+ " " +export.getCustomerContract();
        }
        model.setExportNos(exportNos);
        model.setCreateTime(new Date());
        model.setState(0L);
        packingListDao.save(model);
    }

    @Override
    public void update(PackingList model) {
        PackingList oldModel = packingListDao.get(PackingList.class, model.getId());
        oldModel.setSeller(model.getSeller());
        oldModel.setBuyer(model.getBuyer());
        oldModel.setInvoiceNo(model.getInvoiceNo());
        oldModel.setInvoiceDate(model.getInvoiceDate());
        oldModel.setMarks(model.getMarks());
        oldModel.setDescriptions(model.getDescriptions());

        packingListDao.evict(model);
        packingListDao.update(oldModel);
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
