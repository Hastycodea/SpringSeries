import React from "react";
import Navbar from "../components/Navbar";
import Memories from "../components/Memories";
import { BrowserRouter as Router, Routes, Route } from "react-router";
import SingleMemory from "../components/SingleMemory";
import AddMemory from "../components/AddMemory";

const Home = () => {
  return (
    <div>
      <Router>
        <Navbar />
        <Routes>
          <Route path="/" element={<Memories />} />
          <Route path="/memory/:id" element={<SingleMemory />} />
          <Route path="/add_memory" element={<AddMemory />} />
        </Routes>
      </Router>
    </div>
  );
};

export default Home;
