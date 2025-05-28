import java.util.*;

public class Enrollment implements IEnrollment {
    private final Map<Integer, EnrollmentDetail> daftarEnrollment;
    private final ICourse kursus;
    private int enrollmentCounter;

    public Enrollment(ICourse kursus) {
        this.kursus = kursus;
        this.daftarEnrollment = new HashMap<>();
        this.enrollmentCounter = 1;
    }

    @Override
    public void enrollCourse(int idSiswa, int idKursus) {
        if (!kursus.exists(idKursus)) {
            System.out.println("[Kursus tidak ditemukan]");
            return;
        }

        if (sudahTerdaftar(idSiswa, idKursus)) {
            System.out.println("[Anda sudah terdaftar di kursus ini]");
            return;
        }

        EnrollmentDetail enrollment = new EnrollmentDetail(enrollmentCounter++, idSiswa, idKursus);
        daftarEnrollment.put(enrollment.getId(), enrollment);
        System.out.println("[Berhasil mendaftar kursus]");
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
            System.out.println("[Anda belum terdaftar di kursus apapun]");
        }
    }

    @Override
    public void giveReview(int idSiswa, int idKursus, int rating, String komentar) {
        if (!sudahTerdaftar(idSiswa, idKursus)) {
            System.out.println("[Anda belum terdaftar di kursus ini]");
            return;
        }

        if (rating < 1 || rating > 5) {
            System.out.println("[Rating harus antara 1 sampai 5]");
            return;
        }

        for (EnrollmentDetail enrollment : daftarEnrollment.values()) {
            if (enrollment.getUserId() == idSiswa && enrollment.getCourseId() == idKursus) {
                enrollment.addReview(rating, komentar);
                System.out.println("[Ulasan berhasil dikirim]");
                return;
            }
        }
    }

    @Override
    public void updateProgress(int idSiswa, int idKursus, int progress) {
        if (!sudahTerdaftar(idSiswa, idKursus)) {
            System.out.println("[Anda belum terdaftar di kursus ini]");
            return;
        }

        if (progress < 0 || progress > 100) {
            System.out.println("[Progress harus antara 0 sampai 100]");
            return;
        }

        for (EnrollmentDetail enrollment : daftarEnrollment.values()) {
            if (enrollment.getUserId() == idSiswa && enrollment.getCourseId() == idKursus) {
                enrollment.setProgress(progress);
                System.out.println("[Progress berhasil diperbarui]");
                return;
            }
        }
    }

    private boolean sudahTerdaftar(int idSiswa, int idKursus) {
        for (EnrollmentDetail enrollment : daftarEnrollment.values()) {
            if (enrollment.getUserId() == idSiswa && enrollment.getCourseId() == idKursus) {
                return true;
            }
        }
        return false;
    }
}