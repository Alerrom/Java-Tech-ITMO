package dao;

import entity.Cat;
import entity.CatDb;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
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
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        String sql = "SELECT * FROM cats";
        Query query = session.createSQLQuery(sql);
        List<Cat> cats = query.list();
        session.close();
        return cats;
    }

    @Override
    public List<Integer> findCatFriends(int catId) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        String sql = "SELECT friend_id FROM friends WHERE cat_id = " + catId;
        Query query = session.createSQLQuery(sql);
        List<Integer> catsId = query.list();
        session.close();
        return catsId;
    }
}
