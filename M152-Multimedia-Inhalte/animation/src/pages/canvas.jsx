import { React, useRef, useEffect } from 'react';
import './canvas-style.css';

export const CanvasPage = () => {
    const canvasRef = useRef();

    function sleep(ms) {
        return new Promise(resolve => setTimeout(resolve, ms));
    }

    async function draw () {
        var canvas = canvasRef.current;
        var context = canvas.getContext('2d');
        let x = 0;
        while (x <= canvas.width) {
            context.clearRect(0, 0, canvas.width, canvas.height);
            context.fillRect(x, 0, 5, canvas.height);
            (x === canvas.width || x >= canvas.width) ? x = 0 : x += 1;
            await sleep(10);
        }
    }

    useEffect(draw, []);


    return (
        <div>
            <canvas id="canvas-area" ref={canvasRef}></canvas>
        </div>
    );
}

export default CanvasPage;