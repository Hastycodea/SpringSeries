import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { Link } from "react-router-dom";

const SingleDog = () => {
  const [dog, setDog] = useState([]);
  const { name } = useParams();

  useEffect(() => {
    const fetchSingleDogData = async () => {
      try {
        const res = await fetch(
          `https://api.thedogapi.com/v1/breeds/search?q=${name}`
        );
        const data = await res.json();
        setDog(data);
        console.log("The dog data", data);
      } catch (error) {
        console.error(error);
      }
    };
    fetchSingleDogData();
  }, [name]);

  return (
    <div>
      <section className="max-w-5xl mx-auto flex items-center justify-center h-screen  ">
        {dog.map((item) => (
          <div key={item.id} className="grid grid-cols-1 md:grid-cols-2 gap-8 p-8 md:place-items-center">
            <article>
              <img
                src={`https://cdn2.thedogapi.com/images/${item.reference_image_id}.jpg`}
                alt={item.name}
                loading="lazy"
              />
            </article>
            <article>
              <h1 className="text-white font-bold lg:text-5xl mb-8">{item.name}</h1>

              {item.description && <p className="text-slate-300 mb-8 lg:text-base leading-loose lg:leading-relaxed ">{item.description}</p>}
              <ul className="text-sm text-slate-400 leading-loose lg:leading-relaxed lg:text-base ">
                <li> <span className="font-bold text-white">Bred For:</span> {item.bred_for}</li>
                <li> <span className="font-bold text-white">Height: </span> {item.height.metric} cm</li>
                <li> <span className="font-bold text-white">Weight:</span> {item.weight.metric} kgs</li>
                <li> <span className="font-bold text-white">Origin:</span> {item.origin}</li>
                <li> <span className="font-bold text-white"> Temperament:</span> {item.temperament}</li>
                <li> <span className="font-bold text-white">Lifespan:</span> {item.life_span}</li>
              </ul>

              <Link to="/" className="inline-block bg-slate-600 px-4 py-2 rounded transition-all  text-white hover:bg-slate-500 duration-200 mt-8">
              &larr; Back
              </Link>
            </article>
          </div>
        ))}
      </section>
    </div>
  );
};

export default SingleDog;
