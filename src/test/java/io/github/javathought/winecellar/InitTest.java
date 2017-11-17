package io.github.javathought.winecellar;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(VertxUnitRunner.class)
public class InitTest {

    private static final Logger LOG = LoggerFactory.getLogger(InitTest.class);

    private Vertx vertx;
    private String deploymentId;

    @Test
    public void before(TestContext context) {
        vertx = Vertx.vertx(new VertxOptions().setMaxEventLoopExecuteTime(Long.MAX_VALUE));
        Async async = context.async();
        vertx.deployVerticle(MainVerticle.class.getName(), res -> {
            if (res.succeeded()) {
                deploymentId = res.result();
                LOG.info("Verticle deployed");
                JUnitCore junit = new JUnitCore();
                Result result = junit.run(BDDRunnerTest.class);
                async.complete();
            } else {
                LOG.info("Verticle deployment failed!");
                context.fail("Verticle deployment failed!");
                async.complete();
            }
        });
    }

    @After
    public void after(TestContext context) {
        vertx.close(context.asyncAssertSuccess());
    }

}
