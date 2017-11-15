package io.github.javathought.winecellar;

import io.vertx.core.Vertx;

public class Start {
    
    public static void main (String[] args) {
        Vertx vertx;
        vertx = Vertx.vertx();
        vertx.deployVerticle(MainVerticle.class.getName());
    }
}
