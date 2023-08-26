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

    useEffect(() => {
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
            {/* <div style={{marginTop: '20px'}}> */}
               <ProgressBar stepNumber={2}></ProgressBar>
            {/* </div> */}
            <div>
                <h1 style={{color: headlineTextColor}}>Planning Study Plan #{routeParams.studyPlanId}</h1>
            </div>
            <CoursesDrag courses={courses} updateCreditReqNum={updateCreditReqNum}></CoursesDrag>
            <button className="buttonValidate" onClick={e => validateCourses(routeParams.studyPlanId,courses)} style={{marginTop:'50px'}}>בדיקת תוכנית לימודים</button>
            <div style={{ display: 'flex', justifyContent: 'center', marginTop: '20px' }}>
                <div style={{ maxWidth: '800px', width: '45%', padding: '0 20px' , marginRight: '5%'}}>
                    <CreditReqTable creditReq={nakazReq}></CreditReqTable>
                </div>
                <div style={{ maxWidth: '800px', width: '45%', padding: '0 20px' , marginLeft: '5%'}}>
                    <MissingCourses coursesDepen={coursesDepen} coursesMust={coursesMust} getCourseByNumber={getCourseByNumber}></MissingCourses>
                    <SummaryButton planReady={planReady} courses={collectChosenCourses()}></SummaryButton>
                </div>
            </div>
        </div>
    )
}

export default PlanPage