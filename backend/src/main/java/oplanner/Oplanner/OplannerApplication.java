package oplanner.Oplanner;

import oplanner.Oplanner.Model.StudyPlan;
import oplanner.Oplanner.repository.StudyPlanRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@SpringBootApplication
@RestController
public class OplannerApplication {

	public static void main(String[] args) {SpringApplication.run(OplannerApplication.class, args);}

	@Bean
	CommandLineRunner commandLineRunner(StudyPlanRepository studyPlan){
		return args -> {
			studyPlan.save(new StudyPlan("computer"));
				};
	}

	// @GetMapping("/")
	// public String apiRoot(){
	// 	return "hello world";
	// }

}
