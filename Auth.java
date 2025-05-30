// File: Auth.java
import java.util.ArrayList;
import java.util.List;

public class Auth implements IUserMgt {
    private User currentUser;
    private final List<User> users;

    public Auth() {
        users = new ArrayList<>();
        users.add(new User("admin", "admin123", "admin"));
        users.add(new User("siswa", "siswa123", "siswa"));
    }

    public boolean login(String username, String password) {
        for (User user : users) {
            if (user.login(username, password)) {
                currentUser = user;
                return true;
            }
        }
        return false;
    }

    public void logout() {
        currentUser = null;
    }

    public boolean isAdmin() {
        return currentUser != null && "admin".equals(currentUser.getRole());
    }

    public User getCurrentUser() {
        return currentUser;
    }

    @Override
    public boolean register(String username, String password, String role) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return false;
            }
        }
        users.add(new User(username, password, role));
        return true;
    }

    @Override
    public void deleteUser(int userId) {
        users.removeIf(user -> user.getId() == userId);
    }

    @Override
    public void viewAllUsers() {
        if (users.isEmpty()) {
            System.out.println("[Belum ada pengguna terdaftar]");
            return;
        }
        System.out.println("============== DAFTAR PENGGUNA ==============");
        System.out.println("ID\tUsername\tRole");
        System.out.println("-------------------------------------------");
        for (User user : users) {
            System.out.printf("%d\t%-10s\t%s\n", user.getId(), user.getUsername(), user.getRole());
        }
    }

    @Override
    public User getUser(int userId) {
        for (User user : users) {
            if (user.getId() == userId) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void notifyUser(String message) {
        System.out.println("[SISTEM] " + message);
    }
}
