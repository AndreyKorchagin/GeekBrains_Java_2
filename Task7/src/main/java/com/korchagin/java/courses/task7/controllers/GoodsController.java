package com.korchagin.java.courses.task7.controllers;

import com.korchagin.java.courses.task7.entity.Goods;
import com.korchagin.java.courses.task7.services.GoodsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class GoodsController {
    private GoodsService goodsService;


    @GetMapping("/goods/all")
    public String showAllGoods(Model model, @RequestParam(required = false) Integer maxPrice){
        model.addAttribute("goods", goodsService.getGoodsWithFilter(maxPrice));
        return "goods-page";
    }

    @PostMapping("/goods/add")
    public String addGoods(@ModelAttribute Goods goods){
        goodsService.saveOrUpdateGoods(goods);
        return "redirect:/goods/all";
    }

    @GetMapping("/goods/edit/{id}")
    public String showEditGoodsForm(@PathVariable Long id, Model model){
        model.addAttribute("goods", goodsService.getGoodsById(id));
        return "goods-edit-page";
    }

    @PostMapping("/goods/edit")
    public String editGoods(@ModelAttribute Goods goods){
        goodsService.saveOrUpdateGoods(goods);
        return "redirect:/goods/all";
    }

    @GetMapping("/goods/remove/{id}")
    public String removeGoodsById(@PathVariable Long id){
        goodsService.deleteGoodsById(id);
        return "redirect:/goods/all";
    }


}
