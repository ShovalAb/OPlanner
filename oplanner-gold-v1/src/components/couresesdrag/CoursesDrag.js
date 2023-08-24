import React, { useState } from "react";
import CoursesChosen from "./CoursesChosen";
import CoursesPool from "./CoursesPool";

const CoursesDrag = ({courses, updateCreditReqNum}) => {
    const [refresher, setRefresher] = useState(0);

    const refresh = (n) => {
        setRefresher(n);
    }

    if (courses != null) {
        return (
            <div style={{ display: 'flex', justifyContent: 'center', marginTop: '20px' }}>
                 <div style={{ maxWidth: '800px', width: '50%', padding: '0 20px' }}>
                      <CoursesChosen courses={courses} refresher={refresher} setRefresher={refresh} updateCreditReqNum={updateCreditReqNum}></CoursesChosen>
                </div>
                <div style={{ maxWidth: '800px', width: '50%', padding: '0 20px' }}>
                    <CoursesPool courses={courses} refresher={refresher} setRefresher={refresh} updateCreditReqNum={updateCreditReqNum}></CoursesPool>
                </div>
            </div>
        )
    }
}

export default CoursesDrag