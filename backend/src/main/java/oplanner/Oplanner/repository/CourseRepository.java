package oplanner.Oplanner.repository;
import oplanner.Oplanner.Model.Course;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.*;

public interface CourseRepository extends CrudRepository<Course, Integer> {
    @Query("""
        SELECT *
        FROM Course 
        Where number = :number
        """)
    Course findByNumber(int number);

        @Query("""
        SELECT DISTINCT c.*
        FROM Course as c inner join course_in_study_plan as s
        Where plan_Id = :planId
        """)
    Course[] findByPlanId(int planId);

    @Query("""
            SELECT *
            FROM Course
            Where plan_number = :planNumber
            """)
    Course findByCourseNumber (int planNumber);
}