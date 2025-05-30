# UAS_PLBK_Kelompok16

 # Sistem Manajemen Kursus Online

Sistem ini adalah aplikasi manajemen kursus online yang memungkinkan admin untuk mengelola kursus dan pengguna, serta siswa untuk mendaftar kursus dan memberikan ulasan.

## Fitur Utama

### Admin
- Manajemen Kursus:
  - Tambah, edit, hapus kursus
  - Lihat semua kursus
  - Cari kursus berdasarkan kata kunci
- Manajemen User:
  - Tambah user baru (admin/siswa)
  - Hapus user
  - Lihat semua user

### Siswa
- Melihat daftar kursus yang tersedia
- Mendaftar kursus
- Memberikan ulasan untuk kursus yang diikuti
- Melihat kursus yang sudah didaftar

## Struktur Kode

```
src/
├── Auth.java               # Sistem autentikasi dan manajemen user
├── Course.java             # Manajemen kursus
├── CourseDetail.java       # Detail informasi kursus
├── Enrollment.java         # Sistem pendaftaran kursus
├── EnrollmentDetail.java   # Detail pendaftaran kursus
├── IUserMgt.java           # Interface manajemen user
├── ICourseMgt.java         # Interface manajemen kursus
├── IEnrollment.java        # Interface pendaftaran kursus
├── IReview.java           # Interface sistem ulasan
├── OnlineCourseManagement.java # Program utama
├── Review.java            # Sistem ulasan
└── ReviewDetail.java      # Detail ulasan
└── User.java              # Model user
```

## Screenshot Output

### Tabel Output Admin

| Menu | Screenshot Output |
|------|-------------------|
| Login Admin | ![image](https://github.com/user-attachments/assets/75dd00a6-7be0-423b-bdfa-e6ccd567f33b) |
| Tambah Kursus | ![image](https://github.com/user-attachments/assets/35e6e9b3-1a01-4322-bfad-e99fb2b06efe) |
| Edit Kursus |  ![image](https://github.com/user-attachments/assets/16ac10bf-13a8-4468-bb1a-5c9d1fea3fee) |
| Hapus Kursus | ![image](https://github.com/user-attachments/assets/233c1528-9000-48ca-a736-58a6216c0e87) |
| Lihat Semua Kursus | ![image](https://github.com/user-attachments/assets/c67cd740-d53c-4b9b-a4ac-13abf4987384) |
| Cari Kursus | ![image](https://github.com/user-attachments/assets/caad9203-1cb8-4bf5-9eb3-7e2138f65f0a) |
| Tambah User | ![image](https://github.com/user-attachments/assets/0ff75d85-ecba-40c9-8a7a-085c522def46) |
| Lihat User | ![image](https://github.com/user-attachments/assets/742d14e7-382d-4ed7-805e-740d24a8af94) |
| Hapus User | ![image](https://github.com/user-attachments/assets/02899fb3-0557-44ae-88b0-71ef66a72d1c) |

### Tabel Output Siswa

| Menu | Screenshot Output |
|------|-------------------|
| Login Siswa | ![image](https://github.com/user-attachments/assets/df8efcd4-625f-4e11-82c1-6188e8530117) |
| Lihat Kursus | ![image](https://github.com/user-attachments/assets/e69221bc-7ae7-43e3-995d-e66383096fe1) |
| Daftar Kursus | ![image](https://github.com/user-attachments/assets/572e5e96-3496-487d-84d5-ee1753d18067) |
| Beri Ulasan | ![image](https://github.com/user-attachments/assets/d9023c1b-5c0f-4c2b-b928-0fd4833879f2) |
| Lihat Kursus Saya | ![image](https://github.com/user-attachments/assets/7a9737b3-0784-4bf5-bad4-f169c9448267) |

## Cara Menjalankan

1. Pastikan Java JDK terinstall
2. Compile semua file Java:
   ```
   javac src/*.java -d bin/
   ```
3. Jalankan program utama:
   ```
   java -cp bin/ OnlineCourseManagement
   ```

## Akun Default

- Admin:
  - Username: admin
  - Password: admin123
- Siswa:
  - Username: siswa
  - Password: siswa123


## Kontributor

| Nama | NPM |
|------|-------------------|
| Berliani Utami | 2208107010082 |
| Riska Haqika Situmorang | 2208107010086 |








