package oplanner.Oplanner.Response;
import oplanner.Oplanner.Model.Dependency;
import oplanner.Oplanner.Model.MandatoryRequirement;
import oplanner.Oplanner.Model.Course;


public class CheckStudyPlanRespone {
    
    private final int ok;
    private final int[] coursesMust;
    private final DependencyResponse[] coursesDepen;
    private final CreditsReqResponse[] creditsReq;
    

    public CheckStudyPlanRespone(int ok, int[] coursesMust, DependencyResponse[] coursesDepen, CreditsReqResponse[] creditsReq){
        this.ok = ok;
        this.coursesMust = coursesMust;
        this.coursesDepen = coursesDepen;
        this.creditsReq = creditsReq;
    }

    public int getOk() {
		return ok;
	}

    public int[] getCoursesMust() {
		return coursesMust;
	}

    public DependencyResponse[]  getCoursesDepen() {
		return coursesDepen;
	}

    public CreditsReqResponse[] getCreditsReqResponse(){
        return creditsReq;
    }

}
