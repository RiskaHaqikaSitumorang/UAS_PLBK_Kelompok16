//file IuserMgt.java
public interface IUserMgt {
    boolean register(String username, String password, String role);
    void deleteUser(int userId);
    void viewAllUsers();
    User getUser(int userId);
    void notifyUser(String message);
}