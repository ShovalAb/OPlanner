package oplanner.Oplanner.repository;

import oplanner.Oplanner.Model.Course;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Integer> {
    @Query("""
        SELECT *
        FROM Course 
        WHERE number = :number
        """)
    Course findByNumber(int number);

    @Query("""
        SELECT DISTINCT c.*
        FROM Course AS c INNER JOIN course_in_study_plan AS s
        WHERE plan_Id = :planId
        """)
    Course[] findByPlanId(int planId);

    @Query("""
        SELECT *
        FROM Course
        WHERE plan_number = :planNumber
        """)
    Course findByCourseNumber(int planNumber);
}
