# App Store
A simple preview site inspired by the Apple app store.
The aim of this react.js project is to create a website to showcase mobile apps without uploading them to the actual app or play store

![AppStore Website Preview](/ReactionTime/AppStorePage/res/app_store_preview.png)

## Installation and use
After downloading, the website can be preview by running 
```javascript
>>> yarn install
>>> yarn start
```
In order to enter your own content, first edit the ``` info.json ``` file which is located in the root directory.
This file is a basic JSON file where all the text for the app can be placed. The following elements are currently supported:
* **Appn name** (actual name of the app/ title)
* **Short app description** (short description of the app displayed below the name)
* **app description** (description of the app in its own paragraph)
* **footer** (small footer text at bottom of site)

To add the icon image, navigate to ``` /src/assets/ ```. Replace the ``` icon.png ``` file in this directory (it is recommended to use a 1:1 image as an icon).
For the preview images, you'll need to go one layer deeper at ``` /src/assets/screenshots ```. In there you can place all of your preview images. The naming of the files is not of importance.

In order for the dowload button to work a ``` app.apk ``` file has to be added to ``` /public/app/ ```. The naming of the file and the file extension do matter. In order to change these properties go to line ``` 56 ``` in the  ``` app.js ``` file.
