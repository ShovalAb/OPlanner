import { Paper, TableBody, TableCell, TableHead, TableRow } from "@mui/material";
import React from "react";
import { Table } from "react-bootstrap";
import { creditReqBadColor, creditReqGoodColor, creditReqHeaderColor} from "../colors";

const CreditReqTable = ({creditReq}) => {

    // Decide color line based on current credits number
    const whatColor = (cReq) => {
        if (cReq.neededCredits > cReq.currentCredits) {
            return (creditReqBadColor)
        }
        return (creditReqGoodColor)
    }

    if (creditReq != undefined){
        return (
            <Paper style={{direction: 'rtl'}}>
                <Table style={{textAlign: 'right', border: '1px solid black'}}>
                    <TableHead>
                        <TableRow>
                            <TableCell style={{textAlign: 'right', background: creditReqHeaderColor, border: '1px solid black'}}>סוג נק"ז</TableCell>
                            <TableCell style={{textAlign: 'right', background: creditReqHeaderColor, border: '1px solid black'}}>כמות נדרשת</TableCell>
                            <TableCell style={{textAlign: 'right', background: creditReqHeaderColor, border: '1px solid black'}}>כמות בתוכנית הנוכחית</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {creditReq.map((row, index) => (
                            <TableRow key={index}>
                                <TableCell style={{textAlign: 'right', background: whatColor(row), border: '1px solid black'}}>{row.creditsType}</TableCell>
                                <TableCell style={{textAlign: 'right', background: whatColor(row), border: '1px solid black'}}>{row.neededCredits}</TableCell>
                                <TableCell style={{textAlign: 'right', background: whatColor(row), border: '1px solid black'}}>{row.currentCredits}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </Paper>
        )
    }
    return null;
}

export default CreditReqTable