import React from 'react'
import { newArrivals } from '../../data'
import Products from "./Products"

const NewArrivals = () => {
  return (
    <div>
        <Products items={newArrivals} heading="New Arrivals" />
    </div>
  )
}

export default NewArrivals