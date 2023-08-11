package oplanner.Oplanner.repository;

import oplanner.Oplanner.Model.CreditsRequirement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jdbc.repository.query.Query;

public interface CreditsRequirementRepository extends CrudRepository<CreditsRequirement, Integer> {
    @Query("""
            SELECT * 
            FROM credits_requirement
<<<<<<< HEAD
            WHERE plna_id = :planId
=======
            WHERE plan_id = :planId
>>>>>>> shoval
            """)
    CreditsRequirement[] findByPlanId(int planId);
}