package oplanner.Oplanner.Model;

import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;

public class Dependency {
    @Id
    private int id; 
    private int dependentCourse;
    private List<Integer> baseCourse;

    // Constructors
    public Dependency(int dependentCourse, List<Integer> baseCourse) {
        this.dependentCourse = dependentCourse;
        this.baseCourse = baseCourse;
    }

    // Generated hashCode and equals methods
    @Override
    public int hashCode() {
        return Objects.hash(id, dependentCourse, baseCourse);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Dependency dependency = (Dependency) o; 
        return Objects.equals(id, dependency.id);
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "Dependency {" +
            "id=" + id +
            ", dependent course'" + dependentCourse +
            ", base course'" + baseCourse +
            '}';
    }

    // Getter and setter methods
    public void setId(int id) {
        this.id = id;
    }

    public void setDependentCourse(int dependentCourse) {
        this.dependentCourse = dependentCourse;
    }

    public void setBaseCourse(List<Integer> baseCourse) {
        this.baseCourse = baseCourse;
    }

    public int getId() {
        return id;
    }

    public int getDependentCourse() {
        return dependentCourse;
    }

    public List<Integer> getBaseCourse() {
        return baseCourse;
    }
}
