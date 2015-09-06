package config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public final class HibernateUtils {  
	  
 //   private static final Logger LOG = Logger.getLogger(HibernateUtils.class);  
  
   
    public static SessionFactory newSessionFactory(final String pathToHibernateCfgXml) {  
 //       LOG.info("Loading Hibernate Session Factory with configurations from file "  
  //              + pathToHibernateCfgXml + "...");  
        Configuration hibernateConfiguration = new Configuration();  
        hibernateConfiguration.configure(pathToHibernateCfgXml);  
        ServiceRegistry serviceRegistry;serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
        		hibernateConfiguration.getProperties()).build();
        return hibernateConfiguration.buildSessionFactory(serviceRegistry);  
    }  
  
}  