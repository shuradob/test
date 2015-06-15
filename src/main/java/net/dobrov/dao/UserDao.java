package net.dobrov.dao;

/**
 * Created by Shura on 04.06.2015.
 */
import java.util.List;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import net.dobrov.model.User;

import net.dobrov.util.HibernateUtil;

public class UserDao {

    SessionFactory sessionFactory=HibernateUtil.getSessionFactory();

    public UserDao() {

    }
    public List<User> getUsers()

    {

        Session s = sessionFactory.openSession();

        List<User> users = s.createCriteria(User.class).list();

        s.close();
        return users;

    }

    public User getUser(int id)

    {

        Session s=sessionFactory.openSession();

        Criteria cr=s.createCriteria(User.class);

        cr.add(Restrictions.eq("id", id));

        User user =(User) cr.list().get(0);

        s.close();
        return user;

    }

    public User create(User user) {
        Session session = sessionFactory.openSession();

        Transaction tx = null;
        int id = 0;
        try{
            tx = session.beginTransaction();
            id = (int) session.save(user);
            user.setId(id);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return user;
    }

    public User update(User user) {
        Session session = sessionFactory.openSession();

        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(user);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return user;
    }

    public void remove(int id) {
        Session session = sessionFactory.openSession();

        User user = (User) session.load(User.class, id);
        if (user != null) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.delete(user);

                //This makes the pending delete to be done
                session.flush();
                tx.commit();
            } catch (HibernateException e) {
                if (tx != null) tx.rollback();
                e.printStackTrace();
            } finally {
                session.close();
            }
        }
    }

}
