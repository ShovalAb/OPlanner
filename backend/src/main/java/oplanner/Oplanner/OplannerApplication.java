package oplanner.Oplanner;

import oplanner.Oplanner.Model.Course;
import oplanner.Oplanner.Model.Dependency;
import oplanner.Oplanner.Model.StudyPlan;
import oplanner.Oplanner.repository.CourseRepository;
import oplanner.Oplanner.repository.DependencyRepository;
import oplanner.Oplanner.repository.StudyPlanRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin(allowedHeaders = "*", origins = "*")
@SpringBootApplication
@RestController
public class OplannerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OplannerApplication.class, args)
	;}

	@Bean
	CommandLineRunner commandLineRunner(StudyPlanRepository studyPlan, DependencyRepository dep, CourseRepository course){
		return args -> {
			studyPlan.save(new StudyPlan("computer"));
			studyPlan.save(new StudyPlan("Math"));
			int [] intarr = {1,2};
			dep.save(new Dependency(2,intarr));
			course.save(new Course("Infi",3067, "base", 4));
		};
	}
}
