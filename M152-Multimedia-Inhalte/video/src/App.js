import { React, useState, useEffect } from 'react';
import { Player } from 'video-react';
//styling
import { ToggleButton, ToggleButtonGroup } from '@material-ui/lab';
import './App.css';
//sources
import video from './video/video.mp4';
// optimized with: https://www.videosmaller.com/
import videoOptimized from './video/video_optimized.mp4';
import videoWebM from './video/video_optimized.webm';

function App() {
  const [size, setSize] = useState({width: window.innerWidth * 0.85, height: window.innerHeight * 0.85});
  const [mode, setMode] = useState(1);

  function handleResize () {
    //calculate new dimensions
    let newWidth = window.innerWidth * 0.85;
    let newHeight = window.innerHeight * 0.85;
    //set the state
    setSize({width: newWidth, height: newHeight});
  }

  useEffect(() => {
    // responsive design
    window.addEventListener('resize', handleResize);
    return () => window.removeEventListener('resize', handleResize);
  });

  const FullImplementation = () => (
    <video id="video" width={size.width} height={size.height} controls preload="auto">
        <source src={videoWebM} type="video/webm" />
        <source src={videoOptimized} type="video/mp4" />
    </video>
  );

  // mp4 implementation with one line of code
  const Mp4Implementation = () => (
    <video id="video" src={video} width={size.width} height={size.height} controls preload="auto" />
  );

  //made with video-react
  const LibraryImplementation = () => (
    <div style={{ width: '80%' }}>
      <Player playsinline src={videoOptimized} />
    </div>
  );
  
  return (
    <div className="app">
      <div id="navbar-switcher">
        <ToggleButtonGroup
          value={mode}
          exclusive
          onChange={(e, value) => value !== null ? setMode(value) : null}
          id="toggle-group"
        >
          <ToggleButton value={1}> 2 Sources </ToggleButton>
          <ToggleButton value={2}> MP4 </ToggleButton>
          <ToggleButton value={3}> Library </ToggleButton>
        </ToggleButtonGroup>
      </div>

      {/* load the 3 different implementations depending on which button was pressed */}
      { mode === 1 && <FullImplementation />}
      { mode === 2 && <Mp4Implementation />}
      { mode === 3 && <LibraryImplementation />}
      
      <div id="background-layer" />
    </div>
  );
}

export default App;
