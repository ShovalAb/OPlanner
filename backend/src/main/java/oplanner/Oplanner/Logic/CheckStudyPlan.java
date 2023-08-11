package oplanner.Oplanner.Logic;

import java.util.Arrays;

import oplanner.Oplanner.Model.Course;
import oplanner.Oplanner.Model.CreditsRequirement;
import oplanner.Oplanner.Model.Dependency;
import oplanner.Oplanner.Model.MandatoryRequirement;
import oplanner.Oplanner.Model.StudyPlan;
import oplanner.Oplanner.repository.CourseRepository;
import oplanner.Oplanner.repository.CreditsRequirementRepository;
import oplanner.Oplanner.repository.MandatoryRequirementRepository;
import oplanner.Oplanner.repository.StudyPlanRepository;
import oplanner.Oplanner.repository.DependencyRepository;

public class CheckStudyPlan {

    private final MandatoryRequirementRepository mr;
    private final DependencyRepository dep;
    private final CourseRepository course;

    public CheckStudyPlan(MandatoryRequirementRepository mr, DependencyRepository dep, CourseRepository course)
    {
        this.mr = mr;
        this.dep = dep;
        this.course = course;
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

