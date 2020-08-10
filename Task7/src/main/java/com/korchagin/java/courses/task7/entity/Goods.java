package com.korchagin.java.courses.task7.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "goods")
@Data
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "goods_name")
    private String goodsName;

    @Column(name = "price")
    private Integer price;
}
