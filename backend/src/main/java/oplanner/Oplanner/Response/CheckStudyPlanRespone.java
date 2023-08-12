package oplanner.Oplanner.Response;
import oplanner.Oplanner.Model.Dependency;
import oplanner.Oplanner.Model.MandatoryRequirement;

import java.util.List;

import oplanner.Oplanner.Model.Course;


public class CheckStudyPlanRespone {
    
    private final int ok;
    private final List<Integer>  coursesMust;
    private final List<DependencyResponse> coursesDepen;
    private final CreditsReqResponse[] creditsReq;
    

    public CheckStudyPlanRespone(int ok, List<Integer> coursesMust, List<DependencyResponse> coursesDepen, CreditsReqResponse[] creditsReq){
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

    public CreditsReqResponse[] getCreditsReqResponse(){
        return creditsReq;
    }

}
