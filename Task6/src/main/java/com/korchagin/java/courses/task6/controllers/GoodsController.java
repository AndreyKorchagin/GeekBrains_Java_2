package com.korchagin.java.courses.task6.controllers;

import com.korchagin.java.courses.task6.entity.Goods;
import com.korchagin.java.courses.task6.services.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RequestMapping(value = "goods")
public class GoodsController {

    private GoodsService goodsService;

    @Autowired
    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @RequestMapping(value = "goods")
    public String showAllGoods(Model model){
        List<Goods> goods = goodsService.getAllGoods();
        model.addAttribute("goods", goods);
        return "goods-page";
    }

    @RequestMapping(value = "/goods/add")
    public String addGoods(@RequestParam String name, @RequestParam Integer price){
        Goods goods = new Goods(name, price);
        goodsService.saveGoods(goods);
        return "redirect:/goods";
    }

    @RequestMapping("goods/find")
    public String findGoods(Model model, @RequestParam Long id){
        Goods goods = goodsService.getGoodsById(id);
        model.addAttribute("goods", goods);
        return "goods-find";
    }

    @GetMapping("goods/remove/{id}")
    public String deleteGoodsById(@PathVariable(value = "id") Long id){
        goodsService.deleteGoodsById(id);
        return "redirect:/goods";
    }
}
