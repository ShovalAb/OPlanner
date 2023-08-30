import React from "react";
import CourseTable from '../coursetable/CourseTable'
import backgroundImage from '../../images/finishing.jpeg'
import ProgressBar from "../progressbar/ProgressBar";
import {headlineTextColor ,tableHeaderColor, tableRowEvenColor } from "../colors";

const FinalStudyPlan = () => {
    // Get the elements from the previous page
    const chosenCourses = JSON.parse(localStorage.getItem('chosenCourses'));
    const studyPlanName = JSON.parse(localStorage.getItem('studyPlanName'));
    const studyPlanId = JSON.parse(localStorage.getItem('studyPlanId'));

    const doNothing = (row) => {}

    return (
        <div style={{
        backgroundImage: `url(${backgroundImage})`,
        backgroundSize: 'cover',
        backgroundPosition: 'center',
        backgroundRepeat: 'no-repeat',
        minHeight: '100vh', 
      }}>
            <ProgressBar stepNumber={3} studyPlanId={studyPlanId}></ProgressBar>
            <h1 style={{color:headlineTextColor, fontWeight: 'bold'}}>!התכנית שלך לתואר "{studyPlanName}" מוכנה</h1>
            <div style={{ display: 'flex', justifyContent: 'center', marginTop: '20px' }}>
                <div style={{ maxWidth: '800px', width: '100%', padding: '0 20px' }}>
                        <CourseTable data={chosenCourses} onRowClick={doNothing} activeTab={"nofilter"} downloadable={true} colors={{'header':tableHeaderColor, 'row':tableRowEvenColor}}/>
                </div>
            </div>
        </div>
    )
}

export default FinalStudyPlan;