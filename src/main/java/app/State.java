package app;

public class State {
    
    
    
    public static String addState(String countryName, String state, String year, String avgTemp, String minTemp, String maxTemp) {
        
        
        
        avgTemp = avgTemp + "C";
        minTemp = minTemp + "C";
        maxTemp = maxTemp + "C";
        
        
        String row = "<tr><td>" + year + "</td><td>" + countryName + "</td><td>" + state + "</td><td>" + avgTemp + "</td><td>" + minTemp + "</td><td>" + maxTemp + "</td></tr>";
        return row;
    }

    
    
}
