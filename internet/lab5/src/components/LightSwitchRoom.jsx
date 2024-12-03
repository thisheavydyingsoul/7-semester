import React, { useState } from "react";

function LightSwitchRoom() {
    const [isLightOn, setIsLightOn] = useState(true);

    const toggleLight = () => {
        setIsLightOn(!isLightOn);
    };

    return (
        <div
            style={{
                height: "100vh",
                display: "flex",
                flexDirection: "column",
                justifyContent: "center",
                alignItems: "center",
                backgroundColor: isLightOn ? "white" : "black",
                color: isLightOn ? "black" : "white",
                transition: "background-color 0.5s, color 0.5s",
            }}
        >
            <p>The room is {isLightOn ? "bright" : "dark"}.</p>
            <button
                onClick={toggleLight}
                style={{
                    padding: "10px 20px",
                    fontSize: "16px",
                    cursor: "pointer",
                    backgroundColor: isLightOn ? "lightgray" : "gray",
                    color: isLightOn ? "black" : "white",
                    border: "none",
                    borderRadius: "5px",
                    transition: "background-color 0.5s, color 0.5s",
                }}
            >
                Toggle Light
            </button>
        </div>
    );
}

export default LightSwitchRoom;
