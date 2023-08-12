package oplanner.Oplanner.Controller;

import org.springframework.web.bind.annotation.RestController;
import oplanner.Oplanner.Model.Course;
import oplanner.Oplanner.Response.CheckStudyPlanRespone;
import oplanner.Oplanner.repository.CourseRepository;
import oplanner.Oplanner.repository.DependencyRepository;
import oplanner.Oplanner.repository.MandatoryRequirementRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;

import oplanner.Oplanner.Logic.CheckStudyPlan;
import oplanner.Oplanner.Logic.Logic;
import java.util.*;


@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("/api/verifyPlan")
public class ValidateController {
    private Logic logic = new Logic();
    private final CourseRepository courseRepo;
    private final MandatoryRequirementRepository mr;
    private final DependencyRepository depRepository;
    
    public ValidateController (CourseRepository courseRepo, MandatoryRequirementRepository mr, DependencyRepository depRepository) {
        this.courseRepo = courseRepo;
        this.mr = mr;
        this.depRepository = depRepository;
    }

    @PostMapping
    public CheckStudyPlanRespone validateStudyPlan (@RequestBody Map<String,Object> selectedCourses){
        int id = Integer.parseInt(selectedCourses.get("planId").toString());
        List<Course> courses = new ArrayList<Course>();
        List<Integer> coursesNumber = (List<Integer>) selectedCourses.get("courses");
        for (int number : coursesNumber){
            courses.add(courseRepo.findByNumber(number));
        }
        CheckStudyPlan checkStudyPlan = new CheckStudyPlan(id, courses, mr, courseRepo, depRepository);
        return checkStudyPlan.checkStudyPlanRespone();
    }
}
