import React from "react";

const Option = (opt) => {
    return <option key={opt.id} value={opt.id}>{opt.planName}</option>
}

const StudyPlans = ({studyPlans}) => {
    if (studyPlans != undefined) {
        return (
            <select>
                {studyPlans.map(Option)}
            </select>
        )
    }
    return null;
}

export default StudyPlans