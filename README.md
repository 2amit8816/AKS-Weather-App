Weather App -

**Objective:**
- The assignment's main objective is to create a weather app based on the provided Figma design and use the WeatherAPI to fetch the weather data.

  **Empty State:**
- When the app is opened for the first time, show the start screen with an empty state as per the design.
  
  **Search for a location:**
- User should be able to search for a City or a region
- The user should be able to select the current location option. When the user taps on the
current location, use the CoreLocation (for iOS) or Android Location API (for Android) to get the latitude and longitude. Use the latitude and longitude to fetch the weather data (WeatherAPI supports lat, long)
- To get the location suggestions, use Search Suggestions API from the WeatherAPI.

  **Weather Expand / Collapse:**
- Once the desired location is selected, add the location to your persistence layer of your choice, and show the expanded weather details of the location by fetching the weather data from the WeatherAPI. The weather data should be presented as per the design. Explore the WeatherAPI documentation and use the appropriate API(s) to fetch and display the data.
- Persist the location: When a user searches for a location, persist the data locally and show them as a list in the start screen. Users should be able to tap on the location the next time when they visit the app and should be able to expand the Weather data. Tapping on the weather data again will collapse the weather data. Users can use the swipe to delete option to remove the persisted location.
  **Attention to detail:**
Replication of exact designs during the development.

**API**
- Go to https://weatherapi.com and signup to get the free API Key.
- Detailed documentation at https://www.weatherapi.com/docs/ for the all the APIs. Go through the
documentation and use the appropriate APIs.

**Assets**
- Assignment UI reference: Figma design link
- For all weather forecast-related icons, either use the URLs of the image directly from the API response or download the icons from here and use weather conditions API data to map the local icons with the API (Suitable for Light / Dark mode differentiation)
- For icons used in the app, use SF Symbols (for iOS) / Material Icons (for Android). Here is the list of icons and system name of the icons that were used in the Figma designs:

<img width="691" alt="Screenshot 2024-12-10 at 9 41 17 PM" src="https://github.com/user-attachments/assets/ff067a9f-e0b5-4a5e-aee1-f654ad1f5229">

**- MVVM with CLEAN Architecture, HILT, ROOM DB, Jetpack compose**
