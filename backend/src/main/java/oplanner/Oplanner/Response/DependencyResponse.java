package oplanner.Oplanner.Response;

import java.util.List;

/**
 * Represents the response for missing course dependencies.
 */
public class DependencyResponse {

    private int course;
    private List<Integer> dep;

    /**
     * Constructor to create a DependencyResponse object.
     *
     * @param course The course number for which dependencies are missing.
     * @param dep The list of course numbers representing missing dependencies.
     */
    public DependencyResponse(int course, List<Integer> dep) {
        this.course = course;
        this.dep = dep;
    }

    /**
     * Get the list of course numbers representing missing dependencies.
     *
     * @return A list of integers representing missing dependency course numbers.
     */
    public List<Integer> getDep() {
        return dep;
    }

    /**
     * Get the course number for which dependencies are missing.
     *
     * @return The course number as an integer.
     */
    public int getCourse() {
        return course;
    }
}
