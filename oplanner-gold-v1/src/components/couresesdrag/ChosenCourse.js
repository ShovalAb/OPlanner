import React from "react";

const ChosenCourse = ({course, refresher, setRefresher, updateCreditReqNum}) => {
    // console.log(10)
    const toggleChosen = (event, courseNumber) => {
        // console.log(event)
        // console.log(id)
        course.chosen = !course.chosen
        setRefresher(refresher + 1)
        updateCreditReqNum()
    }

    return (
        <tr onClick={e => toggleChosen(e, course.courseNumber)}>
            <td>
                <p>{course.courseName}</p>
            </td>
        </tr>
    )
}

export default ChosenCourse