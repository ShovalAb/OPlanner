
import './App.css';
import api from './api/axiosConfig';
import {useEffect, useState} from 'react';
import Layout from './components/Layout';
import { Route, Routes } from 'react-router-dom';
import Home from './components/home/Home';
import Header from './components/header/Header';
import FinalStudyPlan from './components/final/FinalStudyPlans';

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
      <Header/>
      <Routes>
        <Route path ="/" element={<Layout/>}>
        <Route path ="/" element={<Home/>}/>
        <Route path ="/finalstudyplan" element={<FinalStudyPlan/>}/>
        </Route>
      </Routes>
    </div>
  );
}

export default App;
