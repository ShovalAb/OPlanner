import React from "react";

const MissingCourses = ({coursesMust, coursesDepen, nakazReq, getCourseByNumber}) => {
    const missingMustCourse = (course) => {
        return (
            <p key={course.courseNumber}><b>{course.courseName} ({course.courseNumber})</b></p>
        )
    }

    const allMissingMustCourses = (courseMustNumber) => {
        const course = getCourseByNumber(courseMustNumber)
        if (course != null) {
            return (missingMustCourse(course))
        }
        return null;
    }

    const missingDepenCourse = (preCourseMissing, course) => {
        return (
            <p key={course.courseNumber}><b>{course.courseName}({course.courseNumber})</b> needs the course <b>{preCourseMissing.courseName}({preCourseMissing.courseNumber})</b>!</p>
        )
    }

    const allMissingDepenCourses = (courseNums) => {
        const preCourse = getCourseByNumber(courseNums[0])
        const course = getCourseByNumber(courseNums[1])

        if (course != null && preCourse != null) {
            return (missingDepenCourse(preCourse, course))
        }
        return null;
    }

    const allMissingNakazReq = (nakazReqIssue) => {
        return (
            <div key={nakazReqIssue["creditsType"]}>
            <p><b>{nakazReqIssue["neededCredits"]}</b> nakaz of <b>{nakazReqIssue["creditsType"]}</b> are needed.</p>
            <p>In your plan there are only <b>{nakazReqIssue["currentCredits"]}</b></p>
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