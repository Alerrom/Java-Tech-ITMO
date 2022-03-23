package dao;

import entity.Cat;
import entity.CatDb;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class CatDaoImpl implements CatDao {
    @Override
    public Cat findById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CatDb cat = session.get(CatDb.class, id);
        session.close();

        return cat;
    }

    @Override
    public void save(Cat cat) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();
        session.save(cat);
        transaction.commit();

        session.close();
    }

    @Override
    public void update(Cat cat) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();
        session.update(cat);
        transaction.commit();

        session.close();
    }

    @Override
    public void delete(Cat cat) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();
        session.delete(cat);
        transaction.commit();

        session.close();
    }

    @Override
    public List<Cat> findAll() {
        //TODO: implement method
        return null;
    }

    @Override
    public List<Cat> findCatFriends() {
        //TODO: implement method
        return null;
    }
}
