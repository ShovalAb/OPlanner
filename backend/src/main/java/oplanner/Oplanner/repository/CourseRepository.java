package oplanner.Oplanner.repository;
import oplanner.Oplanner.Model.Course;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.*;

public interface CourseRepository extends CrudRepository<Course, Integer> {
    @Query("""
        SELECT *
        FROM course 
        Where number = :number
        """)
    Course findByNumber(int number);

        @Query("""
        SELECT DISTINCT c.*
        FROM course as c inner join Course_In_Study_Plan as s
        Where plan_Id = :planId
        """)
    Course[] findByPlanId(int planId);

    @Query("""
            SELECT *
            FROM course
            Where plan_number = :planNumber
            """)
    Course findByCourseNumber (int planNumber);
}