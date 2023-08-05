package oplanner.Oplanner.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import oplanner.Oplanner.repository.StudyPlanRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import oplanner.Oplanner.Model.StudyPlan;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("/api/studyplan")
public class StudyPlanController {
    private final StudyPlanRepository studyPlan;

    public StudyPlanController(StudyPlanRepository studyPlan){
        this.studyPlan = studyPlan;
    }

    @GetMapping
    public Iterable<StudyPlan> findAll(){
        return studyPlan.findAll();
    }
    // public StudyPlan findById(){
    //     return studyPlan.findPlanById(1);
    // }

    


}
