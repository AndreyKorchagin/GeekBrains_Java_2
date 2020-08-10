package com.korchagin.java.courses.task6.services;

import com.korchagin.java.courses.task6.entity.Goods;
import com.korchagin.java.courses.task6.repositories.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GoodsService {
    private GoodsRepository goodsRepository;

    @Autowired
    public GoodsService(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    public List<Goods> getAllGoods(){
        return goodsRepository.getAllGoods();
    }

    public Goods getGoodsById(Long id){
        return goodsRepository.getGoodsById(id);
    }

    public void saveGoods(Goods goods){
        goodsRepository.saveGoods(goods);
    }

    public void saveAllGoods(List<Goods> goods){
        goodsRepository.saveAllGoods(goods);
    }

    public void deleteGoodsById(Long id){ goodsRepository.deleteGoodsById(id); }
}
