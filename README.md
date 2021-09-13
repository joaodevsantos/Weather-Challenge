# Weather-Challenge

This repository contains the source code of an Android application developed as a requirement for the application for Android Developer at Wit-Software. The app makes requests to [OpenWeatherApi](https://openweathermap.org/)  and parses its JSON responses to get the weather at the current user's location and 10 European cities: Lisbon, Madrid, Paris, Berlin, Copenhagen, Rome, London, Dublin, Prague and Vienna.

## Project Management

The following tasks have tests included in their time.

| Task                                                   | Hours           |
| ------------------------------------------------------ |:---------------:|
| Implementation of the UI                               | 1 hour          |
| Manage UI navigation                                   | 20 minutes      |
| Implementation of the Retrofit Client                  | 30 minutes      |
| Manage current location (including permission request) | 2 hours         |
| Create the model                                       | 1 hour          |
| Processing the HTTP GET Requests                       | 2 hours         |
| Code CleanUp                                           | 30 minutes      |
| **Total**                                              | **+/- 8 hours** |

## Architecture

The HTTP GET requests are asynchronously made during the view creation of the Locations Fragment. The first request is for the weather at the user's current location (if provided) and after that, 10 requests are made to get the weather at the previously mentioned European cities.

The response (on JSON format) is serialized using the [Gson](https://github.com/google/gson) Java library to get the Java objects. This Java objects are added to an ArrayList that populates the Recycler View on the Locations Fragment.

When the user clicks on an item, the corresponding Java object is parcelable and sent through Bundle to the detailed Location Fragment.

![alt text](https://github.com/joaodevsantos/Weather-Challenge/blob/main/architecture/architecture.png?raw=true)

## Libraries Used

* [Android Jetpack](https://developer.android.com/jetpack) - is a suite of libraries to help developers follow best practices, reduce boilerplate code, and write code that works consistently across Android versions and devices so that developers can focus on the code they care about.
* [Glide](https://github.com/bumptech/glide) - A fast and efficient open source media management and image loading framework for Android
* [Gson](https://github.com/google/gson) - A Java library that can be used to convert Java Objects into their JSON representation. 
* [Retrofit2](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.

## Prerequisites

In order to run the application you need to:

* Replace the `API_KEY` value [here](https://github.com/joaodevsantos/Weather-Challenge/blob/main/app/src/main/java/com/example/weatherchallenge/utils/Constants.java) with your own API Key (Get it [here](https://openweathermap.org/)).

## Improvements

* The current location is only possible to obtain on Huawei devices. At this days Huawei devices don't have access to Google Play Services and since I only have Huawei smartphones to test the application, I had to use the HMS Core Services. I want to change the way I'm getting the location according to the access (or not) to Google Play Services.
* The images could be improved if I get them in Vector.
* I want the title to move when the recycler view is scrolled.
* I want to save the preferred units in Shared Preferences and include a button to the user change his preference.

## Author

<img src="https://avatars.githubusercontent.com/u/43722840?v=4" width="115"><br>
[João Santos](https://github.com/joaodevsantos)
