import React from 'react'
import Products from "./Products"
import { bestsellers } from '../../data'


const BestSellers = () => {
  return (
    <div>
        <Products items={bestsellers} heading="Bestsellers" />
    </div>
  )
}

export default BestSellers