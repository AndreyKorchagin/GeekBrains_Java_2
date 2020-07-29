package com.korchagin.java;

import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class DBAPI {
    private String confing;
    private EntityManager em;

    public DBAPI(String confing) {
        this.confing = confing;
    }

    public void connect(){
        EntityManagerFactory factory = new Configuration()
                .configure(confing)
                .buildSessionFactory();
        em = factory.createEntityManager();
    }

    public void getTransaction(String buyersName, String goodsName){
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT bg FROM BuyerGoods bg " +
                                        "INNER JOIN Buyers b ON b.id = bg.idBuyer " +
                                        "INNER JOIN Goods g ON g.id = bg.idGoods " +
                                        "WHERE b.name = :bName AND g.name = :gName");
        query.setParameter("bName", buyersName);
        query.setParameter("gName", goodsName);
        List<BuyerGoods> bg = query.getResultList();
        System.out.println(bg.size());
        if (bg.size() != 0) {
            System.out.printf("    id|id_buyer|id_goods|price|\n");
            for (int i = 0; i < bg.size(); i++) {
                bg.get(i).print();
            }
        } else {
            System.out.println("По таким параметрам не было покупок или пользователя");
        }
    }

    public void showProductsByConsumer(String buyerName){
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT b FROM Buyers b WHERE name = :name");
        query.setParameter("name", buyerName);
        List<Buyers> buyer = query.getResultList();
        if(buyer.size() != 0){
            System.out.println("Пользователь " + buyerName + " покупал следующие товары:");
            for (Goods g : buyer.get(0).getGoods()) {
                    System.out.println(g.getName());
            }
        } else {
            System.out.println("Пользователь с таким именем ничего не покупал!!!");
        }
        em.getTransaction().commit();
    }

    public void showConsumersByProductTitle(String goodsName){
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT g FROM Goods g WHERE name = :name");
        query.setParameter("name", goodsName);
        List<Goods> goods = query.getResultList();
        if(goods.size() != 0){
            System.out.println("Товар " + goodsName + " покупали следующие польщователи:");
            for (Buyers b : goods.get(0).getBuyers()) {
                System.out.println(b.getName());
            }
            em.getTransaction().commit();
        } else {
            System.out.println("Пользователь с таким именем ничего не покупал!!!");
        }
    }

    public void deleteConsumer(String buyerName){
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Buyers WHERE name = :name").setParameter("name", buyerName).executeUpdate();
        em.getTransaction().commit();
        System.out.printf("Пользователь с именем: %s был удален\n", buyerName);
    }

    public void deleteProduct(String goodsName){
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Goods WHERE name = :name").setParameter("name", goodsName).executeUpdate();
        em.getTransaction().commit();
        System.out.printf("Товар с именем: %s был удален\n", goodsName);

    }

    public void buy(String buyerName, String goodsName){
        em.getTransaction().begin();
        try {
            Buyers buyers = (Buyers) em.createQuery("SELECT b FROM Buyers b WHERE name = :name").setParameter("name", buyerName).getSingleResult();
            Goods goods = (Goods) em.createQuery("SELECT g FROM Goods g WHERE name = :name").setParameter("name", goodsName).getSingleResult();
            long idBuyer = buyers.getId();
            long idGoods = goods.getId();
            int price = goods.getPrice();
            em.persist(new BuyerGoods(idBuyer, idGoods, price));
            em.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
        }
    }


}