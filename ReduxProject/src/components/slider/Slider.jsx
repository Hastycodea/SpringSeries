import React from 'react'
import { IoMdArrowDropleft, IoMdArrowDropright } from 'react-icons/io'
import { sliderItems } from '../../data'
import './Slider.css'

const Slider = () => {
  return (
    <div className="slider-container">
        <div className="arrow" direction='left'>
            <IoMdArrowDropleft className='icon' />
        </div>

        <div className="slider-wrapper">
            {sliderItems.map((item) => (
                <div key={item.id} className='slide' style={{backgroundColor: `#${item.bg}`}}>
                    <div className="img-container">
                        <img src={item.img} alt="" className='img' />
                    </div>
                    <div className="info-container">
                        <h1 className='title'>{item.title}</h1>
                        <p className="desc">{item.desc}</p>
                        <button className='button'>Shop Now</button>
                    </div>
                </div>
            ))}
        </div>

        <div className="arrow" direction='right'>
            <IoMdArrowDropright className='icon' />
        </div>
    </div>
  )
}

export default Slider