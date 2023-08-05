import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import api from '../../api/axiosConfig';
import CoursesDrag from '../couresesdrag/CoursesDrag'


const PlanPage = () => {
    const routeParams = useParams();
    const [courses, setCourses] = useState();

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
            const response = await api.get('/api/courseGroup/' + studyPlanId.toString())
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
        </div>
    )
}

export default PlanPage