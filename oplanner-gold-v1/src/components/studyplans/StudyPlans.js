import React from "react";

const StudyPlans = ({studyPlans, setSelectedStudyPlan}) => {
    
    const Option = (opt) => {
        return <option key={opt.id} value={opt.id}>{opt.planName}</option>
    }

    const handleChange = (event) => {
        setSelectedStudyPlan(event.target.value);        
    }

    if (studyPlans != undefined) {
        return (
            <select onChange={handleChange}>
                <option value="-1">אנא בחר תוכנית...</option>
                {studyPlans.map(Option)}
            </select>
        )
    }
    return null;
}

export default StudyPlans