package io.github.javathought.winecellar.handlers;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.api.RequestParameter;
import io.vertx.ext.web.api.RequestParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.netty.handler.codec.http.HttpResponseStatus.INTERNAL_SERVER_ERROR;

public class AddBottleHandler implements Handler<RoutingContext> {

    private static final Logger LOG = LoggerFactory.getLogger(AddBottleHandler.class);
    private Vertx vertx;

    public AddBottleHandler(Vertx vertx){
        this.vertx = vertx;
    }

    @Override
    public void handle(RoutingContext routingContext) {
        RequestParameters params = routingContext.get("parsedParameters");
        // Handle addBottle

        RequestParameter body = params.body();
        JsonObject jsonBody = body.getJsonObject();

        vertx.eventBus().send("bottles.post", jsonBody, msg -> {


            if (msg.succeeded()) {
                    routingContext.response().setStatusCode((int) msg.result().body())
                            .putHeader("Content-Type", "application/json")
                            .end(jsonBody.encodePrettily());
            } else {
                LOG.error("Erreur ", msg.cause());
                routingContext.response().setStatusCode(INTERNAL_SERVER_ERROR.code()).end();
            }
        } );
    }

}