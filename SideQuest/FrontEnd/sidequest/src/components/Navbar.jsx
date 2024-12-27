import React from "react";
import { Link } from "react-router-dom";

const Navbar = () => {
  return (
    <div>
      <div className="text-white flex justify-center items-center mx-auto gap-3 p-4 bg-black w-[50%] rounded-md">
        <Link to="/">
          <h4>Side Quest</h4>
        </Link>
      </div>
    </div>
  );
};

export default Navbar;
