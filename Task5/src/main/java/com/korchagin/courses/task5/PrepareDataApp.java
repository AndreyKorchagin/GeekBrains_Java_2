package com.korchagin.courses.task5;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class PrepareDataApp {
    public static void forcePrepareData(DBSession factory) {
//        SessionFactory factory = new Configuration()
//                .configure("hibernate.cfg.xml")
//                .buildSessionFactory();
        Session session = null;
        try {
            String sql = Files.lines(Paths.get("full.sql")).collect(Collectors.joining(" "));
//            session = factory.getCurrentSession();
            session = factory.getSession();
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
            System.out.println("OK");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//            factory.close();
//            factory.closeSession();
            if (session != null) {
                session.close();
            }
        }
    }
}

