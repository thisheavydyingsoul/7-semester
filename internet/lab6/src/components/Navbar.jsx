import React from "react";
import { NavLink } from "react-router-dom";

function Navbar() {
    return (
        <nav style={{ display: "flex", gap: "10px", marginBottom: "20px" }}>
            <NavLink to="/" style={({ isActive }) => ({ fontWeight: isActive ? "bold" : "normal" })}>
                Home
            </NavLink>
            <NavLink to="/about" style={({ isActive }) => ({ fontWeight: isActive ? "bold" : "normal" })}>
                About Us
            </NavLink>
            <NavLink to="/products" style={({ isActive }) => ({ fontWeight: isActive ? "bold" : "normal" })}>
                Products
            </NavLink>
        </nav>
    );
}

export default Navbar;
