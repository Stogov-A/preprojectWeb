package dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import util.DBHelper;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UserDaoFactory {

    public UserDAO getDao() {
        Properties properties = new Properties();
        UserDAO userDAO = null;
        try {
            String propFileName = "daoType.property";
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            properties.load(inputStream);
            if (properties.getProperty("daoType").equals("Hibernate")) {
                userDAO = new UserHibernateDAO(createSessionFactory());
            } else if (properties.getProperty("daoType").equals("jdbc")) {
                userDAO = new UserJdbcDAO(DBHelper.getConnection());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userDAO;
    }

    private static SessionFactory createSessionFactory() {
        Configuration configuration = DBHelper.getConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
