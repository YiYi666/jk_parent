package top.greathead.jk.entity;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Export extends BaseEntity implements Serializable {
    private String id;
    private Date inputDate;
    private String contractIds;
    private String customerContract;
    private String lcno;
    private String consignee;
    private String marks;
    private String shipmentPort;
    private String destinationPort;
    private String transportMode;
    private String priceCondition;
    private String remark;
    private Long boxNums;
    private Long grossWeights;
    private Long measurements;
    private Long state; //0-草稿 1-已上报 2-装箱 3-委托 4-发票 5-财务

    private Set<ExportProduct> exportProducts = new HashSet<>();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getInputDate() {
        return inputDate;
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

    public String getContractIds() {
        return contractIds;
    }

    public void setContractIds(String contractIds) {
        this.contractIds = contractIds;
    }

    public String getCustomerContract() {
        return customerContract;
    }

    public void setCustomerContract(String customerContract) {
        this.customerContract = customerContract;
    }

    public String getLcno() {
        return lcno;
    }

    public void setLcno(String lcno) {
        this.lcno = lcno;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getShipmentPort() {
        return shipmentPort;
    }

    public void setShipmentPort(String shipmentPort) {
        this.shipmentPort = shipmentPort;
    }

    public String getDestinationPort() {
        return destinationPort;
    }

    public void setDestinationPort(String destinationPort) {
        this.destinationPort = destinationPort;
    }

    public String getTransportMode() {
        return transportMode;
    }

    public void setTransportMode(String transportMode) {
        this.transportMode = transportMode;
    }

    public String getPriceCondition() {
        return priceCondition;
    }

    public void setPriceCondition(String priceCondition) {
        this.priceCondition = priceCondition;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getBoxNums() {
        return boxNums;
    }

    public void setBoxNums(Long boxNums) {
        this.boxNums = boxNums;
    }

    public Long getGrossWeights() {
        return grossWeights;
    }

    public void setGrossWeights(Long grossWeights) {
        this.grossWeights = grossWeights;
    }

    public Long getMeasurements() {
        return measurements;
    }

    public void setMeasurements(Long measurements) {
        this.measurements = measurements;
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public Set<ExportProduct> getExportProducts() {
        return exportProducts;
    }

    public void setExportProducts(Set<ExportProduct> exportProducts) {
        this.exportProducts = exportProducts;
    }
}
