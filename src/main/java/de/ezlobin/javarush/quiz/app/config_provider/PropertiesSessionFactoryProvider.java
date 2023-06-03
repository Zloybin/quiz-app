package de.ezlobin.javarush.quiz.app.config_provider;

import de.ezlobin.javarush.quiz.app.model.Noun;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PropertiesSessionFactoryProvider implements SessionFactoryProvider{

    @Override
    public SessionFactory getSessionFactory() {
        return new Configuration()
                .addAnnotatedClass(Noun.class)
                .buildSessionFactory();
    }
}
