import React from 'react'
import train from "../assets/video/train.mp4";

const Train = () => {
    return(
        <div className='home'>
            <video src={train} autoPlay loop muted /> </div>
    )

}

export default Train