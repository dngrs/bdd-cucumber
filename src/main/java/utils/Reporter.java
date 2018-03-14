package utils;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Igor Odokienko
 */
public class Reporter {

    private static final String PROJECT_NAME = "cucumberProject";
    private static final String REPORT_DIRECTORY = "target/cucumber-parallel/";

    public static void generateReport() {
        File reportDirectory = new File(REPORT_DIRECTORY);
        List<String> jsonFiles = new ArrayList<>();
        File[] allFiles = reportDirectory.listFiles();
        if (Objects.nonNull(allFiles)) {
            for (File file : allFiles) {
                if (file.isFile() && file.getName().endsWith(".json")) {
                    jsonFiles.add(REPORT_DIRECTORY + file.getName());
                }
            }
        }
        Configuration configuration = new Configuration(reportDirectory, PROJECT_NAME);
        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }

}
