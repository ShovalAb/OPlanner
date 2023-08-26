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
    // private final MandatoryRequirementRepository mandatoryRequirementRepository;
    // private final DependencyRepository dependencyRepository;
    // private final CreditsRequirementRepository creditsRepository;
    // private final CreditTypesRepository creditTypesRepository;
    
    public ValidateController (
            CourseRepository courseRepository, 
            MandatoryRequirementRepository mandatoryRequirementRepository, 
            DependencyRepository dependencyRepository,
            CreditsRequirementRepository creditsRepository,
            CreditTypesRepository creditTypesRepository) {
        this.checkStudyPlanObj = new CheckStudyPlan(mandatoryRequirementRepository, courseRepository, dependencyRepository, creditsRepository, creditTypesRepository);
        this.courseRepository = courseRepository;
        // this.mandatoryRequirementRepository = mandatoryRequirementRepository;
        // this.dependencyRepository = dependencyRepository;
        // this.creditsRepository = creditsRepository;
        // this.creditTypesRepository = creditTypesRepository;
    }

    /**
     * Validate a study plan.
     *
     * @param selectedCourses Map containing planId and courses list.
     * @return CheckStudyPlanRespone containing validation results.
     */
    @PostMapping
    public CheckStudyPlanRespone validateStudyPlan (@RequestBody Map<String, Object> selectedCourses) {
        int id = Integer.parseInt(selectedCourses.get("planId").toString());
        List<Course> courses = new ArrayList<>();
        List<Integer> coursesNumber = (List<Integer>) selectedCourses.get("courses");
        for (int number : coursesNumber){
            courses.add(courseRepository.findByNumber(number));
        }
        // CheckStudyPlan checkStudyPlan = new CheckStudyPlan(mandatoryRequirementRepository, courseRepository, dependencyRepository, creditsRepository, creditTypesRepository);
        return checkStudyPlanObj.checkStudyPlanRespone(id, courses);
    }
    
    /**
     * Validate credits Requirements.
     *
     * @param selectedCourses Map containing planId and courses list.
     * @return CheckStudyPlanRespone containing validation results.
     */
    @PostMapping("/creditsReq")
    public List<CreditsReqResponse> validateStudyPlan2 (@RequestBody Map<String, Object> selectedCourses) {
        int id = Integer.parseInt(selectedCourses.get("planId").toString());
        List<Course> courses = new ArrayList<>();
        List<Integer> coursesNumber = (List<Integer>) selectedCourses.get("courses");
        for (int number : coursesNumber){
            courses.add(courseRepository.findByNumber(number));
        }
        // CheckStudyPlan checkStudyPlan = new CheckStudyPlan(mandatoryRequirementRepository, courseRepository, dependencyRepository, creditsRepository, creditTypesRepository);
        return checkStudyPlanObj.checkCredits(id, courses);
    }
}
