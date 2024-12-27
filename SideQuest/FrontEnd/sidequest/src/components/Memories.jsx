import axios from "axios";
import React, { useEffect, useState } from "react";

const Memories = () => {
  const [memories, setMemories] = useState([]);

  useEffect(() => {
    const fetchMemories = async () => {
      const res = await axios.get("http://localhost:8080/workers");
      setMemories(res.data);
      console.log(res.data);
    };
    fetchMemories();
  }, []);

  return (
    <div className="grid grid-cols-4 gap-3 mt-3">
      {memories.map(({ workerId, firstName, lastName }) => (
        <div key={workerId} className="">
          <img src="mem.jpg" className="w-[400px]" alt="" />
          <div className="flex gap-3">
            <p>
              {firstName} {lastName}
            </p>
          </div>
        </div>
      ))}
    </div>
  );
};

export default Memories;
