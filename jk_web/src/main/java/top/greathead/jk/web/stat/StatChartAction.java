package top.greathead.jk.web.stat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import top.greathead.jk.entity.ProductSale;
import top.greathead.jk.service.FactoryService;
import top.greathead.jk.service.UserService;
import top.greathead.jk.utils.FastJsonUtil;
import top.greathead.jk.web.BaseAction;

import java.util.ArrayList;
import java.util.List;

@Controller("statChartAction")
@Scope("prototype")
public class StatChartAction extends BaseAction {

    @Autowired
    private FactoryService factoryService;
    @Autowired
    private UserService userService;

    private String xAxisData;  //['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
    private String seriesData;  ////[10, 52, 200, 334, 390, 330, 220]


    public String factorysale(){
        List<Object[]> factorySaleList = factoryService.findFactorySale();
        xAxisData ="[";
        seriesData ="[";
        int i = 0;
        for(Object[] objects : factorySaleList){
            xAxisData= xAxisData + "\'"+ objects[0].toString() +"\'";
            seriesData+=objects[1].toString();
            if(++i<factorySaleList.size()){
                xAxisData+=",";
                seriesData+=",";
            }
        }
        xAxisData +="]";
        seriesData +="]";
        return "factorysale";
    }

    public String productsale(){
        List<Object[]> productSaleList = factoryService.findProductSale();
        ArrayList<ProductSale> productSales = new ArrayList<>();
        xAxisData ="[";
        int i = 0;
        for(Object[] objects : productSaleList){
            xAxisData= xAxisData + "\'"+ objects[0].toString() +"\'";
            if(++i<productSaleList.size()){
                xAxisData+=",";
                productSales.add(new ProductSale(Integer.valueOf(objects[1].toString()),objects[0].toString()));
            }
        }
        xAxisData +="]";
        seriesData = FastJsonUtil.toJSONString(productSales);
        return "productsale";
    }

    public String onlineinfo(){
        List<Object[]> onlineInfoList = userService.findOnlineInfo();
        xAxisData ="[";
        seriesData ="[";
        int i = 0;
        for(Object[] objects : onlineInfoList){
            xAxisData= xAxisData + "\'"+ objects[0].toString() +"\'";
            seriesData+=objects[1].toString();
            if(++i<onlineInfoList.size()){
                xAxisData+=",";
                seriesData+=",";
            }
        }
        xAxisData +="]";
        seriesData +="]";
        return "onlineinfo";
    }
    public String getXAxisData() {
        return xAxisData;
    }

    public void setXAxisData(String xAxisData) {
        this.xAxisData = xAxisData;
    }

    public String getSeriesData() {
        return seriesData;
    }

    public void setSeriesData(String seriesData) {
        this.seriesData = seriesData;
    }
}
