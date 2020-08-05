package com.korchagin.java.task3;

import java.util.List;
import java.util.concurrent.CountDownLatch;


public class MainApp {
    public static void main(String[] args) throws InterruptedException {
        PrepareDataApp.forcePrepareData();

        DBAPI db = new DBAPI("hibernate.cfg.xml");
        db.connect();
        final CountDownLatch cdl = new CountDownLatch(8);
        List<Users> users = db.getUsers();
        OptimisticLock(db, users, cdl);
        PessimisticLock(db, users, cdl);
        db.close();
    }

    public static void OptimisticLock(DBAPI db, List<Users> users, CountDownLatch cdl){
        long time = System.currentTimeMillis();
        for (Users u: users) {
            DBAPI dbThread = new DBAPI("hibernate.cfg.xml");
            dbThread.connect();

            new Thread(() -> {
                    try {
                        dbThread.CurrentUp( 100, u);
                        cdl.countDown();
                        System.out.println("Поток " + u.getId() + " готов");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }).start();
        }
        try {
            cdl.await();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        if (db.cheackRate() == 100 * 8 * 1000){
            System.out.printf("OK + time: %d\n", System.currentTimeMillis() - time);
        }
    }


    public static void PessimisticLock(DBAPI db, List<Users> users, CountDownLatch cdl){
        long time = System.currentTimeMillis();
        for (Users u: users) {
            DBAPI dbThread = new DBAPI("hibernate.cfg.xml");
            dbThread.connect();
            new Thread(() -> {
                try {
                    dbThread.CurrentUpWithLock( 100, u);
                    cdl.countDown();
                    System.out.println("Поток " + u.getId() + " готов");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        try {
            cdl.await();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        if (db.cheackRate() == 100 * 8 * 1000){
            System.out.printf("OK + time: %d\n", System.currentTimeMillis() - time);
        } else {
            System.out.println("KO");
        }

    }
}

//OPTIMISTIC OK + time: 14791
//PESIMISTIC OK + time: 6530
