package top.greathead.jk.web.cargo;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ModelDriven;
import com.yaorange.webservice.EpService;
import com.yaorange.webservice.Exception_Exception;
import com.yaorange.webservice.ExportEResponse;
import com.yaorange.webservice.ExportResult;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import top.greathead.jk.entity.Export;
import top.greathead.jk.entity.ExportProduct;
import top.greathead.jk.service.ContractService;
import top.greathead.jk.service.ExportService;
import top.greathead.jk.service.PackingListService;
import top.greathead.jk.utils.FastJsonUtil;
import top.greathead.jk.utils.Pagination;
import top.greathead.jk.web.BaseAction;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static oracle.net.aso.C05.e;

/**
 * @author coach tam
 * @date 2017/12/20
 */
@Controller("exportAction")
@Scope("prototype")
public class ExportAction extends BaseAction implements ModelDriven<Export>{

    private Pagination page = new Pagination();

    @Autowired
    private ExportService exportService;
    @Autowired
    private ContractService contractService;
    @Autowired
    private EpService epService;

    private String[] mr_id;
    private String[] mr_changed;
    private String[] mr_orderNo;
    private String[] mr_cnumber;
    private String[] mr_grossWeight;
    private String[] mr_netWeight;
    private String[] mr_sizeLength;
    private String[] mr_sizeWidth;
    private String[] mr_sizeHeight;
    private String[] mr_exPrice;
    private String[] mr_tax;



    private List<Export> exportList;
    private ExportProduct ep;

    private Export model = new Export();

    public String contractList(){
        Long state = 1L;
        page = contractService.findByPage(page,state);
        page.setUrl("exportAction_contractList");
        push(page);
        return "contractList";
    }

    public String tocreate(){
        return "tocreate";
    }
    public String insert(){
        exportService.insert(model);
        return contractList();
    }

    public String list(){
        page = exportService.findByPage(page);
        page.setUrl("exportAction_list");
        push(page);
        return "list";
    }

    public String toupdate(){
        model = exportService.findById(model.getId());
        push(model);
        return "toupdate";
    }
    public String update(){

        exportService.update(model);
        return list();
    }

    public void updateExportProduct(){
        exportService.updateEP(ep);
    }

    public String delete(){
        exportService.delete(model.getId().split(", "));
        return list();
    }

    public String toview(){
        model = exportService.findById(model.getId());
        push(model);
        return "toview";
    }

    public String submit(){
        Long exportState = 1L;
        //Long contractState = 2L;
        exportService.updateState(model,exportState);
        return list();
    }

    public String cancel(){
        Long exportState = 0L;
        //Long contractState = 1L;
        exportService.updateState(model,exportState);
        return list();
    }
    public String export(){
        Export export = exportService.findById(model.getId());
        com.yaorange.webservice.Export ex = new com.yaorange.webservice.Export();
        BeanUtils.copyProperties(ex,export);
        ex.setExportId(export.getId());
        Set<ExportProduct> exportProducts = export.getExportProducts();
        Set<com.yaorange.webservice.ExportProduct> epSet = new HashSet<>();
        for (ExportProduct exportProduct:exportProducts) {
            com.yaorange.webservice.ExportProduct ep = new com.yaorange.webservice.ExportProduct();
            BeanUtils.copyProperties(ep,exportProduct);
            ep.setExportProductId(exportProduct.getId());
            epSet.add(ep);
        }
        ex.setProducts(epSet);
        String json = FastJsonUtil.toJSONString(ex);
        try {
            String exportE = epService.exportE(json);
            ExportResult exportResult = JSON.parseObject(exportE, ExportResult.class);
            String remark = exportResult.getRemark();
            //System.out.println(remark);
            //TODO
            if(exportResult.getState().equals("2")){
                exportService.updateState(model,1L);
            }
        } catch (Exception_Exception e) {
            e.printStackTrace();
        }
        return list();
    }

    public void getJsonStr() throws IOException {
        List<ExportProduct> exportProductList = exportService.fingExportProductByExportId(model.getId());
        String json = FastJsonUtil.toJSONString(exportProductList);
        HttpServletResponse response = ServletActionContext.getResponse();
        FastJsonUtil.write_json(response,json);
    }
    public Pagination getPage() {
        return page;
    }

    public void setPage(Pagination page) {
        this.page = page;
    }

    public List<Export> getExportList() {
        return exportList;
    }

    @Override
    public Export getModel() {
        return model;
    }


    public String[] getMr_id() {
        return mr_id;
    }

    public void setMr_id(String[] mr_id) {
        this.mr_id = mr_id;
    }

    public String[] getMr_changed() {
        return mr_changed;
    }

    public void setMr_changed(String[] mr_changed) {
        this.mr_changed = mr_changed;
    }

    public String[] getMr_orderNo() {
        return mr_orderNo;
    }

    public void setMr_orderNo(String[] mr_orderNo) {
        this.mr_orderNo = mr_orderNo;
    }

    public String[] getMr_cnumber() {
        return mr_cnumber;
    }

    public void setMr_cnumber(String[] mr_cnumber) {
        this.mr_cnumber = mr_cnumber;
    }

    public String[] getMr_grossWeight() {
        return mr_grossWeight;
    }

    public void setMr_grossWeight(String[] mr_grossWeight) {
        this.mr_grossWeight = mr_grossWeight;
    }

    public String[] getMr_netWeight() {
        return mr_netWeight;
    }

    public void setMr_netWeight(String[] mr_netWeight) {
        this.mr_netWeight = mr_netWeight;
    }

    public String[] getMr_sizeLength() {
        return mr_sizeLength;
    }

    public void setMr_sizeLength(String[] mr_sizeLength) {
        this.mr_sizeLength = mr_sizeLength;
    }

    public String[] getMr_sizeWidth() {
        return mr_sizeWidth;
    }

    public void setMr_sizeWidth(String[] mr_sizeWidth) {
        this.mr_sizeWidth = mr_sizeWidth;
    }

    public String[] getMr_sizeHeight() {
        return mr_sizeHeight;
    }

    public void setMr_sizeHeight(String[] mr_sizeHeight) {
        this.mr_sizeHeight = mr_sizeHeight;
    }

    public String[] getMr_exPrice() {
        return mr_exPrice;
    }

    public void setMr_exPrice(String[] mr_exPrice) {
        this.mr_exPrice = mr_exPrice;
    }

    public String[] getMr_tax() {
        return mr_tax;
    }

    public void setMr_tax(String[] mr_tax) {
        this.mr_tax = mr_tax;
    }

    public ExportProduct getEp() {
        return ep;
    }

    public void setEp(ExportProduct ep) {
        this.ep = ep;
    }
}
