package org.dao.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	  private static SessionFactory sessionFactory = buildSessionFactory();;
	   
	  public static SessionFactory buildSessionFactory() {
	      try {
	    	  return sessionFactory = new Configuration().configure().buildSessionFactory();
	      } catch (Throwable ex) {
	        System.err.println("Initial SessionFactory creation failed." + ex);
	        throw new ExceptionInInitializerError(ex);
	      }
	    }

	    public static SessionFactory getSessionFactory() {
	      return sessionFactory;
	    }
	}