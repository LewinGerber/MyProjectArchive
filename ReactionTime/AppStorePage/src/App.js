import './App.css';
import info from './info.json';
import Carousel from 'react-multi-carousel';
import 'react-multi-carousel/lib/styles.css';
import ReactStars from "react-rating-stars-component";
import SimpleReactLightbox, { SRLWrapper } from 'simple-react-lightbox'
import { useEffect } from 'react';

function App() {

  useEffect(() => {
    
  });

  const responsive = {
    superLargeDesktop: {
      breakpoint: { max: 2000, min: 1000 },
      items: 5
    },
    desktop: {
      breakpoint: { max: 2000, min: 1080 },
      items: 3
    },
    tablet: {
      breakpoint: { max: 1080, min: 800},
      items: 2
    },
    mobile: {
      breakpoint: { max: 800, min: 0 },
      items: 1
    }
  };

  const screenshots = importImagesFromDir(require.context('./assets/screenshots/', false, /\.(png|jpe?g|svg)$/));;

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
    return imageOutput;
  }

  return (
    <div id="container">
      <div class="top_box">
        <div id="app_icon_container"></div>
        <div>
          <h3 id="app_name">{info.appName}</h3>
          <p id="app_short_description">{info.shortDescription}</p>
          <ReactStars
            count={info.rating}
            size={22}
            activeColor="#ffd700"
          />
        </div>
        <a href="/app/app.apk" download>
          <div id="download_button">
            <p>GET</p>
          </div>
        </a>
      </div>
      <div class="description_box">
        <p>Beschreibung: </p>
        <p id="app_description">{info.description}</p>
      </div>
      <div class="image_slider_box">
        <p>Einblick: </p>
        {console.log(screenshots)}
        <div id="carousel">
          <SimpleReactLightbox>
            <SRLWrapper>
              <Carousel responsive={responsive}>
                {
                  screenshots.map((screenshot) => {
                    let styleUrl = "url(" + screenshot.url + ")";
                    return (
                      <a href={screenshot.url}>
                        <img className="image" style={{ backgroundImage: styleUrl }} />
                      </a>
                    );
                  })
                }
              </Carousel>
            </ SRLWrapper>
          </SimpleReactLightbox>
        </div>
      </div>
      <div class="footer_box">
        {info.footer}
      </div>
    </div>
  );
}

export default App;
