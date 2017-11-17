package io.github.javathought.winecellar;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.Ignore;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(strict = false, format = { "pretty",
        "json:target/cucumber.json" })
@Ignore
public class BDDRunnerTest {

}
