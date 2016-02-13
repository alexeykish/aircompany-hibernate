package by.pvt.kish.aircompany.utils;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Utility class that creates the SessionFactory from standard (hibernate.cfg.xml) config file,
 * creates ,return and close session
 *
 * @author Kish Alexey
 */
public class HibernateUtil {
    private static Logger logger = Logger.getLogger(HibernateUtil.class);
    private static SessionFactory sessionFactory = null;
    private final ThreadLocal<Session> threadSession = new ThreadLocal<Session>();
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
        Session session = threadSession.get();
        if (session == null) {
            session = sessionFactory.openSession();
            threadSession.set(session);
            logger.info("open session: " + session);
        }
        return session;
    }

    public static HibernateUtil getUtil() {
        if (util == null) {
            util = new HibernateUtil();
        }
        return util;
    }

    public void closeSession(Session session){
        if(session != null){
            try{
                threadSession.remove();
                session.close();
                logger.info("session closed");
            }
            catch(HibernateException e){
                logger.error(e);
            }
        }
    }
}
