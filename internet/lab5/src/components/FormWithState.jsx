import React, { Component } from "react";

class FormWithState extends Component {
    constructor(props) {
        super(props);
        this.state = {
            firstName: "",
            lastName: "",
            email: "",
            phone: "",
            gender: "",
            errors: {},
            submittedData: null,
        };
    }

    validateField = (fieldName, value) => {
        if (!value) {
            return `${fieldName} is required.`;
        }
        return "";
    };

    handleChange = (e) => {
        const { name, value } = e.target;
        this.setState({
            [name]: value,
            errors: { ...this.state.errors, [name]: this.validateField(name, value) },
        });
    };

    handleSubmit = (e) => {
        e.preventDefault();

        const { firstName, lastName, email, phone, gender } = this.state;

        const errors = {};
        if (!firstName) errors.firstName = "First Name is required.";
        if (!lastName) errors.lastName = "Last Name is required.";
        if (!email) errors.email = "Email is required.";
        if (!phone) errors.phone = "Phone is required.";
        if (!gender) errors.gender = "Gender is required.";

        if (Object.keys(errors).length > 0) {
            this.setState({ errors });
            return;
        }

        this.setState({
            submittedData: { firstName, lastName, email, phone, gender },
            firstName: "",
            lastName: "",
            email: "",
            phone: "",
            gender: "",
            errors: {},
        });
    };

    render() {
        const {
            firstName,
            lastName,
            email,
            phone,
            gender,
            errors,
            submittedData,
        } = this.state;

        return (
            <div>
                <form onSubmit={this.handleSubmit}>
                    <label>
                        First Name:
                        <input
                            type="text"
                            name="firstName"
                            value={firstName}
                            onChange={this.handleChange}
                        />
                        <div style={{ color: "red" }}>{errors.firstName}</div>
                    </label>
                    <br />
                    <label>
                        Last Name:
                        <input
                            type="text"
                            name="lastName"
                            value={lastName}
                            onChange={this.handleChange}
                        />
                        <div style={{ color: "red" }}>{errors.lastName}</div>
                    </label>
                    <br />
                    <label>
                        Email:
                        <input
                            type="email"
                            name="email"
                            value={email}
                            onChange={this.handleChange}
                        />
                        <div style={{ color: "red" }}>{errors.email}</div>
                    </label>
                    <br />
                    <label>
                        Phone:
                        <input
                            type="tel"
                            name="phone"
                            value={phone}
                            onChange={this.handleChange}
                        />
                        <div style={{ color: "red" }}>{errors.phone}</div>
                    </label>
                    <br />
                    <label>
                        Gender:
                        <select name="gender" value={gender} onChange={this.handleChange}>
                            <option value="">Select</option>
                            <option value="male">Male</option>
                            <option value="female">Female</option>
                        </select>
                        <div style={{ color: "red" }}>{errors.gender}</div>
                    </label>
                    <br />
                    <button type="submit">Submit</button>
                </form>

                {/* Отображение отправленных данных */}
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
}

export default FormWithState;
