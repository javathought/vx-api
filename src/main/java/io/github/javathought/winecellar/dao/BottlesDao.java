package io.github.javathought.winecellar.dao;

import io.github.javathought.winecellar.model.Bottle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import rx.Single;

import java.util.List;

public interface BottlesDao {
    //addBottle
    void addBottle(Bottle newBottle, Handler<AsyncResult<Void>> handler);
    
    //getBottleByBottleId
    void getBottleByBottleId(Long bottleId, Handler<AsyncResult<Bottle>> handler);
    
    //getBottles
    public Single<List<Bottle>> getBottles();
    
}
