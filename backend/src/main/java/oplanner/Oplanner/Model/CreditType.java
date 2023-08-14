package oplanner.Oplanner.Model;

import java.util.List;
import java.util.Objects;

public class CreditType {
    private int id;
    private String creditsType;
    private List<String> subType;

    // Constructors
    public CreditType() {}

    public CreditType(String creditsType, List<String> subType) {
        this.creditsType = creditsType;
        this.subType = subType;
    }

    // Generated hashCode and equals methods
    @Override
    public int hashCode() {
        return Objects.hash(id, creditsType, subType);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CreditType type = (CreditType) o;
        return Objects.equals(id, type.id);
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "Credit Type {" +
            "id=" + id +
            ", credits type'" + creditsType +
            ", sub Type'" + subType +
            '}';
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getCreditsType() {
        return creditsType;
    }

    public List<String> getSubType() {
        return subType;
    }
}
