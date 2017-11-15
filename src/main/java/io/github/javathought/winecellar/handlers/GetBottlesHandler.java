package io.github.javathought.winecellar.handlers;

import io.vertx.core.Handler;
import io.vertx.ext.web.api.RequestParameters;
import io.vertx.ext.web.RoutingContext;

public class GetBottlesHandler implements Handler<RoutingContext> {

    public GetBottlesHandler(){

    }

    @Override
    public void handle(RoutingContext routingContext) {
        // Handle getBottles
        routingContext.response().setStatusCode(501).setStatusMessage("Not Implemented").end();
    }

}