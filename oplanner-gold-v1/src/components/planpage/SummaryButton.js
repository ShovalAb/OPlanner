import React from "react";
import { Button } from "react-bootstrap";
import { Link } from "react-router-dom";

const SummaryButton = ({planReady, courses, studyPlanName, studyPlanId}) => {

    const saveData = () => {
        localStorage.setItem('chosenCourses', JSON.stringify(courses));
        localStorage.setItem('studyPlanName', JSON.stringify(studyPlanName));
        localStorage.setItem('studyPlanId', JSON.stringify(studyPlanId));
    }

    if (planReady) {
        return (
            <Link to="/finalstudyplan">
                <Button onClick={saveData} 
                style={{
                    background: 'lightgreen', 
                    borderColor: 'black', 
                    color: 'black', 
                    width: '50%', 
                    height: '50%', 
                    fontSize: '30px', 
                    marginTop: '20px'
                    }}>!התוכנית מוכנה <br /> לחץ לסיום</Button>
            </Link>
        )
    }
    return (
        <></>
    )
}

export default SummaryButton