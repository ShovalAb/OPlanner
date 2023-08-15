package oplanner.Oplanner.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import oplanner.Oplanner.repository.StudyPlanRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import oplanner.Oplanner.Model.StudyPlan;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("/api/studyplan")
public class StudyPlanController {
    private final StudyPlanRepository studyPlanRepository;

    public StudyPlanController(StudyPlanRepository studyPlanRepository) {
        this.studyPlanRepository = studyPlanRepository;
    }

    /**
     * Get all study plans.
     *
     * @return Iterable of all study plans.
     */
    @RequestMapping(method = RequestMethod.GET)
    public Iterable<StudyPlan> findAll() {
        return studyPlanRepository.findAll();
    }

    /**
     * Get a study plan by its ID.
     *
     * @param planId Study plan ID.
     * @return Study plan with the given ID.
     */
    @RequestMapping(method = RequestMethod.GET, params = "planId")
    public StudyPlan getById(@RequestParam("planId") int planId) {
        return studyPlanRepository.findPlanById(planId);
    }
}
