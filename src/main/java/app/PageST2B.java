package app;


import java.util.ArrayList;


import io.javalin.http.Context;
import io.javalin.http.Handler;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
* Example Index HTML class using Javalin
* <p>
* Generate a static HTML page using Javalin
* by writing the raw HTML into a Java String object
*
* @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
* @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
*/
public class PageST2B implements Handler {


   // URL of this page relative to http://localhost:7001/
   public static final String URL = "/page2B.html";

    private String countryNameSelected;
    private String cityOrStateSelected;
    private String startYearSelected;
    private String endYearSelected;
    
    private String sortBySelected;
    
   @Override
   public void handle(Context context) throws Exception {
       // Create a simple HTML webpage in a String
       String html = "<html>";


       // Add some Head information
       html = html + "<head>" +
              "<title>Subtask 2.2</title>";


       // Add some CSS (external file)
       html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
       html = html + "</head>";


       // Add the body
       html = html + "<body>";


       // Add the topnav
       // This uses a Java v15+ Text Block
       html = html + """
           <div class='topnav'>
               <a class='button' href='/'>Homepage</a>
               <a class='button' href='mission.html'>Our Mission</a>
               <a class='button' href='page2A.html'>Sub Task 2.A</a>
               <a class='button' href='page2B.html'>Sub Task 2.B</a>
               <a class='button' href='page3A.html'>Sub Task 3.A</a>
               <a class='button' href='page3B.html'>Sub Task 3.B</a>
           </div>
       """;


       // Add header content block
       html = html + """
           <div class='header'>
               <h1>Subtask 2.B</h1>
           </div>
       """;
 

       // Add Div for page Content
       html = html + "<div class='content'>";

       ArrayList<String> countryNames = getCountryName();


      
       // Add HTML for the page content

       
       
       
       
       
       


       html = html + "<form action='/page2B.html' method='post'>";


       html = html + "<div style = 'position:absolute; left: 820px; top: 240;'><b>Rank by</b></div>";
       html = html + "<div class='buttonAvg'>";
       html = html + "<select name='sortby' id='sortby' required>";
       html = html + "<option value='mintemp'> Change in Min Temperature</option>";
       html = html + "<option value='maxtemp'> Change in Max Temperature</option>";
       html = html + "<option value='avgtemp'> Change in Average Temperature</option>";
       html = html + "</select>";
       html = html + "</div>";

       String sortBy = context.formParam("sortby");
       


       if (sortBy != null && sortBy.equals("mintemp")) {
        sortBySelected = "minTemp";
       } else if (sortBy != null && sortBy.equals("maxtemp")) {
        sortBySelected = "maxTemp";
       } else if (sortBy != null && sortBy.equals("avgtemp")) {
        sortBySelected = "avgTemp";
       }
       



       html = html + "<div style = 'position:absolute; left: 165px; top: 240px;'><b>Select Country</b></div>";
       html = html + "<div class='dropdownCountry'>";
       html = html + "<select name='countryselection' id='countryselection' required>";

       html = html + "<option value='Select'>Select Country</option>";
       
       
       for (String name : countryNames) {
           html = html + "<option value='" + name + "'>" + name + "</option>";


       }
       
       html = html + "</select>";
       html = html + "</div>";
       
       String countrySelected = context.formParam("countryselection");
       countryNameSelected = countrySelected;
       
       
       
       
       

       html = html + "<div style = 'position:absolute; left: 425px; top: 240px;'><b>Show States</b></div>";

       
       
       html = html + "<div class ='dropdownState'>";
       html = html + "<input type='radio' id='chooseState' name='CityorState' value='State'>";
       
       html = html + "</div>";
       
       

       

       html = html + "<div style = 'position:absolute; left: 640px; top: 240px;'><b>Show Cities</b></div>";
       html = html + "<div class ='dropdownCity'>";
       html = html + "<input type='radio' id='chooseCity' name='CityorState' value='City'>";
       
       html = html + "</div>";
       
       String cityOrState = context.formParam("CityorState");
       cityOrStateSelected = cityOrState;


       
       html = html + "<div style='position: absolute; top: 200px; left: 400px;'><b><i>Please ensure to fill all fields, then press 'Show data'.</b></i></div>";
       
       //table
       

       html = html + "<div style = 'position: absolute; left: 1100px; top: 200px;'><b>Available Years: between 1750 and 2013</b></div>";
       html = html + "<div style = 'position:absolute; left: 1100px; top: 240px;'><b>Select Start Year</b></div>";

       
       html = html + "<div class='dateselectStart'>";
       html = html + "<input type='number' min='1750' max='2013' name='startDate' id='startDate'>";
       
       html = html + "</div>";
       
       String startDate = context.formParam("startDate");
       startYearSelected = startDate;
       
       html = html + "<div style = 'position:absolute; left: 1250px; top: 240px;'><b>Select End Year</b></div>";
       
       html = html + "<div class='dateselectEnd'>";
       html = html + "<input type='number' min='1750' max='2013' name='endDate' id='endDate'>";
       html = html + "</div>";

       String endDate = context.formParam("endDate");
       endYearSelected = endDate;

       String TableState = getListState();
       String TableCity = getListCity();
       String whatisRankedby = "";
       if (sortBy != null && sortBy.equals("mintemp")) {
        whatisRankedby = "Change in minimum temperature";
       } else if (sortBy != null && sortBy.equals("maxtemp")) {
        whatisRankedby = "Change in maximum temperature";
       } else if (sortBy != null && sortBy.equals("avgtemp")) {
        whatisRankedby = "Change in average temperature";
       }
       html = html + "<button type='submit' class='buttonSubmitCountry'>Show Data</button>";
       if (countryNameSelected != null) {
        html = html + "<div class='whatshappening'>Currently showing temperatures from <b>" + cityOrState + "s</b> in <b>" + countryNameSelected + "</b> from years <b>" + startYearSelected + "</b> and <b>" + endYearSelected + "</b> ranked by <b>" + whatisRankedby + "</b></div>";
       }
       html = html + "</form>";
       html = html + "<div class='tableM'>";
       html = html + "<table class='table'>";
       
       if (cityOrStateSelected != null && cityOrStateSelected.equals("State")) {
       html = html + TableState;
        } else if (cityOrStateSelected != null && cityOrStateSelected.equals("City")) {

        html = html + TableCity;

       
       }
       html = html + "</table>";
       html = html + "</div>";
       html = html + "</div>";
       // Footer
       html = html + """
           <div class='footer'>
               <p>COSC2803 - Studio Project Starter Code (Apr23)</p>
           </div>
       """;


       // Finish the HTML webpage
       html = html + "</body>" + "</html>";
      
       

       // DO NOT MODIFY THIS
       // Makes Javalin render the webpage
       context.html(html);
   }
   public ArrayList<String> getCountryName() {
       // Create the ArrayList of LGA objects to return
       ArrayList<String> countryNames = new ArrayList<String>();


       // Setup the variable for the JDBC connection
       Connection connection = null;


       try {
           // Connect to JDBC data base
           connection = DriverManager.getConnection(JDBCConnectionClimate.DATABASE);


           // Prepare a new SQL Query & Set a timeout
           Statement statement = connection.createStatement();
           statement.setQueryTimeout(30);


           // The Query
           String query = "SELECT * FROM Country";
          
           // Get Result
           ResultSet results = statement.executeQuery(query);


           // Process all of the results
           while (results.next()) {
               String name16  = results.getString("CountryName");


               // Add the lga object to the array
               countryNames.add(name16);
           }


           // Close the statement because we are done with it
           statement.close();
       } catch (SQLException e) {
           // If there is an error, lets just pring the error
           System.err.println(e.getMessage());
       } finally {
           // Safety code to cleanup
           try {
               if (connection != null) {
                   connection.close();
               }
           } catch (SQLException e) {
               // connection close failed.
               System.err.println(e.getMessage());
           }
       }


       // Finally we return all of the lga
       return countryNames;
   }
   public ArrayList<String> getStateName() {
       // Create the ArrayList of LGA objects to return
       ArrayList<String> StateNames = new ArrayList<String>();


       // Setup the variable for the JDBC connection
       Connection connection = null;


       try {
           // Connect to JDBC data base
           connection = DriverManager.getConnection(JDBCConnectionClimate.DATABASE);


           // Prepare a new SQL Query & Set a timeout
           Statement statement = connection.createStatement();
           statement.setQueryTimeout(30);


           // The Query
           String query = "SELECT DISTINCT CountryName, STATE FROM StateTempObservation INNER JOIN Country ON StateTempObservation.Country = Country.CountryCode WHERE Country.CountryName = '" + countryNameSelected + "';";
          
           // Get Result
           ResultSet results = statement.executeQuery(query);


           // Process all of the results
           while (results.next()) {
               String name16  = results.getString("STATE");


               // Add the lga object to the array
               StateNames.add(name16);
           }


           // Close the statement because we are done with it
           statement.close();
       } catch (SQLException e) {
           // If there is an error, lets just pring the error
           System.err.println(e.getMessage());
       } finally {
           // Safety code to cleanup
           try {
               if (connection != null) {
                   connection.close();
               }
           } catch (SQLException e) {
               // connection close failed.
               System.err.println(e.getMessage());
           }
       }


       // Finally we return all of the lga
       return StateNames;
   }
   public ArrayList<String> getCityName() {
       // Create the ArrayList of LGA objects to return
       ArrayList<String> CityNames = new ArrayList<String>();


       // Setup the variable for the JDBC connection
       Connection connection = null;


       try {
           // Connect to JDBC data base
           connection = DriverManager.getConnection(JDBCConnectionClimate.DATABASE);


           // Prepare a new SQL Query & Set a timeout
           Statement statement = connection.createStatement();
           statement.setQueryTimeout(30);


           // The Query
           String query = "SELECT DISTINCT CountryName, City FROM CityTempObservation INNER JOIN Country ON CityTempObservation.CountryCode = Country.CountryCode WHERE Country.CountryName = '" + countryNameSelected + "';";
          
           // Get Result
           ResultSet results = statement.executeQuery(query);


           // Process all of the results
           while (results.next()) {
               String name16  = results.getString("City");


               // Add the lga object to the array
               CityNames.add(name16);
           }


           // Close the statement because we are done with it
           statement.close();
       } catch (SQLException e) {
           // If there is an error, lets just pring the error
           System.err.println(e.getMessage());
       } finally {
           // Safety code to cleanup
           try {
               if (connection != null) {
                   connection.close();
               }
           } catch (SQLException e) {
               // connection close failed.
               System.err.println(e.getMessage());
           }
       }


       // Finally we return all of the lga
       return CityNames;
   }
   public ArrayList<String> getDates() {
       // Create the ArrayList of LGA objects to return
       ArrayList<String> Dates = new ArrayList<String>();


       // Setup the variable for the JDBC connection
       Connection connection = null;


       try {
           // Connect to JDBC data base
           connection = DriverManager.getConnection(JDBCConnectionClimate.DATABASE);


           // Prepare a new SQL Query & Set a timeout
           Statement statement = connection.createStatement();
           statement.setQueryTimeout(30);


           // The Query
           String query = "SELECT * FROM Date;";
          
           // Get Result
           ResultSet results = statement.executeQuery(query);


           // Process all of the results
           while (results.next()) {
               String name16  = results.getString("Year");


               // Add the lga object to the array
               Dates.add(name16);
           }


           // Close the statement because we are done with it
           statement.close();
       } catch (SQLException e) {
           // If there is an error, lets just pring the error
           System.err.println(e.getMessage());
       } finally {
           // Safety code to cleanup
           try {
               if (connection != null) {
                   connection.close();
               }
           } catch (SQLException e) {
               // connection close failed.
               System.err.println(e.getMessage());
           }
       }


       // Finally we return all of the lga
       return Dates;
   }
   public ArrayList<String> getListCountry() {
       // Create the ArrayList of LGA objects to return
       ArrayList<String> List = new ArrayList<String>();


       // Setup the variable for the JDBC connection
       Connection connection = null;


       try {
           // Connect to JDBC data base
           connection = DriverManager.getConnection(JDBCConnectionClimate.DATABASE);


           // Prepare a new SQL Query & Set a timeout
           Statement statement = connection.createStatement();
           statement.setQueryTimeout(30);


           // The Query
           String query = "Select Country.CountryName, Year, AvgTemp, Mintemp, Maxtemp, Population from CountryTempObservation INNER JOIN Country on CountryTempObservation.CountryCode = Country.CountryCode WHERE (Country.CountryName = '" + countryNameSelected + "') AND (Year BETWEEN " + startYearSelected + " AND " + endYearSelected + ");";
          
           // Get Result
           ResultSet results = statement.executeQuery(query);


           // Process all of the results
           while (results.next()) {
               String name16  = results.getString("CountryName");
               String year  = results.getString("Year");
               String avgTemp  = results.getString("AvgTemp");
               String minTemp  = results.getString("Mintemp");
               String maxTemp  = results.getString("Maxtemp");
               String population  = results.getString("Population");

               // Add the lga object to the array
               if (population.equals("0"))   {
                population = "No Data";
               }
               List.add(Country.addCountry(name16, year, avgTemp, minTemp, maxTemp, population));
               
               
           }


           // Close the statement because we are done with it
           statement.close();
       } catch (SQLException e) {
           // If there is an error, lets just pring the error
           System.err.println(e.getMessage());
       } finally {
           // Safety code to cleanup
           try {
               if (connection != null) {
                   connection.close();
               }
           } catch (SQLException e) {
               // connection close failed.
               System.err.println(e.getMessage());
           }
       }


       // Finally we return all of the lga
       return List;
   }
   public String getListState() {
       // Create the ArrayList of LGA objects to return
       
       ArrayList<String> state = new ArrayList<String>();
       ArrayList<String> temp1 = new ArrayList<String>();
       ArrayList<String> temp2 = new ArrayList<String>();
       ArrayList<String> diffTemp = new ArrayList<String>();
       

    

       // Setup the variable for the JDBC connection
       Connection connection = null;
        String list = "<tr><th>Rank</th><th>State</th><th>Temp at Start Year (C)</th><th>Temp at end year (C)</th><th>Difference in temp (C)</th></tr>";

       try {
           // Connect to JDBC data base
           connection = DriverManager.getConnection(JDBCConnectionClimate.DATABASE);


           // Prepare a new SQL Query & Set a timeout
           Statement statement = connection.createStatement();
           statement.setQueryTimeout(30);

           String query = "";
           // The Query
           
           query = "SELECT S.State, AVG(CASE WHEN S.Year = " + startYearSelected + " THEN S." + sortBySelected + " END) AS avgStartYearTemp, AVG(CASE WHEN S.Year = " + endYearSelected + " THEN S." + sortBySelected + " END) AS avgEndYearTemp, ROUND(Abs(AVG(CASE WHEN S.Year = " + endYearSelected + " THEN S." + sortBySelected + " END) - AVG(CASE WHEN S.Year = " + startYearSelected + " THEN S." + sortBySelected + " END)), 2) AS tempDifference FROM StateTempObservation S JOIN Country C ON S.Country = C.CountryCode WHERE C.CountryName = '" + countryNameSelected + "' AND S.Year IN (" + startYearSelected + ", " + endYearSelected + ") GROUP BY S.State ORDER BY tempDifference DESC;";
           
           // Get Result
           ResultSet results = statement.executeQuery(query);
           

           // Process all of the results
           while (results.next()) {
               
               String staten  = results.getString("State");
               
               String temp1is  = results.getString("avgStartYearTemp");
               String temp2is = results.getString("avgEndYearTemp");
               String diffTempis  = results.getString("tempDifference");
               ;

               // Add the lga object to the array
               
               
               state.add(staten);
               temp1.add(temp1is);
               temp2.add(temp2is);
               diffTemp.add(diffTempis);
               
               
               
           }
           
           for (int i = 0; i < state.size(); ++i){
           
           list = list + "<tr><td>" + (i + 1) + "</td><td>" +  state.get(i) + "</td><td>" + temp1.get(i) + "</td><td>" + temp2.get(i) + "</td><td>" + diffTemp.get(i) + "</td></tr>";
           }
           // Close the statement because we are done with it
           statement.close();
       } catch (SQLException e) {
           // If there is an error, lets just pring the error
           System.err.println(e.getMessage());
       } finally {
           // Safety code to cleanup
           try {
               if (connection != null) {
                   connection.close();
               }
           } catch (SQLException e) {
               // connection close failed.
               System.err.println(e.getMessage());
           }
       }


       
    // Finally we return all of the lga
       return list;
   }
   public String getListCity() {
       // Create the ArrayList of LGA objects to return
       
       ArrayList<String> city = new ArrayList<String>();
       
       ArrayList<String> temp1 = new ArrayList<String>();
       ArrayList<String> temp2 = new ArrayList<String>();
       ArrayList<String> diffTemp = new ArrayList<String>();
       


       // Setup the variable for the JDBC connection
       Connection connection = null;

        String list = "<tr><th>Rank</th><th>City</th><th>Start Year Temperature (C)</th><th>End Year Temperature (C)</th><th>Difference in Temperatures (C)</th></tr>";

       try {
           // Connect to JDBC data base
           connection = DriverManager.getConnection(JDBCConnectionClimate.DATABASE);


           // Prepare a new SQL Query & Set a timeout
           Statement statement = connection.createStatement();
           statement.setQueryTimeout(30);


           // The Query
           String query = "SELECT Citytempobservation.City, AVG(CASE WHEN Citytempobservation.Year = " + startYearSelected + " THEN Citytempobservation." + sortBySelected + " END) AS avgStartYearTemp, AVG(CASE WHEN Citytempobservation.Year = " + endYearSelected + " THEN Citytempobservation." + sortBySelected + " END) AS avgEndYearTemp, ROUND(abs(AVG(CASE WHEN Citytempobservation.Year = " + endYearSelected + " THEN Citytempobservation." + sortBySelected + " END) - AVG(CASE WHEN Citytempobservation.Year = " + startYearSelected + " THEN Citytempobservation." + sortBySelected + " END)), 2) AS tempDifference FROM CityTempObservation JOIN Country ON CityTempObservation.CountryCode = Country.CountryCode WHERE Country.CountryName = '" + countryNameSelected + "' AND Citytempobservation.Year IN (" + startYearSelected + ", " + endYearSelected + ") GROUP BY Citytempobservation.City ORDER BY tempDifference DESC;";
          
           // Get Result
           ResultSet results = statement.executeQuery(query);
           if (results.next() == false) {
            list = "<h1>No data for selected filters</h1>";
           } else {

           // Process all of the results
           while (results.next()) {
               
               String citym  = results.getString("City");
               String temp1is = results.getString("avgStartYearTemp");
               String temp2is  = results.getString("avgEndYearTemp");
               String diffTempis  = results.getString("tempDifference");
               
               

               // Add the lga object to the array
               
               
               city.add(citym);
               temp1.add(temp1is);
               temp2.add(temp2is);
               diffTemp.add(diffTempis);
               
               
           }
        
           for (int i = 0; i < city.size(); ++i){
           
           list = list + "<tr><td>" + (i + 1) + "</td><td>" +  city.get(i) + "</td><td>" + temp1.get(i) + "</td><td>" + temp2.get(i) + "</td><td>" + diffTemp.get(i) + "</td></tr>";
           }
        }
           // Close the statement because we are done with it
           statement.close();
       } catch (SQLException e) {
           // If there is an error, lets just pring the error
           System.err.println(e.getMessage());
       } finally {
           // Safety code to cleanup
           try {
               if (connection != null) {
                   connection.close();
               }
           } catch (SQLException e) {
               // connection close failed.
               System.err.println(e.getMessage());
           }
       }


       // Finally we return all of the lga
       return list;
   }
   
}



