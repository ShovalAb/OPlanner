import React from "react";
import { Button } from "react-bootstrap";

const SummaryButton = ({planReady}) => {
    if (planReady) {
        return (
            <Button>PLAN READY!!</Button>
        )
    }
    return (
        <></>
    )
}

export default SummaryButton