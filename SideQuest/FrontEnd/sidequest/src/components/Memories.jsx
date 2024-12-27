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
    <div>
      {memories.map(({workerId, firstName, lastName}) => (
        <div key={workerId} className="flex gap-5">
          <p><span className="font-bold">First Name:</span>{firstName}</p>
          <p><span className="font-bold">Last Name:</span>{lastName}</p>
        </div>
      ))}
    </div>
  );
};

export default Memories;
