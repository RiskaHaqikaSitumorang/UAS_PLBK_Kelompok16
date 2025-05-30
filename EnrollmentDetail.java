// File EnrolmentDetail.jva
public class EnrollmentDetail {
    private final int id;
    private final int userId;
    private final int courseId;
    private int progress;

    public EnrollmentDetail(int id, int userId, int courseId) {
        this.id = id;
        this.userId = userId;
        this.courseId = courseId;
        this.progress = 0;
    }

    public int getId() { return id; }
    public int getUserId() { return userId; }
    public int getCourseId() { return courseId; }
    public int getProgress() { return progress; }
    
    public void setProgress(int progress) { this.progress = progress; }
}
