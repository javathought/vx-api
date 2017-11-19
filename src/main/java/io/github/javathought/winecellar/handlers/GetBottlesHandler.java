package io.github.javathought.winecellar.handlers;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.RoutingContext;

import static org.apache.commons.lang3.CharEncoding.UTF_8;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;

public class GetBottlesHandler implements Handler<RoutingContext> {

    private Vertx vertx;

    public GetBottlesHandler(Vertx vertx){
        this.vertx = vertx;
    }

    @Override
    public void handle(RoutingContext routingContext) {

        vertx.eventBus().send("bottles.get", "get", msg -> {
            if (msg.succeeded()) {
                routingContext.response().setStatusCode(OK.code())
                        .putHeader("Content-Type", "application/json")
                        .end(
                                ((JsonArray)msg.result().body()).encodePrettily()
                                , UTF_8);
            }
        });
    }

}