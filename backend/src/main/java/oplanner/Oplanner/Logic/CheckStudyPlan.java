package oplanner.Oplanner.Logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.tomcat.util.digester.SystemPropertySource;

import oplanner.Oplanner.Model.Course;
import oplanner.Oplanner.Model.CreditsRequirement;
import oplanner.Oplanner.Model.Dependency;
import oplanner.Oplanner.Model.MandatoryRequirement;
import oplanner.Oplanner.Model.StudyPlan;
import oplanner.Oplanner.Response.CheckStudyPlanRespone;
import oplanner.Oplanner.Response.CreditsReqResponse;
import oplanner.Oplanner.Response.DependencyResponse;
import oplanner.Oplanner.repository.CourseRepository;
import oplanner.Oplanner.repository.CreditsRequirementRepository;
import oplanner.Oplanner.repository.MandatoryRequirementRepository;
import oplanner.Oplanner.repository.StudyPlanRepository;
import oplanner.Oplanner.repository.DependencyRepository;

public class CheckStudyPlan {

    private final int studyPlanId;
    private final List <Course>  courses;
    private final MandatoryRequirementRepository mrRepository;
    private final CourseRepository courseRepository;
    private final DependencyRepository depRepository;

    public CheckStudyPlan(int studyPlanId, List <Course> courses, MandatoryRequirementRepository mrRepository, CourseRepository courseRepository, DependencyRepository depRepository)
    {
        this.studyPlanId = studyPlanId;
        this.courses = courses;
        this.mrRepository = mrRepository;
        this.courseRepository = courseRepository;
        this.depRepository = depRepository;
    }

    public CheckStudyPlanRespone checkStudyPlanRespone ()
    {
        int[] d11 = {3065, 3067};
        int[] d12 = {3066, 3067};
        // DependencyResponse d1 = new DependencyResponse(3066, d11);
        // DependencyResponse d2 = new DependencyResponse(3065, d12);
        List<DependencyResponse>  d = checkDependencies();
        CreditsReqResponse c1 = new CreditsReqResponse("Math", 40, 50);
        CreditsReqResponse c2 = new CreditsReqResponse("Comp", 20, 20);
        CreditsReqResponse[] c = {c1, c2};
        List<Integer>  a = checkMandatoryRequirement();
        CheckStudyPlanRespone res = new CheckStudyPlanRespone(1, a, d, c);
        return res;
    }

    public List<Integer> checkMandatoryRequirement ()
    {
        MandatoryRequirement [] mandatoryReq = mrRepository.findByPlanId(studyPlanId);
        List<Integer>  missingCourses = new ArrayList<Integer> ();
        boolean found = false;
        for (MandatoryRequirement req : mandatoryReq)
        {
            found = false;
            for (int optionReq : req.getCourseId())
            {
                for (Course c : courses)
                {
                    if (c.getCourseNumber() == optionReq)
                        found = true;
                        break;
                }   
            }
            if (found)
            {
                continue;
            }
            else
            {
                missingCourses.add(req.getCourseId()[0]);

            }

        }
        return missingCourses;
    }

    public List<DependencyResponse> checkDependencies ()
    {
        List<DependencyResponse> missingDependencies = new ArrayList<DependencyResponse> ();
        List<Integer> missingDependenciesForSpecificCourse = new ArrayList<Integer> ();
        boolean foundDep = false;
        boolean courseMissingDep = false;
        for (Course course : courses)
        {
            courseMissingDep = false;
            Dependency[] depForThisCourse = depRepository.findByCourseId(course.getCourseNumber());
            for (Dependency dep : depForThisCourse)
            {
                foundDep = false;
                for (int option : dep.getBaseCourse()){
                    if (courses.contains(courseRepository.findByNumber(option))){
                        foundDep = true;
                    }
                }
                if (foundDep == false){
                    missingDependenciesForSpecificCourse.add(dep.getBaseCourse().get(0));
                    courseMissingDep = true;
                } 
            }
            if (courseMissingDep == true){
                DependencyResponse d = new DependencyResponse (course.getCourseNumber(),missingDependenciesForSpecificCourse );
                missingDependencies.add(d);
                missingDependenciesForSpecificCourse = new ArrayList<Integer> ();
            }

        }
        return missingDependencies;
    }
}

