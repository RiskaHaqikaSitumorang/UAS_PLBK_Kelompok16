// File: OnlineCourseManagement.java
import java.util.Scanner;

public class OnlineCourseManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        IAuth auth = new Auth();
        ICourse course = new Course();
        IEnrollment enrollment = new Enrollment(course);
        INotification notif = new Notification();

        System.out.println("============== SISTEM MANAJEMEN KURSUS ONLINE ==============");

        while (true) {
            // Menu utama sebelum login
            System.out.println("\nPilih peran Anda:");
            System.out.println("1. Admin");
            System.out.println("2. Siswa");
            System.out.println("3. Keluar");
            System.out.print("Masukkan pilihan: ");
            String pilihanRole = scanner.nextLine();

            if (pilihanRole.equals("3")) {
                System.out.println("Terima kasih telah menggunakan sistem.");
                break;
            }

            if (!pilihanRole.equals("1") && !pilihanRole.equals("2")) {
                notif.notifyUser("Pilihan tidak valid. Silakan coba lagi.");
                continue;
            }

            String role = pilihanRole.equals("1") ? "admin" : "siswa";
            
            // Login sesuai role
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();

            if (!auth.login(username, password)) {
                notif.notifyUser("Login gagal. Username atau password salah.");
                continue;
            }

            // Verifikasi role sesuai pilihan
            if (pilihanRole.equals("1") && !auth.isAdmin()) {
                notif.notifyUser("Anda login sebagai siswa, bukan admin.");
                auth.logout();
                continue;
            } else if (pilihanRole.equals("2") && auth.isAdmin()) {
                notif.notifyUser("Anda login sebagai admin, bukan siswa.");
                auth.logout();
                continue;
            }

            User pengguna = auth.getCurrentUser();
            notif.notifyUser("Login berhasil sebagai " + (auth.isAdmin() ? "Admin" : "Siswa"));

            boolean tetapLogin = true;
            while (tetapLogin) {
                try {
                    if (auth.isAdmin()) {
                        menuAdmin(scanner, auth, course, notif);
                    } else {
                        menuSiswa(scanner, auth, course, enrollment, notif, pengguna.getId());
                    }
                } catch (Exception e) {
                    notif.notifyUser("Terjadi kesalahan: " + e.getMessage());
                    scanner.nextLine();
                }
            }
        }
        scanner.close();
    }

    private static void menuAdmin(Scanner scanner, IAuth auth, ICourse course, INotification notif) {
        System.out.println("\n============== MENU ADMIN ==============");
        System.out.println("1. Tambah Kursus");
        System.out.println("2. Edit Kursus");
        System.out.println("3. Hapus Kursus");
        System.out.println("4. Lihat Semua Kursus");
        System.out.println("5. Cari Kursus");
        System.out.println("6. Logout");
        System.out.print("Pilih menu: ");
        
        String pilihan = scanner.nextLine();
        switch (pilihan) {
            case "1":
                System.out.print("Judul kursus: ");
                String judul = scanner.nextLine();
                System.out.print("Deskripsi: ");
                String deskripsi = scanner.nextLine();
                System.out.print("Kategori: ");
                String kategori = scanner.nextLine();
                course.addCourse(judul, deskripsi, kategori);
                break;
            case "2":
                System.out.print("ID kursus yang akan diedit: ");
                int idEdit = Integer.parseInt(scanner.nextLine());
                System.out.print("Judul baru: ");
                String judulBaru = scanner.nextLine();
                System.out.print("Deskripsi baru: ");
                String deskripsiBaru = scanner.nextLine();
                System.out.print("Kategori baru: ");
                String kategoriBaru = scanner.nextLine();
                course.editCourse(idEdit, judulBaru, deskripsiBaru, kategoriBaru);
                break;
            case "3":
                System.out.print("ID kursus yang akan dihapus: ");
                int idHapus = Integer.parseInt(scanner.nextLine());
                course.deleteCourse(idHapus);
                break;
            case "4":
                course.viewAllCourses();
                break;
            case "5":
                System.out.print("Kata kunci pencarian: ");
                String kataKunci = scanner.nextLine();
                course.searchCourse(kataKunci);
                break;
            case "6":
                auth.logout();
                notif.notifyUser("Anda telah logout");
                return;
            default:
                notif.notifyUser("Pilihan tidak valid");
        }
    }

    private static void menuSiswa(Scanner scanner, IAuth auth, ICourse course, 
                                IEnrollment enrollment, INotification notif, int idSiswa) {
        System.out.println("\n============== MENU SISWA ==============");
        System.out.println("1. Lihat Semua Kursus");
        System.out.println("2. Daftar Kursus");
        System.out.println("3. Beri Rating dan Ulasan");
        System.out.println("4. Perbarui Progress Belajar");
        System.out.println("5. Lihat Kursus Saya");
        System.out.println("6. Logout");
        System.out.print("Pilih menu: ");
        
        String pilihan = scanner.nextLine();
        switch (pilihan) {
            case "1":
                course.viewAllCourses();
                break;
            case "2":
                System.out.print("ID kursus yang akan diikuti: ");
                int idKursus = Integer.parseInt(scanner.nextLine());
                enrollment.enrollCourse(idSiswa, idKursus);
                break;
            case "3":
                System.out.print("ID kursus yang akan diulas: ");
                int idUlasan = Integer.parseInt(scanner.nextLine());
                System.out.print("Rating (1-5): ");
                int rating = Integer.parseInt(scanner.nextLine());
                System.out.print("Ulasan: ");
                String ulasan = scanner.nextLine();
                enrollment.giveReview(idSiswa, idUlasan, rating, ulasan);
                break;
            case "4":
                System.out.print("ID kursus yang akan diperbarui: ");
                int idProgress = Integer.parseInt(scanner.nextLine());
                System.out.print("Progress (0-100): ");
                int progress = Integer.parseInt(scanner.nextLine());
                enrollment.updateProgress(idSiswa, idProgress, progress);
                break;
            case "5":
                enrollment.viewUserCourses(idSiswa);
                break;
            case "6":
                auth.logout();
                notif.notifyUser("Anda telah logout");
                return;
            default:
                notif.notifyUser("Pilihan tidak valid");
        }
    }

}