package com.korchagin.courses.task5;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component(value = "dbs")
public class DBSession {
    private final String  config = "hibernate.cfg.xml";
    private final SessionFactory factory;

    public DBSession() {
        factory = new Configuration()
                .configure(config)
                .buildSessionFactory();
    }

    public Session getSession(){
        return factory.getCurrentSession();
    }

    public void closeSession(){
        factory.close();
    }
}
