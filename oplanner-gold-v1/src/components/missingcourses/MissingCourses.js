import React from "react";

const MissingCourses = ({coursesMust, coursesDepen, getCourseById}) => {
    const missingMustCourse = (course) => {
        return (
            <p key={course.id}>{course.name} ({course.id})</p>
        )
    }

    const allMissingMustCourses = (courseMustId) => {
        const course = getCourseById(courseMustId)
        if (course != null) {
            return (missingMustCourse(course))
        }
        return null;
    }

    if (coursesMust && coursesDepen) {
        if (coursesMust.length > 0 || coursesDepen.length > 0) {
            return (
                <div>
                    <h2>Must</h2>
                    <>{coursesMust.map(allMissingMustCourses)}</>
                    <h2>Dependencies</h2>
                    <p>{coursesDepen}</p>
                </div>
            )
        }
    }
    return null;
}

export default MissingCourses