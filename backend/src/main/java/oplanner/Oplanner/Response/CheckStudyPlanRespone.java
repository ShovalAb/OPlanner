package oplanner.Oplanner.Response;


import java.util.List;


public class CheckStudyPlanRespone {
    
    private final int ok;
    private final List<Integer>  coursesMust;
    private final List<DependencyResponse> coursesDepen;
    private final List <CreditsReqResponse> creditsReq;
    

    public CheckStudyPlanRespone(int ok, List<Integer> coursesMust, List<DependencyResponse> coursesDepen, List <CreditsReqResponse> creditsReq){
        this.ok = ok;
        this.coursesMust = coursesMust;
        this.coursesDepen = coursesDepen;
        this.creditsReq = creditsReq;
    }

    public int getOk() {
		return ok;
	}

    public List<Integer>  getCoursesMust() {
		return coursesMust;
	}

    public List<DependencyResponse> getCoursesDepen() {
		return coursesDepen;
	}

    public List <CreditsReqResponse> getCreditsReqResponse(){
        return creditsReq;
    }

}
