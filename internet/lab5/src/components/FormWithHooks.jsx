import React, { useState } from "react";

function FormWithHooks() {
    const [form, setForm] = useState({
        firstName: "",
        lastName: "",
        email: "",
        phone: "",
        gender: "",
    });
    const [errors, setErrors] = useState({});
    const [submittedData, setSubmittedData] = useState(null); // Для сохранения отправленных данных

    const validateField = (name, value) => {
        if (!value) {
            return `${name} is required.`;
        }
        return "";
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setForm({ ...form, [name]: value });
        setErrors({ ...errors, [name]: validateField(name, value) });
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        const newErrors = {};
        Object.keys(form).forEach((key) => {
            if (!form[key]) {
                newErrors[key] = `${key} is required.`;
            }
        });

        if (Object.keys(newErrors).length > 0) {
            setErrors(newErrors);
            return;
        }

        setSubmittedData(form);

        setForm({
            firstName: "",
            lastName: "",
            email: "",
            phone: "",
            gender: "",
        });
        setErrors({});
    };

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <label>
                    First Name:
                    <input
                        type="text"
                        name="firstName"
                        value={form.firstName}
                        onChange={handleChange}
                    />
                    <div style={{ color: "red" }}>{errors.firstName}</div>
                </label>
                <br />
                <label>
                    Last Name:
                    <input
                        type="text"
                        name="lastName"
                        value={form.lastName}
                        onChange={handleChange}
                    />
                    <div style={{ color: "red" }}>{errors.lastName}</div>
                </label>
                <br />
                <label>
                    Email:
                    <input
                        type="email"
                        name="email"
                        value={form.email}
                        onChange={handleChange}
                    />
                    <div style={{ color: "red" }}>{errors.email}</div>
                </label>
                <br />
                <label>
                    Phone:
                    <input
                        type="tel"
                        name="phone"
                        value={form.phone}
                        onChange={handleChange}
                    />
                    <div style={{ color: "red" }}>{errors.phone}</div>
                </label>
                <br />
                <label>
                    Gender:
                    <select
                        name="gender"
                        value={form.gender}
                        onChange={handleChange}
                    >
                        <option value="">Select</option>
                        <option value="male">Male</option>
                        <option value="female">Female</option>
                    </select>
                    <div style={{ color: "red" }}>{errors.gender}</div>
                </label>
                <br />
                <button type="submit">Submit</button>
            </form>

            {submittedData && (
                <div style={{ marginTop: "20px", border: "1px solid #ccc", padding: "10px" }}>
                    <h3>Submitted Data:</h3>
                    <p>
                        <strong>First Name:</strong> {submittedData.firstName}
                    </p>
                    <p>
                        <strong>Last Name:</strong> {submittedData.lastName}
                    </p>
                    <p>
                        <strong>Email:</strong> {submittedData.email}
                    </p>
                    <p>
                        <strong>Phone:</strong> {submittedData.phone}
                    </p>
                    <p>
                        <strong>Gender:</strong> {submittedData.gender}
                    </p>
                </div>
            )}
        </div>
    );
}

export default FormWithHooks;
