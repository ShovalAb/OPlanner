package oplanner.Oplanner.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import oplanner.Oplanner.repository.StudyPlanRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import oplanner.Oplanner.Model.StudyPlan;

@RestController
@RequestMapping("/api/studyplan")
public class StudyPlanController {
    private final StudyPlanRepository studyPlans;

    public StudyPlanController(StudyPlanRepository studyPlans){
        this.studyPlans = studyPlans;
    }

    @CrossOrigin(allowedHeaders = "*", origins = "*")
    @GetMapping
    public Iterable<StudyPlan> findAll(){
        return studyPlans.findAll();
    }


}
