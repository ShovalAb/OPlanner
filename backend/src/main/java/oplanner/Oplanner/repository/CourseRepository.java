package oplanner.Oplanner.repository;
import oplanner.Oplanner.Model.Course;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Integer> {
    @Query("""
            SELECT *
            FROM course
            Where id = :id
            """)
    Course findById(int id);
}