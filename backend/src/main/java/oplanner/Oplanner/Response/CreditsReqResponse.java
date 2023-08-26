package oplanner.Oplanner.Response;

/**
 * Represents the response for credit requirement fulfillment status.
 */
public class CreditsReqResponse {

    private final String creditsType;
    private final int currentCredits;
    private final int neededCredits;  

    /**
     * Constructor to create a CreditsReqResponse object.
     *
     * @param creditsType The type of credits being checked.
     * @param currentCredits The total current credits fulfilling the requirement.
     * @param neededCredits The total credits needed to fulfill the requirement.
     */
    public CreditsReqResponse(String creditsType, int currentCredits, int neededCredits){
        this.creditsType = creditsType;
        this.currentCredits = currentCredits;
        this.neededCredits = neededCredits;
    }

    /**
     * Get the type of credits being checked.
     *
     * @return The credits type as a string.
     */
    public String getCreditsType() {
        return creditsType;
    }

    /**
     * Get the total current credits fulfilling the requirement.
     *
     * @return The current credits as an integer.
     */
    public int getCurrentCredits() {
        return currentCredits;
    }

    /**
     * Get the total credits needed to fulfill the requirement.
     *
     * @return The needed credits as an integer.
     */
    public int getNeededCredits() {
        return neededCredits;
    }
}
