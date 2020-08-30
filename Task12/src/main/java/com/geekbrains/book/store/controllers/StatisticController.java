package com.geekbrains.book.store.controllers;

import com.geekbrains.book.store.DAO.AppLoggingAspect;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/statistic")
@AllArgsConstructor
public class StatisticController {
    private AppLoggingAspect appLoggingAspect;

    @GetMapping
    private String statistic(Model model){
        System.out.println(appLoggingAspect.getStatistic());
        model.addAttribute("statistic", appLoggingAspect.getStatistic());
        return "statistic-page";
    }
}
