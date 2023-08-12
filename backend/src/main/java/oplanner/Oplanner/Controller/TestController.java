package oplanner.Oplanner.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import oplanner.Oplanner.repository.StudyPlanRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import oplanner.Oplanner.Model.Dependency;
import oplanner.Oplanner.Model.StudyPlan;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("/api/test")
public class TestController {
    private final DependencyController dep;

    public TestController(DependencyController dep){
        this.dep = dep;
    }
    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Dependency> findAll(){
        return dep.findAll();
    }

    
}
