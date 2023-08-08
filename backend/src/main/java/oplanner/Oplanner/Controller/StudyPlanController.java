package oplanner.Oplanner.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import oplanner.Oplanner.repository.StudyPlanRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import oplanner.Oplanner.Model.Course;
import oplanner.Oplanner.Model.StudyPlan;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("/api/studyplan")
public class StudyPlanController {
    private final StudyPlanRepository studyPlan;

    public StudyPlanController(StudyPlanRepository studyPlan){
        this.studyPlan = studyPlan;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<StudyPlan> findAll(){
        return studyPlan.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, params = "planId")
    public StudyPlan getById(@RequestParam("planId") int planId){
        return studyPlan.findPlanById(planId);
    }
}