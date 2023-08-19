import React from "react";
import { useState } from "react";
import ChosenCourse from "./ChosenCourse";
import CourseTable from "../coursetable/CourseTable";
import { blue } from "@mui/material/colors";
import TabsSidebar from "../coursetable/TabsSideBar";

const CoursesChosen = ({courses, refresher, setRefresher, updateCreditReqNum}) => {
    const [activeTab, setActiveTab] = useState('');
    const tabs = Array ()
    for (let i = 0; i < courses.length; i++) {
        tabs.push({label: courses[i].creditsType, value: courses[i].creditsType});
    }

    const handleTabClick = (tabValue) => {
        setActiveTab(tabValue);
    };

    const toggleChosen = (course) => {
        // console.log(event)
        // console.log(id)
        course.chosen = !course.chosen
        setRefresher(refresher + 1)
        updateCreditReqNum()
    }

    const filterChosen = (coursesList) => {
        var chosenCourses = Array()
        for (let i = 0; i < coursesList.length; i++) {
            for (let j = 0; j < coursesList[i].courses.length; j++) {
                
                // console.log("Checking Course " + coursesList[i].courses[j].courseName)
                if(coursesList[i].courses[j].chosen) {
                    // console.log(coursesList[i].courseName + " is Chosen!")
                    chosenCourses.push(coursesList[i].courses[j])
                }
            }
        }
        return chosenCourses
    }

    return (
    <div style={{ display: 'flex' }}>
      <CourseTable data={filterChosen(courses)} onRowClick={toggleChosen} activeTab={'nofilter'} downloadable={false} colors={{'header':'lightblue', 'row':'#ABFFF0'}}/>
      {/* <div style={{ display: 'flex', flexDirection: 'column' }}>
        {tabs.map((tab, index) => (
          <TabsSidebar
            key={index}
            tabs={[tab]}
            activeTab={activeTab}
            onTabClick={handleTabClick}
          />
        ))}
      </div> */}
    </div>
  );
}

export default CoursesChosen