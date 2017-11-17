package io.github.javathought.winecellar.dao;

import io.github.javathought.winecellar.model.Bottle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.jboss.weld.vertx.VertxConsumer;
import org.jboss.weld.vertx.VertxEvent;
import rx.Single;
import v2.io.swagger.models.auth.In;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@ApplicationScoped
public class BottlesDaoImpl {

    Map<Long, Bottle> bottles = new TreeMap<>();

//    @Override
    public void addBottle(Bottle newBottle, Handler<AsyncResult<Void>> handler) {
        bottles.put(newBottle.getId(), newBottle);
        handler.handle(Future.succeededFuture());
    }

//    @Override
    public void getBottleByBottleId(Long bottleId, Handler<AsyncResult<Bottle>> handler) {
        handler.handle(Future.succeededFuture(bottles.get(bottleId)));
    }

    public Single<List<Bottle>> getBottles() {

        return Single.just(new ArrayList<>(bottles.values()));
    }

    private void getBottleByBottleId(@Observes @VertxConsumer("bottles.getById") VertxEvent event) {
        Long bottleId = ((JsonObject) event.getMessageBody()).getLong("bottle_id");

        if (bottles.containsKey(bottleId)) {
            event.setReply(Json.encode(bottles.get(bottleId)));
        } else {
            event.fail(404, "No bottle with this id");
        }

    }



    private   void addBottles(@Observes @VertxConsumer("bottles.post") VertxEvent event) {
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
