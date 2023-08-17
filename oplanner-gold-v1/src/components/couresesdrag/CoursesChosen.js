import React from "react";
import ChosenCourse from "./ChosenCourse";

const CoursesChosen = ({courses, refresher, setRefresher, updateCreditReqNum}) => {

    const createChosenCourse = (course) => {
        if (course.chosen) {
            return (
                <ChosenCourse key={course.courseNumber} course={course} refresher={refresher} setRefresher={setRefresher} updateCreditReqNum={updateCreditReqNum}></ChosenCourse>
            )
        }
        return null;
    }

    const displayChosenCourses = (coursesClass) => {
        return (
            <React.Fragment key={coursesClass.creditsType}>
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