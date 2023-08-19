import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import api from '../../api/axiosConfig';
import CoursesDrag from '../couresesdrag/CoursesDrag'
import MissingCourses from "../missingcourses/MissingCourses";
import SummaryButton from "./SummaryButton";
import CreditReqTable from "./CreditReqTable";

const PlanPage = () => {
    console.log("D")
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
            const response = await api.post('/api/creditsReq', {'planId':routeParams.studyPlanId, 'courses':coursesChosen})
            console.log(response.data)
            setNakazReq(response.data["creditsReqResponse"])
            
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
            
            if (response.data.ok) {
                // ok = 1, the program is validated
                console.log("Yay, the program is valid!")
                setPlanReady(true)
            } else {
                console.log("Ooof, the program is invalid!")
                setPlanReady(false)
            }

        } catch (error) {
            console.log(error)
        }
    }

    return (
        <div>
            <div>
                <h1>Planning Study Plan #{routeParams.studyPlanId}</h1>
            </div>
            <CoursesDrag courses={courses} updateCreditReqNum={updateCreditReqNum}></CoursesDrag>
            <button className="buttonValidate" onClick={e => validateCourses(routeParams.studyPlanId,courses)}>Validate Study Plan</button>
            <CreditReqTable creditReq={nakazReq}></CreditReqTable>
            <MissingCourses coursesDepen={coursesDepen} coursesMust={coursesMust} nakazReq={nakazReq} getCourseByNumber={getCourseByNumber}></MissingCourses>
            <SummaryButton planReady={planReady} courses={collectChosenCourses()}></SummaryButton>
        </div>
    )
}

export default PlanPage