import React from "react";

const CreditReqRow = (creditReq) => {
    return (
        <tr>
            <td>{creditReq.creditType}</td>
            <td>{creditReq.neededCredits}</td>
            <td>{creditReq.currentCredits}</td>
        </tr>
    )
}

const CreditReqTable = ({creditReq}) => {
    if (creditReq != undefined){
        return (
            <table>
                <thead>
                    <tr>
                        <th>Credits Type</th>
                        <th>Credits Needed</th>
                        <th>Credits Current</th>
                    </tr>
                </thead>
                <tbody>
                    {creditReq.map(CreditReqRow)}
                </tbody>
            </table>
        )
    }
    return null;
}

export default CreditReqTable