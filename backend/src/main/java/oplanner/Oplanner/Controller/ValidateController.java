package oplanner.Oplanner.Controller;

import org.springframework.web.bind.annotation.RestController;
import oplanner.Oplanner.Model.Course;
import oplanner.Oplanner.repository.CourseRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import oplanner.Oplanner.Logic.Logic;
import java.util.*;


@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("/api/verifyPlan")
public class ValidateController {
    private Logic logic = new Logic();
    private final CourseRepository courseRepo;
    
    public ValidateController (CourseRepository courseRepo){
        this.courseRepo = courseRepo;
    }

    @PostMapping
    public List<Course> validateStudyPlan (@RequestBody Map<String,Object> selectedCourses){
        int id = (int) selectedCourses.get("planId");
        List<Course> courses = new ArrayList<Course>();
        List<Integer> coursesNumber = (List<Integer>) selectedCourses.get("courses");
        for (int number : coursesNumber){
            courses.add(courseRepo.findByNumber(number));
        }
        return logic.validate(id, courses);
    }
}
