package oplanner.Oplanner.repository;

import oplanner.Oplanner.Model.Course;
import oplanner.Oplanner.Model.CreditType;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.*;

public interface CreditTypesRepository extends CrudRepository<CreditType, Integer> {
    @Query("""
        SELECT *
        FROM credit_types
        WHERE credits_type = :type
        LIMIT 1
        """)
    CreditType findByCreditType(String type);
}