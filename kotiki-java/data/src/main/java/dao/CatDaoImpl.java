package dao;

import entity.CatDb;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class CatDaoImpl implements CatDao {
    @Override
    public CatDb findById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CatDb catDb = session.get(CatDb.class, id);
        session.close();

        return catDb;
    }

    @Override
    public void save(CatDb catDb) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();
        session.save(catDb);
        transaction.commit();

        session.close();
    }

    @Override
    public void update(CatDb catDb) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();
        session.update(catDb);
        transaction.commit();

        session.close();
    }

    @Override
    public void delete(CatDb catDb) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();
        session.delete(catDb);
        transaction.commit();

        session.close();
    }

    @Override
    public List<CatDb> findAll() {
        //TODO: implement method
        return null;
    }

    @Override
    public List<CatDb> findCatFriends() {
        //TODO: implement method
        return null;
    }
}
