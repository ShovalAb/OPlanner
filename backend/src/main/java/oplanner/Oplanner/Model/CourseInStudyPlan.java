package oplanner.Oplanner.Model;
import java.util.Objects;
import org.springframework.data.annotation.Id;

public class CourseInStudyPlan {
    @Id
    private int id; 
    private int courseId;
    private int planId;


    public CourseInStudyPlan(){}

    public CourseInStudyPlan(int courseId, int studyPlanId){
        this.courseId = courseId;
        this.planId = studyPlanId;
    }

    @Override
	public int hashCode() {

		return Objects.hash(id, courseId, planId);
	}

    @Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
        CourseInStudyPlan coursesInPlan = (CourseInStudyPlan) o; 
		return Objects.equals(id, coursesInPlan.id);
	}

    @Override
	public String toString() {
		return "Credits Requirement {" +
			"id=" + id +
			", course id'" + courseId +
            ", study Plan id'" + planId +
			'}';
	}

    public void setId(int id) {
		this.id = id;
	}

    public void setCourseId(int courseId){
        this.courseId = courseId;
    }

    public void setPlanId(int studyPlanId) {
		this.planId = studyPlanId;
	}

    public int getId() {
		return id;
	}

    public int getCourseId(){
        return courseId;
    }
    
    public int getPlanId() {
		return planId;
	}

}
