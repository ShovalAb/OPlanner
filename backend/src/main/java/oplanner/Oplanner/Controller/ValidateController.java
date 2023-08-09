package oplanner.Oplanner.Controller;

import org.springframework.web.bind.annotation.RestController;

import oplanner.Oplanner.Model.Course;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import oplanner.Oplanner.Logic.Logic;
import java.util.*;


@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("/api/validate")
public class ValidateController {
    private Logic logic = new Logic();

    @PostMapping
    public Course [] validateStudyPlan (@RequestBody Course[] selectedCourses){
        return logic.validate(selectedCourses);
    }
}
