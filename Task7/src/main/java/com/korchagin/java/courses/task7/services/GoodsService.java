package com.korchagin.java.courses.task7.services;

import com.korchagin.java.courses.task7.entity.Goods;
import com.korchagin.java.courses.task7.entity.Users;
import com.korchagin.java.courses.task7.repositories.GoodsRepository;
import com.korchagin.java.courses.task7.repositories.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GoodsService {
    private GoodsRepository goodsRepository;

    public List<Goods> getGoods(){ return goodsRepository.findAll(); }

    public List<Goods> getGoodsWithFilter(Integer maxPrice){
        List<Goods> goods = goodsRepository.findAll();
        if (maxPrice == null){
            return goods;
        }
        goods.removeIf(u -> u.getPrice() > maxPrice);
        return goods;
    }

    public Goods getGoodsById(Long id){
        return goodsRepository.findById(id).orElseThrow();
    }

    public Goods saveOrUpdateGoods(Goods goods){ return goodsRepository.save(goods); }

    public void saveAllGoods(List<Goods> goods){
        goodsRepository.saveAll(goods);
    }

    public void deleteGoodsById(Long id){
        goodsRepository.deleteById(id);
    }
}
