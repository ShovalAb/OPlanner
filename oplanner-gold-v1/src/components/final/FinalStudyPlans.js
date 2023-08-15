import React from "react";

const FinalStudyPlan = () => {
    const chosenCourses = JSON.parse(localStorage.getItem('chosenCourses'));
    return(
        <div>
            Final Study Plan
           <p>{chosenCourses.toString()}</p> 
        </div>
    )
}

export default FinalStudyPlan;