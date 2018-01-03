package top.greathead.jk.service;

import top.greathead.jk.entity.Export;
import top.greathead.jk.entity.ExportProduct;
import top.greathead.jk.utils.Pagination;

import java.util.List;

public interface ExportService {
    Pagination findByPage(Pagination page);

    List<Export> findAll();

    void insert(Export model);

    Export findById(String id);

    void update(Export model, String[] mr_id, String[] mr_changed, String[] mr_orderNo, String[] mr_cnumber, String[] mr_grossWeight, String[] mr_netWeight, String[] mr_sizeLength, String[] mr_sizeWidth, String[] mr_sizeHeight, String[] mr_exPrice, String[] mr_tax);

    void delete(String[] ids);

    void updateState(Export model,Long exportState,Long contractState);

    Pagination findByPage(Pagination page, Long state);

    List<ExportProduct> fingExportProductByExportId(String id);
}
