package oplanner.Oplanner.Response;

import java.util.List;
import oplanner.Oplanner.Model.Course;

/**
 * Represents a collection of courses grouped by their credits type.
 */
public class CoursesByCreditsType {
    private String creditsType;
    private List<Course> courses;

    /**
     * Constructor to create a CoursesByCreditsType object.
     *
     * @param creditType The credits type of the courses in this group.
     * @param list The list of courses associated with the credits type.
     */
    public CoursesByCreditsType(String creditType, List<Course> list) {
        this.creditsType = creditType;
        this.courses = list;
    }

    /**
     * Adds a course to the list of courses in this group.
     *
     * @param course The course to be added.
     */
    public void addCourse(Course course) {
        courses.add(course);
    }

    /**
     * Get the credits type associated with this group.
     *
     * @return The credits type as a string.
     */
    public String getCreditsType() {
        return creditsType;
    }

    /**
     * Get the list of courses in this group.
     *
     * @return A list of Course objects representing the courses.
     */
    public List<Course> getCourses() {
        return courses;
    }
}
