import React from "react";
import {Link} from 'react-router-dom'

const SubmitButton = ({selectedStudyPlan}) => {
    console.log(selectedStudyPlan)
    if (selectedStudyPlan != undefined && selectedStudyPlan != "-1"){
        return (
            <div>
                <Link to={"/plan/" + selectedStudyPlan.toString()}>
                    <button>Start Planning!</button>
                </Link>
            </div>
        )
        }
    return null;
}

export default SubmitButton