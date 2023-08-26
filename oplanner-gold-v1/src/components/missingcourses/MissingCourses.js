import React from "react";
import { headlineTextColor } from "../colors"; 

const MissingCourses = ({coursesMust, coursesDepen, getCourseByNumber}) => {
    const missingMustCourse = (course) => {
        return (
            <li key={course.courseNumber}><b>{course.courseName} ({course.courseNumber})</b></li>
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
            coursesDetails.push(preCourses[i].courseName + " (" + preCourses[i].courseNumber + ")")
        }
        return coursesDetails.join(", ")
    }    

    const missingDepenCourses = (courseDep, preCourses) => {
        var plural = true
        if (preCourses.length == 1) {
            plural = false
        }
        return (
            <li key={courseDep.courseNumber}><b style={{color: 'green'}}>{courseDep.courseName} ({courseDep.courseNumber})</b>
            {!plural && (" תלוי בקורס: ")}
            {plural && (" תלוי בקורסים: ")}
             <b>{preCoursesdetails(preCourses)}</b>
             </li>
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

    if (coursesMust && coursesDepen) {
        if (coursesMust.length > 0 || coursesDepen.length > 0 ) {
            return (
                <div style={{direction: 'rtl', textAlign: 'right'}}>
                    <h2 style={{color: headlineTextColor}}>קורסי חובה חסרים</h2>
                    <div>
                        <ul>
                            <>{coursesMust.map(allMissingMustCourses)}</>
                        </ul>
                    </div>
                    <h2 style={{color: headlineTextColor}}>קורסי תלות חסרים</h2>
                    <div>
                        <ul>
                            <>{coursesDepen.map(allMissingDepenCourses)}</>
                        </ul>
                    </div>
                </div>
            )
        }
        return (
            <div style={{textAlign: 'center'}}>
                <h2 className="validateOK">!אין קורסים חסרים</h2>
            </div>
        )
    }
    return null;
}

export default MissingCourses