// file IEnrolment.java

public interface IEnrollment {
    void viewAllCourses();
    boolean exists(int id);
    void enrollCourse(int userId, int courseId);
    void viewUserCourses(int userId);
    void notifyUser(String message);
    boolean isEnrolled(int userId, int courseId);
}
