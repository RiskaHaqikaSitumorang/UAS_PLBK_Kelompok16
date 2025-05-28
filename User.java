// File User.java

public class User {
    private static int idCounter = 1;
    
    private final int id;
    private final String username;
    private final String password;
    private final String role;

    public User(String username, String password, String role) {
        this.id = idCounter++;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public boolean login(String inputUsername, String inputPassword) {
        return this.username.equals(inputUsername) && this.password.equals(inputPassword);
    }

    // Getters
    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getRole() { return role; }
}