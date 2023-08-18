import createPalette from "@mui/material/styles/createPalette";
import React, { useState } from "react";
import CoursesClass from "./CoursesClass";
import CoursesChosen from "./CoursesChosen";

const CoursesDrag = ({courses, updateCreditReqNum}) => {
    const [refresher, setRefresher] = useState(0);

    const refresh = (n) => {
        setRefresher(n);
    }

    const createCoursesClass = (coursesClass) => {
        return (
            <tr key={coursesClass.creditsType}>
                <td>
                    <CoursesClass coursesClass={coursesClass} refresher={refresher} setRefresher={refresh}></CoursesClass>
                </td>
            </tr>
        )
    }

    if (courses != null) {
        return (
            <div style={{ display: 'flex', justifyContent: 'center', marginTop: '20px' }}>
                 <div style={{ maxWidth: '800px', width: '50%', padding: '0 20px' }}>
                    <table>
                        <tbody>
                            {courses.map(createCoursesClass)}
                        </tbody>
                    </table>
                </div>
                <div style={{ maxWidth: '800px', width: '50%', padding: '0 20px' }}>
                      <CoursesChosen courses={courses} refresher={refresher} setRefresher={refresh} updateCreditReqNum={updateCreditReqNum}></CoursesChosen>
                </div>
            </div>
        )
    }
}

export default CoursesDrag