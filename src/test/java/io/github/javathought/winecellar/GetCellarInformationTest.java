package io.github.javathought.winecellar;

import io.vertx.ext.web.client.HttpResponse;
import io.vertx.core.AsyncResult;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.*;
import org.junit.runner.RunWith;

/**
 * getCellarInformation Test
 */
@RunWith(VertxUnitRunner.class)
public class GetCellarInformationTest extends BaseTest {

    @Override
    @Before
    public void before(TestContext context) {
        super.before(context);
        //TODO add some test initialization code like security token retrieval
    }

    @Override
    @After
    public void after(TestContext context) {
        //TODO add some test end code like session destroy
        super.after(context);
    }

    @Test
    public void testDefault(TestContext test) {
        Async async = test.async(1);

        // TODO set parameters for getCellarInformation request
        apiClient.getCellarInformation((AsyncResult<HttpResponse> ar) -> {
            if (ar.succeeded()) {
                
                //TODO add your asserts
            } else {
                test.fail("Request failed");
            }
            async.countDown();
        });
    }


}