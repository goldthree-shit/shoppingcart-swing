package com.hx.entity;

import java.io.Serializable;

/**
 * @ClassName Commodity
 * @Author goldthree-shit
 * @Date 2021/12/24 20:16
 * @Description 商品实体类
 * @Version 1.0
 */
public class Commodity implements Serializable {
    private static final long serialVersionUID = -9059721037598130156L;

    private String name;
    private Integer price;
    private Integer counts;
    private Integer order;

    public Commodity(String name, Integer price, Integer counts) {
        this.name = name;
        this.counts = counts;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public  Integer getOrder(){
        return order;
    }

    public void setOrder(Integer record) {
        this.order = record;
    }

    @Override
    public String toString() {
        return "Commodity{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", counts=" + counts +
                ", order=" + order +
                '}';
    }
}
