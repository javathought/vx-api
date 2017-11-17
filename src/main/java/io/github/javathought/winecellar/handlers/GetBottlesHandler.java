package io.github.javathought.winecellar.handlers;

import io.github.javathought.winecellar.dao.BottlesDao;
import io.github.javathought.winecellar.dao.BottlesDaoImpl;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.api.RequestParameters;
import io.vertx.ext.web.RoutingContext;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import static org.apache.commons.lang3.CharEncoding.UTF_8;

public class GetBottlesHandler implements Handler<RoutingContext> {

//    private final BottlesDaoImpl bottlesDao;
    Vertx vertx;

    public GetBottlesHandler(Vertx vertx){
        this.vertx = vertx;
    }

    @Override
    public void handle(RoutingContext routingContext) {

        vertx.eventBus().send("bottles.get", "get", msg -> {
            if (msg.succeeded()) {
                routingContext.response().setStatusCode(200)
                        .putHeader("Content-Type", "application/json")
                        .end(
                                ((JsonArray)msg.result().body()).encodePrettily()
                                , UTF_8);
            }
        });
        // Handle getBottles
/*        bottlesDao.getBottles().subscribe( bottles ->

                routingContext.response().setStatusCode(200)
                .putHeader("Content-Type", "application/json")
                .end(
                        new JsonArray(Json.encode(bottles)).encodePrettily()
                        , UTF_8)
        );*/
    }

}