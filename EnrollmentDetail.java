// File EnrolmentDetail.jva
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDetail {
    private final int id;
    private final int userId;
    private final int courseId;
    private int progress;
    private final List<Review> reviews;

    public EnrollmentDetail(int id, int userId, int courseId) {
        this.id = id;
        this.userId = userId;
        this.courseId = courseId;
        this.progress = 0;
        this.reviews = new ArrayList<>();
    }

    public void addReview(int rating, String comment) {
        reviews.add(new Review(rating, comment));
    }

    // Getters and setters
    public int getId() { return id; }
    public int getUserId() { return userId; }
    public int getCourseId() { return courseId; }
    public int getProgress() { return progress; }
    public List<Review> getReviews() { return reviews; }
    
    public void setProgress(int progress) { this.progress = progress; }
}

class Review {
    private final int rating;
    private final String comment;
    private final String date;

    public Review(int rating, String comment) {
        this.rating = rating;
        this.comment = comment;
        this.date = java.time.LocalDate.now().toString();
    }

    @Override
    public String toString() {
        return String.format("Rating: %d/5\nComment: %s\nDate: %s", rating, comment, date);
    }
}