import React from "react";
import ChosenCourse from "./ChosenCourse";
import { create } from "@mui/material/styles/createTransitions";

const CoursesChosen = ({courses, refresher, setRefresher}) => {

    const createChosenCourse = (course) => {
        if (course.chosen) {
            return (
                <ChosenCourse key={course.courseNumber} course={course} refresher={refresher} setRefresher={setRefresher}></ChosenCourse>
            )
        }
        return null;
    }

    const displayChosenCourses = (coursesClass) => {
        return (
            <React.Fragment key={coursesClass.groupName}>
                {coursesClass.courses.map(createChosenCourse)}
            </React.Fragment>
        )
    }

    return (
        <div>
            <table className="chosenTable">
                <tbody>
                    {courses.map(displayChosenCourses)}
                </tbody>
            </table>
        </div>
    )
}

export default CoursesChosen