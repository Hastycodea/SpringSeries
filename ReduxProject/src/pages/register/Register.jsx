import React from "react";
import "./Register.css";
import Navbar from "../../components/navbar/Navbar";

const Register = () => {
  return (
    <div>
      <Navbar />
      <div className="register-container">
        <div className="regiter-wrapper">
          <form action="">
            <h1>Create Your Account</h1>
            <div className="input-box">
              <input type="text" placeholder="username" />
            </div>
            <div className="input-box">
              <input type="password" placeholder="password" />
            </div>
            <div className="input-box">
              <input type="email" placeholder="E-mail" />
            </div>
            <div className="input-box">
              <input type="tel" placeholder="Phone number" />
            </div>
            <div className="input-box">
              <input type="text" placeholder="Country" />
            </div>

            <div className="register-agreement">
              <label>
                <input type="checkbox" /> I agree to the <b>Terms</b> and{" "}
                <b>Privacy Policy</b>
              </label>
            </div>
            <button type="submit">Create Account</button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default Register;
