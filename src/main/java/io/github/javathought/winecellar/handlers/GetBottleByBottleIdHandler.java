package io.github.javathought.winecellar.handlers;

import io.vertx.core.Handler;
import io.vertx.ext.web.api.RequestParameters;
import io.vertx.ext.web.RoutingContext;

public class GetBottleByBottleIdHandler implements Handler<RoutingContext> {

    public GetBottleByBottleIdHandler(){

    }

    @Override
    public void handle(RoutingContext routingContext) {
        RequestParameters params = routingContext.get("parsedParameters");
        // Handle getBottleByBottleId
        routingContext.response().setStatusCode(501).setStatusMessage("Not Implemented").end();
    }

}