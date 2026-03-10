package utils;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Custom filter to log all request and response details to a file
 */
public class RequestResponseLogger implements Filter {
    
    private static final Logger logger = LogManager.getLogger(RequestResponseLogger.class);
    private static final String LOG_FILE_PATH = "target/api-logs/api-execution.txt";
    private static PrintWriter writer;
    private static boolean initialized = false;
    
    /**
     * Initialize the log file - called before first test execution
     */
    public static synchronized void initializeLogFile() {
        try {
            // Create directory if it doesn't exist
            Files.createDirectories(Paths.get("target/api-logs"));
            
            // Initialize writer - overwrite existing file (false = no append)
            writer = new PrintWriter(new FileWriter(LOG_FILE_PATH, false), true);
            
            // Write header
            writer.println("=".repeat(80));
            writer.println("API Test Execution Log");
            writer.println("Generated: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            writer.println("=".repeat(80));
            writer.println();
            
            initialized = true;
            logger.info("API execution log initialized: " + LOG_FILE_PATH);
            
        } catch (IOException e) {
            logger.error("Failed to initialize log file", e);
        }
    }
    
    @Override
    public Response filter(FilterableRequestSpecification requestSpec, 
                          FilterableResponseSpecification responseSpec, 
                          FilterContext ctx) {
        
        // Initialize log file on first use (will reset if not initialized)
        if (!initialized) {
            initializeLogFile();
        }
        
        // Log request details
        logRequest(requestSpec);
        
        // Execute the request
        Response response = ctx.next(requestSpec, responseSpec);
        
        // Log response details
        logResponse(response);
        
        return response;
    }
    
    private void logRequest(FilterableRequestSpecification requestSpec) {
        try {
            writer.println("Request method:\t" + requestSpec.getMethod());
            writer.println("Request URI:\t" + requestSpec.getURI());
            writer.println("Proxy:\t\t\t<none>");
            writer.println("Request params:\t" + (requestSpec.getRequestParams().isEmpty() ? "<none>" : requestSpec.getRequestParams()));
            writer.println("Query params:\t" + formatParams(requestSpec.getQueryParams()));
            writer.println("Form params:\t" + (requestSpec.getFormParams().isEmpty() ? "<none>" : requestSpec.getFormParams()));
            writer.println("Path params:\t" + (requestSpec.getPathParams().isEmpty() ? "<none>" : requestSpec.getPathParams()));
            writer.println("Headers:\t\t" + formatHeaders(requestSpec.getHeaders()));
            
            String cookies = requestSpec.getCookies().size() == 0 ? "<none>" : requestSpec.getCookies().toString();
            writer.println("Cookies:\t\t" + cookies);
            writer.println("Multiparts:\t\t<none>");
            
            String body = requestSpec.getBody() != null ? requestSpec.getBody().toString() : "<none>";
            writer.println("Body:");
            if (!body.equals("<none>")) {
                writer.println(body);
            } else {
                writer.println("\t\t\t" + body);
            }
            
        } catch (Exception e) {
            logger.error("Error logging request", e);
        }
    }
    
    private void logResponse(Response response) {
        try {
            writer.println("HTTP/1.1 " + response.getStatusCode() + " " + response.getStatusLine().split(" ", 3)[2]);
            
            // Log response headers
            response.getHeaders().forEach(header -> 
                writer.println(header.getName() + ": " + header.getValue())
            );
            
            // Log response body
            String responseBody = response.getBody().asString();
            writer.println();
            writer.println(responseBody);
            writer.println();
            
        } catch (Exception e) {
            logger.error("Error logging response", e);
        }
    }
    
    private String formatParams(java.util.Map<String, ?> params) {
        if (params == null || params.isEmpty()) {
            return "<none>";
        }
        StringBuilder sb = new StringBuilder();
        params.forEach((key, value) -> sb.append(key).append("=").append(value).append("\n\t\t\t"));
        return sb.toString().trim();
    }
    
    private String formatHeaders(io.restassured.http.Headers headers) {
        if (headers == null || !headers.iterator().hasNext()) {
            return "<none>";
        }
        StringBuilder sb = new StringBuilder();
        headers.forEach(header -> sb.append(header.getName()).append("=").append(header.getValue()).append("\n\t\t\t\t"));
        return sb.toString().trim();
    }
    
    public static void closeLogger() {
        if (writer != null) {
            writer.println("=".repeat(80));
            writer.println("End of API Test Execution Log");
            writer.println("=".repeat(80));
            writer.close();
            writer = null;
            initialized = false;
        }
    }
    
    /**
     * Reset the logger for a new test run
     */
    public static synchronized void resetLogger() {
        if (writer != null) {
            writer.close();
            writer = null;
        }
        initialized = false;
    }
}
