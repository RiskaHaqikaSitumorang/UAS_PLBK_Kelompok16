// IEnrollment.java
public interface IEnrollment {
    void enrollCourse(int userId, int courseId);
    void viewUserCourses(int userId);
    void giveReview(int userId, int courseId, int rating, String comment);
    void updateProgress(int userId, int courseId, int progress);
}