import React, {useState, useEffect} from "react";
import StudyPlans from "../studyplans/StudyPlans";
import api from '../../api/axiosConfig';


const Home = () => {

    const [studyPlans, setStudyPlans] = useState();
    
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
            <StudyPlans studyPlans={studyPlans}></StudyPlans>
        </div>
    )
}

export default Home;