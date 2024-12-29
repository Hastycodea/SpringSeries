import AddMemory from "./components/AddMemory";
import Navbar from "./components/Navbar";
import SingleMemory from "./components/SingleMemory";
import UpdateMemory from "./components/UpdateMemory";
import Home from "./pages/Home";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

function App() {
  return (
    <>
      <Router>
        <Navbar />
        <Routes>
          <Route path="/" element={<Home />}></Route>
          <Route path="/memory/:id" element={<SingleMemory />} />
          <Route path="/add_memory" element={<AddMemory />} />
          <Route path="/update_memory/:id" element={<UpdateMemory />} />
        </Routes>
      </Router>
    </>
  );
}

export default App;
