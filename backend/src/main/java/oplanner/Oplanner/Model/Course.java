package oplanner.Oplanner.Model;

import java.util.Objects;
import org.springframework.data.annotation.Id;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Course {
	@Id
	@JsonProperty("id")
    private int id; 
	@JsonProperty("courseName")
    private String courseName;
	@JsonProperty("creditsType")
    private String creditsType;
	@JsonProperty("creditsNumber")
    private int creditsNumber;

    public Course(){}

    public Course(String name, String type, int number){
		// this.id = id;
        this.courseName = name;
		this.creditsType = type;
		this.creditsNumber = number;
    }

    @Override
	public int hashCode() {

		return Objects.hash(id, courseName, creditsType, creditsNumber);
	}

    @Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o; 
		return Objects.equals(id, course.id) &&
			Objects.equals(courseName, course.courseName) &&
            Objects.equals(creditsType, course.creditsType) &&
			Objects.equals(creditsNumber, course.creditsNumber)
            ;
	}

    @Override
	public String toString() {
		return "Course {" +
			"id=" + id +
			", Course Name='" + courseName +
			", Credits Type='" + creditsType +
			", Credits Number='" + creditsNumber +
			'}';
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCourseName(String name) {
		this.courseName = name;
	}

	public void setCreditsType(String type) {
		this.creditsType = type;
	}

	public void setCreditsNumber(int number) {
		this.creditsNumber = number;
	}

	public int getId() {
		return id;
	}

	public String getCourseName() {
		return courseName;
	}

	public String getCreditsType() {
		return creditsType;
	}

	public int getCreditsNumber() {
		return creditsNumber;
	}
}
