package io.github.javathought.winecellar.security.handlers;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

public class RolesSecurityHandler implements Handler<RoutingContext> {

    @Override
    public void handle(RoutingContext routingContext) {
        // Handle Roles security schema
        routingContext.next();
    }

}