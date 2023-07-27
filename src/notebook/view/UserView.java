package notebook.view;

import notebook.controller.UserController;
import notebook.model.User;
import notebook.util.Commands;

import java.util.List;
import java.util.Scanner;

public class UserView {
    private final UserController userController;

    public UserView(UserController userController) {
        this.userController = userController;
    }

    public void run() {
        Commands com;

        while (true) {
            String command = prompt("Input command: ");
            com = Commands.valueOf(command);
            if (com == Commands.EXIT) return;
            switch (com) {
                case CREATE:
                    String firstName = prompt("Name: ");
                    String lastName = prompt("Last name: ");
                    String phone = prompt("Phone number: ");
                    userController.saveUser(new User(firstName, lastName, phone));
                    break;
                case READ:
                    String id = prompt("Input user ID: ");
                    try {
                        User user = userController.readUser(Long.parseLong(id));
                        System.out.println(user);
                        System.out.println();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case LIST: // Чтение списка пользователей
                    try {
                        List<User> users = userController.readAllUsers();
                        System.out.println(users);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case UPDATE: // обновление данных пользователя
                    try {
                        Long userId = Long.parseLong(prompt("Input user ID: "));
                        String Name = prompt("Input name: ");
                        String lName = prompt("Input last name: ");
                        String phoneNumber = prompt("Input phone number: ");
                        User updatedUser = new User(Name, lName, phoneNumber);
                        System.out.println(updatedUser);
                        userController.updateUser(userId, updatedUser);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case DELETE: // удаление пользователя
                    try {
                        Long userId = Long.parseLong(prompt("Input ID of user to delete: "));
                        userController.deleteUser(userId);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case NONE: // Ничегонеделание
                    break;
            }
        }
    }
    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
}
