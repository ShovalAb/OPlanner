import React, { useState } from "react";

const CoursesClass = ({coursesClass}) => {
    const [open, setOpen] = useState();

    const toggleFunc = () => {
        setOpen(!open)
    }

    const listCourses = (course) => {
        return (
            <tr>
                <td className="aCourseClass">
                    <p>{course.name} ({course.nakazCount})</p>
                </td>
            </tr>
        )
    }

    return (
        <div>
            <button className="toggleButtonClass" onClick={toggleFunc}>{coursesClass.groupName}</button>
            {open && (<div className="toggle">{coursesClass.courses.map(listCourses)}</div>)}
        </div>
    )
}

export default CoursesClass