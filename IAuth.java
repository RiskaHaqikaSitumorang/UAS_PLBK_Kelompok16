// IAuth.java
public interface IAuth {
    boolean login(String username, String password);
    void logout();
    boolean isAdmin();
    User getCurrentUser();
}