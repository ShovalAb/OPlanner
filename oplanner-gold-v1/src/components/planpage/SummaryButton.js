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
                <Button onClick={saveCourses} style={{background: 'lightgreen', borderColor: 'black', color: 'black', width: '50%', height: '50%', fontSize: '30px', marginTop: '20px'}}>!התוכנית מוכנה <br /> לחץ לסיום</Button>
            </Link>
        )
    }
    return (
        <></>
    )
}

export default SummaryButton