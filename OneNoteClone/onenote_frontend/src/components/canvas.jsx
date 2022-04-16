import React, { useRef, useState, useEffect } from 'react'
import '../assets/styles/note.css'

export const CanvasArea = ({ dataPoints, setDataPoints }) => {
    // canvas context
    const [ctx, setCtx] = useState();
    // canvas refrence
    const canvas = useRef();
    // painting mode
    const [painting, setPainting] = useState(false);
    // list of all the points of the current stroke
    const [drawingPoints, setDrawingPoints] = useState([]);

    useEffect(() => {
        setCtx(canvas.current.getContext("2d"));
    }, []);

    function mouseMovementHandler(event) {
        // only evaluate new points if painting mode is active
        if (painting) {
            // add new point to array
            setDrawingPoints([...drawingPoints, { x: event.clientX, y: event.clientY }]);
            // draw out the current state of the stroke
            drawLine();
        }
    }

    function startDrawing() {
        setPainting(true);
        setDrawingPoints([]);
    }

    function stopDrawing() {
        setPainting(false);
        setDataPoints([...dataPoints, drawingPoints.length]);
    }

    function drawPoint(event) {
        ctx.lineWidth = 4.5;
        ctx.lineCap = "round";
        ctx.strokeStyle = "#aa75d2";
        ctx.beginPath();
        ctx.moveTo(event.clientX, event.clientY);
        ctx.lineTo(event.clientX, event.clientY);
        ctx.stroke();
    }

    function drawLine() {
        ctx.lineWidth = 3;
        ctx.lineCap = "round";
        ctx.strokeStyle = "#aa75d2";
        drawingPoints.map((point, index) => {
            ctx.beginPath();
            ctx.moveTo(point.x, point.y);
            if (!(index + 1 >= drawingPoints.length)) {
                let second = drawingPoints[index + 1];
                ctx.lineTo(second.x, second.y);
            }
            ctx.stroke();
        });
    }

    return (
        <canvas 
            width={window.innerWidth}
            height={window.innerHeight}

            ref={canvas}
            id="canvas"

            onMouseMove={mouseMovementHandler}
            onMouseDown={startDrawing}
            onClick={drawPoint}
            onMouseUp={stopDrawing} 
        />
    );
}

export default CanvasArea;