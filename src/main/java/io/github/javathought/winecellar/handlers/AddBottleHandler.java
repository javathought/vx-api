package io.github.javathought.winecellar.handlers;

import io.github.javathought.winecellar.dao.BottlesDao;
import io.github.javathought.winecellar.dao.BottlesDaoImpl;
import io.github.javathought.winecellar.model.Bottle;
import io.vertx.core.Handler;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.api.RequestParameter;
import io.vertx.ext.web.api.RequestParameters;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class AddBottleHandler implements Handler<RoutingContext> {

    private static final Logger LOG = LoggerFactory.getLogger(AddBottleHandler.class);
    BottlesDaoImpl bottlesDao;

    public AddBottleHandler(){
        bottlesDao = new BottlesDaoImpl();
    }

    @Override
    public void handle(RoutingContext routingContext) {
        RequestParameters params = routingContext.get("parsedParameters");
        // Handle addBottle
//        routingContext.response().setStatusCode(501).setStatusMessage("Not Implemented").end();

        Bottle newBottle = null;
        RequestParameter body = params.body();
        JsonObject jsonBody = body.getJsonObject();
        try {
            newBottle = Json.mapper.readValue(jsonBody.encode(), Bottle.class);
            bottlesDao.addBottle(newBottle, event ->
                    routingContext.response().setStatusCode(201)
                            .putHeader("Content-Type", "application/json")
                            .end(jsonBody.encodePrettily()));
        } catch (IOException e) {
            // Thrown if inexistent property if passed
            LOG.warn("invalid data <{}>", jsonBody.encode());
            routingContext.response().setStatusCode(400)
                    .putHeader("Content-Type", "text/plain")
                    .end("Invalid data / io");
        }
    }

}