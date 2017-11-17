package io.github.javathought.winecellar.handlers;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.ReplyException;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.api.RequestParameters;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetBottleByBottleIdHandler implements Handler<RoutingContext> {

    private static final Logger LOG = LoggerFactory.getLogger(GetBottleByBottleIdHandler.class);

    private final Vertx vertx;

    public GetBottleByBottleIdHandler(Vertx vertx) {
        this.vertx = vertx;
    }

    @Override
    public void handle(RoutingContext routingContext) {
        RequestParameters params = routingContext.get("parsedParameters");
        // Handle getBottleByBottleId

        // Simulate blocking
        vertx.executeBlocking(future -> {try {
                    LOG.info("pause");
                    Thread.sleep(3*1000);
                    LOG.info("resume");
                    future.complete();
                } catch (InterruptedException e) {
            e.printStackTrace();
        }}, false,
          h ->
          {
              vertx.eventBus().send("bottles.getById",
                      new JsonObject().put("bottle_id",params.pathParameter("bottle_id").getLong()),
                      msg -> {
                          if (msg.succeeded()) {
                              routingContext.response().setStatusCode(200).end((String) msg.result().body());

                          } else {
                              if (msg.cause() instanceof ReplyException) {
                                  LOG.warn("warn ", msg.cause());
                                  routingContext.response().setStatusCode(((ReplyException)msg.cause()).failureCode()).end();
                              } else {
                                  LOG.error("Erreur ", msg.cause());
                                  routingContext.response().setStatusCode(500).end();
                              }

                          }
                      });

          }
        );



//            routingContext.response().setStatusCode(501).setStatusMessage("Not Implemented").end();
    }

}