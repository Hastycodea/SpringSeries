import axios from "axios";
import React, { useEffect, useState } from "react";
import { useParams } from "react-router";

const UpdateMemory = () => {
  const { id } = useParams();
  const [worker, setWorker] = useState({});
  const [image, setImage] = useState();
  const [updateWorker, setUpdateWorker] = useState({
    firstName: "",
    lastName: "",
  });
  // const navigate = useNavigate();

  useEffect(() => {
    const fetchWorker = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/workers/${id}`);
        setWorker(response.data);

        const responseImage = await axios.get(
          `http://localhost:8080/worker/${id}/image`,
          { responseType: "blob" }
        );

        const imageFile = await convertUrlToFile(
          responseImage.data,
          response.data.imageName
        );
        setImage(imageFile);
        setUpdateWorker(response.data);
      } catch (error) {
        console.log("Error updating product", error);
      }
    };
    fetchWorker();
  }, [id]);

  const convertUrlToFile = async (blobData, fileName) => {
    const file = new File([blobData], fileName, { type: blobData.type });
    return file;
  };

  // handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault();
    const formData = new FormData();
    formData.append("imageFile", image);
    formData.append(
      "worker",
      new Blob([JSON.stringify(updateWorker)], { type: "application/json" })
    );

    axios
      .put(`http://localhost:8080/worker/${id}`, formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      })
      .then((res) => {
        console.log("Worker added", res.data);
        alert("Worker successfully updated");
      })
      .catch((error) => {
        //Clearing the input field after submission
        console.log("Error updating worker", error);
        alert("Failed to update Worker");
      });
  };
  // handle Image change
  const handleImageChange = (e) => {
    setImage(e.target.files[0]);
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
        <input
          type="file"
          onChange={handleImageChange}
          placeholder="Upload Image"
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
