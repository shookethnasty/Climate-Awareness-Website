package app;

public class City {
    

    public static String addCity(String countryName, String city, String year, String avgTemp, String minTemp, String maxTemp, String latitude, String longitude) {
        countryName = "Country: " + countryName;
        year = "Year: " + year;
        avgTemp = "AvgTemp: " + avgTemp + "C";
        minTemp = "MinTemp: " + minTemp + "C";
        maxTemp = "MaxTemp: " + maxTemp + "C";
        city = "City: " + city;
        longitude = "Longitude: " + longitude;
        latitude = "Latitude: " + latitude;
        String formattedName = String.format("%-20s", countryName);
        String formattedYear = String.format("%-10s", year);
        String formattedAvg = String.format("%-20s", avgTemp);
        String formattedMin = String.format("%-20s", minTemp);
        String formattedMax = String.format("%-20s", maxTemp);
        String formattedCity = String.format("%-24s", city);
        String formattedLat = String.format("%-20s", latitude);
        String formattedLon = String.format("%-20s", longitude);
        String row =  formattedName + " | " + formattedCity + " | " + formattedYear + " | " + formattedAvg + " | " + formattedMin + " | " + formattedMax + " | " + formattedLat + " | " + formattedLon;
        return row;
    }

    

    
}
