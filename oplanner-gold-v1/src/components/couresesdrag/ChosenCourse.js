import React from "react";

const ChosenCourse = ({course, refresher, setRefresher}) => {
    // console.log(10)
    const toggleChosen = (event, id) => {
        // console.log(event)
        // console.log(id)
        course.chosen = !course.chosen
        setRefresher(refresher + 1)
    }

    return (
        <tr onClick={e => toggleChosen(e, course.id)}>
            <td>
                <p>{course.name}</p>
            </td>
        </tr>
    )
}

export default ChosenCourse