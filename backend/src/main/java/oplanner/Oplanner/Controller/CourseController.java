package oplanner.Oplanner.Controller;

import org.springframework.web.bind.annotation.RestController;

import jakarta.websocket.server.PathParam;
import oplanner.Oplanner.Model.Course;
import oplanner.Oplanner.Model.StudyPlan;
import oplanner.Oplanner.repository.CourseRepository;
import oplanner.Oplanner.Response.CoursesByCreditsType;


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
    public Map<String, List<Course>> getCoursesByCreditsType (@RequestParam("planId") int planId){
        Course [] results = course.findByPlanId(planId);
        Map<String, List<Course>> map = new HashMap<>();
        for (Course result : results){
            String creditType = result.getCreditsType();
            if(!map.containsKey(creditType)){
                map.put(creditType, new ArrayList<Course>());
            }
            map.get(creditType).add(result);
        }
            // List<CoursesByCreditsType> response = new ArrayList<>();
            // for (String creditType : map.keySet()) {
            // response.add(new CoursesByCreditsType(creditType, map.get(creditType).getCourses()));
            // }

        return map;
    }



    
}
