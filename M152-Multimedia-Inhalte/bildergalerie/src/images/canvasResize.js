const resizeImage = (image, newWidth, newHeight) => {
    let canvas = document.createElement('canvas');
    let ctx = canvas.getContext("2d");
    ctx.drawImage(image, 0, 0, newWidth, newHeight);
    return canvas.toDataURL("image/png");
}

function imageDataFromSrc(imagesSrc) {
    let image = new Image();
    image.src = imagesSrc;
    let imageThumbnail= resizeImage(image, 350, 450);
    return {thumbnail: imageThumbnail, width: image.width, height: image.height};
}

export default function importImagesFromDir(r) {
    let images = [];
    r.keys().map((item) => 
        images.push((images[item.replace('./', '')] = r(item)).default));
    let imageOutput = [];
    images.map(image => {
      let imageData = imageDataFromSrc(image);
      imageOutput.push({url: image, thumbnail: imageData.thumbnail, width: imageData.width, height: imageData.height})
    });
    return imageOutput;
}

export const imageArray = importImagesFromDir(require.context('./', false, /\.(png|jpe?g|svg)$/));