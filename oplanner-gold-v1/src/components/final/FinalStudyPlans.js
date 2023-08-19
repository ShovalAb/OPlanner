import React from "react";
import CourseTable from '../coursetable/CourseTable'

const FinalStudyPlan = () => {
    const chosenCourses = JSON.parse(localStorage.getItem('chosenCourses'));
    const doNothing = (row) => {}

    return (
        <div>
            <h1>Final Study Plan</h1>
            <div style={{ display: 'flex', justifyContent: 'center', marginTop: '20px' }}>
                <div style={{ maxWidth: '800px', width: '100%', padding: '0 20px' }}>
                    <CourseTable data={chosenCourses} onRowClick={doNothing} downloadable={true} colors={{'header':'#D6FFAB', 'row':'#6AFFA9'}}/>
                </div>
            </div>
        </div>
    )
}

export default FinalStudyPlan;