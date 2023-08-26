package oplanner.Oplanner.Response;

import java.util.List;

/**
 * Represents the response of a study plan validation.
 */
public class CheckStudyPlanRespone {
    
    private final int ok;
    private final List<Integer> coursesMust;
    private final List<DependencyResponse> coursesDepen;
    private final List<CreditsReqResponse> creditsReq;

    /**
     * Constructor to create a CheckStudyPlanRespone object.
     *
     * @param ok The validation status: 1 if valid, 0 if not valid.
     * @param coursesMust The list of missing mandatory courses.
     * @param coursesDepen The list of missing course dependencies.
     * @param creditsReq The list of credit requirement responses.
     */
    public CheckStudyPlanRespone(int ok, List<Integer> coursesMust, List<DependencyResponse> coursesDepen, List<CreditsReqResponse> creditsReq){
        this.ok = ok;
        this.coursesMust = coursesMust;
        this.coursesDepen = coursesDepen;
        this.creditsReq = creditsReq;
    }

    /**
     * Get the validation status.
     *
     * @return An integer representing the validation status: 1 if valid, 0 if not valid.
     */
    public int getOk() {
        return ok;
    }

    /**
     * Get the list of missing mandatory courses.
     *
     * @return A list of integers representing missing mandatory course numbers.
     */
    public List<Integer> getCoursesMust() {
        return coursesMust;
    }

    /**
     * Get the list of missing course dependencies.
     *
     * @return A list of DependencyResponse objects representing missing course dependencies.
     */
    public List<DependencyResponse> getCoursesDepen() {
        return coursesDepen;
    }

    /**
     * Get the list of credit requirement responses.
     *
     * @return A list of CreditsReqResponse objects representing credit requirement fulfillment status.
     */
    public List<CreditsReqResponse> getCreditsReqResponse(){
        return creditsReq;
    }
}
