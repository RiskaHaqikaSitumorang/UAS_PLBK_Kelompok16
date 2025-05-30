// File: Course.java
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Course implements ICourseMgt {
    private final Map<Integer, CourseDetail> daftarKursus;
    private int idCounter;
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public Course() {
        daftarKursus = new HashMap<>();
        idCounter = 1;
    }

    @Override
    public void addCourse(String judul, String deskripsi, String kategori, 
                         String instruktur, String hari, LocalTime waktuMulai, LocalTime waktuSelesai) {
        CourseDetail kursus = new CourseDetail(idCounter, judul, deskripsi, kategori, 
                                             instruktur, hari, waktuMulai, waktuSelesai);
        daftarKursus.put(idCounter++, kursus);
        System.out.println("[Kursus berhasil ditambahkan]");
    }

    @Override
    public void editCourse(int id, String judulBaru, String deskripsiBaru, String kategoriBaru,
                          String instrukturBaru, String hariBaru, LocalTime waktuMulaiBaru, LocalTime waktuSelesaiBaru) {
        if (daftarKursus.containsKey(id)) {
            CourseDetail kursus = daftarKursus.get(id);
            kursus.setTitle(judulBaru);
            kursus.setDescription(deskripsiBaru);
            kursus.setCategory(kategoriBaru);
            kursus.setInstructor(instrukturBaru);
            kursus.setDay(hariBaru);
            kursus.setWaktuMulai(waktuMulaiBaru);
            kursus.setWaktuSelesai(waktuSelesaiBaru);
            System.out.println("[Kursus berhasil diperbarui]");
        } else {
            System.out.println("[Kursus tidak ditemukan]");
        }
    }

    @Override
    public void deleteCourse(int id) {
        if (daftarKursus.remove(id) != null) {
            System.out.println("[Kursus berhasil dihapus]");
        } else {
            System.out.println("[Kursus tidak ditemukan]");
        }
    }

    @Override
    public void viewAllCourses() {
        if (daftarKursus.isEmpty()) {
            System.out.println("[Belum ada kursus tersedia]");
            return;
        }
        System.out.println("============== DAFTAR KURSUS ==============");
        System.out.println("ID\tJudul\t\tInstruktur\tHari\tWaktu Kelas");
        System.out.println("--------------------------------------------------");
        for (CourseDetail kursus : daftarKursus.values()) {
            System.out.printf("%d\t%-15s\t%-10s\t%-7s\t%s - %s\n",
                            kursus.getId(),
                            truncate(kursus.getTitle(), 15),
                            truncate(kursus.getInstructor(), 10),
                            truncate(kursus.getDay(), 7),
                            kursus.getWaktuMulai().format(timeFormatter),
                            kursus.getWaktuSelesai().format(timeFormatter));
        }
    }

    @Override
    public void searchCourse(String kataKunci) {
        boolean ditemukan = false;
        System.out.println("============== HASIL PENCARIAN ==============");
        for (CourseDetail kursus : daftarKursus.values()) {
            if (kursus.matchesKeyword(kataKunci.toLowerCase())) {
                if (!ditemukan) {
                    System.out.println("ID\tJudul\t\tInstruktur\tHari\tWaktu Kelas");
                    System.out.println("--------------------------------------------------");
                    ditemukan = true;
                }
                System.out.printf("%d\t%-15s\t%-10s\t%-7s\t%s - %s\n",
                                kursus.getId(),
                                truncate(kursus.getTitle(), 15),
                                truncate(kursus.getInstructor(), 10),
                                truncate(kursus.getDay(), 7),
                                kursus.getWaktuMulai().format(timeFormatter),
                                kursus.getWaktuSelesai().format(timeFormatter));
            }
        }
        if (!ditemukan) {
            System.out.println("[Tidak ditemukan kursus yang sesuai]");
        }
    }

    @Override
    public CourseDetail getCourse(int id) {
        return daftarKursus.get(id);
    }

    @Override
    public boolean exists(int id) {
        return daftarKursus.containsKey(id);
    }

    @Override
    public void notifyUser(String message) {
        System.out.println("[SISTEM] " + message);
    }

    private String truncate(String text, int maxLength) {
        if (text.length() <= maxLength) {
            return text;
        }
        return text.substring(0, maxLength - 3) + "...";
    }
}
