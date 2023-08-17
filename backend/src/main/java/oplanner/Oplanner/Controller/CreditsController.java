package oplanner.Oplanner.Controller;

import oplanner.Oplanner.Model.Course;
import oplanner.Oplanner.Model.CreditsRequirement;
import oplanner.Oplanner.Response.CheckStudyPlanRespone;
import oplanner.Oplanner.repository.CourseRepository;
import oplanner.Oplanner.repository.CreditsRequirementRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("/api/credits")
public class CreditsController {
    private final CreditsRequirementRepository creditsRepository;
    private final CourseRepository courseRepository;

    public CreditsController(CreditsRequirementRepository creditsRepository, CourseRepository courseRepository) {
        this.creditsRepository = creditsRepository;
        this.courseRepository = courseRepository;
    }

    /**
     * Get credits requirements by plan ID.
     *
     * @param planId Plan ID.
     * @return Array of credits requirements for the given plan ID.
     */
    @RequestMapping(method = RequestMethod.GET, params = "planId")
    public ResponseEntity<CreditsRequirement[]> getByPlanId(@RequestParam("planId") int planId) {
        try {
            CreditsRequirement[] creditsRequirements = creditsRepository.findByPlanId(planId);
            if (creditsRequirements != null && creditsRequirements.length > 0) {
                return ResponseEntity.ok(creditsRequirements);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Get credits requirements by course number.
     *
     * @param selectedCourses Map containing planId and courses list.
     * @return 
     */
    @PostMapping
    public CheckStudyPlanRespone getCreditsReq (@RequestBody Map<String, Object> selectedCourses) {
        int id = Integer.parseInt(selectedCourses.get("planId").toString());
        List<Course> courses = new ArrayList<>();
        List<Integer> coursesNumber = (List<Integer>) selectedCourses.get("courses");
        for (int number : coursesNumber){
                courses.add(courseRepository.findByNumber(number));
        }
        return new CheckStudyPlanRespone(id=1, coursesNumber=null, null, null);
    }
}
