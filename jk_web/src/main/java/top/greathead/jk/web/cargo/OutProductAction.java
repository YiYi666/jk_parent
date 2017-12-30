package top.greathead.jk.web.cargo;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import top.greathead.jk.entity.Contract;
import top.greathead.jk.entity.ContractProduct;
import top.greathead.jk.service.ContractProductService;
import top.greathead.jk.utils.DateFormatUtils;
import top.greathead.jk.web.BaseAction;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Controller("outProductAction")
@Scope("prototype")
public class OutProductAction extends BaseAction {

    private String inputDate;

    @Autowired
    private ContractProductService contractProductService;

    public String toedit(){
        return "toedit";
    }

/*    public String print() throws IOException {

        int rowNo = 0;
        int cellNo = 1;

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("出货表");
        //设置列宽
        sheet.setColumnWidth(1,26*256);
        sheet.setColumnWidth(2,11*256);
        sheet.setColumnWidth(3,26*256);
        sheet.setColumnWidth(4,11*256);
        sheet.setColumnWidth(5,15*256);
        sheet.setColumnWidth(6,10*256);
        sheet.setColumnWidth(7,10*256);
        sheet.setColumnWidth(8,10*256);
        //大标题
        Row row = sheet.createRow(rowNo++);
        Cell cell = row.createCell(1);
        cell.setCellStyle(bigTitle(workbook));
        sheet.addMergedRegion(new CellRangeAddress(0,0,1,8));
        String title = inputDate.replaceAll("-0","年").replaceAll("-","年")+"月份出货表";
        cell.setCellValue(title);
        //小标题
        row = sheet.createRow(rowNo++);
        String[] smallTitles = new String[]{"客户","订单号","货号","数量","工厂","工厂交期","船期","贸易条款"};
        for(int i = 0 ; i<smallTitles.length ; i++ ){
            cell = row.createCell(cellNo++);
            cell.setCellStyle(title(workbook));
            cell.setCellValue(smallTitles[i]);
        }
        //正文

        List<ContractProduct> cplist = contractProductService.findOutProduct(inputDate);
        CellStyle cellStyle = text(workbook);
        String[] datas;
        for (ContractProduct cp: cplist) {
            row = sheet.createRow(rowNo++);
            cellNo = 1;
            Contract contract = cp.getContract();
            datas = new String[]{contract.getCustomName(),
                    contract.getContractNo(),cp.getProductNo(),
                    cp.getCnumber().toString() ,cp.getFactoryName(),
                    DateFormatUtils.simpleDateFormat.format(contract.getDeliveryPeriod()),
                    DateFormatUtils.simpleDateFormat.format(contract.getShipTime()),contract.getTradeTerms()};
            for(String data: datas){
                cell = row.createCell(cellNo++);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(data);
            }
        }

        HttpServletResponse response = ServletActionContext.getResponse();
        response.setHeader("Content-Disposition", "attachment;filename="+new String((title).getBytes("gbk"), "iso8859-1")+".xls");
        workbook.write(response.getOutputStream());
        workbook.close();
        return null;
    }*/

    public String print() throws IOException {

        int rowNo = 0;
        int cellNo = 1;

        String realPath = ServletActionContext.getServletContext().getRealPath("/make/xlsprint/tOUTPRODUCT.xls");
        Workbook workbook = new HSSFWorkbook(new FileInputStream(realPath));
        Sheet sheet = workbook.getSheetAt(0);

        //大标题
        Row row = sheet.getRow(rowNo++);
        Cell cell = row.getCell(1);
        String title = inputDate.replaceAll("-0","年").replaceAll("-","年")+"月份出货表";
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
    }





    //大标题的样式
    public CellStyle bigTitle(Workbook wb){
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short)16);
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);					//字体加粗

        style.setFont(font);

        style.setAlignment(CellStyle.ALIGN_CENTER);					//横向居中
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);		//纵向居中

        return style;
    }
    //小标题的样式
    public CellStyle title(Workbook wb){
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("黑体");
        font.setFontHeightInPoints((short)12);

        style.setFont(font);

        style.setAlignment(CellStyle.ALIGN_CENTER);					//横向居中
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);		//纵向居中

        style.setBorderTop(CellStyle.BORDER_THIN);					//上细线
        style.setBorderBottom(CellStyle.BORDER_THIN);				//下细线
        style.setBorderLeft(CellStyle.BORDER_THIN);					//左细线
        style.setBorderRight(CellStyle.BORDER_THIN);				//右细线

        return style;
    }

    //文字样式
    public CellStyle text(Workbook wb){
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("Times New Roman");
        font.setFontHeightInPoints((short)10);

        style.setFont(font);

        style.setAlignment(CellStyle.ALIGN_LEFT);					//横向居左
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);		//纵向居中

        style.setBorderTop(CellStyle.BORDER_THIN);					//上细线
        style.setBorderBottom(CellStyle.BORDER_THIN);				//下细线
        style.setBorderLeft(CellStyle.BORDER_THIN);					//左细线
        style.setBorderRight(CellStyle.BORDER_THIN);				//右细线

        return style;
    }

    public void setInputDate(String inputDate) {
        this.inputDate = inputDate;
    }
}
