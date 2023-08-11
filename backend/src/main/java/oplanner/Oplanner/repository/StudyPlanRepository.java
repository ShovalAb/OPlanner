package oplanner.Oplanner.repository;

import oplanner.Oplanner.Model.StudyPlan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jdbc.repository.query.Query;

public interface StudyPlanRepository extends CrudRepository<StudyPlan, Integer> {
    @Query("""
            SELECT *
            FROM study_plan
            WHERE id = :id
            """)
    StudyPlan findPlanById(int id);
}

