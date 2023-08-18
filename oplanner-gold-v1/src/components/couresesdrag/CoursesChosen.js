import React from "react";
import ChosenCourse from "./ChosenCourse";
import CourseTable from "../coursetable/CourseTable";

const CoursesChosen = ({courses, refresher, setRefresher, updateCreditReqNum}) => {
    // console.log("My courses - " + Object.getOwnPropertyNames(courses[0]))
    // const createChosenCourse = (course) => {
    //     if (course.chosen) {
    //         return (
    //             <ChosenCourse key={course.courseNumber} course={course} refresher={refresher} setRefresher={setRefresher} updateCreditReqNum={updateCreditReqNum}></ChosenCourse>
    //         )
    //     }
    //     return null;
    // }

    const toggleChosen = (course) => {
        // console.log(event)
        // console.log(id)
        course.chosen = !course.chosen
        setRefresher(refresher + 1)
        updateCreditReqNum()
    }

    // const displayChosenCourses = (coursesClass) => {
    //     return (
    //         <React.Fragment key={coursesClass.creditsType}>
    //             {coursesClass.courses.map(createChosenCourse)}
    //         </React.Fragment>
    //     )
    // }

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
        <CourseTable data={filterChosen(courses)} onRowClick={toggleChosen}/>
    )

    // return (
    //     <div>
    //         <table className="chosenTable">
    //             <tbody>
    //                 {courses.map(displayChosenCourses)}
    //             </tbody>
    //         </table>
    //     </div>
    // )
}

export default CoursesChosen