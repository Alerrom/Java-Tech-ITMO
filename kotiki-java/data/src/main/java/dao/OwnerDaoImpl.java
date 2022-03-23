package dao;

import entity.Owner;
import entity.OwnerDb;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class OwnerDaoImpl implements OwnerDao {

    @Override
    public Owner findById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        OwnerDb ownerDb = session.get(OwnerDb.class, id);
        session.close();

        return ownerDb;
    }

    @Override
    public void save(Owner owner) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();
        session.save(owner);
        transaction.commit();

        session.close();
    }

    @Override
    public void update(Owner owner) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();
        session.update(owner);
        transaction.commit();

        session.close();
    }

    @Override
    public void delete(Owner owner) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();
        session.delete(owner);
        transaction.commit();

        session.close();
    }

    @Override
    public List<Owner> findAll() {
        //TODO: implement method!
        return null;
    }
}
