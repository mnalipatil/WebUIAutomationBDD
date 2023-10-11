package com.qa.automation.runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features="@target/failedrerun.txt",//to tell cucumber where is ur feature file
        glue="com.qa.automation.stefdefs", // to tell cucumber where is ur step def code
        tags="", // to tell which tagged feature file to execute
        plugin = {"pretty", // to generate reports
            "html:target/html/web_ui_automation_rerun_report.html",
            "json:target/json/json_rerun_report.json",
            "junit:target/cucumberXML_rerun_reort.xml",
            },
        monochrome = true,
        publish=true,
        dryRun=false // to tell whether to test run(true) or actual run(false)
        )
public class TestRunnerRerun {

}
