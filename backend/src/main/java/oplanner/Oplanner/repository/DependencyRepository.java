package oplanner.Oplanner.repository;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import oplanner.Oplanner.Model.Dependency;


public interface DependencyRepository extends CrudRepository<Dependency, Integer> {
    @Query("""
            SELECT *
            FROM Dependency
            WHERE dependent_course = :courseId
            """)
    Dependency[] findByCourseId(int courseId);
}
