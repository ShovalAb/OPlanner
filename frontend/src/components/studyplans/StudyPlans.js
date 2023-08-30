import React from "react";

const StudyPlans = ({ studyPlans, setSelectedStudyPlan }) => {
    const selectStyle = {
        fontSize: "20px", 
        padding: "10px",
    };

    const Option = (opt) => {
        return <option key={opt.id} value={opt.id}>{opt.planName}</option>;
    };

    const handleChange = (event) => {
        setSelectedStudyPlan(event.target.value);
    };

    if (studyPlans !== undefined) {
        return (
            <select style={selectStyle} onChange={handleChange} dir="rtl">
                <option value="-1"> בחר תוכנית</option>
                {studyPlans.map(Option)}
            </select>
        );
    }
    return null;
};

export default StudyPlans;