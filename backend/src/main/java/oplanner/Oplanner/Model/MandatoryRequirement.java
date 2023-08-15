package oplanner.Oplanner.Model;

import lombok.Data;
import java.util.Objects;
import org.springframework.data.annotation.Id;

@Data
public class MandatoryRequirement {
    @Id
    private int id;
    private int planId;
    private int[] courseNumber;

    // Constructors
    public MandatoryRequirement() {}

    public MandatoryRequirement(int planId, int[] courseId) {
        this.planId = planId;
        this.courseNumber = courseId;
    }

    // Generated hashCode and equals methods
    @Override
    public int hashCode() {
        return Objects.hash(id, planId, courseNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MandatoryRequirement mandatoryReq = (MandatoryRequirement) o;
        return Objects.equals(id, mandatoryReq.id);
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "Mandatory Requirements {" +
            "id=" + id +
            ", plan id'" + planId +
            ", course id'" + courseNumber +
            '}';
    }

    // Getter and setter methods
    public void setId(int id) {
        this.id = id;
    }

    public void setPlanID(int id) {
        this.planId = id;
    }

    public void setCourseId(int[] courseId) {
        this.courseNumber = courseId;
    }

    public int getId() {
        return id;
    }

    public int getPlanId() {
        return planId;
    }

    public int[] getCourseId() {
        return courseNumber;
    }
}
