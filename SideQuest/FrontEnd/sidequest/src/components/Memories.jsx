import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

const Memories = () => {
  const [memories, setMemories] = useState([]);

  const navigate = useNavigate();

  const handleViewMemory = (id) => {
    navigate(`/memory/${id}`);
  };

  useEffect(() => {
    const fetchMemories = async () => {
      try {
        const res = await axios.get("http://localhost:8080/workers");
        setMemories(res.data);
        console.log(res.data);
      } catch (error) {
        console.log("Error fetching workers", error);
      }
    };
    fetchMemories();
  }, []);

  return (
    <div className="grid grid-cols-4 gap-3 mt-3">
      {memories.map(({ workerId, firstName, lastName }) => (
        <div key={workerId} className="">
          <img
            src={`http://localhost:8080/worker/${workerId}/image`}
            className="w-[300px] h-[200px] object-scale-down shadow-md"
            alt={firstName}
            onClick={() => {
              handleViewMemory(workerId);
            }}
          />
          <div className="flex gap-3">
            <p className="text-center w-[100%]">
              {firstName} {lastName}
            </p>
          </div>
        </div>
      ))}
    </div>
  );
};

export default Memories;
