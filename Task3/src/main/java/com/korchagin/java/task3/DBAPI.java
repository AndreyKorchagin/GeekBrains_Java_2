package com.korchagin.java.task3;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.*;
import java.util.List;
import java.util.Random;

import static java.lang.Thread.sleep;

public class DBAPI {
    private String confing;
    private EntityManager em;
    private SessionFactory factory;
    private Session session;

    public DBAPI(String confing) {
        this.confing = confing;
    }

    public void connect(){
        factory = new Configuration()
                .configure(confing)
                .buildSessionFactory();
        em = factory.createEntityManager();
        session = null;
    }

    public List<Users> getUsers() {
        em.getTransaction().begin();
        List<Users> users = em.createQuery("SELECT u FROM Users u").getResultList();
        em.getTransaction().commit();
        return users;
    }

    public void CurrentUp(Integer delta, Users currentUser) throws InterruptedException {
        Random r = new Random();
        Lots lt = null;
        System.out.println(currentUser.getId());
        em.getTransaction().begin();
        for (int i = 0; i < 1000; i++) {
            long lotId = r.nextInt(4) + 1;
            lt = em.find(Lots.class, (Long) lotId);
            lt.setCurrentRate(lt.getCurrentRate() + delta);
            lt.setUser(currentUser);
            sleep(1);
        }
        try{
            em.getTransaction().commit();

        } catch (OptimisticLockException e){
            em.getTransaction().rollback();
            CurrentUp(delta, currentUser);
            e.printStackTrace();
        }
        em.close();
    }

    public void CurrentUpWithLock(Integer delta, Users currentUser) throws InterruptedException {
        Random r = new Random();
        System.out.println(currentUser.getId());
        for (int i = 0; i < 1000; i++) {
            session = factory.getCurrentSession();
            session.beginTransaction();
            long lotId = r.nextInt(4) + 1;
            Query query = session.createQuery("SELECT l FROM Lots l WHERE l.id = :id", Lots.class);
            query.setParameter("id", lotId);
            query.setLockMode(LockModeType.PESSIMISTIC_WRITE);
            Lots lt = (Lots) query.getSingleResult();
            lt.setCurrentRate(lt.getCurrentRate() + delta);
            lt.setUser(currentUser);
            session.getTransaction().commit();
            sleep(1);
        }
        session.close();


    }

    public Long cheackRate(){
        Long result = (Long) em.createQuery("SELECT sum(currentRate) FROM Lots").getSingleResult();
        System.out.println(result);
        return result;
    }

    public void close(){
        em.close();
    }
}
