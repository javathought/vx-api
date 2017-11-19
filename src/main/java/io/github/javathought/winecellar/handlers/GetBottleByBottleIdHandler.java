package io.github.javathought.winecellar.handlers;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.ReplyException;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.api.RequestParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.netty.handler.codec.http.HttpResponseStatus.INTERNAL_SERVER_ERROR;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;


public class GetBottleByBottleIdHandler implements Handler<RoutingContext> {

    private static final Logger LOG = LoggerFactory.getLogger(GetBottleByBottleIdHandler.class);
    private static final String BOTTLE_ID = "bottle_id";

    private final Vertx vertx;

    public GetBottleByBottleIdHandler(Vertx vertx) {
        this.vertx = vertx;
    }

    @Override
    public void handle(RoutingContext routingContext) {
        RequestParameters params = routingContext.get("parsedParameters");
        // Handle getBottleByBottleId

        vertx.eventBus().send("bottles.getById",
                new JsonObject().put("bottle_id", params.pathParameter(BOTTLE_ID).getLong()),
                msg -> {
                    if (msg.succeeded()) {
                        routingContext.response().putHeader("Content-Type", "application/json")
                                .setStatusCode(OK.code()).end((String) msg.result().body());

                    } else {
                        if (msg.cause() instanceof ReplyException) {
                            LOG.warn("warn ", msg.cause());
                            routingContext.response().setStatusCode(((ReplyException) msg.cause()).failureCode()).end();
                        } else {
                            LOG.error("Erreur ", msg.cause());
                            routingContext.response().setStatusCode(INTERNAL_SERVER_ERROR.code()).end();
                        }

                    }
                });

    }

}