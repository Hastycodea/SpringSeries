import React from "react";
import { useParams } from "react-router-dom";
import { allProducts } from "../../data";
import Navbar from "../../components/navbar/Navbar";
import "./SingleProduct.css";

const SingleProduct = () => {
  const { id } = useParams();
  const product = allProducts.find((product) => product.id === parseInt(id));

  const colors = ["red", "purple", "green", "teal", "black"];
  const sizes = ["xs", "sm", "m", "l", "xl"];

  return (
    <div className="singleProduct-container">
      <Navbar />
      <div className="singleProduct-wrapper">
        <div className="singleProduct-imageSection">
          <img src={product.img} alt="" className="singleProduct-image" />
        </div>
        <div className="singleProduct-infoSection">
          <h2 className="singleProduct-title">{product.title}</h2>
          <p className="singleProduct-number">${product.price}</p>
          <h4 className="description-title">Description</h4>
          <p className="singleProduct-description">
            Lorem ipsum dolor sit amet consectetur adipisicing elit. Corrupti
            sunt neque exercitationem ratione blanditiis, omnis iusto aut, ipsa
            inventore necessitatibus earum nobis accusamus nostrum quia,
            incidunt excepturi harum dolorem deleniti.
          </p>
          <div className="singleProduct-options">
            <h4>Colors</h4>
            <div className="colors">
              {colors.map((color) => (
                <div
                  className="color-circle"
                  key={color}
                  style={{ backgroundColor: color }}
                ></div>
              ))}
            </div>

            <div className="sizes-section">
              <h4>Size</h4>
              <div className="sizes">
                {sizes.map((size) => (
                  <span key={size}>{size}</span>
                ))}
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default SingleProduct;
