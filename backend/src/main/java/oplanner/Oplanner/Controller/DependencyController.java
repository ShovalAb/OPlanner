package oplanner.Oplanner.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import oplanner.Oplanner.Model.Dependency;
import oplanner.Oplanner.repository.DependencyRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("/api/dependency")
public class DependencyController {
        private final DependencyRepository dep;

    public DependencyController(DependencyRepository dep){
        this.dep = dep;
    }

    @GetMapping
    public Iterable<Dependency> findAll(){
        return dep.findAll();
    }
    
}
