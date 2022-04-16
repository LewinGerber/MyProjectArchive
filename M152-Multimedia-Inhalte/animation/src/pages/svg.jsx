import './svg-style.css';
import svgBg from './svg_bg.gif';

export const SvgPage = () => {
    return (
            <div id="svg-wrapper">
                <img className="bg-image" src={svgBg} />
                <svg width="100%" height="100%" id="svg-block">
                    <circle cx="50%" cy="50%" stroke="black" strokeWidth="20" r="45%" fill="none" id="circle-1" />
                    <circle cx="50%" cy="50%" stroke="black" strokeWidth="12.5" r="22.5%" fill="none" id="circle-2" />
                    <circle cx="50%" cy="50%" stroke="black" strokeWidth="7.5" r="6.25%" fill="none" id="circle-3" />
                </svg>
            </div>
    );
}

export default SvgPage;