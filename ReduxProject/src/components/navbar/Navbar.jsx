import React from 'react'
import "./Navbar.css"
import { MdOutlineShoppingCart } from 'react-icons/md'
import { IoSearch } from 'react-icons/io5'

const Navbar = () => {
  return (
    <nav className="nav-container">
        <div className="navbar-wrapper">
            <div className="navbar-left">
                <span className="navbar-language">EN</span>
                <div className="navbar-searchContainer">
                    <input type="text" placeholder='Search' className='navbar-input' />
                    {/* <IOSearch className='icon' /> */}
                    <IoSearch className='icon' />
                    

                </div>
            </div>

            <div className="navbar-center">
                <h1 className="navbar-logo">
                    Rise of Coding
                </h1>
            </div>

            <div className="navbar-right">
                <div className="navbar-menuItem">Register</div>
                <div className="navbar-menuItem">Login</div>
                <div className="navbar-menuItem">
                    <MdOutlineShoppingCart className='icon' />
                    <span className="cart-badge"></span>
                </div>
            </div>
        </div>
    </nav>
  )
}

export default Navbar