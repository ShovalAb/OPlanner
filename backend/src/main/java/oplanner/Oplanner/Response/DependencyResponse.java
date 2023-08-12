package oplanner.Oplanner.Response;

import java.util.List;

public class DependencyResponse {

    private int course;
    private List<Integer>  dep;

    public DependencyResponse(int course, List<Integer> dep){
        this.course = course;
        this.dep = dep;
    }

    public List<Integer> getDep() {
		return dep;
	}

    public int getCourse() {
		return course;
	}


}