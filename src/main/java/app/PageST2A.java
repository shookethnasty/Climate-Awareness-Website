package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import io.javalin.http.Context;
import io.javalin.http.Handler;

/**
 * Example Index HTML class using Javalin
 * <p>
 * Generate a static HTML page using Javalin
 * by writing the raw HTML into a Java String object
 *
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class PageST2A implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page2A.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Head information
        html = html + "<head>" + 
               "<title>Subtask 2.1</title>";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='PagestA.css' />";
        html = html + "</head>";
        html = html + """
            <style>


            </style>
        """;
 
        // Add the body
        html = html + "<body style = 'background-colour: #e8edf1dc;'>";

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

        
        html = html + "<div style='position: absolute; top: 300px; left: 300px;'><b><i>Please select all desired filters then press 'Show Data'.</i></b></div>";
        html = html + """
     <div style='padding-left: 100px; padding-right: 10px;'>
        <div style='float: left; width: 100%; padding-left: 50px; padding-right: 50px; position: absolute; left: 150px; top 350px'>
            <h3><u>Filters</u></h3>
    
            <form action='/page2A.html' id='population-form' method='post'>
                <label for='view-select'>View by:</label>
                <select id='view-select' name='regionOption'>
                    <option value='countries'>Countries</option>
                    <option value='world'>World</option>
                </select>


    
                <label for='start-year-select'>Start Year:</label>
                <select id='start-year-select' name='startingyearValue'>
                    <!-- Populate with available start years from the backend -->
                    """;

            for (int i = 1750; i <= 2013; ++i) {
                html = html + "<option value='" + i + "'>" + i + "</option>";
            }

            html += """
                    <!-- Add more options based on available data -->
                </select>
                
                
                <label for='end-year-select'>End Year:</label>
                <select id='end-year-select' name='endingyearValue'>
                    <!-- Populate with available end years from the backend -->
                    """;
            for (int i = 1750; i <= 2013; ++i) {
                html = html + "<option value='" + i + "'>" + i + "</option>";
            }

            html = html + """
                    <!-- Add more options based on available data -->
                </select>
                
            <label for='view-select'>Criterion:</label>
                <select id='view-select' name='criteria'>
                    <option value='temperature'>Temperature</option>
                    <option value='population'>Population</option>
                </select>

            <label for='view-select'>Sort:</label>
                <select id='view-select' name='sorting'>
                    <option value='ascending'>Ascending</option>
                    <option value='descending'>Descending</option>
                </select>

            <button style='top: 50%; padding: 10px;' type='ShowData' form='population-form' value='Submitted'>Show Data</button>
                
            </form>
            
    </div>
                """;

    
        html = html + """
            
        
            <br>
        """;

        String countryorWorld = context.formParam("regionOption");
        String startYear = context.formParam("startingyearValue");
        String endYear = context.formParam("endingyearValue");
        String sort = context.formParam("sorting"); 
        String criterion = context.formParam("criteria");

        System.out.println(countryorWorld);
        html = html + "<div class='tableM'>";
        if (countryorWorld != null && countryorWorld.equals("countries")){
            displayCountryTable(startYear, endYear, sort, criterion);
            String countryTable = displayCountryTable(startYear, endYear, sort, criterion);
            html = html + countryTable + """
        
       
    <br>
    <br>     
                
    """;
        }
        else if (countryorWorld != null && countryorWorld.equals("world")){
            displayworldtable(startYear, endYear, sort, criterion);
            String worldTable = displayworldtable(startYear, endYear, sort, criterion);

            html = html + worldTable + """
            
        
    <br>
    <br>
                
    """;
        }
        html = html + "</div></div>";

        // Footer
        
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

    public String displayCountryTable(String startyear, String endyear, String sort,String criterion) {
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
            
            if(criterion == null) {
                criterion = "Null";
            }

            if(sort == null) {
                sort = "Null";
            }
            
            String order = "";
            String temorpop = "";
            String ascDesc = "";
 
            // The Query
            String query = "";
            
            if (criterion.equals("temperature")){
                temorpop = """
                    cto_start.AvgTemp AS StartYearAvgTemp,
                    cto_end.AvgTemp AS EndYearAvgTemp,
                    """;
                order = """
                        StartYearAvgTemp
                        """;
            }
            else if (criterion.equals("population")) {
                temorpop = """
                    cto_start.Population AS StartYearPopulation,
                    cto_end.Population AS EndYearPopulation,
                    """;
                order = """
                        StartYearPopulation
                        """;
            }
            
            // Add sorting conditions
            if (sort != null && sort.equals("ascending")){
                ascDesc = " ASC;";
            } else if (sort != null && sort.equals("descending")){
                ascDesc = " DESC;";
            }

            query = """
            SELECT
            c.CountryName,
            """ + temorpop + """
            ((cto_end.AvgTemp * (cto_end.Population - cto_start.Population)) - (cto_start.AvgTemp * cto_start.Population)) /
            (SQRT((cto_end.AvgTemp * cto_end.AvgTemp) - (cto_start.AvgTemp * cto_start.AvgTemp)) *
            SQRT(((cto_end.Population - cto_start.Population) * (cto_end.Population - cto_start.Population)) - (cto_start.Population * cto_start.Population)))
            AS Correlation    
            FROM
            CountryTempObservation cto_start
            JOIN
            CountryTempObservation cto_end ON cto_start.CountryCode = cto_end.CountryCode
            JOIN
            Country c ON c.CountryCode = cto_start.CountryCode
            WHERE
            cto_start.Year = """ + startyear + " AND cto_end.Year = " + endyear + " ORDER BY " + order + ascDesc;

                        
                    
                System.out.println(query);   

        //get Result 
        ResultSet results = statement.executeQuery(query);
        
        //creating seperate arraylist to store rows of each column
        List<String> countryname = new ArrayList<>();
        List<String> temperatureorpopulationstartyear = new ArrayList<>();
        List<String> temperatureorpopulationendyear = new ArrayList<>();
        List<String> corelation = new ArrayList<>();


        //Process all the results 
        while (results.next()){
            String theCountryName = results.getString("CountryName");
            String thecorelation= results.getString("Correlation");
            
            corelation.add(thecorelation);
            countryname.add(theCountryName);
            

            if(criterion.equals("temperature")) {
                String tempyear = results.getString("StartYearAvgTemp");
                temperatureorpopulationstartyear.add(tempyear);
                String popyear = results.getString("EndYearAvgTemp");
                temperatureorpopulationendyear.add(popyear);
            }

            else if(criterion.equals("population")) {
                String tempyear = results.getString("StartYearPopulation");
                temperatureorpopulationstartyear.add(tempyear);
                String popyear = results.getString("EndYearPopulation");
                temperatureorpopulationendyear.add(popyear);

            }

        }
        
        html = html + """
                <table>
                <tr><th>Country Name</th>""";
                
        if(criterion.equals("temperature")) {
            html = html + "<th> Average Temperature(Start Year)</th> <th>Average Temperature(End Year)"; 
        } 

        else {
            html = html +"<th> Population(StartYear) </th> <th> Population(End Year) </th>";
        }

        html += """
                <th>Correlation</tr>
                """;

                for (int i = 0; i < countryname.size(); i++) {
                    html += "<tr>";
                    html += "<td>" + countryname.get(i) + "</td>";
                    html += "<td>" + temperatureorpopulationstartyear.get(i) + "</td>";
                    html += "<td>" + temperatureorpopulationendyear.get(i) + "</td>";
                    html += "<td>" + corelation.get(i) + "</td>";
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
    public String displayworldtable(String startyear, String endyear, String sort,String criterion) {
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
            
            if(criterion == null) {
                criterion = "Null";
            }

            if(sort == null) {
                sort = "Null";
            }
            
            String order = "";
            String temorpop = "";
            String ascDesc = "";
 
            // The Query
            String query = "";
            
            if (criterion.equals("temperature")){
                temorpop = """
                    AVG(cto.AvgTemp) AS AverageTemperature,
                    """;
                order = """
                        AverageTemperature
                        """;
            }
            else if (criterion.equals("population")) {
                temorpop = """
                    SUM(cto.Population) AS Population,
                    """;
                order = """
                        Population
                        """;
            }
            
            // Add sorting conditions
            if (sort != null && sort.equals("ascending")){
                ascDesc = " ASC;";
            } else if (sort != null && sort.equals("descending")){
                ascDesc = " DESC;";
            }

            query = "SELECT "
            + "gyt.Year, "
            + temorpop
            + "((SUM(cto.AvgTemp * cto.Population) - SUM(cto.AvgTemp) * SUM(cto.Population) / COUNT(cto.Year)) / "
            + "SQRT((SUM(cto.AvgTemp * cto.AvgTemp) - (SUM(cto.AvgTemp) * SUM(cto.AvgTemp) / COUNT(cto.Year))) * "
            + "(SUM(cto.Population * cto.Population) - (SUM(cto.Population) * SUM(cto.Population) / COUNT(cto.Year))))) "
            + "AS Correlation "
            + "FROM "
            + "GlobalYearlyTemp gyt "
            + "LEFT JOIN "
            + "CountryTempObservation cto ON cto.Year = gyt.Year "
            + "WHERE "
            + "gyt.Year IN (" + startyear + ", " + endyear + ") "
            + "GROUP BY "
            + "gyt.Year "
            + "ORDER BY "
            + order + ascDesc;

                        
                    
                System.out.println(query);   

        //get Result 
        ResultSet results = statement.executeQuery(query);
        
        //creating seperate arraylist to store rows of each column
        List<String> Year = new ArrayList<>();
        List<String> temperatureorpopulations = new ArrayList<>();
        List<String> corelation = new ArrayList<>();


        //Process all the results 
        while (results.next()){
            String year = results.getString("Year");
            String thecorelation= results.getString("Correlation");
            
            corelation.add(thecorelation);
            Year.add(year);
            

            if(criterion.equals("temperature")) {
                String tempyear = results.getString("AverageTemperature");
                temperatureorpopulations.add(tempyear);
                ;
            }

            else if(criterion.equals("population")) {
                String population = results.getString("Population");
                temperatureorpopulations.add(population);

            }

        }
        
        html = html + """
                <table>
                <tr><th>Year</th>""";
                
        if(criterion.equals("temperature")) {
            html = html + "<th>Average Temperature(for start and end year)"; 
        } 

        else {
            html = html +"<th> Population(population for start and end year)</th>";
        }

        html += """
                <th>Correlation</tr>
                """;

                for (int i = 0; i < Year.size(); i++) {
                    html += "<tr>";
                    html += "<td>" + Year.get(i) + "</td>";
                    html += "<td>" + temperatureorpopulations.get(i) + "</td>";
                    html += "<td>" + corelation.get(i) + "</td>";
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

}





