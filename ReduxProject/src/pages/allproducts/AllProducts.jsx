import React from 'react'
import Navbar from '../../components/navbar/Navbar'
import Products from '../../components/products/Products'
import { allProducts } from '../../data' 

const AllProducts = () => {
  return (
    <div>
        <Navbar />
        <Products heading="All Products" items={allProducts} />
    </div>
  )
}

export default AllProducts