import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Navbar from "./components/Navbar";
import Home from "./components/Home";
import About from "./components/About";
import Products from "./components/Products";
import Category from "./components/Category";
import ProductDetails from "./components/ProductDetails";

function App() {
  return (
      <Router>
        <div>
          <Navbar />
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/about" element={<About />} />
            <Route path="/products" element={<Products />}>
              <Route path=":category" element={<Category />} />
              <Route path=":category/:id" element={<ProductDetails />} />
            </Route>
            <Route path="*" element={<h1>Page not found</h1>} />
          </Routes>
        </div>
      </Router>
  );
}

export default App;
