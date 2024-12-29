/* eslint-disable no-undef */
import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router";

const UpdateMemory = () => {
  const { id } = useParams();
  const [worker, setWorker] = useState({});
  const [updateWorker, setUpdateWorker] = useState({});
  const navigate = useNavigate();

  useEffect(() => {
    try {
      const fetchWorker = async () => {
        const res = await axios.get(`http://localhost:8080/workers/${id}`);
        setWorker(res.data);
        setUpdateWorker(res.data);
      };
      fetchWorker();
    } catch (error) {
      console.log("Error updating product", error);
    }
  }, [id]);

  // handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const res = await axios.put(
        `http://localhost:8080/workers/${id}`,
        updateWorker
      );
      console.log(res.data);
      alert("Worker updated successfully");
      navigate("/");
    } catch (error) {
      console.log("Error updating worker", error);
    }
  };

  // handle inputChange
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setUpdateWorker((prevWorker) => ({ ...prevWorker, [name]: value }));
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
          value={updateWorker.firstName}
          onChange={handleInputChange}
          placeholder={worker.firstName}
          className="p-[10px] bg-gray-200 rounded-md outline-none mb-4"
          required
        />
        <label>Last Name</label>
        <input
          type="text"
          name="lastName"
          value={updateWorker.lastName}
          onChange={handleInputChange}
          placeholder={worker.lastName}
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
          Update Worker
        </button>
      </form>
    </div>
  );
};

export default UpdateMemory;
