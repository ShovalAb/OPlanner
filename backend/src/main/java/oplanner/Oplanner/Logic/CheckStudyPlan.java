package oplanner.Oplanner.Logic;

import java.util.Arrays;
import java.util.List;

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
    private final MandatoryRequirementRepository mr;
    private final CourseRepository course;

    public CheckStudyPlan(int studyPlanId, List <Course> courses, MandatoryRequirementRepository mr, CourseRepository course)
    {
        this.studyPlanId = studyPlanId;
        this.courses = courses;
        this.mr = mr;
        this.course = course;
    }

    public CheckStudyPlanRespone checkStudyPlanRespone ()
    {
        int[] d11 = {3065, 3067};
        int[] d12 = {3066, 3067};
        DependencyResponse d1 = new DependencyResponse(d11);
        DependencyResponse d2 = new DependencyResponse (d12);
        DependencyResponse[] d = {d1, d2};
        CreditsReqResponse c1 = new CreditsReqResponse("Math", 40, 50);
        CreditsReqResponse c2 = new CreditsReqResponse("Comp", 20, 20);
        CreditsReqResponse[] c = {c1, c2};
        int[] a = {3065, 3066};
        CheckStudyPlanRespone res = new CheckStudyPlanRespone(0, a, d, c);
        return res;
    }

    public Course[] checkMandatoryRequirement ()
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

    // public Dependency[] checkDependencies ()
    // {
    //     Dependency[] missingDependencies = new Dependency[0];
    //     for (Course course : courses)
    //     {
    //         Dependency[] depForThisCourse = dep.findByCourseId(course.getId());
    //     }
    // }
}

