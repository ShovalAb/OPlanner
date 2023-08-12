package oplanner.Oplanner.Model;
import java.util.Objects;
import org.springframework.data.annotation.Id;

public class CourseInStudyPlan {
    @Id
    private int id; 
    private int courseNumber;
    private int planId;
    private String department;


    public CourseInStudyPlan(){}

    public CourseInStudyPlan(int courseId, int studyPlanId, String department){
        this.courseNumber = courseId;
        this.planId = studyPlanId;
        this.department = department;
    }

    @Override
	public int hashCode() {

		return Objects.hash(id, courseNumber, planId, department);
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
			", course id'" + courseNumber +
            ", study Plan id'" + planId +
            ", department'" + department +
			'}';
	}

    public void setId(int id) {
		this.id = id;
	}

    public void setCourseId(int courseId){
        this.courseNumber = courseId;
    }

    public void setPlanId(int studyPlanId) {
		this.planId = studyPlanId;
	}

	public void setDepartment(String department) {
		this.department = department;
    }

    public int getId() {
		return id;
	}

    public int getCourseId(){
        return courseNumber;
    }
    
    public int getPlanId() {
		return planId;
	}

	public String getDepartment() {
		return department;
	}

}
