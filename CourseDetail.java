// File CourseDetail

public class CourseDetail {
    private final int id;
    private String title;
    private String description;
    private String category;
    
    public CourseDetail(int id, String title, String description, String category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
    }

    public boolean matchesKeyword(String keyword) {
        return title.toLowerCase().contains(keyword.toLowerCase()) || 
               description.toLowerCase().contains(keyword.toLowerCase()) ||
               category.toLowerCase().contains(keyword.toLowerCase());
    }

    // Getters and setters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getCategory() { return category; }
    
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setCategory(String category) { this.category = category; }

    @Override
    public String toString() {
        return String.format("ID: %d\nTitle: %s\nCategory: %s\nDescription: %s", 
                            id, title, category, description);
    }
}