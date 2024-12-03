import React, { useState } from "react";

function StudentList() {
    const [students, setStudents] = useState([]);
    const [inputValue, setInputValue] = useState("");

    const handleKeyPress = (e) => {
        if (e.key === "Enter" && inputValue.trim()) {
            setStudents([...students, inputValue.trim()]);
            setInputValue("");
        }
    };

    return (
        <div>
            <input
                type="text"
                value={inputValue}
                onChange={(e) => setInputValue(e.target.value)}
                onKeyDown={handleKeyPress}
                placeholder="Enter a student name and press space"
                style={{ width: "400px" }}
            />
            <ul>
                {students.map((student, index) => (
                    <li key={index}>{student}</li>
                ))}
            </ul>
        </div>
    );
}

export default StudentList;
