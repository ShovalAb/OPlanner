package oplanner.Oplanner.Model;

import java.util.Objects;
import org.springframework.data.annotation.Id;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Course {
	@Id
    private int id; 
    private String name;
	private int number;
    private String creditsType;
    private int creditsNumber;

    public Course(){}

    public Course(String name, int number, String creditsType, int creditsNumber){
		// this.id = id;
        this.name = name;
		this.number = number;
		this.creditsType = creditsType;
		this.creditsNumber = creditsNumber;
    }

    @Override
	public int hashCode() {

		return Objects.hash(id, name, number, department, creditsType, creditsNumber);
	}

    @Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o; 
		return Objects.equals(number, course.number)
            ;
	}

    @Override
	public String toString() {
		return "Course {" +
			"id=" + id +
			", Course Name='" + name +
			", Course Number='" + number +
			", Course Department='" + department +
			", Credits Type='" + creditsType +
			", Credits Number='" + creditsNumber +
			'}';
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCourseName(String name) {
		this.name = name;
	}

	public void setCourseNumber(int number) {
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

	public String getCourseName() {
		return name;
	}

	public int getCourseNumber() {
		return number;
	}


	public String getCreditsType() {
		return creditsType;
	}

	public int getCreditsNumber() {
		return creditsNumber;
	}
}
