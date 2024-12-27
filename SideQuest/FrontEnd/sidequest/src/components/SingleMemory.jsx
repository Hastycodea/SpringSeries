import axios from "axios";
import React, { useEffect, useState } from "react";
import { useParams } from "react-router";

const SingleMemory = () => {
  const [  memory, setMemory  ] = useState([]);
  const { id } = useParams();

  useEffect(() => {
    const fetchMemory = async () => {
      const res = await axios.get(`http://localhost:8080/workers/${id}`);
      console.log(res);
      setMemory(res.data);
    };
    fetchMemory();
  }, [id]);

  return (
    <div className="mt-3 flex flex-col items-center justify-center">
      <img src="/mem.jpg" className="w-[400px]" alt="" />
        <div key={memory.memoryId}>
          <p>
            {memory.firstName} {memory.lastName}
          </p>
        </div>
    </div>
  );
};

export default SingleMemory;
