import React from "react";
import ChosenCourse from "./ChosenCourse";
import CourseTable from "../coursetable/CourseTable";

const CoursesChosen = ({courses, refresher, setRefresher, updateCreditReqNum}) => {

    const toggleChosen = (course) => {
        // console.log(event)
        // console.log(id)
        course.chosen = !course.chosen
        setRefresher(refresher + 1)
        updateCreditReqNum()
    }

    const filterChosen = (coursesList) => {
        var chosenCourses = Array()
        for (let i = 0; i < coursesList.length; i++) {
            for (let j = 0; j < coursesList[i].courses.length; j++) {
                
                // console.log("Checking Course " + coursesList[i].courses[j].courseName)
                if(coursesList[i].courses[j].chosen) {
                    // console.log(coursesList[i].courseName + " is Chosen!")
                    chosenCourses.push(coursesList[i].courses[j])
                }
            }
        }
        return chosenCourses
    }

    return (
        <CourseTable data={filterChosen(courses)} onRowClick={toggleChosen} downloadable={false}/>
    )
}

export default CoursesChosen