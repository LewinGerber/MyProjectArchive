import { React, useState } from 'react';
import Slider from 'react-animated-slider';
import horizontalCss from 'react-animated-slider/build/horizontal.css';
import { Gallery, Item } from 'react-photoswipe-gallery';
import { SiUnsplash } from 'react-icons/si';
import { FaCamera } from 'react-icons/fa';
import { HiUpload } from 'react-icons/hi';
import { useDropzone } from 'react-dropzone';
import 'photoswipe/dist/photoswipe.css';
import 'photoswipe/dist/default-skin/default-skin.css';
import './App.css';

import unsplashImages from './images/unsplash.js';

function App() {
  //images for the slider 
  const sliderImages = [
    { url: 'https://images.unsplash.com/photo-1468581264429-2548ef9eb732?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1950&q=80' },
    { url: 'https://images.unsplash.com/photo-1494825514961-674db1ac2700?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1950&q=80' },
    { url: 'https://images.unsplash.com/photo-1451976426598-a7593bd6d0b2?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1950&q=80' },
    { url: 'https://images.unsplash.com/photo-1497752531616-c3afd9760a11?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1950&q=80' },
  ]

  // images from unsplash
  const images = unsplashImages;

  // uploaded images
  const [uploadedImages, setUploadedImages] = useState([]);

  // self created images
  const selfCreatedImages = importImagesFromDir(require.context('./images/', false, /\.(png|jpe?g|svg)$/));;

  function importImagesFromDir(r) {
    let images = [];
    r.keys().map((item) =>
      images.push((images[item.replace('./', '')] = r(item)).default));
    let imageOutput = [];
    images.map((image) => {
      let emptyImage = new Image();
      emptyImage.src = image;
      imageOutput.push({ url: image, width: emptyImage.width, height: emptyImage.height })
    });
    console.log(imageOutput)
    return imageOutput;
  }

  // read file from upload
  function readFile(e) {
    var image = new Image();
    const files = e.target.files[0];
    if (files) {
      const fileReader = new FileReader();
      fileReader.readAsDataURL(files);
      fileReader.addEventListener("load", function () {
        image.src = this.result;
        image.onload = () => {
          setUploadedImages([...uploadedImages,
          {
            src: image.src,
            width: image.width,
            height: image.height
          }
          ]);
        };
      });
    }
  }

  function Dropzone() {
    const {acceptedFiles, getRootProps, getInputProps} = useDropzone();

    const images = acceptedFiles.map(file => {
      var image = new Image();
      const fileReader = new FileReader();
      fileReader.readAsDataURL(file);
      fileReader.addEventListener("load", function () {
        image.src = this.result;
        image.onload = () => {
          setUploadedImages([...uploadedImages,
          {
            src: image.src,
            width: image.width,
            height: image.height
          }
          ]);
        };
      });
    });
  
    return (
      <section className="dropzone-section">
        <div {...getRootProps({className: 'dropzone'})}>
          <input {...getInputProps()} />
          <p>Drag 'n' drop some files here, or click to select files</p>
        </div>
      </section>
    );
  }

  return (
    <div className="App">
      <div id="title-area">
        <h1>Bildergalerie</h1>
        <div id="nav-links">
          <a href="#unsplash"><SiUnsplash style={{ marginRight: '0.25rem' }} /> Unsplash </a>
          <a href="#eigene-bilder"><FaCamera style={{ marginRight: '0.25rem' }} /> Eigene Bilder </a>
          <a href="#upload"><HiUpload style={{ marginRight: '0.25rem' }} /> Upload </a>
        </div>
      </div>
      <div id="slider-area">
        <Slider classNames={horizontalCss}>
          {sliderImages.map((image) =>
            <div style={{ background: 'url(' + image.url + ') no-repeat center center', backgroundSize: 'cover' }} />
          )}
        </Slider>
      </div>
      <div id="gallery-area">
        <div id="unsplash" style={{ backgroundColor: 'red', height: '2px', width: '2px', position: 'relative' }} />
        <h2 className="subtitle"><SiUnsplash style={{ marginRight: '1rem' }} />Unsplash</h2>
        <div id="gallery-wrapper">
          <Gallery>
            {
              images.map((image) =>
                <Item
                  original={image.url}
                  thumbnail={image.thumbnail}
                  width={image.width}
                  height={image.height}
                >
                  {({ ref, open }) => (
                    <img ref={ref} id="gallery-item" onClick={open} src={image.thumbnail} />
                  )}
                </Item>
              )
            }
          </Gallery>
        </div>
        <div id="eigene-bilder" style={{ backgroundColor: 'red', height: '2px', width: '2px', position: 'relative' }} />
        <h2 className="subtitle"><FaCamera style={{ marginRight: '1rem' }} />Eigene Bilder</h2>
        <div id="gallery-wrapper">
          <Gallery>
            {console.log(selfCreatedImages)}
            {
              selfCreatedImages.map((image) =>
                <Item
                  original={image.url}
                  thumbnail={image}
                  width={image.width}
                  height={image.height}
                >
                  {
                  }
                  {({ ref, open }) => (
                    <div ref={ref} id="custom-gallery-item" style={{ backgroundImage: 'url(' + image.url + ')' }} onClick={open} />
                  )}
                </Item>
              )
            }
          </Gallery>
        </div>
        <div id="upload" style={{ backgroundColor: 'red', height: '2px', width: '2px', position: 'relative' }} />
        <h2 className="subtitle"><HiUpload style={{ marginRight: '1rem' }} />Upload</h2>
        <div id="gallery-wrapper">
          <Gallery>
            {
              uploadedImages.map((picture) =>
                <Item
                  original={picture.src}
                  thumbnail={picture}
                  width={picture.width}
                  height={picture.height}
                >
                  {({ ref, open }) => (
                    <img ref={ref} id="gallery-item" width="350px" height="450px" style={{ objectFit: 'cover' }} onClick={open} src={picture.src} />
                  )}
                </Item>
              )
            }
          </Gallery>
        </div>

        {/*
        <form>
          <div id="upload-image-container">
            <h1 id="upload-backdrop-text">UPLOAD</h1>
            <input id="image-upload" type="file" accept="image/*" id="choose-file" name="choose-file" onChange={readFile} />
          </div>
        </form>
        */}
        
        <div id="dropzone-wrapper">
          <Dropzone />
        </div>
        
      </div>

      <div id="small-footer">
        <p>
          Bildergalerie - Unsplash x Lewin Gerber
        </p>
      </div>
    </div>
  );
}

export default App;
