package oplanner.Oplanner.Model;

import java.util.Objects;
import org.springframework.data.annotation.Id;

public class CourseInStudyPlan {
    @Id
    private int id; 
    private int courseNumber;
    private int planId;

    // Constructors
    public CourseInStudyPlan() {}

    public CourseInStudyPlan(int courseId, int planId) {
        this.courseNumber = courseId;
        this.planId = planId;
    }

    // Generated hashCode and equals methods
    @Override
    public int hashCode() {
        return Objects.hash(id, courseNumber, planId);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CourseInStudyPlan coursesInPlan = (CourseInStudyPlan) o; 
        return Objects.equals(id, coursesInPlan.id);
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "Credits Requirement {" +
            "id=" + id +
            ", course id'" + courseNumber +
            ", study Plan id'" + planId +
            '}';
    }

    // Getter and setter methods
    public void setId(int id) {
        this.id = id;
    }

    public void setCourseId(int courseId) {
        this.courseNumber = courseId;
    }

    public void setPlanId(int studyPlanId) {
        this.planId = studyPlanId;
    }

    public int getId() {
        return id;
    }

    public int getCourseId() {
        return courseNumber;
    }
    
    public int getPlanId() {
        return planId;
    }
}
