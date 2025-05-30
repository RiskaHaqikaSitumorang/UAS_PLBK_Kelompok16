// file IReview.java
public interface IReview {
    void giveReview(int userId, int courseId, int rating, String comment);
    void notifyUser(String message);
}