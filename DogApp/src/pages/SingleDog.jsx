import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

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
        console.log('The dog data', data)
      } catch (error) {
        console.error(error);
      }
    };
    fetchSingleDogData();
  }, [name]);

  return (
    <div>
      <section className="max-w-5xl mx-auto">
        {dog.map((item) => (
          <div key={item.id}>
            <article>
              <img src={`https://cdn2.thedogapi.com/images/${item.reference_image_id}.jpg`} alt={item.name} />
            </article>
            <article>
              <h1>{item.name}</h1>
              <p>{item.description}</p>
            </article>

          </div>
        ))}
      </section>
    </div>
  );
};

export default SingleDog;
