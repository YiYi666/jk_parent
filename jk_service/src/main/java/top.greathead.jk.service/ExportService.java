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

    void update(Export model);

    void delete(String[] ids);

    void updateState(Export model,Long exportState,Long contractState);

    Pagination findByPage(Pagination page, Long state);

    List<ExportProduct> fingExportProductByExportId(String id);

    void updateEP(ExportProduct ep);
}
