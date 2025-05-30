//file Review.java

import java.util.HashMap;
import java.util.Map;

public class Review implements IReview {
    private final Map<Integer, ReviewDetail> daftarReview;
    private final IEnrollment enrollment;
    private final ICourseMgt kursus;
    private int reviewCounter;

    public Review(IEnrollment enrollment, ICourseMgt kursus) {
        this.enrollment = enrollment;
        this.kursus = kursus;
        this.daftarReview = new HashMap<>();
        this.reviewCounter = 1;
    }

    @Override
    public void giveReview(int userId, int courseId, int rating, String comment) {
        if (!kursus.exists(courseId)) {
            notifyUser("Kursus tidak ditemukan");
            return;
        }
        if (!enrollment.isEnrolled(userId, courseId)) {
            notifyUser("Anda belum terdaftar di kursus ini");
            return;
        }
        if (rating < 1 || rating > 5) {
            notifyUser("Rating harus antara 1 sampai 5");
            return;
        }
        ReviewDetail review = new ReviewDetail(reviewCounter++, userId, courseId, rating, comment);
        daftarReview.put(review.getId(), review);
        notifyUser("Ulasan berhasil dikirim");
    }

    @Override
    public void notifyUser(String message) {
        System.out.println("[SISTEM] " + message);
    }
}

class ReviewDetail {
    private final int id;
    private final int userId;
    private final int courseId;
    private final int rating;
    private final String comment;
    private final String date;

    public ReviewDetail(int id, int userId, int courseId, int rating, String comment) {
        this.id = id;
        this.userId = userId;
        this.courseId = courseId;
        this.rating = rating;
        this.comment = comment;
        this.date = java.time.LocalDate.now().toString();
    }

    public int getId() { return id; }
    public int getUserId() { return userId; }
    public int getCourseId() { return courseId; }
    public int getRating() { return rating; }
    public String getComment() { return comment; }
    public String getDate() { return date; }

    @Override
    public String toString() {
        return String.format("Rating: %d/5\nComment: %s\nDate: %s", rating, comment, date);
    }
}