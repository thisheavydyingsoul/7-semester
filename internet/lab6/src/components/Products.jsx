import React from "react";
import { Outlet, NavLink } from "react-router-dom";

function Products() {
    return (
        <div>
            <h1>Our Products</h1>
            <nav>
                <NavLink to="cats">Cats</NavLink> | <NavLink to="dogs">Dogs</NavLink>
            </nav>
            <Outlet />
        </div>
    );
}

export default Products;
