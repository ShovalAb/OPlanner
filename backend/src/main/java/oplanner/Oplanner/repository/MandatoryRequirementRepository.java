package oplanner.Oplanner.repository;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import oplanner.Oplanner.Model.MandatoryRequirement;


public interface MandatoryRequirementRepository extends CrudRepository<MandatoryRequirement, Integer> {
    @Query("""
            SELECT * 
            FROM mandatory_requirement
            WHERE plan_id = :planId
            """)
    MandatoryRequirement[] findByPlanId(int planId);     
}

