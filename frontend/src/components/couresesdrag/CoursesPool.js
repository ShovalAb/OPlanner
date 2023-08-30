import React from "react";
import { useState } from "react";
import CourseTable from "../coursetable/CourseTable";
import TabsSidebar from "../coursetable/TabsSideBar";
import { tableBGColor, tableHeaderColor, tableRowEvenColor, tableRowOddColor } from "../colors";

const CoursesPool = ({courses, refresher, setRefresher, updateCreditReqNum}) => {
    const allTab = {label: 'הכל', value: 'nofilter'}
    const [activeTab, setActiveTab] = useState(allTab.value);

    // Create all tabs
    const tabs = Array ()
    tabs.push(allTab)
    for (let i = 0; i < courses.length; i++) {
        tabs.push({label: courses[i].creditsType, value: courses[i].creditsType});
    }

    const handleTabClick = (tabValue) => {
        setActiveTab(tabValue);
    };

    // Toggle the chosen property of a course 
    const toggleChosen = (course) => {
        course.chosen = !course.chosen
        setRefresher(refresher + 1)
        updateCreditReqNum()
    }

    // Collect only the NOT chosen courses
    const filterNotChosen = (coursesList) => {
        var chosenCourses = Array()
        for (let i = 0; i < coursesList.length; i++) {
            for (let j = 0; j < coursesList[i].courses.length; j++) {
                if(!coursesList[i].courses[j].chosen) {
                    chosenCourses.push(coursesList[i].courses[j])
                }
            }
        }
        return chosenCourses
    }

    return (
    <div style={{ display: 'flex' }}>
      <CourseTable data={filterNotChosen(courses)} onRowClick={toggleChosen} activeTab={activeTab} downloadable={false} colors={{'header':tableHeaderColor, 'row':tableRowEvenColor}} numberOfTabs={tabs.length}/>
      <div style={{width:'25000px', backgroundColor: tableHeaderColor}}></div>
      <div style={{ display: 'flex', flexDirection: 'column', marginTop: '70px' }}>
        {tabs.map((tab, index) => (
          <TabsSidebar
            key={index}
            tabs={[tab]}
            activeTab={activeTab}
            onTabClick={handleTabClick}
          />
        ))}
      </div>
    </div>
  );
}

export default CoursesPool