// File CourseDetail
import java.time.LocalTime;

public class CourseDetail {
    private final int id;
    private String title;
    private String description;
    private String category;
    private String instructor;
    private String day;
    private LocalTime waktuMulai;
    private LocalTime waktuSelesai;
    
    public CourseDetail(int id, String title, String description, String category, 
                      String instructor, String day, LocalTime waktuMulai, LocalTime waktuSelesai) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.instructor = instructor;
        this.day = day;
        this.waktuMulai = waktuMulai;
        this.waktuSelesai = waktuSelesai;
    }

    public boolean matchesKeyword(String keyword) {
        return title.toLowerCase().contains(keyword) || 
               description.toLowerCase().contains(keyword) ||
               category.toLowerCase().contains(keyword) ||
               instructor.toLowerCase().contains(keyword) ||
               day.toLowerCase().contains(keyword);
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getCategory() { return category; }
    public String getInstructor() { return instructor; }
    public String getDay() { return day; }
    public LocalTime getWaktuMulai() { return waktuMulai; }
    public LocalTime getWaktuSelesai() { return waktuSelesai; }
    
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setCategory(String category) { this.category = category; }
    public void setInstructor(String instructor) { this.instructor = instructor; }
    public void setDay(String day) { this.day = day; }
    public void setWaktuMulai(LocalTime waktuMulai) { this.waktuMulai = waktuMulai; }
    public void setWaktuSelesai(LocalTime waktuSelesai) { this.waktuSelesai = waktuSelesai; }

    @Override
    public String toString() {
        return String.format(
            "ID: %d\nJudul: %s\nKategori: %s\nInstruktur: %s\n" +
            "Hari: %s\nWaktu Kelas: %s - %s\nDeskripsi: %s", 
            id, title, category, instructor, 
            day, waktuMulai.toString(), waktuSelesai.toString(), description
        );
    }
}
