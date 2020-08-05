package com.korchagin.java;

public class MainApp {
    public static void main(String[] args) {

        PrepareDataApp.forcePrepareData();

        DBAPI DB = new DBAPI("hibernate.cfg.xml");
        DB.connect();
        DB.getTransaction("USER1", "GOODS1");
        DB.showProductsByConsumer("USER1");
        DB.showConsumersByProductTitle("GOODS1");
        DB.deleteConsumer("USER1");
        DB.deleteProduct("GOODS4");
        DB.buy("USER2", "GOODS2");

    }
}
