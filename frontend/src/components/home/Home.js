import React, {useState, useEffect} from "react";
import StudyPlans from "../studyplans/StudyPlans";
import api from '../../api/axiosConfig';
import SubmitButton from "../studyplans/SubmitButton";
import backgroundImage from '../../images/firstBG.png'
import {tabSidebarBGInactiveColor, tabSidebarTextColor} from "../colors";
import ProgressBar from "../progressbar/ProgressBar";

const Home = () => {

    const [studyPlans, setStudyPlans] = useState();
    const [selectedStudyPlan, setSelectedStudyPlan] = useState();

    const getStudyPlans = async () => {
        try {
            const response = await api.get('/api/studyplan')
            setStudyPlans(response.data)
        } catch (error) {
            console.log(error)
        }
    }


    const getInstructions = () => {
        
        const instructionsStyle = {
            backgroundColor: "rgba(255, 255, 255, 0.8)",
            color: "black",
            padding: "10px",
            border: "1px solid #ccc",
            borderRadius: "5px",
            margin: "10px auto", 
            width: "50%", 
            direction: "rtl", // Set the direction to right-to-left
            textAlign: "right", // Align the text from right to left
        };
    
        return (
            <div>
            <div style={instructionsStyle}>
                <p>הוראות שימוש במערכת:</p>
                <p>בתהליך יש 3 שלבים:</p>
                <ol>
                    <li>בחירת תוכנית לימודים - יש לבחור מתוך תוכניות המוצעות איזו תוכנית לימודים תרצה לתכנן.</li>
                    <li>
                        תכנון תוכנית הלימודים -
                        <ol>
                            <li>יש ללחוץ בטבלה הימינית על כל קורס שתרצה להוסיף לתוכנית הלימודים שלך. במידה ויש קורס שתרצה לבטל את בחירתו ניתן ללחוץ עליו בטבלה השמאלית והוא יוסר מהתוכנית.</li>
                            <li>בטבלה המוצגת בתחתית העמוד ניתן לראות על אילו מדרישות הנקז הקורסים עונים (דרישה אשר מסומנת בירוק) ועל אילו טרם (דרישה אשר מסומנת באדום).</li>
                            <li>לאחר לחיצה על כפתור "בדיקת תוכנית לימודים" ניתן לראות בתחתית העמוד בצד ימין מהם קורסי החובה החסרים ואילו תלויות חסרות לקורסים ספציפיים.</li>
                            <li>בסיום התכנון כאשר התוכנית עומדת בכל הקריטריונים יופיע כפתור "סיום תכנון תוכנית לימודים". לחיצה על כפתור זה תוביל לעמוד הסיום.</li>
                        </ol>
                    </li>
                    <li>בעמוד הסיום ניתן לראות את כל הקורסים המרכיבים את תוכנית הלימודים התקינה וניתן להוציא אותה לאקסל.</li>
                </ol>
            </div>
            </div>
        );
    }

    useEffect(() => {
        getStudyPlans();
    },[])
    
    return (
        <div>
            <div style={{
                backgroundImage: `url(${backgroundImage})`,
                backgroundSize: 'cover',
                backgroundPosition: 'center',
                backgroundRepeat: 'no-repeat',
                minHeight: '100vh', 
                display: 'flex',
                flexDirection: 'column',
                alignItems: 'center',
            }}>
                <div>
                    <ProgressBar stepNumber={1}></ProgressBar>
                    <h1 style={{ fontWeight: 'bold' }}>ברוכים הבאים למערכת לתכנון תוכנית לימודים</h1>
                    {getInstructions()} 
                    <StudyPlans studyPlans={studyPlans} setSelectedStudyPlan={setSelectedStudyPlan}></StudyPlans>
                    <div style={{ marginTop: '20px' }}>
                        <SubmitButton selectedStudyPlan={selectedStudyPlan}></SubmitButton>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Home;