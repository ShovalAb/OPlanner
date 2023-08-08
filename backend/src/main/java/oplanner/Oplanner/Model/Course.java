package oplanner.Oplanner.Model;

import java.util.Objects;
import org.springframework.data.annotation.Id;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Course {
	@Id
	@JsonProperty("id")
    private int id; 
	@JsonProperty("courseName")
    private String name;
	private int number;
	@JsonProperty("creditsType")
    private String creditsType;
	@JsonProperty("creditsNumber")
    private int creditsNumber;

    public Course(){}

    public Course(String name, int number, String type, int creditsNumber){
		// this.id = id;
        this.name = name;
		this.number = number;
		this.creditsType = type;
		this.creditsNumber = creditsNumber;
    }

    @Override
	public int hashCode() {

		return Objects.hash(id, name, number, creditsType, creditsNumber);
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
			", Course Name='" + name +
			", Course Number='" + number +
			", Credits Type='" + creditsType +
			", Credits Number='" + creditsNumber +
			'}';
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

		public void setCourseNamber(int number) {
		this.number = number;
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

	public String getName() {
		return name;
	}

	public int getNumber() {
		return number;
	}

	public String getCreditsType() {
		return creditsType;
	}

	public int getCreditsNumber() {
		return creditsNumber;
	}
}
