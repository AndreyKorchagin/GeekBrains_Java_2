package com.korchagin.java;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "buyers")
public class Buyers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(name = "buyer_goods",
                joinColumns = @JoinColumn(name = "id_buyer"),
                inverseJoinColumns = @JoinColumn(name = "id_goods")
                )
    private List<Goods> goods;

    public Buyers(){}

    public Buyers(String name) {
        this.name = name;
    }

    public long getId() { return id; }
    public String getName() { return name; }
    public List<Goods> getGoods() { return goods; }

    public void setId(long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setGoods(List<Goods> goods) { this.goods = goods; }


}
