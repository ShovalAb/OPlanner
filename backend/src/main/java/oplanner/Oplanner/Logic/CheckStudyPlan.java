package oplanner.Oplanner.Logic;

import java.util.ArrayList;
import java.util.List;


import oplanner.Oplanner.Model.Course;
import oplanner.Oplanner.Model.CreditType;
import oplanner.Oplanner.Model.CreditsRequirement;
import oplanner.Oplanner.Model.Dependency;
import oplanner.Oplanner.Model.MandatoryRequirement;
import oplanner.Oplanner.Response.CheckStudyPlanRespone;
import oplanner.Oplanner.Response.CreditsReqResponse;
import oplanner.Oplanner.Response.DependencyResponse;
import oplanner.Oplanner.repository.CourseRepository;
import oplanner.Oplanner.repository.CreditTypesRepository;
import oplanner.Oplanner.repository.CreditsRequirementRepository;
import oplanner.Oplanner.repository.MandatoryRequirementRepository;
import oplanner.Oplanner.repository.DependencyRepository;

public class CheckStudyPlan {

    private final MandatoryRequirementRepository mrRepository;
    private final CourseRepository courseRepository;
    private final DependencyRepository depRepository;
    private final CreditsRequirementRepository creditsRepository;
    private final CreditTypesRepository creditTypesRepository;

    private final int OK = 1;
    private final int notOk = 0;
    private final int notValid = -1;


     /**
     * Constructor to create a CheckStudyPlan instance with required repositories.
     *
     * @param mrRepository The repository for mandatory requirements.
     * @param courseRepository The repository for courses.
     * @param depRepository The repository for course dependencies.
     * @param creditsRepository The repository for credit requirements.
     * @param creditTypesRepository The repository for credit types.
     */
    public CheckStudyPlan(MandatoryRequirementRepository mrRepository, CourseRepository courseRepository, DependencyRepository depRepository, CreditsRequirementRepository creditsRepository, CreditTypesRepository creditTypesRepository)
    {

        this.mrRepository = mrRepository;
        this.courseRepository = courseRepository;
        this.depRepository = depRepository;
        this.creditsRepository = creditsRepository;
        this.creditTypesRepository = creditTypesRepository;
    }

    /**
     * Checks the validity of a study plan and generates a response.
     *
     * @param studyPlanId The ID of the study plan.
     * @param courses The list of courses in the study plan.
     * @return A CheckStudyPlanRespone object containing validation results.
     */
    public CheckStudyPlanRespone checkStudyPlanRespone (int studyPlanId, List <Course>  courses)
    {
        List<Integer>  a = checkMandatoryRequirement(studyPlanId, courses);
        List<DependencyResponse>  b = checkDependencies(studyPlanId, courses);
        List <CreditsReqResponse> c = checkCredits(studyPlanId, courses);
        int ok = checkIfPlanOK(a, b, c);
        CheckStudyPlanRespone res = new CheckStudyPlanRespone(ok, a, b, c);
        return res;
    }

    /**
     * Checks if all credit requirements are valid and met.
     *
     * @param creditsReqResponses The list of credit requirement responses.
     * @return An integer representing validation status:
     *         -1 if some credit requirement is not valid,
     *          0 if some credit requirement is not met,
     *          1 if all credit requirements are met.
     */
    public int checkIfAllCreditsReqValid (List <CreditsReqResponse> creditsReqResponses)
    {
        for (CreditsReqResponse creditReq : creditsReqResponses)
        {
            if (creditReq.getCurrentCredits() == notValid)
            {
                return notValid;
            }
            if (creditReq.getCurrentCredits() < creditReq.getNeededCredits())
            {
                return notOk;
            }
        }
        return OK;
    }


     /**
     * Checks if the entire study plan is valid based on mandatory requirements, dependencies, and credit requirements.
     *
     * @param mandatoryReq The list of missing mandatory courses.
     * @param dep The list of missing dependencies.
     * @param creditsReq The list of credit requirement responses.
     * @return An integer representing the overall validation status:
     *         -1 if credit requirements are not valid,
     *          0 if the study plan is not valid,
     *          1 if the study plan is valid.
     */    
    public int checkIfPlanOK (List<Integer> mandatoryReq, List<DependencyResponse> dep, List <CreditsReqResponse> creditsReq)
    {
        int temp = checkIfAllCreditsReqValid(creditsReq);
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

    /**
     * Checks for missing mandatory courses in the study plan.
     *
     * @param studyPlanId The ID of the study plan.
     * @param courses The list of courses in the study plan.
     * @return A list of course numbers representing missing mandatory courses.
     */
    public List<Integer> checkMandatoryRequirement (int studyPlanId, List <Course>  courses)
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

    /**
     * Checks for missing dependencies in the study plan.
     *
     * @param studyPlanId The ID of the study plan.
     * @param courses The list of courses in the study plan.
     * @return A list of DependencyResponse objects representing missing course dependencies.
     */
    public List<DependencyResponse> checkDependencies (int studyPlanId, List <Course>  courses)
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

    /**
     * Checks for credit requirement fulfillment in the study plan.
     *
     * @param studyPlanId The ID of the study plan.
     * @param courses The list of courses in the study plan.
     * @return A list of CreditsReqResponse objects representing credit requirement fulfillment status.
     */
    public List <CreditsReqResponse> checkCredits (int studyPlanId, List <Course>  courses)
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
