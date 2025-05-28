//File Notification.java
public class Notification implements INotification {
    @Override
    public void notifyUser(String message) {
        System.out.println("[SISTEM] " + message);
    }
}