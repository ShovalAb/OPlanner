import React from "react";

const MissingCourses = ({coursesMust, coursesDepen, nakazReq, getCourseById}) => {
    const missingMustCourse = (course) => {
        return (
            <p key={course.id}><b>{course.name} ({course.id})</b></p>
        )
    }

    const allMissingMustCourses = (courseMustId) => {
        const course = getCourseById(courseMustId)
        if (course != null) {
            return (missingMustCourse(course))
        }
        return null;
    }

    const missingDepenCourse = (preCourseMissing, course) => {
        return (
            <p key={course.id}><b>{course.name}({course.id})</b> needs the course <b>{preCourseMissing.name}({preCourseMissing.id})</b>!</p>
        )
    }

    const allMissingDepenCourses = (courseIds) => {
        const preCourse = getCourseById(courseIds[0])
        const course = getCourseById(courseIds[1])

        if (course != null && preCourse != null) {
            return (missingDepenCourse(preCourse, course))
        }
        return null;
    }

    const allMissingNakazReq = (nakazReqIssue) => {
        return (
            <div key={nakazReqIssue["groupName"]}>
            <p><b>{nakazReqIssue["Razui"]}</b> nakaz of <b>{nakazReqIssue["groupName"]}</b> are needed.</p>
            <p>In your plan there are only <b>{nakazReqIssue["Mazui"]}</b></p>
            </div>
        )
    }

    console.log("YYYY" + coursesMust + coursesDepen)
    if (coursesMust && coursesDepen) {
        if (coursesMust.length > 0 || coursesDepen.length > 0) {
            return (
                <div>
                    <h2>Must</h2>
                    <>{coursesMust.map(allMissingMustCourses)}</>
                    <h2>Dependencies</h2>
                    <>{coursesDepen.map(allMissingDepenCourses)}</>
                    <h2>Nakaz Requirements</h2>
                    <>{nakazReq.map(allMissingNakazReq)}</>
                </div>
            )
        }
        return (
            <div>
                <h2 className="validateOK">All good! your plan match all of the criteria!</h2>
            </div>
        )
    }
    return null;
}

export default MissingCourses