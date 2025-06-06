package io.github.ninobomba.t4m.test.api.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith ( Cucumber.class )
@CucumberOptions (
		features = "src/test/resources/features/todo/jira001/TODO_FIRST_ACTION.feature",
		glue = { "io.github.ninobomba.t4m.test.api.cucumber" },
		plugin = {
				"pretty" ,
				"html:target/cucumber-reports/cucumber-pretty.html" ,
				"json:target/cucumber-reports/CucumberTestReport.json" ,
				"rerun:target/cucumber-reports/rerun.txt" },
		monochrome = true )
public class Jira001TodoRunner {
}
