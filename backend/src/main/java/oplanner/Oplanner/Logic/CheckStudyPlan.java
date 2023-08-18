package oplanner.Oplanner.Logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.tomcat.util.digester.SystemPropertySource;

import oplanner.Oplanner.Model.Course;
import oplanner.Oplanner.Model.CreditType;
import oplanner.Oplanner.Model.CreditsRequirement;
import oplanner.Oplanner.Model.Dependency;
import oplanner.Oplanner.Model.MandatoryRequirement;
import oplanner.Oplanner.Model.StudyPlan;
import oplanner.Oplanner.Response.CheckStudyPlanRespone;
import oplanner.Oplanner.Response.CreditsReqResponse;
import oplanner.Oplanner.Response.DependencyResponse;
import oplanner.Oplanner.repository.CourseRepository;
import oplanner.Oplanner.repository.CreditTypesRepository;
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
    private final CreditsRequirementRepository creditsRepository;
    private final CreditTypesRepository creditTypesRepository;

    private final int OK = 1;
    private final int notOk = 0;
    private final int notValid = -1;



    public CheckStudyPlan(int studyPlanId, List <Course> courses, MandatoryRequirementRepository mrRepository, CourseRepository courseRepository, DependencyRepository depRepository, CreditsRequirementRepository creditsRepository, CreditTypesRepository creditTypesRepository)
    {
        this.studyPlanId = studyPlanId;
        this.courses = courses;
        this.mrRepository = mrRepository;
        this.courseRepository = courseRepository;
        this.depRepository = depRepository;
        this.creditsRepository = creditsRepository;
        this.creditTypesRepository = creditTypesRepository;
    }

    public CheckStudyPlanRespone checkStudyPlanRespone ()
    {
        List<Integer>  a = checkMandatoryRequirement();
        List<DependencyResponse>  b = checkDependencies();
        List <CreditsReqResponse> c = checkCredits();
        int ok = checkIfPlanOK(a, b, c);
        CheckStudyPlanRespone res = new CheckStudyPlanRespone(ok, a, b, c);
        return res;
    }

    public int checkIfallCreditsReqValid (List <CreditsReqResponse> creditsReqResponses)
    {
        for (CreditsReqResponse creditReq : creditsReqResponses)
        {
            if (creditReq.getCurrentCredits() == notValid)
            {
                return notValid;
            }
            if (creditReq.getCurrentCredits() < creditReq.getNeededCredits())
            {
                return OK;
            }
        }
        return notOk;
    }

    public int checkIfPlanOK (List<Integer> mandatoryReq, List<DependencyResponse> dep, List <CreditsReqResponse> creditsReq)
    {
        int temp = checkIfallCreditsReqValid(creditsReq);
        if (temp == notValid)
        {
            return notValid;
        }
        if (mandatoryReq.isEmpty() && dep.isEmpty() && temp == OK)
        {
            return OK;
        }
        else 
        {
            return notOk;
        }
    }

    public List<Integer> checkMandatoryRequirement ()
    {
        MandatoryRequirement [] mandatoryReq = mrRepository.findByPlanId(studyPlanId);
        List<Integer> missingCourses = new ArrayList<Integer> ();
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
                }   
            }
            if (found == false)
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

    public List <CreditsReqResponse> checkCredits ()
    {
        List<CreditsReqResponse> creditsReqResponse = new ArrayList <CreditsReqResponse> ();
        CreditsRequirement[] creditsRequirements = creditsRepository.findByPlanId(studyPlanId);
        int sumCurrentCredits;
        for (CreditsRequirement req : creditsRequirements)
        {
            sumCurrentCredits = 0;
            CreditType creditsType = creditTypesRepository.findByCreditType(req.getCreditsType());
            if (creditsType == null)
            {
                CreditsReqResponse response = new CreditsReqResponse(req.getCreditsType(), -1, req.getCreditsNumber());
                creditsReqResponse.add(response);
            }
            else 
            {

                List <String> creditsTypeOptions = creditsType.getSubType();
                for (Course course : courses)
                {
                    if (creditsTypeOptions.contains(course.getCreditsType()))
                    {
                        sumCurrentCredits = sumCurrentCredits + course.getCreditsNumber();
                    }
                }

                CreditsReqResponse response = new CreditsReqResponse(req.getCreditsType(), sumCurrentCredits, req.getCreditsNumber());
                creditsReqResponse.add(response);
            }
        }

        return creditsReqResponse;
    }
}

