package io.github.javathought.winecellar.dao;

import io.github.javathought.winecellar.model.Bottle;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.jboss.weld.vertx.VertxConsumer;
import org.jboss.weld.vertx.VertxEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

@ApplicationScoped
public class BottlesDaoImpl {

    private Map<Long, Bottle> bottles = new TreeMap<>();

    void getBottleByBottleId(@Observes @VertxConsumer("bottles.getById") VertxEvent event) {
        Long bottleId = ((JsonObject) event.getMessageBody()).getLong("bottle_id");

        if (bottles.containsKey(bottleId)) {
            event.setReply(Json.encode(bottles.get(bottleId)));
        } else {
            event.fail(404, "No bottle with this id");
        }

    }

    void addBottles(@Observes @VertxConsumer("bottles.post") VertxEvent event) {
        JsonObject body = (JsonObject) event.getMessageBody();

        try {
            Bottle newBottle = Json.mapper.readValue(body.encode(), Bottle.class);
            bottles.put(newBottle.getId(), newBottle);
            event.setReply(201);
        } catch (IOException e) {
            event.fail(500, e.getLocalizedMessage());
        }

    }

    void getBottles(@Observes @VertxConsumer("bottles.get") VertxEvent event) {
        // Reply to the message - io.vertx.core.eventbus.Message.reply(Object)

        event.setReply(new JsonArray(Json.encode(new ArrayList<>(bottles.values()))));
    }
}
