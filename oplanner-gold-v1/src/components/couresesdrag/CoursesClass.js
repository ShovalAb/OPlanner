import React, { useState } from "react";

const CoursesClass = ({coursesClass}) => {
    const [open, setOpen] = useState();

    const toggleFunc = () => {
        setOpen(!open)
    }

    const listCourses = (course) => {
        return (
            <tr>
                <td>
                    <p>{course.name}</p>
                </td>
            </tr>
        )
    }

    return (
        <div>
            <button onClick={toggleFunc}>{coursesClass.groupName}</button>
            {open && (<div className="toggle">{coursesClass.courses.map(listCourses)}</div>)}
        </div>
    )
}

export default CoursesClass