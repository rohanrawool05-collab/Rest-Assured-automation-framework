package runners;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Report Generator class to generate Cucumber HTML reports
 */
public class ReportGenerator {
    
    public static void generateReport() {
        File reportOutputDirectory = new File("target/cucumber-reports");
        
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add("target/cucumber.json");
        
        String projectName = "REST API Automation Framework";
        
        Configuration configuration = new Configuration(reportOutputDirectory, projectName);
        configuration.setBuildNumber("1.0");
        configuration.addClassifications("Platform", "API");
        configuration.addClassifications("Environment", "QA");
        configuration.addClassifications("Framework", "REST Assured + Cucumber");
        
        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }
    
    public static void main(String[] args) {
        System.out.println("Generating Cucumber HTML Report...");
        generateReport();
        System.out.println("Report generated successfully at: target/cucumber-reports/cucumber-html-reports/overview-features.html");
    }
}
