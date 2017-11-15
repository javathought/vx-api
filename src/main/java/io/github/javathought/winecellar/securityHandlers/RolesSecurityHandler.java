package io.github.javathought.winecellar.securityHandlers;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

public class RolesSecurityHandler implements Handler<RoutingContext> {

    public RolesSecurityHandler(){

    }

    @Override
    public void handle(RoutingContext routingContext) {
        // Handle Roles security schema
        routingContext.next();
    }

}