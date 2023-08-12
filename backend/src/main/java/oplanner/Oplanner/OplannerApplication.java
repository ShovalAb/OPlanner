package oplanner.Oplanner;

import oplanner.Oplanner.Model.Course;
import oplanner.Oplanner.Model.CourseInStudyPlan;
import oplanner.Oplanner.Model.CreditsRequirement;
import oplanner.Oplanner.Model.Dependency;
import oplanner.Oplanner.Model.MandatoryRequirement;
import oplanner.Oplanner.Model.StudyPlan;
import oplanner.Oplanner.repository.CourseInStudyPlanRepository;
import oplanner.Oplanner.repository.CourseRepository;
import oplanner.Oplanner.repository.CreditsRequirementRepository;
import oplanner.Oplanner.repository.DependencyRepository;
import oplanner.Oplanner.repository.MandatoryRequirementRepository;
import oplanner.Oplanner.repository.StudyPlanRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;


@CrossOrigin(allowedHeaders = "*", origins = "*")
@SpringBootApplication
@RestController
public class OplannerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OplannerApplication.class, args)
	;}

	@Bean
	CommandLineRunner commandLineRunner(StudyPlanRepository studyPlan, DependencyRepository dep, CourseRepository course, CourseInStudyPlanRepository courseSP, CreditsRequirementRepository creditsReq, MandatoryRequirementRepository mrRepository, DependencyRepository depRepository){
		return args -> {
			studyPlan.save(new StudyPlan("computer"));
			studyPlan.save(new StudyPlan("computer science"));
			List<Integer> intarr = new ArrayList<Integer> ();
			intarr.add(3065);
			intarr.add(3066);
			dep.save(new Dependency(3067,intarr));
			course.save(new Course("Infi",3067, "base", 4));
			course.save(new Course("Liniar",3065, "base", 4));
			course.save(new Course("Liniar 2",3066, "Advanced", 4));
			courseSP.save(new CourseInStudyPlan(1, 2, "Math"));
			courseSP.save(new CourseInStudyPlan(1, 1, "Must"));
			creditsReq.save(new CreditsRequirement(1,1,"Computser Science",40));
			creditsReq.save(new CreditsRequirement(1,1,"Advanced Computser Science",20));
			int[]a = {3065, 3066};
			mrRepository.save(new MandatoryRequirement(1,a));

		};
	}


}
