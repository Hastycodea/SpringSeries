import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";

const Home = () => {
  const [dogs, setDogs] = useState([]);
  const [text, setText] = useState("");

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

  const searchForDog = async() => {
    try {
        const res = await fetch(`https://api.thedogapi.com/v1/breeds/search?q=${text}`);
        const data = await res.json();
        setDogs(data);
      
    } catch (error) {
      console.error(error)
      
    }
  }

  const handleSubmit = (e) => {
    e.preventDefault() //prevent default loading of the page
    searchForDog()
  }

  return (
    <div>
      {!dogs ? (
        <h1 className="flex items-center justify-center h-screen text-3xl  text-white font-bold uppercase ">
          Loading...
        </h1>
      ) : (
        <section className="p-8 max-w-6xl mx-auto text-center">
          <h1 className="flex items-center justify-center  text-3xl lg:text-5xl  text-white font-bold  ">
            The Dog App
          </h1>
          <p className="py-8 text-white">
            This website is powered by{" "}
            <a
              href="https://thedogapi.com/"
              className="text-indigo-600 underline active:text-orange-400"
            >
              The Dog API
            </a>
          </p>

          <form onSubmit={handleSubmit} className="max-w-2xl mx-auto" autoComplete="off">
            <input
              type="text"
              name="search"
              id="search"
              placeholder="Search for a dog/breed"
              className="w-full  py-2 px-4 rounded shadow  bg-slate-400 placeholder-white text-white outline-none"
              value={text}
              onChange={(e) => setText(e.target.value)}
            />
          </form>

          <div className="grid grid-cols-1 gap-8 md:grid-cols-2 xl:grid-cols-3 my-10 rounded">
            {dogs.map((dog) => (
              // eslint-disable-next-line react/jsx-key
              <Link
                to={`${dog.name}`}
                key={dog.rank}
                className="bg-slate-600 hover:bg-slate-400 transition-all duration-200"
              >
                <article>
                  <img
                    src={`https://cdn2.thedogapi.com/images/${dog.reference_image_id}.jpg`}
                    alt={dog.name}
                    className="rounded md:h-56 w-full object-cover "
                    loading="lazy"
                  />
                  <h3 className="font-bold text-white mt-3 ">{dog.name}</h3>
                  <p className="text-slate-300">{dog.bred_for}</p>
                </article>
              </Link>
            ))}
          </div>
        </section>
      )}
    </div>
  );
};

export default Home;
