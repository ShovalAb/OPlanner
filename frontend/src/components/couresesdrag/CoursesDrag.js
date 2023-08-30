import React, { useState } from "react";
import CoursesChosen from "./CoursesChosen";
import CoursesPool from "./CoursesPool";
import arrowImage from '../../images/arrowsnobg.png'

const CoursesDrag = ({courses, updateCreditReqNum}) => {
    const [refresher, setRefresher] = useState(0);

    const refresh = (n) => {
        setRefresher(n);
    }

    if (courses != null) {
        return (
            <div style={{ display: 'flex', justifyContent: 'center', marginTop: '20px' }}>
                 <div style={{ maxWidth: '800px', width: '45%', padding: '0 20px' }}>
                      <CoursesChosen courses={courses} refresher={refresher} setRefresher={refresh} updateCreditReqNum={updateCreditReqNum}></CoursesChosen>
                </div>
                    <img src={arrowImage} alt="TransferImage" style={{width: '10%', height: '10%', marginTop: '10%'}}></img>
                <div style={{ maxWidth: '800px', width: '45%', padding: '0 20px' }}>
                    <CoursesPool courses={courses} refresher={refresher} setRefresher={refresh} updateCreditReqNum={updateCreditReqNum}></CoursesPool>
                </div>
            </div>
        )
    }
}

export default CoursesDrag