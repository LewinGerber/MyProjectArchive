import Sketch from 'react-p5'

export const P5Page = () => {
    let x = 50;
    let y = 50;

    let speedSlider, gapSlider, sizeSlider, radiusSlider;

    const setup = (p5, parent) => {
        //p5.createCanvas(500, 500)
        p5.createCanvas(window.innerWidth, window.innerHeight).parent(parent);;
        p5.angleMode(p5.DEGREES);
        p5.rectMode(p5.CENTER);

        speedSlider = p5.createSlider(0, 500, 300, 10);
        speedSlider.position(window.innerWidth - 200, window.innerHeight - 120);

        gapSlider = p5.createSlider(0, 40, 40, 2);
        gapSlider.position(window.innerWidth - 200, window.innerHeight - 90);

        sizeSlider = p5.createSlider(100, 1200, 600, 50);
        sizeSlider.position(window.innerWidth - 200, window.innerHeight - 60);

        radiusSlider = p5.createSlider(100, 300, 100, 5);
        radiusSlider.position(window.innerWidth - 200, window.innerHeight - 30);
    }

    const draw = p5 => {
        let speed = speedSlider.value();
        let gap = gapSlider.value();
        let size = sizeSlider.value();
        let radius = radiusSlider.value();

        p5.background(10, 20, 30);
        p5.noFill();
        let frameCount = p5.frameCount;

        p5.translate(p5.width / 2, p5.height / 2)

        for (var i = 0; i < 15; i++) {
            p5.push();
            p5.rotate(p5.sin(frameCount + i) * speed);

            var r = p5.map(p5.sin(frameCount), -1, 1, 50, 255);
            var g = p5.map(p5.cos(frameCount / 2), -1, 1, 50, 255);
            var b = p5.map(p5.sin(frameCount / 4), -1, 1, 50, 255);

            p5.stroke(r, g, b);
            p5.rect(0, 0, 600 - i * gap, size, radius - i);

            p5.pop();
        }
    }

    return (
        <div style={{height: '100%', overflow: 'hidden'}}>
            <Sketch setup={setup} draw={draw}/>
        </div>
    );
}

export default P5Page;