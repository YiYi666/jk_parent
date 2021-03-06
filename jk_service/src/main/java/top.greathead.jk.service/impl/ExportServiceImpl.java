package top.greathead.jk.service.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.greathead.jk.dao.BaseDao;
import top.greathead.jk.entity.*;
import top.greathead.jk.service.ExportService;
import top.greathead.jk.utils.Pagination;
import top.greathead.jk.utils.SysConstant;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ExportServiceImpl implements ExportService {

    @Autowired
    private BaseDao<Export,String> exportDao;

    @Autowired
    private BaseDao<Contract,String> contractDao;

    @Autowired
    private BaseDao<ExportProduct,String> exportProductDao;

    @Override
    @Transactional(readOnly = true)
    public Pagination findByPage(Pagination page) {
        return exportDao.pageByHql("from Export order by inputDate desc" ,page.getPageNo(),page.getPageSize());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Export> findAll() {
        return exportDao.getListByHQL("from Export");
    }

    @Override
    public void insert(Export model) {
        String ids = model.getId();
        String[] contractIds =  ids.split(", ");
        model.setId(null);
        Long state = 0L;

        model.setState(state);
        model.setInputDate(new Date());
        model.setCreateTime(new Date());
        model.setContractIds(ids);

        User user = (User) ServletActionContext.getRequest().getSession().getAttribute(SysConstant.C_USER);
        model.setCreateBy(user.getId());
        model.setCreateDept(user.getDept().getId());

        String customerContract="";
        for(String contractId:contractIds){
            Contract contract = contractDao.get(Contract.class, contractId);
            contract.setState(2L);
            Set<ContractProduct> contractProducts = contract.getContractProducts();
            // Set<ExportProduct> exportProducts = model.getExportProducts();
            customerContract = customerContract +" " + contract.getContractNo();

            for(ContractProduct contractProduct:contractProducts){
                try {
                    ExportProduct exportProduct = new ExportProduct();
                    BeanUtils.copyProperties(exportProduct,contractProduct);
                    exportProduct.setId(null);
                    model.getExportProducts().add(exportProduct);

                    Set<ExtCproduct> extCproducts = contractProduct.getExtCproducts();
                    //Set<ExtEproduct> extEproducts = exportProduct.getExtEproducts();

                    for(ExtCproduct extCproduct:extCproducts){
                        try {
                            ExtEproduct extEproduct = new ExtEproduct();
                            BeanUtils.copyProperties(extEproduct,extCproduct);
                            extEproduct.setId(null);
                            exportProduct.getExtEproducts().add(extEproduct);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                contractDao.update(contract);
            }
        }
        model.setCustomerContract(customerContract);


        exportDao.save(model);
    }

/*    @Override
    public void update(Export model, String[] mr_id, String[] mr_changed, String[] mr_orderNo,
                       String[] mr_cnumber, String[] mr_grossWeight, String[] mr_netWeight, String[] mr_sizeLength,
                       String[] mr_sizeWidth, String[] mr_sizeHeight, String[] mr_exPrice, String[] mr_tax) {
        for(int i=0; i<mr_changed.length; i++){
            if(!mr_changed[i].isEmpty()){
                ExportProduct exportProduct = exportProductDao.get(ExportProduct.class, mr_id[i]);
                exportProduct.setCnumber(toLong(mr_cnumber[i]));
                exportProduct.setGrossWeight(toLong(mr_grossWeight[i]));
                exportProduct.setNetWeight(toLong(mr_netWeight[i]));
                exportProduct.setSizeLength(toLong(mr_sizeLength[i]));
                exportProduct.setSizeWidth(toLong(mr_sizeWidth[i]));
                exportProduct.setSizeHeight(toLong(mr_sizeHeight[i]));
                exportProduct.setExPrice(toLong(mr_exPrice[i]));
                exportProduct.setTax(toLong(mr_tax[i]));
                exportProductDao.update(exportProduct);
            }
        }
        Export export = exportDao.get(Export.class, model.getId());
        model.setExportProducts(export.getExportProducts());
        exportDao.evict(export);
        model.setState(export.getState());
        exportDao.update(model);
    }*/

    @Override
    public void update(Export model) {
        Export export = exportDao.get(Export.class, model.getId());
        model.setExportProducts(export.getExportProducts());
        exportDao.evict(export);
        model.setState(export.getState());
        /*User user = (User) ServletActionContext.getRequest().getSession().getAttribute(SysConstant.C_USER);
        model.setUpdateBy(user.getId());
        model.setUpdateTime(new Date());*/
        exportDao.update(model);
    }

    private Long toLong (String data){
        return data.isEmpty()? null : Long.valueOf(data);
    }


    @Override
    @Transactional(readOnly = true)
    public Export findById(String id) {
        return exportDao.get(Export.class,id);
    }

    @Override
    public void delete(String[] ids) {
        for (String id : ids) {
            exportDao.deleteById(Export.class,id);
        }
    }

    @Override
    public void updateState(Export model,Long exportState) {
        Export export = exportDao.get(Export.class, model.getId());
        exportDao.evict(export);
        Export ex = exportDao.get(Export.class, model.getId());
        ex.setState(exportState);
            /*String contractIds = export.getContractIds();
            if(contractIds!=null){
                String[] Ids = contractIds.split(", ");
                for(String contractId : Ids){
                    Contract contract = contractDao.get(Contract.class, contractId);
                    contract.setState(contractState);
                    contractDao.update(contract);//
                }
            }*/
       // exportDao.evict(model);
        exportDao.update(ex);
    }

    @Override
    public Pagination findByPage(Pagination page, Long state) {
        return exportDao.pageByHql("from Export where state = ?" ,page.getPageNo(),page.getPageSize() , state);
    }

    @Override
    public List<ExportProduct> fingExportProductByExportId(String id) {
        return exportProductDao.getListByHQL("from ExportProduct where export.id = ?",id);
    }

    @Override
    public void updateEP(ExportProduct ep) {
        ExportProduct exportProduct = exportProductDao.get(ExportProduct.class, ep.getId());
        exportProduct.setCnumber(ep.getCnumber());
        exportProduct.setGrossWeight(ep.getGrossWeight());
        exportProduct.setNetWeight(ep.getNetWeight());
        exportProduct.setSizeLength(ep.getSizeLength());
        exportProduct.setSizeWidth(ep.getSizeWidth());
        exportProduct.setSizeHeight(ep.getSizeHeight());
        exportProduct.setExPrice(ep.getExPrice());
        exportProduct.setTax(ep.getTax());
        exportProductDao.update(exportProduct);
    }
}
