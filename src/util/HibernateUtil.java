
package util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

/**
 * @netbeans.hibernate.util
 */
public class HibernateUtil
{
   private static Log log = LogFactory.getLog(HibernateUtil.class);
   
   private static SessionFactory sessionFactory;
   
   private static SessionFactory getSessionFactory()
   {
      try
      {
         if (sessionFactory == null)
         {
            // Create the SessionFactory from hibernate.cfg.xml
            sessionFactory = new Configuration().configure().buildSessionFactory();
         }         
      }
      catch (Throwable ex)
      {
         log.error("Initial SessionFactory creation failed.", ex);
         throw new ExceptionInInitializerError(ex);
      }
      return  sessionFactory;
   }
   
   public static final ThreadLocal session = new ThreadLocal();
   
   public static Session currentSession() throws HibernateException
   {
      Session s = (Session) session.get();
      if (s == null)
      {
         s = getSessionFactory().openSession();
         session.set(s);
      }
      return s;
   }
   
   public static void closeSession() throws HibernateException
   {
      Session s = (Session) session.get();
      session.set(null);
      if (s != null)
         s.close();
   }
   
}