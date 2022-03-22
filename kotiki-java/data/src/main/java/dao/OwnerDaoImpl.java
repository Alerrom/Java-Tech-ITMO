package dao;

import entity.OwnerDb;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class OwnerDaoImpl implements OwnerDao {
    @Override
    public OwnerDb findById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        OwnerDb ownerDb = session.get(OwnerDb.class, id);
        session.close();
        return ownerDb;
    }

    @Override
    public void save(OwnerDb ownerDb) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();
        session.save(ownerDb);
        transaction.commit();

        session.close();
    }

    @Override
    public void update(OwnerDb ownerDb) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();
        session.update(ownerDb);
        transaction.commit();

        session.close();
    }

    @Override
    public void delete(OwnerDb ownerDb) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();
        session.delete(ownerDb);
        transaction.commit();

        session.close();
    }

    @Override
    public List<OwnerDb> findAll() {
        //TODO: impl method
        return null;
    }
}
