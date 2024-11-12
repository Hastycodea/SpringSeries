import React, { useEffect, useState } from "react";

const Home = () => {
  const [dogs, setDogs] = useState([]);
  useEffect(() => {
    const fetchDogData = async () => {
      try {
        const res = await fetch("https://api.thedogapi.com/v1/breeds");
        const data = await res.json();

        setDogs(data);
        console.log(data);
      } catch (error) {
        console.error(error);
      }
    };
    fetchDogData();
  }, []);

  return (
    <div>
      {!dogs ? (
        <h1 className="flex items-center justify-center h-screen text-3xl  text-white font-bold uppercase ">
          Loading...
        </h1>
      ) : (
        <section className="p-8 max-w-6xl mx-auto text-center">
          <h1 className="flex items-center justify-center  text-3xl lg:text-5xl  text-white font-bold  " >The Dog App</h1> 
          <p className="py-8 text-white">This website is powered by <a href="https://thedogapi.com/" className="text-indigo-600 underline active:text-orange-400">The Dog API</a></p>

          <form action="" className="max-w-2xl mx-auto" autoComplete="off">
            <input type="text" name="search" id="search" placeholder="Search for a dog/breed" className="w-full  py-2 px-4 rounded shadow  bg-slate-400 placeholder-white text-white outline-none"/>
          </form>
        </section>
      )}
    </div>
  );
};

export default Home;
