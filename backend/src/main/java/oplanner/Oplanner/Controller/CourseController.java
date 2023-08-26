package oplanner.Oplanner.Controller;

import oplanner.Oplanner.Model.Course;
import oplanner.Oplanner.repository.CourseRepository;
import oplanner.Oplanner.Response.CoursesByCreditsType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;

import java.util.*;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("/api/course")
public class CourseController {
    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    /**
     * Get all courses.
     *
     * @return List of all courses.
     */
    @GetMapping("/allCourses")
    public Iterable<Course> findAll() {
        try {
            return courseRepository.findAll();
        } catch (Exception e) {
            // Handle exception or log it
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * Get a course by its number.
     *
     * @param number Course number.
     * @return ResponseEntity with the course if found, or not found response.
     */
    @RequestMapping(method = RequestMethod.GET, params = "number")
    public ResponseEntity<Course> getByNumber(@RequestParam("number") int number) {
        try {
            Course result = courseRepository.findByNumber(number);
            if (result != null) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Get courses grouped by credits type for a specific plan.
     *
     * @param planId Plan ID.
     * @return List of courses grouped by credits type.
     */
    // @RequestMapping(params = "planId", method = RequestMethod.GET)
    // public List<CoursesByCreditsType> getCoursesByCreditsType(@RequestParam("planId") int planId) {
    //     try {
    //         Course[] results = courseRepository.findByPlanId(planId);
    //         Map<String, List<Course>> map = new HashMap<>();
    //         for (Course result : results) {
    //             String dep = result.getCreditsType();
    //             map.computeIfAbsent(dep, k -> new ArrayList<>()).add(result);
    //         }
    //         List<CoursesByCreditsType> response = new ArrayList<>();
    //         for (Map.Entry<String, List<Course>> entry : map.entrySet()) {
    //             CoursesByCreditsType courseType = new CoursesByCreditsType(entry.getKey(), entry.getValue());
    //             response.add(courseType);
    //         }
    //         return response;
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         return Collections.emptyList();
    //     }
    // }

    /**
     * Get courses grouped by credits type for a specific plan.
     *
     * @param planId Plan ID.
     * @return List of courses grouped by credits type.
     */
    @RequestMapping(params = "planId", method = RequestMethod.GET)
    public List<CoursesByCreditsType> getCoursesByCreditsType(@RequestParam("planId") int planId) {
        try {
            Course[] results = courseRepository.findAllCourses();
            Map<String, List<Course>> map = new HashMap<>();
            for (Course result : results) {
                String dep = result.getCreditsType();
                map.computeIfAbsent(dep, k -> new ArrayList<>()).add(result);
            }
            List<CoursesByCreditsType> response = new ArrayList<>();
            for (Map.Entry<String, List<Course>> entry : map.entrySet()) {
                CoursesByCreditsType courseType = new CoursesByCreditsType(entry.getKey(), entry.getValue());
                response.add(courseType);
            }
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}

