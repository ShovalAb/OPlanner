import React from "react";
import { Link } from 'react-router-dom'
import {tabSidebarBGInactiveColor, tabSidebarTextColor} from "../colors";

const SubmitButton = ({ selectedStudyPlan }) => {
    console.log(selectedStudyPlan);
    if (selectedStudyPlan !== undefined && selectedStudyPlan !== "-1") {
        const buttonStyle = {
            direction: "rtl", 
            fontSize: "24px",
            fontWeight: "bold",
            width: "200px",
            height: "50px",
            backgroundColor: tabSidebarBGInactiveColor, 
            color: tabSidebarTextColor, 
            border: "2px solid white",
        };

        return (
            <div>
                <Link to={"/plan/" + selectedStudyPlan.toString()}>
                    <button style={buttonStyle}>התחל לתכנן!</button>
                </Link>
            </div>
        );
    }
    return null;
}

export default SubmitButton;