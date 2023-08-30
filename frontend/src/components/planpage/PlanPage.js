import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import api from '../../api/axiosConfig';
import CoursesDrag from '../couresesdrag/CoursesDrag'
import MissingCourses from "../missingcourses/MissingCourses";
import SummaryButton from "./SummaryButton";
import CreditReqTable from "./CreditReqTable";
import { headlineTextColor , planpageBGColor} from "../colors";
import ProgressBar from "../progressbar/ProgressBar";

const PlanPage = () => {
    const routeParams = useParams();
    const [courses, setCourses] = useState();
    const [coursesMust, setCoursesMust] = useState();
    const [coursesDepen, setCoursesDepen] = useState();
    const [nakazReq, setNakazReq] = useState();
    const [planReady, setPlanReady] = useState(false);
    const [studyPlanName, setStudyPlanName] = useState("");

    const getStudyPlanNameByPlanId = async (planId) => {
        const params = {
                planId : planId,
            }
        try{
            const response = await api.get("/api/studyplan", {params})
            console.log("resppp - " + response)
            return response.data.planName
        } catch (error) {
            console.log(error)
            return ""
        }
    }

    const getCourseByNumber = (courseNumber) => {
        // console.log("Trying to find - " + courseNumber)
        for (let i = 0; i < courses.length; i++) {
            const coursesClass = courses[i];
            for (let j = 0; j < coursesClass.courses.length; j++) {
                const course = coursesClass.courses[j];
                if (course.courseNumber == courseNumber) {
                    // console.log("Found it!")
                    return (course)
                }
            }
        }
        // console.log("Didn't Found it")
        return null;
    }

    const setCoursesChosenState = (courses) => {

        for (let i = 0; i < courses.length; i++) {
            var coursesClass = courses[i];
            for (let j = 0; j < coursesClass.courses.length; j++) {
                coursesClass.courses[j].chosen = false;
            }
        }
        return courses
    }

    const getCourses = async (studyPlanId) => {
        try {
            const params = {
                planId : studyPlanId,
            }
            const response = await api.get('/api/course', {params})
            console.log('Got from server ' + response.data)
            setCourses(setCoursesChosenState(response.data))
        } catch (error) {
            //console.log("Plan " + studyPlanId.toString() + " doesn't exist!")
            console.log(error)
        }
    }

    async function fetchStudyPlanName (planId) {
        const name = await getStudyPlanNameByPlanId(planId)
        console.log("gotttt - " + name)
        setStudyPlanName(name)
    }

    useEffect(() => {
        fetchStudyPlanName(routeParams.studyPlanId)
        getCourses(routeParams.studyPlanId);
        updateCreditReqNum();
    },[])
    
    const collectChosenCourses = () => {
        var coursesChosen = Array()
        if (courses != undefined) {
            for (let i = 0; i < courses.length; i++) {
                const coursesClass = courses[i];
                for (let j = 0; j < coursesClass.courses.length; j++) {
                    const course = coursesClass.courses[j];
                    if (course.chosen) {
                        coursesChosen.push(course)
                    }
                }            
            }
        }
        return coursesChosen;
    }


    const collectChosenCoursesNumbers = () => {
        var coursesChosen = Array()
        if (courses != undefined) {
            for (let i = 0; i < courses.length; i++) {
                const coursesClass = courses[i];
                for (let j = 0; j < coursesClass.courses.length; j++) {
                    const course = coursesClass.courses[j];
                    if (course.chosen) {
                        coursesChosen.push(course.courseNumber)
                    }
                }            
            }
        }
        return coursesChosen;
    }

    const updateCreditReqNum = async () => {
        // console.log("HEREEEE")
        try {
            const coursesChosen = collectChosenCoursesNumbers();
            const response = await api.post('/api/verifyPlan/creditsReq', {'planId':routeParams.studyPlanId, 'courses':coursesChosen})
            setNakazReq(response.data)
            
        } catch (error) {
            console.log(error)
        }
    }

    const validateCourses = async (studyPlanId, courses) => {
        try {
            const coursesChosen = collectChosenCoursesNumbers();
            const response = await api.post('/api/verifyPlan', {'planId':studyPlanId, 'courses':coursesChosen})
            console.log(response.data)
            setCoursesMust(response.data["coursesMust"])
            setCoursesDepen(response.data["coursesDepen"])
            setNakazReq(response.data["creditsReqResponse"])
            
            if (response.data.ok == 1) {
                // ok = 1, the program is validated
                setPlanReady(true)
            } else {
                // ok = 0 OR -1, the program is not validated
                setPlanReady(false)
            }

        } catch (error) {
            console.log(error)
        }
    }

    return (
        <div style={{background: planpageBGColor}}>
            <ProgressBar stepNumber={2} studyPlanId={routeParams.studyPlanId}></ProgressBar>
            <div>
                <h1 style={{color: headlineTextColor, fontWeight: 'bold'}}>"תכנון תוכנית לימודים "{studyPlanName}</h1>
            </div>
            <CoursesDrag courses={courses} updateCreditReqNum={updateCreditReqNum}></CoursesDrag>
            <button className="buttonValidate" onClick={e => validateCourses(routeParams.studyPlanId,courses)} style={{marginTop:'50px'}}>בדיקת תוכנית לימודים</button>
            <div style={{ display: 'flex', justifyContent: 'center', marginTop: '20px' }}>
                <div style={{ maxWidth: '800px', width: '45%', padding: '0 20px' , marginRight: '5%'}}>
                    <CreditReqTable creditReq={nakazReq}></CreditReqTable>
                </div>
                <div style={{ maxWidth: '800px', width: '45%', padding: '0 20px' , marginLeft: '5%'}}>
                    <MissingCourses coursesDepen={coursesDepen} coursesMust={coursesMust} getCourseByNumber={getCourseByNumber}></MissingCourses>
                    <SummaryButton planReady={planReady} courses={collectChosenCourses()} studyPlanName={studyPlanName} studyPlanId={routeParams.studyPlanId}></SummaryButton>
                </div>
            </div>
        </div>
    )
}

export default PlanPage