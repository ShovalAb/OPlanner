package oplanner.Oplanner.Controller;

import oplanner.Oplanner.Model.Course;
import oplanner.Oplanner.repository.CourseRepository;
import oplanner.Oplanner.Response.CoursesByCreditsType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import java.util.*;


@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("/api/course")
public class CourseController {
    private final CourseRepository course;

    public CourseController(CourseRepository course){
        this.course = course;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Course> findAll(){
        return course.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, params="number")
    public ResponseEntity<Course> getByNumber (@RequestParam("number") int number){
        Course result = course.findByNumber(number);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(params="planId", method = RequestMethod.GET)
    public List<CoursesByCreditsType> getCoursesByCreditsType (@RequestParam("planId") int planId){
        Course [] results = course.findByPlanId(planId);
        Map<String, List<Course>> map = new HashMap<>();
        for (Course result : results){
            String dep = result.getCreditsType();
            if(!map.containsKey(dep)){
                map.put(dep, new ArrayList<Course>());
            }
            map.get(dep).add(result);
        }
        List<CoursesByCreditsType> response = new ArrayList<>();
        for (Map.Entry<String, List<Course>> entry : map.entrySet()) {
            CoursesByCreditsType courseType = new CoursesByCreditsType(entry.getKey(), entry.getValue());
            response.add(courseType);
        }

        return response;
    }



    
}
