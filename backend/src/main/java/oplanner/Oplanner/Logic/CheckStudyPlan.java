package oplanner.Oplanner.Logic;

import java.util.Arrays;

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

    private final MandatoryRequirementRepository mr;
    private final DependencyRepository dep;
    private final CourseRepository course;

    public CheckStudyPlan(int studyPlanId, Course[] courses, MandatoryRequirementRepository mr, DependencyRepository dep, CourseRepository course)
    {
        this.mr = mr;
        this.dep = dep;
        this.course = course;
    }

    public CheckStudyPlanRespone checkStudyPlanRespone (int studyPlanId, Course[] courses)
    {
        int[] d11 = {122, 123};
        int[] d12 = {456, 457};
        DependencyResponse d1 = new DependencyResponse(d11);
        DependencyResponse d2 = new DependencyResponse (d12);
        DependencyResponse[] d = {d1, d2};
        CreditsReqResponse c1 = new CreditsReqResponse("Math", 40, 50);
        CreditsReqResponse c2 = new CreditsReqResponse("Comp", 20, 20);
        CreditsReqResponse[] c = {c1, c2};
        int[] a = {1234, 3456, 2047, 2043};
        CheckStudyPlanRespone res = new CheckStudyPlanRespone(0, a, d, c);
        return res;
    }

    public Course[] checkMandatoryRequirement (int studyPlanId, Course[] courses)
    {
        MandatoryRequirement [] mandatoryReq = mr.findByPlanId(studyPlanId);
        Course[] missingCourses = new Course[0];
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
                Course[] newArray = Arrays.copyOf(missingCourses, missingCourses.length + req.getCourseId().length);
                for (int i=0; i < req.getCourseId().length; i++)
                {
                    newArray [missingCourses.length + i] = course.findByCourseNumber(req.getCourseId()[i]);
                }
                missingCourses = newArray;
            }

        }

        return missingCourses;
    }

    // public Dependency[] checkDependencies (int studyPlanId, Course[] courses)
    // {
    //     Dependency[] missingDependencies = new Dependency[0];
    //     for (Course course : courses)
    //     {
    //         Dependency[] depForThisCourse = dep.findByCourseId(course.getId());
    //     }
    // }
}

