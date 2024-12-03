import React from "react";
import { NavLink, useParams } from "react-router-dom";

const data = {
    cats: [
        { id: 1, name: "Cat Food", description: "Premium food for your cats" },
        { id: 2, name: "Cat Toys", description: "Toys your cats will love" },
    ],
    dogs: [
        { id: 1, name: "Dog Food", description: "Nutritious food for dogs" },
        { id: 2, name: "Dog Beds", description: "Comfortable beds for dogs" },
    ],
};

function Category() {
    const { category } = useParams();
    const items = data[category] || [];

    return (
        <div>
            <h2>{category.charAt(0).toUpperCase() + category.slice(1)} Products</h2>
            <ul>
                {items.map((item) => (
                    <li key={item.id}>
                        <NavLink to={`${item.id}`}>{item.name}</NavLink>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default Category;
