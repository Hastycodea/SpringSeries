import axios from "axios";
import React, { useState } from "react";

const AddMemory = () => {
  const [worker, setWorker] = useState({ firstName: "", lastName: "" });

  //handle inputchange for firstName and lastName
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setWorker((prevWorker) => ({ ...prevWorker, [name]: value }));
  };

  //handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const res = await axios.post("http://localhost:8080/workers", worker);
      console.log("Worker added", res.data);
      alert("Worker successfully added");
      setWorker({ firstName: "", lastName: "" }); //Reset form
    } catch (error) {
      console.log("Error adding worker", error);
      alert("Failed to add Worker");
    }
  };

  return (
    <div>
      <form
        onSubmit={handleSubmit}
        className="flex flex-col w-[40%] mx-auto shadow-md mt-5 p-3"
      >
        <label>First Name</label>
        <input
          type="text"
          name="firstName"
          value={worker.firstName}
          onChange={handleInputChange}
          placeholder="Enter first name"
          className="p-[10px] bg-gray-200 rounded-md outline-none mb-4"
          required
        />
        <label>Last Name</label>
        <input
          type="text"
          name="lastName"
          value={worker.lastName}
          onChange={handleInputChange}
          placeholder="Enter last name"
          className="p-[10px] bg-gray-200 rounded-md outline-none mb-4"
          required
        />
        {/* <label>Choose an Image</label>
        <input
          type="file"
          placeholder="Enter last name"
          className="p-[10px] bg-gray-200 rounded-md outline-none mb-4"
        /> */}
        <button type="submit" className="bg-blue-600 text-white p-2 rounded-lg">
          Submit
        </button>
      </form>
    </div>
  );
};

export default AddMemory;
