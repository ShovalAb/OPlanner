package oplanner.Oplanner.Model;

import lombok.Data;
import java.util.Objects;
import org.springframework.data.annotation.Id;

@Data
public class MandatoryRequirement{
    @Id
    private int id;
    private int planId;
    private int courseId;

    public MandatoryRequirement(){}

    public MandatoryRequirement(int planID, int courseID){
        this.planId = planID;
        this.courseId = courseID;
    }

    @Override
	public int hashCode() {

		return Objects.hash(id, planId, courseId);
	}

    @Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
        MandatoryRequirement mandatoryReq = (MandatoryRequirement) o; 
		return Objects.equals(id, mandatoryReq.id);
	}

    @Override
	public String toString() {
		return "Mandatory Requirements {" +
			"id=" + id +
			", plan id'" + planId +
            ", course id'" + courseId +
			'}';
	}

    public void setId(int id) {
		this.id = id;
	}

    public void setPlanID(int id){
        this.planId = id;
    }

        public void setCourseID(int id){
        this.courseId = id;
    }

    public int getId() {
		return id;
	}

    public int getPlanID(){
        return planId;
    }

        public int getCourseID(){
        return courseId;
    }


}
