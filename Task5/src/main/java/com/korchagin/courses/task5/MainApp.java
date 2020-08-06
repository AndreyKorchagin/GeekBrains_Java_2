package com.korchagin.courses.task5;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Task5Config.class);

        DBSession dbs = context.getBean("dbs", DBSession.class);
        PrepareDataApp.forcePrepareData(dbs);

        UsersService usersService = context.getBean("usersService", UsersService.class);
        GoodsService goodsService = context.getBean("goodsService", GoodsService.class);

        List<Users> users = usersService.getAllUsers();
        List<Goods> goods = goodsService.getAllGoods();

        for (Users u: users) {
            u.print();
        }
        for (Goods g: goods) {
            g.print();
        }


        dbs.closeSession();
        context.close();
    }
}
