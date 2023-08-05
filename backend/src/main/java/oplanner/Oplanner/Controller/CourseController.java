package oplanner.Oplanner.Controller;

import org.springframework.web.bind.annotation.RestController;
import oplanner.Oplanner.Model.Course;
import oplanner.Oplanner.repository.CourseRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("/api/course")
public class CourseController {
    private final CourseRepository course;

    public CourseController(CourseRepository course){
        this.course = course;
    }

    @GetMapping
    public Iterable<Course> findAll(){
        return course.findAll();
    }

    // @GetMapping
    // public Course[] findByCourseId(int planId){
    //     return course.findByPlanId(planId);
    // }
    
}
