import React from "react";
import CourseTable from '../coursetable/CourseTable'
import backgroundImage from '../../images/finishing.jpeg'
import ProgressBar from "../progressbar/ProgressBar";
import { tableHeaderColor, tableRowEvenColor } from "../colors";

const FinalStudyPlan = () => {
    const chosenCourses = JSON.parse(localStorage.getItem('chosenCourses'));
    const doNothing = (row) => {}

    return (
        <div style={{
        backgroundImage: `url(${backgroundImage})`,
        backgroundSize: 'cover',
        backgroundPosition: 'center',
        backgroundRepeat: 'no-repeat',
        minHeight: '100vh', // Ensure the background covers the entire viewport height
      }}>
            <ProgressBar stepNumber={3}></ProgressBar>
            <h1>Final Study Plan</h1>
            <div style={{ display: 'flex', justifyContent: 'center', marginTop: '20px' }}>
                <div style={{ maxWidth: '800px', width: '100%', padding: '0 20px' }}>
                        <CourseTable data={chosenCourses} onRowClick={doNothing} activeTab={"nofilter"} downloadable={true} colors={{'header':tableHeaderColor, 'row':tableRowEvenColor}}/>
                </div>
            </div>
        </div>
    )
}

export default FinalStudyPlan;