package de.ezlobin.javarush.quiz.app.config_provider;

import org.hibernate.SessionFactory;

public interface SessionFactoryProvider {
    SessionFactory getSessionFactory();
}
