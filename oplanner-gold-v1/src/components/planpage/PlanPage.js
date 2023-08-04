import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import api from '../../api/axiosConfig';

const PlanPage = () => {
    const routeParams = useParams();
    const [courses, setCourses] = useState();
    const getCourses = async (studyPlanId) => {
        try {
            const response = await api.get('/api/courseGroup/' + studyPlanId.toString())
            console.log(response.data)
            setCourses(response.data)
        } catch (error) {
            console.log(error)
        }
    }
    useEffect(() => {
        getCourses(routeParams.studyPlanId);
    },[])
    return (
        <div>
            <div>HelloPlanPageeee</div>
            <div>Planning Study Plan of {routeParams.studyPlanId}</div>
        </div>
    )
}

export default PlanPage