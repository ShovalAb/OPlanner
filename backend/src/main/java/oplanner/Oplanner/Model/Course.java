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
	private int courseNumber;
	@JsonProperty("creditsType")
    private String creditsType;
	@JsonProperty("creditsNumber")
    private int creditsNumber;

    public Course(){}

    public Course(String name, int number, String type, int creditsNumber){
		// this.id = id;
        this.courseName = name;
		this.courseNumber = number;
		this.creditsType = type;
		this.creditsNumber = creditsNumber;
    }

    @Override
	public int hashCode() {

		return Objects.hash(id, courseName, courseNumber, creditsType, creditsNumber);
	}

    @Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o; 
		return Objects.equals(id, course.id)
            ;
	}

    @Override
	public String toString() {
		return "Course {" +
			"id=" + id +
			", Course Name='" + courseName +
			", Course Number='" + courseNumber +
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

		public void setCourseNamber(int number) {
		this.courseNumber = number;
	}

	public void setCreditsType(String type) {
		this.creditsType = type;
	}

	public void setCreditsNumber(int CreditNumber) {
		this.creditsNumber = CreditNumber;
	}

	public int getId() {
		return id;
	}

	public String getCourseName() {
		return courseName;
	}

	public int getCourseNumber() {
		return courseNumber;
	}

	public String getCreditsType() {
		return creditsType;
	}

	public int getCreditsNumber() {
		return creditsNumber;
	}
}
