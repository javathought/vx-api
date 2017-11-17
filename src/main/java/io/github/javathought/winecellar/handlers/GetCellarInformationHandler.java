package io.github.javathought.winecellar.handlers;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

public class GetCellarInformationHandler implements Handler<RoutingContext> {

    @Override
    public void handle(RoutingContext routingContext) {
        // Handle getCellarInformation
        routingContext.response().setStatusCode(200).end("Hi");
    }

}