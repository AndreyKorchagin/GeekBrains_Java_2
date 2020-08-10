package com.korchagin.java.courses.task6;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@Component(value = "dbs")
public class DBSession {
    private final String config = "hibernate.cfg.xml";
    private final SessionFactory factory;
    Session session = null;

    public DBSession() {
        factory = new Configuration()
                .configure(config)
                .buildSessionFactory();
    }

    @PostConstruct
    private void init(){
        try {
            String sql = Files.lines(Paths.get("full.sql")).collect(Collectors.joining(" "));
            session = getSession();
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
            System.out.println("OK");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public Session getSession(){
        return factory.getCurrentSession();
    }

    public void closeSession(){
        factory.close();
    }
}
