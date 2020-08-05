package com.korchagin.courses.task5;

import javax.persistence.*;

@Entity
@Table(name = "goods")
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "goods_name")
    private String goodsName;

    @Column(name = "price")
    private Integer price;

    public Goods(){}

    public Goods(String goodsName, Integer price) {
        this.goodsName = goodsName;
        this.price = price;
    }

    public void print(){
        System.out.println(id + " " + goodsName + " " + price);
    }

    public Long getId() { return id; }
    public String getGoodsName() { return goodsName; }
    public Integer getPrice() { return price; }

    public void setId(Long id) { this.id = id; }
    public void setGoodsName(String goodsName) { this.goodsName = goodsName; }
    public void setPrice(Integer price) { this.price = price; }
}
