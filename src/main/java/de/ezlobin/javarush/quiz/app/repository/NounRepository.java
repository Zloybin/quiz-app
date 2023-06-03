package de.ezlobin.javarush.quiz.app.repository;

import de.ezlobin.javarush.quiz.app.model.Noun;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class NounRepository {

    private final SessionFactory sessionFactory;

    public NounRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void add(Noun noun) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(noun);
            transaction.commit();
        } catch (RuntimeException ex) {
            System.out.println("Ошибка: " + ex.getMessage());
        }
    }

    public List<Noun> getAll() {
        List<Noun> listObject = null;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<Noun> nounsQuery = session.createQuery("from Noun ", Noun.class);
            List<Noun> list = nounsQuery.list();
            listObject = list;
            transaction.commit();
        } catch (RuntimeException ex) {
            System.out.println("Ошибка: " + ex.getMessage());
        }
        return listObject;
    }

}
