import React from "react";
import CourseTable from "../coursetable/CourseTable";
import { tableHeaderColor, tableRowEvenColor } from "../colors";

const CoursesChosen = ({courses, refresher, setRefresher, updateCreditReqNum}) => {

    // Create the tabs array
    const tabs = Array ()
    for (let i = 0; i < courses.length; i++) {
        tabs.push({label: courses[i].creditsType, value: courses[i].creditsType});
    }

    // Toggle the chosen property of a course 
    const toggleChosen = (course) => {
        course.chosen = !course.chosen
        setRefresher(refresher + 1)
        updateCreditReqNum()
    }

    // Collect only the chosen courses
    const filterChosen = (coursesList) => {
        var chosenCourses = Array()
        for (let i = 0; i < coursesList.length; i++) {
            for (let j = 0; j < coursesList[i].courses.length; j++) {
                
                if(coursesList[i].courses[j].chosen) {
                    chosenCourses.push(coursesList[i].courses[j])
                }
            }
        }
        return chosenCourses
    }

    return (
    <div style={{ display: 'flex' }}>
      <CourseTable data={filterChosen(courses)} onRowClick={toggleChosen} activeTab={'nofilter'} downloadable={false} colors={{'header':tableHeaderColor, 'row':tableRowEvenColor}} numberOfTabs={tabs.length + 1}/>
    </div>
  );
}

export default CoursesChosen