package io.github.javathought.winecellar.handlers;

import io.vertx.core.Handler;
import io.vertx.ext.web.api.RequestParameters;
import io.vertx.ext.web.RoutingContext;

public class GetCellarInformationHandler implements Handler<RoutingContext> {

    public GetCellarInformationHandler(){

    }

    @Override
    public void handle(RoutingContext routingContext) {
        // Handle getCellarInformation
        routingContext.response().setStatusCode(501).setStatusMessage("Not Implemented").end();
    }

}