package top.greathead.jk.web.cargo;

import com.opensymphony.xwork2.ModelDriven;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import top.greathead.jk.entity.Contract;
import top.greathead.jk.entity.ContractProduct;
import top.greathead.jk.service.ContractService;
import top.greathead.jk.utils.DateFormatUtils;
import top.greathead.jk.utils.Pagination;
import top.greathead.jk.web.BaseAction;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

/**
 * @author coach tam
 * @date 2017/12/20
 */
@Controller("contractAction")
@Scope("prototype")
public class ContractAction extends BaseAction implements ModelDriven<Contract>{

    private Pagination page = new Pagination();

    @Autowired
    private ContractService contractService;

    private List<Contract> contractList;

    private Contract model = new Contract();

    public String insert(){
        contractService.insert(model);
        return "rlist";
    }

    public String list(){
        page = contractService.findByPage(page);
        page.setUrl("contractAction_list");
        push(page);
        return "list";
    }
    public String tocreate(){
        contractList = contractService.findAll();
        return "tocreate";
    }
    public String toupdate(){
        contractList = contractService.findAll();
        model = contractService.findById(model.getId());
        contractList.remove(model);
        push(model);
        return "toupdate";
    }
    public String update(){
        contractService.update(model);
        return "rlist";
    }

    public String delete(){
        contractService.delete(model.getId().split(", "));
        return "rlist";
    }

    public String toview(){
        model = contractService.findById(model.getId());
        push(model);
        return "toview";
    }

    public String submit(){
        Contract contract = contractService.findById(model.getId());
        Set<ContractProduct> contractProducts = contract.getContractProducts();

        if(contractProducts.isEmpty()){
           return "rlist";
        }

        Long state = 1L;
        contractService.updateState(model.getId(),state);
        return "rlist";
    }

   /* public String print() throws IOException {

        int rowNo = 0;
        int cellNo = 1;

        String realPath = ServletActionContext.getServletContext().getRealPath("/make/xlsprint/tEXPORT.xls");
        Workbook workbook = new HSSFWorkbook(new FileInputStream(realPath));
        Sheet sheet = workbook.getSheetAt(0);


        Row row = sheet.getRow(rowNo++);
        Cell cell = row.getCell(1);
        //String title = inputDate.replaceAll("-0","年").replaceAll("-","年")+"月份出货表";
        cell.setCellValue(title);
        //小标题
        rowNo++;
        //正文
        row = sheet.getRow(rowNo++);
        CellStyle[] cellStyle = new CellStyle[8];
        for(int i = 0; i<cellStyle.length; i++){
            cell = row.getCell(i+1);
            cellStyle[i] = cell.getCellStyle();
        }


        List<ContractProduct> cplist = contractProductService.findOutProduct(inputDate);
        String[] datas;
        for (ContractProduct cp: cplist) {

            cellNo = 1;
            Contract contract = cp.getContract();
            datas = new String[]{contract.getCustomName(),
                    contract.getContractNo(),cp.getProductNo(),
                    cp.getCnumber().toString() ,cp.getFactoryName(),
                    DateFormatUtils.simpleDateFormat.format(contract.getDeliveryPeriod()),
                    DateFormatUtils.simpleDateFormat.format(contract.getShipTime()),contract.getTradeTerms()};
            for(String data: datas){
                cell = row.createCell(cellNo++);
                cell.setCellStyle(cellStyle[cellNo-2]);
                cell.setCellValue(data);
            }
            row = sheet.createRow(rowNo++);
        }

        HttpServletResponse response = ServletActionContext.getResponse();
        response.setHeader("Content-Disposition", "attachment;filename="+new String((title).getBytes("gbk"), "iso8859-1")+".xls");
        workbook.write(response.getOutputStream());
        workbook.close();
        return null;
    }*/

    public Pagination getPage() {
        return page;
    }

    public void setPage(Pagination page) {
        this.page = page;
    }

    public List<Contract> getContractList() {
        return contractList;
    }

    @Override
    public Contract getModel() {
        return model;
    }
}
