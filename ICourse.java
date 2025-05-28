// ICourse.java
public interface ICourse {
    void addCourse(String title, String description, String category);
    void editCourse(int id, String newTitle, String newDescription, String newCategory);
    void deleteCourse(int id);
    void viewAllCourses();
    void searchCourse(String keyword);
    CourseDetail getCourse(int id);
    boolean exists(int id);
}