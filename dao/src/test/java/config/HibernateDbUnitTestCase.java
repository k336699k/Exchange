package config;

import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;

public abstract class HibernateDbUnitTestCase extends DBTestCase {  
	  
     
  
    private static SessionFactory sessionFactory;  
    protected Session session;  
  
    
    public HibernateDbUnitTestCase() {  
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "com.mysql.jdbc.Driver");  
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:mysql://localhost:3306/test");  
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "root");  
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "admin123");  
    }  
  
   
    @Before  
    public void setUp() throws Exception {  
    //    HSQLServerUtil.getInstance().start("DBNAME");  
  
       
        if (sessionFactory == null) {  
            sessionFactory = HibernateUtils.newSessionFactory("hibernateTest.cfg.xml");  
        }  
  
        session = sessionFactory.openSession();  
  
        super.setUp();  
    }  
  
    
    @After  
    public void tearDown() throws Exception {  
        session.close();  
        super.tearDown();  
      //  HSQLServerUtil.getInstance().stop();  
    }  
  
     
    protected IDataSet getDataSet() throws Exception {  
        throw new Exception("Specify data set for test: " + this.getClass().getSimpleName());  
    }  
  
    
    protected DatabaseOperation getSetUpOperation() throws Exception {  
    	
    	return DatabaseOperation.CLEAN_INSERT;  
    }  
  
      
    protected DatabaseOperation getTearDownOperation() throws Exception {  
        return DatabaseOperation.DELETE_ALL;  
    }  
  
  
}  