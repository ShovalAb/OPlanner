package oplanner.Oplanner.Controller;

import org.springframework.web.bind.annotation.RestController;

import oplanner.Oplanner.Model.CreditsRequirement;
import oplanner.Oplanner.Model.StudyPlan;
import oplanner.Oplanner.repository.CreditsRequirementRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import java.util.*;


@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("/api/credits")
public class CreditsController {
    private final CreditsRequirementRepository credits;

    public CreditsController(CreditsRequirementRepository credits){
        this.credits = credits;
    }

    @RequestMapping(method = RequestMethod.GET, params = "planId")
    public CreditsRequirement[] getByPlanId(@RequestParam("planId") int planId){
        return credits.findByPlanId(planId);
    }
}