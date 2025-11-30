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
public class PageIndex implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Header information
        html = html + "<head>" + 
               "<title>Homepage</title>";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
        html = html + "</head>";
html = html + """
    <style>
    body {
        font-family: 'Roboto', sans-serif;
        margin: 0;
        padding: 0;
        background-color: #
    }
    header {
        background-color: #333;
        color: #fff;
        padding: 20px;
        text-align: center;
    }
    header h1 {
        font-size: 36px;
        margin: 0;
        font-weight: 700;
    }
    header h2 {
        font-size: 18px;
        margin: 0;
        font-weight: 400;
        margin-bottom: 20px;
    }
    .topnav {
        overflow: hidden;
        background-color: #f1f1f1;
        text-align: center;
    }
    .topnav a {
        display: inline-block;
        padding: 15px 20px;
        text-decoration: none;
        color: #333;
        font-weight: 700;
        text-transform: uppercase;
    }
    section.hero {
        background-color: #f1f1f1;
        text-align: center;
        padding: 50px 0;
    }
    section.hero h3 {
        font-size: 28px;
        margin-bottom: 20px;
        font-weight: 700;
    }
    section.hero p {
        font-size: 16px;
        margin-bottom: 20px;
    }
    section.hero .btn {
        display: inline-block;
        padding: 12px 24px;
        background-color: #1c8676;
        color: #fff;
        text-decoration: none;
        font-weight: 700;
        text-transform: uppercase;
        border-radius: 4px;
    }
    section.features {
        display: flex;
        justify-content: center;
        align-items: flex-start;
        padding: 50px 0;
        background-color: #1c8676;
    }
    section.features .feature {
        width: 300px;
        margin: 0 20px;
        padding: 20px;
        background-color: #fff;
        text-align: center;
        border-radius: 4px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }
    section.features h4 {
        font-size: 20px;
        margin-bottom: 10px;
        font-weight: 700;
    }
    section.features p {
        font-size: 14px;
        margin-bottom: 0;
    }
    footer {
        background-color: #333;
        color: #fff;
        padding: 20px;
        text-align: center;
    }
    footer p {
        margin: 0;
        font-size: 14px;
    }
</style>
        """;
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
                <a  class='button'href='page3B.html'>Sub Task 3.B</a>
            </div>
        """;

        // Add header content block
        html = html + """
            <header>
        <h1>Welcome to the Climate Data Portal</h1>
        <h2>Exploring Climate Change Trends and Population Data</h2>
    </header>
        """;

        // Add Div for page Content
       

        // Add HTML for the page content
        ArrayList<String> yeardata = getyeardata();
        ArrayList<String> populationdata = getpopulationdata();
        ArrayList<String> creator = getcreatordetails();
        ArrayList<String> yeardataforpop = getglobaltempdata();
        
 
        html = html + """
            <section class='hero'>
        <h3>Discover Climate Change Trends and Population Data</h3>
        <p>Explore temperature changes and population trends across the world over the years.</p>
    </section>

    <section class='features'>
        <div class='feature'>
            <h4>Global Temperature</h4>
            <p>Year Range of available data is: """ + yeardata.get(0) + """
             Years</p>
            <p>StartYear: """ + yeardata.get(1) + """
            </p>
            <p>EndYear: """ + yeardata.get(2) + """
            </p>


        </div>
        <div class='feature'>
            <h4>Global Land Temperature</h4>
            <p>StartYear: """ + yeardataforpop.get(0) + """
            </p>
            <p>EndYear: """ + yeardataforpop.get(1) + """
             <p>
        </div>
        
        <div class='feature'>
            <h4>World Population</h4>
            <p>Year Range of available data is: """ + populationdata.get(2) + """
             Years</p>
            <p>StartYear: """ + populationdata.get(0) + """ 
            </p>
            <p>EndYear: """ + populationdata.get(1) + """
             <p>
        </div>
        
        <div class='feature'>
            <h4>World Population Number</h4>
            <p>StartYear: """ + populationdata.get(3) + """
             People </p>
            <p>EndYear: """ + populationdata.get(4) + """
              People<p>

        
    </section>
            """;
        // Finish the List HTML
        html = html + "</ul>";

        // Close Content div
        html = html + "</div>";

        // Footer
        html = html + """
            <div class='footer'>
                <h4> About Us</h4>
                <p>Creators: """ + creator.get(1) + ", " + creator.get(4) + """
                </p>
                <p>
                email id:  """ + creator.get(2) + ", " + creator.get(5) + """  
                 </p>
            </div>
        """;

        // Finish the HTML webpage
        html = html + "</body>" + "</html>";


        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }


    /**
     * Get the names of the LGAs in the database.
     */
    public ArrayList<String> getLGAs2016() {
        // Create the ArrayList of LGA objects to return
        ArrayList<String> lgas = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(JDBCConnectionCtg.DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM LGA WHERE year='2016'";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                String name16  = results.getString("name");

                // Add the lga object to the array
                lgas.add(name16);
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
        return lgas;
    }

    public ArrayList<String> getyeardata() {
        // Create the ArrayList of LGA objects to return
        ArrayList<String> yeardata = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;
    

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(JDBCConnectionClimate.DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            

            String query = "SELECT COUNT(*) AS TotalYears, MIN(Year) AS StartYear, MAX(Year) AS EndYear FROM GlobalYearlyTemp";

            //get Result 
        ResultSet results = statement.executeQuery(query);

         // Process the result set
            if (results.next()) {
                String totalYears = results.getString("TotalYears");
                String startYear = results.getString("StartYear");
                String endYear = results.getString("EndYear");

                // Add the data to the ArrayList
                yeardata.add(totalYears);
                yeardata.add(startYear);
                yeardata.add(endYear);
            }

            // Close the result set, statement, and connection
            results.close();
            statement.close();
            connection.close();

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
        return yeardata;
    }

    public ArrayList<String> getpopulationdata() {
        // Create the ArrayList of LGA objects to return
        ArrayList<String> populationdata = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null; 
        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(JDBCConnectionClimate.DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            

            String query = 
    "SELECT " +
    "   start.StartYear, " +
    "   end.EndYear, " +
    "   (end.EndYear - start.StartYear + 1) AS TotalYears, " +
    "   SUM(CASE WHEN c.Year = start.StartYear THEN c.Population ELSE 0 END) AS StartYearPopulation, " +
    "   SUM(CASE WHEN c.Year = end.EndYear THEN c.Population ELSE 0 END) AS EndYearPopulation " +
    "FROM " +
    "   CountryTempObservation c " +
    "JOIN " +
    "   (SELECT MIN(Year) AS StartYear FROM CountryTempObservation WHERE Population > 0) start " +
    "JOIN " +
    "   (SELECT MAX(Year) AS EndYear FROM CountryTempObservation WHERE Population > 0) end";





            //get Result 
        ResultSet results = statement.executeQuery(query);

         // Process the result set
            if (results.next()) {
                String startyear = results.getString("StartYear");
                String endYear = results.getString("EndYear");
                String totalYear = results.getString("TotalYears");
                String startyearpopulation = results.getString("Startyearpopulation");
                String endyearpopulation = results.getString("EndYearPopulation");



                // Add the data to the ArrayList
                populationdata.add(startyear);
                populationdata.add(endYear);
                populationdata.add(totalYear);
                populationdata.add(startyearpopulation);
                populationdata.add(endyearpopulation);


            }

            // Close the result set, statement, and connection
            results.close();
            statement.close();
            connection.close();

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
        return populationdata;
    }
       
    
    public ArrayList<String> getcreatordetails() {
        // Create the ArrayList of LGA objects to return
        ArrayList<String> creator = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;
    

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(JDBCConnectionClimate.DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            

            String query = "SELECT * FROM Studentdetails";

            //get Result 
        ResultSet results = statement.executeQuery(query);

         // Process the result set
            while (results.next()) {
                String studentid = results.getString("StudentID");
                String Studentname = results.getString("Name");
                String studentemail = results.getString("Email");

                // Add the data to the ArrayList
                creator.add(studentid);
                creator.add(Studentname);
                creator.add(studentemail);
            }

            // Close the result set, statement, and connection
            results.close();
            statement.close();
            connection.close();

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
        return creator;
    }

    public ArrayList<String> getglobaltempdata() {
        // Create the ArrayList of LGA objects to return
        ArrayList<String> yeardatafortemp = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;
    

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(JDBCConnectionClimate.DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            

            String query = "SELECT " +
    "(SELECT AvgTemp FROM GlobalYearlyTemp ORDER BY Year ASC LIMIT 1) AS FirstYearAvgTemp, " +
    "(SELECT AvgTemp FROM GlobalYearlyTemp ORDER BY Year DESC LIMIT 1) AS LastYearAvgTemp;";


            //get Result 
        ResultSet results = statement.executeQuery(query);

         // Process the result set
            if (results.next()) {
                // String totalYears = results.getString("TotalYears");
                String startYear = results.getString("FirstYearAvgTemp");
                String endYear = results.getString("LastYearAvgTemp");

                // Add the data to the ArrayList
                // yeardata.add(totalYears);
                yeardatafortemp.add(startYear);
                yeardatafortemp.add(endYear);
            }

            // Close the result set, statement, and connection
            results.close();
            statement.close();
            connection.close();

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
        return yeardatafortemp;
    }

            
}


