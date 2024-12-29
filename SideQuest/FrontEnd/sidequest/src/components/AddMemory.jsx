import React from "react";

const AddMemory = () => {
  return (
    <div>
      <form
        action="submit"
        className="flex flex-col w-[40%] mx-auto shadow-md mt-5 p-3"
      >
        <label>First Name</label>
        <input
          type="text"
          placeholder="Enter first name"
          className="p-[10px] bg-gray-200 rounded-md outline-none mb-4"
        />
        <label>Last Name</label>
        <input
          type="text"
          placeholder="Enter last name"
          className="p-[10px] bg-gray-200 rounded-md outline-none mb-4"
        />
        <label>Choose an Image</label>
        <input
          type="file"
          placeholder="Enter last name"
          className="p-[10px] bg-gray-200 rounded-md outline-none mb-4"
        />
        <button type="submit" className="bg-blue-600 text-white p-2 rounded-lg">Submit</button>
      </form>
    </div>
  );
};

export default AddMemory;
