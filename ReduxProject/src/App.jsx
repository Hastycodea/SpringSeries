import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import "./App.css";
import Home from "./pages/home/Home";
import Register from "./pages/register/Register";
import AllProducts from "./pages/allproducts/AllProducts";
import SingleProduct from "./pages/single product/SingleProduct";
import Cart from "./pages/cart/Cart";

function App() {
  return (
    <>
      <Router>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/register" element={<Register />} />
          <Route path="/products" element={<AllProducts />} />
          <Route path="/product/:id" element={<SingleProduct />} />
          <Route path="/cart" element={<Cart />} />
        </Routes>
      </Router>
    </>
  );
}

export default App;
