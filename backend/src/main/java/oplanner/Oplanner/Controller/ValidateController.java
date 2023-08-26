package oplanner.Oplanner.Controller;

import org.springframework.web.bind.annotation.RestController;
import oplanner.Oplanner.Model.Course;
import oplanner.Oplanner.Response.CheckStudyPlanRespone;
import oplanner.Oplanner.Response.CreditsReqResponse;
import oplanner.Oplanner.repository.CourseRepository;
import oplanner.Oplanner.repository.CreditTypesRepository;
import oplanner.Oplanner.repository.CreditsRequirementRepository;
import oplanner.Oplanner.repository.DependencyRepository;
import oplanner.Oplanner.repository.MandatoryRequirementRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import oplanner.Oplanner.Logic.CheckStudyPlan;
import java.util.*;


@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("/api/verifyPlan")
public class ValidateController {
    private final CheckStudyPlan checkStudyPlanObj;
    private final CourseRepository courseRepository;
    
    /**
     * Constructor to create a ValidateController instance with required repositories and dependencies.
     *
     * @param courseRepository The repository for courses.
     * @param mandatoryRequirementRepository The repository for mandatory requirements.
     * @param dependencyRepository The repository for course dependencies.
     * @param creditsRepository The repository for credit requirements.
     * @param creditTypesRepository The repository for credit types.
     */
    public ValidateController(CourseRepository courseRepository, MandatoryRequirementRepository mandatoryRequirementRepository, DependencyRepository dependencyRepository, CreditsRequirementRepository creditsRepository, CreditTypesRepository creditTypesRepository) {
        // Create a CheckStudyPlan instance with the provided repositories and dependencies
        this.checkStudyPlanObj = new CheckStudyPlan(mandatoryRequirementRepository, courseRepository, dependencyRepository, creditsRepository, creditTypesRepository);
        
        // Set the courseRepository for further use within the ValidateController instance
        this.courseRepository = courseRepository;
    }



/**
 * Validate a study plan.
 *
 * @param selectedCourses Map containing planId and courses list.
 * @return CheckStudyPlanRespone containing validation results.
 */
@PostMapping("/validateStudyPlan")
public CheckStudyPlanRespone validateStudyPlan(@RequestBody Map<String, Object> selectedCourses) {
    int id;
    List<Course> courses = new ArrayList<>();

    Object planIdObj = selectedCourses.get("planId");
    Object coursesObj = selectedCourses.get("courses");

    if (!(planIdObj instanceof Integer) || !(coursesObj instanceof List)) {
        // Handle invalid input data
        // Return a CheckStudyPlanRespone object with -1 status
        return new CheckStudyPlanRespone(-1,null,null,null);
    }

    id = (Integer) planIdObj;

    List<?> coursesList = (List<?>) coursesObj;
    for (Object courseNumberObj : coursesList) {
        if (courseNumberObj instanceof Integer) {
            int courseNumber = (Integer) courseNumberObj;
            Course course = courseRepository.findByNumber(courseNumber);
            if (course != null) {
                courses.add(course);
            }
        } else {
            return new CheckStudyPlanRespone(-1,null,null,null);
        }
        }
    return checkStudyPlanObj.checkStudyPlanRespone(id, courses);
}

    
/**
 * Validate credit requirements.
 *
 * @param selectedCourses Map containing planId and courses list.
 * @return List of CreditsReqResponse containing validation results.
 */
@PostMapping("/creditsReq")
public List<CreditsReqResponse> validateStudyPlan2(@RequestBody Map<String, Object> selectedCourses) {
    int id;
    List<Course> courses = new ArrayList<>();
    
    Object planIdObj = selectedCourses.get("planId");
    Object coursesObj = selectedCourses.get("courses");
    
    if (!(planIdObj instanceof Integer) || !(coursesObj instanceof List)) {
        // Handle invalid input data
        // Return an empty list
        return Collections.emptyList(); 
    }
    
    id = (Integer) planIdObj;
    
    List<?> coursesList = (List<?>) coursesObj;
    for (Object courseNumberObj : coursesList) {
        if (courseNumberObj instanceof Integer) {
            int courseNumber = (Integer) courseNumberObj;
            Course course = courseRepository.findByNumber(courseNumber);
            if (course != null) {
                courses.add(course);
            } 
        } else {
            // Return an empty list
            return Collections.emptyList(); 
        }
    }
    
    return checkStudyPlanObj.checkCredits(id, courses);
}

}


