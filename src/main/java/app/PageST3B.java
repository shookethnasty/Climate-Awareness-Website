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
public class PageST3B implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page3B.html";

    private String countryNameSelected;
    private String stateNameSelected;
    private String cityNameSelected;
    private String startYearSelected;
    private String timeperiodSelected;
    private String amountsimilarSelected;
    private String similarbySelected;
    private String sortBySelected;
    private String temppopboth;
    @Override
   public void handle(Context context) throws Exception {
       // Create a simple HTML webpage in a String
       String html = "<html>";


       // Add some Head information
       html = html + "<head>" +
              "<title>Subtask 3.2</title>";


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
               <h1>Subtask 3.B</h1>
           </div>
       """;
 

       // Add Div for page Content
       html = html + "<div class='content'>";

       html = html + "<form action='/page3B.html' method='post'>";


      
       // Add HTML for the page content



       html = html + "<div style = 'position:absolute; left: 160px; top: 305px;'><b>Amount of Similar Values</b></div>";
       html = html + "<div class='SimilarAmount'>'";
       html = html + "<input type='number' min='0' name='amountsimilar' id='amountsimilar'>";
       
       
       html = html + "</div>";

       String amountofsimilar = context.formParam("amountsimilar");
       amountsimilarSelected = amountofsimilar;

       
       
       ArrayList<String> countryName = getCountryName();
       html = html + "<div style = 'position:absolute; left: 165px; top: 240px;'><b>Select Country</b></div>";
       html = html + "<div class='dropdownCountry'>";
       html = html + "<select name='countryselection' id='countryselection' required>";

       html = html + "<option value='Select'>Select Country</option>";
       
       
       for (String name : countryName) {
           html = html + "<option value='" + name + "'>" + name + "</option>";


       }
       
       html = html + "</select>";
       
       
       html = html + "</div>";

       String countryselected = context.formParam("countryselection");
       countryNameSelected = countryselected;

       ArrayList<String> stateName = getStateName();
       html = html + "<div style = 'position:absolute; left: 440px; top: 240px;'><b>Select State</b></div>";
       html = html + "<div class='dropdownState'>";
       html = html + "<select name='stateselection' id='stateselection' required>";

       html = html + "<option value='Select'>Select State</option>";
       
       
       for (String name : stateName) {
           html = html + "<option value='" + name + "'>" + name + "</option>";


       }
       
       html = html + "</select>";
       html = html + "</div>";

       String stateselected = context.formParam("stateselection");
       stateNameSelected = stateselected;


       ArrayList<String> cityName = getCityName();
       html = html + "<div style = 'position:absolute; left: 665px; top: 240px;'><b>Select City</b></div>";
       html = html + "<div class='dropdownCity'>";
       html = html + "<select name='cityselection' id='cityselection' required>";

       html = html + "<option value='Select'>Select City</option>";
       
       
       for (String name : cityName) {
           html = html + "<option value='" + name + "'>" + name + "</option>";


       }
       
       html = html + "</select>";
       
       html = html + "</div>";


       String cityselected = context.formParam("cityselection");
       cityNameSelected = cityselected;
       
       //table
       


       html = html + "<div style = 'position:absolute; left: 1080px; top: 240px;'><b>Select Start Year</b></div>";
       html = html + "<div class='dateselectStart'>";
       html = html + "<input type='number' min='1750' max='2013' name='startdate' id='startdate'>";
       
       html = html + "</div>";

       String startdate = context.formParam("startdate");
       startYearSelected = startdate;

       html = html + "<div style = 'position:absolute; left: 1250px; top: 240px;'><b>Select Time period</b></div>";
       html = html + "<div class='dateselectEnd'>";
       html = html + "<input type='number' min='0' name='timeperiod' id='timeperiod'>";
       html = html + "</div>";

       String timeperiod = context.formParam("timeperiod");
       timeperiodSelected = timeperiod;

       html = html + "<div style='position: absolute; left: 400px; top: 305px;'><b>Find Similar Temperatures</b></div>";
       html = html + "<div class='buttonSimilarTemp'>";
       html = html + "<input type='radio' id='findsimilar' name='findsimilar' value='findsimilarTemp'>";
       html = html + "</div>";
       html = html + "<div style='position: absolute; left: 650px; top: 305px;'>(Countries only) <b>Find Similar Populations</b></div>";
       html = html + "<div class='buttonSimilarPop'>";
       html = html + "<input type='radio' id='findsimilar' name='findsimilar' value='findsimilarPop'>";
       html = html + "</div>";
       


       String findsimilar = context.formParam("findsimilar");
       temppopboth = findsimilar;

       html = html + "<div style='position: absolute; top: 200px; left: 400px;'><b><i>Please Select only one (Country, State Or City) and ensure to fill out all other fields, then press 'Show Data'</i></b></div>";
       html = html + "<div style = 'position:absolute; left: 890px; top: 240px;'><b>Sort By</b></div>";
       html = html + "<div class='dropdownSortBy'>";
       html = html + "<select name='SortBy' id='SortBy'>";
       
       html = html + "<option value='Most'>Most Similar</option>";
       html = html + "<option value='Least'>Least Similar</option>";
       html = html + "</select>";
       html = html + "</div>";

       String sortby = context.formParam("SortBy");
       if (sortby != null && sortby.equals("Most")) {
        sortBySelected = "ASC";
       }    else if (sortby != null && sortby.equals("Least")) {
        sortBySelected = "DESC";
       }

       html = html + "<button type='submit' class='buttonsubmit'>Show Data</button>";
       if (stateselected != null && !stateselected.equals("Select")) {
        html = html + "<div class='whatshappening'>Currently Showing the <b>" + amountsimilarSelected + "</b><b> " + sortby + " Similar</b>  values to state: <b>" + stateNameSelected + "</b> starting at <b>" + startYearSelected + "</b> for a <b>" + timeperiodSelected + "</b> year time span.</div>";
       } else if (cityselected != null && !cityselected.equals("Select")) {
        html = html + "<div class='whatshappening'>Currently Showing the <b>" + amountsimilarSelected + "</b><b> " + sortby + " Similar</b>  values to city: <b>" + cityNameSelected + "</b> starting at <b>" + startYearSelected + "</b> for a <b>" + timeperiodSelected + "</b> year time span.</div>";
       } else if (countryselected != null && !countryselected.equals("Select")){
        html = html + "<div class='whatshappening'>Currently Showing the <b>" + amountsimilarSelected + "</b><b> " + sortby + " Similar</b>  values to country: <b>" + countryNameSelected + "</b> starting at <b>" + startYearSelected + "</b> for a <b>" + timeperiodSelected + "</b> year time span.</div>";
       }
       String tableCountries = "";
       html = html + "</form>";
       if (temppopboth != null && temppopboth.equals("findsimilarTemp")){
        tableCountries = getListCountryTemp();
       } else if (temppopboth != null && temppopboth.equals("findsimilarPop")) {
        tableCountries = getListCountryPop();
       }
       
       String tableCities = getListCity();
       String tableStates = getListState();
       html = html + "<div class='tableM'>";
       html = html + "<table class='table'>";
       if (stateselected != null && !stateselected.equals("Select")) {
        html = html + tableStates;
       } else if (cityselected != null && !cityselected.equals("Select")) {
        html = html + tableCities;
       } else if (countryselected != null && !countryselected.equals("Select")){
        html = html + tableCountries;
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
           String query = "SELECT DISTINCT CountryName, STATE FROM StateTempObservation INNER JOIN Country ON StateTempObservation.Country = Country.CountryCode ORDER BY STATE ASC;";
          
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
           String query = "SELECT DISTINCT CountryName, City FROM CityTempObservation INNER JOIN Country ON CityTempObservation.CountryCode = Country.CountryCode ORDER BY City ASC;";
          
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
   public String getListCountryTemp() {
       // Create the ArrayList of LGA objects to return
       ArrayList<String> country = new ArrayList<String>();
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

            
           // The Query
           String query = "SELECT C.CountryName, AVG(CASE WHEN CTO.Year = " + startYearSelected + " THEN CTO.AvgTemp END) AS avgStartYearTemp, AVG(CASE WHEN CTO.Year = " 
                + startYearSelected + " + " + timeperiodSelected + " THEN CTO.AvgTemp END) AS avgEndYearTemp, ROUND(ABS(AVG(CASE WHEN CTO.Year = " 
                + startYearSelected + " + " + timeperiodSelected + " THEN CTO.AvgTemp END) - AVG(CASE WHEN CTO.Year = " + startYearSelected 
                + " THEN CTO.AvgTemp END)), 5) AS tempDifference FROM CountryTempObservation CTO JOIN Country C ON CTO.CountryCode = C.CountryCode WHERE CTO.Year IN (" 
                + startYearSelected + ", " + startYearSelected + " + " + timeperiodSelected + ") GROUP BY C.CountryCode, C.CountryName HAVING AVG(CASE WHEN CTO.Year = " 
                + startYearSelected + " THEN CTO.AvgTemp END) IS NOT NULL AND AVG(CASE WHEN CTO.Year = " + startYearSelected + " + " + timeperiodSelected 
                + " THEN CTO.AvgTemp END) IS NOT NULL AND (C.CountryName = '" + countryNameSelected + "' OR tempDifference >= (SELECT ROUND(ABS(AVG(CASE WHEN CTO.Year = " 
                + startYearSelected + " + " + timeperiodSelected + " THEN CTO.AvgTemp END) - AVG(CASE WHEN CTO.Year = " + startYearSelected 
                + " THEN CTO.AvgTemp END)), 5) FROM CountryTempObservation CTO JOIN Country C ON CTO.CountryCode = C.CountryCode WHERE C.CountryName = '" + countryNameSelected 
                + "' AND CTO.Year IN (" + startYearSelected + ", " + startYearSelected + " + " + timeperiodSelected 
                + ") GROUP BY C.CountryCode, C.CountryName)) ORDER BY tempDifference ASC LIMIT " + amountsimilarSelected + ";";

          
           // Get Result
           ResultSet results = statement.executeQuery(query);
            
         
           // Process all of the results
           while (results.next()) {
               String staten  = results.getString("CountryName");
               
               String temp1is  = results.getString("avgStartYearTemp");
               String temp2is = results.getString("avgEndYearTemp");
               String diffTempis  = results.getString("tempDifference");
               ;

               // Add the lga object to the array
               
               
               country.add(staten);
               temp1.add(temp1is);
               temp2.add(temp2is);
               diffTemp.add(diffTempis);
               
               
           }
           
           for (int i = 0; i < country.size(); ++i){
           
           list = list + "<tr><td>" + (i + 1) + "</td><td>" +  country.get(i) + "</td><td>" + temp1.get(i) + "</td><td>" + temp2.get(i) + "</td><td>" + diffTemp.get(i) + "</td></tr>";
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
   public String getListCountryPop() {
       // Create the ArrayList of LGA objects to return
       ArrayList<String> country = new ArrayList<String>();
       ArrayList<String> temp1 = new ArrayList<String>();
       ArrayList<String> temp2 = new ArrayList<String>();
       ArrayList<String> diffTemp = new ArrayList<String>();


       // Setup the variable for the JDBC connection
       Connection connection = null;
        String list = "<tr><th>Rank</th><th>State</th><th>Population at Start Year</th><th>Population at end year</th><th>Difference in Popluation</th></tr>";

       try {
           // Connect to JDBC data base
           connection = DriverManager.getConnection(JDBCConnectionClimate.DATABASE);


           // Prepare a new SQL Query & Set a timeout
           Statement statement = connection.createStatement();
           statement.setQueryTimeout(30);

            
           // The Query
           String query = "SELECT C.CountryName, AVG(CASE WHEN CTO.Year = " + startYearSelected 
           + " THEN CTO.Population END) AS avgStartYearTemp, AVG(CASE WHEN CTO.Year = " + startYearSelected + " + " + timeperiodSelected 
           + " THEN CTO.Population END) AS avgEndYearTemp, ABS(AVG(CASE WHEN CTO.Year = " + startYearSelected + " + " + timeperiodSelected 
           + " THEN CTO.Population END) - AVG(CASE WHEN CTO.Year = " + startYearSelected 
           + " THEN CTO.Population END)) AS tempDifference FROM CountryTempObservation CTO JOIN Country C ON CTO.CountryCode = C.CountryCode WHERE CTO.Year IN (" 
           + startYearSelected + ", " + startYearSelected + " + " + timeperiodSelected + ") GROUP BY C.CountryCode, C.CountryName HAVING AVG(CASE WHEN CTO.Year = " 
           + startYearSelected + " THEN CTO.Population END) IS NOT NULL AND AVG(CASE WHEN CTO.Year = " + startYearSelected + " + " + timeperiodSelected 
           + " THEN CTO.Population END) IS NOT NULL AND (C.CountryName = '" + countryNameSelected + "' OR tempDifference >= (SELECT ABS(AVG(CASE WHEN CTO.Year = " 
           + startYearSelected + " + " + timeperiodSelected + " THEN CTO.Population END) - AVG(CASE WHEN CTO.Year = " + startYearSelected 
           + " THEN CTO.Population END)) FROM CountryTempObservation CTO JOIN Country C ON CTO.CountryCode = C.CountryCode WHERE C.CountryName = '" 
           + countryNameSelected + "' AND CTO.Year IN (" + startYearSelected + ", " + startYearSelected + " + " + timeperiodSelected 
           + ") GROUP BY C.CountryCode, C.CountryName)) ORDER BY tempDifference ASC LIMIT " + amountsimilarSelected + ";";

          
           // Get Result
           ResultSet results = statement.executeQuery(query);
            
         
           // Process all of the results
           while (results.next()) {
               String staten  = results.getString("CountryName");
               
               String temp1is  = results.getString("avgStartYearTemp");
               String temp2is = results.getString("avgEndYearTemp");
               String diffTempis  = results.getString("tempDifference");
               ;

               // Add the lga object to the array
               
               
               country.add(staten);
               temp1.add(temp1is);
               temp2.add(temp2is);
               diffTemp.add(diffTempis);
               
               
           }
           
           for (int i = 0; i < country.size(); ++i){
           
           list = list + "<tr><td>" + (i + 1) + "</td><td>" +  country.get(i) + "</td><td>" + temp1.get(i) + "</td><td>" + temp2.get(i) + "</td><td>" + diffTemp.get(i) + "</td></tr>";
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
           


query = "SELECT S.State, " +
        "AVG(CASE WHEN S.Year = " + startYearSelected + " THEN S.AvgTemp END) AS avgStartYearTemp, " +
        "AVG(CASE WHEN S.Year = " + startYearSelected + " + " + timeperiodSelected + " THEN S.AvgTemp END) AS avgEndYearTemp, " +
        "ROUND(ABS(AVG(CASE WHEN S.Year = " + startYearSelected + " + " + timeperiodSelected + " THEN S.AvgTemp END) - AVG(CASE WHEN S.Year = " 
        + startYearSelected + " THEN S.AvgTemp END)), 3) AS tempDifference " +
        "FROM StateTempObservation S " +
        "JOIN Country C ON S.Country = C.CountryCode " +
        "WHERE S.Year IN (" + startYearSelected + ", " + startYearSelected + " + " + timeperiodSelected + ") " +
        "GROUP BY S.State " +
        "HAVING AVG(CASE WHEN S.Year = " + startYearSelected + " THEN S.AvgTemp END) IS NOT NULL " +
        "AND AVG(CASE WHEN S.Year = " + startYearSelected + " + " + timeperiodSelected + " THEN S.AvgTemp END) IS NOT NULL " +
        "AND tempDifference >= (SELECT ROUND(ABS(AVG(CASE WHEN S.Year = " + startYearSelected + " + " + timeperiodSelected + " THEN S.AvgTemp END) - AVG(CASE WHEN S.Year = " 
        + startYearSelected + " THEN S.AvgTemp END)), 3) " +
        "FROM StateTempObservation S " +
        "WHERE S.State = '" + stateNameSelected + "' " +
        "AND S.Year IN (" + startYearSelected + ", " + startYearSelected + " + " + timeperiodSelected + ") " +
        "GROUP BY S.State) " +
        "ORDER BY tempDifference " + sortBySelected + " " +
        "LIMIT " + amountsimilarSelected + ";";
           
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
          String query = "SELECT CTO.City, AVG(CASE WHEN CTO.Year = " + startYearSelected + " THEN CTO.AvgTemp END) AS avgStartYearTemp, AVG(CASE WHEN CTO.Year = " 
                + startYearSelected + " + " + timeperiodSelected + " THEN CTO.AvgTemp END) AS avgEndYearTemp, ROUND(ABS(AVG(CASE WHEN CTO.Year = " + startYearSelected 
                + " + " + timeperiodSelected + " THEN CTO.AvgTemp END) - AVG(CASE WHEN CTO.Year = " + startYearSelected 
                + " THEN CTO.AvgTemp END)), 5) AS tempDifference FROM CityTempObservation CTO JOIN Country C ON CTO.CountryCode = C.CountryCode WHERE CTO.Year IN (" 
                + startYearSelected + ", " + startYearSelected + " + " + timeperiodSelected + ") GROUP BY CTO.City HAVING AVG(CASE WHEN CTO.Year = " + startYearSelected 
                + " THEN CTO.AvgTemp END) IS NOT NULL AND AVG(CASE WHEN CTO.Year = " + startYearSelected + " + " + timeperiodSelected 
                + " THEN CTO.AvgTemp END) IS NOT NULL AND (CTO.City = '" + cityNameSelected + "' OR tempDifference >= (SELECT ROUND(ABS(AVG(CASE WHEN CTO.Year = " 
                + startYearSelected + " + " + timeperiodSelected + " THEN CTO.AvgTemp END) - AVG(CASE WHEN CTO.Year = " + startYearSelected 
                + " THEN CTO.AvgTemp END)), 5) FROM CityTempObservation CTO JOIN Country C ON CTO.CountryCode = C.CountryCode WHERE CTO.City = '" + cityNameSelected 
                + "' AND CTO.Year IN (" + startYearSelected + ", " + startYearSelected + " + " + timeperiodSelected + ") GROUP BY CTO.City)) ORDER BY tempDifference " 
                + sortBySelected + " LIMIT " + amountsimilarSelected + ";";

          
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



