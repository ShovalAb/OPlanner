import React from "react";
import { useParams } from "react-router-dom";
import api from '../../api/axiosConfig'

const PlanPage = () => {
    const routeParams = useParams();
    const getCourses = async () => {
        try {
            const response = await api.get('/api/courseGroup/')
        } catch (error) {
            console.log(error)
        }
    }
    return (
        <div>
            <div>HelloPlanPageeee</div>
            <div>Planning Study Plan of {routeParams.studyPlanId}</div>
        </div>
    )
}

export default PlanPage