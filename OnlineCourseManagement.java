// File OnlineCourseManagement.java

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class OnlineCourseManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Auth auth = new Auth();
        Course course = new Course();
        Enrollment enrollment = new Enrollment(course);
        Review review = new Review(enrollment, course);

        System.out.println("============== SISTEM MANAJEMEN KURSUS ONLINE ==============");

        while (true) {
            System.out.println("Pilih peran:");
            System.out.println("1. Admin");
            System.out.println("2. Siswa");
            System.out.println("3. Keluar");
            System.out.print("Masukkan pilihan (1/2/3): ");
            String pilihanRole = scanner.nextLine();

            if (pilihanRole.equals("3")) {
                System.out.println("Terima kasih telah menggunakan sistem.");
                break;
            }

            if (!pilihanRole.equals("1") && !pilihanRole.equals("2")) {
                auth.notifyUser("Pilihan tidak valid. Silakan pilih 1, 2, atau 3.");
                continue;
            }

            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();

            if (!auth.login(username, password)) {
                auth.notifyUser("Login gagal. Username atau password salah.");
                continue;
            }

            boolean isAdmin = auth.isAdmin();
            if ((pilihanRole.equals("1") && !isAdmin) || (pilihanRole.equals("2") && isAdmin)) {
                auth.notifyUser("Role tidak sesuai dengan akun yang dipilih.");
                auth.logout();
                continue;
            }

            User pengguna = auth.getCurrentUser();
            auth.notifyUser("Login berhasil sebagai " + (isAdmin ? "Admin" : "Siswa") + ": " + pengguna.getUsername());

            if (isAdmin) {
                menuAdmin(scanner, auth, course, enrollment);
            } else {
                menuSiswa(scanner, auth, course, enrollment, review, pengguna.getId());
            }
        }
        scanner.close();
    }

    private static void menuAdmin(Scanner scanner, Auth auth, Course course, Enrollment enrollment) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        while (true) {
            System.out.println("\n============== MENU ADMIN ==============");
            System.out.println("-- Kelola Kursus --");
            System.out.println("1. Tambah Kursus");
            System.out.println("2. Edit Kursus");
            System.out.println("3. Hapus Kursus");
            System.out.println("4. Lihat Semua Kursus");
            System.out.println("5. Cari Kursus");
            System.out.println("-- Kelola User --");
            System.out.println("6. Tambah User");
            System.out.println("7. Hapus User");
            System.out.println("8. Lihat User");
            System.out.println("9. Logout");
            System.out.print("Pilih menu: ");

            String pilihan = scanner.nextLine();
            switch (pilihan) {
                case "1":
                    tambahKursus(scanner, course, timeFormatter);
                    break;
                case "2":
                    editKursus(scanner, course, timeFormatter);
                    break;
                case "3":
                    hapusKursus(scanner, course);
                    break;
                case "4":
                    course.viewAllCourses();
                    break;
                case "5":
                    System.out.print("\nMasukkan kata kunci pencarian: ");
                    String keyword = scanner.nextLine();
                    course.searchCourse(keyword);
                    break;
                case "6":
                    tambahUser(scanner, auth);
                    break;
                case "7":
                    hapusUser(scanner, auth);
                    break;
                case "8":
                    auth.viewAllUsers();
                    break;
                case "9":
                    auth.logout();
                    auth.notifyUser("Anda telah logout.");
                    return;
                default:
                    auth.notifyUser("Pilihan tidak valid.");
            }
        }
    }

    private static void tambahKursus(Scanner scanner, Course course, DateTimeFormatter timeFormatter) {
        System.out.println("\n============== TAMBAH KURSUS BARU ==============");
        System.out.print("Judul kursus: ");
        String judul = scanner.nextLine();
        System.out.print("Deskripsi: ");
        String deskripsi = scanner.nextLine();
        System.out.print("Kategori: ");
        String kategori = scanner.nextLine();
        System.out.print("Nama Instruktur: ");
        String instruktur = scanner.nextLine();
        System.out.print("Hari pelaksanaan: ");
        String hari = scanner.nextLine();
        try {
            System.out.print("Waktu kelas (HH:mm - HH:mm): ");
            String inputWaktu = scanner.nextLine();
            String[] waktuSplit = inputWaktu.split("\\s*-\\s*");
            if (waktuSplit.length != 2) {
                throw new DateTimeParseException("Format tidak valid", inputWaktu, 0);
            }
            LocalTime waktuMulai = LocalTime.parse(waktuSplit[0], timeFormatter);
            LocalTime waktuSelesai = LocalTime.parse(waktuSplit[1], timeFormatter);
            if (waktuSelesai.isBefore(waktuMulai)) {
                course.notifyUser("Waktu selesai harus setelah waktu mulai.");
                return;
            }
            course.addCourse(judul, deskripsi, kategori, instruktur, hari, waktuMulai, waktuSelesai);
            course.notifyUser("Kursus berhasil ditambahkan.");
        } catch (DateTimeParseException e) {
            course.notifyUser("Format waktu tidak valid. Gunakan format HH:mm - HH:mm.");
        } catch (Exception e) {
            course.notifyUser("Terjadi kesalahan saat menambahkan kursus.");
        }
    }

    private static void editKursus(Scanner scanner, Course course, DateTimeFormatter timeFormatter) {
        course.viewAllCourses();
        System.out.print("Masukkan ID kursus yang akan diedit: ");
        String inputId = scanner.nextLine();
        int idEdit;
        try {
            idEdit = Integer.parseInt(inputId);
        } catch (NumberFormatException e) {
            course.notifyUser("ID harus berupa angka.");
            return;
        }
        CourseDetail kursus = course.getCourse(idEdit);
        if (kursus == null) {
            course.notifyUser("Kursus tidak ditemukan.");
            return;
        }
        System.out.println("\nKosongkan input jika tidak ingin mengubah field.");
        System.out.print("Judul kursus [" + kursus.getTitle() + "]: ");
        String judul = scanner.nextLine();
        judul = judul.isEmpty() ? kursus.getTitle() : judul;
        System.out.print("Deskripsi [" + kursus.getDescription() + "]: ");
        String deskripsi = scanner.nextLine();
        deskripsi = deskripsi.isEmpty() ? kursus.getDescription() : deskripsi;
        System.out.print("Kategori [" + kursus.getCategory() + "]: ");
        String kategori = scanner.nextLine();
        kategori = kategori.isEmpty() ? kursus.getCategory() : kategori;
        System.out.print("Nama Instruktur [" + kursus.getInstructor() + "]: ");
        String instruktur = scanner.nextLine();
        instruktur = instruktur.isEmpty() ? kursus.getInstructor() : instruktur;
        System.out.print("Hari pelaksanaan [" + kursus.getDay() + "]: ");
        String hari = scanner.nextLine();
        hari = hari.isEmpty() ? kursus.getDay() : hari;
        LocalTime waktuMulai = kursus.getWaktuMulai();
        LocalTime waktuSelesai = kursus.getWaktuSelesai();
        try {
            System.out.print("Waktu kelas [" + kursus.getWaktuMulai().format(timeFormatter) + " - " + 
                           kursus.getWaktuSelesai().format(timeFormatter) + "]: ");
            String inputWaktu = scanner.nextLine();
            if (!inputWaktu.isEmpty()) {
                String[] waktuSplit = inputWaktu.split("\\s*-\\s*");
                if (waktuSplit.length != 2) {
                    throw new DateTimeParseException("Format tidak valid", inputWaktu, 0);
                }
                waktuMulai = LocalTime.parse(waktuSplit[0], timeFormatter);
                waktuSelesai = LocalTime.parse(waktuSplit[1], timeFormatter);
                if (waktuSelesai.isBefore(waktuMulai)) {
                    course.notifyUser("Waktu selesai harus setelah waktu mulai.");
                    return;
                }
            }
            course.editCourse(idEdit, judul, deskripsi, kategori, instruktur, hari, waktuMulai, waktuSelesai);
            course.notifyUser("Kursus berhasil diperbarui.");
        } catch (DateTimeParseException e) {
            course.notifyUser("Format waktu tidak valid. Gunakan format HH:mm - HH:mm.");
        } catch (Exception e) {
            course.notifyUser("Terjadi kesalahan saat mengedit kursus.");
        }
    }

    private static void hapusKursus(Scanner scanner, Course course) {
        course.viewAllCourses();
        System.out.print("Masukkan ID kursus yang akan dihapus: ");
        String inputId = scanner.nextLine();
        int idHapus;
        try {
            idHapus = Integer.parseInt(inputId);
        } catch (NumberFormatException e) {
            course.notifyUser("ID harus berupa angka.");
            return;
        }
        CourseDetail kursus = course.getCourse(idHapus);
        if (kursus == null) {
            course.notifyUser("Kursus tidak ditemukan.");
            return;
        }
        System.out.println("\nAnda akan menghapus kursus berikut:");
        System.out.println(kursus);
        System.out.print("Apakah Anda yakin? (ya/tidak): ");
        String konfirmasi = scanner.nextLine();
        if (konfirmasi.equalsIgnoreCase("ya")) {
            course.deleteCourse(idHapus);
            course.notifyUser("Kursus berhasil dihapus.");
        } else {
            course.notifyUser("Penghapusan dibatalkan.");
        }
    }

    private static void tambahUser(Scanner scanner, Auth auth) {
        System.out.println("\n============== TAMBAH PENGGUNA BARU ==============");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Role (admin/siswa): ");
        String role = scanner.nextLine();
        if (!role.equalsIgnoreCase("admin") && !role.equalsIgnoreCase("siswa")) {
            auth.notifyUser("Role harus 'admin' atau 'siswa'.");
            return;
        }
        if (auth.register(username, password, role)) {
            auth.notifyUser("Pengguna berhasil ditambahkan.");
        } else {
            auth.notifyUser("Username sudah ada.");
        }
    }

    private static void hapusUser(Scanner scanner, Auth auth) {
        auth.viewAllUsers();
        System.out.print("Masukkan ID pengguna yang akan dihapus: ");
        String inputId = scanner.nextLine();
        int userId;
        try {
            userId = Integer.parseInt(inputId);
        } catch (NumberFormatException e) {
            auth.notifyUser("ID harus berupa angka.");
            return;
        }
        User user = auth.getUser(userId);
        if (user == null) {
            auth.notifyUser("Pengguna tidak ditemukan.");
            return;
        }
        System.out.println("\nAnda akan menghapus pengguna berikut:");
        System.out.println("ID: " + user.getId() + ", Username: " + user.getUsername() + ", Role: " + user.getRole());
        System.out.print("Apakah Anda yakin? (ya/tidak): ");
        String konfirmasi = scanner.nextLine();
        if (konfirmasi.equalsIgnoreCase("ya")) {
            auth.deleteUser(userId);
            auth.notifyUser("Pengguna berhasil dihapus.");
        } else {
            auth.notifyUser("Penghapusan dibatalkan.");
        }
    }

    private static void menuSiswa(Scanner scanner, Auth auth, Course course, Enrollment enrollment, Review review, int idSiswa) {
        while (true) {
            System.out.println("\n============== MENU SISWA ==============");
            System.out.println("1. Lihat Semua Kursus");
            System.out.println("2. Daftar Kursus");
            System.out.println("3. Beri Ulasan");
            System.out.println("4. Lihat Kursus Saya");
            System.out.println("5. Logout");
            System.out.print("Pilih menu: ");
            String pilihan = scanner.nextLine();
            switch (pilihan) {
                case "1":
                    enrollment.viewAllCourses();
                    break;
                case "2":
                    daftarKursus(scanner, course, enrollment, idSiswa);
                    break;
                case "3":
                    beriUlasan(scanner, course, enrollment, review, idSiswa);
                    break;
                case "4":
                    enrollment.viewUserCourses(idSiswa);
                    break;
                case "5":
                    auth.logout();
                    auth.notifyUser("Anda telah logout.");
                    return;
                default:
                    auth.notifyUser("Pilihan tidak valid.");
            }
        }
    }

    private static void daftarKursus(Scanner scanner, Course course, Enrollment enrollment, int idSiswa) {
        System.out.println("\n============== DAFTAR KURSUS TERSEDIA ==============");
        enrollment.viewAllCourses();
        System.out.print("Masukkan ID kursus yang ingin diikuti: ");
        String inputId = scanner.nextLine();
        int idKursus;
        try {
            idKursus = Integer.parseInt(inputId);
        } catch (NumberFormatException e) {
            enrollment.notifyUser("ID harus berupa angka.");
            return;
        }
        if (!course.exists(idKursus)) {
            enrollment.notifyUser("Kursus tidak ditemukan.");
            return;
        }
        enrollment.enrollCourse(idSiswa, idKursus);
    }

    private static void beriUlasan(Scanner scanner, Course course, Enrollment enrollment, Review review, int idSiswa) {
        System.out.println("\n============== KURSUS ANDA ==============");
        enrollment.viewUserCourses(idSiswa);
        System.out.print("Masukkan ID kursus yang akan diberi ulasan: ");
        String inputId = scanner.nextLine();
        int idKursus;
        try {
            idKursus = Integer.parseInt(inputId);
        } catch (NumberFormatException e) {
            review.notifyUser("ID harus berupa angka.");
            return;
        }
        if (!course.exists(idKursus)) {
            review.notifyUser("Kursus tidak ditemukan.");
            return;
        }
        System.out.print("Berikan rating (1-5): ");
        String inputRating = scanner.nextLine();
        int rating;
        try {
            rating = Integer.parseInt(inputRating);
        } catch (NumberFormatException e) {
            review.notifyUser("Rating harus berupa angka.");
            return;
        }
        System.out.print("Tulis ulasan Anda: ");
        String ulasan = scanner.nextLine();
        review.giveReview(idSiswa, idKursus, rating, ulasan);
    }
}
