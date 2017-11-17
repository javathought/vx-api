package io.github.javathought.winecellar;

import cucumber.api.CucumberOptions;
import cucumber.api.java.Before;
import cucumber.api.junit.Cucumber;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;

@RunWith(Cucumber.class)
@CucumberOptions(strict = false, format = { "pretty",
        "json:target/cucumber.json" })
public class BDDRunnerTest {

    static Vertx vertx;
    String deploymentId;

    @BeforeClass
    public static void before() {
        vertx = Vertx.vertx(new VertxOptions().setMaxEventLoopExecuteTime(Long.MAX_VALUE));
        vertx.deployVerticle(MainVerticle.class.getName());
    }

    @AfterClass
    public static void after() {
        vertx.undeploy(MainVerticle.class.getName());
        vertx.close();
    }

}
