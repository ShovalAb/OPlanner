package oplanner.Oplanner.Response;

import java.util.List;

import oplanner.Oplanner.Model.Course;

public class CoursesByCreditsType {
      private String creditType;
    private List<Course> courses;

    public CoursesByCreditsType(String creditType, List<Course> list) {
        this.creditType = creditType;
        this.courses = list;
    }

    public void addCourse(Course course){
        courses.add(course);
    }

    public List<Course> getCourses(){
        return courses;
    }
}
