package com.korchagin.java;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "goods")
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private int price;

    @ManyToMany
    @JoinTable(name = "buyer_goods",
            joinColumns = @JoinColumn(name = "id_goods"),
            inverseJoinColumns = @JoinColumn(name = "id_buyer")
    )
    private List<Buyers> buyers;

    public Goods(){}

    public Goods(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public long getId() { return id; }
    public String getName() { return name; }
    public int getPrice() { return price; }
    public List<Buyers> getBuyers() { return buyers; }

    public void setId(long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(int price) { this.price = price; }
    public void setBuyers(List<Buyers> buyers) { this.buyers = buyers; }

}
