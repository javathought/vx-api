package io.github.javathought.winecellar;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.ext.unit.Async;
import org.junit.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

@RunWith(Cucumber.class)
@CucumberOptions(strict = false, format = { "pretty",
        "json:target/cucumber.json" })
public class BDDRunnerTest {

    private static Vertx vertx;
    private static CountDownLatch latch;


    @BeforeClass
    public static void startVertX() throws Exception {
        latch = new CountDownLatch(1);
        final boolean[] failed = {false};

        vertx = Vertx.vertx(new VertxOptions().setMaxEventLoopExecuteTime(Long.MAX_VALUE));
        vertx.deployVerticle(MainVerticle.class.getName(), res -> {
            if (res.succeeded()) {
                latch.countDown();
            } else {
                failed[0] = true;
                latch.countDown();
            }
        });
        latch.await();
        if (failed[0]) {
            throw new Exception("error starting Vert.X");
        }


    }

    @AfterClass
    public static void stopVertX() {
        vertx.close();
    }


}
