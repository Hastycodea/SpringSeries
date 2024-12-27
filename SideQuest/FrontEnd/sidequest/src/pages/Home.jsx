import React from "react";
import Navbar from "../components/Navbar";
import Memories from "../components/Memories";
import { BrowserRouter as Router, Routes, Route } from "react-router";
import SingleMemory from "../components/SingleMemory";

const Home = () => {
  return (
    <div>
      <Router>
        <Navbar />
        <Routes>
          <Route path="/" element={<Memories />} />
          <Route path="/memory/:id" element={<SingleMemory />} />
        </Routes>
      </Router>
    </div>
  );
};

export default Home;
