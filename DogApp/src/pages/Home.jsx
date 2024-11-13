import React, { useEffect, useState } from "react";
import axios from "axios";

const Home = () => {
  const [dogs, setDogs] = useState([]);
  const [nonFiction, setNonFiction] = useState([]);

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

  useEffect(() => {
    const fetchNonFiction = async () => {
      const results = await axios.get(
        `https://api.nytimes.com/svc/books/v3/lists/current/hardcover-nonfiction.json?api-key=${
          import.meta.env.VITE_BOOKS_API
        }`
      );
      setNonFiction(results.data.results.books);
      // console.log(res.data.results.books);
    };
    fetchNonFiction();
  }, []);

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

          <form action="" className="max-w-2xl mx-auto" autoComplete="off">
            <input
              type="text"
              name="search"
              id="search"
              placeholder="Search for a dog/breed"
              className="w-full  py-2 px-4 rounded shadow  bg-slate-400 placeholder-white text-white outline-none"
            />
          </form>

          <div className="grid grid-cols-1 gap-8 md:grid-cols-2 xl:grid-cols-3">
            {nonFiction.slice(0, 12).map((book) => (
              // eslint-disable-next-line react/jsx-key
              <div className="flex flex-col group relative">
                <img
                  src={book.book_image}
                  alt=""
                  className="h-[280px] w-[100%] object-contain hover:scale-95 duration-300 ease-out"
                />
                <p className="text-sm text-gray-500">{book.title}</p>
                <p className="text-sm text-gray-500">{book.price}</p>
              </div>
            ))}
          </div>
        </section>
      )}
    </div>
  );
};

export default Home;
