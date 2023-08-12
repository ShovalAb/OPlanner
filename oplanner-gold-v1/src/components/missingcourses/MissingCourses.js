import React from "react";

const MissingCourses = ({coursesMust, coursesDepen, nakazReq, getCourseByNumber}) => {
    const missingMustCourse = (course) => {
        return (
            <p key={course.courseNumber}><b>{course.courseName} ({course.courseNumber})</b></p>
        )
    }

    const allMissingMustCourses = (courseMustNumber) => {
        // console.log("Course Must number - " + courseMustNumber)
        const course = getCourseByNumber(courseMustNumber)
        if (course != null) {
            return (missingMustCourse(course))
        }
        return null;
    }

    const preCoursesdetails = (preCourses) => {
        const coursesDetails = Array()
        for (let i = 0; i < preCourses.length; i++) {
            coursesDetails.push(preCourses[i].courseName + "(" + preCourses[i].courseNumber + ")")
        }
        return coursesDetails.join(", ")
    }    

    const missingDepenCourses = (courseDep, preCourses) => {
        return (
            <p key={courseDep.courseNumber}><b>{courseDep.courseName}({courseDep.courseNumber})</b> needs the courses <b>{preCoursesdetails(preCourses)}</b>!</p>
        )
    }

    const allMissingDepenCourses = (depObj) => {
        const courseDepNum = depObj.course
        const courseNums = depObj.dep

        const courseDep = getCourseByNumber(courseDepNum)

        const preCourses = Array()
        for (let i = 0; i < courseNums.length; i++) {
            const preCourse = getCourseByNumber(courseNums[i]);
            if (preCourse != null) {
                preCourses.push(preCourse)
            }
            
        }

        if (courseDep != null && preCourses.length != 0) {
            return (missingDepenCourses(courseDep, preCourses))
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