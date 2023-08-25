package oplanner.Oplanner.Model;

import lombok.Data;
import java.util.Objects;
import org.springframework.data.annotation.Id;

@Data
public class StudyPlan {
    @Id
    private int id; 
    private String planName;

    // Constructors
    public StudyPlan() {}

    public StudyPlan(String name) {
        planName = name;
    }

    // Generated hashCode and equals methods
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

    // toString method for debugging
    @Override
    public String toString() {
        return "Study Plan {" +
            "id=" + id +
            ", Plan Name='" + planName +
            '}';
    }

    // Getter and setter methods
    public void setId(int id) {
        this.id = id;
    }

    public void setPlanName(String name) {
        planName = name;
    }

    public int getId() {
        return id;
    }

    public String getPlanName() {
        return planName;
    }
}
