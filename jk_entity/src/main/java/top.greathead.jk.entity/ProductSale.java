package top.greathead.jk.entity;

import java.io.Serializable;

public class ProductSale implements Serializable {
    private Integer value;
    private String name;

    public ProductSale() {
    }

    public ProductSale(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
