package oplanner.Oplanner.Model;

import java.util.Objects;

import org.springframework.data.annotation.Id;

public class CreditsRequirement {
     @Id
    private int id;
    private int planId;
    private String creditsType;
    private int creditsNumber;

    public CreditsRequirement(){}

    public CreditsRequirement(int planID, int courseId,String creditsType, int creditsNumber){
        this.planId = planID;
        this.creditsType = creditsType;
        this.creditsNumber = creditsNumber;
    }

    @Override
	public int hashCode() {

		return Objects.hash(id, planId, creditsType, creditsNumber);
	}

    @Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
        CreditsRequirement creditsReq = (CreditsRequirement) o; 
		return Objects.equals(id, creditsReq.id);
	}

    @Override
	public String toString() {
		return "Credits Requirement {" +
			"id=" + id +
			", plan id'" + planId +
            ", credits type'" + creditsType +
            ", credits number'" + creditsNumber +
			'}';
	}

    public void setId(int id) {
		this.id = id;
	}

    public void setPlanID(int id){
        planId = id;
    }

    public void setCreditsType(String type) {
		this.creditsType = type;
	}

	public void setCreditsNumber(int number) {
		this.creditsNumber = number;
    }

    public int getId() {
		return id;
	}

    public int getPlanID(){
        return planId;
    }
    
    public String getCreditsType() {
		return creditsType;
	}

	public int getCreditsNumber() {
		return creditsNumber;
	}



}
