package oplanner.Oplanner.Response;

public class CreditsReqResponse {

    private final String creditsType;
    private final int currentCredits;
    private final int neededCredits;  


    public CreditsReqResponse(String creditsType, int currentCredits, int neededCredits){
        this.creditsType = creditsType;
        this.currentCredits = currentCredits;
        this.neededCredits = neededCredits;
    }

    public String getCreditsType() {
		return creditsType;
	}

    public int getCurrentCredits() {
		return currentCredits;
	}

    public int getNeededCredits() {
		return neededCredits;
	}


}