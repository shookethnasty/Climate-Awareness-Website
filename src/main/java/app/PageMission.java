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
public class PageMission implements Handler {


   // URL of this page relative to http://localhost:7001/
   public static final String URL = "/mission.html";


   @Override
   public void handle(Context context) throws Exception {
       // Create a simple HTML webpage in a String
       String html = "<html>";


       // Add some Head information
       html = html + "<div class='head'>" +
              "<title>Our Mission</title>";


       // Add some CSS (external file)
       html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
       html = html + "</div>";


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
               <h1>Our Mission</h1>
           </div>
       """;


       // Add Div for page Content
       html = html + "<div class='content'>";


       // Add HTML for the page content
      


       // This example uses JDBC to lookup the LGAs
      


       // Next we will ask this *class* for the LGAs
      


       // Add HTML for the LGA list
       html = html + "<div style='top: 100px'><h2>Our Mission and Target Audience</h2></div>";

       ArrayList<String> Personas = getPersonas();
       // Finally we can print out all of the LGAs
       html = html + "<div class='persona1'>";
       html = html + "<p>By creating this program, we aim to assist users to view data regarding temperature based on specific locations, and display the everchaing population statistics of certain countries. By catering to 3 key peronas, we belive we are able to assist a wide range of individuals no matter how in depth the information they require. <br> <br><b>Our first persona is as follows:</b></p>";
       html = html + "<br><br><br><br>";
       html = html + "<p>" + Personas.get(0) + "</p>";
       html = html + "<img src=persona1.png style='position: absolute; left 70%; width: 100px; height: 100px; top: 130px'>";
       
       html = html + "<p><b>Our second persona:</b></p>";
       html = html + "<br><br><br><br>";
       html = html + "<p>" + Personas.get(1) + "</p>";
       html = html + "<img src=persona2.png style='position: absolute; left 70%; width: 100px; height: 100px; top: 380px'>";
       
       html = html + "<p><b>Our third and final persona</b></p>";
       html = html + "<br><br><br><br>";
       html = html + "<p>" + Personas.get(2) + "</p>";
       html = html + "<img src=persona3.png style='position: absolute; left 70%; width: 100px; height: 100px; top: 635px'>";
       html = html + "</div>";
       // Finish the List HTML
       html = html + "</ul>";




       // Close Content div
       html = html + "</div>";
      
       // Footer
       html = html + """
           <div class='footer'>
               <p>Creators: Alex Riippa and Harshit Chordiya</p>
           </div>
       """;


       // Finish the HTML webpage
       html = html + "</body>" + "</html>";
      


       // DO NOT MODIFY THIS
       // Makes Javalin render the webpage
       context.html(html);
   }
   public ArrayList<String> getPersonas() {
       // Create the ArrayList of LGA objects to return
       ArrayList<String> Personas = new ArrayList<String>();


       // Setup the variable for the JDBC connection
       Connection connection = null;


       try {
           // Connect to JDBC data base
           connection = DriverManager.getConnection(JDBCConnectionClimate.DATABASE);


           // Prepare a new SQL Query & Set a timeout
           Statement statement = connection.createStatement();
           statement.setQueryTimeout(30);


           // The Query
           String query = "SELECT PersonaDesc FROM Personas;";
          
           // Get Result
           ResultSet results = statement.executeQuery(query);


           // Process all of the results
           while (results.next()) {
               String name16  = results.getString("PersonaDesc");


               // Add the lga object to the array
               Personas.add(name16);
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
       return Personas;
   }

}



