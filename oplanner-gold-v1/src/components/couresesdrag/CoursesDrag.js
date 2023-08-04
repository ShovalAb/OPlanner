import createPalette from "@mui/material/styles/createPalette";
import React, { useState } from "react";
import CoursesClass from "./CoursesClass";
import CoursesChosen from "./CoursesChosen";

const CoursesDrag = ({courses}) => {
    const [refresher, setRefresher] = useState(0);

    const refresh = (n) => {
        setRefresher(n);
    }

    const createCoursesClass = (coursesClass) => {
        return (
            <tr key={coursesClass.groupName}>
                <td>
                    <CoursesClass coursesClass={coursesClass} refresher={refresher} setRefresher={refresh}></CoursesClass>
                </td>
            </tr>
        )
    }

    if (courses != null) {
        return (
            <div className="plan-page-courses-drag">
                <div>
                    <table>
                        <tbody>
                            {courses.map(createCoursesClass)}
                        </tbody>
                    </table>
                </div>
                <div>
                      <CoursesChosen courses={courses} refresher={refresher} setRefresher={refresh}></CoursesChosen>
                </div>
            </div>
        )
    }
}

export default CoursesDrag