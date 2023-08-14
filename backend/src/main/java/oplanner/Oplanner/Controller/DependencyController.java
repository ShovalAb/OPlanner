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
    private final DependencyRepository dependencyRepository;

    public DependencyController(DependencyRepository dependencyRepository) {
        this.dependencyRepository = dependencyRepository;
    }

    /**
     * Get all dependencies.
     *
     * @return Iterable of all dependencies.
     */
    @GetMapping
    public Iterable<Dependency> findAll() {
        return dependencyRepository.findAll();
    }
}
