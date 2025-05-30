// ICourseMgt

import java.time.LocalTime;

public interface ICourseMgt {
    void addCourse(String judul, String deskripsi, String kategori, String instruktur, 
                   String hari, LocalTime waktuMulai, LocalTime waktuSelesai);
    void editCourse(int id, String judulBaru, String deskripsiBaru, String kategoriBaru,
                    String instrukturBaru, String hariBaru, LocalTime waktuMulaiBaru, LocalTime waktuSelesaiBaru);
    void deleteCourse(int id);
    void viewAllCourses();
    CourseDetail getCourse(int id);
    boolean exists(int id);
    void searchCourse(String kataKunci);
    void notifyUser(String message);
}
