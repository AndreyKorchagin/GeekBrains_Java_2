package com.korchagin.courses.task5;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GoodsRepository {
    private final DBSession dbs;

    @Autowired
    public GoodsRepository(DBSession dbs) {
        this.dbs = dbs;
    }

    public List<Goods> getAllGoods(){
        Session session = dbs.getSession();
        session.getTransaction().begin();
        List<Goods> goods = session.createQuery("SELECT g FROM Goods g", Goods.class).getResultList();
        session.getTransaction().commit();
        return goods;
    }

    public Goods getGoodsById(Long id){
        Session session = dbs.getSession();
        session.getTransaction().begin();
        Goods goods = session.find(Goods.class, id);
        session.getTransaction().commit();
        return goods;
    }

    public void saveGoods(Goods goods){
        Session session = dbs.getSession();
        session.getTransaction().begin();
        session.save(goods);
        session.getTransaction().commit();
    }

    public void saveAllGoods (List<Goods> goods) {
        for (Goods g : goods) {
            saveGoods(g);
        }
    }
}
