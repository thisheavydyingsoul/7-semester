import React, { useState } from "react";
import FormWithState from "./components/FormWithState";
import FormWithHooks from "./components/FormWithHooks";
import StudentList from "./components/StudentList";
import LightSwitchRoom from "./components/LightSwitchRoom";

function App() {
    const [activeTask, setActiveTask] = useState("task1");

    const renderTask = () => {
        switch (activeTask) {
            case "task1":
                return <FormWithState />;
            case "task2":
                return <FormWithHooks />;
            case "task3":
                return <StudentList />;
            case "task4":
                return <LightSwitchRoom />;
            default:
                return <p>Select a task to view.</p>;
        }
    };

    return (
        <div>
            <h1>Lab 5 - React Forms and Hooks</h1>
            <div>
                <button onClick={() => setActiveTask("task1")}>Задание 1</button>
                <button onClick={() => setActiveTask("task2")}>Задание 2</button>
                <button onClick={() => setActiveTask("task3")}>Задание 3</button>
                <button onClick={() => setActiveTask("task4")}>Задание 4</button>
            </div>
            <div style={{ marginTop: "20px" }}>
                {renderTask()}
            </div>
        </div>
    );
}

export default App;
