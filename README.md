# Image Explorer

![App-Screens](https://user-images.githubusercontent.com/13706140/80315717-0a7fe280-8817-11ea-9c0f-e3c27f89b3d7.png)

Explore soul soothing images and view its details using Image Explorer. Both List screen and Details screen support Portrait as well as Landscape orientation. The Images are fetched from API Endpoints hosted at `https://dev-test.dokku.mub.lu/`


## Project Setup
> Clone the repo, open the project in Android Studio, hit "Run". Done!


## Architecture Overview
The application is written in Kotlin language with the latest official Android architecture pattern - **MVVM**.  
It showcases the usage of **ViewModels**, **DataBinding**, **Animations & Transitions**, **Repository Pattern for Data** and **Testing**.   
The Project is separated into the following packages - 
- **API**:  
This package contains the API Client, API Constants, API Model files and API Service Endpoints 
- **Repository**:  
This package contains files which deal with fetching the data. This can be either local storage or remote. In our current application's case, Photo repository fetches images from the remote API.
- **UI**:  
This package contains all the UI related files viz. Activities, Fragments, Adapters, ViewData, ViewModels
- **Utils**:  
All the helper methods and utility files go here.


## External Libraries 
- **Retrofit**: Used for Networking
- **Moshi**: Used for JSON Parsing
- **Stetho**: Used for Debugging Network request responses
- **Shimmer**: Used as **Loading Indicator** while data is being fetched from API.
- **Lottie**: Used to display Lottie animations.
- **Glide**: Used to fetch and display Images.
- **RoboElectric**: Used for Testing.
- **Mockito**: Used for Testing.


## API DATA Inconsistencies
- **\n\n** being returned in one of the Image object's title. We have handled that using the Kotlin's trim function.
- **Future Date** being returned returned for one of the Image object.


## Future Improvements

**Use of Kotlin Navigation Components** -
App can be further migrated to use the latest released Android Navigation Components which prefers the use of single Activity and all Fragments. This would also help in creating transition effects using the official Kotlin's Navigation Fragment Transiton library.