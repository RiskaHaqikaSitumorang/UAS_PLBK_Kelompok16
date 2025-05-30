// file enrolment.java
import java.util.HashMap;
import java.util.Map;

public class Enrollment implements IEnrollment {
    private final Map<Integer, EnrollmentDetail> daftarEnrollment;
    private final ICourseMgt kursus;
    private int enrollmentCounter;

    public Enrollment(ICourseMgt kursus) {
        this.kursus = kursus;
        this.daftarEnrollment = new HashMap<>();
        this.enrollmentCounter = 1;
    }

    @Override
    public void enrollCourse(int idSiswa, int idKursus) {
        if (!kursus.exists(idKursus)) {
            notifyUser("Kursus tidak ditemukan");
            return;
        }
        if (isEnrolled(idSiswa, idKursus)) {
            notifyUser("Anda sudah terdaftar di kursus ini");
            return;
        }
        EnrollmentDetail enrollment = new EnrollmentDetail(enrollmentCounter++, idSiswa, idKursus);
        daftarEnrollment.put(enrollment.getId(), enrollment);
        notifyUser("Berhasil mendaftar kursus");
    }

    @Override
    public void viewUserCourses(int idSiswa) {
        System.out.println("============== KURSUS ANDA ==============");
        boolean adaKursus = false;
        for (EnrollmentDetail enrollment : daftarEnrollment.values()) {
            if (enrollment.getUserId() == idSiswa) {
                CourseDetail detailKursus = kursus.getCourse(enrollment.getCourseId());
                System.out.println("ID Pendaftaran: " + enrollment.getId());
                System.out.println(detailKursus);
                System.out.println("Progress: " + enrollment.getProgress() + "%");
                System.out.println("-------------------------------------------");
                adaKursus = true;
            }
        }
        if (!adaKursus) {
            notifyUser("Anda belum terdaftar di kursus apapun");
        }
    }

    public void updateProgress(int idSiswa, int idKursus, int progress) {
        if (!isEnrolled(idSiswa, idKursus)) {
            notifyUser("Anda belum terdaftar di kursus ini");
            return;
        }
        if (progress < 0 || progress > 100) {
            notifyUser("Progress harus antara 0 sampai 100");
            return;
        }
        for (EnrollmentDetail enrollment : daftarEnrollment.values()) {
            if (enrollment.getUserId() == idSiswa && enrollment.getCourseId() == idKursus) {
                enrollment.setProgress(progress);
                notifyUser("Progress berhasil diperbarui");
                return;
            }
        }
    }

    @Override
    public void notifyUser(String message) {
        System.out.println("[SISTEM] " + message);
    }

    @Override
    public void viewAllCourses() {
        kursus.viewAllCourses();
    }

    @Override
    public boolean exists(int id) {
        return kursus.exists(id);
    }

    public boolean isEnrolled(int idSiswa, int idKursus) {
        for (EnrollmentDetail enrollment : daftarEnrollment.values()) {
            if (enrollment.getUserId() == idSiswa && enrollment.getCourseId() == idKursus) {
                return true;
            }
        }
        return false;
    }
}
