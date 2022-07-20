package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();


        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser("Name", "LastName", (byte) 5);


//        userService.createUsersTable();
//
//        for (int i = 0; i < 4; i++) {
//            userService.saveUser("Name" + i, "LastName" + i, (byte) (i + 10));
//            System.out.println("Пользователь Name" + i + " добавлен в базу данных");
//        }
//        userService.removeUserById(1);
//        userService.getAllUsers().stream().forEach(System.out::println);
//        userService.cleanUsersTable();
//        userService.dropUsersTable();
    }
}
