package io.github.javathought.winecellar.dao;

import io.github.javathought.winecellar.model.Bottle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import rx.Single;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
}
