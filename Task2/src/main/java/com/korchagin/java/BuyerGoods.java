package com.korchagin.java;

import javax.persistence.*;

@Entity
@Table(name = "buyer_goods")
public class BuyerGoods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "id_buyer")
    private long idBuyer;

    @Column(name = "id_goods")
    private long idGoods;

    @Column(name = "price")
    private int price;

    public BuyerGoods(){}

    public BuyerGoods(long idBuyer, long idGoods, int price) {
        this.idBuyer = idBuyer;
        this.idGoods = idGoods;
        this.price = price;
    }

    public long getId() { return id; }
    public long getIdBuyer() { return idBuyer; }
    public long getIdGoods() { return idGoods; }
    public int getPrice() { return price; }

    public void setId(long id) { this.id = id; }
    public void setId_buyer(long idBuyer) { this.idBuyer = idBuyer; }
    public void setId_goods(long idGoods) { this.idGoods = idGoods; }
    public void setPrice(int price) { this.price = price; }

    public void print(){
        System.out.printf("%6d|%8d|%8d|%5d|\n", id, idBuyer, idGoods, price);
    }
}
