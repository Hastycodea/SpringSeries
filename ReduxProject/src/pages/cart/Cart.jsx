import React from "react";
import "./Cart.css";
import { useDispatch, useSelector } from "react-redux";
import Navbar from "../../components/navbar/Navbar";
import { BiShoppingBag } from "react-icons/bi";

const Cart = () => {
  const cartItems = useSelector((state) => state.cart.cartItems);

  const dispatch = useDispatch();

  const totalPrice = cartItems.reduce(
    (acc, item) => acc + item.price * item.quantity,
    0
  );

  return (
    <div>
      <Navbar />

      <div className="cart-container">
        <div className="cart-title-container">
          <BiShoppingBag className="icon" />
          <h2 className="cart-title">Shopping Cart</h2>
        </div>

        {cartItems.length === 0 ? (
          <p>Your Cart is Empty</p>
        ): (
          <>
          <div className="cart-content">\
            <div className="cart-items-section">
              <div className="cart-header">
                <div className="header-item">Product</div>
                <div className="header-item">Price</div>
                <div className="header-item">Quantity</div>
                <div className="header-item">Total</div>

              </div>
            </div>
          </div>
          </>

        )}
      </div>
    </div>
  );
};

export default Cart;
