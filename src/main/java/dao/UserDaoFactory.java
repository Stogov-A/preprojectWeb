package dao;

import util.DBHelper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UserDaoFactory {

    public UserDAO getDao() {
        FileInputStream fileInputStream;
        Properties properties = new Properties();
        UserDAO userDAO = null;
        try {
            String propFileName = "daoType.property";
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            properties.load(inputStream);
            if (properties.getProperty("daoType").equals("Hibernate")) {
                //  userDAO = new UserHibernateDAO();
            } else if (properties.getProperty("daoType").equals("jdbc")) {
                userDAO = new UserJdbcDAO(DBHelper.getDbHelper().getConnection());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userDAO;
    }
}
