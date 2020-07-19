package testRunner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		/*
		 * If we want to Run all the feature files we have then, "./Features/" If
		 * specific files:add the number of files you want to add
		 * {".//Features/Login.feature", ".//Features/Customers.feature"}, and now if
		 * out of 10 I want to run only 5 Say exactly the name of itSpecify which one
		 * you want to execute
		 * 
		 */
		features = ".//Features/", 
		glue = "stepDefinitions", 
		dryRun = false, 
		monochrome = true, 
		plugin = {"pretty", "html:test-output" },
		tags= {"@sanity,@regression"}

)
public class TestRun {

}
