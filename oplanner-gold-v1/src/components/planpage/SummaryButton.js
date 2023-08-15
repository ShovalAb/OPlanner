import React from "react";
import { Button } from "react-bootstrap";
import { Link } from "react-router-dom";

const SummaryButton = ({planReady, courses}) => {

    const saveCourses = () => {
        localStorage.setItem('chosenCourses', JSON.stringify(courses));
    }

    if (planReady) {
        return (
            <Link to="/finalstudyplan">
                <Button onClick={saveCourses}>PLAN READY!!</Button>
            </Link>
        )
    }
    return (
        <></>
    )
}

export default SummaryButton