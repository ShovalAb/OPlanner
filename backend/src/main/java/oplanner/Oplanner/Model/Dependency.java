package oplanner.Oplanner.Model;

import java.util.*;

import org.springframework.data.annotation.Id;

public class Dependency {
    @Id
    private int id; 
    private int dependentCourse;
    private List<Integer> baseCourse;

    public Dependency(){}

    public Dependency(int dependentCourse, List<Integer> baseCourse){
        this.dependentCourse = dependentCourse;
        this.baseCourse = baseCourse;
    }

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

    @Override
	public String toString() {
		return "Dependency {" +
			"id=" + id +
			", dependent course'" + dependentCourse +
            ", base course'" + baseCourse +
			'}';
	}

    public void setId(int id) {
		this.id = id;
	}

    public void setDependentCourse(int dependentCourse){
        this.dependentCourse = dependentCourse;
    }

    public void setBaseCourse(List<Integer> baseCourse){
        this.baseCourse = baseCourse;
    }

    public int getId() {
		return id;
	}

    public int getDependentCourse(){
        return dependentCourse;
    }

        public List<Integer> getBaseCourse(){
        return baseCourse;
    }




}
