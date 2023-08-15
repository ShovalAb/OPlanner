import React, {useState, useEffect} from "react";
import StudyPlans from "../studyplans/StudyPlans";
import api from '../../api/axiosConfig';
import SubmitButton from "../studyplans/SubmitButton";

const Home = () => {

    const [studyPlans, setStudyPlans] = useState();
    const [selectedStudyPlan, setSelectedStudyPlan] = useState();

    const getStudyPlans = async () => {
        try {
            const response = await api.get('/api/studyplan')
            console.log('GOTT - ' + response.data)
            setStudyPlans(response.data)
        } catch (error) {
            console.log(error)
        }
    }

    useEffect(() => {
        getStudyPlans();
    },[])

    return(
        <div>
            <StudyPlans studyPlans={studyPlans} setSelectedStudyPlan={setSelectedStudyPlan}></StudyPlans>
            <SubmitButton selectedStudyPlan={selectedStudyPlan}></SubmitButton>
        </div>
    )
}

export default Home;