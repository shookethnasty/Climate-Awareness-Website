package app;

import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;

public class App {

    // Default port for local development
    public static final int DEFAULT_PORT = 7001;
    public static final String CSS_DIR = "css/";
    public static final String IMAGES_DIR = "images/";

    public static void main(String[] args) {

        // Use PORT from environment (Render) or fallback to local default
        String portEnv = System.getenv("PORT");
        int port = (portEnv != null) ? Integer.parseInt(portEnv) : DEFAULT_PORT;

        // Start Javalin server
        Javalin app = Javalin.create(config -> {
            config.registerPlugin(new RouteOverviewPlugin("/help/routes"));

            // Static files
            config.addStaticFiles(CSS_DIR);
            config.addStaticFiles(IMAGES_DIR);
        }).start(port);

        // Configure routes
        configureRoutes(app);
    }


    public static void configureRoutes(Javalin app) {

        // Health check (important for Render)
        app.get("/healthz", ctx -> ctx.result("OK"));

        // GET Pages
        app.get(PageIndex.URL, new PageIndex());
        app.get(PageMission.URL, new PageMission());
        app.get(PageST2A.URL, new PageST2A());
        app.get(PageST2B.URL, new PageST2B());
        app.get(PageST3A.URL, new PageST3A());
        app.get(PageST3B.URL, new PageST3B());

        // POST Pages
        app.post(PageST2A.URL, new PageST2A());
        app.post(PageST2B.URL, new PageST2B());
        app.post(PageST3A.URL, new PageST3A());
        app.post(PageST3B.URL, new PageST3B());
    }
}
