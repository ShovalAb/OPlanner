import React from "react";
import { useParams } from "react-router-dom";

const PlanPage = () => {
    const routeParams = useParams();
    return (
        <div>
            <div>HelloPlanPageeee</div>
            <div>Planning Study Plan of {routeParams.studyPlanId}</div>
        </div>
    )
}

export default PlanPage