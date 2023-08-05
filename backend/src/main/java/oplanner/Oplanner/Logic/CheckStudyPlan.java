package oplanner.Oplanner.Logic;

import java.util.Arrays;

import oplanner.Oplanner.Model.Course;
import oplanner.Oplanner.Model.CreditsRequirement;
import oplanner.Oplanner.Model.MandatoryRequirement;
import oplanner.Oplanner.Model.StudyPlan;
import oplanner.Oplanner.repository.CourseRepository;
import oplanner.Oplanner.repository.CreditsRequirementRepository;
import oplanner.Oplanner.repository.MandatoryRequirementRepository;
import oplanner.Oplanner.repository.StudyPlanRepository;

public class CheckStudyPlan {

    private final MandatoryRequirementRepository mr;
    private final CourseRepository course;

    public CheckStudyPlan(MandatoryRequirementRepository mr, CourseRepository course)
    {
        this.mr = mr;
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
}

