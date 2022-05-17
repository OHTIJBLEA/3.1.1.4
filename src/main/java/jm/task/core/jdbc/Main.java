package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
//        Util.getConnection();
//        UserDao userDao = new UserDaoJDBCImpl();
        Util.getSessionFactory();
        UserDao userDao = new UserDaoHibernateImpl();

        userDao.createUsersTable();
        for (int i = 0; i < 4; i++) {
            userDao.saveUser("Name" + i, "LastName" + i, (byte) (i + 10));
            System.out.println("Пользователь Name" + i + " добавлен в базу данных");
        }
        userDao.removeUserById(1);
        userDao.getAllUsers().stream().forEach(System.out::println);
//        userDao.cleanUsersTable();
//        userDao.dropUsersTable();
    }
}
