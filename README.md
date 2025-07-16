# CatImg
Android application for editing images.

CatImg — it is my finished pet-project for editing photos.

List of app functions:
- Import photo from Gallery or making photo with phone camera.
- Applying filters (Almost all of the filters from the GPUImage Library that can be applied to the image).
- Cropping image.
- Transforming (rotating by a clockwise and anticlockwise, horizontal and vertical flipping).
- Undo/Redo function.
- Export to device with custom name.
- Option switch dark/light theme with saving user preferences.
- Sharing photo to messangers.

Warning:
Some filters do not work as well as they can because they need to select (guess) bound values. Not all the filters have correct documentation (GPUImage).

Used technologies:
- **Jetpack Compose** - UI.
- **GPUImage** — applying filters.
    - License: [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0)
    - Copyright 2018 CyberAgent, Inc.
- **Image Cropper** — cropping images.
    - License: [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0)
    - Copyright 2016, Arthur Teplitzki, 2013, Edmodo, Inc.

Download:
git clone https://github.com/Quienley/CatImg
