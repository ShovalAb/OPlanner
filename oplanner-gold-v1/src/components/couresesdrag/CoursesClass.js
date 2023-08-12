import { render } from "@testing-library/react";
import React, { useState } from "react";

const CoursesClass = ({coursesClass, refresher, setRefresher}) => {
    const [open, setOpen] = useState();
    // const [refresher, setRefresher] = useState(0);

    const toggleFunc = () => {
        setOpen(!open)
    }

    const toggleChosen = (event, courseNumber) => {
        // console.log(event)
        // console.log(id)
        for (let i = 0; i < coursesClass.courses.length; i++) {
            if (coursesClass.courses[i].courseNumber == courseNumber) {
                coursesClass.courses[i].chosen = (!coursesClass.courses[i].chosen)
            }
        }
        setRefresher(refresher + 1)
    }

    const listCourses = (course) => {
        if (!course.chosen) {
            return (
                <tr key={course.courseNumber} onClick={e => toggleChosen(e, course.courseNumber)}>
                    <td className="aCourseClass">
                        <p>{course.courseName} ({course.creditsNumber})</p>
                    </td>
                </tr>
            )
        }
    }

    return (
        <div>
            <button className="toggleButtonClass" onClick={toggleFunc}>{coursesClass.creditType}</button>
            {open && (
                <div className="toggle">
                    <table>
                        <tbody>
                            {coursesClass.courses.map(listCourses)}
                        </tbody>
                    </table>
                </div>
            )}
        </div>
    )
}

export default CoursesClass