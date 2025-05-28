// File: Course.java
import java.util.*;

public class Course implements ICourse {
    private final Map<Integer, CourseDetail> courses;
    private int idCounter;

    public Course() {
        courses = new HashMap<>();
        idCounter = 1;
    }

    @Override
    public void addCourse(String title, String description, String category) {
        CourseDetail course = new CourseDetail(idCounter, title, description, category);
        courses.put(idCounter++, course);
        System.out.println("[Course added successfully]");
    }

    @Override
    public void editCourse(int id, String newTitle, String newDescription, String newCategory) {
        if (courses.containsKey(id)) {
            CourseDetail course = courses.get(id);
            course.setTitle(newTitle);
            course.setDescription(newDescription);
            course.setCategory(newCategory);
            System.out.println("[Course updated successfully]");
        } else {
            System.out.println("[Course not found]");
        }
    }

    @Override
    public void deleteCourse(int id) {
        if (courses.remove(id) != null) {
            System.out.println("[Course deleted successfully]");
        } else {
            System.out.println("[Course not found]");
        }
    }

    @Override
    public void viewAllCourses() {
        if (courses.isEmpty()) {
            System.out.println("[No courses available]");
            return;
        }

        System.out.println("============== COURSE LIST ==============");
        for (CourseDetail course : courses.values()) {
            System.out.println(course);
            System.out.println("-------------------------------------------");
        }
    }

    @Override
    public void searchCourse(String keyword) {
        boolean found = false;
        System.out.println("============== SEARCH RESULTS ==============");
        for (CourseDetail course : courses.values()) {
            if (course.matchesKeyword(keyword)) {
                System.out.println(course);
                System.out.println("-------------------------------------------");
                found = true;
            }
        }
        if (!found) {
            System.out.println("[No courses found matching your search]");
        }
    }

    @Override
    public CourseDetail getCourse(int id) {
        return courses.get(id);
    }

    @Override
    public boolean exists(int id) {
        return courses.containsKey(id);
    }
}