import React from 'react'
import care from "../assets/video/care.mp4";

const Care = () => {
    return(
        <div className='home'>
            <video src={care} autoPlay loop muted /> </div>
    )

}

export default Care