
import './App.css';
import api from './api/axiosConfig';
import {useEffect, useState} from 'react';
import Layout from './components/Layout';
import { Route, Routes } from 'react-router-dom';
import Home from './components/home/Home';
import Header from './components/header/Header';
import PlanPage from './components/planpage/PlanPage';

function App() {
  return (
    <div className="App">
      <Header/>
      <Routes>
        <Route path ="/" element={<Layout/>}>
        <Route path ="/" element={<Home/>}/>
        <Route path ="/plan/:studyPlanId" element={<PlanPage/>}/>
        </Route>
      </Routes>
    </div>
  );
}

export default App;
