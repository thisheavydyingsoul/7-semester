import React from "react";
import { useParams } from "react-router-dom";

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

function ProductDetails() {
    const { category, id } = useParams();
    const product = (data[category] || []).find((item) => item.id === parseInt(id));

    if (!product) {
        return <h2>Product not found</h2>;
    }

    return (
        <div>
            <h1>{product.name}</h1>
            <p>{product.description}</p>
        </div>
    );
}

export default ProductDetails;
