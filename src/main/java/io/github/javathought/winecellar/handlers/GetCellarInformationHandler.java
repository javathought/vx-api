package io.github.javathought.winecellar.handlers;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;

public class GetCellarInformationHandler implements Handler<RoutingContext> {

    @Override
    public void handle(RoutingContext routingContext) {
        // Handle getCellarInformation
        routingContext.response().setStatusCode(OK.code()).end("Hi");
    }

}