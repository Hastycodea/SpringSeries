import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router";

const SingleMemory = () => {
  const [memory, setMemory] = useState([]);
  const { id } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    const fetchMemory = async () => {
      const res = await axios.get(`http://localhost:8080/workers/${id}`);
      console.log(res);
      setMemory(res.data);
    };
    fetchMemory();
  }, [id]);

  const deleteMemory = async () => {
    try {
      await axios.delete(`http://localhost:8080/workers/${id}`);
      console.log("Item deleted successfully");
      alert("Deleted successfully");
      navigate("/");
    } catch (error) {
      console.log("Error deleting product", error);
    }
  };

  const handleEditClick = () => {
    navigate(`/update_memory/${id}`)
  }

  return (
    <div className="mt-3 flex flex-col items-center justify-center">
      <img src="/mem.jpg" className="w-[400px]" alt="" />
      <div key={memory.memoryId}>
        <p>
          {memory.firstName} {memory.lastName}
        </p>
        <div className="flex gap-5">
          <button
            className="p-3 bg-blue-600 text-white rounded-md"
            type="button"
            onClick={deleteMemory}
          >
            Delete
          </button>
          <button
            className="p-3 bg-blue-600 text-white rounded-md"
            type="button"
            onClick={handleEditClick}
          >
            Update
          </button>
        </div>
      </div>
    </div>
  );
};

export default SingleMemory;
