package top.greathead.jk.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class ExportProduct implements Serializable {
    private String id;
    private String productNo;
    private String packingUnit;
    private Long cnumber;
    private Long boxNum;
    private Long grossWeight;
    private Long netWeight;
    private Long sizeLength;
    private Long sizeWidth;
    private Long sizeHeight;
    private Long exPrice;
    private Long price;
    private Long tax;
    private Long orderNo;

    private Set<ExtEproduct> extEproducts = new HashSet<>();

    private Factory factory;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getPackingUnit() {
        return packingUnit;
    }

    public void setPackingUnit(String packingUnit) {
        this.packingUnit = packingUnit;
    }

    public Long getCnumber() {
        return cnumber;
    }

    public void setCnumber(Long cnumber) {
        this.cnumber = cnumber;
    }

    public Long getBoxNum() {
        return boxNum;
    }

    public void setBoxNum(Long boxNum) {
        this.boxNum = boxNum;
    }

    public Long getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(Long grossWeight) {
        this.grossWeight = grossWeight;
    }

    public Long getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(Long netWeight) {
        this.netWeight = netWeight;
    }

    public Long getSizeLength() {
        return sizeLength;
    }

    public void setSizeLength(Long sizeLength) {
        this.sizeLength = sizeLength;
    }

    public Long getSizeWidth() {
        return sizeWidth;
    }

    public void setSizeWidth(Long sizeWidth) {
        this.sizeWidth = sizeWidth;
    }

    public Long getSizeHeight() {
        return sizeHeight;
    }

    public void setSizeHeight(Long sizeHeight) {
        this.sizeHeight = sizeHeight;
    }

    public Long getExPrice() {
        return exPrice;
    }

    public void setExPrice(Long exPrice) {
        this.exPrice = exPrice;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getTax() {
        return tax;
    }

    public void setTax(Long tax) {
        this.tax = tax;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public Set<ExtEproduct> getExtEproducts() {
        return extEproducts;
    }

    public void setExtEproducts(Set<ExtEproduct> extEproducts) {
        this.extEproducts = extEproducts;
    }

    public Factory getFactory() {
        return factory;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }
}
