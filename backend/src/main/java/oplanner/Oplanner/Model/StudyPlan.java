package oplanner.Oplanner.Model;

import lombok.Data;
import java.util.Objects;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;

@Data
public class StudyPlan {
    @Id
    private int id; 
    private String planName;

    public StudyPlan(){}

    public StudyPlan(String name){
        planName = name;
    }

    @Override
	public int hashCode() {

		return Objects.hash(id, planName);
	}

    @Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
        StudyPlan studyPlan = (StudyPlan) o; 
		return Objects.equals(id, studyPlan.id) &&
			Objects.equals(planName, studyPlan.planName);
	}

    @Override
	public String toString() {
		return "Study Plan {" +
			"id=" + id +
			", Plan Name='" + planName +
			'}';
	}

    public void setId(int id) {
		this.id = id;
	}

    public void setPlanName(String name){
        planName = name;
    }

    public int getId() {
		return id;
	}

    public String getPlanName(){
        return planName;
    }
    
}
