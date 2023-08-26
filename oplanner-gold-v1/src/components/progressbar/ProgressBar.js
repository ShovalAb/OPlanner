import React from "react";
import { Button } from "react-bootstrap";
import { ProgressBarCurrentColor, ProgressBarDoneColor, ProgressBarNotyetColor } from "../colors";
import { Link } from "react-router-dom";

const ProgressBar = ({stepNumber, studyPlanId}) => {

    var studyPlanUrl = "/"
    if (studyPlanId > 0) {
        studyPlanUrl = "/plan/" + studyPlanId
    }

    const buttonStyle = (buttonNumber) => {
        var buttonColor = ProgressBarNotyetColor;
        if (buttonNumber < stepNumber) {
            buttonColor = ProgressBarDoneColor;
        } else if (buttonNumber == stepNumber) {
            buttonColor = ProgressBarCurrentColor;
        }
        return (
            {
                borderRadius: '100%',
                background: buttonColor,
                borderColor: "white",
                // width: "50%",
                // margin: "0 3%"
            }
        )
    }

    const lineStyle = (startButtonNumber) => {
        var lineColor = ProgressBarNotyetColor;
        if (startButtonNumber < stepNumber) {
            lineColor = ProgressBarDoneColor;
        }
        return (
            {
                width: "100%",
                height: "5px",
                background: lineColor,
                margin: "10px 0"
            }
        )
    }

    return (
        // <div style={{ display: 'flex', justifyContent: 'center', width: '100%', alignItems: "center"}}>
        <div
      style={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        width: "100%",
      }}
    >
      <div
        style={{
          display: "flex",
          width: "50%", // Set the container width to 50%
          justifyContent: "space-between",
          alignItems: "center",
          direction: 'rtl'
        }}
      >
            <Link to={"/"}>
                <Button style={buttonStyle(1)}>1</Button>
            </Link>
            <div style={lineStyle(1)}></div>
            <Link to={studyPlanUrl}>
                <Button style={buttonStyle(2)}>2</Button>
            </Link>
            <div style={lineStyle(2)}></div>
            <Button style={buttonStyle(3)}>3</Button>
        </div>
        </div>
    )
}

export default ProgressBar;