// File: Auth.java
import java.util.ArrayList;
import java.util.List;

public class Auth implements IAuth {
    private User currentUser;
    private final List<User> users;

    public Auth() {
        users = new ArrayList<>();
        // Tambahkan user admin dan siswa default
        users.add(new User("admin", "admin123", "admin"));
        users.add(new User("siswa", "siswa123", "siswa"));
    }

    @Override
    public boolean login(String username, String password) {
        for (User user : users) {
            if (user.login(username, password)) {
                currentUser = user;
                return true;
            }
        }
        return false;
    }

    @Override
    public void logout() {
        currentUser = null;
    }

    @Override
    public boolean isAdmin() {
        return currentUser != null && "admin".equals(currentUser.getRole());
    }

    @Override
    public User getCurrentUser() {
        return currentUser;
    }

    // Tambahkan user baru (untuk fitur registrasi)
    public boolean register(String username, String password, String role) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return false; // Username sudah ada
            }
        }
        users.add(new User(username, password, role));
        return true;
    }
}