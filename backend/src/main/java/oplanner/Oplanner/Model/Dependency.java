package oplanner.Oplanner.Model;

import java.util.Objects;

import org.springframework.data.annotation.Id;

public class Dependency {
    @Id
    private int id; 
    private int dependentCourse;
    private int baseCourse;

    public Dependency(){}

    public Dependency(int dependentCourse, int baseCourse){
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

    public void setPlanID(int dependentCourse){
        this.dependentCourse = dependentCourse;
    }

        public void setCourseID(int baseCourse){
        this.baseCourse = baseCourse;
    }

    public int getId() {
		return id;
	}

    public int getPlanID(){
        return dependentCourse;
    }

        public int getCourseID(){
        return baseCourse;
    }




}
