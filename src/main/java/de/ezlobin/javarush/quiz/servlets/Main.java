package de.ezlobin.javarush.quiz.servlets;

import de.ezlobin.javarush.quiz.app.config_provider.PropertiesSessionFactoryProvider;
import de.ezlobin.javarush.quiz.app.config_provider.SessionFactoryProvider;
import de.ezlobin.javarush.quiz.app.model.Noun;
import de.ezlobin.javarush.quiz.app.repository.NounRepository;

import org.hibernate.SessionFactory;

import java.util.List;


public class Main {
    public static void main(String[] args) {

        SessionFactoryProvider provider = new PropertiesSessionFactoryProvider();
        SessionFactory sessionFactory = provider.getSessionFactory();
        NounRepository nounRepository = new NounRepository(sessionFactory);
        List<Noun> beforeChanges = nounRepository.getAll();
        for (Noun noun : beforeChanges) {
            System.out.println("---> " + noun);
        }

        Noun newData = Noun.builder()
                .article("der")
                .word("Junge")
                .translation("Мальчик")
                .build();

        nounRepository.add(newData);

        List<Noun> afterChanges = nounRepository.getAll();
        for (Noun noun : afterChanges) {
            System.out.println("---> " + noun);
        }

    }
}