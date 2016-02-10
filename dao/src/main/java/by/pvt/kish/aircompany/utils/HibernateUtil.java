package by.pvt.kish.aircompany.utils;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * @author Kish Alexey
 */
public class HibernateUtil {
    private static Logger logger = Logger.getLogger(HibernateUtil.class);
    private static SessionFactory sessionFactory = null;
    private final ThreadLocal threadSession = new ThreadLocal();
    private static HibernateUtil util = null;

    private HibernateUtil() {
        try {
            Configuration configuration = new Configuration().configure();
            configuration.setNamingStrategy(new CustomNamingStrategy());
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
        } catch (Exception e) {
            logger.error("Initial session factory creating failed" + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public Session getSession() {
        Session session = (Session) threadSession.get();
        if (session == null) {
            session = sessionFactory.openSession();
            threadSession.set(session);
        }
        return session;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static HibernateUtil getUtil() {
        if (util == null) {
            util = new HibernateUtil();
        }
        return util;
    }

    public static Session beginTransaction() {
        Session hibernateSession = sessionFactory.getCurrentSession();
        hibernateSession.beginTransaction();
        return hibernateSession;
    }

    public static void commitTransaction() {
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

    public static void rollbackTransaction() {
        sessionFactory.getCurrentSession().getTransaction().rollback();
    }
}
