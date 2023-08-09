import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import api from '../../api/axiosConfig';
import CoursesDrag from '../couresesdrag/CoursesDrag'
import MissingCourses from "../missingcourses/MissingCourses";

const PlanPage = () => {
    const routeParams = useParams();
    const [courses, setCourses] = useState();
    const [coursesMust, setCoursesMust] = useState();
    const [coursesDepen, setCoursesDepen] = useState();
    const [nakazReq, setNakazReq] = useState();


    const getCourseById = (courseId) => {
        for (let i = 0; i < courses.length; i++) {
            const coursesClass = courses[i];
            for (let j = 0; j < coursesClass.courses.length; j++) {
                const course = coursesClass.courses[j];
                if (course.id == courseId) {
                    return (course)
                }
            }
        }
        return null;
    }

    const setCoursesChosenState = (courses) => {

        for (let i = 0; i < courses.length; i++) {
            var coursesClass = courses[i];
            for (let j = 0; j < coursesClass.courses.length; j++) {
                coursesClass.courses[j].chosen = false;
                // if (coursesClass.courses[j].id == 20406) {
                //     coursesClass.courses[j].chosen = true;
                // }
            }
        }
        return courses
    }

    const getCourses = async (studyPlanId) => {
        try {
            const params = {
                planId : studyPlanId,
            }
            const response = await api.get('/api/studyplan', {params})
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

    const validateCourses = async (studyPlanId, courses) => {
        try {

            var coursesChosen = Array()
            for (let i = 0; i < courses.length; i++) {
                const coursesClass = courses[i];
                for (let j = 0; j < coursesClass.courses.length; j++) {
                    const course = coursesClass.courses[j];
                    if (course.chosen) {
                        coursesChosen.push(course.id)
                    }
                }            
            }

            const response = await api.post('/api/verifyPlan', {'planId':studyPlanId, 'courses':coursesChosen})
            console.log(response.data)
            setCoursesMust(response.data["courses-must"])
            setCoursesDepen(response.data["courses-depen"])
            setNakazReq(response.data["nakaz-req"])
            
            if (response.data.ok) {
                // ok = 1, the program is validated
                console.log("Yay, the program is valid!")
            } else {
                console.log("Ooof, the program is invalid!")
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
            <CoursesDrag courses={courses}></CoursesDrag>
            <button className="buttonValidate" onClick={e => validateCourses(routeParams.studyPlanId,courses)}>Validate Study Plan</button>
            <MissingCourses coursesDepen={coursesDepen} coursesMust={coursesMust} nakazReq={nakazReq} getCourseById={getCourseById}></MissingCourses>
        </div>
    )
}

export default PlanPage