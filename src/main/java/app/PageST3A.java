package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import kotlin.coroutines.ContinuationInterceptor.Key;

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

public class PageST3A implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page3A.html";
    
    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        
        String html = "<html>";

        // Add some Head information
        html = html + "<head>" + 
               "<title>Subtask 3.1</title>";

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
                <h1>Population and Temperature Changes</h1>
            </div>
        """;

        // Add Div for page Content
        html = html + "<div class='content'>";

        // // Add HTML for the page content
        // html = html + """
        //     <p>Subtask 3.A page content</p>
        //     """;

        ArrayList<String> CountryNames = getCountryNames();
        
        
        html = html+ """
                    <div style ='float: left; width: 25%; padding-left: 150px; padding-right: 50px; height: auto; position: absolute; left: -110px; top: 175px;'>
                    <center><h3><u>Filters</u></h3></center>
                    </div>
                    <form action = '/page3A.html' method ='post'>
                    """;
        html = html +"""
            
            <div style='position: absolute; top: 220px; left: 165px;'><h4><b>Country</b></h4></div>
            <div class='dropdownCountry'>
            <select id = 'countryDropdown' name = 'Countryname'>
                        <option value ='Sel'>-- Select Country --</option>
                                """;
            for(String name : CountryNames){
                html = html + "<option value='" + name + "'>" + name + "</option>";
            }

        html = html + """
                </select></div>
                """;
        html = html + """
                </select>
                """;
        html = html + """
                <div class='SelectRegion'>
                <h4><b>Select Region</b></h4>
                <input type = 'radio' id ='cityname' name = 'citystateCountry' value ='City' >
                <label for = 'cityname'>City</label><br>
                <input type ='radio'  id ='statename' name = 'citystateCountry' value ='State' >
                <label for = 'statename'>State</label><br>
                <input type ='radio'  id ='countryname' name = 'citystateCountry' value ='Country' >
                <label for = 'statename'>Country</label><br>
                </div>
                <br>
                """;
        
        html = html + """
                
                """;
        
        //storing user's choice of country,state or city in a string variable 
        String choosecountry_state_city = context.formParam("citystateCountry");
        // String choose_Country = context.formParam("")
        
        
        
        System.out.println("Country state or city " + choosecountry_state_city);

        //Fix for null error issue 
        if (choosecountry_state_city == null){
            choosecountry_state_city = "Didnt work";
        }
        //three forms for city,state or country based on the users choice 
        //city form
        
            
            html = html + """
                    

                    <br>
                    <div class='dateselectStart3'>
                    <h4><b>Start Year(s)</b></h4>                     
                    <input type='text' id='starting_Year' name='starting_Year' required title ='Please enter a valid string' placeholder='1880, 1990, 2000' style='width: 80px;'>
                    <br><br></div>
                    <div class='dateselectEnd3'>
                    <h4><b>Time Period</b></h4> 
                    <input type='number' id='time_period' name='TimePeriod' required title = 'Please enter a valid number' placeholder='10' size ='4' style='width: 80px;'>
                    <br><br></div>
                    <div class='tempRange'>
                    <label for='tempChange'><b>Temperature Range:</b></label><br>
                    <input type='number' name='minTempChange' id='minTempChange' placeholder='Minimum Temp'>
                    <input type='number' name='maxTempChange' id='maxTempChange' placeholder='Maximum Temp'><br><br>
                    
                    </div>
                    """;
            
            html = html + "<div style='position: absolute; top: 175px; left: 400px;'><b><i>Please Select a Country, then a region within the country. Then add a start year and time period as well as the criterion and how you would like to sort.<br>Then add your average temperature range and press 'Apply Filters'.</i></b></div>";


            html = html + "<div style='position: absolute; top: 245px; left: 440px'><b>Sort BY Criterion:</b></div>";
            html = html +"<div class = 'dropdownState'>";
            html = html + "<select id = 'criterion_select' name = 'criterion_select'>";
            html = html + "<option>Average Temperature</option>";
            html = html + "<option>Difference in Average Temperature</option>";
            
            html = html + "</select>";
            html = html + "</div>";
            html = html + "<br>";
            
            html = html + "<div class='dropdownSortsL'><b>Sort BY: </b></div>";
            html = html + "<div class='dropdownSorts'>";
            html = html + "<select id = 'asc_desc_drop' name = 'asc_desc_drop'>";
            html = html + "<option value='ascending'>Ascending</option>";
            html = html + "<option value='descending'>Descending</option>";
            html = html + "</select>";
            html = html + "</div>";
            html = html + "<div class='buttonsubmit3'>";
            html = html + "<input type='submit' value='Apply Filters'>";
            html = html + "</div>";
            html = html + "</form>";
            
                            
        String startingyears = context.formParam("starting_Year");
        String timePeriod = context.formParam("TimePeriod");
        String sort = context.formParam("asc_desc_drop");
        String criterion2 = context.formParam("criterion_select");
        String country_name_dropdown = context.formParam("Countryname");
        String tempChangemin = context.formParam("minTempChange");
        String tempChangemax = context.formParam("maxTempChange");
        String popChangemax = context.formParam("maxpopChange");
        String popChangemin = context.formParam("minpopChange");
        
        System.out.println("Country Name " + country_name_dropdown);
        System.out.println("Criterion " + criterion2);
        
        //Fix for null error issue 
        // if (startingyears == null && timePeriod == null && sort == null && criterion == null && country_name_dropdown == null){
        //     startingyears = "Null";
        //     timePeriod = "Null";
        //     sort = "Null";
        //     criterion = "Null";
        //     country_name_dropdown = "Null";
        // }
                   html = html + """
                    </div>
                    <div>
                    """;
        String finaltable = "";
        if (choosecountry_state_city != null && choosecountry_state_city.equals("City")) {
            finaltable = displayCityTable(startingyears, timePeriod, sort, criterion2, country_name_dropdown, tempChangemin, tempChangemax);
        } else if (choosecountry_state_city != null && choosecountry_state_city.equals("State")) {
            finaltable = displayStateTable(startingyears, timePeriod, sort, criterion2, country_name_dropdown, tempChangemin, tempChangemax);
        }
           else if (choosecountry_state_city != null && choosecountry_state_city.equals("Country")) {
            finaltable = displaycountryTable(startingyears, timePeriod, sort, criterion2,tempChangemin, tempChangemax);
        }

        
        html = html + "<div class='tableM'>";

         html = html + finaltable;
         
                 
        html = html + "</div>";

        // Close Content div
        html = html + "</div></div>";
        // // Footer
        // html = html + """
        //     <div class='footer'>
        //         <p></p>
        //     </div>
        // """;

        // Finish the HTML webpage
        html = html + "</body>" + "</html>"; 
 

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

    public ArrayList<String> getCountryNames() {
        // Create the ArrayList of Country objects to return
        ArrayList<String> CountryNames = new ArrayList<String>();
 
 
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
                CountryNames.add(name16);
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
        return CountryNames;
    }
    public String displayCityTable(String startingyears, String timeperiod, String sort, String criterion2, String country_name_dropdown, String tempChangemin, String tempChangemax) {
        // Setup the variable for the JDBC connection
        Connection connection = null;

        // create variable to display in table 
        String html = "";

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(JDBCConnectionClimate.DATABASE);
 
 
            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            
            if(criterion2 == null) {
                criterion2 = "Null";
            }

            if(sort == null) {
                sort = "Null";
            }
            
            String temportempdiff = "";
            String ascDesc = "";
 
            // The Query
            String query = "";
            
            if (criterion2.equals("Average Temperature")){
                temportempdiff = """
                    AVG(t2.AvgTemp) AS AvgTempAvg, 
                    AVG(t2.MinTemp) AS MinTempAvg, 
                    AVG(t2.MaxTemp) AS MaxTempAvg
                    """;
            }
            else if (criterion2.equals("Difference in Average Temperature")) {
                temportempdiff = """
                    AVG(t2.AvgTemp) - AVG(t1.AvgTemp) AS AvgTempDiff, 
                    AVG(t2.MinTemp) - AVG(t1.MinTemp) AS MinTempDiff, 
                    AVG(t2.MaxTemp) - AVG(t1.MaxTemp) AS MaxTempDiff
                    """;
            }
            
            // Add sorting conditions
            if (sort != null && sort.equals("ascending")){
                ascDesc = " ASC;";
            } else if (sort != null && sort.equals("descending")){
                ascDesc = " DESC;";
            }

            query = """
                    SELECT t1.City, 
                    t1.Year AS StartYear, 
                    """ + temportempdiff + """ 
                    FROM CityTempObservation t1 
                    JOIN CityTempObservation t2 
                    ON t2.City = t1.City 
                    AND t2.Year >= t1.Year 
                    AND t2.Year <= t1.Year +""" + timeperiod + """
                     JOIN Country c 
                    ON c.CountryCode = t1.CountryCode 
                    WHERE t1.Year IN (""" + startingyears + """
                    ) 
                    AND c.CountryName = '""" + country_name_dropdown + "'" + """
                        
                    AND t1.AvgTemp >= """ + tempChangemin + " " +""" 
                    AND t1.AvgTemp <= """ + tempChangemax + " " +"""
                    GROUP BY t1.City, t1.Year 
                    ORDER BY t1.City """ + ascDesc + """
                        """;
                    
                        
                    
               System.out.println(query);   
                

        //get Result 
        ResultSet results = statement.executeQuery(query);
        
        //creating seperate arraylist to store rows of each column
        List<String> city = new ArrayList<>();
        List<String> startyear = new ArrayList<>();
        List<String> avgtemperatureordiffrenceintempavg = new ArrayList<>();
        List<String> avgtemperatureordiffrenceintempmin = new ArrayList<>();
        List<String> avgtemperatureordiffrenceintempmax = new ArrayList<>();
        //Process all the results 
        while (results.next()){
            String thecity = results.getString("City");
            String startyears= results.getString("Startyear");
            
            city.add(thecity);
            startyear.add(startyears);
            

            if(criterion2.equals("Average Temperature")) {
                String tempyear = results.getString("AvgTempAvg");
                avgtemperatureordiffrenceintempavg.add(tempyear);
                String mintemp = results.getString("MinTempAvg");
                avgtemperatureordiffrenceintempmin.add(mintemp);
                String maxtemp = results.getString("MaxTempAvg");
                avgtemperatureordiffrenceintempmax.add(maxtemp);
            }

            else if(criterion2.equals("Difference in Average Temperature")) {
                String tempyear = results.getString("AvgTempDiff");
                avgtemperatureordiffrenceintempavg.add(tempyear);
                String mintemp = results.getString("MinTempDiff");
                avgtemperatureordiffrenceintempmin.add(mintemp);
                String maxtemp = results.getString("MaxTempDiff");
                avgtemperatureordiffrenceintempmax.add(maxtemp);
            }

        }
        
        html = html + """
                <table class='table'>
                <tr><th>City Name</th>""";
        html = html + """
                <th>Year</th>
                """;

                
        if(criterion2.equals("Average Temperature")) {
            html = html + "<th> Average Temperature Avg</th> <th>Minimum Temperature Avg </th> <th>Maximum Temperature Avg</th>"; 
        } 

        else {
            html = html +"<th> Average Temperature Diff </th> <th> Minimum Temperature Diff </th> <th> Maximum Temperature Diff </th>";
        }

        html += """
                </tr>
                """;

                for (int i = 0; i < city.size(); i++) {
                    html += "<tr>";
                    html+= "<td>" + city.get(i) + "</td>";
                    html += "<td>" + startyear.get(i) + "</td>";
                    html += "<td>" + avgtemperatureordiffrenceintempavg.get(i) + "</td>";
                    html += "<td>" + avgtemperatureordiffrenceintempmin.get(i) + "</td>";
                    html += "<td>" + avgtemperatureordiffrenceintempmax.get(i) + "</td>";
                     html += "</tr>";
                }

                 // Closing the HTML table
                    html += "</table>";        

            // Close the statement because we are done with it
            statement.close();
                
                
        }catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        }finally {
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
        return html;
        
}
    public String displayStateTable(String startingyears, String timeperiod, String sort, String criterion2, String country_name_dropdown, String tempChangemin, String tempChangemax) {
        // Setup the variable for the JDBC connection
        Connection connection = null;

        // create variable to display in table 
        String html = "";

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(JDBCConnectionClimate.DATABASE);
 
 
            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            
            if(criterion2 == null) {
                criterion2 = "Null";
            }

            if(sort == null) {
                sort = "Null";
            }
            
            String temportempdiff = "";
            String ascDesc = "";
 
            // The Query
            String query = "";
            
            if (criterion2.equals("Average Temperature")){
                temportempdiff = """
                    AVG(t2.AvgTemp) AS AvgTempAvg, 
                    AVG(t2.MinTemp) AS MinTempAvg, 
                    AVG(t2.MaxTemp) AS MaxTempAvg
                    """;
            }
            else if (criterion2.equals("Difference in Average Temperature")) {
                temportempdiff = """
                    AVG(t2.AvgTemp) - AVG(t1.AvgTemp) AS AvgTempDiff, 
                    AVG(t2.MinTemp) - AVG(t1.MinTemp) AS MinTempDiff, 
                    AVG(t2.MaxTemp) - AVG(t1.MaxTemp) AS MaxTempDiff
                    """;
            }
            
            // Add sorting conditions
            if (sort != null && sort.equals("ascending")){
                ascDesc = " ASC;";
            } else if (sort != null && sort.equals("descending")){
                ascDesc = " DESC;";
            }

            query = """
                    SELECT t1.State, 
                    t1.Year AS StartYear, 
                    """ + temportempdiff + """ 
                    FROM StateTempObservation t1 
                    JOIN StateTempObservation t2 
                    ON t2.State = t1.State 
                    AND t2.Year >= t1.Year 
                    AND t2.Year <= t1.Year +""" + timeperiod + """
                     JOIN Country c 
                    ON c.CountryCode = t1.Country 
                    WHERE t1.Year IN (""" + startingyears + """
                    ) 
                    AND c.CountryName = '""" + country_name_dropdown + "'" + """
                        
                    AND t1.AvgTemp >= """ + tempChangemin + " " +""" 
                    AND t1.AvgTemp <= """ + tempChangemax + " " +"""
                    GROUP BY t1.State, t1.Year 
                    ORDER BY t1.State """ + ascDesc + """
                        """;                     
                    
            //    System.out.println(query);   
                

        //get Result 
        ResultSet results = statement.executeQuery(query);
        
        //creating seperate arraylist to store rows of each column
        List<String> state = new ArrayList<>();
        List<String> startyear = new ArrayList<>();
        List<String> avgtemperatureordiffrenceintempavg = new ArrayList<>();
        List<String> avgtemperatureordiffrenceintempmin = new ArrayList<>();
        List<String> avgtemperatureordiffrenceintempmax = new ArrayList<>();
        //Process all the results 
        while (results.next()){
            String thestate = results.getString("State");
            String startyears= results.getString("Startyear");
            
            state.add(thestate);
            startyear.add(startyears);            

            if(criterion2.equals("Average Temperature")) {
                String tempyear = results.getString("AvgTempAvg");
                avgtemperatureordiffrenceintempavg.add(tempyear);
                String mintemp = results.getString("MinTempAvg");
                avgtemperatureordiffrenceintempmin.add(mintemp);
                String maxtemp = results.getString("MaxTempAvg");
                avgtemperatureordiffrenceintempmax.add(maxtemp);
            }

            else if(criterion2.equals("Difference in Average Temperature")) {
                String tempyear = results.getString("AvgTempDiff");
                avgtemperatureordiffrenceintempavg.add(tempyear);
                String mintemp = results.getString("MinTempDiff");
                avgtemperatureordiffrenceintempmin.add(mintemp);
                String maxtemp = results.getString("MaxTempDiff");
                avgtemperatureordiffrenceintempmax.add(maxtemp);
            }

        }
        
        html = html + """
                <table class='table'>
                <tr><th>State Name</th>""";
        html = html + """
                <th>Year</th>
                """;

                
        if(criterion2.equals("Average Temperature")) {
            html = html + "<th> Average Temperature Avg</th> <th>Minimum Temperature Avg </th> <th>Maximum Temperature Avg</th>"; 
        } 

        else {
            html = html +"<th> Average Temperature Diff </th> <th> Minimum Temperature Diff </th> <th> Maximum Temperature Diff </th>";
        }

        html += """
                </tr>
                """;

                for (int i = 0; i < state.size(); i++) {
                    html += "<tr>";
                    html+= "<td>" + state.get(i) + "</td>";
                    html += "<td>" + startyear.get(i) + "</td>";
                    html += "<td>" + avgtemperatureordiffrenceintempavg.get(i) + "</td>";
                    html += "<td>" + avgtemperatureordiffrenceintempmin.get(i) + "</td>";
                    html += "<td>" + avgtemperatureordiffrenceintempmax.get(i) + "</td>";
                     html += "</tr>";
                }

                 // Closing the HTML table
                    html += "</table>";        

            // Close the statement because we are done with it
            statement.close();
                
                
        }catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        }finally {
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
        return html;
        
}
    public String displaycountryTable(String startingyears, String timeperiod, String sort, String criterion2,  String tempChangemin, String tempChangemax) {
        // Setup the variable for the JDBC connection
        Connection connection = null;

        // create variable to display in table 
        String html = "";

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(JDBCConnectionClimate.DATABASE);
 
 
            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            
            if(criterion2 == null) {
                criterion2 = "Null";
            }

            if(sort == null) {
                sort = "Null";
            }
            
            String temportempdiff = "";
            String ascDesc = "";
 
            // The Query
            String query = "";
            
            if (criterion2.equals("Average Temperature")){
                temportempdiff = """
                    AVG(t2.AvgTemp) AS AvgTempAvg,
                    AVG(t2.MinTemp) AS MinTempAvg,
                    AVG(t2.MaxTemp) AS MaxTempAvg
                    """;
            }
            else if (criterion2.equals("Difference in Average Temperature")) {
                temportempdiff = """
                    AVG(t2.AvgTemp) - AVG(t1.AvgTemp) AS AvgTempDiff,
                    AVG(t2.MinTemp) - AVG(t1.MinTemp) AS MinTempDiff,
                    AVG(t2.MaxTemp) - AVG(t1.MaxTemp) AS MaxTempDiff
                    """;
            }
            else if (criterion2.equals("Population")){
                temportempdiff = """
                        AVG(t2.Population) - AVG(t1.Population) AS PopulationDiff,
                        AVG(t1.Population) AS AvgPopulation
                        """;
            }
            
            // Add sorting conditions
            if (sort != null && sort.equals("ascending")){
                ascDesc = " ASC;";
            } else if (sort != null && sort.equals("descending")){
                ascDesc = " DESC;";
            }

            query = """
                    SELECT t1.CountryCode, 
                    t1.Year AS StartYear,
                    """ + temportempdiff + """ 
                    FROM CountryTempObservation t1
                    JOIN CountryTempObservation t2
                    ON t2.CountryCode = t1.CountryCode
                    AND t2.Year >= t1.Year
                    AND t2.Year <= t1.Year + """ + timeperiod + " " + """
                    JOIN Country c
                    ON c.CountryCode = t1.CountryCode
                    WHERE t1.Year IN (""" + startingyears + """
                    )
                    AND t1.AvgTemp >= """ + tempChangemin + " " +""" 
                    AND t1.AvgTemp <= """ + tempChangemax + " " +"""
                    
                    GROUP BY t1.CountryCode, t1.Year
                    ORDER BY t1.CountryCode """ + ascDesc + """
                        """;                     
                    
               System.out.println(query);   
                

        //get Result 
        ResultSet results = statement.executeQuery(query);
        
        //creating seperate arraylist to store rows of each column
        List<String> countrycode = new ArrayList<>();
        List<String> startyear = new ArrayList<>();
        List<String> avgtemperatureordiffrenceintempavg = new ArrayList<>();
        List<String> avgtemperatureordiffrenceintempmin = new ArrayList<>();
        List<String> avgtemperatureordiffrenceintempmax = new ArrayList<>();
        //Process all the results 
        while (results.next()){
            String thecontry = results.getString("CountryCode");
            String startyears= results.getString("Startyear");
            
            countrycode.add(thecontry);
            startyear.add(startyears);            

            if(criterion2.equals("Average Temperature")) {
                String tempyear = results.getString("AvgTempAvg");
                avgtemperatureordiffrenceintempavg.add(tempyear);
                String mintemp = results.getString("MinTempAvg");
                avgtemperatureordiffrenceintempmin.add(mintemp);
                String maxtemp = results.getString("MaxTempAvg");
                avgtemperatureordiffrenceintempmax.add(maxtemp);
            }

            else if(criterion2.equals("Difference in Average Temperature")) {
                String tempyear = results.getString("AvgTempDiff");
                avgtemperatureordiffrenceintempavg.add(tempyear);
                String mintemp = results.getString("MinTempDiff");
                avgtemperatureordiffrenceintempmin.add(mintemp);
                String maxtemp = results.getString("MaxTempDiff");
                avgtemperatureordiffrenceintempmax.add(maxtemp);
            }
             else if(criterion2.equals("Population")) {
                String tempyear = results.getString("AvgPopulation");
                avgtemperatureordiffrenceintempavg.add(tempyear);
                String mintemp = results.getString("PopulationDiff");
                avgtemperatureordiffrenceintempmin.add(mintemp);
                

        }
    }
        
        
        
        html = html + """
                <table class='table'>
                <tr><th>Country Code</th>""";
        html = html + """
                <th>Year</th>
                """;

                
        if(criterion2.equals("Average Temperature")) {
            html = html + "<th> Average Temperature Avg</th> <th>Minimum Temperature Avg </th> <th>Maximum Temperature Avg</th>"; 
        } 

        else if(criterion2.equals("Difference in Average Temperature")){
            html = html +"<th> Average Temperature Diff </th> <th> Minimum Temperature Diff </th> <th> Maximum Temperature Diff </th>";
        }
        else if(criterion2.equals("Population")){
            html = html +"<th> Average Population </th> <th> Average Population Diff </th>";  
        }


        html += """
                </tr>
                """;

                for (int i = 0; i < countrycode.size(); i++) {
                    html += "<tr>";
                    html+= "<td>" + countrycode.get(i) + "</td>";
                    html += "<td>" + startyear.get(i) + "</td>";
                    html += "<td>" + avgtemperatureordiffrenceintempavg.get(i) + "</td>";
                    html += "<td>" + avgtemperatureordiffrenceintempmin.get(i) + "</td>";
                    html += "<td>" + avgtemperatureordiffrenceintempmax.get(i) + "</td>";
                     html += "</tr>";
                }

                 // Closing the HTML table
                    html += "</table>";        

            // Close the statement because we are done with it
            // statement.close();
                
                
        statement.close();
        }catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        }finally {
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
        return html;
        
    }
}
