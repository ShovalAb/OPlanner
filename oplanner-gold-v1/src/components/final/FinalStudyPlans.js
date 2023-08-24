import React from "react";
import CourseTable from '../coursetable/CourseTable'
import backgroundImage from '../../images/finishing.jpeg'
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
            <h1>Final Study Plan</h1>
            <div style={{ display: 'flex', justifyContent: 'center', marginTop: '20px' }}>
                <div style={{ maxWidth: '800px', width: '100%', padding: '0 20px' }}>
                        <CourseTable data={chosenCourses} onRowClick={doNothing} activeTab={"nofilter"} downloadable={true} colors={{'header':'#D6FFAB', 'row':'#6AFFA9'}}/>
                </div>
            </div>
        </div>
    )
}

export default FinalStudyPlan;