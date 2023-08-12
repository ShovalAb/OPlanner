package oplanner.Oplanner.Response;

public class DependencyResponse {

    private int[] dep;

    public DependencyResponse(int [] dep){
        this.dep = dep;
    }

    public int[] getDep() {
		return dep;
	}

}