
import './App.css';
import api from './api/axiosConfig';
import {useEffect, useState} from 'react';
import Layout from './components/Layout';
import { Route, Routes } from 'react-router-dom';
import Home from './components/home/Home';
import FinalStudyPlan from './components/final/FinalStudyPlans';
import PlanPage from './components/planpage/PlanPage';

function App() {

  const [studyPlan, setStudyPlan] =useState();

  const getStudyPlan = async() =>{
      try{
          const response = await api.get("/api/studyplan");
          console.log(response.data);

          setStudyPlan(response.data);
      }
      catch(err){
          console.log(err);
      }

  }

  useEffect(() => {
    getStudyPlan();
  }, [])


  return (
    <div className="App">
      <Routes>
        <Route path ="/" element={<Layout/>}>
        <Route path ="/" element={<Home/>}/>
        <Route path ="/finalstudyplan" element={<FinalStudyPlan/>}/>
        <Route path ="/plan/:studyPlanId" element={<PlanPage/>}/>
        </Route>
      </Routes>
    </div>
  );
}

export default App;