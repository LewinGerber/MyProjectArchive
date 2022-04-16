import { React, useEffect, useRef } from 'react';
import { gsap } from 'gsap';
import './gsap-style.css';

export const GsapPage = () => {
    const boxRef = useRef();

    useEffect(() => {
        var windowYoyo = gsap.utils.wrapYoyo(0, window.innerWidth); 
        gsap.to([boxRef.current], {
            x: window.innerWidth-160,
            modifiers: {
                x: x => windowYoyo(parseFloat(x)) + "px"
            },
            duration: 10
        })
    })

    return (
        <div id="gsap-wrapper">
            <div 
            ref={boxRef}
            style={{
                width: '160px',
                height: '160px',
                background: 'salmon'
            }} 
            />
        </div>
    );
}

export default GsapPage;