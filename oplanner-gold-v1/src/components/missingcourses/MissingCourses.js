import React from "react";

const MissingCourses = ({coursesData, coursesMust, coursesDepen}) => {
    const missingMustCourse = (course) => {
        return (
            <p key={course.id}>{course.name} ({course.id})</p>
        )
    }

    const allMissingMustCourses = (courseMustId) => {
        for (let j = 0; j < coursesData.length; j++) {
            const coursesClass = coursesData[j];
            for (let k = 0; k < coursesClass.courses.length; k++) {
                const course = coursesClass.courses[k];
                if (course.id == courseMustId) {
                      return (missingMustCourse(course))
                }
            }
        }
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