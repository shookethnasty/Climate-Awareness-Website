package app;

public class Country {
    

    public static String addCountry(String countryName, String year, String avgTemp, String minTemp, String maxTemp, String population) {
        countryName = "Country: " + countryName;
        year = "Year: " + year;
        avgTemp = "AvgTemp: " + avgTemp + "C";
        minTemp = "MinTemp: " + minTemp + "C";
        maxTemp = "MaxTemp: " + maxTemp + "C";
        population = "Population: " + population;
        String formattedName = String.format("%-20s", countryName);
        String formattedYear = String.format("%-10s", year);
        String formattedAvg = String.format("%-20s", avgTemp);
        String formattedMin = String.format("%-20s", minTemp);
        String formattedMax = String.format("%-20s", maxTemp);
        String formattedPop = String.format("%-24s", population);
        String row =  formattedName + " | " + formattedYear + " | " + formattedAvg + " | " + formattedMin + " | " + formattedMax + " | " + formattedPop;
        return row;
    }

    

    
}
