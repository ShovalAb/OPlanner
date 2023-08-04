import createPalette from "@mui/material/styles/createPalette";
import React from "react";
import CoursesClass from "./CoursesClass";


const CoursesDrag = ({courses}) => {
    const createCoursesClass = (coursesClass) => {
        return (
            <tr key={coursesClass.groupName}>
                <td>
                    <CoursesClass coursesClass={coursesClass}></CoursesClass>
                </td>
            </tr>
        )
    }

    if (courses != null) {
        return (
            <div className="plan-page-courses-drag">
                <table>
                    <tbody>
                        {courses.map(createCoursesClass)}
                    </tbody>
                </table>
            </div>
        )
    }
}

export default CoursesDrag