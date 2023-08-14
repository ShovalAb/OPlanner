package oplanner.Oplanner.Controller;

import oplanner.Oplanner.Model.CreditsRequirement;
import oplanner.Oplanner.repository.CreditsRequirementRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("/api/credits")
public class CreditsController {
    private final CreditsRequirementRepository creditsRepository;

    public CreditsController(CreditsRequirementRepository creditsRepository) {
        this.creditsRepository = creditsRepository;
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
}
